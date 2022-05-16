<?php

namespace App\Controller;

use Psr\Log\LoggerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\RedirectResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Mime\Email;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\Component\Mailer\MailerInterface;

class MailerController extends AbstractController
{
    /**
     * @Route("/email/{to}/{sjt}/{text}")
     */
    public function sendEmail(MailerInterface $mailer,$to,$sjt,$text):Response 
    {
        
        $email = (new Email())
            ->from('temanimohameddahmani@gmail.com')
            ->to($to)
            //->cc('cc@example.com')
            //->bcc('bcc@example.com')
            //->replyTo('fabien@example.com')
            //->priority(Email::PRIORITY_HIGH)
            ->subject($sjt)
            ->text($text);

        $mailer->send($email);

      return new JsonResponse(['ajout'=>'ok']);
      }
    
}
