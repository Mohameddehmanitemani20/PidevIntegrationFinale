<?php

namespace App\Controller;

use App\Entity\Produit;

use App\Form\ProduitType;

use App\Entity\Reclamation;
use App\Form\Reclamation1Type;
use App\Repository\ReclamationRepository;

use App\Entity\CategorySearch;
use App\Form\CategorySearchType;

use App\Entity\PriceSearch;
use App\Form\PriceSearchType;

use App\Entity\PropertySearch;
use App\Form\PropertySearchType;
use App\Repository\ProduitRepository;



use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\Routing\Annotation\Route;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\NumberType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Mgilet\NotificationBundle\Entity\Notification;
use Mgilet\NotificationBundle\NotifiableInterface;



/**
 * @Route("/produit")
 */
class ProduitController extends AbstractController
{  
    /**
     * @Route("/",name="article_par_cat")
     * Method({"GET", "POST"})
     */
   
     public function Filtrer(Request $request) {
        $propertySearch = new PropertySearch();
        $priceSearch = new PriceSearch();
        $categorySearch = new CategorySearch();

        $forms = $this->createForm(CategorySearchType::class,$categorySearch);
        $formp = $this->createForm(PriceSearchType::class,$priceSearch);
        $formr = $this->createForm(PropertySearchType::class,$propertySearch);
        $forms->handleRequest($request);
        $formp->handleRequest($request);
        $formr->handleRequest($request);
  
        $produit= $this->getDoctrine()->getRepository(Produit::class)->findAll();
   

        //Recherche par nom//
        if($formr->isSubmitted() && $formr->isValid()) {
        
             $nom = $propertySearch->getNom();   
             if ($nom!="") 
            
               $produit= $this->getDoctrine()->getRepository(Produit::class)->findBy(['nomp' => $nom]);
             else   
               $produit= $this->getDoctrine()->getRepository(Produit::class)->findAll();
            }
         //Rechercher par categ//

              if($forms->isSubmitted() && $forms->isValid()) {
              $category = $categorySearch->getCategory();
             if ($category!="") 
          {
           $produit= $this->getDoctrine()->getRepository(Produit::class)->findBy(['idcateg' => $category] );
          }
          else   
            $produit= $this->getDoctrine()->getRepository(Produit::class)->findAll();
          }
        
         //recherche par prix//
        if($formp->isSubmitted() && $formp->isValid()) {
        $minPrice = $priceSearch->getMinPrice(); 
        $maxPrice = $priceSearch->getMaxPrice();
          
        $produit= $this->getDoctrine()->getRepository(Produit::class)->findByPriceRange($minPrice,$maxPrice);
    }
          
        return $this->render('produit/index.html.twig',['forms' => $forms->createView(),'formp' => $formp->createView(),'formr' => $formr->createView(),'produits' => $produit]);
    }
   

    

    /**
    * @Route("/", name="app_produit_index")
    */
    public function index(Request $request): Response
    {   
        
          $produit= $this->getDoctrine()->getRepository(Produit::class)->findAll();
     
        
       return  $this->render('produit/index.html.twig',[ 'form' =>$form->createView(), 'produits' => $produit]); 
    
}
    

    /**
     * @Route("/new", name="app_produit_new", methods={"GET", "POST"})
     */
      public function new(Request $request, ProduitRepository $produitRepository): Response
    {
        $produit = new Produit();
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $produitRepository->add($produit);
            return $this->redirectToRoute('app_produit_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('produit/new.html.twig', [
            'produit' => $produit,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idp}", name="app_produit_show", methods={"GET"})
     */
    public function show(Produit $produit): Response
    {
        return $this->render('produit/show.html.twig', [
            'produit' => $produit,
        ]);
    }

    /**
     * @Route("/{idp}/edit", name="app_produit_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Produit $produit, ProduitRepository $produitRepository): Response
    {
        $form = $this->createForm(ProduitType::class, $produit);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $produitRepository->add($produit);
            return $this->redirectToRoute('app_produit_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('produit/edit.html.twig', [
            'produit' => $produit,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idp}", name="app_produit_delete", methods={"POST"})
     */
    public function delete(Request $request, Produit $produit, ProduitRepository $produitRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$produit->getIdp(), $request->request->get('_token'))) {
            $produitRepository->remove($produit);
        }

        return $this->redirectToRoute('app_produit_index', [], Response::HTTP_SEE_OTHER);
    }

    




} 
