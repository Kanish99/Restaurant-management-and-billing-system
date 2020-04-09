import javax.swing.*;
import java.sql.*;

class HotalManagement
{
	public static void main(String args[])
	{
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e)
		{
		}
		if(createDataBase())
		{
			new UserLogin();
		}
	}
	
	private static boolean createDataBase()
	{
		if(!(checkDBExists("hotalmanagement")))
		{
			Statement stmt=null;
			Connection conn=null;
			try
			{
				String oracleurl="jdbc:mysql://localhost:3306/";
				String username="root";
				String password="root";
								
				
				Class.forName("com.mysql.jdbc.Driver");
				conn=DriverManager.getConnection(oracleurl,username,password);

			  //STEP 4: Execute a query
				System.out.println("Creating database...");
				stmt = conn.createStatement();
				  
				String sql = "CREATE DATABASE hotalmanagement;";
				stmt.executeUpdate(sql);
				System.out.println("Database created successfully...");
				  
				  
				  //Creting Tables..
				createTables();
				
				return true;
		   }
		   catch(SQLException ex)
		   {
			  
		   }
		   catch(Exception ex)
		   {
			  
		   }
		   finally
		   {
			  try
			  {
				 if(stmt!=null)
					stmt.close();
			  }
			  catch(SQLException ex)
			  {
			  }
			  
			  try
			  {
				 if(conn!=null)
					conn.close();
			  }
			  catch(SQLException ex)
			  {
				 
			  }
		   }
		}
		else
		{
			return true;
		}
		return false;
	}
	
	private static void createTables()
	{
		Statement stmt=null;
		Connection con=null;
		
		try
		{
		
				String oracleurl="jdbc:mysql://localhost:3306/hotalmanagement";
				String username="root";
				String password="root";
								
				
				Class.forName("com.mysql.jdbc.Driver");
				con=DriverManager.getConnection(oracleurl,username,password);
				stmt = con.createStatement();
				
				String sql1 = "CREATE TABLE `sys_login` (  `ID` int(11) NOT NULL AUTO_INCREMENT,  `UserName` varchar(45) DEFAULT NULL,  `Password` varchar(45) DEFAULT NULL,  `TotalTable` int(11) DEFAULT NULL,  `lastAttendance` date DEFAULT NULL,  `serviceTax` double DEFAULT NULL,  PRIMARY KEY (`ID`));";
				String sql2 = "CREATE TABLE `food_item` (  `id` int(11) NOT NULL AUTO_INCREMENT,  `name` varchar(100) DEFAULT NULL,  `price` double DEFAULT NULL,  PRIMARY KEY (`id`));";
				String sql3 = "CREATE TABLE `employee` (  `id` int(11) NOT NULL AUTO_INCREMENT,  `emp_name` varchar(100) DEFAULT NULL,  `emp_cnt` varchar(45) DEFAULT NULL,  `emp_add` varchar(150) DEFAULT NULL,  `emp_salary` double DEFAULT NULL,  PRIMARY KEY (`id`));";
				String sql4 = "CREATE TABLE `emp_attendance` (  `id` int(11) NOT NULL AUTO_INCREMENT,  `emp_id` int(11) DEFAULT NULL,  `date` date DEFAULT NULL,  `status` bit(1) DEFAULT NULL,  PRIMARY KEY (`id`),  KEY `fk_attendance_emp_idx` (`emp_id`),  CONSTRAINT `fk_attendance_emp` FOREIGN KEY (`emp_id`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION);";
				String sql5 = "CREATE TABLE `bill` (  `billID` int(11) NOT NULL AUTO_INCREMENT,  `EId` int(11) DEFAULT NULL,  `EName` varchar(100) DEFAULT NULL,  `TableNo` int(11) DEFAULT NULL,  `BillDate` date DEFAULT NULL,  `STax` double DEFAULT NULL,  `inTime` varchar(45) DEFAULT NULL,  `outTime` varchar(45) DEFAULT NULL,  `BillAmount` double DEFAULT NULL,  `AmountWithTax` double DEFAULT NULL,  PRIMARY KEY (`billID`),  KEY `fk_toEmp_idx` (`EId`),  CONSTRAINT `fk_toEmp` FOREIGN KEY (`EId`) REFERENCES `employee` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION);";
				String sql6 = "CREATE TABLE `bill_item` (  `id` int(11) NOT NULL AUTO_INCREMENT,  `BId` int(11) DEFAULT NULL,  `Name` varchar(100) DEFAULT NULL,  `Price` double DEFAULT NULL,  `Qty` double DEFAULT NULL,  `Amt` double DEFAULT NULL,  PRIMARY KEY (`id`),  KEY `fk_tobill_idx` (`BId`),  CONSTRAINT `fk_tobill` FOREIGN KEY (`BId`) REFERENCES `bill` (`billID`) ON DELETE CASCADE ON UPDATE CASCADE);";
			stmt.executeUpdate(sql1);
			stmt.executeUpdate(sql2);
			stmt.executeUpdate(sql3);
			stmt.executeUpdate(sql4);
			stmt.executeUpdate(sql5);
			stmt.executeUpdate(sql6);
			
			System.out.println("Created tables in given database...");
			
			String query="INSERT INTO `hotalmanagement`.`sys_login`(`UserName`,`Password`,`TotalTable`,`serviceTax`, `lastAttendance`)VALUES('Admin','12345',5,12.5,'2015-08-01');";
			stmt.executeUpdate(query);
		}
		catch(Exception ex)
		{
		}
	}
	
	private static boolean checkDBExists(String dbName)
	{
		 try
		 {
			String oracleurl="jdbc:mysql://localhost:3306/hotalmanagement";
			String username="root";
			String password="root";
								
			Connection con=null;
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(oracleurl,username,password);

			ResultSet resultSet = con.getMetaData().getCatalogs();

			while (resultSet.next()) 
			{
			  String databaseName = resultSet.getString(1);
				if(databaseName.equals(dbName)){
					return true;
				}
			}
			resultSet.close();
		}
		catch(Exception e){
			
		}

		return false;
	}
}