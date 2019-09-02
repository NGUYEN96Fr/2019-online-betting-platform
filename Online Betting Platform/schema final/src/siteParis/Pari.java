package siteParis;

import Competition;
import Joueur;


public class Pari {

	/**
	 * @uml.property  name="parJoueur"
	 */
	private Joueur parJoueur;

	/**
	 * Getter of the property <tt>parJoueur</tt>
	 * @return  Returns the parJoueur.
	 * @uml.property  name="parJoueur"
	 */
	public Joueur getParJoueur() {
		return parJoueur;
	}

	/**
	 * @uml.property  name="appartientCompetition"
	 */
	private Competition appartientCompetition;

	/**
	 * Getter of the property <tt>appartientCompetition</tt>
	 * @return  Returns the appartientCompetition.
	 * @uml.property  name="appartientCompetition"
	 */
	public Competition getAppartientCompetition() {
		return appartientCompetition;
	}

	/**
	 * Setter of the property <tt>appartientCompetition</tt>
	 * @param appartientCompetition  The appartientCompetition to set.
	 * @uml.property  name="appartientCompetition"
	 */
	public void setAppartientCompetition(Competition appartientCompetition) {
		this.appartientCompetition = appartientCompetition;
	}

	/**
	 * Setter of the property <tt>parJoueur</tt>
	 * @param parJoueur  The parJoueur to set.
	 * @uml.property  name="parJoueur"
	 */
	public void setParJoueur(Joueur parJoueur) {
		this.parJoueur = parJoueur;
	}

	/**
	 * @uml.property  name="miseEnJetons"
	 */
	private long miseEnJetons;

	/**
	 * Setter of the property <tt>miseEnJetons</tt>
	 * @param miseEnJetons  The miseEnJetons to set.
	 * @uml.property  name="miseEnJetons"
	 */
	public void setMiseEnJetons(long miseEnJetons) {
		this.miseEnJetons = miseEnJetons;
	}

	/**
		 */
		public void Pari(){
		}

			
			/**
			 */
			public void getCompetition(){
			}

			/**
			 * @uml.property  name="vainqueurEnvisage"
			 */
			private String vainqueurEnvisage;

			/**
			 * Setter of the property <tt>vainqueurEnvisage</tt>
			 * @param vainqueurEnvisage  The vainqueurEnvisage to set.
			 * @uml.property  name="vainqueurEnvisage"
			 */
			public void setVainqueurEnvisage(String vainqueurEnvisage) {
				this.vainqueurEnvisage = vainqueurEnvisage;
			}

				
				/**
				 */
				public void getVainqueurEnvisage(){
				}

					
					/**
					 */
					public void getMiseEnJetons(){
					}

						
						/**
						 */
						public void getJoueur(){
						}

							
							/**
							 */
							public void toString(){
							}

}
