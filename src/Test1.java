

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sms_following.beans.DaoUser;
import com.sms_following.beans.User;
import com.sms_following.dao.DaoFactory;
import com.sms_following.dao.DaoImplementation;

/**
 * Servlet implementation class Test1
 */
public class Test1 extends HttpServlet {
	private ArrayList<String > message = new ArrayList<String>();
	DaoFactory daoFactory;
	public void init(){
		 daoFactory =(DaoFactory) this.getServletContext().getAttribute("daoFactory");
	}
	
	private static final String VIEW = "/WEB-INF/test.jsp";
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DaoUser user = new DaoImplementation(daoFactory);
		User user1 =user.searchForUser();
		message.add(user1.toString());
		request.setAttribute("message", message);
		request.getServletContext().getRequestDispatcher(VIEW).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
