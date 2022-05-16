<?php

namespace App\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Bridge\Doctrine\Validator\Constraints\UniqueEntity;
use Symfony\Component\Security\Core\User\UserInterface;
use Symfony\Component\Validator\Constraints as Assert;
use Captcha\Bundle\CaptchaBundle\Validator\Constraints as CaptchaAssert;
use Symfony\Component\Serializer\Annotation\Groups;

/**
 * User
 *
 * @ORM\Table(name="user")
 * @ORM\Entity
 * @ORM\Entity(repositoryClass="App\Repository\UserRepository")
 * @UniqueEntity(fields={"username"}, message="There is already an account with this username")
 */
class User implements UserInterface
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer", nullable=false)
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="IDENTITY")
     * @Groups("read:users")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="adresse", type="string", length=255, nullable=true)
     * @Assert\NotBlank(message="le champs ne doit pas etre vide")
     * @Groups("read:users")
     */
    private $adresse;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="date_naissance", type="date", nullable=true)
     * @Groups("read:users")
     */
    private $dateNaissance;

    /**
     * @var string
     *
     * @ORM\Column(name="email", type="string", length=255, nullable=true)
     * @Assert\NotBlank(message="le champs ne doit pas etre vide")
     * @Assert\Email(message = "The email '{{ value }}' is not a valid email")
     * @Groups("read:users")
     */
    private $email;

    /**
     * @var string
     *
     * @ORM\Column(name="nom", type="string", length=255, nullable=false)
     *  @Assert\NotBlank(message="le champs ne doit pas etre vide")
     * @Assert\Length(
     *     min=3,
     *     max=50,
     *     minMessage="The first name must be at least 3 characters long",
     *     maxMessage="The first name cannot be longer than 50 characters"
     * )
     *  @Groups("read:users")
     */
    private $nom;

    /**
     * @var int
     *
     * @ORM\Column(name="num_tel", type="integer", nullable=true)
     * @Assert\Length(
     *      min = 8,
     *      max = 8,
     *      minMessage="le numero de telephone doit etre 8 chiffres",
     *     maxMessage="le numero de telephone doit etre 8 chiffres"
     * )
     *  @Groups("read:users")
     */
    private $numTel;

    /**
     * @var string
     *
     * @ORM\Column(name="password", type="string", length=255, nullable=true)
     *  @Groups("read:users")
     */
    private $password;

    /**
     * @var string
     *
     * @ORM\Column(name="prenom", type="string", length=255, nullable=true)
     *  @Assert\NotBlank(message="le champs ne doit pas etre vide")
     * @Assert\Length(
     *     min=3,
     *     max=50,
     *     minMessage="The last name must be at least 3 characters long",
     *     maxMessage="The last name cannot be longer than 50 characters"
     * )
     *  @Groups("read:users")
     */
    private $prenom;

    /**
     * @ORM\Column(type="json")
     *  @Groups("read:users")
     */
    private $roles = [];

    /**
     * @var string
     *
     * @ORM\Column(name="username", type="string", length=255, nullable=true)
     * @Assert\NotBlank(message="le champs ne doit pas etre vide")
     *  @Groups("read:users")
     */
    private $username;

    /**
     * @var string|null
     *
     * @ORM\Column(name="genre", type="string", length=30, nullable=true)
     * @Assert\NotBlank(message="le champs ne doit pas etre vide")
     *  @Groups("read:users")
     */
    private $genre;

    /**
     * @var int|null
     *
     * @ORM\Column(name="id_eq", type="integer", nullable=true)
     * @Assert\NotBlank(message="le champs ne doit pas etre vide")
     *  @Groups("read:users")
     */
    private $idEq;

    /**
     * @ORM\Column(type="string", length=50, nullable=true)
     */
    private $activation_token;

    /**
     * @ORM\Column(type="string", length=60, nullable=true)
     */
    private $reset_token;

     /**
     * @ORM\Column(type="string", length=65, nullable=true)
     */
    private $disable_token;

     /**
     * @ORM\Column(type="string", length=255, nullable=true)
     * @Groups("read:users")
     */
    private $verificationCode;


    /**
     * @ORM\Column(type="boolean")
     */
    private $isVerified = false;


     /**
   * @CaptchaAssert\ValidCaptcha(
   *      message = "CAPTCHA validation failed, try again.",
   *      groups={"registration"}
   * )
   */
  protected $captchaCode;

 

  public function getCaptchaCode()
  {
    return $this->captchaCode;
  }

  public function setCaptchaCode($captchaCode)
  {
    $this->captchaCode = $captchaCode;
  }





    public function getId(): ?int
    {
        return $this->id;
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

    public function getDateNaissance(): ?\DateTimeInterface
    {
        return $this->dateNaissance;
    }

    public function setDateNaissance($dateNaissance): self
    {
    $this->dateNaissance = $dateNaissance;
    
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

    public function getNom(): ?string
    {
        return $this->nom;
    }

    public function setNom(string $nom): self
    {
        $this->nom = $nom;

        return $this;
    }

    public function getNumTel(): ?int
    {
        return $this->numTel;
    }

    public function setNumTel(int $numTel): self
    {
        $this->numTel = $numTel;

        return $this;
    }

    public function getPassword(): ?string
    {
        return $this->password;
    }

    public function setPassword(string $password): self
    {
        $this->password = $password;

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

   

    public function getUsername(): ?string
    {
        return $this->username;
    }

    public function setUsername(string $username): self
    {
        $this->username = $username;

        return $this;
    }

    public function getGenre(): ?string
    {
        return $this->genre;
    }

    public function setGenre(?string $genre): self
    {
        $this->genre = $genre;

        return $this;
    }

    public function getIdEq(): ?int
    {
        return $this->idEq;
    }

    public function setIdEq(?int $idEq): self
    {
        $this->idEq = $idEq;

        return $this;
    }

    public function getRoles(): array
    {
        $roles = $this->roles;
        // guarantee every user at least has ROLE_USER
        //$roles[] = 'ROLE_USER';

        return array_unique($roles);
    }

    public function setRoles(array $roles): self
    {
        $this->roles = $roles;

        return $this;
    }

    public function getSalt()
    {
        // TODO: Implement getSalt() method.
    }

    public function eraseCredentials()
    {
        // TODO: Implement eraseCredentials() method.
    }

    public function __toString()
    {
        return $this->getNom();
    }

  

    public function getActivationToken(): ?string
    {
        return $this->activation_token;
    }

    public function setActivationToken(?string $activation_token): self
    {
        $this->activation_token = $activation_token;

        return $this;
    }

    public function getResetToken(): ?string
    {
        return $this->reset_token;
    }

    public function setResetToken(?string $reset_token): self
    {
        $this->reset_token = $reset_token;

        return $this;
    }

    public function getDisableToken(): ?string
    {
        return $this->disable_token;
    }

    public function setDisableToken(?string $disable_token): self
    {
        $this->disable_token = $disable_token;

        return $this;
    }

    public function isVerified(): bool
    {
        return $this->isVerified;
    }

    public function setIsVerified(bool $isVerified): self
    {
        $this->isVerified = $isVerified;

        return $this;
    }

    public function getVerificationCode(): ?string
    {
        return $this->verificationCode;
    }

    public function setVerificationCode(?string $verificationCode): self
    {
        $this->verificationCode = $verificationCode;

        return $this;
    }

    public function serialize() {
        return serialize($this->id);
        }
    
        public function unserialize($data) {
        $this->id = unserialize($data);
        }

       


}
