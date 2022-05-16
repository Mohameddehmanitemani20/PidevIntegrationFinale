<?php

namespace App\Controller;

use App\Entity\AffectationFormateur;
use App\Entity\User;
use App\Form\AffectationFormateurType;
use App\Repository\AffectationFormateurRepository;
use App\Repository\ReponseRepository;
use App\Repository\UserRepository;
use App\Security\LoginFormAuthenticator;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bridge\Twig\Mime\TemplatedEmail;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mailer\MailerInterface;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;

/**
 * @Route("/affectation/formateur")
 */
class AffectationFormateurController extends AbstractController
{
    /**
     * @Route("/", name="affectation_formateur_index", methods={"GET"})
     */
    public function index(EntityManagerInterface $entityManager): Response
    {
        $affectationFormateurs = $entityManager
            ->getRepository(AffectationFormateur::class)
            ->findAll();

        return $this->render('affectation_formateur/index.html.twig', [
            'affectation_formateurs' => $affectationFormateurs,
        ]);
    }


      /**
     * @Route("/AffectationJSON", name="AffectationJSON")
     */
    public function listformationJSON(EntityManagerInterface $entityManager,NormalizerInterface $Normalizer)
    {
        $affectations = $entityManager
            ->getRepository(AffectationFormateur::class)
            ->findAll();
$jsonContent =$Normalizer->normalize($affectations,'json',['groups'=>'post:read']);
//dump($formations);
return new Response(json_encode($jsonContent));
        // return $this->render('formation/formationjson.html.twig', [
        //     'formations' => $jsonContent,
        // ]);
    }

    /**
     * @Route("/new", name="affectation_formateur_new", methods={"GET", "POST"})
     */
    public function new(MailerInterface $mailer,Request $request, EntityManagerInterface $entityManager): Response
    {
        $affectationFormateur = new AffectationFormateur();
        $form = $this->createForm(AffectationFormateurType::class, $affectationFormateur);
        $data = $form->getData();
        //$affectationFormateur->setFormateur( $this->getUser());
       // $affectationFormateur->setFormation($data->get)
        $form->handleRequest($request);
        //$affectationFormateur->setReponse(1);
        $img='data:image;base64,'.base64_encode(@file_get_contents('C:\Users\pc\OneDrive\Pictures\logo.jpeg'));

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($affectationFormateur);
            $entityManager->flush();

            $email = (new TemplatedEmail())
                ->from('temanimohameddahmani@gmail.com')
                ->to($affectationFormateur->getFormateur()->getEmail())
                ->subject('Nouvelle affectation')
                ->htmlTemplate('affectation_formateur/aff.html.twig')
                ->context([
                    'aff' => $affectationFormateur,
                    'fin' => $affectationFormateur->getFormation()->getDateFin()->format( 'd/m/Y'),
                    'debut' => $affectationFormateur->getFormation()->getDateDebut()->format( 'd/m/Y'),
                    'img1' =>  $img,
                    'msg'=>"Consulter Votre compte Vous avez une nouvelle affectation ",
                    'url'=>"http://127.0.0.1:8000/affectation/formateur/affect/Formateur/"
                ]);


            $mailer->send($email);
            /*
            //$user =
            $message = (new \Swift_Message('Nouvelle Affecation'))
            // On attribue l'expéditeur
            ->setFrom("temanimohameddahmani@gmail.com")
        
            // On attribue le destinataire
            ->setTo("temanimohameddahmani@gmail.com")
        
            // On crée le texte avec la vue
            ->setBody(
                $this->render('affectation_formateur/aff.html.twig', [
                    'aff' => $affectationFormateur,
                    'fin' => $affectationFormateur->getFormation()->getDateFin()->format( 'd/m/Y'),
                    'debut' => $affectationFormateur->getFormation()->getDateDebut()->format( 'd/m/Y'),
                    'img1' =>  $img,
                    'msg'=>"Consulter Votre compte Vous avez une nouvelle affectation ",
                    'url'=>"http://127.0.0.1:8000/affectation/formateur/affect/Formateur/"
                ]),
                'text/html'
            )
        ;
        $mailer->send($message);*/
        $this->addFlash('message', 'Votre message a été transmis.'); // Permet un message flash de renvoi
     
            return $this->redirectToRoute('affectation_formateur_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('affectation_formateur/new.html.twig', [
            'affectation_formateur' => $affectationFormateur,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idAffectation}", name="affectation_formateur_show", methods={"GET"})
     */
    public function show(AffectationFormateur $affectationFormateur): Response
    {
        return $this->render('affectation_formateur/show.html.twig', [
            'affectation_formateur' => $affectationFormateur,
        ]);
    }

    /**
     * @Route("/{idAffectation}/edit", name="affectation_formateur_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, AffectationFormateur $affectationFormateur, ReponseRepository $rep,EntityManagerInterface $entityManager): Response
    { //$r=$rep->find(1);
       // $affectationFormateur->setReponse($r);
        $form = $this->createForm(AffectationFormateurType::class, $affectationFormateur);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
           
            $entityManager->flush();

            return $this->redirectToRoute('affectation_formateur_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('affectation_formateur/edit.html.twig', [
            'affectation_formateur' => $affectationFormateur,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idAffectation}", name="affectation_formateur_delete", methods={"POST"})
     */
    public function delete(Request $request, AffectationFormateur $affectationFormateur, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$affectationFormateur->getIdAffectation(), $request->request->get('_token'))) {
            $entityManager->remove($affectationFormateur);
            $entityManager->flush();
        }

        return $this->redirectToRoute('affectation_formateur_index', [], Response::HTTP_SEE_OTHER);
    }


/**
     * @Route("/affect/Formateur/", name="affectForm", methods={"GET"})
     */
    public function affectForm(LoginFormAuthenticator $log,AffectationFormateurRepository $a,UserRepository $u): Response
    {
        
        $user = $this->get('security.token_storage')->getToken()->getUser();
        //  $user=new User() ;
         //$user=$u->findbyusername($username);
        // $x=$user->getId();
        $username=$user->getUsername();

        $affectationFormateurs = $a->AffectationForm($username);

        return $this->render('affectation_formateur/affectForm.html.twig', [
            'affectation_formateurs' => $affectationFormateurs,
        ]);
    }
    /**
     * @Route("/accepter/{idAffectation}", name="accepter", methods={"GET","POST"})
     */
    public function accepter($idAffectation, AffectationFormateurRepository $entityManager): Response
    {
      
        $f= $entityManager
       
        ->accepter($idAffectation);
       
        return $this->redirectToRoute('affectForm');    }



           /**
     * @Route("/refuser/{idAffectation}", name="refuser", methods={"GET","POST"})
     */
    public function refuser($idAffectation, AffectationFormateurRepository $entityManager): Response
    {
      
        $f= $entityManager
      
        ->refuser($idAffectation);
       
        return $this->redirectToRoute('affectForm');    }
          /**
     * @Route("/", name="app_recherche2", methods={"POST"})
     */
    public function rechercher(Request $request,AffectationFormateurRepository $repository)
    {
       
        if( $request->isMethod("POST"))
        {
            $nom =$request->get('forma');
            $aff =$repository->findEntities($nom);
        }

        return $this->render('affectation_formateur/index.html.twig', [
            'affectation_formateurs' => $aff
        ]);}


/**
     * @Route("/affect/Formateur/A", name="affectFormA", methods={"GET"})
     */
    public function affectA(AffectationFormateurRepository $a,UserRepository $u): Response
    {$username="formateur1";
       //  $user=new User() ;
         //$user=$u->findbyusername($username);
        // $x=$user->getId();
        $affectationFormateurs = $a->FormA($username);

        return $this->render('affectation_formateur/affectFormA.html.twig', [
            'affectation_formateurs' => $affectationFormateurs,
        ]);
    }
/**
     * @Route("/affect/Formateur/R", name="affectFormR", methods={"GET"})
     */
    public function affectNC(AffectationFormateurRepository $a,UserRepository $u): Response
    {$username="formateur1";
       //  $user=new User() ;
         //$user=$u->findbyusername($username);
        // $x=$user->getId();
        $affectationFormateurs = $a->FormR($username);

        return $this->render('affectation_formateur/affectFormR.html.twig', [
            'affectation_formateurs' => $affectationFormateurs,
        ]);
    }

}
