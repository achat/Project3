import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.Statement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import java.awt.Font;

public class Login extends JFrame {

	private JPanel loginPane;
	private JPanel mainScreenPane;
	private JTextField username;
	private JTextField password;
	private static Login frame;
	private JLabel logo;
	ResultSet rs;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					frame = new GUILogin();
//					frame.setVisible(true);
//					frame.setTitle("BookCinema");
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public Login() {
		frame = this;	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		loginPane = new JPanel();
		loginPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(loginPane);
		loginPane.setLayout(null);
		
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setBounds(74, 91, 86, 14);
		loginPane.add(usernameLabel);
		
		username = new JTextField();
		username.setBounds(170, 88, 86, 20);
		loginPane.add(username);
		username.setColumns(10);
				
		JButton loginButton = new JButton("Login");
		loginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
					String user = username.getText().trim();
					String sqlQuery = "SELECT username FROM users WHERE username = '"+user+"'";
					if (!user.isEmpty()){
						rs = new DatabaseConnector().querySQL(sqlQuery);
						
						int count = 0;
						while(rs.next()){
							count = count + 1;
						}

						if (count == 1){
							JOptionPane.showMessageDialog(null, "Welcome "+user);

							MainScreen ms = new MainScreen();
							ms.setVisible(true);
							frame.dispose();				
						}
						else if (count > 1){
							JOptionPane.showMessageDialog(null, "Duplicate User!Can not access!");	
							System.exit(0);
						}
						else {
							String insertQuery = "INSERT INTO users (username) VALUES('"+user+"')";
							int rd = new DatabaseConnector().updatequerySQL(insertQuery);

							JOptionPane.showMessageDialog(null, "User created! Welcome "+user);
							MainScreen ms = new MainScreen();
							ms.setVisible(true);
							frame.dispose();				
						}
					}					
				}
				catch(Exception ex){
					
				}
			}
		});
		loginButton.setBounds(170, 144, 86, 23);
		loginPane.add(loginButton);
		
		logo = new JLabel("BookCinema");
		logo.setFont(new Font("Tahoma", Font.BOLD, 14));
		logo.setBounds(0, 0, 375, 40);
		logo.setHorizontalAlignment(SwingConstants.CENTER);
		loginPane.add(logo);
		
	}
}
