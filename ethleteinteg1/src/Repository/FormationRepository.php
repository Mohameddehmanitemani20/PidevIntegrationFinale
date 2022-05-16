<?php

namespace App\Repository;

use App\Controller\AffectationFormateurController;
use App\Data\SearchData;
use App\Entity\AffectationFormateur;

use App\Entity\Formation;
use App\Entity\Search;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Formation|null find($id, $lockMode = null, $lockVersion = null)
 * @method Formation|null findOneBy(array $criteria, array $orderBy = null)
 * @method Formation[]    findAll()
 * @method Formation[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class FormationRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Formation::class);
    }

    // /**
    //  * @return Formation[] Returns an array of Formation objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('f')
            ->andWhere('f.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('f.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Formation
    {
        return $this->createQueryBuilder('f')
            ->andWhere('f.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
    // public function FormationByNom($val)
    
    // {
    //     $query= $this-> getEntityManager()->createQuery("SELECT s  FROM APP\Entity\Formation s wher s.nomFormation like ':v'
       
    //     ")


    //    ->setParameter('v', $val);
    //    return  $query->getResult();
    
    // }
    public function FormationByFormateur($val)
    
    {
 /*return $this->createQueryBuilder('f')
 
 ->leftJoin('f.idFormation','a')
 ->where('a.formateur.id =:v')
 ->setParameter('v', $val)
 ->getQuery()
 ->getResult();*/

    
            $entityManager=$this->getEntityManager();

            $query=$entityManager
            ->createQuery("SELECT s  FROM APP\Entity\Formation s ,
             APP\Entity\AffectationFormateur a where  a.formateur=:v and s.idFormation=a.formation
            
             ")


            ->setParameter('v', $val);
            return $query->getResult();

    }



    public function list()
    
    {


    
 $entityManager=$this->getEntityManager();

 $query=$entityManager
 ->createQuery("SELECT s  FROM APP\Entity\Formation s 
  
 
  ");


 
 return $query->getResult();

           
   

    }
    public function findSearch($min,$max)
    {
  
        
      
        $entityManager=$this->getEntityManager();

        $query=$entityManager
        ->createQuery("SELECT s  FROM APP\Entity\Formation s  where s.dateDebut BETWEEN :dateOne AND :dateTwo
         
        
         ")
       
        
             
                ->setParameter('dateOne',$min)
                    ->setParameter('dateTwo',$max);
        

      


        return $query->getResult();
      

    }
    public function findEntities($str){
        return $this->getEntityManager()
            ->createQuery(
                "SELECT p
                FROM APP\Entity\Formation p
                WHERE p.nomFormation LIKE :str"
            )
            ->setParameter('str', '%'.$str.'%')
            ->getResult();
    }

    public function findSearch1($str)
    {
  
        
      
        return $this->getEntityManager()
        ->createQuery(
            "SELECT p
            FROM APP\Entity\Formation p
            WHERE p.nom_formation LIKE :str"
        )
        ->setParameter('str', '%'.$str.'%')
        ->getResult();
      

    }
    public function listEN()
    
    {


    
 $entityManager=$this->getEntityManager();

 return $entityManager
 ->createQuery("SELECT s  FROM APP\Entity\Formation s where s.dispositif like :str
  
 
  ")   ->setParameter('str', 'En_Ligne')->getResult();
    }
    public function listPR()
    
    {
        $entityManager=$this->getEntityManager();

    
        return $entityManager
        ->createQuery("SELECT s  FROM APP\Entity\Formation s where s.dispositif not like :str
         
        
         ")   ->setParameter('str', 'En_Ligne')->getResult();
    }


    public function tri()
    
    {


        return $this->createQueryBuilder('s')
        ->orderBy('s.dateDebut', 'DESC')
        ->getQuery()->getResult();
           
   

    }
    public function triN()
    
    {


        return $this->createQueryBuilder('s')
        ->orderBy('s.nomFormation', 'DESC')
        ->getQuery()->getResult();
           
   

    }

}
