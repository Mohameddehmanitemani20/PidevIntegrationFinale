<?php

namespace App\Controller;

use App\Repository\FormationRepository;
use App\Repository\ParticipationRepository;
use DateTime;
use Ob\HighchartsBundle\Highcharts\Highchart;
use PhpParser\Node\Expr\Cast\Array_;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use Symfony\UX\Chartjs\Builder\ChartBuilderInterface;
use Symfony\UX\Chartjs\Model\Chart;

class StatController extends AbstractController
{
    /**
     * @Route("/stat", name="stat", methods={"GET"})
     */
    public function index(ChartBuilderInterface $chartBuilder,ParticipationRepository $p,FormationRepository $fr): Response
    {$chart = $chartBuilder->createChart(Chart::TYPE_PIE);
       
       $s=$p->nbPartByForm();
     $labels=[];
     $data=[];
    $age=[];
  
       foreach($s as $s1)
{    
    
    array_push($labels,  array($s1['nomFormation'], $s1[1]));
    //array_push($data,  $s1[1]);

   // $labels.array_push( $s1[$i]['nomFormation']);
  //  $data.array_push($s1[$i][$i+1]);
 
}
/*$series = array(
    array("name" => $labels,    "data" => $data)
);

$ob = new Highchart();

$ob->chart->renderTo('linechart');  
$ob->title->text('nbParticipantf(Formation)');
$ob->xAxis->title(array('text'  => "Formation"));
$ob->yAxis->title(array('text'  => "Nombre de participant"));

$ob->series($series);*/
$ob = new Highchart();
$ob->chart->renderTo('piechart');
$ob->title->text('Nombre de participant par formation');
$ob->plotOptions->plot(array(
    'allowPointSelect'  => true,
    'cursor'    => 'pointer',
    'dataLabels'    => array('enabled' => false),
    'showInLegend'  => true,
    

));
$participations=$p->findAll();
$agee=0;
$jeune=0;
$d1 = new DateTime('1992-01-01 00:00:00');
foreach($participations as $f)
{

if( $f->getIdParticipant()->getDateNaissance()>$d1)
{$agee=$agee+1;}
else
{$jeune=$jeune+1;}


}

array_push($age, array("Personnes Agées", $agee));
array_push($age,  array("Jeune",$jeune));
$ob2 = new Highchart();
            $ob2->chart->renderTo('piechart2');
            $ob2->title->text('Statistiques Par Age');
            $ob2->plotOptions->pie(array(
                'allowPointSelect'  => true,
                'cursor'    => 'pointer',
                'dataLabels'    => array('enabled' => false),
                'showInLegend'  => true,
                'colors'=> ['#2f7ed8', '#0d233a', '#8bbc21', '#910000', '#1aadce',
                '#492970', '#f28f43', '#77a1e5', '#c42525', '#a6c96a']
            ));
            
$formations1 = $fr
            ->findAll();
            $nbf=0;
            $nbh=0;
 
            foreach($formations1 as $f)
            { $participants = $p->partbyform($f->getIdFormation());
                foreach($participants as $part)
                {
if($part->getGenre()=="homme")
{
    $nbh=$nbh+1;
}
else 
{$nbf=$nbf+1;}


                }
            }
            array_push($data, array("nbHommes", $nbh));
            array_push($data,  array("nbFemmes",$nbf));
            $ob1 = new Highchart();
            $ob1->chart->renderTo('piechart1');
            $ob1->title->text('Statistiques Par Genre');
            $ob1->plotOptions->pie(array(
                'allowPointSelect'  => true,
                'cursor'    => 'pointer',
                'dataLabels'    => array('enabled' => false),
                'showInLegend'  => true,
                'colors'=> [ '#c42525', '#a6c96a']
            ));
            

$ob->series(array(array('type' => 'pie','name' => 'Browser share', 'data' => $labels)));
$ob1->series(array(array('type' => 'pie','name' => 'Browser share', 'data' => $data)));
$ob2->series(array(array('type' => 'pie','name' => 'Browser share', 'data' => $age)));
return $this->render('stat/index.html.twig', array(
    'chart' => $ob,  'chart1' => $ob1, 'chart2' => $ob2
));



        $chart->setData([
            'labels' => $labels,
            'datasets' => [
                [
                    'label' => 'Formation!',
                    'backgroundColor' => 'rgb(255, 99, 132)',
                    'borderColor' => 'rgb(255, 99, 132)',
                    'data' => $data,
                ],
            ],
        ]);
      /*  return $this->render('stat/index.html.twig', [
            'data' =>  $data,
            'labels' =>  $labels,

        ]);*/
        
        
    }
}
