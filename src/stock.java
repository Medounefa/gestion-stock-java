import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import net.proteanit.sql.DbUtils;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class stock {

	
	private JFrame frame;
	private JTextField nomprod;
	private JTextField qt;
	private JTextField fournisseur;
	private JTextField search;
	private JTable table;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					stock window = new stock();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public stock() {
		initialize();
		connect();
		table_load();
	}
	Connection con;
	PreparedStatement pst;
    ResultSet rs;
   
	
	
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/gestionstock", "root", "");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
		} catch(SQLException e) {
			
		}
	}

	
	public void table_load() {
		try {
			pst = con.prepareStatement("select * from produit");
			rs = pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 200, 850, 505);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Gestion de stock");
		lblNewLabel.setFont(new Font("Tahoma", Font.ITALIC, 40));
		lblNewLabel.setBounds(215, 11, 315, 35);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Nom produit");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(42, 161, 103, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		nomprod = new JTextField();
		nomprod.setColumns(10);
		nomprod.setBounds(155, 157, 190, 27);
		frame.getContentPane().add(nomprod);
		
		JLabel lblNewLabel_3 = new JLabel("Quantite");
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_3.setBounds(42, 235, 103, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		qt = new JTextField();
		qt.setColumns(10);
		qt.setBounds(155, 231, 190, 27);
		frame.getContentPane().add(qt);
		
		JLabel lblNewLabel_4 = new JLabel("Fournisseur");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_4.setBounds(42, 299, 103, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		fournisseur = new JTextField();
		fournisseur.setColumns(10);
		fournisseur.setBounds(155, 295, 190, 27);
		frame.getContentPane().add(fournisseur);
		
		JButton valide = new JButton("Valider");
		valide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String  nom, quantite, fourni;
				
				
				nom = nomprod.getText();
				quantite = qt.getText();
				fourni = fournisseur.getText();
				
				try {
					
					pst = con.prepareStatement("insert into produit( nom, quantite, fournisseur) values ( ?, ?, ?)");
					
					pst.setString(1, nom);
					pst.setString(2, quantite);
					pst.setString(3, fourni);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Insertion reussite");
					table_load();
					
					nomprod.setText("");
					qt.setText("");
					fournisseur.setText("");
					
					
				}catch(SQLException e) {
					e.printStackTrace();
				}
				
			}
		});
		valide.setFont(new Font("Tahoma", Font.BOLD, 15));
		valide.setBounds(256, 339, 89, 23);
		frame.getContentPane().add(valide);
		
		JLabel lblNewLabel_5 = new JLabel("Recherche Produit");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_5.setBounds(42, 408, 150, 14);
		frame.getContentPane().add(lblNewLabel_5);
		
		search = new JTextField();
		search.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				
				try {
					String idp ;
					idp = search.getText();
					
					pst = con.prepareStatement("select nom, quantite, fournisseur from produit where id = ?");
					pst.setString(1, idp);
					ResultSet rs = pst.executeQuery();
					if(rs.next()== true) {
						String name = rs.getString(1);
						String  qtt= rs.getString(2);
						String fournis = rs.getString(3);
						
						nomprod.setText(name);
						qt.setText(qtt);
						fournisseur.setText(fournis);
					}
					
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		});
		search.setBounds(202, 407, 143, 20);
		frame.getContentPane().add(search);
		search.setColumns(10);
		
		JButton modifier = new JButton("Modifier");
		modifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
               String  nom, quantite, fourni, prodId;
				
				
				nom = nomprod.getText();
				quantite = qt.getText();
				fourni = fournisseur.getText();
				prodId = search.getText();
				
				try {
					
					pst = con.prepareStatement("update produit set nom= ?, quantite =?, fournisseur = ? where id = ?");
					
					pst.setString(1, nom);
					pst.setString(2, quantite);
					pst.setString(3, fourni);
					pst.setString(4, prodId);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Insertion reussite");
					table_load();
					
					nomprod.setText("");
					qt.setText("");
					fournisseur.setText("");
					
					
				}catch(SQLException e1) {
					e1.printStackTrace();
				}
				
				
				
				
			}
		});
		modifier.setFont(new Font("Tahoma", Font.BOLD, 15));
		modifier.setBounds(355, 406, 111, 23);
		frame.getContentPane().add(modifier);
		
		JButton btnNewButton = new JButton("Effacer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				nomprod.setText(null);
				qt.setText(null);
				fournisseur.setText(null);
				
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(540, 404, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton sup = new JButton("supprimer");
		sup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 String idpro;
				 idpro = search.getText();
				 
				try {
					pst = con.prepareStatement("delete from produit where id= ? ");
					pst.setString(1, idpro);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record delete");
					table_load();
					nomprod.setText("");
					qt.setText("");
					fournisseur.setText("");
				    
					
				}catch(SQLException ex) {
					ex.printStackTrace();
				}
				
			}
		});
		sup.setFont(new Font("Tahoma", Font.BOLD, 15));
		sup.setBounds(694, 406, 111, 23);
		frame.getContentPane().add(sup);
		
		JScrollPane table_1 = new JScrollPane();
		table_1.setBounds(367, 84, 438, 288);
		frame.getContentPane().add(table_1);
		
		table = new JTable();
		table_1.setViewportView(table);
	}

	public void setVisible(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
