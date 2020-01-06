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

public class Admin {
	
	private String id_admin;
	private String mot_pass;
	private String nom_admin;
	
	private DBConnexion db=new DBConnexion();
	
	public Admin(String nom,String mp) {
		mot_pass=mp;
		nom_admin=nom;
	}

	public Admin() {
		// TODO Auto-generated constructor stub
	}

	public String getId_admin() {
		return id_admin;
	}

	public void setId_admin(String id_admin) {
		this.id_admin = id_admin;
	}

	
	public Admin identifAdmin() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://"+db.getDBServer()+"/"+db.getDBname()+"?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",db.getDBusername(),db.getDBpassword());  
			Statement stmt=con.createStatement();  
			String query = "SELECT * FROM admin ";
            ResultSet rs = stmt.executeQuery(query);
            String lastid;
            if(rs.next()) {
            	nom_admin = rs.getString("nom_admin");
            	mot_pass = rs.getString("mpass_ad");
			} 
            con.close();
		}
		catch(Exception e) {
		System.out.println(e);
		System.out.println("SQLException: " + e.getMessage());
		}
		Admin ad=new Admin(nom_admin,mot_pass);
		return ad ;
	}

	public String getMot_pass() {
		return mot_pass;
	}

	public void setMot_pass(String mot_pass) {
		this.mot_pass = mot_pass;
	}

	public String getNom_admin() {
		return nom_admin;
	}

	public void setNom_admin(String nom_admin) {
		this.nom_admin = nom_admin;
	}


}
