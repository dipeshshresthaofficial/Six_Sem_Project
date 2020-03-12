import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JDesktopPane;
import javax.swing.JButton;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.ImageIcon;

import java.awt.Color;
import javax.swing.JInternalFrame;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Choice;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import java.awt.Button;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextPane;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.Font;

public class Dashboard {

	private JFrame frame;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard window = new Dashboard();
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
	public Dashboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(Color.BLACK);
		frame.setBounds(100, 100, 711, 497);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		Button qrScannerBtn = new Button("Start Scan");
		qrScannerBtn.setForeground(Color.RED);
		qrScannerBtn.setBackground(Color.WHITE);
		qrScannerBtn.setFont(new Font("Dialog", Font.PLAIN, 16));
		qrScannerBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
//CLOSE THE FRAME BECAUSE WITHOUT CLOSING THE FRAME THE WEBCAM WAS NOT STARTING
//				frame.dispose();
				
				Menu m1= new Menu();
				m1.main(null);
			}
		});
		qrScannerBtn.setBounds(304, 387, 98, 36);
		frame.getContentPane().add(qrScannerBtn);
		
		JLabel custImgLabel = new JLabel("");
		custImgLabel.setHorizontalAlignment(SwingConstants.CENTER);
		
//		ADDING IMAGE TO THIS LABEL
		Image img= new ImageIcon(this.getClass().getResource("/checkout.jpg ")).getImage();
//		Resizing the image height and width according to the size of label
		Image modifiedImg= img.getScaledInstance(491,305,java.awt.Image.SCALE_SMOOTH);
		
		custImgLabel.setIcon(new ImageIcon(modifiedImg));
		custImgLabel.setBounds(100, 76, 491, 305);
		frame.getContentPane().add(custImgLabel);
		
		JLabel lblNewLabel = new JLabel("Welcome to Lowes family!");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(114, 21, 477, 48);
		frame.getContentPane().add(lblNewLabel);
	}
}

