import java.io.*;
import java.sql.*;
class student
{
    
     public static void main(String args[])
     {
          try
          {
             int i,n,r,dob,doj,rn=0,flag=0;
	String na;
	float p;
	Connection con=null;
	ResultSet rs=null;
	Statement st=null;
			
	Class.forName("com.mysql.jdbc.Driver");
	con = DriverManager.getConnection("jdbc:mysql://localhost:3306/student","root","root");
			
	BufferedReader br=new BufferedReader(new InputStreamReader(System.in));

	System.out.println("\n Insert the student details");
	System.out.println("Enter student roll no, student name,student date of birth, student DOJ");
	r=Integer.parseInt(br.readLine());
	na=br.readLine();
	dob=Integer.parseInt(br.readLine());
	doj=Integer.parseInt(br.readLine());
	st=con.createStatement();
	rs=st.executeQuery("select * from student");
	while(rs.next())
	{
		if(rs.getInt(1)==r)
		{
			System.out.println("Duplicate roll no:");
			flag=1;
		}
	}
	if(flag==1)
	{
		System.exit(0);
	}
	else
	{
		st=con.createStatement();
		st.executeUpdate("insert into student values("+r+",'"+na+"',"+dob+","+doj+")");
		System.out.println("Insertion successfully");
	}

	System.out.println("\nUpdate the student details\n");
           	System.out.println("Enter roll no.");
	r=Integer.parseInt(br.readLine());
	st=con.createStatement();
	rs=st.executeQuery("select * from student");
	while(rs.next())
	{
		if(rs.getInt(1)==r)
		{
		System.out.println("Roll no\t"+rs.getInt(1)+"\nName\t"+rs.getString(2)+"\nDOB\t"+rs.getInt(8)+"\nDOJ\t"+rs.getInt(8));
		}
	}
	System.out.println("enter date of birth to update");
	dob=Integer.parseInt(br.readLine());
	st=con.createStatement();
	st.executeUpdate("update student set dob='"+dob+"' where roll_no="+r+"");
	System.out.println("updated successfully");


	System.out.println("\nDelete the student details\n");			
	System.out.println("Enter roll no.");
	r=Integer.parseInt(br.readLine());
	st=con.createStatement();
	rs=st.executeQuery("select * from student");
	while(rs.next())
	{
		if(rs.getInt(1)==r)
		{
			System.out.println("Roll no"+rs.getInt(1)+"Name"+rs.getString(2)+"\nDOB\t"+rs.getInt(8)+"\nDOJ\t"+rs.getInt(8));
		}
	}
	st=con.createStatement();
	st.executeUpdate("delete from student where roll_no="+r);
	System.out.println("deleted successfully");
	
	System.out.println("Display the details of student\n");		
	st=con.createStatement();
	rs=st.executeQuery("select * from student");
	System.out.println("Roll no \t name \t percentage\t");
	while(rs.next())
	{
		System.out.println(""+rs.getInt(1)+","+rs.getString(2)+","+rs.getInt(3));
	}
				
	System.out.println("Query on student table\n");		
	System.out.println("Enter roll no.");
	r=Integer.parseInt(br.readLine());
	st=con.createStatement();
	rs=st.executeQuery("select * from student");
	st=con.createStatement();
	rs=st.executeQuery("select na,dob,doj from student where roll_no="+r);
	
          }
          catch(Exception ex)
          {
               ex.printStackTrace();
          }
     }
}