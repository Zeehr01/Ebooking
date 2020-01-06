
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import javax.swing.border.LineBorder;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Toolkit;
import javax.swing.JSeparator;
import java.awt.Button;
import javax.swing.JPasswordField;
import java.awt.Panel;
import java.awt.Dimension;

import element.Admin;
import element.Commande;
import element.Client;
import element.Livre;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import javax.swing.JEditorPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.event.PopupMenuListener;
import javax.swing.event.PopupMenuEvent;
import Bd.DBConnexion;

public class Home extends JFrame {
	int mouseX;
	int mouseY;
	
	private String id_admin;
	
	private int id_panier;
	private String nom_client;
	private String entite; 
	
	
	
	// L'identifiant du cleint choisi
		private String id_client;
		private static int id_entite = 0;		
		// liste des Livres
		// liste des identifiants des Livres ajouté au panier
		List<Integer> panier_List= new ArrayList<>();
		
		
		private static DBConnexion db = new DBConnexion(); 

	private JTextField text_Livre_principal;
	private JTextField text_dessert;
	private JTextField textField_1;
	private JPasswordField passwordField_1;
	private JTextField Fiel_nom_livre;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_type;
	private JTextField textField_prix;
	private JTextField textField_8;
	private JPasswordField passwordField_2;
	private JTable table;
	private JTextField text_entree;
	
	public Home() {
		setResizable(false);
		setSize(new Dimension(748, 416));
		setIconImage(Toolkit.getDefaultToolkit().getImage("src\\images\\Untitled.png"));
		getContentPane().setLayout(null);
		setUndecorated(true);
		
		// bouton de fermerture de la fenetre
				JPanel close = new JPanel();
				close.setBorder(new EmptyBorder(0, 0, 0, 0));
				close.setBounds(690, 0, 58, 35);
				close.setBackground(Color.DARK_GRAY);
				getContentPane().add(close);
				close.setLayout(null);
				JLabel croix = new JLabel("X");
				croix.setForeground(Color.WHITE);
				croix.setFont(new Font("Tahoma", Font.PLAIN, 20));
				croix.setBounds(23, 0, 25, 35);
				close.add(croix);
				close.addMouseListener(new MouseAdapter()  
				{  
				    public void mouseClicked(MouseEvent e)  
				    {  
				       dispose();

				    }  
				});
					
			

		// Faire bouger la fenêtre
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int cordinatX = e.getXOnScreen();
				int cordinatY = e.getYOnScreen();
				setLocation(cordinatX - mouseX,cordinatY-mouseY);
			}
		});
		
		
		
		JPanel home_panel = new JPanel();
		home_panel.setBounds(0, 0, 788, 434);
		Border brd = BorderFactory.createLineBorder(new Color(255, 255, 230,12), 3);
		Border bord = BorderFactory.createLineBorder(new Color(255, 255, 230,20), 2);
		Border brdset=BorderFactory.createLineBorder(new Color(255, 255, 230,40),3);
		Commande c=new Commande();
		
		 Border border2 = BorderFactory.createLineBorder(new Color(209, 224, 209,150),3);
		 //invisible border
		 Border no_border = BorderFactory.createLineBorder(new Color(1, 1, 1,0), 0);
		
		JPanel panel_admin = new JPanel();
		panel_admin.setBackground(Color.BLACK);
		panel_admin.setBounds(0, 0, 748, 416);
		getContentPane().add(panel_admin);
		panel_admin.setLayout(null);
		panel_admin.setVisible(true);
		
		   JButton btnback_ad = new JButton("");
		   btnback_ad.setIcon(new ImageIcon("C:\\Users\\Utilisateur\\Desktop\\appResto\\back.png"));
		   btnback_ad.setBounds(10, 0, 48, 45);
		   panel_admin.add(btnback_ad);
		   btnback_ad.setBackground(new Color(0,0,0,0));
		   
		   JLabel lblEntit_admin = new JLabel("Identifiant :");
		   lblEntit_admin.setForeground(Color.WHITE);
		   lblEntit_admin.setFont(new Font("Lucida Handwriting", Font.PLAIN, 20));
		   lblEntit_admin.setBounds(91, 148, 168, 52);
		   panel_admin.add(lblEntit_admin);
		   
	
		   Button butsave = new Button("S'identifier");
		   butsave.setForeground(Color.WHITE);
		   butsave.setBackground(Color.DARK_GRAY);
		   butsave.setFont(new Font("Lucida Handwriting", Font.PLAIN, 28));
		   butsave.setBounds(269, 330, 201, 45);
		   panel_admin.add(butsave);

		   //ajout d'un bouton
		   Button test = new Button("test");
		   butsave.setForeground(Color.WHITE);
		   butsave.setBackground(Color.DARK_GRAY);
		   butsave.setFont(new Font("Lucida Handwriting", Font.PLAIN, 28));
		   butsave.setBounds(269, 330, 201, 45);
		   panel_admin.add(test);
		   
		   textField_1 = new JTextField();
		   textField_1.setBounds(269, 159, 201, 34);
		   panel_admin.add(textField_1);
		   textField_1.setColumns(10);
		   
		   JLabel lblMotDePasse_1 = new JLabel("Mot de passe :");
		   lblMotDePasse_1.setForeground(Color.WHITE);
		   lblMotDePasse_1.setFont(new Font("Lucida Handwriting", Font.PLAIN, 20));
		   lblMotDePasse_1.setBounds(91, 211, 175, 45);
		   panel_admin.add(lblMotDePasse_1);
		   
		   passwordField_1 = new JPasswordField();
		   passwordField_1.setBounds(269, 225, 202, 34);
		   panel_admin.add(passwordField_1);
		   
		   JLabel identi = new JLabel("");
		   identi.setHorizontalAlignment(SwingConstants.CENTER);
		   identi.setIcon(new ImageIcon("src\\images\\book-opened-symmetrical-shape (2).png"));
		   identi.setBounds(252, 73, 96, 64);
		   panel_admin.add(identi);
		   
		   JLabel label = new JLabel("");
		   label.setHorizontalAlignment(SwingConstants.CENTER);
		   label.setIcon(new ImageIcon("src\\images\\user (2).png"));
		   label.setBounds(206, 330, 60, 45);
		   panel_admin.add(label);
		   
		   JLabel lblEbooking_1 = new JLabel("E-Booking");
		   lblEbooking_1.setFont(new Font("Felix Titling", Font.PLAIN, 17));
		   lblEbooking_1.setForeground(Color.WHITE);
		   lblEbooking_1.setBounds(343, 93, 96, 34);
		   panel_admin.add(lblEbooking_1);
		   //Revenir à l'Accueil
			 		btnback_ad.addMouseListener(new MouseAdapter()  
		   	{  
		   	    public void mouseClicked(MouseEvent e) 
		   	    {  
		   	        panel_admin.setVisible(false);

		   	    }
		   	    
		   		
		   	});
						
											
											JPanel panel_admin_1 = new JPanel();
											panel_admin_1.setForeground(new Color(143, 188, 143));
											panel_admin_1.setBounds(0, 0, 748, 416);
											getContentPane().add(panel_admin_1);
											panel_admin_1.setBackground(Color.DARK_GRAY);
											panel_admin_1.setLayout(null);
											panel_admin_1.setVisible(false);
											
											JLabel lblGestionLivres = new JLabel("Gestion livres");
											lblGestionLivres.setForeground(Color.WHITE);
											lblGestionLivres.setFont(new Font("Lucida Handwriting", Font.PLAIN, 19));
											lblGestionLivres.setHorizontalAlignment(SwingConstants.CENTER);
											lblGestionLivres.setBounds(244, 131, 237, 55);
											lblGestionLivres.setBorder(brdset);
											panel_admin_1.add(lblGestionLivres);
											
											
											
											
											JLabel lblGestionClient = new JLabel("Gestion Client");
											lblGestionClient.setForeground(Color.WHITE);
											lblGestionClient.setFont(new Font("Lucida Handwriting", Font.PLAIN, 19));
											lblGestionClient.setHorizontalAlignment(SwingConstants.CENTER);
											lblGestionClient.setBounds(244, 224, 237, 55);
											lblGestionClient.setBorder(brdset);
											panel_admin_1.add(lblGestionClient);
											
												JLabel lblGestionCommandes = new JLabel("Gestion Commandes");
												lblGestionCommandes.setForeground(Color.WHITE);
												lblGestionCommandes.setFont(new Font("Lucida Handwriting", Font.PLAIN, 19));
												lblGestionCommandes.setHorizontalAlignment(SwingConstants.CENTER);
												lblGestionCommandes.setBounds(251, 312, 237, 55);
												lblGestionCommandes.setBorder(brdset);
												panel_admin_1.add(lblGestionCommandes);
												
												JLabel label_7 = new JLabel("");
												label_7.setIcon(new ImageIcon("C:\\Users\\Utilisateur\\Desktop\\appResto\\ocp (1).png"));
												label_7.setBounds(694, 343, 54, 73);
												panel_admin_1.add(label_7);
												
		
		
		
		
		//-------------------------------Admin----------------------------------------------
		
		JButton back_home = new JButton("");
		back_home.setIcon(new ImageIcon("C:\\Users\\Utilisateur\\Desktop\\appResto\\back.png"));
		back_home.setBackground(new Color(0, 0, 0, 0));
		back_home.setBounds(10, 0, 45, 45);
		panel_admin_1.add(back_home);
		
		JLabel label_2 = new JLabel("");
		label_2.setForeground(Color.WHITE);
		label_2.setIcon(new ImageIcon("C:\\Users\\Utilisateur\\Desktop\\ebooking\\book-opened-symmetrical-shape (2).png"));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setBounds(232, 52, 112, 64);
		panel_admin_1.add(label_2);
		
		JLabel lblEbooking = new JLabel("E-Booking");
		lblEbooking.setForeground(Color.WHITE);
		lblEbooking.setFont(new Font("Felix Titling", Font.PLAIN, 19));
		lblEbooking.setBounds(322, 72, 153, 31);
		panel_admin_1.add(lblEbooking);
		
		back_home.addMouseListener(new MouseAdapter()  
		{  
		    public void mouseClicked(MouseEvent e) 
		    {  
		        panel_admin_1.setVisible(false);
		        panel_admin.setVisible(false);
		    }
		    
			
		});			
												
										JPanel panel_gestionLivres = new JPanel();
										panel_gestionLivres.setBackground(Color.DARK_GRAY);
										panel_gestionLivres.setBounds(0, 0, 748, 416);
										getContentPane().add(panel_gestionLivres);
										panel_gestionLivres.setLayout(null);
										panel_gestionLivres.setVisible(false);
										
										JPanel panel_gestion_1 = new JPanel();
										panel_gestion_1.setBackground(Color.BLACK);
										panel_gestion_1.setBounds(0, 0, 245, 416);
										panel_gestionLivres.add(panel_gestion_1);
										panel_gestion_1.setLayout(null);
										
										
										JLabel label_9 = new JLabel("Gestion des Livres ");
										label_9.setForeground(Color.WHITE);
										label_9.setBounds(0, 78, 232, 51);
										label_9.setHorizontalAlignment(SwingConstants.CENTER);
										label_9.setFont(new Font("Lucida Handwriting", Font.PLAIN, 18));
										label_9.setBorder(bord);
										panel_gestion_1.add(label_9);
										
										JLabel lblGestionDesClients = new JLabel("Gestion des Clients");
										lblGestionDesClients.setForeground(Color.WHITE);
										lblGestionDesClients.setHorizontalAlignment(SwingConstants.CENTER);
										lblGestionDesClients.setFont(new Font("Lucida Handwriting", Font.PLAIN, 18));
										lblGestionDesClients.setBounds(0, 155, 232, 51);
										lblGestionDesClients.setBorder(brd);
										panel_gestion_1.add(lblGestionDesClients);
										
										JLabel lblGestionDesCommandes = new JLabel("Gestion des Commandes");
										lblGestionDesCommandes.setForeground(Color.WHITE);
										lblGestionDesCommandes.setHorizontalAlignment(SwingConstants.CENTER);
										lblGestionDesCommandes.setFont(new Font("Lucida Handwriting", Font.PLAIN, 17));
										lblGestionDesCommandes.setBounds(0, 230, 239, 51);
										lblGestionDesCommandes.setBorder(brd);
										panel_gestion_1.add(lblGestionDesCommandes);
										
										JLabel label_1 = new JLabel("");
										label_1.setHorizontalAlignment(SwingConstants.CENTER);
										label_1.setIcon(new ImageIcon("C:\\Users\\Utilisateur\\Desktop\\ebooking\\book-opened-symmetrical-shape (2).png"));
										label_1.setBounds(37, 11, 144, 56);
										panel_gestion_1.add(label_1);
										
										JLabel lblGestionDesLivres = new JLabel("Gestion des Livres ");
										lblGestionDesLivres.setForeground(Color.WHITE);
										lblGestionDesLivres.setFont(new Font("Lucida Handwriting", Font.PLAIN, 20));
										lblGestionDesLivres.setHorizontalAlignment(SwingConstants.CENTER);
										lblGestionDesLivres.setBounds(346, 0, 259, 56);
										panel_gestionLivres.add(lblGestionDesLivres);
										
										JLabel lblAjouterLivres = new JLabel("Ajouter un livre :");
										lblAjouterLivres.setForeground(Color.WHITE);
										lblAjouterLivres.setFont(new Font("Segoe UI", Font.PLAIN, 17));
										lblAjouterLivres.setBounds(247, 44, 138, 27);
										panel_gestionLivres.add(lblAjouterLivres);
										
										
										
											Fiel_nom_livre = new JTextField();
											Fiel_nom_livre.setBounds(332, 87, 222, 27);
											panel_gestionLivres.add(Fiel_nom_livre);
											Fiel_nom_livre.setColumns(10);
											
											JTextField Fiel_cl_livre = new JTextField();
											Fiel_cl_livre.setBounds(332, 190, 222, 27);
											panel_gestionLivres.add(Fiel_cl_livre);
											Fiel_cl_livre.setColumns(10);
											
											JTextField Fiel_desc_livre = new JTextField();
											Fiel_desc_livre.setBounds(332, 228, 273, 74);
											panel_gestionLivres.add(Fiel_desc_livre);
											Fiel_desc_livre.setColumns(10);
											
											JLabel lblSupprimerUnLivre = new JLabel("Supprimer un livre :");
											lblSupprimerUnLivre.setForeground(Color.WHITE);
											lblSupprimerUnLivre.setFont(new Font("Segoe UI", Font.PLAIN, 17));
											lblSupprimerUnLivre.setBounds(247, 301, 186, 35);
											panel_gestionLivres.add(lblSupprimerUnLivre);
											
											textField_3 = new JTextField();
											textField_3.setColumns(10);
											textField_3.setBounds(364, 337, 222, 27);
											panel_gestionLivres.add(textField_3);
											
											JLabel lblVoirLivre = new JLabel("Voir la liste des Livres");
											lblVoirLivre.setHorizontalAlignment(SwingConstants.CENTER);
											lblVoirLivre.setFont(new Font("Segoe UI", Font.PLAIN,18));
											lblVoirLivre.setBounds(537, 372, 186, 33);
											lblVoirLivre.setBorder(brd);
											lblVoirLivre.setForeground(Color.WHITE);
											
											panel_gestionLivres.add(lblVoirLivre);
											
											JLabel lblIdentifiant = new JLabel("Identifiant :");
											lblIdentifiant.setFont(new Font("Segoe UI", Font.PLAIN, 14));
											lblIdentifiant.setBounds(264, 331, 90, 27);
											lblIdentifiant.setForeground(Color.WHITE);
											panel_gestionLivres.add(lblIdentifiant);
											
											JLabel lblNom_1 = new JLabel("Nom :");
											lblNom_1.setFont(new Font("Segoe UI", Font.PLAIN, 14));
											lblNom_1.setBounds(255, 85, 50, 26);
											lblNom_1.setForeground(Color.WHITE);
											panel_gestionLivres.add(lblNom_1);
											
											textField_type = new JTextField();
											textField_type.setColumns(10);
											textField_type.setBounds(332, 122, 222, 26);
											panel_gestionLivres.add(textField_type);
											
											textField_prix = new JTextField("5");
											textField_prix.setColumns(10);
											textField_prix.setBounds(332, 156, 222, 27);
											panel_gestionLivres.add(textField_prix);
											String prix_text=textField_prix.getText();
											
											
											id_admin=textField_1.getText();
											int prix=Integer.parseInt(prix_text);
							
											JLabel lblType = new JLabel("Cat\u00E9gorie :");
											lblType.setFont(new Font("Segoe UI", Font.PLAIN, 14));
											lblType.setBounds(245, 120, 87, 27);
											lblType.setForeground(Color.WHITE);
											panel_gestionLivres.add(lblType);
											
											JLabel lblPrix = new JLabel("Prix :");
											lblPrix.setFont(new Font("Segoe UI", Font.PLAIN, 14));
											lblPrix.setBounds(255, 154, 42, 27);
											lblPrix.setForeground(Color.WHITE);
											panel_gestionLivres.add(lblPrix);
											
											//ajouter un boutton 
											JButton btnback_admin = new JButton("");
											btnback_admin.setIcon(new ImageIcon("C:\\Users\\Utilisateur\\Desktop\\appResto\\back.png"));
											btnback_admin.setBounds(10, 0, 48, 45);
											panel_gestionLivres.add(btnback_admin);
											JLabel lblAjouter = new JLabel("Ajouter");
											lblAjouter.setBorder(brd);
											lblAjouter.setHorizontalAlignment(SwingConstants.CENTER);
											lblAjouter.setFont(new Font("Mongolian Baiti", Font.PLAIN, 23));
											lblAjouter.setBounds(615, 267, 108, 35);
											lblAjouter.setForeground(Color.WHITE);
											panel_gestionLivres.add(lblAjouter);
											lblAjouter.addMouseListener(new MouseAdapter()  
											{  
											    public void mouseClicked(MouseEvent e) 
											    {  
											    	Livre p=new Livre();
											    	Livre Livre=new Livre((p.getlastID()+1),Fiel_nom_livre.getText(),textField_type.getText(),Integer.parseInt(Fiel_cl_livre.getText()),Fiel_desc_livre.getText(),prix);
											    	Livre.add_Livre();
											    }	
											});
											
											
											
											lblAjouterLivres.addMouseListener(new MouseAdapter()  
													{  
													    public void mouseClicked(MouseEvent e) 
													    {  
													    	Livre p=new Livre();
													    	Livre plat=new Livre((p.getlastID()+1),Fiel_nom_livre.getText(),textField_type.getText(),Integer.parseInt(Fiel_cl_livre.getText()),Fiel_desc_livre.getText(),prix);
													    	plat.add_Livre();
													    }	
													});
											
													 
													
													
													JLabel lblSupprimer = new JLabel("Supprimer");
													lblSupprimer.setHorizontalAlignment(SwingConstants.CENTER);
													lblSupprimer.setFont(new Font("Mongolian Baiti", Font.PLAIN, 23));
													lblSupprimer.setBounds(615, 326, 108, 35);
													lblSupprimer.setBorder(brd);
													lblSupprimer.setForeground(Color.WHITE);
													panel_gestionLivres.add(lblSupprimer);
													
													JLabel lblDescreption = new JLabel("Descreption :");
													lblDescreption.setForeground(Color.WHITE);
													lblDescreption.setFont(new Font("Segoe UI", Font.PLAIN, 14));
													lblDescreption.setBounds(245, 228, 90, 26);
													panel_gestionLivres.add(lblDescreption);
													
													JLabel lblClassement = new JLabel("Classement :");
													lblClassement.setForeground(Color.WHITE);
													lblClassement.setFont(new Font("Segoe UI", Font.PLAIN, 14));
													lblClassement.setBounds(245, 189, 79, 24);
													panel_gestionLivres.add(lblClassement);
													
													
													//revenir al liste gestion
													btnback_admin.addMouseListener(new MouseAdapter()  
													{  
													    public void mouseClicked(MouseEvent e) 
													    {  
													    	panel_admin.setVisible(false);
													        panel_gestionLivres.setVisible(false);
													        panel_admin_1.setVisible(true);
													    }	
													});
						
						JPanel panel_gestionClient = new JPanel();
						panel_gestionClient.setBackground(Color.DARK_GRAY);
						panel_gestionClient.setBounds(0, 0, 748, 416);
						getContentPane().add(panel_gestionClient);
						panel_gestionClient.setLayout(null);
						panel_gestionClient.setVisible(false);
						
								JPanel panel_gestion_2 = new JPanel();
								panel_gestion_2.setBackground(Color.BLACK);
								panel_gestion_2.setBounds(0, 0, 244, 416);
								panel_gestionClient.add(panel_gestion_2);
								panel_gestion_2.setLayout(null);
								
								JLabel label_10 = new JLabel("Gestion des Livres ");
								label_10.setBounds(0, 88, 244, 51);
								panel_gestion_2.add(label_10);
								label_10.setForeground(Color.WHITE);
								label_10.setHorizontalAlignment(SwingConstants.CENTER);
								label_10.setFont(new Font("Lucida Handwriting", Font.PLAIN, 18));
								label_10.setBorder(brd);
								
								
								JLabel lblGestionDesClient = new JLabel("Gestion des Clients");
								lblGestionDesClient.setBounds(0, 161, 244, 51);
								panel_gestion_2.add(lblGestionDesClient);
								lblGestionDesClient.setForeground(Color.WHITE);
								lblGestionDesClient.setHorizontalAlignment(SwingConstants.CENTER);
								lblGestionDesClient.setFont(new Font("Lucida Handwriting", Font.PLAIN, 18));
								lblGestionDesClient.setBorder(bord);
								
								JLabel lblGestionDeCommande = new JLabel("Gestion des Commandes");
								lblGestionDeCommande.setBounds(0, 243, 244, 51);
								panel_gestion_2.add(lblGestionDeCommande);
								lblGestionDeCommande.setForeground(Color.WHITE);
								lblGestionDeCommande.setHorizontalAlignment(SwingConstants.CENTER);
								lblGestionDeCommande.setFont(new Font("Lucida Handwriting", Font.PLAIN, 17));
								lblGestionDeCommande.setBorder(brd);
								
								JLabel lblGestionDesClients_1 = new JLabel("Gestion des Clients");
								lblGestionDesClients_1.setHorizontalAlignment(SwingConstants.CENTER);
								lblGestionDesClients_1.setForeground(Color.WHITE);
								lblGestionDesClients_1.setFont(new Font("Lucida Handwriting", Font.PLAIN, 24));
								lblGestionDesClients_1.setBounds(295, 49, 349, 63);
								panel_gestionClient.add(lblGestionDesClients_1);
								
								JLabel lblAjouterClient = new JLabel("Ajouter Client :");
								lblAjouterClient.setHorizontalAlignment(SwingConstants.CENTER);
								lblAjouterClient.setForeground(Color.WHITE);
								lblAjouterClient.setFont(new Font("Segoe UI", Font.PLAIN, 18));
								lblAjouterClient.setBounds(269, 99, 189, 46);
								panel_gestionClient.add(lblAjouterClient);
								
								JLabel lblSupprimerClient = new JLabel("Supprimer Client :");
								lblSupprimerClient.setHorizontalAlignment(SwingConstants.CENTER);
								lblSupprimerClient.setForeground(Color.WHITE);
								lblSupprimerClient.setFont(new Font("Segoe UI", Font.PLAIN, 18));
								lblSupprimerClient.setBounds(262, 262, 205, 46);
								panel_gestionClient.add(lblSupprimerClient);
								
								textField_4 = new JTextField();
								textField_4.setBounds(452, 134, 205, 31);
								panel_gestionClient.add(textField_4);
								textField_4.setColumns(10);
								
								textField_5 = new JTextField();
								textField_5.setBounds(455, 320, 222, 39);
								panel_gestionClient.add(textField_5);
								textField_5.setColumns(10);
								
								JLabel lblVoirLaListe = new JLabel("Voir la liste des Clients ");
								lblVoirLaListe.setHorizontalAlignment(SwingConstants.CENTER);
								lblVoirLaListe.setForeground(Color.WHITE);
								lblVoirLaListe.setFont(new Font("Segoe UI", Font.PLAIN, 15));
								lblVoirLaListe.setBounds(295, 370, 205, 46);
								lblVoirLaListe.setBorder(brd);
								panel_gestionClient.add(lblVoirLaListe);
								
								JLabel lblNom_2 = new JLabel("Nom :");
								lblNom_2.setForeground(Color.WHITE);
								lblNom_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
								lblNom_2.setBounds(404, 144, 54, 26);
								panel_gestionClient.add(lblNom_2);
								
								textField_8 = new JTextField();
								textField_8.setColumns(10);
								textField_8.setBounds(452, 184, 205, 31);
								panel_gestionClient.add(textField_8);
								
								JLabel lblNom_3 = new JLabel("Identifiant :");
								lblNom_3.setFont(new Font("Tahoma", Font.PLAIN, 11));
								lblNom_3.setForeground(Color.WHITE);
								lblNom_3.setBounds(378, 186, 80, 26);
								panel_gestionClient.add(lblNom_3);
								
								JLabel label_11 = new JLabel("Identifiant :");
								label_11.setFont(new Font("Tahoma", Font.PLAIN, 11));
								label_11.setForeground(Color.WHITE);
								label_11.setBounds(366, 326, 80, 26);
								panel_gestionClient.add(label_11);
								
								
								JButton btnback_admin_1 = new JButton("");
								btnback_admin_1.setIcon(new ImageIcon("C:\\Users\\Utilisateur\\Desktop\\appResto\\back.png"));
								btnback_admin_1.setBounds(10, 0, 48, 45);
								panel_gestionClient.add(btnback_admin_1);
								
								JLabel lblMotDePasse_2 = new JLabel("Mot de passe :");
								lblMotDePasse_2.setForeground(Color.WHITE);
								lblMotDePasse_2.setFont(new Font("Tahoma", Font.PLAIN, 11));
								lblMotDePasse_2.setBounds(366, 226, 92, 26);
								panel_gestionClient.add(lblMotDePasse_2);
								
								passwordField_2 = new JPasswordField();
								passwordField_2.setBounds(452, 226, 205, 31);
								panel_gestionClient.add(passwordField_2);
								
								JLabel lblSupprimer_1 = new JLabel("Supprimer");
								lblSupprimer_1.setHorizontalAlignment(SwingConstants.CENTER);
								lblSupprimer_1.setForeground(Color.WHITE);
								lblSupprimer_1.setFont(new Font("Segoe UI", Font.PLAIN,  15));
								lblSupprimer_1.setBounds(617, 363, 121, 42);
								lblSupprimer_1.addMouseListener(new MouseAdapter()  
								{  
								    public void mouseClicked(MouseEvent e) 
								    {  
								    	 Client user=new Client(textField_5.getText());
								    	 user.delUser(textField_5.getText());
								    	 JOptionPane jop1 = new JOptionPane();
								        	jop1.showMessageDialog(null, "Client supprimé de la base de donnée", ";", JOptionPane.INFORMATION_MESSAGE);
								    }	
								});
								panel_gestionClient.add(lblSupprimer_1);
								
								JLabel lblAjouter_1 = new JLabel("Ajouter");
								lblAjouter_1.setHorizontalAlignment(SwingConstants.CENTER);
								lblAjouter_1.setForeground(Color.WHITE);
								lblAjouter_1.setFont(new Font("Segoe UI", Font.PLAIN, 15));
								lblAjouter_1.setBounds(617, 266, 121, 42);
								panel_gestionClient.add(lblAjouter_1);
								
								JPanel panel_GestionCommandes = new JPanel();
								panel_GestionCommandes.setBackground(Color.DARK_GRAY);
								panel_GestionCommandes.setBounds(0, 0, 748, 416);
								getContentPane().add(panel_GestionCommandes);
								panel_GestionCommandes.setLayout(null);
								panel_GestionCommandes.setVisible(false);
								
								
								JPanel panel_gestion_3 = new JPanel();
								panel_gestion_3.setBackground(Color.BLACK);
								panel_gestion_3.setBounds(0, 0, 245, 416);
								panel_GestionCommandes.add(panel_gestion_3);
								panel_gestion_3.setLayout(null);
								
								JLabel label_12 = new JLabel("Gestion des Livres ");
								label_12.setBounds(0, 88, 235, 51);
								panel_gestion_3.add(label_12);
								label_12.setForeground(Color.WHITE);
								label_12.setHorizontalAlignment(SwingConstants.CENTER);
								label_12.setFont(new Font("Lucida Handwriting", Font.PLAIN, 18));
								label_12.setBorder(brd);
								
								
								JLabel lblGestionDsClient = new JLabel("Gestion des Clients");
								lblGestionDsClient.setBounds(0, 161, 235, 51);
								panel_gestion_3.add(lblGestionDsClient);
								lblGestionDsClient.setForeground(Color.WHITE);
								lblGestionDsClient.setHorizontalAlignment(SwingConstants.CENTER);
								lblGestionDsClient.setFont(new Font("Lucida Handwriting", Font.PLAIN, 18));
								lblGestionDsClient.setBorder(brd);
								
								JLabel lblGestionDsCommande = new JLabel("Gestion des Commandes");
								lblGestionDsCommande.setBounds(0, 243, 235, 51);
								panel_gestion_3.add(lblGestionDsCommande);
								lblGestionDsCommande.setForeground(Color.WHITE);
								lblGestionDsCommande.setHorizontalAlignment(SwingConstants.CENTER);
								lblGestionDsCommande.setFont(new Font("Lucida Handwriting", Font.PLAIN, 17));
								lblGestionDsCommande.setBorder(bord);
								
								JLabel lblGestionCommandes_1 = new JLabel("Gestion Commandes ");
								lblGestionCommandes_1.setHorizontalAlignment(SwingConstants.CENTER);
								lblGestionCommandes_1.setFont(new Font("Lucida Handwriting", Font.PLAIN, 22));
								lblGestionCommandes_1.setBounds(291, 22, 311, 58);
								panel_GestionCommandes.add(lblGestionCommandes_1);
								
								JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
								tabbedPane.setBounds(269, 110, 469, 267);
								panel_GestionCommandes.add(tabbedPane);
								
								table = new JTable();
								table.setSurrendersFocusOnKeystroke(true);
								table.setCellSelectionEnabled(true);
								table.setFillsViewportHeight(true);
								table.setColumnSelectionAllowed(true);
								table.setModel(new DefaultTableModel(
									new Object[][] {
										{c.getId_commande(), c.getId_client(c.getId_commande()), c.get_date(c.getId_commande())},
									
									},
									new String[] {
										"Id_commande", "id_client", "date"
									}
								) {
									/**
									 * 
									 */
									private static final long serialVersionUID = 1L;
									Class[] columnTypes = new Class[] {
										Long.class, String.class, Integer.class
									};
									public Class getColumnClass(int columnIndex) {
										return columnTypes[columnIndex];
									}
								});
								table.getColumnModel().getColumn(0).setPreferredWidth(84);
								table.getColumnModel().getColumn(1).setPreferredWidth(84);
								table.getColumnModel().getColumn(2).setPreferredWidth(84);
								tabbedPane.addTab("",null, table, null);
								
								JLabel lblIdcommande = new JLabel("Id_Commande");
								lblIdcommande.setForeground(Color.WHITE);
								lblIdcommande.setFont(new Font("Lucida Handwriting", Font.PLAIN, 15));
								lblIdcommande.setHorizontalAlignment(SwingConstants.CENTER);
								lblIdcommande.setBounds(269, 91, 158, 43);
								panel_GestionCommandes.add(lblIdcommande);
								
								JLabel lblIdclient = new JLabel("Id_Client");
								lblIdclient.setForeground(Color.WHITE);
								lblIdclient.setFont(new Font("Lucida Handwriting", Font.PLAIN, 15));
								lblIdclient.setHorizontalAlignment(SwingConstants.CENTER);
								lblIdclient.setBounds(423, 91, 158, 43);
								panel_GestionCommandes.add(lblIdclient);
								
								JLabel lblDate = new JLabel("Date");
								lblDate.setForeground(Color.WHITE);
								lblDate.setFont(new Font("Lucida Handwriting", Font.PLAIN, 15));
								lblDate.setHorizontalAlignment(SwingConstants.CENTER);
								lblDate.setBounds(580, 91, 158, 43);
								panel_GestionCommandes.add(lblDate);
								lblAjouter_1.addMouseListener(new MouseAdapter()  
								{  
								    public void mouseClicked(MouseEvent e) 
								    {  
								    	 Client user=new Client(textField_8.getText(),textField_4.getText(),passwordField_2.getText());
									       user.registerUser();
									       JOptionPane jop1 = new JOptionPane();
								        	jop1.showMessageDialog(null, "Client ajouté à la base de donnée", "Merci", JOptionPane.INFORMATION_MESSAGE);
									       
								    }	
								});
								
								//revenir à la page gestion depuis le panel GestionClient
								btnback_admin_1.addMouseListener(new MouseAdapter()  
								{  
								    public void mouseClicked(MouseEvent e) 
								    {  
								    	panel_admin.setVisible(false);
								    	panel_admin_1.setVisible(false);
								        panel_gestionClient.setVisible(false);
								        panel_admin_1.setVisible(true);
								    }	
								});
															 
															
										
			

								butsave.addMouseListener(new MouseAdapter()  
								{  
								    public void mouseClicked(MouseEvent e) 
								    {  //admin.getId_admin()==textField_1.getText() && admin.getNom_admin()==textField.getText() && admin.getMot_pass()==passwordField_1.getText()
								    	Admin admin = new Admin(textField_1.getText(), passwordField_1.getText());
								    	Admin admin2=new Admin();
								    	admin2=admin2.identifAdmin();
								    	if(admin2.getNom_admin().contentEquals(admin.getNom_admin())  && admin2.getMot_pass().contentEquals(admin.getMot_pass())) {
									        panel_admin.setVisible(false);
									        panel_admin_1.setVisible(true);
										}
										else {
											
											System.out.print("l'une des  informations entrées n'est pas validées .Merci d'essayer encore une fois");
										}
								    	
								    }
								    
									
								});
								
								

								//afficher la gestion des livre 
								lblGestionLivres.addMouseListener(new MouseAdapter()  
								{  
								    public void mouseClicked(MouseEvent e) 
								    {  
								    	panel_admin.setVisible(false);
								        panel_admin_1.setVisible(false);
								        panel_gestionLivres.setVisible(true);
								    }	
								});
								
								
								
								//afficher la page gestion clients
								lblGestionClient.addMouseListener(new MouseAdapter()  
								{  
								    public void mouseClicked(MouseEvent e) 
								    {  
								    	panel_admin.setVisible(false);
								        panel_gestionLivres.setVisible(false);
								        panel_admin_1.setVisible(false);
								        panel_gestionClient.setVisible(true);
								    }	
								});
								//accéder à la page gestion commandes
								lblGestionCommandes.addMouseListener(new MouseAdapter()  
								{  
								    public void mouseClicked(MouseEvent e) 
								    {  
								    	panel_admin.setVisible(false);
								        panel_gestionLivres.setVisible(false);
								        panel_admin_1.setVisible(false);
								        panel_GestionCommandes.setVisible(true);
								    }	
								});
				 
	}
}