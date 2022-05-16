<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * Fournisseur
 *
 * @ORM\Table(name="fournisseur", uniqueConstraints={@ORM\UniqueConstraint(name="telf", columns={"telf", "adresse"})}, indexes={@ORM\Index(name="idp", columns={"idp"})})
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="App\Repository\FournisseurRepository")
 */
class Fournisseur
{
    /**
     * @var int
     *
     * @ORM\Column(name="idf", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idf;

    
    /**
     * @var string
     * 
     * @ORM\Column(name="nomf", type="string", length=50)
     * @Assert\NotBlank(message="Veuillez remplir ce champs")
     */
    private $nomf;


    /**
     * @var string
     *
     * @ORM\Column(name="prenomf", type="string", length=50, nullable=false)
     * @Assert\NotBlank(message="Veuillez remplir ce champs")
     */
    private $prenomf;

    /**
     * @var string
     *
     * @ORM\Column(name="email", type="string", length=50, nullable=false)
     * @Assert\Email(
     *     message = "The email '{{ value }}' is not a valid email."
     * )
     */
    private $email;

    /**
     * @var int
     *
     * @ORM\Column(name="telf", type="integer", nullable=false)
     * @Assert\Positive
     * @Assert\Length(
     *      min = 8,
     *      max = 8,
     *      minMessage = "the phone number  must be at least {{ limit }} characters long",
     *      maxMessage = "the phone number cannot be longer than {{ limit }} characters"
     * )
     */
    private $telf;

    /**
     * @var string
     *
     * @ORM\Column(name="adresse", type="string", length=50, nullable=false)
     */
    private $adresse;

    /**
     * @var \Produit
     *
     * @ORM\ManyToOne(targetEntity="Produit")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idp", referencedColumnName="idp")
     * })
     */
    private $idp;

    public function getIdf(): ?int
    {
        return $this->idf;
    }

    public function getNomf(): ?string
    {
        return $this->nomf;
    }

    public function setNomf(string $nomf): self
    {
        $this->nomf = $nomf;

        return $this;
    }

    public function getPrenomf(): ?string
    {
        return $this->prenomf;
    }

    public function setPrenomf(string $prenomf): self
    {
        $this->prenomf = $prenomf;

        return $this;
    }

    public function getEmail(): ?string
    {
        return $this->email;
    }

    public function setEmail(string $email): self
    {
        $this->email = $email;

        return $this;
    }

    public function getTelf(): ?int
    {
        return $this->telf;
    }

    public function setTelf(int $telf): self
    {
        $this->telf = $telf;

        return $this;
    }

    public function getAdresse(): ?string
    {
        return $this->adresse;
    }

    public function setAdresse(string $adresse): self
    {
        $this->adresse = $adresse;

        return $this;
    }

    public function getIdp(): ?Produit
    {
        return $this->idp;
    }

    public function setIdp(?Produit $idp): self
    {
        $this->idp = $idp;

        return $this;
    }


}
