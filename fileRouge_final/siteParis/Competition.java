package siteParis;

import java.util.LinkedList;


public class Competition{

   /**
   * Declarer les attributs de cette classe 
   * @para    nom             representer le nom de la competition
   * @para    aCompetiteur    list de competiteurs dans la competition
   * @para    aPari           list de paris dans la competition
   * @para    dateCloture     le datecloture de la competition 
   */
   
   private String nom;
   private LinkedList<Competiteur> aCompetiteurs = new LinkedList<Competiteur>();
   private LinkedList<Pari> aParis = new LinkedList<Pari>();
   private DateFrancaise dateCloture;

   
   /**
   * le constructeur de la classe
   * @para dateCloture
   * @para nom
   */
   
   public Competition( String nom, DateFrancaise dateCloture) throws CompetitionException{
      
      // verifier le nom de la competition
      if (nom == null) throw new CompetitionException();
      if (nom.length() < 4) throw new CompetitionException();
      for (int i = 0 ; i < nom.length() ; i++){
         char c = nom.charAt(i);
         if (!Character.isLetterOrDigit(c) && c!='-' && c!='_') throw new CompetitionException();
      } 
      
      // verifier le datecloture est intialise ou non
      if (dateCloture == null) throw new CompetitionException();
      
         this.nom = nom;
         this.dateCloture = dateCloture;
      
       //  this.aCompetiteurs = new LinkedList<Competiteur>();
       //  this.aParis = new LinkedList<Pari>();
        
   }
   
   /**
   * Methode rend un liste de competiteurs
   */
   
   public LinkedList<Competiteur> getCompetiteur(){
   
      return this.aCompetiteurs;
   }
   
   /**
   * Methode rend la date cloture
   */
      
   public DateFrancaise getDate(){
   
      return this.dateCloture;
   }
   
   /**
   * Methode rend le nom de la competition
   */
   
   public String getNom(){
   
      return this.nom;
   }
   
   /**
   * Methode adde un nouveau competiteur dans la competition
   * @para    competiteur
   */
   
   public void addCompetiteur(Competiteur competiteur){
      this.aCompetiteurs.addLast(competiteur);   
   }
   
   /**
   * Methode adde un liste des noms de type String[] comme un liste des competiteurs
   *@ para competieurs
   */
   
   public void ajouterCompetiteur(String[] competiteurs) throws CompetitionException{
      // Creer des competiteurs et les adder dans la competition      
      for (String c: competiteurs){
         Competiteur c1 = new Competiteur(c,this);
         this.addCompetiteur(c1);
      }
      
   }
   
   
   /**
   * Methode rend un liste de paris dans la competition
   */
   
   public LinkedList<Pari> getParisDeLaCompetition(){
      return this.aParis;
   }
   
   /**
   * Methode adde un pari dans la competition
   * @para pari
   */
   
   public void addPari(Pari pari){
   
      this.aParis.addLast(pari);
   }
   
   /**
   * Redistribuer les jetons pour les joueurs qui a mis sur le vainqueur specifique
   * @para  Vainqueur
   */
  
   public void solderUneCompetition(String vainqueur){
   
      long sommeTotale = 0; // la somme totale de la competition
      long sommeMise = 0; // la somme misee sur ce vainqueur
      
      // Prendre la some totale et la somme misee sur le vainqueur
      
      for (Pari p: this.aParis){    
         sommeTotale = sommeTotale + p.getMiseEnJetons();
         if (p.getVainqueurEnvisage().equals(vainqueur)){
            sommeMise = sommeMise + p.getMiseEnJetons();
         }    
      }
         
      // Calculer le gain du vainqueur dans le competition
      
      float gain = 0;
      if(sommeMise != 0)
         gain = ((float)sommeTotale/(float)sommeMise);
      //System.out.println("Gain: "+gain+" de Competition"+this.getNom());
         
      // Calculer les jetons que les joueur recoivent dans deux cas
      // cas 1:  Auccun joueur gagne dans la competition ce-a-dire sommeMise = 0 et gain = 0
      // cas 2:  Il y a un moins de joueur gagnant dans la competition ce-a-dire sommeMise != 0 et gain >= 1
      
         if(gain == 0){
            for(Pari p: this.aParis){
               Joueur j = p.getJoueur();
               j.solderVainqueur(0,p.getMiseEnJetons());
            }         
         }
         else{
            for(Pari p: this.aParis){
               if (p.getVainqueurEnvisage().equals(vainqueur)){
                  Joueur j = p.getJoueur();
                  j.solderVainqueur(gain,p.getMiseEnJetons());
               }
               else
               {
                  Joueur j = p.getJoueur();
                  j.diminuerVainqueur(p.getMiseEnJetons());
               }
            }
         }
           
   }
   
   /**
   * Rendre l'information sur la competition
   */
   
   public LinkedList<String> toList(){
   
      LinkedList<String> info = new LinkedList<String>();
      info.add("Le nom de competition: " +this.nom);
      info.add("Le date cloture: " +this.dateCloture);
      return info;
      
   }
   
   /**
   * Main function
   */
   
   public static void main(String[] args){
      
     // Test constructor
  try {
      System.out.println(" Test constructor ");
      DateFrancaise.setDate(21, 10, 2018);   
      try{
         
         Competition c = new Competition(null, new DateFrancaise(1, 11, 2018));
         System.out.println(" Creer une competition invalide ( nom = null ) n'a pas leve CompetitionException ");
      }
      catch(CompetitionException e){}
      catch(Exception e){
         System.out.println(" Creer une competition invalide ( nom = null ) n'a pas leve CompetitionException mais leve:  "+ e.getClass().getName());
      }
      
      try{
         
         Competition c = new Competition("ABC", new DateFrancaise(1,11,2018));
         System.out.println(" Creer une competition invalide (le nombre de character est inferieur a 4 ) n'a pas leve CompetitionException ");
      }
      catch(CompetitionException e){}
      catch(Exception e){
         System.out.println(" Creer une competition invalide (le nombre de character est inferieur a 4 ) n'a pas leve CompetitionException mais leve:  "+ e.getClass().getName());
      }
      
      try{
         
         Competition c = new Competition("football_2", null);
         System.out.println(" Creer une competition invalide ( le datecloture n'est pas initialise ) n'a pas leve CompetitionException ");
      }
      catch(CompetitionException e){}
      catch(Exception e){
         System.out.println(" Creer une competition invalide ( le datecloture n'est pas initialise ) n'a pas leve CompetitionException mais leve:  "+ e.getClass().getName());
      }
      
      try{
         
         Competition c = new Competition("football_2", new DateFrancaise(1,11,2018));
      }
      catch(CompetitionException e){System.out.println(" Creer une competition valide a  leve CompetitionException ");}
      catch(Exception e){
         System.out.println(" Creer une competition valide n'a pas leve CompetitionException mais leve:  "+ e.getClass().getName());
      }
      
      // Test les methodes de la classe Competition
      System.out.println(" Test les methods de la classe Competition ");
      
      System.out.println(" Creer une competition avec le nom 'football_2' et la datecloture '1 11 2018' ");
      Competition c = new Competition("football_2", new DateFrancaise(1,11,2018));
      System.out.println("Test toString: " + c.toList());
      
      // Creer joueurs
      Joueur j1 = new Joueur("NGUYEN", "Van-Khoa", "nvkvn1");
      Joueur j2 = new Joueur("NGUYEN", "Van-Khoa", "nvkvn2");
      Joueur j3 = new Joueur("NGUYEN", "Van-Khoa", "nvkvn3");

      //Crediter les comptes de joueurs
      j1.crediterUnJoueur(100);
      j2.crediterUnJoueur(100);
      j3.crediterUnJoueur(100);
 
      //Creer les competiteurs pour la competition
      System.out.println("Creer une competition avec deux competiteur Barcelona et RealMarid ");
      String[] competiteurs = {"Barcelona", "RealMarid"};
      c.ajouterCompetiteur(competiteurs);
      System.out.println(" Test ajouterCompetiteur: "+c.getCompetiteur().toString());
      
      // Miser sur Vainqueur
      
      Pari p1 = j1.miserVainqueur(c,"Barcelona",30);
      Pari p2 = j2.miserVainqueur(c,"RealMarid",20);
      Pari p3 = j3.miserVainqueur(c, "RealMarid",15);
      
      // Adder les paris dans le liste de pari en cours de la competition
      c.addPari(p1);
      c.addPari(p2);
      c.addPari(p3);
      System.out.println("Test addPari: " + c.getParisDeLaCompetition().toString());
      
      // Tester la fonction solderUneCompetition 
      System.out.println("L'information de chaque joueurs apres avoir effectue solder une competition pour Barcelona ");
      c.solderUneCompetition("Barcelona");
      System.out.println(j1.toList());
      System.out.println(j2.toList());
      System.out.println(j3.toList());
           
   } catch(Exception e){ System.out.println("Exception non souhait ");
      e.printStackTrace();
   }
   
   }
   
   
} 