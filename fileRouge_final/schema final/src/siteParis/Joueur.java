package siteParis;

import Pari;
import java.util.LinkedList;
import java.util.Collection;


public class Joueur {

	/**
	 * @uml.property  name="faitParis"
	 */
	private LinkedList faitParis;

	/**
	 * Getter of the property <tt>faitParis</tt>
	 * @return  Returns the faitParis.
	 * @uml.property  name="faitParis"
	 */
	public LinkedList getFaitParis() {
		return faitParis;
	}

	/**
	 * Setter of the property <tt>faitParis</tt>
	 * @param faitParis  The faitParis to set.
	 * @uml.property  name="faitParis"
	 */
	public void setFaitParis(LinkedList faitParis) {
		this.faitParis = faitParis;
	}

	/**
	 * @uml.property  name="nom"
	 */
	private String nom;

	/**
	 * Setter of the property <tt>nom</tt>
	 * @param nom  The nom to set.
	 * @uml.property  name="nom"
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}

	/**
	 * @uml.property  name="miseEnJetons"
	 */
	private long miseEnJetons;

	/**
	 * Getter of the property <tt>miseEnJetons</tt>
	 * @return  Returns the miseEnJetons.
	 * @uml.property  name="miseEnJetons"
	 */
	public long getMiseEnJetons() {
		return miseEnJetons;
	}

	/**
	 * Setter of the property <tt>miseEnJetons</tt>
	 * @param miseEnJetons  The miseEnJetons to set.
	 * @uml.property  name="miseEnJetons"
	 */
	public void setMiseEnJetons(long miseEnJetons) {
		this.miseEnJetons = miseEnJetons;
	}

	/**
	 * @uml.property  name="prenom"
	 */
	private String prenom;

	/**
	 * Setter of the property <tt>prenom</tt>
	 * @param prenom  The prenom to set.
	 * @uml.property  name="prenom"
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	/**
	 * @uml.property  name="pseudo"
	 */
	private String pseudo;

	/**
	 * Setter of the property <tt>pseudo</tt>
	 * @param pseudo  The pseudo to set.
	 * @uml.property  name="pseudo"
	 */
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	/**
	 * @uml.property  name="password"
	 */
	private String password;

	/**
	 * Setter of the property <tt>password</tt>
	 * @param password  The password to set.
	 * @uml.property  name="password"
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @uml.property  name="soldeEnJetons"
	 */
	private String soldeEnJetons;

	/**
	 * Getter of the property <tt>soldeEnJetons</tt>
	 * @return  Returns the soldeEnJetons.
	 * @uml.property  name="soldeEnJetons"
	 */
	public String getSoldeEnJetons() {
		return soldeEnJetons;
	}

	/**
	 * Setter of the property <tt>soldeEnJetons</tt>
	 * @param soldeEnJetons  The soldeEnJetons to set.
	 * @uml.property  name="soldeEnJetons"
	 */
	public void setSoldeEnJetons(String soldeEnJetons) {
		this.soldeEnJetons = soldeEnJetons;
	}

		
		/**
		 */
		public Joueur(){
		}

			
			/**
			 */
			public void getMise(){
			}

				
				/**
				 */
				public void getSolde(){
				}

					
					/**
					 */
					public void getNom(){
					}

						
						/**
						 */
						public void getPrenom(){
						}

							
							/**
							 */
							public void getPseudo(){
							}

								
								/**
								 */
								public void getPassword(){
								}

									
									/**
									 */
									public void toList(){
									}

										
										/**
										 */
										public void getParisMise(){
										}

											
											/**
											 */
											public void addPari(){
											}

												
												/**
												 */
												public void solderVainqueur(){
												}

													
													/**
													 */
													public void diminuerVainqueur(){
													}

														
														/**
														 */
														public void miserVainqueur(){
														}

															
															/**
															 */
															public void crediterunJoueur(){
															}

																
																/**
																 */
																public void crediterUnJoueur(){
																}

																	
																	/**
																	 */
																	public void debiterUnJoueur(){
																	}

																	/** 
																	 * @uml.property name="pari"
																	 * @uml.associationEnd multiplicity="(0 -1)" inverse="joueur1:siteParis.Pari"
																	 * @uml.association name="avoir"
																	 */
																	private Collection pari;

																	/** 
																	 * Getter of the property <tt>pari</tt>
																	 * @return  Returns the pari.
																	 * @uml.property  name="pari"
																	 */
																	public Collection getPari() {
																		return pari;
																	}

																	/** 
																	 * Setter of the property <tt>pari</tt>
																	 * @param pari  The pari to set.
																	 * @uml.property  name="pari"
																	 */
																	public void setPari(
																			Collection pari) {
																				this.pari = pari;
																			}

}
