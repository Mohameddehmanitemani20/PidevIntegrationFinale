/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ethlete;
import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.util.props.PropertyException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.SpeechResult;
import edu.cmu.sphinx.api.StreamSpeechRecognizer;
import edu.cmu.sphinx.util.props.PropertyException;
import models.Formation;
import models.Participation;
import models.Utilisateur;
import services.ServiceAffectationFormateur;
import services.ServiceFormateur;
import services.ServiceFormation;
import services.ServiceParticipation;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import javax.sound.midi.Synthesizer;
import models.AffectationFormateur;
import models.Avis;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.Billet;
import models.Categorie;
import models.Commentaire;
import models.Equipe;
import models.Evenement;
import models.Intervenant;
import models.Invitation;
import models.Produit;
import models.Reclamation;
import models.Reponse_Form;
import models.Role;
import models.User;
import services.SendEmail;
import services.ServiceAvis;
import services.ServiceBillet;
import services.ServiceCommentaireImp;
import services.ServiceEquipe;
import services.ServiceEvenement;
import services.ServiceIntervenant;
import services.ServiceInvitation;
import services.ServiceReponseImp;
import services.ServicesCategorie;
import services.ServicesProduit;
import services.ServicesReclamation;

import t2s.son.LecteurTexte;
import util.Mailapi;
import util.Smsapi;

/**
 *
 * @author pc
 */
public class PIDEV {
    public static final String ACCOUNT_SID = "AC68d4b1a9a27586d27aa6f9410dde36f5"; 
    public static final String AUTH_TOKEN = "3cf893f5dadd191a2489feae35d0eb3d"; 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            /*    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
            Message message = Message.creator(
            new  PhoneNumber("+21625378713"),
            new PhoneNumber( "+14439032479"),
            "Bienvenue chez nextec khouk moatez oumour api mayfdhlkch ab3th capture 3ad  khater andi l7a9 f trayef msget")
            .create();
            System.out.println(message.getSid()); */
            
            // Smsapi.sendSMS("", "123");
            // TODO code application logic here
            //ServiceFormation s=new ServiceFormation() ;
            // Formation f=new Formation(2,"form2",Date.valueOf("2012-02-02"),Date.valueOf("2012-02-02"),"En_Ligne","moatez111111111111111111111111111");
            //s.generer_pdf(f);
            // s.ajouter(f);
            //
            
            /*ServiceAffectationFormateur d1=new ServiceAffectationFormateur();
            d1.affectation_formateur(f,u);
            
            
            
            */
            /*
            
            int nbh=0;
            int nbf=0;
            Map<Formation,List<Utilisateur>> map1=new HashMap();
            System.out.println(sp.consulter_particiapnts_par_formation().keySet());*/
            
            /*  ServiceCommentaireImp sc=new ServiceCommentaireImp();
            
            Formation f=new Formation(9);
            System.out.println(sc.filtrer_par_formation(f));
            List<Commentaire> comments=new ArrayList();
            comments=sc.filtrer_par_formation(f);
            for(int i=0;i<=comments.size();i++)
            System.out.println(comments.get(i).getComments());*/
//ServiceParticipation sp=new ServiceParticipation();

//Utilisateur u=new Utilisateur(7);
// System.out.println(sp.consulter_particiapnts_par_formation().values());
// Map<Formation,List<Utilisateur>> map1=sp.consulter_particiapnts_par_formation();

//Formation f=new Formation(6);
//ServiceFormation s=new ServiceFormation() ;
//   List <Utilisateur> list=new ArrayList();
// System.out.println( map1.get(s.rechercher_formation(f)));
/*List <Utilisateur> list=map1.get(f);
for(Formation c:map1.keySet())
System.out.println(map1.get(c));
Utilisateur u=new Utilisateur(7);*/

/*     ServiceParticipation s1=new ServiceParticipation();
s1.Participer_Une_Formation(f, u);*/
/*      ServiceReponseImp sr=new ServiceReponseImp();
ServiceAffectationFormateur af=new ServiceAffectationFormateur();
AffectationFormateur rf = af.ConsulterAffectation_precise1(u, f);
//System.out.print(rf.toString());
ServiceReponseImp srr=new ServiceReponseImp();
srr.consulter_rep(rf.getReponse());
for(Formation c:map1.keySet())
System.out.println(map1.get(c));*/


 
// ServiceAffectationFormateur d1=new ServiceAffectationFormateur();
//System.out.print(d1.consulter_toutes_affectation1(1));
//ServiceParticipation s1= new ServiceParticipation();
//ServiceFormation sf=new ServiceFormation();
//System.out.println(s1.consulter_particiapnts_par_formation());
//Formation f=sf.consulter_formation(3);
//System.out.print(f.toString());
//System.out.println(s1.consulter_particiapnts_par_formation().keySet());

//s1.fichier_participation();
// Map<Integer, List<Participation>> map=s1.afficher().stream().collect(Collectors.groupingBy(e -> e.getFormation_id()));
//System.out.println(map);
//ServiceAffectationFormateur d1=new ServiceAffectationFormateur();
//d1.affectation_formateur(f, u);
//System.out.println(d1.consulter_formateurs_par_formation());
//s1.imprimer_liste_participant_par_form();
/*ServiceBillet sb=new ServiceBillet();
ServiceEvenement se=new ServiceEvenement();
ServiceIntervenant si=new ServiceIntervenant();
Evenement E = new Evenement(1,"lea2gue1",Date.valueOf("2012-02-02"),Date.valueOf("2012-02-02"),"Formation",6,6,0);
//se.ajouterEvenement(E);
//se.modifierEvenement(E);
//se.supprimerEvenement(E);
Billet B = new Billet(3,2,4.5f,Date.valueOf("2021-03-02"));
Billet B1 = new Billet(2,2,4.5f,Date.valueOf("2021-03-02"));

//sb.ajouterBillet(B);
//sb.modifierEvenement(B);
sb.supprimerEvenement(B);
Intervenant I = new Intervenant(1,"Ben 55Abda","Yasmine","benabda@gmail.com",27156643,"sponsor");

si.ajouterIntervenant(I);
//si.modifierIntervenant(I);
si.supprimerIntervenant(I);*/
/*ServiceEquipe us=new ServiceEquipe();

Equipe u1=new Equipe(1,"lefriki121",1);
//us.ajouter(u1);
us.supprimer(1);
// us.ajouter(u);
ServiceAvis ss=new ServiceAvis();
ServiceAvis ss1=new ServiceAvis();
Avis av=new Avis(1,5);
ss1.ajouter(av);
// System.out.println(us.paginateEquipes(1, 1));
System.out.println(ss.afficher());
//System.out.println(ss1.sortBynote());
ServiceInvitation si=new ServiceInvitation();
Invitation i=new Invitation("non_consulté",2,1);
si.ajouter(i);
ServicesProduit sp1 = new ServicesProduit();
Categorie cg1 = new Categorie("accessoires");
ServicesCategorie scg  = new ServicesCategorie();
// scg.ajouterCategorie(cg1);
//scg.ajouterCategorie(cg1);  // ajouter


//System.out.println(scg.afficherCategorie());

Categorie cg2 = new Categorie(3,"Bureatique");
//Produit p1 = new Produit("bracelet",15,8);

//sp.ajouterProduit(p1);  // ajouter


//System.out.println(sp.afficherProduits());

Produit p2 = new Produit("necklace",78,15);
sp1.ajouterProduit(p2);*/
//LecteurTexte lecteur = new LecteurTexte("bonjour");
//   lecteur.playAll();
// lecteur.setTexte("je suis un synthétiseur vocal, qui êtes-vous?");
//lecteur.playAll();
//
// */
//   ServiceInvitation si=new ServiceInvitation();
//Invitation i=new Invitation("non_consulté",2,1);
// si.ajouter(i);

/*  Twilio.init(ACCOUNT_SID, AUTH_TOKEN);


Message message = Message.creator(
new com.twilio.type.PhoneNumber("+21624030100"),
new com.twilio.type.PhoneNumber("+14439032479"),
"check your nextec account")
.create();

System.out.println(message.getSid());*/
//Smsapi.sendSMS("", "Bienvenue chez nextec");

/*  Configuration configuration = new Configuration();

configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

StreamSpeechRecognizer recognizer;
try {
recognizer = new StreamSpeechRecognizer(configuration);
InputStream stream = new FileInputStream(new File("‪C:\\Users\\pc\\OneDrive\\Bureau\\moatez.wav"));

recognizer.startRecognition(stream);
SpeechResult result;
while ((result = recognizer.getResult()) != null) {
System.out.format("Hypothesis: %s\n", result.getHypothesis());
}
recognizer.stopRecognition();

} catch (IOException ex) {
Logger.getLogger(PIDEV.class.getName()).log(Level.SEVERE, null, ex);
}*/

/*System.setProperty("mbrola.base", "C:\\Users\\pc\\OneDrive\\Bureau\\java\\Mbrola2");

try{  final String m="hello";

Voice v;
VoiceManager vm=VoiceManager.getInstance();
v=vm.getVoice(m);

v.allocate();

v.speak("hello");}
catch(Exception e)
{System.out.println("erreur");

}*/
//Reconnaisance r=new Reconnaisance();
//   r.reconnaitre();
// ServiceFormateur sf=new ServiceFormateur();
//System.out.println(sf.afficher());
/*ServicesReclamation sr=new ServicesReclamation();
sr.ajouterReclamation(new Reclamation("mm",1,5,"en cours"));
ServiceIntervenant si=new ServiceIntervenant();
si.modifierIntervenant(new Intervenant (2,"","0","0",123));
System.out.println(si.afficherIntervenant());
ServiceEvenement se=new ServiceEvenement();
//   System.out.println(se.modifierEvenement(new Evenement(1,"","","",1234,"")));*/

        SendEmail.mailing( "rmatoussi3@gmail.com", "m", "moatez");} catch (Exception ex) {
            Logger.getLogger(PIDEV.class.getName()).log(Level.SEVERE, null, ex);
        }
        Role r1 = null;
      User u=new User(r1.ROLE_ADMIN);
      
      String [] s= {u.getRole().toString()};
      
            System.out.println(Arrays.toString(s));
      


    }
    
}
