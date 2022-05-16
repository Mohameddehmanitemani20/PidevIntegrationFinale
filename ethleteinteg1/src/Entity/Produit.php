<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;

/**
 * Produit
 *
 * @ORM\Table(name="produit", indexes={@ORM\Index(name="idcateg", columns={"idcateg"})})
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="App\Repository\ProduitRepository")
 * @UniqueEntity("nomp")
 */
class Produit
{
    /**
     * @var int
     *
     * @ORM\Column(name="idp", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     */
    private $idp;

    /**
     * @var string
     *
     * @ORM\Column(name="nomp", type="string", length=40, nullable=false)
     * @Assert\NotBlank(message="Veuillez remplir ce champs")
     */
    private $nomp;

    /**
     * @var float
     *
     * @ORM\Column(name="prix", type="float", precision=10, scale=0, nullable=false)
     * @Assert\NotBlank(message="Veuillez remplir ce champs")
     * @Assert\Positive(message="the price you entered is either negative or null")
     *
     */
    private $prix;

    /**
     * @var \Categorie
     *
     * @ORM\ManyToOne(targetEntity="Categorie")
     * @ORM\JoinColumns({
     *   @ORM\JoinColumn(name="idcateg", referencedColumnName="idcateg")
     * })
     */
    private $idcateg;

    public function getIdp(): ?int
    {
        return $this->idp;
    }

    public function getNomp(): ?string
    {
        return $this->nomp;
    }

    public function setNomp(string $nomp): self
    {
        $this->nomp = $nomp;

        return $this;
    }

    public function getPrix(): ?float
    {
        return $this->prix;
    }

    public function setPrix(float $prix): self
    {
        $this->prix = $prix;

        return $this;
    }

    public function getIdcateg(): ?Categorie
    {
        return $this->idcateg;
    }

    public function setIdcateg(?Categorie $idcateg): self
    {
        $this->idcateg = $idcateg;

        return $this;
    }


}
