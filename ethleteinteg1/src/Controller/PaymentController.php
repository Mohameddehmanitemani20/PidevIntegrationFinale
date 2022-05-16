<?php

namespace App\Controller;


use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Stripe\Checkout\Session;
use Stripe\Stripe;


class PaymentController extends AbstractController
{
    /**
     * @Route("/payment", name="app_payment")
     */
    public function index(): Response
    {
        return $this->render('payment/index.html.twig', [
            'controller_name' => 'PaymentController',
        ]);
    }
    /**
     * @Route("/checkout", name="app_payment_checkout", methods={"GET","POST"})
     */

    public function checkout(  ): Response
    {


        $YOUR_DOMAIN =  'http://localhost:8000/evenement/eventFront';
        Stripe::setApiKey('sk_test_51Kr33FLwe7UpWWfw1iRcl1uDtdjjkQWcYitMVAxEGnJFDrZDMPgWFye4DqBOU2USEqDdC4dwVWnzP9dVXqAIsnvH0042qcSBcw');



        $session = Session::create([
            'payment_method_types' => ['card'],
            'line_items'           => [
                [
                    'price_data' => [
                        'currency'     => 'eur',
                        'product_data' => [
                            'name' => 'Coins',

                        ],
                        'unit_amount'  => 69 *100,
                    ],
                    'quantity'   => 1,
                ]
            ],
            'mode' => 'payment',
            'success_url' => $YOUR_DOMAIN,
            'cancel_url' => $YOUR_DOMAIN,
        ]);

        // TODO

        if($session){
            //FONCTION TE3EK WIN BECH TZID FEL BD el montant or idk
            return $this->redirect($session->url,303);}else{
            return $this->redirectToRoute('app_payment_successurl');
        }


    }
}
