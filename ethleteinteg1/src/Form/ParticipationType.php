<?php

namespace App\Form;

use App\Entity\Formation;
use App\Entity\Participation;
use App\Entity\User;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class ParticipationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
           
            ->add('idParticipant',EntityType::class,[
                'class'=> User::class,
                
                'choice_label'=>'id',
                 ])
            ->add('formation',EntityType::class,[
                'class'=> Formation::class,
                
                'choice_label'=>'nom_formation',
                 ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Participation::class,
        ]);
    }
}
