import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.border.Border;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalTime;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Homes extends Login{
	
	public static String dbFname;
	public static String username;
	public static String cardForAdding= "cardForAdding";
	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					Homes window1 = new Homes();
					System.out.println(window1.temp);
					window1.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Homes() {
		
		initialize();
		
	}

	

	/**
	 * Initialize the contents of the frame.
	 * @param dbFname 
	 * @param username 
	 */
	private void initialize() {
		
		System.out.println("hello"+this.temp);
		dbFname= this.temp;
		username= this.temp;
		frame = new JFrame();
		frame.setBounds(100, 100, 504, 495);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
//		Icon icon =new ImageIcon(".image/lowes.png");
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 152, 34);
		frame.getContentPane().add(menuBar);
		
		JMenu checkoutMenu = new JMenu("Checkout");
		menuBar.add(checkoutMenu);
		
		
		JMenuItem kmartMenuItem = new JMenuItem("K-Mart");
		kmartMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
						
						
						Dashboard d1 = new Dashboard();
						d1.main(null);
						
			}
		
		
		});
		checkoutMenu.add(kmartMenuItem);
		
		JMenuItem moreMenuItem = new JMenuItem("More Mega Store");
		moreMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Dashboard m2 = new Dashboard();
				m2.main(null);
			}
		});
		checkoutMenu.add(moreMenuItem);
		
		JMenu addMenu = new JMenu("Options");
		menuBar.add(addMenu);
		
		JMenuItem cardPaymentMenuItem = new JMenuItem("Add Card");
		cardPaymentMenuItem.addMouseListener(new MouseAdapter() {
			private char[] temp;

			@Override
			public void mousePressed(MouseEvent e) {
				
				
				
//				System.out.println(this.temp);
				Checkout c1 = new Checkout(cardForAdding,0, dbFname);
				c1.main(cardForAdding,0,dbFname);
			}
		});
		

		addMenu.add(cardPaymentMenuItem);
		
		JMenuItem transactionMenu = new JMenuItem("View Transactions");
		transactionMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				System.out.println("Showing Transactions");
				TransactionRecords t = new TransactionRecords(username,"transactions");
			}
		});
		
		addMenu.add(transactionMenu);
		
		JMenuItem orderMenu = new JMenuItem("View Orders");
		orderMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("Showing Orders");
				TransactionRecords t = new TransactionRecords(username,"orders");
			}
		});
		
		addMenu.add(orderMenu);
		
		
		
		
		JLabel profileLabelBtn = new JLabel("");
		profileLabelBtn.setHorizontalAlignment(SwingConstants.CENTER);
		Image img = new ImageIcon(this.getClass().getResource("/profile1.png")).getImage();
		Image modifiedImg = img.getScaledInstance(64, 64, java.awt.Image.SCALE_SMOOTH);
		profileLabelBtn.setIcon(new ImageIcon(modifiedImg));
		profileLabelBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				System.out.println("HEllo");
			}
		});
		profileLabelBtn.setBounds(210, 129, 64, 64);
		frame.getContentPane().add(profileLabelBtn);
		
		JLabel paytmLabel = new JLabel("");
		
		paytmLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
		Image img1 = new ImageIcon(this.getClass().getResource("/paytm-logo.png")).getImage();
		Image modifiedImg1 = img1.getScaledInstance(200, 220, java.awt.Image.SCALE_SMOOTH);
		paytmLabel.setIcon(new ImageIcon(modifiedImg1));
		paytmLabel.setBounds(174, 332, 180, 87);
		frame.getContentPane().add(paytmLabel);
		
		JLabel transferMoneyLabel = new JLabel("Transfer Money");
		transferMoneyLabel.setHorizontalAlignment(SwingConstants.CENTER);
		transferMoneyLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		transferMoneyLabel.setBounds(164, 262, 180, 25);
		frame.getContentPane().add(transferMoneyLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(0, 262, 488, 2);
		frame.getContentPane().add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(0, 287, 488, 2);
		frame.getContentPane().add(separator_1);
		
		
		
		
		
		
		
		LocalTime time= LocalTime.now();
		String time1 = time.toString();
		String[] time2= time1.split(":");
		int cur_time = Integer.parseInt(time2[0]);
		
		if(cur_time>=0 && cur_time<12) {
			
			JLabel greetingLabel = new JLabel("Good Morning, "+dbFname+"!");
			greetingLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			greetingLabel.setHorizontalAlignment(SwingConstants.CENTER);
			greetingLabel.setBounds(160, 70, 199, 36);
			frame.getContentPane().add(greetingLabel);
		}
		else if(cur_time>=12 && cur_time<17){
			
			JLabel greetingLabel = new JLabel("Good Afternoon, "+dbFname+"!");
			greetingLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			greetingLabel.setHorizontalAlignment(SwingConstants.CENTER);
			greetingLabel.setBounds(160, 70, 199, 36);
			frame.getContentPane().add(greetingLabel);
		}
		else {
			
			JLabel greetingLabel = new JLabel("Good Evening, "+dbFname+"!");
			greetingLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			greetingLabel.setHorizontalAlignment(SwingConstants.CENTER);
			greetingLabel.setBounds(160, 70, 199, 36);
			frame.getContentPane().add(greetingLabel);
		}
		
		
	
		
		
	}
}
