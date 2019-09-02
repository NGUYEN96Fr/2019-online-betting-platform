package siteParis;


import siteParis.Pari;
import siteParis.Compétiteur;
import java.util.LinkedList;
import Competiteur;
import DateFrancaise;
import java.util.List;
import java.util.Collection;

public class Compétition {

	/**
	 * @uml.property  name="nom"
	 */
	private String nom;

	/**
	 * Getter of the property <tt>nom</tt>
	 * @return  Returns the nom.
	 * @uml.property  name="nom"
	 */
	public String getNom() {
		return nom;
	}

	/**
	 * Setter of the property <tt>nom</tt>
	 * @param nom  The nom to set.
	 * @uml.property  name="nom"
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

		
		/**
		 */
		public Compétition(String nom, LinkedList<Compétiteur> compétiteurs, DateFrancaise datecloture){
		}

			
			/**
			 */
			public LinkedList getCompétiteurs(){
				return null;
			}

				
				/**
				 */
				public DateFrancaise getDate(){
					return null;
				}

					
					/**
					 */
					public String getName(){
						return "";	
					}

						
						/**
						 */
						public LinkedList getParisdelaCompétitions(){
							return null;
						}


						/**
						 * @uml.property  name="aCompetiteur"
						 */
						private LinkedList aCompetiteur;

						/**
						 * Getter of the property <tt>aCompetiteur</tt>
						 * @return  Returns the aCompetiteur.
						 * @uml.property  name="aCompetiteur"
						 */
						public LinkedList getACompetiteur() {
							return aCompetiteur;
						}

						/**
						 * Setter of the property <tt>aCompetiteur</tt>
						 * @param aCompetiteur  The aCompetiteur to set.
						 * @uml.property  name="aCompetiteur"
						 */
						public void setACompetiteur(LinkedList aCompetiteur) {
							this.aCompetiteur = aCompetiteur;
						}


						/**
						 * @uml.property  name="aPari"
						 */
						private LinkedList aPari;

						/**
						 * Getter of the property <tt>aPari</tt>
						 * @return  Returns the aPari.
						 * @uml.property  name="aPari"
						 */
						public LinkedList getAPari() {
							return aPari;
						}

						/**
						 * Setter of the property <tt>aPari</tt>
						 * @param aPari  The aPari to set.
						 * @uml.property  name="aPari"
						 */
						public void setAPari(LinkedList aPari) {
							this.aPari = aPari;
						}


						/**
						 * @uml.property  name="dateCloture"
						 */
						private DateFrancaise dateCloture;

						/**
						 * Getter of the property <tt>dateCloture</tt>
						 * @return  Returns the dateCloture.
						 * @uml.property  name="dateCloture"
						 */
						public DateFrancaise getDateCloture() {
							return dateCloture;
						}

						/**
						 * Setter of the property <tt>dateCloture</tt>
						 * @param dateCloture  The dateCloture to set.
						 * @uml.property  name="dateCloture"
						 */
						public void setDateCloture(DateFrancaise dateCloture) {
							this.dateCloture = dateCloture;
						}

							
							/**
							 */
							public Compétition(){
							}

								
								/**
								 */
								public void addCompetiteur(){
								}

									
									/**
									 */
									public void toList(){
									}

										
										/**
										 */
										public void addPari(){
										}

											
											/**
											 */
											public void ajouterCompetiteur(){
											}

												
												/**
												 */
												public void solderUneCompetition(){
												}


												/** 
												 * @uml.property name="pari"
												 * @uml.associationEnd multiplicity="(0 -1)" ordering="true" inverse="compétition:siteParis.Pari"
												 * @uml.association name="avoir"
												 */
												private List pari;

												/** 
												 * Getter of the property <tt>pari</tt>
												 * @return  Returns the pari.
												 * @uml.property  name="pari"
												 */
												public List getPari() {
													return pari;
												}

												/** 
												 * Setter of the property <tt>pari</tt>
												 * @param pari  The pari to set.
												 * @uml.property  name="pari"
												 */
												public void setPari(List pari) {
													this.pari = pari;
												}


												/** 
												 * @uml.property name="compétiteur"
												 * @uml.associationEnd multiplicity="(2 -1)" inverse="compétition:siteParis.Compétiteur"
												 */
												private Collection compétiteur;

												/** 
												 * Getter of the property <tt>compétiteur</tt>
												 * @return  Returns the compétiteur.
												 * @uml.property  name="compétiteur"
												 */
												public Collection getCompétiteur() {
													return compétiteur;
												}

												/** 
												 * Setter of the property <tt>compétiteur</tt>
												 * @param compétiteur  The compétiteur to set.
												 * @uml.property  name="compétiteur"
												 */
												public void setCompétiteur(
														Collection compétiteur) {
															this.compétiteur = compétiteur;
														}

}
