<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;

class CategorySearch
{

    /**
     * @ORM\ManyToOne(targetEntity="App\Entity\Categorie")
     */
    private $category;


    public function getCategory(): ?Categorie
    {
        return $this->category;
    }

    public function setCategory(?Categorie $category): self
    {
        $this->category = $category;

        return $this;
    }



}