<?php

namespace App\Controller;

use App\Entity\Reclamation;
use App\Form\Reclamation1Type;
use App\Form\Reclamation2Type;
use App\Repository\ReclamationRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Mime\Email;



/**
 * @Route("/reclamation")
 */
class ReclamationController extends AbstractController
{   /**
    * @Route("/count", name="count", methods={"GET"})
    */

    public function nombreRec(ReclamationRepository $reclamationRepository): Response
    {   
        return $this->render('back.html.twig', [
      'numbers' => $reclamationRepository->num(),
        ]);
    }

   /**
     * @Route("/", name="app_reclamation_index", methods={"GET"})
     */
    public function index(ReclamationRepository $reclamationRepository): Response
    {       
           return $this->render('reclamation/index.html.twig', [
            'reclamations' => $reclamationRepository->findAll(),
            'numbers' => $reclamationRepository->num()
        ]);
    }

    /**
     * @Route("/new", name="app_reclamation_new", methods={"GET", "POST"})
     */
    public function new(Request $request, ReclamationRepository $reclamationRepository): Response
    {
        $user = $this->get('security.token_storage')->getToken()->getUser();
        $reclamation = new Reclamation();
        $form = $this->createForm(Reclamation1Type::class, $reclamation);
        $form->handleRequest($request);
        $reclamation->setId($user->getId());
        if ($form->isSubmitted() && $form->isValid()) {
            $reclamationRepository->add($reclamation);
            return $this->redirectToRoute('app_reclamation_index', [], Response::HTTP_SEE_OTHER);
        }
        
        return $this->render('reclamation/new.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form->createView(),
            'numbers' => $reclamationRepository->num()
        ]);
      
    }

    /**
     * @Route("/{idr}", name="app_reclamation_show", methods={"GET"})
     */
    public function show(Reclamation $reclamation,ReclamationRepository $reclamationRepository): Response
    {  
        return $this->render('reclamation/show.html.twig', [
            'reclamation' => $reclamation,
            'numbers' => $reclamationRepository->num()
        ]);
    }

    /**
     * @Route("/{idr}/edit", name="app_reclamation_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Reclamation $reclamation, ReclamationRepository $reclamationRepository,MailerInterface $mailer): Response
    {  
        $form = $this->createForm(Reclamation2Type::class, $reclamation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $reclamationRepository->add($reclamation);
            $user = $this->get('security.token_storage')->getToken()->getUser();
            $email = (new Email())
            ->from('rihemmatoussi2@gmail.com')
            ->to($user->getEmail())
            ->subject('Confirmation Reclamation')
           // ->htmlTemplate('utilisateur/template.html.twig')
            ->text('texxttttt');
          $mailer->send($email);

            return $this->redirectToRoute('app_reclamation_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reclamation/edit.html.twig', [
            'reclamation' => $reclamation,
            'form' => $form->createView(),
            'numbers' => $reclamationRepository->num()
        ]);
    }

    /**
     * @Route("/{idr}", name="app_reclamation_delete", methods={"POST"})
     */
    public function delete(Request $request, Reclamation $reclamation, ReclamationRepository $reclamationRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reclamation->getIdr(), $request->request->get('_token'))) {
            $reclamationRepository->remove($reclamation);
        }

        return $this->redirectToRoute('app_reclamation_index', [], Response::HTTP_SEE_OTHER);
    }
}
