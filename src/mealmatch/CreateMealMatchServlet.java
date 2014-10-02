package mealmatch;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateMealMatchServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

		resp.setContentType("text/html");
	    String name = req.getParameter("name");
	    String year = req.getParameter("year");
	    String phone = req.getParameter("phone");
	    //CREATE SESSION TO STORE USER DATA
	    HttpSession session = req.getSession(true);
	    session.setAttribute("StudentName", name);
	    session.setAttribute("Year", year);
	    session.setAttribute("Checkedin", "No");
	    session.setAttribute("College", "");
	    session.setAttribute("Phone", phone);

	    //FORWARD TO MAIN COLLEGE SELECT PAGE
	    RequestDispatcher collegeView = req.getRequestDispatcher("/collegeview.jsp");
	    collegeView.forward(req,resp);
	}
}