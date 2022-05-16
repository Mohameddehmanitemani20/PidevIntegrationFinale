<?php

namespace App\Entity;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Symfony\Component\Serializer\Annotation\Groups;
/**
 * Competition
 *
 * @ORM\Table(name="competition")
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="App\Repository\CompetitionRepository")
 */
class Competition
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_competition", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @Groups("post:read")
     */
    private $idCompetition;

    /**
     * @var int
     *
     * @ORM\Column(name="nb_equipe", type="integer", nullable=false)
     * @Assert\NotEqualTo(
     *     value = 0,
     *     message = "Le nombre d'equipe ne doit pas etre égal a 0 "
     * )
     * * @Groups("post:read")
     */
    private $nbEquipe;

    /**
     * @var $date
     * @ORM\Column(name="date", type="string", nullable=true)
     * @Assert\Date
     * @Groups("post:read")
     */
    private $date;

    /**
     * @var string
     *
     * @ORM\Column(name="adresse", type="string", length=50, nullable=false)
     * @Assert\Length(
     *     min = 5,
     *     max = 50,
     *     minMessage = "Le nombre d'equipe  doit comporter au moins {{ limit }} caractéres ",
     *     maxMessage = "Le nombre d'equipe  doit comporter au plus {{ limit }} caractéres "
     * )
     * @Groups("post:read")
     */
    private $adresse;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=50, nullable=false)
     * @Assert\Length(
     *     min = 5,
     *     max = 50,
     *     minMessage = "Le nom de competition doit comporter au moins {{ limit }} caractéres ",
     *     maxMessage = "Le nom de competition  doit comporter au plus {{ limit }} caractéres "
     * )
     * @Groups("post:read")
     */
    private $nom;

    public function getIdCompetition(): ?int
    {
        return $this->idCompetition;
    }

    public function getNbEquipe(): ?int
    {
        return $this->nbEquipe;
    }

    public function setNbEquipe(int $nbEquipe): self
    {
        $this->nbEquipe = $nbEquipe;

        return $this;
    }

    public function getDate(): ?string
    {
        return $this->date;
    }
    /**
     * Set date
     *
     * @param \string $date
     *
     * @return EntityName
     */
    public function setDate(?string $date)
    {
        $this->date = $date;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->adresse;
    }
    public function __toString()
    {
        return $this->date;
    }
    public function setAdresse(string $adresse): self
    {
        $this->adresse = $adresse;

        return $this;
    }

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }


}
