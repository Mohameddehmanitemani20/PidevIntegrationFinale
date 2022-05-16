<?php

namespace App\Form;

use App\Entity\Evenement;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;


class EvenementType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('nomEvent')
            ->add('dateDebut')
            ->add('dateFin')
            ->add('typee',ChoiceType::class, [
                'choices'  => [
                    'Compétition' => true,
                    'Formation' => false,
                ],
            ])
            ->add('lieu')
            ->add('prixu')
            ->add('idFormation')
            ->add('idInter')
            ->add('idCompet')
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Evenement::class,
        ]);
    }
}
