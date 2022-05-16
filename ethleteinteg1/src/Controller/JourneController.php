<?php

namespace App\Controller;

use App\Entity\Journe;
use App\Form\JourneType;
use App\Form\SearchForm;
use App\Repository\CompetitionRepository;
use App\Repository\JourneRepository;
use App\Service\T_HTML2PDF;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;


/**
 * @Route("/journe")
 */
class JourneController extends AbstractController
{
    /**
     * @Route("/", name="app_journe_index", methods={"GET"})
     */
    public function index(Request $request, PaginatorInterface $paginator, EntityManagerInterface $entityManager): Response
    {
        $donne = $entityManager
            ->getRepository(Journe::class)
            ->findAll();
        $journes = $paginator->paginate(
            $donne,
            $request->query->getInt('page',1),4
        );

        return $this->render('journe/index.html.twig', [
            'journes' => $journes,
        ]);
    }
    /**
     * @Route("/userindex", name="app_journe_userindex", methods={"GET"})
     */
    public function userindex(EntityManagerInterface $entityManager): Response
    {
        $journe = $entityManager
            ->getRepository(Journe::class)
            ->findAll();

        return $this->render('journe/userindex.html.twig', [
            'journe' => $journe,
        ]);
    }
    /**
     * @Route("/affiche/{idJourne}", name="app_journe_showf", methods={"GET"})
     */
    public function showf(Journe $journe): Response
    {
        return $this->render('journe/usershow.html.twig', [
            'journe' => $journe,
        ]);
    }

    /**
     * @Route("/", name="app_journe_recherche", methods={"POST"})
     */
    public function rechercher(Request $request, PaginatorInterface $paginator,JourneRepository $repository)
    {
        $em = $this-> getDoctrine()->getManager();
        $journes=$em->getRepository(Journe::class)->findall();

        if( $request->isMethod("POST"))
        {
            $numjourne =$request->get('numjourne');
            $journes =$em->getRepository("App:Journe")->findBy(array('numjourne'=> $numjourne),array('idJourne' => 'DESC'));
            $journes = $paginator->paginate(
                $journes, $request->query->getInt('page',1),4

            );

        }
        return $this->render('journe/index.html.twig', [
            'journes' => $journes,
        ]);
    }

    /**
     * @Route("/new", name="app_journe_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $journe = new Journe();
        $form = $this->createForm(JourneType::class, $journe);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($journe);
            $entityManager->flush();

            return $this->redirectToRoute('app_journe_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('journe/new.html.twig', [
            'journe' => $journe,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idJourne}", name="app_journe_show", methods={"GET"})
     */
    public function show(Journe $journe): Response
    {
        return $this->render('journe/show.html.twig', [
            'journe' => $journe,
        ]);
    }

    /**
     * @Route("/{idJourne}/edit", name="app_journe_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Journe $journe, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(JourneType::class, $journe);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_journe_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('journe/edit.html.twig', [
            'journe' => $journe,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idJourne}", name="app_journe_delete", methods={"POST"})
     */
    public function delete(Request $request, Journe $journe, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$journe->getIdJourne(), $request->request->get('_token'))) {
            $entityManager->remove($journe);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_journe_index', [], Response::HTTP_SEE_OTHER);
    }
    /**
     * @Route("/{idJourne}/journe_pdf", name="journe", methods={"GET"})
     */
    public function showPdf(EntityManagerInterface $entityManager): Response
    {
        $journes = $entityManager
            ->getRepository(Journe::class)
            ->findAll();
        $datee = new \DateTime('@' . strtotime('now'));;

        $template = $this->render('journe/print.html.twig', [
            'journes' => $journes,
            'datee' => $datee

        ]);
        $html2pdf = new T_HTML2PDF('P', 'A4', 'fr', true, 'UTF-8', array(10, 15, 10, 15));
        $html2pdf->create('P', 'A4', 'fr', true, 'UTF-8', array(10, 15, 10, 15));
        return $html2pdf->generatePdf($template, "facture");
    }

/*
    /**
     * @Route("/sort", name="app_journe_sort", methods={"GET"})
     */
    /*public function sortbynumjourne(EntityManagerInterface $entityManager): Response
    {
        $journes = $entityManager
            ->getRepository(Journe::class)
            ->findBy(array(),array('numjourne'=>'DESC'));

        return $this->render('journe/index.html.twig', [
            'journes' => $journes,
        ]);
    }*/

}
