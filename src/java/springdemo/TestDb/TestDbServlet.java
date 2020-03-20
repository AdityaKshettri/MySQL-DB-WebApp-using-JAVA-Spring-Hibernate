package springdemo.TestDb;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String user = "root";
        String pass = "1234";
        String jdbcUrl = "jdbc:mysql://localhost:3306/web_customer_tracker";
        String driver = "com.mysql.jdbc.Driver";
        
        try {
            PrintWriter out = response.getWriter();
            out.println("Connecting to database : " + jdbcUrl);
            Class.forName(driver);
            Connection myConn = DriverManager.getConnection(jdbcUrl, user, pass);
            out.println("Connected successfully");
        }
        catch(Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        }
    }
}
