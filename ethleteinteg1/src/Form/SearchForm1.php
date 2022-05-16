<?php


namespace App\Form;

use App\Data\SearchData;
use Doctrine\DBAL\Types\DateType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;


use Symfony\Component\Form\Extension\Core\Type\DateTimeType as TypeDateTimeType;
use Symfony\Component\Form\Extension\Core\Type\DateType as TypeDateType;

class SearchForm1 extends AbstractType
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
        ->add('min', TypeDateType::class, [
            
            'required' => false,      'placeholder' => [
                'year' => 'Year', 'month' => 'Month', 'day' => 'Day']
           
        ])
        ->add('max',TypeDateType::class,  [
          
            'required' => false,
            'placeholder' => [
                'year' => 'Year', 'month' => 'Month', 'day' => 'Day']
        ])


        ;


    }
}