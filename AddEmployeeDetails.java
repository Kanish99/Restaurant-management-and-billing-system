import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.util.*;

class AddEmployeeDetails extends JFrame
{
	Container container;

	JPanel topPanel;
	JPanel mainPanel;
	JPanel bottomPanel;

	JTextField txtEmpName;
	JTextField txtCnt;
	JTextField txtAdd;
	JTextField txtSalary;
	
	JButton btnAdd;
	
	
	public AddEmployeeDetails()
	{
		super("Add Employee Details");
		
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
				if(!((txtEmpName.getText()).isEmpty()) && !((txtCnt.getText()).isEmpty()) && !((txtAdd.getText()).isEmpty()) && !((txtSalary.getText()).isEmpty()))
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
					
							String query="INSERT INTO `hotalmanagement`.`employee`(`emp_name`,`emp_cnt`,`emp_add`,`emp_salary`)VALUES('"+ txtEmpName.getText() +"','"+ txtCnt.getText() +"','"+ txtAdd.getText() +"',"+ Double.parseDouble(txtSalary.getText()) +");";
							int result=st.executeUpdate(query);
			
							if(result==1)
							{
								JOptionPane.showMessageDialog(null,"Employee Details Successfully Add !!");
							}							
							else
							{
								JOptionPane.showMessageDialog(null,"Employee Details not Add !!");
							}
							
							txtEmpName.setText("");
							txtCnt.setText("");
							txtAdd.setText("");
							txtSalary.setText("");
							
							
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
					txtEmpName.setText("");
					txtCnt.setText("");
					txtAdd.setText("");
					txtSalary.setText("");
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Please, Enter all Details..!!");
							
							txtEmpName.setText("");
							txtCnt.setText("");
							txtAdd.setText("");
							txtSalary.setText("");
				}
			}
		});
		
	}
	
	private void setFrame()
	{
		//Title...
		topPanel=new JPanel(new FlowLayout());
		JLabel lblTitle=new JLabel("Add New Employee");
		lblTitle.setFont(new java.awt.Font("Palatino Linotype", 0, 14));
		topPanel.add(lblTitle);

		//User Input Fields/.....
		
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		
		mainPanel=new JPanel(gridbag);
		mainPanel.setBackground(Color.white);

		
		//Employee Name
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0; 
		c.gridy = 0;
		c.gridwidth = 3; 
		c.gridheight = 3;
		JLabel lblfname=new JLabel("Employee First Name");
		lblfname.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(lblfname, c);
		mainPanel.add(lblfname);
		
		
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
		txtEmpName=new JTextField(14);
		txtEmpName.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(txtEmpName, c);
		mainPanel.add(txtEmpName);

		//Employee Contact
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0; 
		c.gridy = 3;
		c.gridwidth = 3; 
		c.gridheight = 3;
		JLabel lblcnt=new JLabel("Contact No");
		lblcnt.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(lblcnt, c);
		mainPanel.add(lblcnt);
		
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 4; 
		c.gridy = 3;
		c.gridwidth = 5; 
		c.gridheight = 3;
		JLabel lbl2=new JLabel(":");
		lbl2.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(lbl2, c);
		mainPanel.add(lbl2);
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 9; 
		c.gridy = 3;
		c.gridwidth = 120; 
		c.gridheight = 3;
		txtCnt=new JTextField(14);
		txtCnt.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(txtCnt, c);
		mainPanel.add(txtCnt);
		
		//Employee Address
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0; 
		c.gridy = 7;
		c.gridwidth = 3; 
		c.gridheight = 3;
		JLabel lbladd=new JLabel("Address");
		lbladd.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(lbladd, c);
		mainPanel.add(lbladd);
		
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 4; 
		c.gridy = 7;
		c.gridwidth = 5; 
		c.gridheight = 3;
		JLabel lbl3=new JLabel(":");
		lbl3.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(lbl3, c);
		mainPanel.add(lbl3);
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 9; 
		c.gridy = 7;
		c.gridwidth = 120; 
		c.gridheight = 3;
		txtAdd=new JTextField(14);
		txtAdd.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(txtAdd, c);
		mainPanel.add(txtAdd);
		
		//Employee Salary
	
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0; 
		c.gridy = 10;
		c.gridwidth = 3; 
		c.gridheight = 3;
		JLabel lblsalary=new JLabel("Salary");
		lblsalary.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(lblsalary, c);
		mainPanel.add(lblsalary);
		
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 4; 
		c.gridy = 10;
		c.gridwidth = 5; 
		c.gridheight = 3;
		JLabel lbl4=new JLabel(":");
		lbl4.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(lbl4, c);
		mainPanel.add(lbl4);
		
		c.insets = new Insets(5,5,5,5);
		c.gridx = 9; 
		c.gridy = 10;
		c.gridwidth = 120; 
		c.gridheight = 3;
		txtSalary=new JTextField(14);
		txtSalary.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		gridbag.setConstraints(txtSalary, c);
		mainPanel.add(txtSalary);
	
		//Bottom Panel
		bottomPanel=new JPanel(new FlowLayout(FlowLayout.RIGHT));
		btnAdd=new JButton("Add Employee");
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
		
	}
	
	private class WindowEventHandler extends WindowAdapter
	{
		public void windowClosing(WindowEvent evt)
		{
			
		}
	}
}