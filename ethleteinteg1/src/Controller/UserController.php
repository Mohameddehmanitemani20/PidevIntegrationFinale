<?php

namespace App\Controller;

use App\Data\SearchData;
use App\Entity\User;
use App\Form\SearchForm;
use App\Form\UserType;
use App\Form\ProfileType;
use App\Form\EditUserType;
use App\Repository\AffectationFormateurRepository;
use App\Repository\UserRepository;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Authentication\Token\TokenInterface;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\IsGranted;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Component\Security\Http\Authentication\AuthenticationUtils;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\SerializerInterface;
use Twilio\Rest\Client;
use Symfony\Component\Mailer\MailerInterface;

/**
 * @Route("/user")
 */
class UserController extends AbstractController
{


   
        //============================MOBILE=================================================//
    /**
     * @Route("/resetPasswordUser/mobile", name="api_resetPasswordUser", methods={"POST"})
     */
    public function api_resetPasswordUser(UserRepository $userRepository, UserPasswordEncoderInterface $userPasswordEncoder, NormalizerInterface $normalizable, EntityManagerInterface $entityManager, Request $request, UserPasswordEncoderInterface $passwordHasher)
    {
        $user = $userRepository->findOneBy(['email' => $request->get('email')]);

        if ($user) {
            $password = $request->get('password');
            $Confirmpassword = $request->get('confirmpassword');

            if ($password ==  $Confirmpassword) {
                $user->setPassword(
                    $userPasswordEncoder->encodePassword(
                        $user,
                        $request->get('password')
                    )
                );
                $entityManager->persist($user);
                $entityManager->flush();
                return new JsonResponse([
                    'success' => "Password updated"
                ]);
            } else {
                return new JsonResponse([
                    'error' => "Password doesnt match"
                ]);
            }
        } else {
            return new JsonResponse([
                'error' => "user doesnt exist"
            ]);
        }
    }


    /**
     * @Route("/UpdatePassword/mobile", name="api_UpdatePasswordMobile")
     */
    public function UpdatePassword_Mobile(UserRepository $userRepository, NormalizerInterface $normalizable, EntityManagerInterface $entityManager, Request $request, UserPasswordEncoderInterface $userPasswordEncoder)
    {
        $password = $request->get('password');
        $email = $request->get('email');
        $user = $userRepository->findOneBy(['email' => $email]);
        if ($user) {
            $user->setPassword(
                $userPasswordEncoder->encodePassword(
                    $user,
                    $password
                )
            );
            $entityManager->persist($user);
            $entityManager->flush();
            return new JsonResponse([
                'success' => "password has been updated"
            ]);
        } else {
            return new JsonResponse([
                'error' => "error updating user"
            ]);
        }
    }

   

    /**
     * @Route("/signup/mobile", name="api_signup", methods={"POST"})
     */
    public function api_signup(MailerInterface $mailer,UserPasswordEncoderInterface $userPasswordEncoder, NormalizerInterface $normalizable, EntityManagerInterface $entityManager, Request $request, UserPasswordEncoderInterface $passwordHasher)
    {
        //$users = $userRepository->findAll();
        $user = new User();


        $user->setNom($request->get('nom'));
        $user->setPrenom($request->get('prenom'));
        $user->setUsername($request->get('username'));
        $eem=$request->query->get('email');
      $user->setEmail($eem);
        $user->setNumTel($request->get('numtel'));
        $user->setAdresse($request->get('adresse'));
        $user->setGenre($request->get('genre'));
        $user->setIdEq($request->get('IdEquipe'));




        $user->setPassword(
            $userPasswordEncoder->encodePassword(
                $user,
                $request->get('password')
            )
        );
        $current_date = new \DateTime('@' . strtotime('+01:00'));
        $user->setDateNaissance($current_date);
        $user->setRoles(["ROLE_USER"]);

        $bytes = random_bytes(3);
        $verificationCode = bin2hex($bytes);
        $user->SetVerificationCode($verificationCode);
        $sjt="BIENVENUE CHEZ NEXTEC";
        $txt="welcome to Us !";
$mail= new MailerController();
$mail->sendEmail($mailer,$eem,$sjt,$txt);
        /* $this->twilio->messages->create("+216" . $user->getNumTel(), [
            'from' => $this->fromNumber,
            'body' => "To Activate Your account please use this code upon logging in \n Code :$verificationCode"
        ]); */
        $user->setIsVerified(1);
        $entityManager->persist($user);
        $entityManager->flush();
        return new JsonResponse([
            'success' => "user has been added"
        ]);
    }




    /**
     * @Route("/login/mobile", name="api_login", methods={"POST"})
     */
    public function api_login(NormalizerInterface $normalizable, UserRepository $userRepository, Request $request, UserPasswordEncoderInterface $passwordHasher)
    {
        //$users = $userRepository->findAll();

        $user = $userRepository->findOneBy(['username' => $request->get('username')]);
        if ($user) {
            $result = $passwordHasher->isPasswordValid($user, $request->get('password'));
            if ($result) {
                $jsonContent = $normalizable->normalize($user, 'json', ['groups' => 'read:users']);
                return new Response(json_encode($jsonContent));
            }
        }
        return new JsonResponse([
            'error' => "invalid informations"
        ]);
    }

    /**
     * @Route("/editUser/mobile", name="api_editUser", methods={"POST"})
     */
    public function api_EditUser(UserRepository $userRepository, NormalizerInterface $normalizable, EntityManagerInterface $entityManager, Request $request, $id)
    {
        $user = new User();
        $user = $userRepository->findOneBy(['id' => 130]);
        if ($user) {
            $user->setNom($request->get('nom'));
            $user->setPrenom($request->get('prenom'));
            $user->setUsername($request->get('username'));
            $user->setEmail($request->get('email'));
            $user->setPassword($request->get('password'));
           $user->setNumTel($request->get('numtel'));
            $user->setAdresse($request->get('adresse'));
            $user->setGenre($request->get('genre'));
            $user->setIdEq($request->get('IdEquipe'));
           // $user->setRoles(["ROLE_" . $request->get('roles')]);
            $entityManager->persist($user);
            $entityManager->flush();
            return new JsonResponse([
                'success' => "user has been updated"
            ]);
        } else {
            return new JsonResponse([
                'error' => "error updating , please try again"
            ]);
        }
    }

    /**
     * @Route("/passwordMobile", name="passwordMobile")
     */
    public function getPasswordbyPhone(Request $request,SerializerInterface $serializer,NormalizerInterface $normalizable)
    {
        $numtel = $request->query->get("numtel");
        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository(User::class)->findOneBy(['numtel' => $numtel]);
        if ($user) {
            $password=$user->getPassword();
            $formatted = $normalizable->normalize($user, 'json', ['groups' => 'read:users']);
            return new Response(json_encode($formatted));

        }
        else {
            return new Response("user not found");
        }

    }

    
    
    /**
     * @Route("/deleteuser/{id}", name="deleteFormationsJSON", methods={"DELETE"})
     */
    public function deleteUser($id, UserRepository $rep, Request $request, SerializerInterface $serializer, EntityManagerInterface $entityManager, NormalizerInterface $Normalizer)
    {
        $content = json_decode($request->getContent(), true);

        $user = $rep->find($id);

        $entityManager->remove($user);


        $entityManager->flush();
        $jsonContent = $Normalizer->normalize($user, 'json', ['groups' => 'post:read']);

        return new Response('USER supprimée' . json_encode($jsonContent));
    }


    /**
     * @Route("/all/users", name="users_mobile", methods={"GET"})
     */
    public function mobile_all_users(NormalizerInterface $normalizable, UserRepository $userRepository, Request $request)
    {
        $users = $userRepository->findAll();
        $jsonContent = $normalizable->normalize($users, 'json', ['groups' => 'read:users']);
        return new Response(json_encode($jsonContent));
    }

     /**
     * @Route("/ban", name="ban")
     */
    public function ban(Request $request,SerializerInterface $serializer, UserPasswordEncoderInterface $encoder)
    {
        $id=$request->query->get("id");

        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository(User::class)->find($id);
             $user->setActivationToken(1);
            $em->flush();
            return new JsonResponse("success",200);
    }
    /**
     * @Route("/unban", name="unban")
     */
    public function unban(Request $request,SerializerInterface $serializer, UserPasswordEncoderInterface $encoder)
    {
        $id=$request->query->get("id");

        $em = $this->getDoctrine()->getManager();
        $user = $em->getRepository(User::class)->find($id);
        $user->setActivationToken(Null);
        $em->flush();
        return new JsonResponse("success",200);
    }


    //================MOBILE USER================================================//

    
/**
     * @Route("/listFormateur", name="listFormateur")
     */
    public function listStudentEnabled(UserRepository $em)
    {
$formateurs=$em->listFormateur();
       
        return $this->render('user/listFormateur.html.twig', [
            "listFormateur" => $formateurs,
        ]);
    }
      /**
     * @Route("/listFormation/{id}", name="listFormation", methods={"GET"})
     */
    public function showFormations($id,AffectationFormateurRepository $em): Response
    {
     
        $list=$em->FormationByFormateur($id);
        return $this->render('user/listFormation.html.twig', [
            "list" => $list,
        ]);
    }
    
    //================MOBILE================================================//


    /**
     * @IsGranted("ROLE_USER") 
     * @Route("/profile", name="profile", methods={"GET"})
     */
    public function profile(UserRepository $userRepository): Response
    {


        return $this->render('user/profile.html.twig', [
            'user' => $userRepository->findOneBy(['id' => $this->getUser()->getId()]),
        ]);
    }



    /**
     * @IsGranted("ROLE_USER")
     * @Route("/ActivateAccountWithCode", name="ActivateAccountWithCode", methods={"GET","POST"})
     */
    public function ActivateAccountWithCode(UserRepository $userRepository, Request $request): Response
    {
        $error = null;
        if ($request->isMethod('POST')) {
            $code = $request->request->get('verificationcode');
            $codeUser = $this->getUser()->getVerificationCode();
            if ($code == $codeUser) {
                $user = new User();
                $user = $this->getUser();
                $user->setVerificationCode(null);
                $entityManager = $this->getDoctrine()->getManager();
                $entityManager->persist($user);
                $entityManager->flush();
                return $this->redirectToRoute("app_login");
            } else {
                $error = "Please Verify your Code";
                return $this->render('security/ActivateAccountWithCode.html.twig', [
                    'error' => $error,
                ]);
            }
        }
        return $this->render('security/ActivateAccountWithCode.html.twig', [
            'error' => $error,
        ]);
    }

    /**
     * @IsGranted("ROLE_ADMIN")
     * @Route("/disable_user/{id}", name="disable_user", methods={"GET", "POST"})
     */
    public function disable_user(Request $request, User $user, EntityManagerInterface $entityManager): Response
    {
        $user->setDisableToken("1");
        $entityManager->persist($user);
        $entityManager->flush();
        return $this->redirectToRoute('app_user_index', [], Response::HTTP_SEE_OTHER);
    }

    /**
     * @IsGranted("ROLE_ADMIN")
     * @Route("/enable_user/{id}", name="enable_user", methods={"GET", "POST"})
     */
    public function enable_user(Request $request, User $user, EntityManagerInterface $entityManager): Response
    {
        $user->setDisableToken(null);
        $entityManager->persist($user);
        $entityManager->flush();
        //$link = $request->headers->get("referer");
        return $this->redirectToRoute('app_user_index', [], Response::HTTP_SEE_OTHER);
    }


    /**
     * @Route("/DisabledAccount", name="DisabledAccount")
     */
    public function DisabledAccount(): Response
    {
        return $this->render('user/DisabledAccount.html.twig');
    }



    /**
     * @Route("/profile/delete", name="delete_profile", methods={"GET"})
     */
    public function delete_profile(Request $request, EntityManagerInterface $entityManager): Response
    {
        $user = $this->getUser();
        if (!$this->isCsrfTokenValid('delete' . $user->getId(), $request->request->get('_token'))) {
            $entityManager->remove($user);
            $entityManager->flush();
            return $this->redirectToRoute('app_login', [], Response::HTTP_SEE_OTHER);
        }
    }


    /**
     * @IsGranted("ROLE_USER") 
     * @Route("/profile/edit", name="edit_profile", methods={"GET" , "POST" })
     */
    public function edit_profile(Request $request, EntityManagerInterface $entityManager): Response
    {
        $user = $this->getUser();
        $form = $this->createForm(ProfileType::class, $user);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($user);
            $entityManager->flush();

            return $this->redirectToRoute('profile', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('user/profile_edit.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }



    /**
     * @IsGranted("ROLE_ADMIN")
     * @Route("/", name="app_user_index", methods={"GET"})
     */
    public function index(UserRepository $userRepository, Request $request): Response
    {
        $data = new SearchData();
        $form = $this->createForm(SearchForm::class, $data);
        $form->handleRequest($request);
        $users = $userRepository->findSearch($data);


        return $this->render('user/index.html.twig', [
            'users' => $users, 'form' => $form->createView()
        ]);
    }



    /**
     * @IsGranted("ROLE_ADMIN")
     * @Route("/{id}", name="app_user_show", methods={"GET"})
     */
    public function show(User $user): Response
    {
        return $this->render('user/show.html.twig', [
            'user' => $user,
        ]);
    }

    /**
     * @IsGranted("ROLE_ADMIN")
     * @Route("/{id}/edit", name="app_user_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, User $user, UserRepository $userRepository): Response
    {
        $form = $this->createForm(UserType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $userRepository->add($user);
            return $this->redirectToRoute('app_user_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('user/edit.html.twig', [
            'user' => $user,
            'form' => $form->createView(),
        ]);
    }

    /**
     *  @IsGranted("ROLE_ADMIN")
     * @Route("/delete/{id}", name="app_user_delete", methods={"GET","POST"})
     */
    public function delete(Request $request, User $user, UserRepository $userRepository): Response
    {
        if ($this->isCsrfTokenValid('delete' . $user->getId(), $request->request->get('_token'))) {
            $userRepository->remove($user);
        }

        return $this->redirectToRoute('app_user_index', [], Response::HTTP_SEE_OTHER);
    }
}
