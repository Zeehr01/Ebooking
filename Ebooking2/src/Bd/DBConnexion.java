package Bd;


import java.sql.*;


/**
 * <b>DBConnexion est un classe qui contient tous les informations necessaires pour se connecter à la base de données >
 * <p>
 * DBConnexion est caractérisé par :
 * <ul>
 * <li>Le nom de serveur.</li>
 * <li>Le nom de la base de données.</li>
 * <li>Le nom d'utilisateur de la base de données.</li>
 * <li>Le mot de passe de la base de données</li>
 * </ul>
 * </p>
 * 
 * 
 * @author  Zahra BIADY
 * @version 1.0
 */
public class DBConnexion {
	
	private String server;
	
	private String dbname;
	
	private String username;
	
	private String password;
	
	
	public DBConnexion(){
		server = "localhost";
		dbname = "ebooking";
		username = "root";
		password = "";
	}
	
	public DBConnexion(String server, String dbname, String username, String password){
		this.server = server;
		this.dbname = dbname;
		this.username = username;
		this.password = password;
	}
	
	public String getDBServer() {
		return server;
	}
	
	public String getDBusername() {
		return username;
	}

	public String getDBpassword() {
		return password;
	}
	
	public String getDBname() {
		return dbname;
	}
	
	public void setDBServer(String s) {
		server = s;
	}

	public void setDBusername(String u) {
		username = u;
	}
	
	public void setDBpassword(String p) {
		password = p;
	}
	
	public void setDBname(String dbn) {
		dbname = dbn;
	}
	public String toString() {
		
		return "Server = "+server+"\nDataBase Name = "+dbname+"\nUsername = "+username+"\nPassword = "+password;
	}
	
}
