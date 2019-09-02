package siteParis;

import siteParis.Compétition;
import java.util.LinkedList;


public class Compétiteur {

	/**
	 * @uml.property  name="deCompétition"
	 */
	private Compétition deCompétition;
   
   /**
	 * @uml.property  name="nom"
	 */
	private String nom;
   
   private LinkedList<Compétition> compétition;
   

   // the Compétiteur constructor 
   public Compétiteur(String nom) throws CompetitionException{
      if (nom==null) throw new CompetitionException();
      if (nom.length()< 1) throw CompetitionException();
      for (int i=0; i< nom.length();i++){
         char c = nom.charAt(i);
         if (!Character.isLetter(c) && c!='-') throw new CompetitionException;
      }
      
      this.nom = nom;
      this.compétition = new LinkedList<Compétition>;
   }
	/**
	 * Getter of the property <tt>deCompétition</tt>
	 * @return  Returns the deCompétition.
	 * @uml.property  name="deCompétition"
	 */
    

	/**
	 * Setter of the property <tt>deCompétition</tt>
	 * @param deCompétition  The deCompétition to set.
	 * @uml.property  name="deCompétition"
	 */
	public void setDeCompétition(Compétition deCompétition) throws CompetitionException{
      for (int i=0; i< deCompétition.length();i++){
         char c = deCompétition.charAt(i);
         if (!Character.isLetter(c) && c!='-') throw new CompetitionException;
      }
		this.deCompétition = deCompétition;
      compétition.add(deCompétition);
	}
		
		/**
		 */
		public void getNom(){
		}
			
			/**
			 */
			public void getCompetition(){
			}

	/**
	 * Getter of the property <tt>nom</tt>
	 * @return  Returns the nom.
	 * @uml.property  name="nom"
	 */


	/**
	 * Setter of the property <tt>nom</tt>
	 * @param nom  The nom to set.
	 * @uml.property  name="nom"
	 */
	//public void setNom(String nom) {
	//	this.nom = nom;
	//}

				

	/**
	 * @uml.property  name="compétition"
	 * @uml.associationEnd  multiplicity="(0 -1)" inverse="compétiteur:siteParis.Compétition"
	 */
	//private LinkedList<Compétition> compétitions;
   




