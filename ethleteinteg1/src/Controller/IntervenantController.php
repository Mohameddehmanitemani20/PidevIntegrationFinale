<?php

namespace App\Controller;

use App\Entity\Intervenant;
use App\Form\IntervenantType;
use App\Repository\IntervenantRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\SerializerInterface;

/**
 * @Route("/intervenant")
 */
class IntervenantController extends AbstractController
{
    /**
     * @Route("/", name="app_intervenant_index", methods={"GET"})
     */
    public function index(EntityManagerInterface $entityManager): Response
    {
        $intervenants = $entityManager
            ->getRepository(Intervenant::class)
            ->findAll();

        return $this->render('intervenant/index.html.twig', [
            'intervenants' => $intervenants,
        ]);
    }
    /**
     * @Route("/IntJSON", name="IntJSON")
     */
    public function listJSON(EntityManagerInterface $entityManager,NormalizerInterface $Normalizer)
    {
        $int = $entityManager
            ->getRepository(Intervenant::class)
            ->findAll();
$jsonContent =$Normalizer->normalize($int,'json',['groups'=>'post:read']);

return new Response(json_encode($jsonContent));
       
    }
/**
     * @Route("/addJSON/add", name="addIntJSON")
     */
    public function AddJSON(Request $request,EntityManagerInterface $entityManager,NormalizerInterface $Normalizer)
    {
$content=$request->getContent();

$intervenant = new Intervenant();
  
 $intervenant->setImageIn('image');
     $intervenant->setNom($request->get('nom'));
     $intervenant->setPrenom($request->get('prenom'));
     $intervenant->setEmail($request->get('email'));
 
$intervenant->setIdTypeint($request->get('type'));
$intervenant->setTelephone($request->get('telephone'));
            $entityManager->persist($intervenant);

            $entityManager->flush();
$jsonContent =$Normalizer->normalize($intervenant,'json',['groups'=>'post:read']);

return new Response('Intervenant ajouté'.json_encode($jsonContent));
       
    }

     /**
     * @Route("/deleteJSON/{id}", name="deleteIntJSON")
     */
    public function deleteJSON($id,IntervenantRepository $rep,Request $request,SerializerInterface $serializer,EntityManagerInterface $entityManager,NormalizerInterface $Normalizer)
    {
$content=json_decode($request->getContent(), true);

        $int = $rep->find($id);

        $entityManager->remove($int);


            $entityManager->flush();
            $jsonContent =$Normalizer->normalize($int,'json',['groups'=>'post:read']);

return new Response('Intervenant supprimé'.json_encode($jsonContent));
  
    }

    /**
     * @Route("/UpdateJSON/edit", name="UpdateIntJSON")
     */
    public function UpdateJSON(Request $request,NormalizerInterface $Normalizer)
    {
       
        $em = $this->getDoctrine()->getManager();
        $intervenant = $this->getDoctrine()->getRepository(Intervenant::class)->find($request->get('id'));
        $intervenant->setNom($request->get('nom'));
        $intervenant->setPrenom($request->get('prenom'));
        $intervenant->setEmail($request->get('email'));
 
        $intervenant->setIdTypeint($request->get('type'));
        $intervenant->setTelephone($request->get('telephone'));

        $em->flush();
        $jsonContent = $Normalizer->normalize($intervenant,'json',['groups'=>'post:read']);
        return new Response("Update successfully".json_encode($jsonContent));
    }


/**
     * @Route("/", name="app_recherche", methods={"GET","POST"})
     */
    public function rechercher(Request $request,IntervenantRepository $repository)
    {
        if( $request->isMethod("POST"))
        {
            $nom =$request->get('nom');
            $intervenants =$repository->findEntities($nom);
        }

        return $this->render('intervenant/index.html.twig', [
            'intervenants' => $intervenants,
        ]);}

         /**
     * @Route("/rechercheJSON/{nom}", name="rechercheJSON")
     */
    public function rechercherJSON($nom,Request $request,IntervenantRepository $repository,NormalizerInterface $Normalizer)
    {
      
            $intervenants =$repository->findEntities($nom);

            $jsonContent =$Normalizer->normalize($intervenants,'json',['groups'=>'post:read']);
return new Response(json_encode($jsonContent));
     }


    /**
     * @Route("/new", name="app_intervenant_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $intervenant = new Intervenant();
        $form = $this->createForm(IntervenantType::class, $intervenant);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $file = $form->get('imageIn')->getData();
            $Filename = md5(uniqid()).'.'.$file->guessExtension();
            try {
                $file->move(
                    $this->getParameter('images'),
                    $Filename
                );
            } catch (FileException $e) {

            }
            $intervenant->setImageIn($Filename);

            $entityManager->persist($intervenant);
            $entityManager->flush();

            return $this->redirectToRoute('app_intervenant_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('intervenant/new.html.twig', [
            'intervenant' => $intervenant,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idInter}", name="app_intervenant_show", methods={"GET"})
     */
    public function show(Intervenant $intervenant): Response
    {
        return $this->render('intervenant/show.html.twig', [
            'intervenant' => $intervenant,
        ]);
    }

    /**
     * @Route("/{idInter}/edit", name="app_intervenant_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Intervenant $intervenant, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(IntervenantType::class, $intervenant);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_intervenant_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('intervenant/edit.html.twig', [
            'intervenant' => $intervenant,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idInter}", name="app_intervenant_delete", methods={"POST"})
     */
    public function delete(Request $request, Intervenant $intervenant, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$intervenant->getIdInter(), $request->request->get('_token'))) {
            $entityManager->remove($intervenant);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_intervenant_index', [], Response::HTTP_SEE_OTHER);
    }
}