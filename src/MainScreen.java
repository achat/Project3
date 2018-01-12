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
		
		JButton bookTicket = new JButton("\u039A\u03C1\u03AC\u03C4\u03B7\u03C3\u03B7 \u0395\u03B9\u03C3\u03B7\u03C4\u03B7\u03C1\u03AF\u03BF\u03C5");
		bookTicket.setForeground(Color.BLUE);
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
		
		JButton bookingHistory = new JButton("\u0399\u03C3\u03C4\u03BF\u03C1\u03B9\u03BA\u03CC \u039A\u03C1\u03B1\u03C4\u03AE\u03C3\u03B5\u03C9\u03BD");
		bookingHistory.setForeground(Color.BLUE);
		bookingHistory.setBounds(107, 120, 151, 23);
		contentPane.add(bookingHistory);
		
		JButton exit = new JButton("\u0391\u03C0\u03BF\u03C3\u03CD\u03BD\u03B4\u03B5\u03C3\u03B7");
		exit.setForeground(Color.BLUE);
		exit.setBounds(107, 180, 151, 23);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		contentPane.add(exit);
	}
}
