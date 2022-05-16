<?php

namespace App\Repository;

use App\Entity\AffectationFormateur;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method AffectationFormateur|null find($id, $lockMode = null, $lockVersion = null)
 * @method AffectationFormateur|null findOneBy(array $criteria, array $orderBy = null)
 * @method AffectationFormateur[]    findAll()
 * @method AffectationFormateur[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class AffectationFormateurRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, AffectationFormateur::class);
    }

    // /**
    //  * @return AffectationFormateur[] Returns an array of AffectationFormateur objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('a')
            ->andWhere('a.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('a.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?AffectationFormateur
    {
        return $this->createQueryBuilder('a')
            ->andWhere('a.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */


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
  APP\Entity\AffectationFormateur a where  a.formateur.username=:v and s.idFormation=a.formation
 
  ")


 ->setParameter('v', $val);
 return $query->getResult();

           
   

    }
    public function AffectationForm($val)
    
    {
 

    
 $entityManager=$this->getEntityManager();

 $query=$entityManager
 ->createQuery("SELECT a FROM 
  APP\Entity\AffectationFormateur a,APP\Entity\User u where  a.formateur=u.id and u.username =:v
 
  ")


 ->setParameter('v', $val);
 return $query->getResult();

           
   

    }
  
    public function refuser($id)
    
    {
 

    
 $entityManager=$this->getEntityManager();

 $query=$entityManager
 ->createQuery("UPDATE APP\Entity\AffectationFormateur u SET u.reponse = 3 where u.idAffectation=:v

 
  ")

->setParameter('v', $id);;
 return $query->getResult();

           
   

    }
    public function accepter($id)
    
    {
 

    
 $entityManager=$this->getEntityManager();

 $query=$entityManager
 ->createQuery("UPDATE APP\Entity\AffectationFormateur u SET u.reponse = 2 where u.idAffectation=:v

 
  ")

->setParameter('v', $id);
;
 return $query->getResult();

           
   

    }

    public function findEntities($str){
        return $this->getEntityManager()
            ->createQuery(
                "SELECT p
                FROM APP\Entity\AffectationFormateur p,APP\Entity\User u
                WHERE  (u.username LIKE :str or  u.email LIKE :str  or u.numTel LIKE :str) and p.formateur=u.id "
            )
            ->setParameter('str', '%'.$str.'%')
            ->getResult();
    }

    public function FormA($val)
    
    {
 

    
 $entityManager=$this->getEntityManager();

 $query=$entityManager
 ->createQuery("SELECT a FROM 
  APP\Entity\AffectationFormateur a,APP\Entity\User u where  a.formateur=u.id and u.username =:v and a.reponse=2 
  ")


 ->setParameter('v', $val);
 return $query->getResult();

           
    }

 public function FormR($val)
    
    {
 

    
 $entityManager=$this->getEntityManager();

 $query=$entityManager
 ->createQuery("SELECT a FROM 
  APP\Entity\AffectationFormateur a,APP\Entity\User u where  a.formateur=u.id and u.username =:v and a.reponse=1
  ")


 ->setParameter('v', $val);
 return $query->getResult();

           
   

    }
    
}
