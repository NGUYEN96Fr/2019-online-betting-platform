package siteParis;

import java.util.LinkedList;

/**
 *La classe contient tous les methodes qui sont utilise pour gerer tous les activites du site de pari
 *@author NGUYEN Van-Khoa, AUBIGNAT Benjamin, LIU Qingmin
 *
 */
public class SiteDeParisMetier{
   
   private String passwordGestionnaire; // Password de Gestionnaire pour acceder le SiteDeParis
   private LinkedList<Joueur> joueurs; // Une liste de joueurs en cours
   private LinkedList<DateFrancaise> date;
   private LinkedList<Competition> competitions; // Une liste de competition en cours
   private LinkedList<Pari> paris;
   
   /**
   * Constucteur de la classe SiteDeParisMetier
   *@para   passwordGestionnaire
   */
   
   public SiteDeParisMetier(String passwordGestionnaire) throws MetierException{
   
      // valider passwordGestionnaire
      
      //validitePasswordGestionnaire(passwordGestionnaire);
      if(passwordGestionnaire == null) throw new MetierException();
      if(passwordGestionnaire.length() < 8) throw new MetierException();
      for(int i= 0; i< passwordGestionnaire.length(); i++){
         char c = passwordGestionnaire.charAt(i);
         if( !Character.isLetterOrDigit(c) )  throw new MetierException();
      }
      
      
      this.passwordGestionnaire = passwordGestionnaire;
      this.joueurs = new LinkedList<Joueur>();
      this.date = new LinkedList<DateFrancaise>();
      this.competitions = new LinkedList<Competition>();
      this.paris = new LinkedList<Pari>();
   }
   
   // les methodes du gestionnaire
   
   /**
   * Rendre une liste des competiteurs en cours
   * @para  competition
   * @throw CompetitionException si le nom de competition est invalide
   * @throw CompetitionInexistanteException si il n'y pas de competition de meme nom
   */
   
   public LinkedList<String> consulterCompetiteurs(String competition) throws CompetitionException, CompetitionInexistanteException{
         verifierCompetition(competition);
         Competition c = verifierCompetitionExistante(competition);
         
         LinkedList <String> competiteurs = new LinkedList<String>();
         
         for (Competiteur ce: c.getCompetiteur()){
            competiteurs.add(ce.getNom());
         }
         //System.out.println("Liste de competiteurs: "+ competiteurs);
         return competiteurs;   
   }
   
   /**
   * Rendre une liste des competitions en cours incluant les informations suivants:
   * - le nom de la competition
   * - la date cloture de la competition
   */
   
   public LinkedList<LinkedList<String>> consulterCompetitions(){         
         
         LinkedList <LinkedList<String>> listeDeCompetition = new LinkedList<LinkedList<String>>();
         
         for (Competition c: this.competitions){
            listeDeCompetition.add(c.toList());
         }
         
         return listeDeCompetition;   
   }
   
    /**
   * Rendre une liste des joueurs en cours
   * @para  passwordGestionnaire
   * @throw MetiterException si le passwordGestionnaire est invalid ou incorrect
   * @Rendre les informations suivants:
   * - nom
   * - prenom
   * - pseudo
   * - le somme dans le compte disponible
   * - le somme mis sur les competitions
   */
   
   public LinkedList<LinkedList<String>> consulterJoueurs(String passwordGestionnaire) throws MetierException{
         
         // verifier passwordGestionnaire
         validitePasswordGestionnaire(passwordGestionnaire);
         
         LinkedList<LinkedList<String>> listeDeJoueur = new LinkedList<LinkedList<String>>();
         
         for (Joueur j: this.joueurs){
            listeDeJoueur.add(j.toList());
         }
         
         return listeDeJoueur;   
   }

   
   /*
   * Valider le passwordGestionnaire
   * @Para    passwordGestionnaire
   * @Throw    MetierException si:
   * - passwordGestionnaire est null
   * - le nombre des caracteres est inferieur a 8
   * - les lettres et les chiffres sont les caracteres autorises
   */
   protected void validitePasswordGestionnaire(String passwordGestionnaire) throws MetierException{   
      if(passwordGestionnaire == null) throw new MetierException();
      if(passwordGestionnaire.length() < 8) throw new MetierException();
      for(int i= 0; i< passwordGestionnaire.length(); i++){
         char c = passwordGestionnaire.charAt(i);
         if( !Character.isLetterOrDigit(c) )  throw new MetierException();
      }
     if(!this.passwordGestionnaire.equals(passwordGestionnaire)) throw new MetierException();   
   }
   
   /**
   * Verifier la competition
   * @para nomCompetition
   * @throw    CompetitionException si:
   * - nomCompetition est null
   * - le nombre des caracteres est inferieur a 4
   * - lettres, chiffres, points, traits d'union et souligne sont les seuls caracteres autorisee
   */
   
   public void verifierCompetition(String nomCompetition) throws CompetitionException{   
      if(nomCompetition == null) throw new CompetitionException();
      if(nomCompetition.length() < 4) throw new CompetitionException();
      for(int i= 0; i< nomCompetition.length(); i++){
         char c = nomCompetition.charAt(i);
         if( !Character.isLetterOrDigit(c) && c!='.' && c!= '-' && c!= '_' )  throw new CompetitionException();
      }   
   }
   
   /**
   * Crediter le compte en jetons d'un joueur du site de pari
   * @para     passwordGestionnaire
   * @para     nom      le nom du joueur
   * @para     prenom   le prenom du joueur
   * @para     pseudo   le pseudo du joueur
   * @para     sommeEnJetons
   * @throw    MetierException si le passwordGestionnaire est incorrect ou invalide
   * @throw    JoueurException si le some en jetons est negative. le prenom,nom, pseudo sont invalides
   * @throw    JoueurInexistantException si il n'y a pas  de joueur avec les meme nom, prenom et pseudo
   */
   
   public void crediterJoueur(String nom, String prenom, String pseudo, long sommeEnJetons, String passwordGestionnaire) throws MetierException, JoueurException, JoueurInexistantException{
         // verifier le passwordGestionnaire
         validitePasswordGestionnaire(passwordGestionnaire);
         verifierSommeEnJetonsPositive(sommeEnJetons);
         verifierJoueur(nom, prenom, pseudo);
         Joueur j = verifierJoueurExistant(nom, prenom, pseudo);
         j.crediterUnJoueur(sommeEnJetons);
        // System.out.println("le solde en jetons apres de crediter: "+j.getPseudo()+": "+j.getSolde());    
   }
   
   /**
   * Debiter le compte en jetons d'un joueur du site de pari
   * @para     passwordGestionnaire
   * @para     nom      le nom du joueur
   * @para     prenom   le prenom du joueur
   * @para     pseudo   le pseudo du joueur
   * @para     sommeEnJetons
   * @throw    MetierException si le passwordGestionnaire est incorrect ou invalide
   * @throw    JoueurException si le some en jetons est negative. le prenom,nom, pseudo sont invalides, le compte est insuffisant
   * @throw    JoueurInexistantException si il n'y a pas  de joueur avec les meme nom, prenom et pseudo
   */
   
   public void debiterJoueur(String nom, String prenom, String pseudo, long sommeEnJetons, String passwordGestionnaire) throws MetierException, JoueurException, JoueurInexistantException{
         // verifier le passwordGestionnaire
         validitePasswordGestionnaire(passwordGestionnaire);
         verifierSommeEnJetonsPositive(sommeEnJetons);
         verifierJoueur(nom, prenom, pseudo);
         Joueur j = verifierJoueurExistant(nom, prenom, pseudo);
         verifierCompteSuffisant(j,sommeEnJetons);
         j.debiterUnJoueur(sommeEnJetons);    
         //System.out.println("solde en jetons de joueur apres de debiter: "+j.getPseudo()+": "+j.getSolde());
   }
   
   /**
   * Inscrire un joueur 
   * @para     nom
   * @para     prenom
   * @para     pseudo
   * @para     passwordGestionnaire
   * @throw    MetierException si le passwordGestionnaire est invalide ou incorrect
   * @throw    JoueurException si le nom, prenom, peseudo sont invalide
   * @throw    JoueurExistantException si il exist un joueur avec les memes nom, prenom, pseudo
   * @return   le mot de passe du joueur
   */
   
   public String inscrireJoueur(String nom, String prenom, String pseudo, String passwordGestionnaire) throws MetierException, JoueurException, JoueurExistantException{
         
         // verifier password gestionnaire
         validitePasswordGestionnaire(passwordGestionnaire);
         
         // verifier le nom, prenom, pseudo
         verifierJoueur(nom, prenom, pseudo);
         
         // verifier joueur est existant ou pas
         verifierJoueurInexistant(nom, prenom, pseudo);
         
         //Inscrire joueur
         Joueur j = new Joueur(nom, prenom, pseudo);
         this.joueurs.add(j); 
         return j.getPassword();
   }
   
    /**
   * Desinscrire un joueur 
   * @para     nom
   * @para     prenom
   * @para     pseudo
   * @para     passwordGestionnaire
   * @throw    MetierException si le passwordGestionnaire est invalide ou incorrect
   * @throw    JoueurException si le nom, prenom, peseudo sont invalide
   * @throw    JoueurInexistantException si il n'exist pas un joueur avec les memes nom, prenom, pseudo
   * @return   le nombre des jetons disponibles dans le compte du joeur
   */
   
   public long desinscrireJoueur(String nom, String prenom, String pseudo, String passwordGestionnaire) throws MetierException, JoueurException, JoueurInexistantException{
         
         // verifier password gestionnaire
         validitePasswordGestionnaire(passwordGestionnaire);
         
         // verifier le nom, prenom, pseudo
         verifierJoueur(nom, prenom, pseudo);
         
         // verifier joueur est existant ou pas
         Joueur j = verifierJoueurExistant(nom, prenom, pseudo);
         
         // verifier le joueur n'est pas de pari en cours
         verifierJoueurPasEnCours(j);         
         long solde = j.getSolde();
         this.joueurs.remove(j);
         
         return solde;
         
   }
   
   /**
   * ajouter une competition
   * @para     nom de competition
   * @para     date cloture
   * @para     competiteurs
   * @para     passwordGestionnaire
   * @throw    MetierException si le password gestionnaire est incorrect ou invalide, si les competiteurs n'est pas initialise
   * @throw    CompetitionException si la competition, les competiteurs sont invalides, il y a moins deux competitueurs,
   * si un des competiteurs n'est pas instancie, si deux competiteur ont le meme nom, si la date cloture n'est pas instanciee ou est depasse
   * @throw    CompetitionExistanteException si il exist une comepetition avec le meme nom
   */
   
   public void ajouterCompetition(String competition, DateFrancaise dateCloture, String[] competiteurs,  String passwordGestionnaire) throws MetierException, CompetitionException, CompetitionExistanteException{
      
      // verifier password gestionnaire
      
      validitePasswordGestionnaire(passwordGestionnaire);
      
      // verifier competition
      verifierCompetition(competition);
      
      // verifier competiteurs
      verifierCompetiteurs(competiteurs);
      
      // verifier date cloture
      verifierDate(dateCloture);
      
      // verifier competition n'exist pas
      verifierCompetitionInexistante(competition);
      
      // Creer la competition
      Competition c = new Competition(competition, dateCloture);
      
      // ajouter les competiteurs dans la competition
      c.ajouterCompetiteur(competiteurs);
      
      //ajouter la competition dans le liste des competitions en cours
      this.competitions.add(c);
      
      
   }
   
   /**
   * miserVainqueur. C'est-a-dire parier sur une competition en choissiant un Vainqueur
   * @para  pseudo
   * @para  passwordJoueur
   * @para  miseEnJetons
   * @para  competition
   * @para  vainqueurEnvisage
   * @throw MetierException si la somme en jetons est negative
   * @throw JoueurInexistanteException si il n'y a pas de joeur avec les memes pseudos et password
   * @throw CompetitionException si competition ou vainqueurEnvisage sont invalides, si il n'existe pas de competiteur
   * de nom vainqueurEnvisage dans la competition, si la competition n'est plus ouverte (la date de cloture est dans le passe) 
   * @throw JoueurException si pseudo ou password sont invalides, si le compteEnJetons du joueur est insuffisant
   * @throw CompetitionInexistanteException si il n'exist pas competition avec mem nom
   */
   
   public void miserVainqueur(String pseudo, String passwordJoueur, long miseEnJetons ,String competition, String vainqueurEnvisage) throws MetierException, CompetitionInexistanteException,JoueurInexistantException, CompetitionException, JoueurException{
         
         // verifier la validite du password et pseudo du joeur
         validerPasswordJoueur(pseudo,passwordJoueur);
         
         // verifier la competition est valide
         verifierCompetition(competition);
         
         // verifier la competition est existante
         Competition ce = verifierCompetitionExistante(competition);
         
         // verifier la date cloture n'est pas depasse
         verifierDate(ce.getDate());
         
         // verifier le vainqueur en visage exist dans la competition
         verifierCompetiteur(vainqueurEnvisage, ce);
         
         Joueur j1 = null;
         
         for(Joueur j: this.joueurs){
            if(j.getPseudo().equals(pseudo))  j1=j;}
         
         // verifier la somme en jetons n'est pas negative
         verifierSommeEnJetonsPositive(miseEnJetons);
         
         // verifier le compte du joueur est suffisant
         verifierCompteSuffisant(j1,miseEnJetons);
         
         Pari p = j1.miserVainqueur(ce, vainqueurEnvisage, miseEnJetons);
         //System.out.println("Le compte apres miser sur Vainqueur "+j1.getPseudo()+": "+j1.getSolde());
         // ajouter le nouveau pari dans le liste des paris dans la competition
         ce.addPari(p);
         
         // ajouter le nouveau pari dans le liste des paris en cours dans le site des paris
         this.paris.add(p); 
   }
   
   
   /**
   * Solder une competition avec vainqueur
   * Chaque joueur ayant mise sur cette competition en choisissant ce competiteur est credite d'un nombre de jetons egal a:
   * (le montant de sa mise * la somme des jetons mises pour cette competition)/ la somme des jetons mise sur ce competiteur
   * si auccun joueur n'a trouve le bon competiteur, des jetons sont crediters aux joueurs ayant mise sur cette competition
   * (confortement au montant de leurs mises). La competition est supprimee si il ne rest plas de mises suite a ce solde
   * @para  competition    le nom de la competition
   * @para  vainqueur      le nom du vainqueur de la competition
   * @para  passwordGestionnaire    le password du gestionnaire du site
   * @throw MetierException si le passwordGetionnaire est invalide ou incorrect
   * @throw CompetitionInexistanteException  si il n'exist pas de competition de meme nom
   * @throw CompetitionException si le nom de la competition ou du vainqueur est invalide, si il n'existe pas de competiteur du nom vainqueur
   * dans la competition, la date cloture de la competition est dans le futur 
   */
   
   public void solderVainqueur(String competition, String vainqueur, String passwordGestionnaire) throws MetierException, CompetitionInexistanteException, CompetitionException{
      // verifier password gestionnaire
      validitePasswordGestionnaire(passwordGestionnaire);
      
      //verifier le nom de competition est valide
      verifierCompetition(competition);            
      // Verifier la competition existe dans le liste des competitions en cours
      Competition c = verifierCompetitionExistante(competition);
      
      //Verifier la date est depasse
      verifierDateEstPasse(c.getDate());
      
      // Verifier le vainqueur du nom du competiteur existe dans la competition
      verifierCompetiteur(vainqueur, c);
      c.solderUneCompetition(vainqueur);
      
      // enlever la competition de la liste des competitions en cours
      this.competitions.remove(c);
      
   }
   
   /**
   * Verifier le competiteur existe dans la competition
   * @para     competiteur
   * @para     competition
   * @throw    CompetitionException si le nom du competiteur est invalide ou il n'exist pas dans la competition
   */
   
   public void verifierCompetiteur(String competiteur, Competition competition) throws CompetitionException{
         
         // verifier le nom du competieur est valide
         if (competiteur == null) throw new CompetitionException();
               if (competiteur.length()<4) throw new CompetitionException();
               for (int i =0; i < competiteur.length(); i++){
                  char c = competiteur.charAt(i);
                  if (!Character.isLetter(c) && c != '-' && c != '_') throw new CompetitionException();
               }
        
        // verifier le competiteur existe dans la competition
        boolean cherche = false;
        for (Competiteur c: competition.getCompetiteur()){
            if (c.getNom().equals(competiteur)) cherche = true;
        }
        
        if (!cherche) throw new CompetitionException();
    
   }
   
   /**
   * Verifier le password du joueur associe par le pseudo
   * @para  pseudo
   * @para  passwordJoueur
   * @throw JoueurException si le pseudo et le password sont invalide
   * @throw JoueurInexistantException si le pseudo et le password n'exist pas
   */
   
   public void validerPasswordJoueur(String pseudo, String passwordJoueur) throws JoueurException, JoueurInexistantException{
      
               // verifier le pseudo et le password sont valide           
              
               if (pseudo == null) throw new JoueurException();
               if (pseudo.length()<4) throw new JoueurException();
               for (int i =0; i < pseudo.length(); i++){
                  char c = pseudo.charAt(i);
                  if (!Character.isLetterOrDigit(c)) throw new JoueurException();
               }
               
               if (passwordJoueur == null) throw new JoueurException();
               if (passwordJoueur.length()<8) throw new JoueurException();
               for (int i =0; i < passwordJoueur.length(); i++){
                  char c = passwordJoueur.charAt(i);
                  if (!Character.isLetterOrDigit(c)) throw new JoueurException();
               }
               
               // trouver joueur avec pseudo
               Joueur j1 = null;
               boolean cherche = false;
               for(Joueur j: joueurs){
                  if(j.getPseudo().equals(pseudo)){
                     cherche = true;
                     j1 = j;
                  }
               }
               if (!cherche) throw new JoueurInexistantException();
               
               // verifier la correspondante de le password joueur
               if (!passwordJoueur.equals(j1.getPassword())) throw new JoueurInexistantException();
               
      
   }
   
   /**
   *  Verifier date cloture est correcte
   * @para     date
   * @throw    CompetitionException si la date est depasse ou ne pas est instancie
   */
   public void verifierDate(DateFrancaise date) throws CompetitionException{
      if (date == null) throw new CompetitionException();
      if (date.estDansLePasse()) throw new CompetitionException();
   }
   
    /**
   *  Verifier date cloture est depasse
   * @para     date
   * @throw    CompetitionException si la date n'est pas depasse ou ne pas est instancie
   */
   public void verifierDateEstPasse(DateFrancaise date) throws CompetitionException{
      if (date == null) throw new CompetitionException();
      if (!date.estDansLePasse()) throw new CompetitionException();
   }

   
   /**
   * Verifier les competiteurs
   * @para     competiteurs
   * @throw    MetierException si les competiteurs n'sont pas instancie
   * @throw    MetierException si les competiteurs sont invalides, il y a moins deux competitueurs,
   * si deux competiteur ont les memes noms. 
   */
   
   public void verifierCompetiteurs(String [] competiteurs) throws MetierException,CompetitionException{
   
         // Verifier le table des competiteurs est instancie
            if (competiteurs == null) throw new MetierException();
        
         // Verifier le nombre des competiteurs sont supperieur a 2 competiteurs
            if (competiteurs.length < 2) throw new CompetitionException();
            
         // Verifier tous les competiteurs sont valides
            for (String ce: competiteurs){            
               if (ce == null) throw new CompetitionException();
               if (ce.length()<4) throw new CompetitionException();
               for (int i =0; i < ce.length(); i++){
                  char c = ce.charAt(i);
                  if (!Character.isLetter(c) && c != '-' && c != '_') throw new CompetitionException();
               }
            }
            
         // Verifier les competiteurs n'a pas les memes noms
         boolean cherche = false;
         
         for (int i =0; i< competiteurs.length; i++){
            for(int j = i +1; j <competiteurs.length; j++){
               if(competiteurs[i].equals(competiteurs[j])) throw new CompetitionException(); 
            }
         }
      
   }
   
    
   /**
   * Verifier le somme en jetons est positive
   * @para     sommeEnJetons
   * @throw    MetiterException si somme en jetons est negative
   */
   
   public void verifierSommeEnJetonsPositive(long sommeEnJetons) throws MetierException{
      if (sommeEnJetons < 0) throw new MetierException();
   }
   
   /**
   * Verifier le somme en jetons dans  est suffisant
   * @para     joueur
   * @para     sommeEnJetons
   * @throw    JoueurException si le somme en jetons dans le compte est inferieur que le somme a mise.     
   */
   
   public void verifierCompteSuffisant(Joueur joueur, long miseEnJetons) throws JoueurException{
      //System.out.println("solde en jetons de joueur avant de debiter: "+joueur.getPseudo()+": "+joueur.getSolde());
      if ( joueur.getSolde() < miseEnJetons ) throw new JoueurException();
   }
   
   /**
   * Verifier le nom, le prenom, le pseudo sont valides
   * @para     nom
   * @para     prenom
   * @para     pseudo
   * @throw    JouerException si le nom, le prenom, le pseudo sont invalides
   */
   
   public void verifierJoueur(String nom, String prenom, String pseudo) throws JoueurException{
      
      // Verifier le nom du joueur
      if( nom == null ) throw new JoueurException();
      if( nom.length() < 1 ) throw new JoueurException();
      for(int i =0; i<nom.length(); i++){
         char c = nom.charAt(i);
            if(!Character.isLetter(c) && c!= '-') throw new JoueurException();
      }
      
      // Verifier le prenom du joueur
      if( prenom == null ) throw new JoueurException();
      if( prenom.length() < 1 ) throw new JoueurException();
      for(int i =0; i<prenom.length(); i++){
         char c = prenom.charAt(i);
            if(!Character.isLetter(c) && c!= '-') throw new JoueurException();
      }
      
      // Verifier le pseudo du joueur
      if( pseudo == null ) throw new JoueurException();
      if( pseudo.length() < 4 ) throw new JoueurException();
      for(int i =0; i<pseudo.length(); i++){
         char c = pseudo.charAt(i);
            if(!Character.isLetterOrDigit(c)) throw new JoueurException();
      }
     
   }
   
   /**
   * Verifier le joueur est existant
   * @para     nom
   * @para     prenom
   * @para     pseudo
   * @throw    JoueurInexistanteException si il n'exist pas le joueur avec les memes nom, prenom, pseudo
   */
   
   public Joueur verifierJoueurExistant(String nom, String prenom, String pseudo) throws JoueurInexistantException{
   
      boolean cherche = false;
      Joueur j = null;
      if (this.joueurs != null){
      for(Joueur j1 : this.joueurs){
        // if(j1.getNom().equals(nom) && j1.getPrenom().equals(prenom)){
         if(j1.getNom().equals(nom) && j1.getPrenom().equals(prenom) && j1.getPseudo().equals(pseudo)){
            cherche = true;
            j = j1;
         }
      }
      }
      if (cherche == false) throw new JoueurInexistantException();
      return j;
   }
   
   /**
   * Verifier le joueur est inexistant
   * @para     nom
   * @para     prenom
   * @para     pseudo
   * @throw    JoueurExistanteException si il exist le joueur avec les memes nom, prenom, pseudo
   */
   
   public void verifierJoueurInexistant(String nom, String prenom, String pseudo) throws JoueurExistantException{
   
      boolean cherche = false;
      
      for(Joueur j1 : joueurs){
         if(j1.getNom().equals(nom) && j1.getPrenom().equals(prenom)){
            cherche = true;
         }
      }
      
      for(Joueur j1 : joueurs){
            if(pseudo.equals(j1.getPseudo())){
            cherche = true;
         }
      }
      
      if (cherche) throw new JoueurExistantException();
   }
   
   /**
   * Verifier la competition existe ou n'existe pas dans la liste des competitions en cours
   * @para nomCompetition
   * @rendre la competition si il exist et null si non.
   */
   
   public Competition verifierCompetitionExouInex(String nomCompetition){
      
      Competition c  = null;
      for(Competition c1: this.competitions){
         if(c1.getNom().equals(nomCompetition)){
            c = c1;}
      }
      return c;
   }


   /**
   * Verifier l'existante de la competition
   * @para nomCompetition
   * @throw CompetitionInexistanteException si il n'y a pas de competition de meme nom
   */
   
   public Competition verifierCompetitionExistante(String nomCompetition) throws CompetitionInexistanteException{
   
      Competition c  = verifierCompetitionExouInex(nomCompetition);
      if( c == null) throw new CompetitionInexistanteException();
      return c;
   }
   
   
   /**
   * Verifier l'inexistante de la competition
   * @para nomCompetition
   * @throw CompetitionExistanteException si il n'y a pas de competition de meme nom
   */
   
   public void verifierCompetitionInexistante(String nomCompetition) throws CompetitionExistanteException{
   
      Competition c  = verifierCompetitionExouInex(nomCompetition);
      if( c != null) throw new CompetitionExistanteException();
   }
   
   /**
   * Verifier joueur n'est pas de pari en cours
   * @para  joueur
   * @throw    JoueurException si le joueur est un moins d'un pari en cours
   */
   
   public void verifierJoueurPasEnCours(Joueur joueur) throws JoueurException{
      
      boolean cherche = false;
      
      if(this.paris != null){
      for (Pari p: this.paris){
         if(p.getJoueur().equals(joueur)){
            cherche = true;
         }
      }
      }
      
      if (cherche==true) throw new JoueurException();
   }
   
}
