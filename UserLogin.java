import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.util.*;

public class UserLogin extends JFrame implements ActionListener
{
	Container container;

	JPanel topPanel;
	JPanel mainPanel;
	JPanel bottomPanel;

	JTextField txtUsername;
	JPasswordField txtPassword;
	JButton btnLogin;
	JLabel lblChangePassword;

	
	
	public UserLogin()
	{
		super("System Login");
		
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
		}
		
		
		
		//Title...
		topPanel=new JPanel(new FlowLayout());
		JLabel lblTitle=new JLabel("User Login");
		lblTitle.setFont(new java.awt.Font("Palatino Linotype", 0, 14));
		topPanel.add(lblTitle);
		
		
		//User Input Fields/.....
		
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		mainPanel=new JPanel(gridbag);
		mainPanel.setBackground(Color.white);
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0; 
		c.gridy = 0;
		c.gridwidth = 3; 
		c.gridheight = 3;
		JLabel lblUsername=new JLabel("Username");
		lblUsername.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(lblUsername, c);
		mainPanel.add(lblUsername);
		
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 4; 
		c.gridy = 0;
		c.gridwidth = 5; 
		c.gridheight = 3;
		JLabel lbl1=new JLabel(":");
		lbl1.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(lbl1, c);
		mainPanel.add(lbl1);
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 9; 
		c.gridy = 0;
		c.gridwidth = 120; 
		c.gridheight = 3;
		txtUsername=new JTextField(14);
		txtUsername.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(txtUsername, c);
		mainPanel.add(txtUsername);
		
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0; 
		c.gridy = 4;
		c.gridwidth = 3; 
		c.gridheight = 3;
		JLabel lblPassword=new JLabel("Password");
		lblPassword.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(lblPassword, c);
		mainPanel.add(lblPassword);
		
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 4; 
		c.gridy = 4;
		c.gridwidth = 5; 
		c.gridheight = 3;
		JLabel lbl2=new JLabel(":");
		lbl2.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(lbl2, c);
		mainPanel.add(lbl2);
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 9; 
		c.gridy = 4;
		c.gridwidth = 120; 
		c.gridheight = 3;
		txtPassword=new JPasswordField(14);
		txtPassword.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(txtPassword, c);
		mainPanel.add(txtPassword);
		
		//Login Buttons...and Change Password ...
		c.insets = new Insets(5,5,5,5);
		c.gridx = 128 ; 
		c.gridy = 8;
		c.gridwidth = 0; 
		c.gridheight = 3;
		btnLogin=new JButton("Log In");
		btnLogin.setSize(29,20);
		btnLogin.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(btnLogin, c);
		mainPanel.add(btnLogin);
		
		btnLogin.addActionListener(this);
		/*
		c.insets = new Insets(5,5,5,5);
		c.gridx = 128 ; 
		c.gridy = 12;
		c.gridwidth = 0; 
		c.gridheight = 3;
		lblChangePassword=new JLabel("Change Password");
		lblChangePassword.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		lblChangePassword.setForeground(new java.awt.Color(51, 0, 255));
		lblChangePassword.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
		gridbag.setConstraints(lblChangePassword, c);
		mainPanel.add(lblChangePassword);*/
		
        
		
		//Setup JFrame  and show...
		
		container=getContentPane();
		container.add(topPanel,BorderLayout.NORTH);
		container.add(mainPanel,BorderLayout.CENTER);
		
		addWindowListener(new WindowEventHandler());
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(300,200);
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/Restaurant-icon.png"));
		setLocationRelativeTo(null);
		setResizable(false);
		show();
	}
	
	private class WindowEventHandler extends WindowAdapter
	{
		public void windowClosing(WindowEvent evt)
		{
			System.exit(0);
		}
	}
	
	public void actionPerformed(ActionEvent e)
	{
			if(e.getSource()==btnLogin)
			{
				if(!((txtPassword.getText()).isEmpty()) && !((txtUsername.getText()).isEmpty()))
				{
					try
					{
							String oracleurl="jdbc:mysql://localhost:3306/hotalmanagement";
							String username="root";
							String password="root";
						
							Connection con=null;
							Class.forName("com.mysql.jdbc.Driver");
							con=DriverManager.getConnection(oracleurl,username,password);
							Statement st;
							st=con.createStatement();
					
							String query="SELECT * FROM hotalmanagement.sys_login WHERE UserName='"+ txtUsername.getText() +"' AND Password='"+ txtPassword.getText() +"';";
							ResultSet rs=st.executeQuery(query);
			
							while(rs.next())
							{
								JOptionPane.showMessageDialog(null,"Login Successfully!!");
								
								MenuExample mm =  new MenuExample();
								mm.setVisible(true);
								System.out.println("hi");
								this.dispose();
							}		

						st.close();
						con.close();
							
					}
					catch (ClassNotFoundException ex) 
					{
						System.out.println("Error : " + ex);
					}
					catch (SQLException ex) 
					{
						System.out.println("Error : " + ex);
					}	
					catch(Exception ex)
					{
						System.out.println("Error : " + ex);
					}
					
					
					txtPassword.setText("");
					txtUsername.setText("");
					
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Please, Enter valid Username and Password");
					txtPassword.setText("");
					txtUsername.setText("");
				}
				
			}
	}
}

