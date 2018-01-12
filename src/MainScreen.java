import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.Color;

public class MainScreen extends JFrame {

	private JPanel contentPane;
	private static MainScreen frame;

	/**
	 * Launch the application.
	 */
//	public static void mainScreen() {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainScreen frame = new MainScreen();
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
	public MainScreen() {
		frame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 400, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton bookTicket = new JButton("Book a ticket now");
		bookTicket.setForeground(Color.BLACK);
		bookTicket.setBounds(107, 60, 151, 23);
		bookTicket.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new SelectMovie().setVisible(true);
					frame.dispose();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		contentPane.add(bookTicket);
		
		JButton bookingHistory = new JButton("Tickets History");
		bookingHistory.setForeground(Color.BLACK);
		bookingHistory.setBounds(107, 120, 151, 23);
		bookingHistory.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new History().setVisible(true);
					frame.dispose();
				} catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		contentPane.add(bookingHistory);
		
		JButton exit = new JButton("Exit");
		exit.setForeground(Color.BLACK);
		exit.setBounds(107, 180, 151, 23);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Login().setVisible(true);		
				frame.dispose();
			}
		});
		contentPane.add(exit);
	}
}
