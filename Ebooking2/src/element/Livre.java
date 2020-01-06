package element;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Bd.DBConnexion;
/**
 * <b>Livre est la classe qui représente les Livre servis dans chaque restaurant</b>
 * <p>
 * Un Livre peut être une entrée, Livre principal, dessert ou boisson et est caractérisé par :
 * <ul>
 * <li>Un identifiant du Livre unique attribué définitivement.</li>
 * <li>Un identifiant du restaurant où est servis ce Livre.</li>
 * <li>le nom du Livre.</li>
 * <li>le prix du Livre.</li>
 * <li>Le type du Livre</li>
 * </ul>
 * </p>
 * 
 * 
 * @author Zahra BIADY
 * @version 1.0
 */
public class Livre {
	
	private  int id_livre;


    private int classement;
	private String nom;

    private  String description;
	private int prix;

    private int quantite;
	private String categorie;

	private static DBConnexion db = new DBConnexion(); 
	
	public Livre(int id,String no,String typ,int cl,String dsc,int p){
		id_livre=id;
		nom=no;
		categorie=typ;
		classement=cl;
		prix=p;
		description=dsc;
	}
	
	
	public Livre() {
		
	}
	public Livre(int id_p){
		id_livre = id_p;
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://"+db.getDBServer()+"/"+db.getDBname()+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",db.getDBusername(),db.getDBpassword());  
			Statement stmt=con.createStatement();  
			String query = "SELECT * FROM Livre WHERE id_livre="+id_livre;
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next())
            {
                nom = rs.getString("nom");
            	categorie= rs.getString("categorie");
            	prix = rs.getInt("prix");
            }
			con.close();  
		}catch(Exception e){ 
			System.out.println(e);
		}
	}
	/**
     * Retourne l'ID du Livre.
     * 
     * @return L'identifiant du Livre. 
     */
	public int getId() {
		return id_livre;
	}
	/**
     * Retourne le nom du Livre.
     * 
     * @return Le nom du Livre. 
     */
	public String getNom() {
		return nom;
	}
	/**
     * Retourne le prix du Livre.
     * 
     * @return Le prix du Livre. 
     */
	public int getPrix() {
		return prix;
	}

	/**
     * Retourne le type du Livre.
     * 
     * @return Le type du Livre.
     */
	public String getType() {
		return categorie;
	}
	/**
     * Mise à jour du nom du Livre.
     * 
     * @param n
     *            Le nouveau nom du Livre.
     */
	public void setNom(String n) {
		nom = n;
	}
	/**
     * Mise à jour du prix du Livre.
     * 
     * @param p
     *            Le nouveau prix du Livre.
     */
	public void setPrix(int p) {
		prix = p;
	}

 public Integer getlastID() {
    Integer numero=0;
    Integer risultato=-1;
    try {
    	Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con= DriverManager.getConnection("jdbc:mysql://"+db.getDBServer()+"/"+db.getDBname()+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",db.getDBusername(),db.getDBpassword());  
        Statement stmt = con.createStatement();
        numero = stmt.executeUpdate("SELECT id_livre FROM Livre", Statement.RETURN_GENERATED_KEYS);

        ResultSet rs = stmt.getGeneratedKeys();
        if (rs.next()){
            risultato=rs.getInt(1);
        }
        rs.close();
        stmt.close();
    } catch (Exception e) {
        System.out.println(e);
        risultato=-1;
    }
  return risultato;
}

	
	 public void add_Livre() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://"+db.getDBServer()+"/"+db.getDBname()+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",db.getDBusername(),db.getDBpassword());  
			Statement stmt=con.createStatement();
			PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO Livre (`nom`, `categorie`,`classement`,'description',`prix' VALUES (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(2, nom);
            preparedStatement.setString(3,categorie );
			preparedStatement.setInt(4,classement );
			preparedStatement.setString(6,description );
            preparedStatement.setInt(7, prix);
            preparedStatement.executeUpdate();
            ResultSet rs= preparedStatement.getGeneratedKeys();
            int lastid;
			if(rs.next()){
				lastid=rs.getInt(1);
				id_livre = getlastID()+1;
			}
			
            con.close();
		}
		catch(Exception e) {
		System.out.println(e);
		System.out.println("SQLException: " + e.getMessage());
		}
		
	}
	 
	 
	 
		/**
	     * Retourne la liste fiction d'un restaurant
	     *  
	     *  @return listes d'entrées d'un restaurant
	     */
		public static List<Livre> getFiction(){
			List<Livre> pList = new ArrayList<>();
			try{  
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://"+db.getDBServer()+"/"+db.getDBname()+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",db.getDBusername(),db.getDBpassword());  
				Statement stmt=con.createStatement();  
				String query = "SELECT * FROM Livre WHERE categorie='fiction'";
	            ResultSet rs = stmt.executeQuery(query);
	            while(rs.next())
	            {
	            	pList.add(new Livre(rs.getInt("id_livre")));
	            }
				con.close();  
			}catch(Exception e){ 
				System.out.println(e);
			}
			return pList;
		}
		
		
		
		
		
		
		public int getIDLivre(String a){

			ResultSet numero;
			int risultato=0;
			try{  
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://"+db.getDBServer()+"/"+db.getDBname()+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",db.getDBusername(),db.getDBpassword());  
				Statement stmt=con.createStatement();  
				
				ResultSet rs = stmt.getGeneratedKeys();
		        while (rs.next()){
		            risultato=rs.getInt(1);
		        }
		        rs.close();
		        stmt.close();
				con.close();  
			}catch(Exception e){ 
				System.out.println(e);
				risultato=0;
			}
			return risultato;
		}
		
		
		
		
		
		
		
		
		
		
		
		/**
	     * Retourne la liste de Livre principal d'un restaurant
	     *  
	     *  @return liste de Livre principal d'un restaurant
	     */
		public static List<Livre> getLivrePrincipal(){
			List<Livre> pList = new ArrayList<>();
			try{  
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://"+db.getDBServer()+"/"+db.getDBname()+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",db.getDBusername(),db.getDBpassword());  
				Statement stmt=con.createStatement();  
				String query = "SELECT * FROM Livre WHERE type_Livre='Livre_principal'";
	            ResultSet rs = stmt.executeQuery(query);
	            while(rs.next())
	            {
	            	pList.add(new Livre(rs.getInt("id_livre")));
	            }
				con.close();  
			}catch(Exception e){ 
				System.out.println(e);
			}
			return pList;
		}
		/**
	     * Retourne la liste des desserts d'un restaurant
	     * 
	     * @return liste des desserts d'un restaurant
	     */
		public static List<Livre> getDessert(){
			List<Livre> pList = new ArrayList<>();
			try{  
				Class.forName("com.mysql.cj.jdbc.Driver");  
				Connection con=DriverManager.getConnection("jdbc:mysql://"+db.getDBServer()+"/"+db.getDBname()+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",db.getDBusername(),db.getDBpassword());  
				Statement stmt=con.createStatement();  
				String query = "SELECT * FROM Livre WHERE type_Livre='dessert'";
	            ResultSet rs = stmt.executeQuery(query);
	            while(rs.next())
	            {
	            	pList.add(new Livre(rs.getInt("id_livre")));
	            }
				con.close();  
			}catch(Exception e){ 
				System.out.println(e);
			}
			return pList;
		}
	 
	 
	 
	 
	 
		/**
	     * Retourne le prix total d'une listes d'articles
	     * 
	     * @return liste de Livre principal d'un restaurant
	     */
		public static int getTotalPrix(List<Integer> list_pid) {
			int prixTotal = 0;
			for(int i = 0;i < list_pid.size();i++) {
				Livre p = new Livre(list_pid.get(i));
				prixTotal = prixTotal + p.getPrix();
			}
			return prixTotal;
		}

	
	
	
}