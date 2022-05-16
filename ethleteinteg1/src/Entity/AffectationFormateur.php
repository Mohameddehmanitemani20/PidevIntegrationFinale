<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;

/**
 * AffectationFormateur
 *
 * @ORM\Table(name="affectation_formateur", indexes={@ORM\Index(name="fk_formation1", columns={"formation_id"}), @ORM\Index(name="fk_reponse", columns={"reponse"}), @ORM\Index(name="fk_formateur", columns={"formateur_id"})})
 * @ORM\Entity(repositoryClass="App\Repository\AffectationFormateurRepository")
 * @UniqueEntity(fields={"formation","formateur"}, message="Cette Affectation existe  déjà  .")
 
 */
class AffectationFormateur
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_affectation", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idAffectation;

    /**
     * @var \Formation
     *
     * @ORM\ManyToOne(targetEntity="Formation" )
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="formation_id", referencedColumnName="id_formation")
     * })
     */
    private $formation;

    /**
     * @var \User
     *
     * @ORM\ManyToOne(targetEntity="User")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="formateur_id", referencedColumnName="id")
     * })
     */
    private $formateur;

    /**
     * @var \Reponse
     *
     * @ORM\ManyToOne(targetEntity="Reponse")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="reponse", referencedColumnName="id_reponse")
     * })
     */
    private $reponse;

    public function getIdAffectation(): ?int
    {
        return $this->idAffectation;
    }

    public function getFormation()
    {
        return $this->formation;
    }

    public function setFormation(?Formation $formation): self
    {
        $this->formation = $formation;

        return $this;
    }

    public function getFormateur()
    {
        return $this->formateur;
    }

    public function setFormateur(?User $formateur): self
    {
        $this->formateur = $formateur;

        return $this;
    }

    public function getReponse()
    {
        return $this->reponse;
    }

    public function setReponse(?Reponse $reponse): self
    {
        $this->reponse = $reponse;

        return $this;
    }


}
