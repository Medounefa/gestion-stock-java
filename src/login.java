import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class login {

	private JFrame frame;
	private JTextField username;
	private JTextField password;

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
	}

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
		lblNewLabel.setBounds(190, 11, 60, 19);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_1.setBounds(33, 68, 87, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		username = new JTextField();
		username.setBounds(151, 66, 151, 19);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Password");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNewLabel_2.setBounds(33, 115, 87, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		password = new JTextField();
		password.setColumns(10);
		password.setBounds(151, 113, 151, 19);
		frame.getContentPane().add(password);
		
		JButton login = new JButton("Login");
		login.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stock obj = new stock();
				String users , pass;
				users = username.getText();
				pass = password.getText();
				
				if(users.equals("medoune") && pass.equals("fall")) {
					
					obj.setVisible(true);
//					this.dispose();
					
					
				}else {
					JOptionPane.showMessageDialog(null, "User or pass incorrect");;
				}
			}

//			private void dispose() {
//				// TODO Auto-generated method stub
//				
//			}
//
//			private void setVisible(boolean b) {
//				// TODO Auto-generated method stub
//				
//			}
		});
		login.setFont(new Font("Tahoma", Font.BOLD, 15));
		login.setBounds(173, 171, 89, 23);
		frame.getContentPane().add(login);
	}

}
