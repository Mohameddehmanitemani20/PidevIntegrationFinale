<?php


namespace App\Form;



use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;



class TriForm extends AbstractType
{



    public function configureOptions(OptionsResolver $resolver){

        $resolver->setDefaults([
              
                'method'     => 'GET',
              

        ]);
    }

    public function fetBlockPrefix(){

        return '';
    }


    
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
    
        $builder
        


        ;


    }
}