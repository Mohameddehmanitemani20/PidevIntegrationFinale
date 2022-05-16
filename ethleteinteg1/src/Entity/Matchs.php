<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Matchs
 *
 * @ORM\Table(name="matchs", indexes={@ORM\Index(name="fk_eq2", columns={"Equipe2"}), @ORM\Index(name="fk_journee", columns={"id_journe"}), @ORM\Index(name="fk_eq1", columns={"Equipe1"})})
 * @ORM\Entity
 */
class Matchs
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_match", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idMatch;

    /**
     * @var string
     *
     * @ORM\Column(name="Equipe1", type="string", length=255, nullable=false)
     * @Assert\Length(
     *     min = 5,
     *     max = 50,
     *     minMessage = "Le nom d'equipe  doit comporter au moins {{ limit }} caractéres ",
     *     maxMessage = "Le nom d'equipe  doit comporter au plus {{ limit }} caractéres "
     * )
     */
    private $equipe1;

    /**
     * @var string
     *
     * @ORM\Column(name="Equipe2", type="string", length=255, nullable=false)
     * @Assert\Length(
     *     min = 5,
     *     max = 50,
     *     minMessage = "Le nom d'equipe  doit comporter au moins {{ limit }} caractéres ",
     *     maxMessage = "Le nom d'equipe  doit comporter au plus {{ limit }} caractéres "
     * )
     */
    private $equipe2;

    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", length=255, nullable=false, options={"default"="non commencé"})
     */
    private $etat = 'non commencé';

    /**
     * @var int
     *
     * @ORM\Column(name="id_journe", type="integer", nullable=false)
     */
    private $idJourne;

    public function getIdMatch(): ?int
    {
        return $this->idMatch;
    }

    public function getEquipe1(): ?string
    {
        return $this->equipe1;
    }

    public function setEquipe1(string $equipe1): self
    {
        $this->equipe1 = $equipe1;

        return $this;
    }

    public function getEquipe2(): ?string
    {
        return $this->equipe2;
    }

    public function setEquipe2(string $equipe2): self
    {
        $this->equipe2 = $equipe2;

        return $this;
    }

    public function getEtat(): ?string
    {
        return $this->etat;
    }

    public function setEtat(string $etat): self
    {
        $this->etat = $etat;

        return $this;
    }

    public function getIdJourne(): ?int
    {
        return $this->idJourne;
    }

    public function setIdJourne(int $idJourne): self
    {
        $this->idJourne = $idJourne;

        return $this;
    }


}
