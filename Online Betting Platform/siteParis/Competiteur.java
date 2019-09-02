package siteParis;

import java.util.LinkedList;

public class Competiteur{
   private Competition deCompetition;
   private String nom;
   
   /**
    *Constructeur de Competiteur
    *@param nom             le nom du competiteur
    *@param deCompetition   l'objet Competition où le competiteur participe      
    */
   public Competiteur(String nom, Competition deCompetition) throws CompetitionException{
      // Verifier la validite du nom du competiteur
      if (nom == null) throw new CompetitionException();
      
      if (nom.length() < 4) throw new CompetitionException();
      
      for (int i=0 ; i < nom.length() ; i++){
         char c = nom.charAt(i);
         if (!Character.isLetter(c)&&c!='-'&&c!='_') throw new CompetitionException();  
      }
      
      // Verifier la validite de l'object Competition
      if (deCompetition == null) throw new CompetitionException();
      
      this.nom = nom;
      this.deCompetition = deCompetition;
     } 
  /**
   * Get the Competition's object
   */
   public Competition getCompetition(){
      return this.deCompetition;
    }
   
  /**
   *Get the competiteur's name
   */
   public String getNom(){
      return this.nom;   
   }
  
   public static void main(String[] args){
      //Test Constructor
      try{
         // Erreur à cause des autres classes
         System.out.println("Test Constructor");
         DateFrancaise dateCloture = new DateFrancaise(12, 10, 2018, 10, 00);
         Competition c = new Competition("Football", dateCloture);
         
         try{
            Competiteur p = new Competiteur(null,c);
            System.out.println("Un competiteur avec un nom invalid (non initialise) n'a pas leve l'exception CompetitionException");
         }
         catch (CompetitionException e){} 
         // Erreur de lever la classe differencie à la classe CompetitionException
         catch (Exception e){
            System.out.println("Un competiteur avec un nom invalid (non initialise) n'a pas leve l'exception CompetitionException mais l'exception "+e.getClass().getName());
         }
         try{
            Competiteur p =new Competiteur("Liu690",c);
            System.out.println("Un competiteur avec un nom invalid (avec des numeros) n'a pas leve l'exception CompetitionException");
         }
         catch(CompetitionException e){}
         catch(Exception e){
            System.out.println("Un competiteur avec un nom invalid (avec des numeros) n'a pas leve l'exception CompetitionException mais l'exception "+e.getClass().getName());
         }
         try{
            Competiteur p =new Competiteur("Liu Qingmin",c);
            System.out.println("Un competiteur avec un nom invalid (avec des espaces) n'a pas leve l'exception CompetitionException");
         }
         catch(CompetitionException e){}
         catch(Exception e){
            System.out.println("Un competiteur avec un nom invalid (avec des espaces) n'a pas leve l'exception CompetitionException mais l'exception "+e.getClass().getName());
         }
         try{
            Competiteur p =new Competiteur("Liu*/-%",c);
            System.out.println("Un competiteur avec un nom invalid (avec des characters differencie aux lettres et '-' et '_') n'a pas leve l'exception CompetitionException");
         }
         catch(CompetitionException e){}
         catch(Exception e){
            System.out.println("Un competiteur avec un nom invalid (avec des characters differencie aux lettres et '-' et'_') n'a pas leve l'exception CompetitionException mais l'exception "+e.getClass().getName());
         }
         try{
            Competiteur p =new Competiteur("LiuQingmin",null);
            System.out.println("Un competiteur avec un nom de competition invalid (non competition) n'a pas leve l'exception CompetitionException");
         }
         catch(CompetitionException e){}
         catch(Exception e){
            System.out.println("Un competiteur avec un nom de competition invalid (non competition) n'a pas leve l'exception CompetitionException mais l'exception "+e.getClass().getName());
         }
         // Creer un competiteur avec un nom de competiteur valid et de competition valid
         try{
            Competiteur p =new Competiteur("LiuQingmin",c);
         }
         catch(Exception e){
            System.out.println("Un competiteur avec un nom de competiteur valid et de competition valid a leve l'Exception ");
            e.printStackTrace();
         }
         
   // Test getNom() et Test getCompetition()
      Competiteur p = new Competiteur("LiuQuingmin",c);
      System.out.println("Test getNom() et getCompetition()");
      System.out.println("Le competiteur: " + p.getNom() + " de competition: " +p.getCompetition().toList());
   }
   catch(Exception e){
   System.out.println("Exception à la clause des autres classes" +e.getClass().getName());
   }
   }           
}