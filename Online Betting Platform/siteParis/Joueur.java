package siteParis;

import java.util.Random;
import java.util.LinkedList;


public class Joueur{
   
   private LinkedList<Pari> faitParis; // Une liste de Pari que le joueur fait
   private String nom;
   private String prenom;
   private String pseudo;
   private String password;
   private long soldeEnJetons;
   private long miseEnJetons;
   
   public Joueur(String nom, String prenom, String pseudo) throws JoueurException{
      
      // verifier le nom de joueur
      if (nom == null) throw new JoueurException();
      if (nom.length() < 1) throw new JoueurException();
      for (int i = 0 ; i < nom.length() ; i++){
         char c = nom.charAt(i);
         if (!Character.isLetter(c) && c!='-') throw new JoueurException(); // lettres et tiret sont les seuls caractere autorises
      } 
      
      // verifier le prenom de joueur
      if (prenom == null) throw new JoueurException();
      if (prenom.length() < 1) throw new JoueurException();
      for (int i = 0 ; i < nom.length() ; i++){
         char c = prenom.charAt(i);
         if (!Character.isLetter(c) && c!='-') throw new JoueurException(); // lettres et tiret sont les seuls caracteres autorises

      } 
      
      //verifier le pseudo code
      if (pseudo == null) throw new JoueurException();
      if (pseudo.length() < 4) throw new JoueurException();
      for (int i = 0 ; i < pseudo.length() ; i++){
         char c = pseudo.charAt(i);
         if (!Character.isLetterOrDigit(c)) throw new JoueurException(); // lettres et chiffres sont les seuls caracteres autorises
      } 
   
      this.nom = nom;
      this.prenom = prenom;
      this.pseudo = pseudo;
      this.soldeEnJetons = 0;
      this.miseEnJetons = 0;
      this.faitParis = new LinkedList<Pari>();
      
      // creer password d'un joueur
      Random virtualPassword = new Random();                       // password doit avoir au moins 8 caracteres
      this.password = virtualPassword.nextInt(1000000) + 10000 + pseudo; // leetres et chiffre sont les seuls caracteres autorises
      
      // verifier password d'un joueur
      if (this.password == null) throw new JoueurException();
      if (this.password.length() < 8) throw new JoueurException();
      for (int i = 0 ; i < this.password.length() ; i++){
         char c = this.password.charAt(i);
         if (!Character.isLetterOrDigit(c)) throw new JoueurException(); // lettres et chiffres sont les seuls caracteres autorises
      } 
   }
   
   // Ajouter un objet Pari dans la liste des paris que ce joueur fait
   public void addPari(Pari pari){
      this.faitParis.add(pari);
   }
   
   // Ajouter un nombre de l'argent represente par jetons dans le compte de ce joueur
   public void crediterUnJoueur(long Jetons){
      this.soldeEnJetons = this.soldeEnJetons + Jetons;
   }
   
   // Debiter le compte de ce joueur en jetons
   public void debiterUnJoueur(long Jetons){
      this.soldeEnJetons = this.soldeEnJetons - Jetons;
   }
   
   /** 
   * Diminuer les sommes en Jetons quand la competition finit et ce pari ne gagne pas
   * et il y a au moins un pari qui gagne dans cette competition
   * @parametre Jetons        le somme en jetons que ce pari a mise sur cette competition
   */
   public void diminuerVainqueur(long Jetons){
      this.miseEnJetons = this.miseEnJetons - Jetons;
   }
   
   /**
   * Rendre le somme en jetons que le joueur a mis sur les compétitions
   */
   public long getMise(){
      return this.miseEnJetons;
   }
   
   /**
   * Rendre le nom du joueur
   */
   public String getNom(){
      return this.nom;
   }
   
   /**
   * Rendre une liste de Paris que le joueur fait
   */
   public LinkedList<Pari> getParisMise(){
      return this.faitParis;      
   }
   
   /**
   * Rendre le password du joueur
   */
   public String getPassword(){
      return this.password;
   }
   
   /**
   * Rendre le prenom du joueur
   */
   public String getPrenom(){
      return this.prenom;
   }
   
   /**
   * Rendre le Pseudo du joueur
   */
   public String getPseudo(){
      return this.pseudo;
   }
   
   /**
   * Rendre le somme en jetons que le joueur a dans son compte.
   */
   public long getSolde(){
      return this.soldeEnJetons;
   }
   
   /**
   * Parier sur une compétition, en désignant un Vainqueur. Le compte du joueur est débité du nombre du jetons mises
   */
   public Pari miserVainqueur(Competition c, String vainqueurEnvisage,long mise) throws MetierException, CompetitionException, JoueurException{
      this.soldeEnJetons = this.soldeEnJetons - mise;
      this.miseEnJetons = this.miseEnJetons + mise;
         Pari p = new Pari(this, c, mise, vainqueurEnvisage);
         this.addPari(p);
         return p;
   }
   
   /**
   * Crediter le compte de ce joueur si il gagne dans le competition
   * ou si il n'y pas de pari qui gagne dans cette competition
   *@para gain      l'indice du gagne de ce pari (= le somme en jetons mises sur cette competition/ le somme en jetons mises sur le vainqueur gagne)
   *@para Jetons    le jetons que ce pari mise sur le vainqueur
   */
   public void solderVainqueur(float gain, long Jetons){
      //System.out.println("Avant de solderVainqueur joueur: "+this.getPseudo()+": "+this.getSolde());
      if (gain != 0){
      // il y a au moins un pari qui gagne dans la competition
         this.soldeEnJetons = this.soldeEnJetons + (long)(gain*Jetons);
         
      }
      else{
      // il n'y a auccun de pari qui gagne dans cette competition
         this.soldeEnJetons = this.soldeEnJetons + Jetons;
           }
      this.miseEnJetons = this.miseEnJetons - Jetons;
     // System.out.println("Apres de solderVainqueur joueur: "+this.getPseudo()+": "+this.getSolde());
   }
   /**
   * Rendre la liste de information du joueur
   */
   public LinkedList<String> toList(){
   
      LinkedList<String> info = new LinkedList<String>();
      info.add("Nom: " +this.nom);
      info.add("Prenom: " +this.prenom);
      info.add("Pseudo: " +this.pseudo);
      info.add("Le somme dans le compte disponible: " +this.soldeEnJetons);
      info.add("Le somme mis sur les competitions: " +this.miseEnJetons);
      return info;
   }
   
   /**
   * Main function
   */
   
   public static void main(String[] args){
   
      // Test constructor
      try{
      System.out.println(" Test constructor ");
      try{
         Joueur j = new Joueur(null,"vankhoa","vk123");
         System.out.println(" Creer un joueur avec un nom invalid (null) n'a pas leve JoueurException " );
      }
      catch(JoueurException e){}
      catch(Exception e){
         System.out.println(" Creer un joueur avec un nom invalid (null) n'a pas leve JoueurException mais: "+e.getClass().getName());
      }
      try{
         Joueur j = new Joueur("","vankhoa","vk123");
         System.out.println(" Creer un joueur avec un nom invalid (ayant longue étant inférieur à 1) n'a pas leve JoueurException " );
      }
      catch(JoueurException e){}
      catch(Exception e){
         System.out.println(" Creer un joueur avec uun nom invalid (ayant longue étant inférieur à 1) n'a pas leve JoueurException mais: "+e.getClass().getName());
      }
      try{
         Joueur j = new Joueur("NGUYEN96","vankhoa","vk123");
         System.out.println(" Creer un joueur avec un nom invalid (contient le nombre) n'a pas leve JoueurException " );
      }
      catch(JoueurException e){}
      catch(Exception e){
         System.out.println(" Creer un joueur avec  un nom invalid (contient le nombre) n'a pas leve JoueurException mais: "+e.getClass().getName());
      }
       try{
         Joueur j = new Joueur("NGUYEN*","vankhoa","vk123");
         System.out.println(" Creer un joueur avec un nom invalid (contient *)  n'a pas leve JoueurException " );
      }
      catch(JoueurException e){}
      catch(Exception e){
         System.out.println(" Creer un joueur avec un nom invalid (contient *) n'a pas leve JoueurException mais: "+e.getClass().getName());
      }
      try{
         Joueur j = new Joueur("NGUYEN",null,"vk123");
         System.out.println(" Creer un joueur avec un prenom invalide (null) n'a pas leve JoueurException " );
      }
      catch(JoueurException e){}
      catch(Exception e){
         System.out.println(" Creer un joueur avec un prenom invalide (null) n'a pas leve JoueurException mais: "+e.getClass().getName());
      }
      try{
         Joueur j = new Joueur("NGUYEN","","vk123");
         System.out.println(" Creer un joueur avec un prenom invalide (avec la longue étant inférieur à 1) n'a pas leve JoueurException " );
      }
      catch(JoueurException e){}
      catch(Exception e){
         System.out.println(" Creer un joueur avec  un prenom invalide (avec la longue étant inférieur à 1) n'a pas leve JoueurException mais: "+e.getClass().getName());
      }
      try{
         Joueur j = new Joueur("NGUYEN","van123","vk123");
         System.out.println(" Creer un joueur avec un prenom invalid (avec des nombres) n'a pas leve JoueurException " );
      }
      catch(JoueurException e){}
      catch(Exception e){
         System.out.println(" Creer un joueur avec un prenom invalid (avec des nombres) n'a pas leve JoueurException mais: "+e.getClass().getName());
      }
      try{
         Joueur j = new Joueur("NGUYEN","van?","vk123");
         System.out.println(" Creer un joueur avec un prenom invalid (avec ?) n'a pas leve JoueurException " );
      }
      catch(JoueurException e){}
      catch(Exception e){
         System.out.println(" Creer un joueur avec un prenom invalid (avec ?) n'a pas leve JoueurException mais: "+e.getClass().getName());
      }
       try{
         Joueur j = new Joueur("NGUYEN","Van-Khoa",null);
         System.out.println(" Creer un joueur avec un pseudo invalid (null) n'a pas leve JoueurException " );
      }
      catch(JoueurException e){}
      catch(Exception e){
         System.out.println(" Creer un joueur avec un pseudo invalid (null) n'a pas leve JoueurException mais: "+e.getClass().getName());
      }
      try{
         Joueur j = new Joueur("NGUYEN","Van-Khoa","*/%^&");
         System.out.println(" Creer un joueur avec un pseudo invalid (avec */%^&) n'a pas leve JoueurException " );
      }
      catch(JoueurException e){}
      catch(Exception e){
         System.out.println(" Creer un joueur avec un pseudo invalid (avec */%^&) n'a pas leve JoueurException mais: "+e.getClass().getName());
      }
      try{
         Joueur j = new Joueur("NGUYEN","Van-Khoa","vk");
         System.out.println(" Creer un joueur avec un pseudo invalid (la longue est inférieur à 4) n'a pas leve JoueurException " );
      }
      catch(JoueurException e){}
      catch(Exception e){
         System.out.println(" Creer un joueur avec un pseudo invalid (la longue est inférieur à 4) n'a pas leve JoueurException mais: "+e.getClass().getName());
      }
      try{
         Joueur j = new Joueur("NGUYEN","Van-Khoa","vk123");
         System.out.println(" Creer un joueur valid " );
      }
      catch(Exception e){
         System.out.println("Creer un joueur valid a leve JoueurException mais: ");
         e.printStackTrace();
      }
      
      Joueur j = new Joueur("NGUYEN","Van-Khoa","vk123");
      System.out.println(j.toList());
      // Test password creation function
      System.out.println("Test creer password du joueur: "+ j.getPassword());
      
      // Test crediter le joueur
      j.crediterUnJoueur(14);
      System.out.println("Le somme dans le compte du joueur apres crediter en 14 jetons: "+j.getSolde());
      
      //Test debiter le joueur
      j.debiterUnJoueur(7);
      System.out.println("Le somme dans le compte du joueur apres debiter en 7 jetons: "+j.getSolde());
      
      //Creer un competition pour tester la fonction miserVainqueur
      DateFrancaise dateCloture = new DateFrancaise(26,10,2018,10,51);
      Competition c = new Competition("Football",dateCloture);
      
      //Tester la fonction miserVainquer
      System.out.println("Tester la fonction miserVainqueur: ");
      Pari p = j.miserVainqueur(c, "Barcelona", 2);
      System.out.println("Information du joueur apres miser sur Barcelona: ");
      System.out.println(j.toList());
      
      //Tester la fonction solderVainqueur (dans le cas joueur gagne)
      System.out.println("Tester la fonction solderVainqueur: ");
      j.solderVainqueur((float)(2.5),2);
      System.out.println("Information du joueur quand dans le cas de gagner dans la competition : ");
      System.out.println(j.toList());
      
      //Tester la fonction solderVainqueur (dans le cas joueur ne gagne pas)
      System.out.println("Tester la fonction diminuerVainqueur: ");
      System.out.println("Avant de diminuerVainquer: "+j.toList());
      j.diminuerVainqueur(2);
      System.out.println("Information du joueur quand dans le cas de ne pas gagner dans la competition : ");
      System.out.println(j.toList());
      
       //Tester la fonction solderVainqueur (aucun de pari gagne)
      System.out.println("Tester la fonction solderVainqueur avec gain = 0 : ");
      System.out.println("Avant de solderVainquer: "+j.toList());
      j.solderVainqueur(0,2);
      System.out.println("Information du joueur quand dans le cas d'aucun paris gagner dans la competition : ");
      System.out.println(j.toList());
      
   }catch(Exception e){System.out.println("Exception non souhait");
      e.printStackTrace();
   }
   
   }
   
   
   
   
}