import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.jdesktop.swingx.VerticalLayout;

import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import net.miginfocom.swing.MigLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class History extends JFrame {

	private JPanel contentPane;
	private static History frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					History frame = new History();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 * @throws ClassNotFoundException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SQLException 
	 */
	public History() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		
		frame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 588, 461);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(42, 64, 493, 291);
		contentPane.add(panel);
		panel.setLayout(new VerticalLayout());
		
		
		ResultSet res = new DatabaseConnector().querySQL("SELECT * FROM userHistory WHERE userHistory.User='"
				+ Login.user +"'");
		

		
		
		while(res.next()) {
			
			panel.add(new CreateJPanel(res.getString(1),res.getString(2),res.getString(3),res.getString(4)));
			
		}
        
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(42, 11, 493, 42);
		contentPane.add(panel_1);
		panel_1.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel_2 = new JLabel("Movie Name");
		panel_1.add(lblNewLabel_2);
		
		JLabel lblNewLabel = new JLabel("Ticket Number");
		panel_1.add(lblNewLabel);
		
		JLabel lblNewLabel_3 = new JLabel("Date");
		panel_1.add(lblNewLabel_3);
		
		JLabel lblNewLabel_1 = new JLabel("Time");
		panel_1.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Πίσω");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new MainScreen().setVisible(true);
				frame.dispose();
			}
		});
		btnNewButton.setBounds(244, 376, 89, 23);
		contentPane.add(btnNewButton);
		
	}
}
