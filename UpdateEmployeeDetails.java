import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import java.util.*;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;


class UpdateEmployeeDetails extends JFrame
{
	int ID = -1;

	Container container;

	JPanel topPanel;
	JPanel topPanel1;
	JPanel topPanel2;
	JPanel topPanel3;
	JPanel mainPanel;
	JPanel bottomPanel;

	JTable table;
	
	JTextField txtEmpName;
	JTextField txtCnt;
	JTextField txtAdd;
	JTextField txtSalary;
	
	JButton btnUpdate;
	JButton btnSelect;
	
	String[] columnTitles = {"Id","Name","Contact No","Address","Salary"};
	
	Object[][] rowData = null;
	int[] id=null;
	
	public UpdateEmployeeDetails()
	{
		
		super("Update Employee Details");
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
		}
	
		setFrame();
		txtEmpName.setEditable(false);
		txtCnt.setEditable(false);
		txtAdd.setEditable(false);
		txtSalary.setEditable(false);
	
		btnSelect.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
			
				try
				{
					int i;
					int selectedRow = table.getSelectedRow();
						
					ID = id[selectedRow];
							
					txtEmpName.setText(table.getValueAt(selectedRow,1)+"");
					txtCnt.setText(table.getValueAt(selectedRow,2)+"");
					txtAdd.setText(table.getValueAt(selectedRow,3)+"");
					txtSalary.setText(table.getValueAt(selectedRow,4)+"");
					
					txtEmpName.setEditable(true);
					txtCnt.setEditable(true);
					txtAdd.setEditable(true);
					txtSalary.setEditable(true);
					
				}
				catch(ArrayIndexOutOfBoundsException ex)
				{
					JOptionPane.showMessageDialog(null,"Please, Select Employee Record!!");
				}
				catch(Exception ex)
				{
					System.out.println("Error : "+ex);
				}
				
			}
		});
		
		
		btnUpdate.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(!((txtEmpName.getText()).isEmpty()) && !((txtCnt.getText()).isEmpty()) && !((txtAdd.getText()).isEmpty()) && !((txtSalary.getText()).isEmpty()) && ID != -1)
				{
					try
					{
							String oracleurl="jdbc:mysql://localhost:3306/test1";
							String username="root";
							String password="root";
						
							Connection con=null;
							Class.forName("com.mysql.jdbc.Driver");
							con=DriverManager.getConnection(oracleurl,username,password);
							Statement st;
							st=con.createStatement();
					
							String query="UPDATE test1.employee1 set emp_name='"+ txtEmpName.getText() +"', emp_cnt = '"+ txtCnt.getText() +"',emp_add = '"+ txtAdd.getText() +"' ,emp_salary="+ Double.parseDouble(txtSalary.getText()) +" where id= "+ ID + ";";
							int result=st.executeUpdate(query);
			
							if(result==1)
							{
								JOptionPane.showMessageDialog(null,"Employee Details Successfully Updated !!");
								
							}							
							else
							{
								JOptionPane.showMessageDialog(null,"Employee Details not Updated !!");
							}


							//brijesh modification in update
							//setFrame();



							txtEmpName.setText("");
							txtCnt.setText("");
							txtAdd.setText("");
							txtSalary.setText("");
								
							txtEmpName.setEditable(false);
							txtCnt.setEditable(false);
							txtAdd.setEditable(false);
							txtSalary.setEditable(false);
							ID = -1;
							
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
					
					
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Please, Enter all Details..!!");
							
							txtEmpName.setText("");
							txtCnt.setText("");
							txtAdd.setText("");
							txtSalary.setText("");
								
							txtEmpName.setEditable(false);
							txtCnt.setEditable(false);
							txtAdd.setEditable(false);
							txtSalary.setEditable(false);
							ID = -1;				
				}
				//setFrame();
			}
		});
	}

	private void setFrame()
	{		
		//Title...
		
		GridBagLayout topgridbag = new GridBagLayout();
		GridBagConstraints topc = new GridBagConstraints();
		
		topPanel=new JPanel(topgridbag);		
		
		topc.insets = new Insets(5,5,5,5);
		topc.gridx = 0; 
		topc.gridy = 0;
		topc.gridwidth = 3; 
		topc.gridheight = 3;
		topPanel1=new JPanel(new FlowLayout());
		JLabel lblTitle=new JLabel("Employee Details");
		lblTitle.setFont(new java.awt.Font("Palatino Linotype", 0, 14));
		topPanel1.add(lblTitle);
		topgridbag.setConstraints(topPanel1, topc);
		topPanel.add(topPanel1);

		topc.insets = new Insets(5,5,5,5);
		topc.gridx = 0; 
		topc.gridy = 4;
		topc.gridwidth = 3; 
		topc.gridheight = 3 ;
		topPanel2=new JPanel(new FlowLayout());
		
		rowData=fillDataTable();
		table=new JTable(rowData, columnTitles);
		table.getTableHeader().setReorderingAllowed(false);
		table.setModel(new DefaultTableModel(rowData, columnTitles)
		{
		   public boolean isCellEditable(int row, int column) {
			   return false;
		   }
		});

		//brijesh

		//table.repaint();

		//brijesh

		table.setFont(new java.awt.Font("Palatino Linotype", 0, 14));
		table.setRowHeight(25);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		ListSelectionModel cellSelectionModel = table.getSelectionModel();
		//cellSelectionModel.addListSelectionListener(this);
		
		JScrollPane sp=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		sp.setPreferredSize(new Dimension(500, 135));
		topPanel2.add(sp);
		topgridbag.setConstraints(topPanel2, topc);
		topPanel.add(topPanel2);
		
		
		topc.insets = new Insets(5,5,5,5);
		topc.gridx = 0; 
		topc.gridy = 8;
		topc.gridwidth = 0; 
		topc.gridheight = 1;
		topPanel3=new JPanel(new FlowLayout());
		btnSelect=new JButton("Select Details");
		btnSelect.setFont(new java.awt.Font("Palatino Linotype", 0, 14));
		topPanel3.add(btnSelect);
		topgridbag.setConstraints(topPanel3, topc);
		topPanel.add(topPanel3);
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
		btnUpdate=new JButton("Update Employee");
		btnUpdate.setFont(new java.awt.Font("Palatino Linotype", 0, 12));
		bottomPanel.add(btnUpdate);
	
		//setup the component in frame
		container=getContentPane();
		container.add(topPanel,BorderLayout.NORTH);
		container.add(mainPanel,BorderLayout.CENTER);
		container.add(bottomPanel,BorderLayout.SOUTH);
		
		addWindowListener(new WindowEventHandler());
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(550,450);
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/Restaurant-icon.png"));
		setLocationRelativeTo(null);
		setResizable(false);
		
	}
	
	Object[][] fillDataTable()
	{
		ResultSet rs=null;
		Object[][] rowName=null;
	
		try
		{
				String oracleurl="jdbc:mysql://localhost:3306/test1";
				String username="root";
				String password="root";
					
				Connection con=null;
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection(oracleurl,username,password);
				Statement st;
				st=con.createStatement();
					
				String query="SELECT id,emp_name,emp_cnt,emp_add,emp_salary FROM test1.employee1;";
				rs=st.executeQuery(query);
			
				int i=0;
				rs.last();
				rowName=new Object[rs.getRow()][5];
				id=new int[rs.getRow()];
				
				rs.first();
				
				do
				{				
					rowName[i][0]=rs.getInt("id");
					rowName[i][1]=rs.getString("emp_name");
					rowName[i][2]=rs.getString("emp_cnt");
					rowName[i][3]=rs.getString("emp_add");
					rowName[i][4]=rs.getDouble("emp_salary");
					id[i]=rs.getInt("id");
					
					i++;
				}while(rs.next());

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
		
		return rowName;
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
		
		UpdateEmployeeDetails u1=new UpdateEmployeeDetails();
		
	}
}