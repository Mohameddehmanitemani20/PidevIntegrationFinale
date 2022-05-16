<?php

namespace App\Controller;

use App\Entity\User;
use App\Form\RegistrationFormType;
use App\Security\EmailVerifier;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bridge\Twig\Mime\TemplatedEmail;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mime\Address;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Security\Core\Encoder\UserPasswordEncoderInterface;
use Symfony\Contracts\Translation\TranslatorInterface;
use SymfonyCasts\Bundle\VerifyEmail\Exception\VerifyEmailExceptionInterface;
use Twilio\Rest\Client;

class RegistrationController extends AbstractController
{

    private EmailVerifier $emailVerifier;
   
   // private $fromNumber;

    public function __construct(EmailVerifier $emailVerifier)
    {
        $this->emailVerifier = $emailVerifier;
       // $this->fromNumber = "+14758897095";

    }

    /**
     * @Route("/register", name="app_register")
     */
    public function register(Request $request, UserPasswordEncoderInterface $userPasswordEncoder, EntityManagerInterface $entityManager): Response
    {
        $user = new User();
        $form = $this->createForm(RegistrationFormType::class, $user);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            // encode the plain password
            $user->setPassword(
                $userPasswordEncoder->encodePassword(
                    $user,
                    $form->get('plainPassword')->getData()
                )
            );
            $user->setRoles(["ROLE_USER"]);

            $bytes = random_bytes(3);
            $verificationCode = bin2hex($bytes);
            $user->SetVerificationCode($verificationCode);
            $entityManager->persist($user);
            $entityManager->flush();
            // do anything else you need here, like send an email
          /*
            $sid    = "AC0910f591ea614fc16f7d056d273029fc";
            $token  = "abf92be5fe5362f6d98c7ae44a9e5462";
            $twilio = new Client($sid, $token);

            $message = $twilio->messages
                ->create(
                    "+216". $user->getNumTel(), // to 
                    array(
                        'from' => "+14758897095",
                        "body" => "To Activate Your account please use this code upon logging in \n Code :$verificationCode"
                    )
                );

            print($message->sid);
         */
        

           // generate a signed url and email it to the user
           $this->emailVerifier->sendEmailConfirmation('app_verify_email', $user,
           (new TemplatedEmail())
               ->from(new Address('temanimohameddahmani@gmail.com', 'Nextec'))
               ->to($user->getEmail())
               ->subject('Please Confirm your Email')
               ->htmlTemplate('registration/confirmation_email.html.twig')
       );


            return $this->redirectToRoute('app_login');
        }

        return $this->render('registration/register.html.twig', [
            'registrationForm' => $form->createView(),
        ]);
    }

    /**
     * @Route("/verify/email", name="app_verify_email")
     */
    public function verifyUserEmail(Request $request): Response
    {
        $this->denyAccessUnlessGranted('IS_AUTHENTICATED_FULLY');

        // validate email confirmation link, sets User::isVerified=true and persists
        try {
            $this->emailVerifier->handleEmailConfirmation($request, $this->getUser());
        } catch (VerifyEmailExceptionInterface $exception) {
            $this->addFlash('verify_email_error', $exception->getReason());

            return $this->redirectToRoute('app_login');
        }

        // @TODO Change the redirect on success and handle or remove the flash message in your templates
        $this->addFlash('success', 'Your email address has been verified.');

        return $this->redirectToRoute('app_login');
    }
}
