package Time;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.Color;

public class Client extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Client frame = new Client();
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
	public Client() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 145);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GREEN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 11, 125, 51);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(153, 11, 147, 51);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Socket skClient = new Socket("localhost",6002);
					String n = textField.getText();
					DataOutputStream outClient = new DataOutputStream(skClient.getOutputStream());
					outClient.writeBytes(n+"\n");
					outClient.flush();
					DataInputStream inputClient = new DataInputStream(skClient.getInputStream());
					String day =inputClient.readLine();				
					switch(n) {
					case "1": System.out.println("Ngay  "+day);
					break;
					case "2": System.out.println("Thang"+day);
					break;
					case "3":  System.out.println("Nam  "+day);
					break;
					}
					textField_1.setText(day.toString());
					outClient.close();
					inputClient.close();
					skClient.close();
				}catch(Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		btnSend.setBounds(337, 25, 89, 23);
		contentPane.add(btnSend);
	}
}
