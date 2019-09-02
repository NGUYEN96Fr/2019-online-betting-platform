package biblio;

import java.util.*; 
import java.sql.Date;
import java.util.Arrays;
import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;


/**
 * Composant logiciel assurant la gestion des emprunts d'exemplaires
 * de livre par les abonnés.
 */
public class ComposantBDEmprunt {

  /**
   * Retourne le nombre total d'emprunts en cours référencés dans la base.
   * 
   * @return le nombre d'emprunts.
   * @throws SQLException en cas d'erreur de connexion à la base.
   */
  public static int nbEmpruntsEnCours() throws SQLException {
	    int nbEmpruntsEnCour = 0;
		Statement stmt = Connexion.getConnection().createStatement();
		String sql = "select count(id_exemplaire) from emprunt where date_retour is null";
		ResultSet rset = stmt.executeQuery(sql);
		while(rset.next()){
			nbEmpruntsEnCour = rset.getInt("count");
		}
		rset.close();
	    stmt.close();
	    return nbEmpruntsEnCour;
  }

  /**
   * Retourne le nombre d'emprunts en cours pour un abonné donné connu
   * par son identifiant.
   * 
   * @return le nombre d'emprunts.
   * @throws SQLException en cas d'erreur de connexion à la base.
   */
  public static int nbEmpruntsEnCours(int idAbonne) throws SQLException {
	    int nbEmpruntsEnCour = 0;
		Statement stmt = Connexion.getConnection().createStatement();
		String sql = "select count(id_exemplaire) from emprunt where id_abonne = "+ idAbonne+" and date_retour is null";
		ResultSet rset = stmt.executeQuery(sql);
		while(rset.next()){
			nbEmpruntsEnCour = rset.getInt("count");
		}
		rset.close();
	    stmt.close();
	    return nbEmpruntsEnCour;
  }

  
  /**
   * Récupération de la liste complète des emprunts en cours.
   * 
   * @return un <code>ArrayList<String[]></code>. Chaque tableau de chaînes
   * de caractères contenu correspond à un emprunt en cours.<br/>
   * Il doit contenir 8 éléments (dans cet ordre) :
   * <ul>
   *   <li>0 : id de l'exemplaire</li>
   *   <li>1 : id du livre correspondant</li>
   *   <li>2 : titre du livre</li>
   *   <li>3 : son auteur</li>
   *   <li>4 : id de l'abonné</li>
   *   <li>5 : nom de l'abonné</li>
   *   <li>6 : son prénom</li>
   *   <li>7 : la date de l'emprunt</li>
   * </ul>
   * @throws SQLException en cas d'erreur de connexion à la base.
   */
  public static ArrayList<String[]> listeEmpruntsEnCours() throws SQLException {
    ArrayList<String[]> emprunts = new ArrayList<String[]>();
    Statement stmt = Connexion.getConnection().createStatement();
    String sql = "select e.id_exemplaire,ex.id_livre,l.titre,l.auteur,e.id_abonne,a.nom,a.prenom,e.date_emprunt from emprunt as e"+ 
    				" inner join exemplaire as ex on e.id_exemplaire = ex.id"+
    				" inner join livre as l on ex.id_livre = l.id"+
    				" inner join abonne as a on e.id_abonne = a.id and date_retour is null;";
    ResultSet rset = stmt.executeQuery(sql);

    while (rset.next()) {
      String[] emprunt = new String[8];
      emprunt[0] = rset.getString("id_exemplaire");
      emprunt[1] = rset.getString("id_livre");
      emprunt[2] = rset.getString("titre");
      emprunt[3] = rset.getString("auteur");
      emprunt[4] = rset.getString("id_abonne");
      emprunt[5] = rset.getString("nom");
      emprunt[6] = rset.getString("prenom");
      emprunt[7] = rset.getString("date_emprunt");
      
      emprunts.add(emprunt);
    }
    rset.close();
    stmt.close();
    return emprunts;
  }

  /**
   * Récupération de la liste des emprunts en cours pour un abonné donné.
   * 
   * @return un <code>ArrayList<String[]></code>. Chaque tableau de chaînes
   * de caractères contenu correspond à un emprunt en cours pour l'abonné.<br/>
   * Il doit contenir 5 éléments (dans cet ordre) :
   * <ul>
   *   <li>0 : id de l'exemplaire</li>
   *   <li>1 : id du livre correspondant</li>
   *   <li>2 : titre du livre</li>
   *   <li>3 : son auteur</li>
   *   <li>4 : la date de l'emprunt</li>
   * </ul>
   * @throws SQLException en cas d'erreur de connexion à la base.
   */
  public static ArrayList<String[]> listeEmpruntsEnCours(int idAbonne) throws SQLException {
	    ArrayList<String[]> emprunts = new ArrayList<String[]>();
	    Statement stmt = Connexion.getConnection().createStatement();
	    String sql = "select e.id_exemplaire,ex.id_livre,l.titre,l.auteur,e.date_emprunt from emprunt as e"+ 
	    				" inner join exemplaire as ex on e.id_exemplaire = ex.id"+
	    				" inner join livre as l on ex.id_livre = l.id"+
	    				" where e.id_abonne ="+idAbonne+" and e.date_retour is null";
	    ResultSet rset = stmt.executeQuery(sql);

	    while (rset.next()) {
	      String[] emprunt = new String[5];
	      emprunt[0] = rset.getString("id_exemplaire");
	      emprunt[1] = rset.getString("id_livre");
	      emprunt[2] = rset.getString("titre");
	      emprunt[3] = rset.getString("auteur");
	      emprunt[4] = rset.getString("date_emprunt");
	      
	      emprunts.add(emprunt);
	    }
	    rset.close();
	    stmt.close();
	    return emprunts;
  }

  /**
   * Récupération de la liste complète des emprunts passés.
   * 
   * @return un <code>ArrayList<String[]></code>. Chaque tableau de chaînes
   * de caractères contenu correspond à un emprunt passé.<br/>
   * Il doit contenir 9 éléments (dans cet ordre) :
   * <ul>
   *   <li>0 : id de l'exemplaire</li>
   *   <li>1 : id du livre correspondant</li>
   *   <li>2 : titre du livre</li>
   *   <li>3 : son auteur</li>
   *   <li>4 : id de l'abonné</li>
   *   <li>5 : nom de l'abonné</li>
   *   <li>6 : son prénom</li>
   *   <li>7 : la date de l'emprunt</li>
   *   <li>8 : la date de retour</li>
   * </ul>
   * @return un <code>ArrayList</code> contenant autant de tableaux de String (5 chaînes de caractères) que d'emprunts dans la base.
   * @throws SQLException en cas d'erreur de connexion à la base.
   */
  public static ArrayList<String[]> listeEmpruntsHistorique() throws SQLException {
	    ArrayList<String[]> emprunts = new ArrayList<String[]>();
	    Statement stmt = Connexion.getConnection().createStatement();
	    String sql = "select e.id_exemplaire,ex.id_livre,l.titre,l.auteur,e.id_abonne,a.nom,a.prenom,e.date_emprunt, e.date_retour from emprunt as e"+ 
	    				" inner join exemplaire as ex on e.id_exemplaire = ex.id"+
	    				" inner join livre as l on ex.id_livre = l.id"+
	    				" inner join abonne as a on e.id_abonne = a.id and date_retour is not null;";
	    ResultSet rset = stmt.executeQuery(sql);

	    while (rset.next()) {
	      String[] emprunt = new String[9];
	      emprunt[0] = rset.getString("id_exemplaire");
	      emprunt[1] = rset.getString("id_livre");
	      emprunt[2] = rset.getString("titre");
	      emprunt[3] = rset.getString("auteur");
	      emprunt[4] = rset.getString("id_abonne");
	      emprunt[5] = rset.getString("nom");
	      emprunt[6] = rset.getString("prenom");
	      emprunt[7] = rset.getString("date_emprunt");
	      emprunt[8] = rset.getString("date_retour");
	      
	      
	      emprunts.add(emprunt);
	    }
	    rset.close();
	    stmt.close();
	    return emprunts;
  }

  /**
   * Emprunter un exemplaire à partir de l'identifiant de l'abonné et de
   * l'identifiant de l'exemplaire.
   * 
   * @param idAbonne : id de l'abonné emprunteur.
   * @param idExemplaire id de l'exemplaire emprunté.
   * @throws SQLException en cas d'erreur de connexion à la base.
   */
  public static void emprunter(int idAbonne, int idExemplaire) throws SQLException {
	    Statement stmt = Connexion.getConnection().createStatement();
	 	

	    
	    boolean status = true;
	    String sql = "select status from exemplaire where id="+idExemplaire;
	 	ResultSet rset = stmt.executeQuery(sql);
	 	while(rset.next()){
	 		status= rset.getBoolean("status");
	 	}
	 	if (status==false){
	 		Calendar calendar = Calendar.getInstance();
	 		java.util.Date now = calendar.getTime();
		    java.sql.Timestamp date = new java.sql.Timestamp(now.getTime());
		    sql = "insert into emprunt values ("+idAbonne+","+idExemplaire+",'"+date+"')";
	 		System.out.println(sql);
		 	stmt.executeUpdate(sql);
		 	sql = "update exemplaire set status=true where id = "+idExemplaire;
		 	stmt.executeUpdate(sql);
	 	}
	 	stmt.close();
	 	
  }

  /**
   * Retourner un exemplaire à partir de son identifiant.
   * 
   * @param idExemplaire id de l'exemplaire à rendre.
   * @throws SQLException en cas d'erreur de connexion à la base.
   */
  public static void rendre(int idExemplaire) throws SQLException {
	    Statement stmt = Connexion.getConnection().createStatement();
	 	
	    boolean status = false;
	    String sql = "select status from exemplaire where id="+idExemplaire;
	 	ResultSet rset = stmt.executeQuery(sql);
	 	while(rset.next()){
	 		status= rset.getBoolean("status");
	 	}
	 	if (status==true){
	 		Calendar calendar = Calendar.getInstance();
	 		java.util.Date now = calendar.getTime();
		    java.sql.Timestamp date = new java.sql.Timestamp(now.getTime());
		    
		    sql = "select date_emprunt from emprunt where id_exemplaire="+idExemplaire;
		    rset = stmt.executeQuery(sql);
		    
		    java.util.Date date_emprunt = new java.sql.Timestamp(now.getTime());
		    while (rset.next()) {
		    	date_emprunt = rset.getDate("date_emprunt");
		    }
		    java.sql.Timestamp date_emp = new java.sql.Timestamp(date_emprunt.getTime());
	 		sql = "update emprunt set date_retour = '"+date+"' where id_exemplaire = "+idExemplaire+" and date_emprunt = '"+date_emp+"'";
		 	stmt.executeUpdate(sql);
		 	sql = "update exemplaire set status=false where id = "+idExemplaire;
		 	stmt.executeUpdate(sql);
	 	}
	 	
  }
  
  /**
   * Détermine si un exemplaire sonné connu par son identifiant est
   * actuellement emprunté.
   * 
   * @param idExemplaire
   * @return <code>true</code> si l'exemplaire est emprunté, <code>false</code> sinon
   * @throws SQLException en cas d'erreur de connexion à la base.
   */
  public static boolean estEmprunte(int idExemplaire) throws SQLException {
    boolean estEmprunte = false;
    Statement stmt = Connexion.getConnection().createStatement();
    String sql = "select status from exemplaire where id="+idExemplaire;
 	ResultSet rset = stmt.executeQuery(sql);
 	while(rset.next()){
 		estEmprunte= rset.getBoolean("status");
 	}
 	stmt.close();
    return estEmprunte;
  }

  /**
   * Récupération des statistiques sur les emprunts (nombre d'emprunts et de
   * retours par jour).
   * 
   * @return un <code>HashMap<String, int[]></code>. Chaque enregistrement de la
   * structure de données est identifiée par la date (la clé) exprimée sous la forme
   * d'une chaîne de caractères. La valeur est un tableau de 2 entiers qui représentent :
   * <ul>//
    // A COMPLETER
    //
   *   <li>0 : le nombre d'emprunts</li>
   *   <li>1 : le nombre de retours</li>
   * </ul>
   * Exemple :
   * <pre>
   * +-------------------------+
   * | "2017-04-01" --> [3, 1] |
   * | "2017-04-02" --> [0, 1] |
   * | "2017-04-07" --> [5, 9] |
   * +-------------------------+
   * </pre>
   *   
   * @throws SQLException
   */
  public static HashMap<String, int[]> statsEmprunts() throws SQLException
  {
    HashMap<String, int[]> stats = new HashMap<String, int[]>();
    Statement stmt = Connexion.getConnection().createStatement();
    // creer une liste de date_emprunt
    ArrayList<String> date_emprunt = new ArrayList<String>();
    String sql = "select distinct date_emprunt, date_retour from emprunt";   
 	ResultSet rset = stmt.executeQuery(sql);
 	
 	while(rset.next()){
 		String date;
 		String date2;
 		date= rset.getString("date_emprunt");
 		if (date != null){
 	 		date_emprunt.add(date);}
 		date2= rset.getString("date_retour");
 		if (date2 != null){
 	 		date_emprunt.add(date2);}
 	}
 	//remove r = new remove();
 	ArrayList<String> date_emprunt2 = removeDuplicates(date_emprunt);
 	Collections.sort(date_emprunt2);
 	System.out.print(date_emprunt2);
 	// creer HashMap
 	for (String item : date_emprunt2){
 		int[] quantite = new int[2];
 		String sql1 = "select count(date_emprunt) from emprunt where date_emprunt = '"+item+"'";
 		ResultSet emprunt = stmt.executeQuery(sql1);
 		while(emprunt.next()){
 			quantite[0] = emprunt.getInt("count");
 			  //System.out.println(quantite[0]);
 		}
 		String sql2 = "select count(date_retour) from emprunt where date_retour ='"+item+"'";
 		ResultSet retour = stmt.executeQuery(sql2);
 		while(retour.next()){
 			quantite[1] = retour.getInt("count");
 			//System.out.println("///");
 			//System.out.println(quantite[1]);
 		}
 		System.out.println(item);
 		System.out.println(Arrays.toString(quantite));
 		stats.put(item, quantite);
 	}
 	System.out.println("HashMap :"+stats+"\n");
 	//System.out.println("The Value is: ");
 	//System.out.println(stats.get(10/02/2018));
 	stmt.close();
    return stats;
  }
  
	public static ArrayList<String> removeDuplicates(ArrayList<String> list) {

        // Store unique items in result.
        ArrayList<String> result = new ArrayList<>();

        // Record encountered Strings in HashSet.
        HashSet<String> set = new HashSet<>();

        // Loop over argument list.
        for (String item : list) {

            // If String is not in set, add it to the list and the set.
            if (!set.contains(item)) {
                result.add(item);
                set.add(item);
            }
        }
        return result;
	}
}
