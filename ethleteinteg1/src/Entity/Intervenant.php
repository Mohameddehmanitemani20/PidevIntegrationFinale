<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Symfony\Component\Serializer\Annotation\Groups;
/**
 * @ORM\Entity
 * @UniqueEntity("email")
 */
/**
 * Intervenant
 *
 * @ORM\Table(name="intervenant")
 * @ORM\Entity
 */

/**
 * @ORM\Entity(repositoryClass="App\Repository\IntervenantRepository")
 * @UniqueEntity(fields={"nom","prenom"}, message="Cet intervenant existe  déjà  .")
 */
class Intervenant
{ public function __toString()
{
    return $this->nom;
}
    /**
     * @var int
     *
     * @ORM\Column(name="id_inter", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @Groups("post:read")
     */
    private $idInter;

    /**
     * @var string
     *
     * @ORM\Column(name="image_In", type="string", length=100, nullable=false)
     * @Groups("post:read")
     */
    private $imageIn;

    /**
     * @var string
     *
     *  @Assert\NotBlank(message=" Le champ du nom doit etre non vide")
     * @ORM\Column(name="nom", type="string", length=30, nullable=false)
     * @Groups("post:read")
     */
    private $nom;

    /**
     * @var string
     *
     * @Assert\NotBlank(message="prénom doit etre non vide")
     * @ORM\Column(name="prenom", type="string", length=30, nullable=false)
     * @Groups("post:read")
     */
    private $prenom;

    /**
     * @var string
     *@Assert\Email(
     *     message = "The email '{{ value }}' is not a valid email."
     * )
     * @ORM\Column(name="email", type="string", length=50, unique=true)
     * @Groups("post:read")
     */
    private $email;

    /**
     * @var string
     *
     * @ORM\Column(name="telephone", type="string", length=8, nullable=false)
     * @Groups("post:read")
     */
    private $telephone;

    /**
     * @var string
     *
     * @ORM\Column(name="id_typeint", type="string", length=50, nullable=false)
     * @Groups("post:read")
     */
    private $idTypeint;

    public function getIdInter(): ?int
    {
        return $this->idInter;
    }

    public function getImageIn()
    {
        return $this->imageIn;
    }

    public function setImageIn( $imageIn)
    {
        $this->imageIn = $imageIn;

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

    public function getPrenom(): ?string
    {
        return $this->prenom;
    }

    public function setPrenom(string $prenom): self
    {
        $this->prenom = $prenom;

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

    public function getTelephone(): ?string
    {
        return $this->telephone;
    }

    public function setTelephone(string $telephone): self
    {
        $this->telephone = $telephone;

        return $this;
    }

    public function getIdTypeint(): ?string
    {
        return $this->idTypeint;
    }

    public function setIdTypeint(string $idTypeint): self
    {
        $this->idTypeint = $idTypeint;

        return $this;
    }


}