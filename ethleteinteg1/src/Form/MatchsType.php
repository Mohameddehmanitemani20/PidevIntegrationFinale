<?php

namespace App\Form;

use App\Entity\Matchs;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;


class MatchsType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('equipe1')
            ->add('equipe2')
            ->add('etat' , ChoiceType:: class, [
                    'choices' => [
                        'Non Commencé' => "Non Commencé",
                        'Fini' => "Fini",
                        'En Cours' => "En Cours",]])

            ->add('idJourne', ChoiceType::class, [
                'choices' => [
                    '1' => 1,
                    '2' => 2,
                    '548' => 3,
                    '5' => 4,
                    '342354' => 5,
                    '342355' => 6,


                ],
                'choice_attr' => [
                    '2' => ['data-color' => 'Red'],
                    '5' => ['data-color' => 'Yellow'],
                    '6' => ['data-color' => 'Green'],
                ],
            ]);
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Matchs::class,
        ]);
    }
}
