<?php

namespace App\Form;

use App\Entity\Journe;
use App\Entity\Competition;

use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;


class JourneType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('numjourne')
            ->add('dateJourne')
            ->add('IdCompetition', ChoiceType::class, [
            'choices' => [
                '5' => 5,
                '15' => 15,
                '3' => 3,
                '5' => 2,
                '6' => 3,
                '7' => 4,
                '487' => 5,
                '488' => 6,


            ],
            'choice_attr' => [
                '2' => ['data-color' => 'Red'],
                '5' => ['data-color' => 'Yellow'],
                '6' => ['data-color' => 'Green'],
            ],
        ]);

// or use a callable


    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Journe::class,
        ]);
    }
}
