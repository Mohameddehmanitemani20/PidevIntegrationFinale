<?php

namespace App\Controller;
use App\Entity\Reclamation;
use App\Form\Reclamation1Type;
use App\Repository\ReclamationRepository;
use App\Entity\Categorie;
use App\Form\CategorieType;
use App\Repository\CategorieRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Form\Extension\Core\Type\{TextType,ButtonType,EmailType,HiddenType,PasswordType,TextareaType,SubmitType,NumberType,DateType,MoneyType,BirthdayType};
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\SerializerInterface;
use Doctrine\ORM\EntityManagerInterface;
/**
 * @Route("/categorie")
 */
class CategorieController extends AbstractController
{
    /**
     * @Route("/", name="app_categorie_index", methods={"GET"})
     */
    public function index(CategorieRepository $categorieRepository, Request $request,PaginatorInterface $paginator): Response
    {   
        $donnees = $this->getDoctrine()->getRepository(Categorie::class)->findAll();

        $categorie = $paginator->paginate(
            $donnees, // Requête contenant les données à paginer (ici nos articles)
            $request->query->getInt('page', 1),3 
        );
        return $this->render('categorie/index.html.twig', [
            'categories' => $categorieRepository->findAll(),
            'numbers' => $reclamationRepository->num()
        ]);
    }
    /**
     * @Route("/catJSON", name="catJSON")
     */
    public function categJSON(EntityManagerInterface $entityManager,NormalizerInterface $Normalizer)
    {
        $cat = $entityManager
            ->getRepository(Categorie::class)
            ->findAll();
$jsonContent =$Normalizer->normalize($cat,'json',['groups'=>'post:read']);

return new Response(json_encode($jsonContent));
       
    }

/**
     * @Route("/addJSON/new", name="addCatJSON")
     */
    public function AddCategJSON(Request $request,EntityManagerInterface $entityManager,NormalizerInterface $Normalizer)
    {
$content=$request->getContent();

        $categorie = new categorie();
   //  $data=$Normalizer->deserialize($content,Formation::class,'json');
 $categorie->setNomcateg($request->get('nomcateg'));
     
            $entityManager->persist($categorie);

            $entityManager->flush();
$jsonContent =$Normalizer->normalize($categorie,'json',['groups'=>'post:read']);
//dump($formations);
return new Response('categorie ajouté'.json_encode($jsonContent));
        // return $this->render('formation/formationjson.html.twig', [
        //     'formations' => $jsonContent,
        // ]);
    }
/**
     * @Route("/updateJSON/edit", name="updateCatJSON")
     */
    public function updateCategJSON(CategorieRepository $rep,Request $request,SerializerInterface $serializer,EntityManagerInterface $entityManager,NormalizerInterface $Normalizer)
    {
$content=json_decode($request->getContent(), true);

        $cat = $entityManager
        ->getRepository(Categorie::class)
        ->find($request->get('idcateg'));

  empty($request->get('nomcateg')) ? true : $cat->setNomcateg($request->get('nomcateg'));
 

  $jsonContent =$Normalizer->normalize($cat,'json',['groups'=>'post:read']);

            $entityManager->flush();

return new Response('Category updated'.json_encode($jsonContent));
  
    }


      /**
     * @Route("/deletecatJSON/{id}", name="deletecatJSON")
     */
    public function deleteJSON($id,CategorieRepository $rep,Request $request,SerializerInterface $serializer,EntityManagerInterface $entityManager,NormalizerInterface $Normalizer)
    {
$content=json_decode($request->getContent(), true);

        $cat = $entityManager
        ->getRepository(Categorie::class)
        ->find($id);

        $entityManager->remove($cat);


            $entityManager->flush();
            $jsonContent =$Normalizer->normalize($cat,'json',['groups'=>'post:read']);

return new Response('Categorie supprimée'.json_encode($jsonContent));
  
    }

    /**
     * @Route("/new", name="app_categorie_new", methods={"GET", "POST"})
     */
    public function new(Request $request, CategorieRepository $categorieRepository): Response
    {
        $categorie = new Categorie();
        $form = $this->createForm(CategorieType::class, $categorie);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $categorieRepository->add($categorie);
            return $this->redirectToRoute('app_categorie_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('categorie/new.html.twig', [
            'categorie' => $categorie,
            'form' => $form->createView(),
            'numbers' => $reclamationRepository->num()
        ]);
    }

    /**
     * @Route("/{idcateg}", name="app_categorie_show", methods={"GET"})
     */
    public function show(Categorie $categorie): Response
    {
        return $this->render('categorie/show.html.twig', [
            'categorie' => $categorie,
            'numbers' => $reclamationRepository->num()
        ]);
    }

    /**
     * @Route("/{idcateg}/edit", name="app_categorie_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Categorie $categorie, CategorieRepository $categorieRepository): Response
    {
        $form = $this->createForm(CategorieType::class, $categorie);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $categorieRepository->add($categorie);
            return $this->redirectToRoute('app_categorie_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('categorie/edit.html.twig', [
            'categorie' => $categorie,
            'form' => $form->createView(),
            'numbers' => $reclamationRepository->num()
        ]);
    }

    /**
     * @Route("/{idcateg}", name="app_categorie_delete", methods={"POST"})
     */
    public function delete(Request $request, Categorie $categorie, CategorieRepository $categorieRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$categorie->getIdcateg(), $request->request->get('_token'))) {
            $categorieRepository->remove($categorie);
        }

        return $this->redirectToRoute('app_categorie_index', [], Response::HTTP_SEE_OTHER);
    }


    
}
