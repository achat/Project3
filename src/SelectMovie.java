import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicArrowButton;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelectMovie extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SelectMovie frame = new SelectMovie();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public SelectMovie() throws SQLException {
		
		int i=1;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 532);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ResultSet Movie = getFromDb("SELECT ImageUrl FROM Movies");
		Movie.next();
		BufferedImage image = LoadImage(Movie);
		ResultSet Screening = getFromDb("SELECT * FROM Screenings");
		Screening.next();
		
		JLabel icon = new JLabel();
		icon.setSize(new Dimension(388, 260));
		icon.setIcon(fitImage(image,icon));
		icon.setBounds(89, 40, 388, 260);
		contentPane.add(icon);
		
		
		
		JLabel name = new JLabel(Screening.getString(1),SwingConstants.CENTER);
		name.setFont(new Font("Tahoma", Font.PLAIN, 26));
		name.setBounds(89, 328, 388, 32);
		contentPane.add(name);
		
		JLabel time = new JLabel(Screening.getString(2));
		time.setBounds(27, 404, 104, 28);
		contentPane.add(time);
		
		JLabel date = new JLabel(Screening.getString(3));
		date.setBounds(230, 404, 113, 28);
		contentPane.add(date);
		
		JButton button = new JButton("Επιλογή Θέσεων");
		button.setBounds(398, 407, 131, 23);
		contentPane.add(button);
		
		JLabel label = new JLabel("Ώρα:");
		label.setBounds(27, 375, 104, 28);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("Ημερομηνία:");
		label_1.setBounds(230, 375, 104, 28);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Αίθουσα:");
		label_2.setBounds(27, 435, 104, 28);
		contentPane.add(label_2);
		
		JLabel hall = new JLabel(Screening.getString(4));
		hall.setBounds(27, 454, 104, 28);
		contentPane.add(hall);
		
		
		BasicArrowButton east = new BasicArrowButton(BasicArrowButton.EAST);
		east.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(Movie.next() && Screening.next() || !Movie.previous() && !Screening.previous()) {
						
						BufferedImage image = LoadImage(Movie);
						icon.setIcon(fitImage(image,icon));
						name.setText(Screening.getString(1));
						name.setHorizontalAlignment(SwingConstants.CENTER);
						time.setText(Screening.getString(2));
						date.setText(Screening.getString(3));
						hall.setText(Screening.getString(4));
						
					}
					
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
					
				}
			}
			
		});
		east.setBounds(487, 197, 69, 54);
		contentPane.add(east);
		
		BasicArrowButton west = new BasicArrowButton(BasicArrowButton.WEST);
		west.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if(Movie.previous() && Screening.previous() || !Movie.next() && !Screening.next()) {
						
						BufferedImage image = LoadImage(Movie);
						icon.setIcon(fitImage(image,icon));
						name.setText(Screening.getString(1));
						name.setHorizontalAlignment(SwingConstants.CENTER);
						time.setText(Screening.getString(2));
						date.setText(Screening.getString(3));
						hall.setText(Screening.getString(4));
						
					}
					
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
					
				}
			}
			
		});
		west.setBounds(10, 197, 69, 54);
		contentPane.add(west);

	}
	
	public ResultSet getFromDb(String cmd) throws SQLException {
		
		DatabaseConnector db = new DatabaseConnector();
		ResultSet rs = db.querySQL(cmd);
		return rs;
		
	}
	
	public BufferedImage LoadImage(ResultSet r) throws SQLException {

	    BufferedImage image = null;
	    URL url = null;
	    try {
	        url = new URL(r.getString(1));
	        image = ImageIO.read(url);
	    } catch (MalformedURLException ex) {
	        System.out.println("Malformed URL");
	    } catch (IOException iox) {
	        System.out.println("Can not load file");
	    }
	    return image;
	}
	
	
	public ImageIcon fitImage(BufferedImage image, JLabel icon) {
		
		Image dimg = image.getScaledInstance(icon.getWidth(), icon.getHeight(),
		        Image.SCALE_SMOOTH);
		ImageIcon imageicon = new ImageIcon(dimg);
		return imageicon;
	}
}
