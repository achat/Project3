import java.awt.GridLayout;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class CreateJPanel extends JPanel{
	
	public CreateJPanel(String name, String count, String time, String date) {
		
		
		this.setBounds(0, 11, 493, 45);
		
		this.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblNewLabel_5 = new JLabel(name);
		this.add(lblNewLabel_5);
		
		JLabel label = new JLabel(count);
		this.add(label);
		
		JLabel lblNewLabel_4 = new JLabel(date);
		this.add(lblNewLabel_4);
		
		JLabel lblNewLabel_6 = new JLabel(time);
		this.add(lblNewLabel_6);

	}

}
