import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.util.*;

class ChangePasswordFrm extends JFrame
{
	Container container;

	JPanel topPanel;
	JPanel mainPanel;
	JPanel bottomPanel;

	JPasswordField txtNewPassword;
	JPasswordField txtCPassword;
	JPasswordField txtPassword;
	JTextField txtUsername;
	JButton btnAdd;
	
	public ChangePasswordFrm()
	{
		super("Change Password");
		
		
		
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
		}
		
		setFrame();
		
		btnAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(!((txtNewPassword.getText()).isEmpty()) && !((txtCPassword.getText()).isEmpty()) && !((txtPassword.getText()).isEmpty()) && !((txtUsername.getText()).isEmpty()) )
				{
				
					if((txtNewPassword.getText()).equals(txtCPassword.getText()))
					{
					
						if(!((txtNewPassword.getText()).equals(txtPassword.getText())))
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
				
									if(rs.next())
									{
							
										String query1="UPDATE `hotalmanagement`.`sys_login` SET `Password` = '" + txtNewPassword.getText() + "';";
										int result=st.executeUpdate(query1);
						
										if(result==1)
										{
											JOptionPane.showMessageDialog(null,"Password Successfully Change!!");
											dispose();
										}							
										else
										{
											JOptionPane.showMessageDialog(null,"Password not change!!");
										}
										
									}
									else
									{
										JOptionPane.showMessageDialog(null,"Please, Enter Correct Password..!!");
									}
										txtPassword.setText("");
										txtUsername.setText("");
										txtNewPassword.setText("");
										txtCPassword.setText("");
										
									
							}
							catch (ClassNotFoundException ex) 
							{
								System.out.println("Error : " + ex);
							}
							catch (SQLException ex) 
							{
								System.out.println("Error : " + ex);
							}
							catch(NumberFormatException ex)
							{
								JOptionPane.showMessageDialog(null,"Please, Enter Correct Information!!");
							}
							catch(Exception ex)
							{
								System.out.println("Error : " + ex);
							}			
						}
						else
						{
							JOptionPane.showMessageDialog(null,"Old Password and New Password are Same!!");
						}
						txtPassword.setText("");
						txtUsername.setText("");
						txtNewPassword.setText("");
						txtCPassword.setText("");
					}
					else
					{
						JOptionPane.showMessageDialog(null,"Please, Enter Correct Password..!!");
						
						txtPassword.setText("");
						txtUsername.setText("");
						txtNewPassword.setText("");
						txtCPassword.setText("");
				
					}
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Please, Enter all Details..!!");
					
					txtPassword.setText("");
					txtUsername.setText("");	
					txtNewPassword.setText("");
					txtCPassword.setText("");
				}
			}
		});
		
	}
	
	private void setFrame()
	{
		//Title...
		topPanel=new JPanel(new FlowLayout());
		JLabel lblTitle=new JLabel("Change Password");
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
		JLabel lbl3=new JLabel(":");
		lbl3.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(lbl3, c);
		mainPanel.add(lbl3);
		
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
		JLabel lbl4=new JLabel(":");
		lbl4.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(lbl4, c);
		mainPanel.add(lbl4);
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 9; 
		c.gridy = 4;
		c.gridwidth = 120; 
		c.gridheight = 3;
		txtPassword=new JPasswordField(14);
		txtPassword.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(txtPassword, c);
		mainPanel.add(txtPassword);
		
		
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0; 
		c.gridy = 8;
		c.gridwidth = 3; 
		c.gridheight = 3;
		JLabel lblnpass=new JLabel("New Password");
		lblnpass.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(lblnpass, c);
		mainPanel.add(lblnpass);
		
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 4; 
		c.gridy = 8;
		c.gridwidth = 5; 
		c.gridheight = 3;
		JLabel lbl1=new JLabel(":");
		lbl1.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(lbl1, c);
		mainPanel.add(lbl1);
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 9; 
		c.gridy = 8;
		c.gridwidth = 120; 
		c.gridheight = 3;
		txtNewPassword=new JPasswordField(14);
		txtNewPassword.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(txtNewPassword, c);
		mainPanel.add(txtNewPassword);

		//Employee Contact
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0; 
		c.gridy = 12;
		c.gridwidth = 3; 
		c.gridheight = 3;
		JLabel lblcpass=new JLabel("Confirm Password");
		lblcpass.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(lblcpass, c);
		mainPanel.add(lblcpass);
		
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 4; 
		c.gridy = 12;
		c.gridwidth = 5; 
		c.gridheight = 3;
		JLabel lbl2=new JLabel(":");
		lbl2.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(lbl2, c);
		mainPanel.add(lbl2);
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 9; 
		c.gridy = 12;
		c.gridwidth = 120; 
		c.gridheight = 3;
		txtCPassword=new JPasswordField(14);
		txtCPassword.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(txtCPassword, c);
		mainPanel.add(txtCPassword);
			
		//Bottom Panel
		bottomPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnAdd=new JButton("Change Password");
		btnAdd.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		bottomPanel.add(btnAdd);
	
		//setup the component in frame
		container=getContentPane();
		container.add(topPanel,BorderLayout.NORTH);
		container.add(mainPanel,BorderLayout.CENTER);
		container.add(bottomPanel,BorderLayout.SOUTH);
		
		addWindowListener(new WindowEventHandler());
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(350,250);
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/Restaurant-icon.png"));
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}
	
	private class WindowEventHandler extends WindowAdapter
	{
		public void windowClosing(WindowEvent evt)
		{
			
		}
	}
	
	public static void main(String args[])
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
		}
		
		new ChangePasswordFrm();
	}
}