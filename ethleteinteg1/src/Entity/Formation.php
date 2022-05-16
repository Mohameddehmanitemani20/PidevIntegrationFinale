<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Serializer\Annotation\Groups;


/**
 * Formation
 *
 * @ORM\Table(name="formation")
 * @ORM\Entity(repositoryClass="App\Repository\FormationRepository")
 * @UniqueEntity(fields="nomFormation", message="Une Formation existe déjà avec ce nom .")
 */

class Formation
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_formation", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @Groups("post:read")
     */
    private $idFormation;

    /**
     * @var string
     *
     * @ORM\Column(name="nom_formation", type="string", length=30, nullable=false)
     * @Assert\NotBlank(message="Nom de la Formation is required")
     * @Groups("post:read")
     */
    private $nomFormation;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_debut", type="date", nullable=false)
      *@Assert\Range(
       *      min = "now",
     *      max = "last day of December "
     * )
     * @Groups("post:read")
     */
    private $dateDebut;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_fin", type="date", nullable=false)
      * @Assert\Expression(
   *     "this.getDateDebut() < this.getDateFin()",
   *     message="La date fin ne doit pas être antérieure à la date début"
   * )
   * @Groups("post:read")
     */
    private $dateFin;

    /**
     * @var string
     *
     * @ORM\Column(name="dispositif", type="string", length=50, nullable=false)
     * @Groups("post:read")
     */
    private $dispositif;

    /**
     * @var string
     *
     * @ORM\Column(name="programme", type="text", length=500, nullable=false)
     * @Assert\NotBlank(message="Nom de la Formation is required")
     * @Groups("post:read")
     */
    private $programme;


    public function getIdFormation(): ?int
    {
        return $this->idFormation;
    }

    public function getNomFormation(): ?string
    {
        return $this->nomFormation;
    }

    public function setNomFormation(?string $nomFormation): self
    {
        $this->nomFormation = $nomFormation;

        return $this;
    }

    public function getDateDebut(): ?\DateTimeInterface
    {
        return $this->dateDebut;
    }

    public function setDateDebut(\DateTimeInterface $dateDebut): self
    {
        $this->dateDebut = $dateDebut;

        return $this;
    }

    public function getDateFin(): ?\DateTimeInterface
    {
        return $this->dateFin;
    }

    public function setDateFin(\DateTimeInterface $dateFin): self
    {
        $this->dateFin = $dateFin;

        return $this;
    }

    public function getDispositif(): ?string
    {
        return $this->dispositif;
    }

    public function setDispositif(string $dispositif): self
    {
        $this->dispositif = $dispositif;

        return $this;
    }

    public function getProgramme(): ?string
    {
        return $this->programme;
    }

    public function setProgramme(string $programme): self
    {
        $this->programme = $programme;

        return $this;
    }

    public function __toString()
    {
        return $this->getNomFormation();
    }


}
