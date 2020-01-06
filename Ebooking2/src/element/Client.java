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

public class Client {
	
	private String id_client;
	private String nom_client;
	private String mot_de_passe;
	private int id_entite=0;
	
	
	private DBConnexion db=new DBConnexion();
	
	public Client(String c,String n,String mp) {
		id_client=c;
		nom_client=n;
		mot_de_passe=mp;
	}

	public String getMot_de_passe() {
		return mot_de_passe;
	}

	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}

	public int getId_entite() {
		return id_entite;
	}

	public void setId_entite(int id_entite) {
		this.id_entite = id_entite;
	}

	public String getId_client() {
		return id_client;
	}

	public void setId_client(String id_client) {
		this.id_client = id_client;
	}


	public String getNom_client() {
		return nom_client;
	}

	public void setNom_client(String nom_client) {
		this.nom_client = nom_client;
	}

	
	
	
	public void registerUser() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://"+db.getDBServer()+"/"+db.getDBname()+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",db.getDBusername(),db.getDBpassword());  
			Statement stmt=con.createStatement();
			PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO client (`id_client`, `nom`,`motdepass`) VALUES (?,?,?)",Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, id_client);
            preparedStatement.setString(2, nom_client);
            preparedStatement.setString(3, mot_de_passe);
            preparedStatement.executeUpdate();
            ResultSet rs= preparedStatement.getGeneratedKeys();
            String lastid;
			if(rs.next()){
				lastid=rs.getString(1);
				id_client = lastid;
			}
			
            con.close();
		}
		catch(Exception e) {
		System.out.println(e);
		System.out.println("SQLException: " + e.getMessage());
		}
		
	}
	
	
	public Client (String id_c){
		id_client = id_c;
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");  
			Connection con=DriverManager.getConnection("jdbc:mysql://"+db.getDBServer()+"/"+db.getDBname()+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",db.getDBusername(),db.getDBpassword());  
			Statement stmt=con.createStatement();  
			String query = "SELECT * FROM client WHERE id_client="+id_client;
            ResultSet rs = stmt.executeQuery(query);
            String lastid;
			if(rs.next()){
				lastid=rs.getString(1);
				id_client = lastid;
			}
			con.close();  
		}catch(Exception e){ 
			System.out.println(e);
		}
	}
	
		public void delUser(String id) {
		try {
		id_client=id;
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://"+db.getDBServer()+"/"+db.getDBname()+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",db.getDBusername(),db.getDBpassword());  
			Statement stmt=con.createStatement();
			PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM client WHERE client.id_client="+id_client,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.executeUpdate();
            ResultSet rs= preparedStatement.getGeneratedKeys();
            String lastid;
			if(rs.next()){
				lastid=rs.getString(1);
				id_client = lastid;
			}
			
            con.close();
		}
		catch(Exception e) {
		System.out.println(e);
		System.out.println("SQLException: " + e.getMessage());
		}
		
		}
		
		public Client() {
			
		}
		public Client identifClient(String id) {
			id_client=id;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://"+db.getDBServer()+"/"+db.getDBname()+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",db.getDBusername(),db.getDBpassword());  
			Statement stmt=con.createStatement();  
			String query = "SELECT * FROM client WHERE client.id_client="+id_client;
            ResultSet rs = stmt.executeQuery(query);
            String lastid;
            while(rs.next()) {
            	id_client = rs.getString("id_client");
            	nom_client = rs.getString("nom_client");
            	mot_de_passe = rs.getString("mot_de_passe");
			} 
            con.close();
		}
		catch(Exception e) {
		System.out.println(e);
		System.out.println("SQLException: " + e.getMessage());
		}
		Client cl=new Client(id_client,nom_client,mot_de_passe);
		return cl ;
	}
	
}
