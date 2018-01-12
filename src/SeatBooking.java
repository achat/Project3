import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class SeatBooking extends JFrame {

//	JFrame frame = new JFrame(); // Creates JFrame
	private static SeatBooking frame;
	JLabel title;
	JButton l[][], r[][]; // Names grid of JButtons
	String this_name;
	ArrayList<String> tempSeats = new ArrayList<String>();
	
	public SeatBooking(String name) throws HeadlessException {
		super();
		this.this_name=name;
		
		frame = this;
	
		JPanel panel1, panel2; 
	
		System.out.println(this_name);
		title = new JLabel("Ξ�Ο�Ξ¬Ο„Ξ·ΟƒΞ· Ξ�Ξ­ΟƒΞ·Ο‚");
		title.setFont(new Font("Helvetica", Font.BOLD, 20));
		title.setLocation(280,5);
		title.setSize(600, 60);

		frame.setLayout(null); // Setting Grid Layout
    // panel1.setLayout(new GridLayout(seat,row));
		l = new JButton[10][4]; // Allocating Size of Grid
		panel1 = new JPanel(new GridLayout(10,4));
		panel1.setBackground(Color.black);
		panel1.setBounds(20, 70, 230, 200);
		
		JButton kratisi = new JButton("Ξ�Ο�Ξ¬Ο„Ξ·ΟƒΞ·");
		kratisi.setBounds(305,180,100,20);
		kratisi.setEnabled(false);
		

		for(int y = 0; y <4 ; y++){
            	for(int x = 0; x < 10; x++){
            		l[x][y] = new JButton("L" + y + x); // Creates New JButton
            		final int tempx = x;
            		final int tempy = y;
            		// l[x][y].addActionListener(this);
            		l[x][y].addActionListener(new ActionListener() {
            			@Override
            			public void actionPerformed(ActionEvent e) {
            				kratisi.setEnabled(true);
            				l[tempx][tempy].setEnabled(false);
            				tempSeats.add(l[tempx][tempy].getText());
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
                			kratisi.setEnabled(true);
            				r[tempx][tempy].setEnabled(false);
                			tempSeats.add(r[tempx][tempy].getText());
                		}
                	});
                	panel2.add(r[x][y]); //adds button to grid
            	}
		}
		
		
		
		try {
			ResultSet rs = new DatabaseConnector().querySQL("SELECT * FROM `Seats "+this_name+"`");
			while(rs.next()) {
				
				String seat = rs.getString(1);
				String[] parts = seat.split("");
				String side = parts[0];
				String x = parts[1];
				String y = parts[2];
				
				if(side.equals("L")) 
					l[Integer.parseInt(y)][Integer.parseInt(x)].setEnabled(false);
				else if(side.equals("R"))
					r[Integer.parseInt(y)][Integer.parseInt(x)].setEnabled(false);
					
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		JLabel j = new JLabel("Ξ�ΞΈΟ�Ξ½Ξ·");
		j.setFont(new Font("Helvetica", Font.BOLD, 30));
		j.setBounds(310,350,100,100);
    
		
		kratisi.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			for(int k=0;k<tempSeats.size();k++) {
					int rs = new DatabaseConnector().updatequerySQL("INSERT INTO `Seats " + this_name + "` (Seats) " + 
						"VALUES ('"+tempSeats.get(k)+"')");
					tempSeats.remove(k);
    			}
    			int confirmed = JOptionPane.showOptionDialog(null, "Ξ— ΞΊΟ�Ξ¬Ο„Ξ·ΟƒΞ· Ο€Ο�Ξ±Ξ³ΞΌΞ±Ο„ΞΏΟ€ΞΏΞΉΞ®ΞΈΞ·ΞΊΞµ ΞµΟ€ΞΉΟ„Ο…Ο‡Ο�Ο‚!", "", JOptionPane.DEFAULT_OPTION,
    			        JOptionPane.INFORMATION_MESSAGE, null, null, null);
    			System.out.println(confirmed);
//    			if (confirmed == 0 || confirmed == -1) {
//    			     new SeatBooking(null).dispose();
//    			    }
    			new MainScreen().setVisible(true);
    			frame.dispose();
    		}
    	});
    
		frame.add(title);
		frame.add(panel1);
		frame.add(j);
		frame.add(kratisi);
		frame.add(panel2);
		frame.setBounds(100, 100, 582, 532);
		frame.setPreferredSize(new Dimension(730, 470));
		frame.setTitle("Ξ•Ο€ΞΉΞ»ΞΏΞ³Ξ® Ξ�Ξ­ΟƒΞ·Ο‚");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack(); //sets appropriate size for frame
		frame.setVisible(true); //makes frame visible
	}
}
