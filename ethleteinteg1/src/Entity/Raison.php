<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Raison
 *
 * @ORM\Table(name="raison", indexes={@ORM\Index(name="raisontxt", columns={"raisontxt"})})
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="App\Repository\RaisonRepository")
 * @UniqueEntity("raisontxt")
 */
class Raison
{
    /**
     * @var int
     *
     * @ORM\Column(name="idRaison", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idraison;

    /**
     * @var string
     *
     * @ORM\Column(name="raisontxt", type="string", length=100, nullable=false)
     *  @Assert\NotBlank(message="Veuillez remplir ce champs")
     */
    private $raisontxt;

    public function __toString()
   {
      return strval( $this->getIdraison() );
   }


    public function getIdraison(): ?int
    {
        return $this->idraison;
    }

    public function getRaisontxt(): ?string
    {
        return $this->raisontxt;
    }

    public function setRaisontxt(string $raisontxt): self
    {
        $this->raisontxt = $raisontxt;

        return $this;
    }


}
