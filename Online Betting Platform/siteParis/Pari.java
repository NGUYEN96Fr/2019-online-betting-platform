package siteParis;

public class Pari{

   private Joueur parjoueur;
   private Competition appartientCompetition;
   private long miseEnJetons;
   private String vainqueurEnvisage;
   
   /**
   * Constructeur de la classe
   * @para     parjoueur     Pari est fait par le joueur
   * @para     competition    la competition a laquelle le pari participe
   * @para     miseEnJetons   la somme en jetons que le pari a mise sur la competition
   * @para     vainqueurEnvisage    le vainqueur est envisage par le pari
   */
   
   public Pari(Joueur parjoueur, Competition appartientCompetition, long miseEnJetons, String vainqueurEnvisage) throws JoueurException, CompetitionException, MetierException{
   
      if(parjoueur == null) throw new JoueurException();
      if(appartientCompetition == null) throw new CompetitionException();
      if(miseEnJetons <= 0) throw new MetierException();
      if(vainqueurEnvisage == null) throw new CompetitionException();
      
      this.parjoueur = parjoueur;
      this.appartientCompetition = appartientCompetition;
      this.miseEnJetons = miseEnJetons;
      this.vainqueurEnvisage = vainqueurEnvisage; 
   }
   
   /**
   * Rendre la competition
   */
   
   public Competition getCompetition(){
   
      return this.appartientCompetition;
   }
   
   /**
   * Rendre le vainqueur envisage
   */
   
   public String getVainqueurEnvisage(){
   
      return this.vainqueurEnvisage;
   }
   
   /**
   * Rendre le somme en jetons que le pari a mis
   */
   
   public long getMiseEnJetons(){
      return this.miseEnJetons;
   }
   
   /**
   * Rendre le joueur faisant le pari
   */
   
   public Joueur getJoueur(){
   
      return this.parjoueur;
   }
   
   /**
   * Rendre une chain de caractere represant le pari
   */
   
   public String toString(){
   
      String str = "Le joueur: " +this.getJoueur().getNom()+ " avec Pseudo: " +this.getJoueur().getPseudo()+ " a mis sur competition: " +this.getCompetition().getNom()+ " le somme en jetons: " +this.getMiseEnJetons()+ " sur le vainqueur " +this.getVainqueurEnvisage();
      return str;
   }
   
   /**
   * Main function
   */
   public static void main(String[] args){
   // Test constructor
      try {
         DateFrancaise dateCloture = new DateFrancaise(1,11,2018);
         Competition c = new Competition("Football",dateCloture);
         Joueur j = new Joueur("NGUYEN", "Van-Khoa", "nvkvn");
         String[] teams = {"Barcelona", "RealMarid"};
         c.ajouterCompetiteur(teams);
         
         try{
            Pari p = new Pari(null,c,10,"Barcelona");
            System.out.println("Creer un pari invalid (joueur == null) n'a pas leve JoueurException");
         }
         catch(JoueurException e){}
         catch(Exception e){
            System.out.println("Creer un pari invalid (joueur == null) n'a pas leve JoueurException mais leve: "+e.getClass().getName());}
            
        try{
            Pari p = new Pari(j,null,10,"Barcelona");
            System.out.println("Creer un pari invalid (competition == null) n'a pas leve CompetitionException");
         }
         catch(CompetitionException e){}
         catch(Exception e){
            System.out.println("Creer un pari invalid (competition == null) n'a pas leve CompetitionException mais leve: "+e.getClass().getName());}
            
       try{
            Pari p = new Pari(j,c,-1,"Barcelona");
            System.out.println("Creer un pari invalid (sommeEnJetons == null) n'a pas leve MetierException");
         }
         catch(MetierException e){}
         catch(Exception e){
            System.out.println("Creer un pari invalid (sommeEnJetons == null) n'a pas leve MetierException mais leve: "+e.getClass().getName());}
            
       try{
            Pari p = new Pari(j,c,10,null);
            System.out.println("Creer un pari invalid (vainqueurEnvisage == null) n'a pas leve CompetitionException");
         }
         catch(CompetitionException e){}
         catch(Exception e){
            System.out.println("Creer un pari invalid (vainqueurEnvisage == null) n'a pas leve CompetitionException mais leve: "+e.getClass().getName());}
      
      try{
            Pari p = new Pari(j,c,10,"Barcelona");
         }
            catch(Exception e){
            System.out.println("Creer un pari valid  a leve Exception: "+e.getClass().getName());}
            
      // Test toString()
      
      Pari p = new Pari(j,c,10,"Barcelona");
      System.out.println("Test toString(): "+p.toString());

      }
      catch(Exception e){
         System.out.println(" Exception non souhait: " +e.getClass().getName());
      }
      
   }
    

}