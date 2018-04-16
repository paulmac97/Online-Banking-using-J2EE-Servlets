

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import com.mysql.jdbc.PreparedStatement;

/**
 * Servlet implementation class Account
 */
@WebServlet("/Account")
public class Account extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public Account() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @return 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean status=false;
		PrintWriter pw=response.getWriter();
		String y=request.getParameter("yacno");
		Integer yacno=Integer.parseInt(y);
		
		String m=request.getParameter("tacno");
		Integer tacno=Integer.parseInt(m);
		
		String a=request.getParameter("amt");
		Integer amt=Integer.parseInt(a);
		
		//int y=Integer.parseInt(request.getParameter("yacc"));
		//int m=Integer.parseInt(request.getParameter("tacc"));
		//int a=Integer.parseInt(request.getParameter("amt"));
		String sql="select * from blogin where acno='"+m+"'";//add
		String sql2="select * from blogin where acno='"+y+"'";//deduction
		
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","root","root");
			PreparedStatement ps=con.prepareStatement(sql);
			//PreparedStatement ps1=con.prepareStatement("update blogin set bal='"+)
			ResultSet rs=ps.executeQuery();
			
	status=rs.next();
	
		int bal1=rs.getInt(4)+amt;
		String sql1="update blogin set bal='"+bal1+"' where acno='"+m+"'";
		//String sql1="update blogin set bal='5000' where acno=11335577";
		PreparedStatement ps1=con.prepareStatement(sql1);
		
		int i=ps1.executeUpdate();
		System.out.println("transfered");//ps1.close();rs.close();
		
		
		PreparedStatement ps2=con.prepareStatement(sql2);
		ResultSet rs1=ps2.executeQuery();
		status=rs1.next();
		int bal2=rs1.getInt(4)-amt;
		String decu="update blogin set bal='"+bal2+"' where acno='"+y+"'";
		PreparedStatement ps3=con.prepareStatement(decu);
		int j=ps3.executeUpdate();
				System.out.println("deducted");
		
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return;
	}

}
