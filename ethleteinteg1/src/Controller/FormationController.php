<?php

namespace App\Controller;

use App\Data\SearchData;
use App\Entity\Formation;
use App\Entity\Search;
use App\Form\FormationType;
use App\Form\SearchForm1;
use App\Form\TriForm;
use App\Repository\AffectationFormateurRepository;
use App\Repository\FormationRepository;
use App\Repository\ParticipationRepository;
use ContainerF8itYSJ\PaginatorInterface_82dac15;
use Doctrine\ORM\EntityManagerInterface;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Serializer\Normalizer\NormalizerInterface;
use Symfony\Component\Serializer\SerializerInterface;

/**
 * @Route("/formation")
 */
class FormationController extends AbstractController
{

    /**
     * @Route("/", name="formation_index", methods={"GET"})
     */
    public function index(FormationRepository $repository,PaginatorInterface  $paginator,EntityManagerInterface $entityManager,Request $request): Response
    {
      
       
        $form=$this->createForm(SearchForm1::class);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid())

{
    $min = $form['min']->getData();
    $max = $form['max']->getData();
    $formations=$repository->findSearch( $min, $max);
}
        else
       {$formations = $entityManager
            ->getRepository(Formation::class)
            ->findAll();}



          //  if ($form1->isSubmitted())
            //{ $formations = $repository
              //  ->tri();}
            // $formations=     $paginator->paginate(
            //     $donnees, // Requête contenant les données à paginer (ici nos articles)
            //     $request->query->getInt('page', 1), // Numéro de la page en cours, passé dans l'URL, 1 si aucune page
            //     3 // Nombre de résultats par page
            // );
        return $this->render('formation/index.html.twig', [
            'formations' => $formations,'form'=>$form->createView()
        ]);
    }


  /**
     * @Route("/FormationsJSON", name="FormationsJSON")
     */
    public function listformationJSON(EntityManagerInterface $entityManager,NormalizerInterface $Normalizer)
    {
        $formations = $entityManager
            ->getRepository(Formation::class)
            ->findAll();
$jsonContent =$Normalizer->normalize($formations,'json',['groups'=>'post:read']);
//dump($formations);
return new Response(json_encode($jsonContent));
        // return $this->render('formation/formationjson.html.twig', [
        //     'formations' => $jsonContent,
        // ]);
    }



  /**
     * @Route("/listF", name="listF", methods={"GET"})
     */
    public function index1(EntityManagerInterface $entityManager): Response
    {
        $formations = $entityManager
            ->getRepository(Formation::class)
            ->findAll();

        return $this->render('participation/listF.html.twig', [
            'formations' => $formations,
        ]);
    }
 /**
     * @Route("/filtrer", name="filtrerD")
      */
      public function filtrer(FormationRepository $repository, Request $request)
      {
   
        $form=$this->createForm(SearchForm::class);
     
        $form->handleRequest($request);
        $min = $form['min']->getData();
        $max = $form['max']->getData();
        $formations=$repository->findSearch(new \DateTime('2000-11-02'), new \DateTime('2020-11-02 00:00:00'));
         
        return $this->render('formation/listWithSearch.html.twig', [
            'formations' => $formations
        ]);
        }
 /**
     * @Route("/trierD", name="trierD")
      */
      public function trierF(FormationRepository $repository, Request $request,EntityManagerInterface $entityManager)
      {
        $form=$this->createForm(SearchForm::class);
       
        $formations=$repository->tri();
        $this->redirectToRoute('formation_index'); 
        return $this->render('formation/index.html.twig', [
            'formations' => $formations,'form'=>$form->createView()
        ]);
        }


         /**
     * @Route("/trierN", name="trierN", methods={"GET", "POST"})
      */
      public function trierN(FormationRepository $repository, Request $request)
      {
        $form=$this->createForm(SearchForm::class);
       
        $formations=$repository->triN();
         
        return $this->render('formation/index.html.twig', [
            'formations' => $formations,'form'=>$form->createView()
        ]);
        }


    /**
     * @Route("/new", name="formation_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager): Response
    {
        $formation = new Formation();
        $form = $this->createForm(FormationType::class, $formation);
        $form->handleRequest($request);
        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->persist($formation);
            $entityManager->flush();

            return $this->redirectToRoute('formation_index', [], Response::HTTP_SEE_OTHER);
        }
        

        return $this->render('formation/new.html.twig', [
            'formation' => $formation,
            'form' => $form->createView(),
        ]);
    }


 /**
     * @Route("/addFormationsJSON", name="addFormationsJSON")
     */
    public function AddFormationJSON(Request $request,EntityManagerInterface $entityManager,NormalizerInterface $Normalizer)
    {
$content=$request->getContent();

        $formation = new Formation();
   //  $data=$Normalizer->deserialize($content,Formation::class,'json');
 $formation->setNomFormation($request->get('nomFormation'));
     $formation->setProgramme($request->get('programme'));
    
$formation->setDateDebut(\DateTime::createFromFormat('Y-m-d',$request->get('dateDebut')));
    
   $formation->setDateFin(\DateTime::createFromFormat('Y-m-d',$request->get('dateFin')));
 $formation->setDispositif($request->get('dispositif'));
            $entityManager->persist($formation);

            $entityManager->flush();
$jsonContent =$Normalizer->normalize($formation,'json',['groups'=>'post:read']);
//dump($formations);
return new Response('formation ajouté'.json_encode($jsonContent));
        // return $this->render('formation/formationjson.html.twig', [
        //     'formations' => $jsonContent,
        // ]);
    }
 /**
     * @Route("/updateFormationsJSON/{id}", name="updateFormationsJSON")
     */
    public function updateFormationJSON($id,FormationRepository $rep,Request $request,SerializerInterface $serializer,EntityManagerInterface $entityManager,NormalizerInterface $Normalizer)
    {
$content=json_decode($request->getContent(), true);

        $formation = $rep->find($id);

  empty($request->get('nomFormation')) ? true : $formation->setNomFormation($request->get('nomFormation'));
 
 empty($request->get('programme')) ? true : $formation->setProgramme($request->get('programme'));
  empty(\DateTime::createFromFormat('Y-m-d',$request->get('dateDebut'))) ? true : $formation->setDateDebut(\DateTime::createFromFormat('Y-m-d',$request->get('dateDebut')));

  empty(\DateTime::createFromFormat('Y-m-d',$request->get('dateFin'))) ? true : $formation->setDateFin(\DateTime::createFromFormat('Y-m-d',$request->get('dateFin')));
  empty($request->get('dispositif')) ? true : $formation->setDispositif($request->get('dispositif'));
  $jsonContent =$Normalizer->normalize($formation,'json',['groups'=>'post:read']);

            $entityManager->flush();

return new Response('formation updated'.json_encode($jsonContent));
  
    }


/**
     * @Route("/pdfJSON/{id}", name="Formation_showpdfJSON", methods={"GET"})
     */
    public function pdfshowJSON($id,Formation $f,ParticipationRepository $rep,FormationRepository $repository,AffectationFormateurRepository $af,NormalizerInterface $Normalizer)
    {  $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');
        $dompdf = new Dompdf($pdfOptions);
        $participants = $rep
        ->findAll();
        $affectations=$af->findAll();
        $k=[];
        $f1=$repository->find($id);
        foreach($participants as $p)
        {if($p->getFormation()->getNomFormation()==$f1->getNomFormation())
{array_push($k,$p->getIdParticipant());



}}

       $img='data:image;base64,'.base64_encode(@file_get_contents('C:\Users\pc\OneDrive\Pictures\logo.jpeg'));
     
        $html=$this->render('formation/pdfshow.html.twig', [
            'Formation' => $f,
            'date Début' => $f->getDateDebut()->format( 'd/m/Y'),
            'date Fin' => $f->getDateFin()->format( 'd/m/Y'),
            'Programme' => $f->getProgramme(),
            'img1' =>  $img,
             'id'=> $f->getIdFormation(),
             'part'=> $participants ,
             'affect'=> $affectations
            
        ]);
        $dompdf->loadHtml($html);

        $dompdf->setPaper('A4', 'portrait');
        $dompdf->render();
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => false
        ]);

        $jsonContent =$Normalizer->normalize($dompdf,'json',['groups'=>'post:read']);



    }





     /**
     * @Route("/deleteJSONform/{id}", name="deleteFormationJSON")
     */
    public function deleteJSON($id,FormationRepository $rep,Request $request,SerializerInterface $serializer,EntityManagerInterface $entityManager,NormalizerInterface $Normalizer)
    {
$content=json_decode($request->getContent(), true);

        $int = $rep->find($id);

        $entityManager->remove($int);


            $entityManager->flush();
            $jsonContent =$Normalizer->normalize($int,'json',['groups'=>'post:read']);

return new Response('Formation supprimée'.json_encode($jsonContent));
}
  


/**
     * @Route("/getFormationsJSON/{id}", name="getFormationsJSON")
     */
    public function getFormationJSON($id,FormationRepository $rep,Request $request,SerializerInterface $serializer,EntityManagerInterface $entityManager,NormalizerInterface $Normalizer)
    {

        $formation = $rep->find($id);

       $entityManager->remove($formation);


           // $entityManager->flush();
            $jsonContent =$Normalizer->normalize($formation,'json',['groups'=>'post:read']);

return new Response(json_encode($jsonContent));
  
    }



    /**
     * @Route("/{idFormation}", name="formation_show", methods={"GET"})
     */
    public function show(Formation $formation): Response
    {
        return $this->render('formation/show.html.twig', [
            'formation' => $formation,
        ]);
    }

    // /**
    //  * @Route("/{nomFormation}", name="formation_showbynom")
    //  */
    // public function showByNom(FormationRepository  $em, $nomFormation)
    // {
    //     $f=$em->FormationByNom($nomFormation);
    //     return $this->render('formation/showbynom.html.twig', [
    //         'formation' => $f,
    //     ]);
    // }
    /**
     * @Route("/{idFormation}/edit", name="formation_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Formation $formation, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(FormationType::class, $formation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('formation_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('formation/edit.html.twig', [
            'formation' => $formation,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idFormation}", name="formation_delete", methods={"POST"})
     */
    public function delete(Request $request, Formation $formation, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$formation->getIdFormation(), $request->request->get('_token'))) {
            $entityManager->remove($formation);
            $entityManager->flush();
        }

        return $this->redirectToRoute('formation_index', [], Response::HTTP_SEE_OTHER);
    }




/**
     * @Route("/pdf/{id}", name="Formation_showpdf", methods={"GET"})
     */
    public function pdfshow($id,Formation $f,ParticipationRepository $rep,FormationRepository $repository,AffectationFormateurRepository $af)
    {  $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');
        $dompdf = new Dompdf($pdfOptions);
        $participants = $rep
        ->findAll();
        $affectations=$af->findAll();
        $k=[];
        $f1=$repository->find($id);
        foreach($participants as $p)
        {if($p->getFormation()->getNomFormation()==$f1->getNomFormation())
{array_push($k,$p->getIdParticipant());



}}

       $img='data:image;base64,'.base64_encode(@file_get_contents('C:\Users\pc\OneDrive\Pictures\logo.jpeg'));
     
        $html=$this->render('formation/pdfshow.html.twig', [
            'Formation' => $f,
            'date Début' => $f->getDateDebut()->format( 'd/m/Y'),
            'date Fin' => $f->getDateFin()->format( 'd/m/Y'),
            'Programme' => $f->getProgramme(),
            'img1' =>  $img,
             'id'=> $f->getIdFormation(),
             'part'=> $participants ,
             'affect'=> $affectations
            
        ]);
        $dompdf->loadHtml($html);

        $dompdf->setPaper('A4', 'portrait');
        $dompdf->render();
        $dompdf->stream("mypdf.pdf", [
            "Attachment" => false
        ]);
    }
/**
     * @Route("/search", name="ajax_search", methods={"GET"})
     */
    public function searchAction(FormationRepository $repository,Request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $requestString = $request->get('q');
    
$posts=$repository->findEntities($requestString);
        if(!$posts) {
            $result['posts']['error'] = "Post Not found 🙁 ";
        } 
        
        // else {
        //     $result['posts'] = $this->getRealEntities($posts);
        // }
        return new Response(json_encode($result));
    }
    // public function getRealEntities($posts){
    //     foreach ($posts as $post){
    //         $realEntities[$post->getIdFormation()] = $post;
    //     }
    //     return $realEntities;
    // }
/**
 * Search action.
 * @Route("/search1/{search}", name="search1")
 * @param  Request               $request Request instance
 * @param  string                $search  Search term
 * @return Response|JsonResponse          Response instance
 */
public function searchAction1(FormationRepository $repository,Request $request, string $search)
{
    if (!$request->isXmlHttpRequest()) {
        return $this->render("formation/index.html.twig");
    }

    if (!$searchTerm = trim($request->query->get("search", $search))) {
        return new JsonResponse(["error" => "Search term not specified."], Response::HTTP_BAD_REQUEST);
    }

    $em = $this->getDoctrine()->getManager();
    if (!($results =$repository->findEntities($search))) {
        return new JsonResponse(["error" => "No results found."], Response::HTTP_NOT_FOUND);
    }

    return new JsonResponse([
        "html" => $this->renderView("search.ajax.twig", ["results" => $results]),
    ]);
}



 
/**
     * @Route("/best/best", name="best", methods={"GET"})
     */
    public function best(PaginatorInterface  $paginator,ParticipationRepository $p ,Request $request)
    {
       
       $s=$p->nbPartByForm1();
       $formations=     $paginator->paginate(
          $s, // Requête contenant les données à paginer 
           $request->query->getInt('page', 1), 
           3 
       );
    
return $this->render('formation/best.html.twig', [
    'formations' => $formations
]);

        
        
    }


    /**
     * @Route("/", name="app_recherche", methods={"GET","POST"})
     */
    public function rechercher(Request $request,FormationRepository $repository)
    {
        $form=$this->createForm(SearchForm::class);
        if( $request->isMethod("POST"))
        {
            $nom =$request->get('nomF');
            $formations =$repository->findEntities($nom);
        }

        return $this->render('formation/index.html.twig', [
            'formations' => $formations,'form'=>$form->createView()
        ]);}
   /**
     * @Route("/rechercheJSON11/{nom}", name="rechercheJSON11")
     */
    public function rechercherJSON($nom,Request $request,FormationRepository $repository,NormalizerInterface $Normalizer)
    {
      
            $formations =$repository->findEntities($nom);

            $jsonContent =$Normalizer->normalize($formations,'json',['groups'=>'post:read']);
return new Response(json_encode($jsonContent));
     }
}