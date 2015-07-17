package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import java.awt.Font;

public class UserInterface {

	private JFrame frame;
	private JComboBox CB_NORuns;
	private JComboBox CB_Method;
	private JCheckBox ChBox_regions;
	private JTextField TF_DRatio;
	private JTextField TF_PRatio;
	private JTextField TF_NOPassengers;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
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
	public UserInterface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton B_Run = new JButton("Run");
		B_Run.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RunActionPerformed(arg0);
			}
		});
		B_Run.setBounds(184, 307, 135, 43);
		frame.getContentPane().add(B_Run);

		JButton B_Sample = new JButton("Sample");
		B_Sample.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		B_Sample.setBounds(10, 307, 135, 43);
		frame.getContentPane().add(B_Sample);

		CB_NORuns = new JComboBox();
		CB_NORuns.setModel(new DefaultComboBoxModel(new String[] {"30", "60", "90", "120", "150"}));
		CB_NORuns.setBounds(233, 11, 93, 20);
		frame.getContentPane().add(CB_NORuns);

		JLabel L_NumberOfRuns = new JLabel("Number of Runs");
		L_NumberOfRuns.setLabelFor(CB_NORuns);
		L_NumberOfRuns.setBounds(35, 14, 117, 17);
		frame.getContentPane().add(L_NumberOfRuns);

		JLabel L_Method = new JLabel("Method");
		L_Method.setBounds(35, 47, 117, 20);
		frame.getContentPane().add(L_Method);

		CB_Method = new JComboBox();
		L_Method.setLabelFor(CB_Method);
		CB_Method.setModel(new DefaultComboBoxModel(new String[] {"Naive", "Static", "Dynamic", "All"}));
		CB_Method.setBounds(233, 47, 93, 20);
		frame.getContentPane().add(CB_Method);
		
		JLabel L_Sample = new JLabel("Only One Run,\r\nFor Examples");
		L_Sample.setLabelFor(B_Sample);
		L_Sample.setBounds(10, 268, 135, 28);
		frame.getContentPane().add(L_Sample);
		
		JLabel L_Run1 = new JLabel("Tests from 10 to the number");
		L_Run1.setLabelFor(B_Run);
		L_Run1.setBounds(184, 253, 142, 28);
		frame.getContentPane().add(L_Run1);
		
		JLabel L_Run2 = new JLabel("of drivers and passengers");
		L_Run2.setLabelFor(B_Run);
		L_Run2.setBounds(184, 282, 142, 20);
		frame.getContentPane().add(L_Run2);
		
		ChBox_regions = new JCheckBox("Regions");
		ChBox_regions.setBounds(35, 79, 97, 23);
		frame.getContentPane().add(ChBox_regions);
		
		JLabel L_Ratio1 = new JLabel("Ratio Of");
		L_Ratio1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		L_Ratio1.setBounds(35, 148, 57, 28);
		frame.getContentPane().add(L_Ratio1);
		
		TF_DRatio = new JTextField();
		L_Ratio1.setLabelFor(TF_DRatio);
		TF_DRatio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if( !(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE )
						e.consume();
			}
		});
		TF_DRatio.setBounds(86, 152, 40, 20);
		frame.getContentPane().add(TF_DRatio);
		TF_DRatio.setColumns(10);
		
		JLabel L_Ratio2 = new JLabel(":");
		L_Ratio2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		L_Ratio2.setBounds(133, 155, 12, 14);
		frame.getContentPane().add(L_Ratio2);
		
		TF_PRatio = new JTextField();
		L_Ratio2.setLabelFor(TF_PRatio);
		TF_PRatio.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if( !(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE )
					e.consume();
			}
		});
		TF_PRatio.setBounds(146, 152, 40, 20);
		frame.getContentPane().add(TF_PRatio);
		TF_PRatio.setColumns(10);
		
		JLabel L_Ratio3 = new JLabel("Between Drivers , Passengers");
		L_Ratio3.setLabelFor(TF_PRatio);
		L_Ratio3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		L_Ratio3.setBounds(196, 148, 188, 28);
		frame.getContentPane().add(L_Ratio3);
		
		JLabel L_NOPassengers = new JLabel("Number of Passengers");
		L_NOPassengers.setBounds(35, 109, 151, 21);
		frame.getContentPane().add(L_NOPassengers);
		
		TF_NOPassengers = new JTextField();
		L_NOPassengers.setLabelFor(TF_NOPassengers);
		TF_NOPassengers.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if( !(Character.isDigit(c)) || (c == KeyEvent.VK_BACK_SPACE) || c == KeyEvent.VK_DELETE )
					e.consume();
			}
		});
		TF_NOPassengers.setBounds(233, 109, 86, 20);
		frame.getContentPane().add(TF_NOPassengers);
		TF_NOPassengers.setColumns(10);

	}

	protected void RunActionPerformed(ActionEvent ev) {
		String method = (String) CB_Method.getSelectedItem();
		int runs = Integer.parseInt((String) CB_NORuns.getSelectedItem());
		int numPassenger = Integer.parseInt(TF_NOPassengers.getText());
		int passengersInCar = Integer.parseInt(TF_PRatio.getText());
		boolean areas = ChBox_regions.isSelected();
		System.out.println(method +"," + runs +"," + numPassenger +"," + passengersInCar +"," + areas);
		try {
			Graphs g = new Graphs(runs,numPassenger);
			g.Run(method,passengersInCar,areas);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
