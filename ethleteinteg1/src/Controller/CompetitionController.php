<?php

namespace App\Controller;

use App\Entity\Competition;
use App\Form\CompetitionType;
use App\Form\SearchForm;
use App\Repository\CompetitionRepository;
use Doctrine\ORM\EntityManagerInterface;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Spipu\Html2Pdf\Html2Pdf;
use App\Service\T_HTML2PDF;




/**
 * @Route("/competition")
 */
class CompetitionController extends AbstractController
{
    /**
     * @Route("/", name="app_competition_index", methods={"GET"})
     */
    public function index(Request $request, PaginatorInterface $paginator, EntityManagerInterface $entityManager): Response
    {
        $donne = $entityManager
            ->getRepository(Competition::class)
            ->findAll();
        $competitions = $paginator->paginate(
            $donne,
            $request->query->getInt('page',1),4

        );

        return $this->render('competition/index.html.twig', [
            'competitions' => $competitions,
        ]);
    }
    /**
     * @Route("/userindex", name="app_competition_userindex", methods={"GET"})
     */
    public function userindex(EntityManagerInterface $entityManager): Response
    {
        $competitions = $entityManager
            ->getRepository(Competition::class)
            ->findAll();

        return $this->render('competition/userindex.html.twig', [
            'competition' => $competitions,
        ]);
    }
    /**
     * @Route("/affiche/{idCompetition}", name="app_competition_showf", methods={"GET"})
     */
    public function showf(Competition $competitions): Response
    {
        return $this->render('competition/usershow.html.twig', [
            'competition' => $competitions,
        ]);
    }

    /**
     * @Route("/new", name="app_competition_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $competition = new Competition();
        $form = $this->createForm(CompetitionType::class, $competition);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($competition);
            $entityManager->flush();

            return $this->redirectToRoute('app_competition_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('competition/new.html.twig', [
            'competition' => $competition,
            'form' => $form->createView(),
        ]);

    }

    /**
     * @Route("/{idCompetition}", name="app_competition_show", methods={"GET"})
     */
    public function show(Competition $competition): Response
    {
        return $this->render('competition/show.html.twig', [
            'competition' => $competition,
        ]);
    }

    /**
     * @Route("/{idCompetition}/edit", name="app_competition_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Competition $competition, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(CompetitionType::class, $competition);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_competition_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('competition/edit.html.twig', [
            'competition' => $competition,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idCompetition}", name="app_competition_delete", methods={"POST"})
     */
    public function delete(Request $request, Competition $competition, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete' . $competition->getIdCompetition(), $request->request->get('_token'))) {
            $entityManager->remove($competition);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_competition_index', [], Response::HTTP_SEE_OTHER);
    }

    /**
     * @Route("/{idCompetition}/competition_pdf", name="competition", methods={"GET"})
     */
    public function showPdf(EntityManagerInterface $entityManager): Response
    {
        $competitions = $entityManager
            ->getRepository(Competition::class)
            ->findAll();
        $datee = new \DateTime('@' . strtotime('now'));;

        $template = $this->render('competition/print.html.twig', [
            'competitions' => $competitions,
            'datee' => $datee

        ]);
        $html2pdf = new T_HTML2PDF('P', 'A4', 'fr', true, 'UTF-8', array(10, 15, 10, 15));
        $html2pdf->create('P', 'A4', 'fr', true, 'UTF-8', array(10, 15, 10, 15));
        return $html2pdf->generatePdf($template, "facture");
    }

    /**
     * @Route("/", name="app_competition_recherche", methods={"POST"})
     */
    public function rechercher(Request $request, PaginatorInterface $paginator,CompetitionRepository $repository)
    {
        $form=$this->createForm(SearchForm::class);
        if( $request->isMethod("POST"))
        {
            $nom =$request->get('nom');
            $competitions =$repository->findEntities($nom);
            $competitions = $paginator->paginate(
                $competitions,
                $request->query->getInt('page',1),4

            );
        }

        return $this->render('competition/index.html.twig', [
            'competitions' => $competitions,'form'=>$form->createView()
        ]);}






}
