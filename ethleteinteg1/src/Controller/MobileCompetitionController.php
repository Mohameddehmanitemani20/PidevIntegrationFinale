<?php
namespace App\Controller;

use App\Entity\Competition;
use App\Repository\CompetitionRepository;
use App\Service\T_HTML2PDF;
use DateTime;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\BinaryFileResponse;
use Symfony\Component\HttpFoundation\File\Exception\FileException;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\SerializerInterface;
use Symfony\Component\Form\Extension\Core\Type\{TextType,ButtonType,EmailType,HiddenType,PasswordType,TextareaType,SubmitType,NumberType,DateType,MoneyType,BirthdayType};

/**
 * @Route("/competitionmobile")
 */
class MobileCompetitionController extends AbstractController
{
    /**
     * @Route("", methods={"GET"})
     */
    public function index(CompetitionRepository $competitionRepository): Response
    {
        $competitions = $competitionRepository->findAll();

        if ($competitions) {
            return new JsonResponse($competitions, 200);
        } else {
            return new JsonResponse([], 204);
        }
    }
    /**
     * @Route("/compJSON", name="compJSON")
     */
    public function compJSON(EntityManagerInterface $entityManager,NormalizerInterface $Normalizer)
    {
        $comp = $entityManager
            ->getRepository(Competition::class)
            ->findAll();
        $jsonContent =$Normalizer->normalize($comp,'json',['groups'=>'post:read']);

        return new Response(json_encode($jsonContent));

    }

    /**
     * @Route("/addJSON/add", name="addCompJSON")
     */
    public function AddCompJSON(Request $request,EntityManagerInterface $entityManager,NormalizerInterface $Normalizer)
    {
        $content=$request->getContent();

        $competition = new competition();
        //  $data=$Normalizer->deserialize($content,Formation::class,'json');
        $competition->setNom($request->get('nom'));
        $competition->setAdresse($request->get('adresse'));
        $competition->setNbequipe($request->get('nb_equipe'));
        $competition->setDate($request->get('date'));

        $entityManager->persist($competition);

        $entityManager->flush();
        $jsonContent =$Normalizer->normalize($competition,'json',['groups'=>'post:read']);
//dump($formations);
        return new Response('Competition ajouté'.json_encode($jsonContent));
        // return $this->render('formation/formationjson.html.twig', [
        //     'formations' => $jsonContent,
        // ]);
    }

    /**
     * @Route("/UpdateJSON/edit", name="updateee")
     */
    public function updateCompJSON(CompetitionRepository $rep,Request $request,SerializerInterface $serializer,EntityManagerInterface $entityManager,NormalizerInterface $Normalizer)
    {
        $content=json_decode($request->getContent(), true);

        $comp = $entityManager
            ->getRepository(Competition::class)
            ->find($request->get('id'));

            $comp->setNom($request->get('nom'));
            $comp->setAdresse($request->get('adresse'));
            $comp->setNbequipe($request->get('nb_equipe'));
            $comp->setDate($request->get('date'));


        $jsonContent =$Normalizer->normalize($comp,'json',['groups'=>'post:read']);

        $entityManager->flush();

        return new Response('Competition updated'.json_encode($jsonContent));

    }


    /**
     * @Route("/deletecompJSON/{id}", name="deletecompJSON")
     */
    public function deleteJson($id,CompetitionRepository $rep,Request $request,SerializerInterface $serializer,EntityManagerInterface $entityManager,NormalizerInterface $Normalizer)
    {
        $content=json_decode($request->getContent(), true);

        $comp = $entityManager
            ->getRepository(Competition::class)
            ->find($id);

        $entityManager->remove($comp);


        $entityManager->flush();
        $jsonContent =$Normalizer->normalize($comp,'json',['groups'=>'post:read']);

        return new Response('Competition supprimée'.json_encode($jsonContent));

    }

    /**
     * @Route("/show", methods={"POST"})
     */
    public function show(Request $request, CompetitionRepository $competitionRepository): Response
    {
        $competition = $competitionRepository->find((int)$request->get("id"));

        if ($competition) {
            return new JsonResponse($competition, 200);
        } else {
            return new JsonResponse([], 204);
        }
    }

    /**
     * @Route("/add", methods={"POST"})
     */
    public function add(Request $request): JsonResponse
    {
        $competition = new Competition();

        return $this->manage($competition, $request);
    }

    /**
     * @Route("/edit", methods={"POST"})
     */
    public function edit(Request $request, CompetitionRepository $competitionRepository): Response
    {
        $competition = $competitionRepository->find((int)$request->get("id"));

        if (!$competition) {
            return new JsonResponse(null, 404);
        }

        return $this->manage($competition, $request);
    }

    public function manage($competition, $request): JsonResponse
    {
        $file = $request->files->get("file");
        if ($file) {
            $fileName = md5(uniqid()) . '.' . $file->guessExtension();

            try {
                $file->move($this->getParameter('brochures_directory'), $fileName);
            } catch (FileException $e) {
                dd($e);
            }
        } else {
            if ($request->get("image")) {
                $fileName = $request->get("image");
            } else {
                $fileName = "null";
            }
        }

        $competition->setUp(
            $request->get("redacteur"),
            DateTime::createFromFormat("d-m-Y", $request->get("date")),
            $request->get("contenu"),
            $fileName
        );

        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($competition);
        $entityManager->flush();

        return new JsonResponse($competition, 200);
    }

    /**
     * @Route("/delete", methods={"POST"})
     */
    public function delete(Request $request, EntityManagerInterface $entityManager, CompetitionRepository $competitionRepository): JsonResponse
    {
        $competition = $competitionRepository->find((int)$request->get("id"));

        if (!$competition) {
            return new JsonResponse(null, 200);
        }

        $entityManager->remove($competition);
        $entityManager->flush();

        return new JsonResponse([], 200);
    }

    /**
     * @Route("/deleteAll", methods={"POST"})
     */
    public function deleteAll(EntityManagerInterface $entityManager, CompetitionRepository $competitionRepository): Response
    {
        $competitions = $competitionRepository->findAll();

        foreach ($competitions as $competition) {
            $entityManager->remove($competition);
            $entityManager->flush();
        }

        return new JsonResponse([], 200);
    }

    /**
     * @Route("/image/{image}", methods={"GET"})
     */
    public function getPicture(Request $request): BinaryFileResponse
    {
        return new BinaryFileResponse(
            $this->getParameter('brochures_directory') . "/" . $request->get("image")
        );
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
}