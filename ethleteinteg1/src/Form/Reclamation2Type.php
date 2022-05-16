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
class Reclamation2Type extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
        ->add('contenu', HiddenType::class, [
            'required' => true,
            'disabled' => false,
            'mapped' => true,
          ])
          ->add('daterec', HiddenType::class, [
            'required' => true,
            'disabled' => false,
            'mapped' => true,
          ])
            ->add('etat', ChoiceType::class, [
                'required' => true,
                'choices' => [
                    'En cours' => "En cours",
                    'Traitee' => "traitee",
                    
                ],
                'label' => false,
                'data' => 'en cours'
            ])
            ->add('id', HiddenType::class, [
                'required' => true,
                'disabled' => false,
                'mapped' => true,
              ])
            //->add('idraison')
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
