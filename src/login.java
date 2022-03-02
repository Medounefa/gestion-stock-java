import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;

import net.proteanit.sql.DbUtils;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login window = new login();
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
	public login() {
		initialize();
		connect();
	}
	
	Connection con;
	PreparedStatement pst;
    ResultSet rs;
    private JTextField username;
    private JTextField password;
   
	
	
	public void connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/gestionstock", "root", "");
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
		} catch(SQLException e) {
			
		}
	}
//	public void table_load() {
//		try {
//			pst = con.prepareStatement("select user,pass from utilisateur where id ?");
//			rs = pst.executeQuery();
//
//			
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel.setBounds(172, 11, 82, 24);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_1.setBounds(34, 60, 96, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewLabel_2.setBounds(34, 108, 96, 24);
		frame.getContentPane().add(lblNewLabel_2);
		
		username = new JTextField();
		username.setBounds(172, 59, 185, 24);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(172, 112, 185, 24);
		frame.getContentPane().add(password);
		
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					String users, mdp;
					users = username.getText();
					mdp = password.getText();
					
					try {
						pst = con.prepareStatement("select user,pass from utilisateur where id ?");
						pst.setString(1, users);
						pst.setString(2, mdp);
						rs = pst.executeQuery();
						ResultSet rs = pst.executeQuery();
						if(rs.next()== true) {
							String u = rs.getString(1);
							String  p = rs.getString(2);
	
							
							username.setText(p);
							password.setText(u);
							
						}  
						}
						

						
					} catch (SQLException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}
				    
			}
		});
		login.setFont(new Font("Tahoma", Font.BOLD, 15));
		login.setBounds(189, 180, 89, 23);
		frame.getContentPane().add(login);
	}

}
