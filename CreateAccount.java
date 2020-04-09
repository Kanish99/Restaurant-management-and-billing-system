import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;
class CreateAccount extends JFrame{
JTextField text1,text2,text3,text4,text5;
JPasswordField pass1;
JLabel label1,label2,label3,label4,label5,label6;
JButton button,button1;
CreateAccount() {
    setLayout(null);
text1=new JTextField(15);
text2=new JTextField(15);
text3=new JTextField(15);
pass1=new JPasswordField(15);
text4=new JTextField(15);
text5=new JTextField(15);
label1=new JLabel("First Name");
label2=new JLabel("Last Name");
label3=new JLabel("User Name");
label4=new JLabel("Password");
label5=new JLabel("Address");
label6=new JLabel("Phone No");
button=new JButton("Save");
button1=new JButton("Exit");

label1.setBounds(350,100,100,20);
text1.setBounds(450,100,200,20);
label2.setBounds(350,130,100,20);
text2.setBounds(450,130,200,20);
label3.setBounds(350,160,100,20);
text3.setBounds(450,160,200,20);
label4.setBounds(350,190,100,20);
pass1.setBounds(450,190,200,20);
label5.setBounds(350,220,100,20);
text4.setBounds(450,220,200,20);
label6.setBounds(350,250,100,20);
text5.setBounds(450,250,200,20);
button.setBounds(350,280,100,20);
button1.setBounds(450,280,100,20);

add(label1);
add(text1);
add(label2);
add(text2);
add(label3);
add(text3);
add(label4);
add(pass1);
add(label5);
add(text4);
add(label6);
add(text5);
add(button);
add(button1);
button.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
String value1=text1.getText();
String value2=text2.getText();
String value3=text3.getText();
String value4=pass1.getText();
String value5=text4.getText();
String value6=text5.getText();

 Connection con = null;
 String url = "jdbc:mysql://localhost:3306/";;
 String db = "test1";
 String driver = "com.mysql.jdbc.Driver";
 String user = "root";
 String pass = "root";
 String user1="";
 String pass1="";

 try{
 Class.forName(driver);
 con = DriverManager.getConnection(url+db, user, pass);
 Statement st = con.createStatement();
 
int i=st.executeUpdate("insert into login(firstname,lastname,username,password,address,contactno) values('"+value1+"','"+value2+"','"+value3+"','"+value4+"','"+value5+"','"+value6+"')");
 JOptionPane.showMessageDialog(null,"Data is successfully saved.");




 }
catch(Exception e){}
}
});
button1.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ae){
setVisible(false);
}
});
setSize(768,500);
setVisible(true);
}
public static void main(String args[]){
    new CreateAccount();
}

}