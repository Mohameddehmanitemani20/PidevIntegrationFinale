<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Serializer\Annotation\Groups;


/**
 * Avis
 *
 * @ORM\Table(name="avis", indexes={@ORM\Index(name="fk_user", columns={"id_user"})})
 * @ORM\Entity
 */
class Avis
{
    /**
     * @var int
     *
     * @ORM\Column(name="id_avis", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @Groups("post:read")
     */
    private $idAvis;

    /**
     * @var int
     *
     * @ORM\Column(name="note", type="integer", nullable=false)
     * @Groups("post:read")
     */
    private $note;

    /**
     * @var int
     *
     * @ORM\Column(name="id_user", type="integer", nullable=false)
     * @Groups("post:read")
     */
    private $idUser;

   

    public function getIdAvis(): ?int
    {
        return $this->idAvis;
    }

    public function getIdUser(): ?int
    {
        return $this->idUser;
    }

    public function setIdUser(int $idUser): self
    {
        $this->idUser = $idUser;

        return $this;
    }

    public function getNote(): ?int
    {
        return $this->note;
    }

    public function setNote(int $note): self
    {
        $this->note = $note;

        return $this;
    }

   
    public function __toString()
    {
        return $this->note;
        // TODO: Implement __toString() method.
    }


}