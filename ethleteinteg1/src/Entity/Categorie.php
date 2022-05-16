<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * Categorie
 *
 * @ORM\Table(name="categorie")
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="App\Repository\CategorieRepository")
 * @UniqueEntity("nomcateg")
 */
 
class Categorie
{
    /**
     * @var int
     *
     * @ORM\Column(name="idcateg", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @Groups("post:read")
     */
    private $idcateg;

    /**
     * @var string
     *
     * @ORM\Column(name="nomcateg", type="string", length=50, nullable=false)
     * @Assert\NotBlank(message="Veuillez remplir ce champs")
     * @Groups("post:read")
     */
    private $nomcateg;

    public function getIdcateg(): ?int
    {
        return $this->idcateg;
    }

    public function getNomcateg(): ?string
    {
        return $this->nomcateg;
    }

    public function setNomcateg(string $nomcateg): self
    {
        $this->nomcateg = $nomcateg;

        return $this;
    }

    /**
     * @return Collection|Produit[]
     */
    public function getProduit(): Collection
    {
        return $this->produits;
    }



}
