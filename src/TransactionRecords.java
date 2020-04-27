import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TransactionRecords {

	private JFrame frame;
	PreparedStatement prepStmt=null;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String username, String record) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransactionRecords window = new TransactionRecords(username,record);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param username 
	 * @param record 
	 */
	public TransactionRecords(String username, String record) {
		initialize(username, record);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String username, String record) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		
		String transRecord[][]= new String[100][100];
//		String column[]= new String[100];
		int i=0;
		
		if(record.equalsIgnoreCase("transactions")) {
			
			try {
				
				
				Class.forName("org.sqlite.JDBC");  
				Connection con=DriverManager.getConnection( 
				"jdbc:sqlite:./Database/lowes.db");  
				//here dipesh is database name, root is username and password  
				Statement stmt=con.createStatement();  
				
				String select="select * from transaction_record where Customer_id=?";
				prepStmt = con.prepareStatement(select);
				prepStmt.setString(1,username);
				ResultSet rs2=prepStmt.executeQuery();
				
				while(rs2.next())
				{
					transRecord[i][0]=rs2.getString("T_id").toString();
					transRecord[i][1] = rs2.getString("T_amount").toString();
					transRecord[i][2]=rs2.getString("Payment_mode").toString();
					transRecord[i][3]=rs2.getString("T_date").toString();
					transRecord[i][4]=rs2.getString("T_time").toString();
					transRecord[i][5]= rs2.getString("Status").toString();
					transRecord[i][6]= rs2.getString("Order_no").toString();
					i++;
					
				}
				
				con.close();
			}catch(Exception e1) {
				
				System.out.println(e1);
			}
				
			
			String column[]= {"Trans ID","Trans Amount","Payment Mode","Trans Date","Trans Time","Status","Order No"};
			table = new JTable(transRecord,column);
			table.setBounds(30,100,200,300);
			frame.getContentPane().add(table);
			
			JScrollPane sp = new JScrollPane(table); 
	        frame.add(sp); 

			frame.setSize(800,400);
			frame.setVisible(true);
		}
		
		else if(record.equalsIgnoreCase("orders")) {
			
			try {
				
				Class.forName("org.sqlite.JDBC");  
				Connection con=DriverManager.getConnection( 
				"jdbc:sqlite:./Database/lowes.db");  
				//here dipesh is database name, root is username and password  
				Statement stmt=con.createStatement();  
				
				String select="select * from orders where Customer_id=?";
				prepStmt = con.prepareStatement(select);
				prepStmt.setString(1,username);
				ResultSet rs2=prepStmt.executeQuery();
				
				while(rs2.next())
				{
					transRecord[i][0]=rs2.getString("Order_no").toString();
					transRecord[i][1]=rs2.getString("Item_id").toString();
					transRecord[i][2]=rs2.getString("Item_name").toString();
					transRecord[i][3]=rs2.getString("Qty").toString();
					transRecord[i][4]=rs2.getString("Price").toString();
					i++;
					
				}
				
				con.close();
			}catch(Exception e1) {
				
				System.out.println(e1);
			}
			finally {
				
			}
			


			String column[]= {"Order No","Item ID","Item Name","Quantity","Price"};
				
			table = new JTable(transRecord,column);
			table.setBounds(30,100,200,300);
			frame.getContentPane().add(table);
			
			JScrollPane sp = new JScrollPane(table); 
	        frame.add(sp); 

			frame.setSize(800,400);
			frame.setVisible(true);
		}
		
		
	}

}
