<?php

namespace App\Controller;
use App\Entity\Reclamation;
use App\Form\Reclamation1Type;
use App\Repository\ReclamationRepository;
use App\Entity\Raison;
use App\Form\RaisonType;
use App\Repository\RaisonRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/raison")
 */
class RaisonController extends AbstractController
{
    /**
     * @Route("/", name="app_raison_index", methods={"GET"})
     */
    public function index(RaisonRepository $raisonRepository): Response
    {
        return $this->render('raison/index.html.twig', [
            'raisons' => $raisonRepository->findAll(),
            'numbers' => $reclamationRepository->num()
        ]);
    }

    /**
     * @Route("/new", name="app_raison_new", methods={"GET", "POST"})
     */
    public function new(Request $request, RaisonRepository $raisonRepository): Response
    {
        $raison = new Raison();
        $form = $this->createForm(RaisonType::class, $raison);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $raisonRepository->add($raison);
            return $this->redirectToRoute('app_raison_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('raison/new.html.twig', [
            'raison' => $raison,
            'form' => $form->createView(),
            'numbers' => $reclamationRepository->num()
        ]);
    }

    /**
     * @Route("/{idraison}", name="app_raison_show", methods={"GET"})
     */
    public function show(Raison $raison): Response
    {
        return $this->render('raison/show.html.twig', [
            'raison' => $raison,
            'numbers' => $reclamationRepository->num()
        ]);
    }

    /**
     * @Route("/{idraison}/edit", name="app_raison_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Raison $raison, RaisonRepository $raisonRepository): Response
    {
        $form = $this->createForm(RaisonType::class, $raison);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $raisonRepository->add($raison);
            return $this->redirectToRoute('app_raison_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('raison/edit.html.twig', [
            'raison' => $raison,
            'form' => $form->createView(),
            'numbers' => $reclamationRepository->num()
        ]);
    }

    /**
     * @Route("/{idraison}", name="app_raison_delete", methods={"POST"})
     */
    public function delete(Request $request, Raison $raison, RaisonRepository $raisonRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$raison->getIdraison(), $request->request->get('_token'))) {
            $raisonRepository->remove($raison);
        }

        return $this->redirectToRoute('app_raison_index', [], Response::HTTP_SEE_OTHER);
    }
}
