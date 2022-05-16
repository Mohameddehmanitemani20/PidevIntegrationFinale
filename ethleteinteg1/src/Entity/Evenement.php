<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;


/**
 * Evenement
 *
 * @ORM\Table(name="evenement", indexes={@ORM\Index(name="fk_form_event", columns={"id_formation"}), @ORM\Index(name="fk_comp", columns={"id_compet"}), @ORM\Index(name="fk_intervenantss", columns={"id_inter"})})
 * @ORM\Entity(repositoryClass="App\Repository\EventRepository")
 */

class Evenement
{  public function __toString()
{
    return $this->getNomEvent();
}

    /**
     * @var int
     *
     * @ORM\Column(name="id_event", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idEvent;

    /**
     * @var string
     *
     * @ORM\Column(name="imageE", type="string", length=500, nullable=false)
     */
    private $imagee;


    /**
     * @var string
     *
     * @ORM\Column(name="nom_event", type="string", length=30, nullable=false)
     * @Assert\NotBlank(message="Veuillez donner le nom de l'event")
     */
    private $nomEvent;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_debut", type="date", nullable=false)
     */
    private $dateDebut;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_fin", type="date", nullable=false)
     * @Assert\Expression(
     *     "this.getDateDebut() < this.getDateFin()",
     *     message="La date de fin doit obligatoirement etre superieure à celle de début"
     * )
     */
    private $dateFin;

    /**
     * @var string
     *
     * @ORM\Column(name="typeE", type="string", length=50, nullable=false)
     *
     */
    private $typee;

    /**
     * @var string
     *
     * @ORM\Column(name="lieu", type="string", length=100, nullable=false)
     */
    private $lieu;

    /**
     * @var float
     * @Assert\Positive
     * @ORM\Column(name="prixU", type="float", precision=10, scale=0, nullable=false)
     */
    private $prixu;

    /**
     * @var \Formation
     *
     * @ORM\ManyToOne(targetEntity="Formation")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_formation", referencedColumnName="id_formation")
     * })
     */
    private $idFormation;

    /**
     * @var \Intervenant
     *
     * @ORM\ManyToOne(targetEntity="Intervenant")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_inter", referencedColumnName="id_inter")
     * })
     */
    private $idInter;

    /**
     * @var \Competition
     *
     * @ORM\ManyToOne(targetEntity="Competition")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="id_compet", referencedColumnName="id_competition")
     * })
     */
    private $idCompet;

    public function getIdEvent(): ?int
    {
        return $this->idEvent;
    }


    public function getImagee()
    {
        return $this->imagee;
    }

    public function setImagee( $imagee)
    {
        $this->imagee = $imagee;

        return $this;
    }

    public function getNomEvent(): ?string
    {
        return $this->nomEvent;
    }

    public function setNomEvent(string $nomEvent): self
    {
        $this->nomEvent = $nomEvent;

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

    public function getTypee(): ?string
    {
        return $this->typee;
    }

    public function setTypee(string $typee): self
    {
        $this->typee = $typee;

        return $this;
    }

    public function getLieu(): ?string
    {
        return $this->lieu;
    }

    public function setLieu(string $lieu): self
    {
        $this->lieu = $lieu;

        return $this;
    }

    public function getPrixu(): ?float
    {
        return $this->prixu;
    }

    public function setPrixu(float $prixu): self
    {
        $this->prixu = $prixu;

        return $this;
    }

    public function getIdFormation(): ?Formation
    {
        return $this->idFormation;
    }

    public function setIdFormation(?Formation $idFormation): self
    {
        $this->idFormation = $idFormation;

        return $this;
    }

    public function getIdInter(): ?Intervenant
    {
        return $this->idInter;
    }

    public function setIdInter(?Intervenant $idInter): self
    {
        $this->idInter = $idInter;

        return $this;
    }

    public function getIdCompet(): ?Competition
    {
        return $this->idCompet;
    }

    public function setIdCompet(?Competition $idCompet): self
    {
        $this->idCompet = $idCompet;

        return $this;
    }


}