<?php

namespace App\Form;

use App\Entity\AffectationFormateur;
use App\Entity\Formation;
use App\Entity\Reponse;
use App\Entity\User;
use App\Repository\UserRepository;
use Doctrine\ORM\EntityRepository;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class AffectationFormateurType extends AbstractType
{
    
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
        ->add('formation',EntityType::class,[
            'class'=> Formation::class,
            
            'choice_label'=>'nom_formation',
             ])
        ->add('formateur',EntityType::class,[
            'class'=> User::class,
            'query_builder' => function (UserRepository  $er) {
                return $er->listFormateur1();
            },
            


            'choice_label'=>'username',
             ])
            ->add('reponse',EntityType::class,[
                'class'=> Reponse::class,
                'choice_label'=>'reponse'
            
                ]
                
                )
     
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => AffectationFormateur::class,
        ]);
    }
}
