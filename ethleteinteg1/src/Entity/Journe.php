<?php

namespace App\Entity;

use Doctrine\DBAL\Types\DateType;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Journe
 *
 * @ORM\Table(name="journe", indexes={@ORM\Index(name="fk_idcj", columns={"id_competition"})})
 * @ORM\Entity
 */
class Journe
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_journe", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idJourne;

    /**
     * @var int
     *
     * @ORM\Column(name="numJourne", type="integer", nullable=false)
     * @Assert\NotEqualTo(
     *     value = 0,
     *     message = "Le numero de journe ne doit pas etre égal a 0 "
     * )
     */
    private $numjourne;

    /**
     * @var string
     *
     * @ORM\Column(name="date_journe", type="string", length=255, nullable=false)
     * @Assert\Length(
     *     min = 5,
     *     max = 50,
     *     minMessage = "Le nombre d'equipe  doit comporter au moins {{ limit }} caractéres ",
     *     maxMessage = "Le nombre d'equipe  doit comporter au plus {{ limit }} caractéres "
     * )
     */
    private $dateJourne;

    /**
     * @var int
     *
     * @ORM\Column(name="id_competition", type="integer", nullable=false)
     * @Assert\NotEqualTo(
     *     value = 0,
     *     message = "Le nombre d'equipe ne doit pas etre égal a 0 "
     * )
     */
    private $idCompetition;

    public function getIdJourne(): ?int
    {
        return $this->idJourne;
    }

    public function getNumjourne(): ?int
    {
        return $this->numjourne;
    }

    public function setNumjourne(int $numjourne): self
    {
        $this->numjourne = $numjourne;

        return $this;
    }

    public function getDateJourne(): ?string
    {
        return $this->dateJourne;
    }

    public function setDateJourne(string $dateJourne): self
    {
        $this->dateJourne = $dateJourne;

        return $this;
    }

    public function getIdCompetition(): ?int
    {
        return $this->idCompetition;
    }

    public function setIdCompetition(int $idCompetition): self
    {
        $this->idCompetition = $idCompetition;

        return $this;
    }


}
