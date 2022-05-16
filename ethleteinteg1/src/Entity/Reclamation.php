<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Reclamation
 *
 * @ORM\Table(name="reclamation", indexes={@ORM\Index(name="idRaison", columns={"idRaison"}), @ORM\Index(name="id", columns={"id"})})
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="App\Repository\ReclamationRepository")
 */
class Reclamation
{
    /**
     * @var int
     *
     * @ORM\Column(name="idr", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idr;

    /**
     * @var string
     *
     * @ORM\Column(name="contenu", type="text", length=65535, nullable=false)
     * @Assert\NotBlank(message="Veuillez remplir ce champs")
     * @Assert\Length(
     *      min = 1,
     *      max = 150,
     *      minMessage = "the text must be at least {{ limit }} characters long",
     *      maxMessage = "Your text cannot be longer than {{ limit }} characters"
     * )
     */
    private $contenu;

    /**
     * @var string "Y-m-d" formatted value
     *
     * @ORM\Column(name="daterec", type="string", length=10, nullable=false)
     * @Assert\NotBlank(message="Veuillez remplir ce champs")
     * @Assert\Date
     *
     */
    private $daterec;

    /**
     * @var string
     *
     * @ORM\Column(name="etat", type="string", options={"default":"En cours..."})
     *
     */
    private $etat;

    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @Assert\NotBlank(message="Veuillez remplir ce champs")
     */
    private $id;

    /**
     * @var \Raison
     *
     * @ORM\ManyToOne(targetEntity="Raison")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idRaison", referencedColumnName="idRaison")
     * })
     *  @Assert\NotBlank(message="Veuillez remplir ce champs")
     */
    private $idraison;

    
   


    public function getIdr(): ?int
    {
        return $this->idr;
    }

    public function getContenu(): ?string
    {
        return $this->contenu;
    }

    public function setContenu(string $contenu): self
    {
        $this->contenu = $contenu;

        return $this;
    }

    public function getDaterec(): ?string
    {
        return $this->daterec;
    }

    public function setDaterec(string $daterec): self
    {
        $this->daterec = $daterec;

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

    public function getId(): ?int
    {
        return $this->id;
    }

    public function setId(?int $id): self
    {
        $this->id = $id;

        return $this;
    }

    public function getIdraison(): ?Raison
    {
        return $this->idraison;
    }

    public function setIdraison(?Raison $idraison): self
    {
        $this->idraison = $idraison;

        return $this;
    }
 
      

}
