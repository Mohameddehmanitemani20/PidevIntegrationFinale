<?php

namespace App\Repository;

use App\Entity\Participation;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Participation|null find($id, $lockMode = null, $lockVersion = null)
 * @method Participation|null findOneBy(array $criteria, array $orderBy = null)
 * @method Participation[]    findAll()
 * @method Participation[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ParticipationRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Participation::class);
    }

    // /**
    //  * @return Participation[] Returns an array of Participation objects
    //  */
    /*
    public function findByExampleField($value)
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('p.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    */

    /*
    public function findOneBySomeField($value): ?Participation
    {
        return $this->createQueryBuilder('p')
            ->andWhere('p.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
    
    public function nbPartByForm()
    
    {
 

    
 $entityManager=$this->getEntityManager();

 $query=$entityManager
 ->createQuery("SELECT f.nomFormation,count(p)  FROM APP\Entity\Participation p ,
  APP\Entity\Formation f where  f.idFormation=p.formation group by  p.formation
 
  ");


 
 return $query->getResult();

           
   

    }


    public function partbyform($id)
    
    {
 

    
 $entityManager=$this->getEntityManager();

 $query=$entityManager
 ->createQuery("SELECT f  FROM APP\Entity\Participation p ,
  APP\Entity\User f where  p.formation=:val and p.idParticipant=f.id
 
  ")     ->setParameter('val',$id);


 
 return $query->getResult();

           }

           public function nbPartByForm1()
    
    {
 

    
 $entityManager=$this->getEntityManager();

 $query=$entityManager
 ->createQuery("SELECT f FROM APP\Entity\Participation p ,
  APP\Entity\Formation f where  f.idFormation=p.formation group by  p.formation order by count(p) DESC 
 
  ")
  //  ->setMaxResults(3)
  ;


 
 return $query->getResult();

           
   

    }
}
