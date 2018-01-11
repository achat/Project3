import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class SeatBooking extends JFrame {

	JFrame frame = new JFrame(); // Creates JFrame
	JLabel title;
	JButton l[][], r[][]; // Names grid of JButtons
	JPanel panel1, panel2; 
	public SeatBooking() {
		title = new JLabel("Κράτηση Θέσης");
		title.setFont(new Font("Helvetica", Font.BOLD, 20));
		title.setLocation(280,5);
		title.setSize(600, 60);

		frame.setLayout(null); // Setting Grid Layout
    // panel1.setLayout(new GridLayout(seat,row));
		l = new JButton[10][4]; // Allocating Size of Grid
		panel1 = new JPanel(new GridLayout(10,4));
		panel1.setBackground(Color.black);
		panel1.setBounds(20, 70, 230, 200);
		for(int y = 0; y <4 ; y++){
            	for(int x = 0; x < 10; x++){
            		l[x][y] = new JButton("L" + y + x); // Creates New JButton
            		final int tempx = x;
            		final int tempy = y;
            		// l[x][y].addActionListener(this);
            		l[x][y].addActionListener(new ActionListener() {
            			@Override
            			public void actionPerformed(ActionEvent e) {
            				l[tempx][tempy].setEnabled(false);
            			}
            		});
            		panel1.add(l[x][y]); //adds button to grid
            	}
		}

		r = new JButton[10][4]; // Allocating Size of Grid
		panel2 = new JPanel(new GridLayout(10,4));
		panel2.setBackground(Color.black);
		panel2.setBounds(460, 70, 230, 200);
		for(int y = 0; y <4 ; y++){
            	for(int x = 0; x < 10; x++){
            		r[x][y] = new JButton("R" + y + x); // Creates New JButton
                	final int tempx = x;
                	final int tempy = y;
                	r[x][y].addActionListener(new ActionListener() {
                		@Override
                		public void actionPerformed(ActionEvent e) {
                			r[tempx][tempy].setEnabled(false);        
                		}
                	});
                	panel2.add(r[x][y]); //adds button to grid
            	}
		}

		JLabel j = new JLabel("Οθόνη");
		j.setFont(new Font("Helvetica", Font.BOLD, 30));
		j.setBounds(310,350,100,100);
    
		JButton kratisi = new JButton("Κράτηση");
		kratisi.setBounds(305,180,100,20);
    
		frame.add(title);
		frame.add(panel1);
		frame.add(j);
		frame.add(kratisi);
		frame.add(panel2);
		frame.setBounds(100, 100, 582, 532);
		frame.setPreferredSize(new Dimension(730, 470));
		frame.setTitle("Επιλογή Θέσης");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack(); //sets appropriate size for frame
		frame.setVisible(true); //makes frame visible
	}
}
