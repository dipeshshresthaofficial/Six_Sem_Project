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
import java.time.LocalTime;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JSeparator;

public class Homes {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String username, String dbFname) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Homes window = new Homes(username,dbFname);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param dbFname 
	 * @param username 
	 */
	public Homes(String username, String dbFname) {
		initialize(username,dbFname);
	}

	

	/**
	 * Initialize the contents of the frame.
	 * @param dbFname 
	 * @param username 
	 */
	private void initialize(String username, String dbFname) {
		frame = new JFrame();
		frame.setBounds(100, 100, 504, 502);
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
		
		JMenu addMenu = new JMenu("Add");
		menuBar.add(addMenu);
		
		JMenuItem cardPaymentMenuItem = new JMenuItem("Card Payment");
		cardPaymentMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				Checkout c1 = new Checkout(username,0, dbFname);
				c1.main(username,0,dbFname);
			}
		});
		addMenu.add(cardPaymentMenuItem);
		
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
		paytmLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				try {
					  Desktop desktop = java.awt.Desktop.getDesktop();
					  URI oURL = new URI("http://dipesh.paytm.pay/orders/create");
					  desktop.browse(oURL);
					} catch (Exception e1) {
					  e1.printStackTrace();
					}
			}
		});
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
