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
	int count=0;
	
	public SeatBooking(String name) throws HeadlessException, SQLException {
		super();
		this.this_name=name;
		
		frame = this;
	
		JPanel panel1, panel2; 
		
		
	
		title = new JLabel("Book your Seat");
		title.setFont(new Font("Helvetica", Font.BOLD, 20));
		title.setLocation(280,5);
		title.setSize(600, 60);

		frame.setLayout(null); // Setting Grid Layout
    // panel1.setLayout(new GridLayout(seat,row));
		l = new JButton[10][4]; // Allocating Size of Grid
		panel1 = new JPanel(new GridLayout(10,4));
		panel1.setBackground(Color.black);
		panel1.setBounds(20, 70, 230, 200);
		
		JButton kratisi = new JButton("Reserve Seats");
		kratisi.setBounds(290,180,130,20);
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
            				System.out.println(l[tempx][tempy].getText());
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
                			System.out.println(r[tempx][tempy].getText());
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

		JLabel j = new JLabel("Screen");
		j.setFont(new Font("Helvetica", Font.BOLD, 30));
		j.setBounds(310,330,150,150);
		
		
		ResultSet res = new DatabaseConnector().querySQL("SELECT `Time`, `Date` FROM `Screenings` "
				+ "WHERE Screenings.Name='" + this_name + "'");
		res.next();
    
		
		kratisi.addActionListener(new ActionListener() {
    		@Override
    		public void actionPerformed(ActionEvent e) {
    			for(int k=0;k<tempSeats.size();k++) {
					int rs = new DatabaseConnector().updatequerySQL("INSERT INTO `Seats " + this_name + "` (Seats) " + 
						"VALUES ('"+tempSeats.get(k)+"')");
					count++;
    			}
    			
    			for(int k=0;k<tempSeats.size();k++)
    				tempSeats.remove(k);
    			
    			try {
					int rs = new DatabaseConnector().updatequerySQL("INSERT INTO `userHistory` "
							+ "(`MovieName`, `TicketNumber`, `Time`, `Date`, `User`) VALUES ('" +this_name+ "', '"
							+ Integer.toString(count) + "', '" +res.getString(1)+ "', '" + res.getString(2) + "', '"+ Login.user +"')")  ;
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    			
    			count =0;
    			int confirmed = JOptionPane.showOptionDialog(null, "Seat Booking completed succesfully!", "", JOptionPane.DEFAULT_OPTION,
    			        JOptionPane.INFORMATION_MESSAGE, null, null, null);
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
		frame.setTitle("Seat Booking");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack(); //sets appropriate size for frame
		frame.setVisible(true); //makes frame visible
	}
}
