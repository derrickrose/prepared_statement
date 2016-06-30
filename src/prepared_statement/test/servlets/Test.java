package prepared_statement.test.servlets;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test extends HttpServlet {
   private static final String VIEW = "/WEB-INF/test.jsp";
   private static final String MESSAGE_ATTRIBUTE = "message";
//   private String query = "select * from espece limit 3";
//   private String query = "insert into race (nom,espece_id) values ('bolabola',1);";
   private String query = "insert into race (nom,espece_id) values (?,?);";
   private ArrayList<String> message = null;
   
   Object[] paramValues = null;

   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      BaseAccessTest bat = new BaseAccessTest();
      ResultSet resultSet;
      try {
//         resultSet = bat.executeQuery(query, true);
//         this.message = bat.getMessage();
//         try {
//            while (resultSet.next()) {
//               this.message.add("Id : " + resultSet.getInt("id"));
//            }
//         } catch (SQLException e) {
//            this.message.add("resultSet is null");
//         }
    	  this.paramValues = new Object[2];
    	  this.paramValues[0] = new String("bolabola");
    	  this.paramValues[1] = new Integer(1);
    	  bat.executeUpdate(query, true,this.paramValues);
    	  this.message = bat.getMessage();
    	  
      } catch (SQLException e1) {
    	  this.message.add("Error : "+e1.getMessage());
      }
      // ResultSet resultSet = bat.executeQuery(query);
      request.setAttribute(MESSAGE_ATTRIBUTE, this.message);
      this.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
   }

}
