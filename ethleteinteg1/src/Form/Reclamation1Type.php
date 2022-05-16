<?php

namespace App\Form;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use App\Entity\Reclamation;
use App\Entity\Raison;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\HiddenType;
class Reclamation1Type extends AbstractType
{    
   


    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('contenu')
            ->add('daterec')
            ->add('etat', HiddenType::class, [
                'required' => true,
                'disabled' => false,
                'mapped' => true,
                'attr' => [
                  'value' => 'en cours'
                ]
              ])


            ->add('id')
            ->add('idraison')
            ->add('idraison',EntityType::class,['class'=>Raison::class,'choice_label'=>'raisontxt'])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Reclamation::class,
        ]);
    }
}
