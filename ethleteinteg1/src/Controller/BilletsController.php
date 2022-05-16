<?php

namespace App\Controller;

use App\Entity\Evenement;
use App\Repository\EventRepository;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Session\SessionInterface;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/billets")
 */
class BilletsController extends AbstractController{
    /**
     *  @Route("/", name="app_billets_index", methods={"GET"})
     */
    public function index(SessionInterface $session, EventRepository $EventRepository)
    {
        $panier = $session->get("panier", []);

        // On "fabrique" les données
        $dataPanier = [];
        $prix = 0;

        foreach($panier as $id => $nbr_billet){
            $evenement = $EventRepository->find($id);
            $dataPanier[] = [
                "evenement" => $evenement,
                "nbrBillet" => $nbr_billet
            ];
            $prix += $evenement->getPrixu() * $nbr_billet;
        }

        return $this->render('billets/index.html.twig', compact("dataPanier", "prix"));
    }

    /**
     * @Route("/add/{id}", name="add")
     */
    public function add(Evenement $evenement, SessionInterface $session)
    {
        // On récupère le panier actuel
        $panier = $session->get("panier", []);
        $id = $evenement->getIdEvent();

        if(!empty($panier[$id])){
            $panier[$id]++;
        }else{
            $panier[$id] = 1;
        }

        // On sauvegarde dans la session
        $session->set("panier", $panier);

        return $this->redirectToRoute("app_billets_index");
    }

    /**
     * @Route("/remove/{id}", name="remove")
     */
    public function remove(Evenement $evenement, SessionInterface $session)
    {
        // On récupère le panier actuel
        $panier = $session->get("panier", []);
        $id = $evenement->getIdEvent();

        if(!empty($panier[$id])){
            if($panier[$id] > 1){
                $panier[$id]--;
            }else{
                unset($panier[$id]);
            }
        }

        // On sauvegarde dans la session
        $session->set("panier", $panier);

        return $this->redirectToRoute("app_billets_index");
    }

    /**
     * @Route("/delete/{id}", name="delete")
     */
    public function delete(Evenement $evenement, SessionInterface $session)
    {
        // On récupère le panier actuel
        $panier = $session->get("panier", []);
        $id = $evenement->getId();

        if(!empty($panier[$id])){
            unset($panier[$id]);
        }

        // On sauvegarde dans la session
        $session->set("panier", $panier);

        return $this->redirectToRoute("app_billets_index");
    }

    /**
     * @Route("/delete", name="delete_all")
     */
    public function deleteAll(SessionInterface $session)
    {
        $session->remove("panier");

        return $this->redirectToRoute("app_billets_index");
    }

}
