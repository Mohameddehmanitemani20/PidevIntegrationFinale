<?php

namespace App\Controller;

use App\Entity\Commentaire;
use App\Entity\Formation;
use App\Entity\User;
use App\Form\CommentaireType;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\SerializerInterface;

/**
 * @Route("/commentaire")
 */
class CommentaireController extends AbstractController
{
    /**
     * @Route("/", name="commentaire_index", methods={"GET"})
     */
    public function index(EntityManagerInterface $entityManager): Response
    {
        $commentaires = $entityManager
            ->getRepository(Commentaire::class)
            ->findAll();

        return $this->render('commentaire/index.html.twig', [
            'commentaires' => $commentaires,
        ]);
    }

    /**
     * @Route("/new/{idF}/{idU}", name="commentaire_new", methods={"GET", "POST"})
     */
    public function new($idF,$idU,Request $request, EntityManagerInterface $entityManager): Response
    { 
        
        
        $f= $entityManager
        ->getRepository(Formation::class)
        ->find($idF);
        $p= $entityManager
        ->getRepository(User::class)
        ->find($idU);

       


        $commentaire = new Commentaire();
        $form = $this->createForm(CommentaireType::class, $commentaire);
        $form->handleRequest($request);
        $commentaire->setIdFormation($f);
        $commentaire->setIdParticipant($p);
        $rr = $this->filterwords($commentaire->getContenu());

                
        $commentaire->setContenu($rr);
        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($commentaire);
            $entityManager->flush();

            return $this->redirectToRoute('list1', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('commentaire/new.html.twig', [
            'commentaire' => $commentaire,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idCommentaire}", name="commentaire_show", methods={"GET"})
     */
    public function show(Commentaire $commentaire): Response
    {
        return $this->render('commentaire/show.html.twig', [
            'commentaire' => $commentaire,
        ]);
    }

    /**
     * @Route("/{idCommentaire}/edit", name="commentaire_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Commentaire $commentaire, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(CommentaireType::class, $commentaire);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('commentaire_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('commentaire/edit.html.twig', [
            
            'commentaire' => $commentaire,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idCommentaire}", name="commentaire_delete", methods={"POST"})
     */
    public function delete(Request $request, Commentaire $commentaire, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$commentaire->getIdCommentaire(), $request->request->get('_token'))) {
            $entityManager->remove($commentaire);
            $entityManager->flush();
        }

        return $this->redirectToRoute('commentaire_index', [], Response::HTTP_SEE_OTHER);
    }



    public function filterwords($text)
    {
        $filterWords = array('stupid', 'mauvaiseformation', '0/20', 'formateur??');
        $filterCount = count($filterWords);
        $str = "";
        $data = preg_split('/\s+/',  $text);
        foreach($data as $s){
            $g = false;
            foreach ($filterWords as $lib) {
                if($s == $lib){
                    $t = "";
                    for($i =0; $i<strlen($s); $i++) $t .= "*";
                    $str .= $t . " ";
                    $g = true;
                    break;
                }
            }
            if(!$g)
            $str .= $s . " ";
        }
        return $str;
    }



    /**
     * @Route("/newJSON/{idF}/{idU}", name="commentaire_new_JSON", methods={"GET", "POST"})
     */
    public function newJSON($idF,$idU,Request $request, EntityManagerInterface $entityManager,NormalizerInterface $Normalizer)
    { 
        
        
        $f= $entityManager
        ->getRepository(Formation::class)
        ->find($idF);
        $p= $entityManager
        ->getRepository(User::class)
        ->find($idU);

       


        $commentaire = new Commentaire();
        $rr = $this->filterwords($request->get('contenu'));

                
        $commentaire->setContenu($rr);

        $commentaire->setIdFormation($f);
        $commentaire->setIdParticipant($p);

                
  
    
            $entityManager->persist($commentaire);
            $entityManager->flush();
            
            $jsonContent =$Normalizer->normalize($commentaire,'json',['groups'=>'post:read']);
            //dump($formations);
            return new Response('commentaire ajouté'.json_encode($jsonContent));
      

}}
