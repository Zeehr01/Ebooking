package element;


import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import Bd.DBConnexion;
/**
 * <b>Commande est la classe qui représente la commande d'un utilisateur</b>
 * <p>
 * Une commande est caractérisé par
 * <ul>
 * <li>Un identifiant de commande unique attribué définitivement.</li>
 * <li>Un identifiant du restaurant où on a fait la commande.</li>
 * <li>Un liste contenant tous les identifiants des plats commandés.</li>
 * <li>le nom de la personne qui a fait la commande.</li>
 * <li>L'adresse de la personne qui a fait la commande.</li>
 * <li>Le numéro de télephone de la personne qui a fait la commande.</li>
 * <li>Le prix total de la commande.</li>
 * </ul>
 * </p>
 * 
 * 
 * @author Zahra BIADY
 * @version 1.0
 */

public class Commande {
	/**
     * L'ID de la commande.
     */
	private int id_c;
	/**
     * La liste des articles dans le panier
     * <p>
     * La liste contient les identifiants des plats ajouté par l'utilisateur dans le panier
     * <p>
     * 
     * @see Commande#getListePanier()
     */
	private List<Integer> list_panier = new ArrayList<>();
	/**
     * Le nom de celui qui a fait la commande.
     * 
     * @see Commande#getNom()
     * @see Commande#setNom(String)
     */
	private String nom;
	
	// L'identifiant du restaurant choisi
    private String id_client;
	/**
     * Le numéro de télephone de celui qui à fait la commande.
     */
	private int user_id;
	/**
     * Le prix total de tous les articles ajouté au panier.
     */
	private int prix;
	/**
     * Variable contenant les informations pour la connexion à la base de donnée.
     * 
     */
	private DBConnexion db = new DBConnexion();

	
    /**
     * Constructeur Commande.
     * <p>
     * A la construction d'un objet Commande, on lui attribue l'id du restaurant, le nom de l'utilisateur, la listes des articles, l'adresse, le numéro de télephone et le prix total 
     * </p>
     * 
     * @param l
     *            Liste des articles dans le panier
     * @param n
     *            le nom de l'utilisateur
     * @param adrs
     *            l'adresse de l'utilisateur
     * @param t
     *            le numéro de télephone de l'utilisateur
     * @param p
     *            le prix de la commande
     */
	public Commande(List<Integer> l, String id_client2, int p){
		id_client = id_client2;
		list_panier = l;
		prix = p;
	}
	
	public Commande() {
		
	}
	
	 /**
     * Retourne l'ID de la commande.
     * 
     * @return L'identifiant de la commande. 
     */
	public int getId(){
		return id_c;
	}
  
	/**
     * Retourne le nom de l'utilisateur.
     * 
     * @return Le nom de l'utilisateur ayant effectué la commande.
     */
	public String getNom() {
		return nom;
	}
	
	/**
     * Retourne le numéro de télephone de l'utilisateur.
     * 
     * @return Le numéro de télephone de l'utilisateur qui a fait la commande.
     */
	public int getuser_id() {
		return user_id;
	}
	/**
     * Retourne le prix total de la commande.
     * 
     * @return Le prix total de la commande.
     */
	public int getPrix() {
		return prix;
	}
	/**
     * Retourne la liste des ID des articles dans le panier.
     * 
     * @return La listes des articles dans le panier.
     */
	public List<Integer> getListePanier(){
		return list_panier;
	}
	
	/**
     * Met à jour le nom de l'utilisateur.
     * 
     * @param nom
     *            Le nouveau nom de l'utilisateur.
     */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
     * Met à jour le numéro de télephone de l'utilisateur.
     * 
     * @param user_id
     *            Le nouveau numéro de télephone.
     */
	public void setuser_id(int user_id) {
		this.user_id = user_id;
	}
	/**
     * Met à jour le prix total de la commande.
     * 
     * @param prix
     *            Le nouveau prix.
     */
	public void setPrix(int prix) {
		this.prix = prix;
	}
	/**
     * Met à jour la liste des ID des articles dans le panier.
     * 
     * @param l
     *            La nouvelle liste des articles dans le panier.
     */
	public void setListePanier(List<Integer> l){
		list_panier = l;
	}
	
	
	/**
     * Insère les données de la commande dans la base de donnée
     * 
     */
	public void insererCommande(String id_client) {
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con= DriverManager.getConnection("jdbc:mysql://"+db.getDBServer()+"/"+db.getDBname()+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",db.getDBusername(),db.getDBpassword());  
			Statement stmt=con.createStatement();  
			PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO commande (`id_commande`, `date`, `prix_toCom`,`id_client`) VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, id_c);
            Calendar calendar = Calendar.getInstance();
            java.sql.Date ourJavaDateObject = new java.sql.Date(calendar.getTime().getTime());
            preparedStatement.setDate(2, ourJavaDateObject);
            preparedStatement.setInt(3, prix);
            preparedStatement.setString(4, id_client);
            preparedStatement.executeUpdate();
            ResultSet rs= preparedStatement.getGeneratedKeys();
			int lastid;
			if(rs.next()){
				lastid=rs.getInt(1);
				id_c = lastid;
			}
			con.close();  
		}catch(Exception e){ 
			System.out.println(e);
		}
	}
	/**
     * Insère les articles du panier de la commande dans la base de donnée
     * 
     */
	public void insererPanier() {
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con= DriverManager.getConnection("jdbc:mysql://"+db.getDBServer()+"/"+db.getDBname()+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",db.getDBusername(),db.getDBpassword());  
			Statement stmt=con.createStatement();  
			for(int i = 0; i<list_panier.size();i++) {
				PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO panier (`id_commande`, `id_plat`,`id_client`,`id_panier`) VALUES (?,?,?,?)");
	            preparedStatement.setInt(1, 6);
	            preparedStatement.setInt(2, list_panier.get(i));
	            preparedStatement.setString(3, id_client);
	            preparedStatement.setInt(4, user_id);
	            preparedStatement.executeUpdate();
			}
			con.close();
		}catch(Exception e){ 
			System.out.println(e);
		}
	}
	
	
	public int getId_commande(){
		
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://"+db.getDBServer()+"/"+db.getDBname()+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",db.getDBusername(),db.getDBpassword());  
			Statement stmt=con.createStatement();  
			String query = "SELECT * FROM commande";
            ResultSet rs = stmt.executeQuery(query);
            int lastid;
			if(rs.next()){
				lastid=rs.getInt("id_commande");
				id_c = lastid;
			}
			con.close();  
		}catch(Exception e){ 
			System.out.println(e);
		}
		return id_c;
	}
	
	
	
	public int getId_client(int i){
		int id=0;
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://"+db.getDBServer()+"/"+db.getDBname()+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",db.getDBusername(),db.getDBpassword());  
			Statement stmt=con.createStatement();  
			String query = "SELECT * FROM commande where id_commande="+i;
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next())
            {
            	id=rs.getInt("id_client");
            }
			con.close();  
		}catch(Exception e){ 
			System.out.println(e);
		}
		return id;
	}
	
	public Date get_date(int i){
		Date d = null;
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://"+db.getDBServer()+"/"+db.getDBname()+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",db.getDBusername(),db.getDBpassword());  
			Statement stmt=con.createStatement();  
			String query = "SELECT * FROM commande where id_commande="+i;
            ResultSet rs = stmt.executeQuery(query);
            if(rs.next())
            {
            	d=rs.getDate("date");
            }
			con.close();  
		}catch(Exception e){ 
			System.out.println(e);
		}
		return d;
	}	
	

}
