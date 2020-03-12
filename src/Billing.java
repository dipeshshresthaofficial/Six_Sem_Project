import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Billing {
	
	private JFrame frame;
	int sum=0;
	private JTextField billAmountField;
	/**
	 * Launch the application.
	 * @param a2 
	 */
	public static void main(String[] a2, int[] a) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Billing window = new Billing(a2,a);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param a2 
	 */
	public Billing(String[] a2, int[] a) {
		initialize(a2,a);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param a2 
	 */
	private void initialize(String[] a2, int[] a	) {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 534, 466);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JTextArea itemList = new JTextArea();
		itemList.setBounds(10, 11, 498, 284);
		frame.getContentPane().add(itemList);
		
		JLabel billAmountLabel = new JLabel("Bill Amount:");
		billAmountLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		billAmountLabel.setForeground(Color.RED);
		billAmountLabel.setBackground(Color.ORANGE);
		billAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		billAmountLabel.setBounds(80, 323, 134, 38);
		frame.getContentPane().add(billAmountLabel);
		
		billAmountField = new JTextField();
		billAmountField.setBounds(244, 326, 216, 38);
		frame.getContentPane().add(billAmountField);
		billAmountField.setColumns(10);
		
//		converting integer to string
		for(int i=0;i<a2.length;i++) {
			
//			String str1 = Integer.toString(a2[i]);
			itemList.append(a2[i]+"\n");
			sum += a[i];
		}
		
		
		
		
//		System.out.println("a[0]:"+a[0]);
//		System.out.println("a[1]:"+a[1]);
		
		System.out.println("Total:"+sum);
		billAmountField.setText(Integer.toString(sum));
		
		JButton checkOutBtn = new JButton("Check Out");
		checkOutBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				Checkout c1= new Checkout("billCard",sum);
				c1.main("billCard",sum);
			}
		});
		checkOutBtn.setBounds(147, 393, 163, 23);
		frame.getContentPane().add(checkOutBtn);
	}
}
