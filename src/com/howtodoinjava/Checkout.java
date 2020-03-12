package com.howtodoinjava;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Checkout {

	private JFrame frame;
	private JTextField cardNoField;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Checkout window = new Checkout();
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
	public Checkout() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 605, 379);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel cardNoLabel = new JLabel("Card Number");
		cardNoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cardNoLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cardNoLabel.setBounds(39, 34, 104, 26);
		frame.getContentPane().add(cardNoLabel);
		
		cardNoField = new JTextField();
		cardNoField.setBounds(153, 33, 366, 26);
		frame.getContentPane().add(cardNoField);
		cardNoField.setColumns(10);
		
		JLabel expDateLabel = new JLabel("Exp. Date");
		expDateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		expDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		expDateLabel.setBounds(39, 106, 104, 26);
		frame.getContentPane().add(expDateLabel);
		
		textField = new JTextField();
		textField.setBounds(153, 108, 141, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel cvvLabel = new JLabel("CVV");
		cvvLabel.setHorizontalAlignment(SwingConstants.CENTER);
		cvvLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cvvLabel.setBounds(362, 106, 49, 26);
		frame.getContentPane().add(cvvLabel);
		
		textField_1 = new JTextField();
		textField_1.setBounds(422, 108, 97, 26);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JButton confirmBtn = new JButton("Confirm");
		confirmBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				JOptionPane.showMessageDialog(null, "Please wait your payment is being validated....");
				try {
					Thread.sleep(250);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				JOptionPane.showMessageDialog(null, "Payment ....");
			}
		});
		confirmBtn.setForeground(Color.RED);
		confirmBtn.setFont(new Font("Tahoma", Font.BOLD, 14));
		confirmBtn.setBounds(235, 211, 168, 41);
		frame.getContentPane().add(confirmBtn);
	}
}
