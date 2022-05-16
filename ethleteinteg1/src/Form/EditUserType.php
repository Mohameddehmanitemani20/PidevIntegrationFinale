<?php

namespace App\Form;

use App\Entity\User;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class EditUserType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
        ->add('adresse')
        ->add('dateNaissance')
        ->add('email')
        ->add('nom')
        ->add('numTel')
        ->add('password')
        ->add('prenom')
        ->add('roles')
        ->add('username')
        ->add('genre')
        ->add('idEq')
        
        
            
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => User::class,
        ]);
    }
}
