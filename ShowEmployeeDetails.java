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


class ShowEmployeeDetails extends JFrame
{
	
	Container container;

	JPanel topPanel;
	JPanel topPanel1;
	JPanel topPanel2;
	JPanel topPanel3;
	
	JTable table;
	
	
	
	String[] columnTitles = {"Id","Name","Contact No","Address","Salary"};
	
	Object[][] rowData = null;
	int[] id=null;
	
	public ShowEmployeeDetails()
	{
		
		super("Employee Details");
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
		}
	
		setFrame();
		
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
		
		//setup the component in frame
		container=getContentPane();
		container.add(topPanel,BorderLayout.NORTH);
		
		
		addWindowListener(new WindowEventHandler());
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setSize(550,250);
		setIconImage(Toolkit.getDefaultToolkit().getImage("images/customer.png"));
		setLocationRelativeTo(null);
		setResizable(false);
		show();
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
				//System.out.println("Error : " + ex);
			}
			catch (SQLException ex) 
			{
				//System.out.println("Error : " + ex);
			}	
			catch(Exception ex)
			{
				//System.out.println("Error : " + ex);
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
		
		new ShowEmployeeDetails();
	}
}