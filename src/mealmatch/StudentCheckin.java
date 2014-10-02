package mealmatch;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

public class StudentCheckin extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		//INITIALIZE COLLEGE SIGNING INTO, STUDENT NAME, STUDENT COLLEGE
	    String chosenCollege = req.getParameter("chosen");//set chosenCollege as what college they signed into
	    //get name and college from session
		HttpSession session = req.getSession(true);
	    String name = (String) session.getAttribute("StudentName");
	    String year = (String) session.getAttribute("Year");
	    String phone = (String) session.getAttribute("Phone");

	    req.getSession(true).setAttribute("Checkedin", "Yes");
	    req.getSession(true).setAttribute("College", chosenCollege);

	    //END INITIALIZE

	    //CREATE STUDENT ENTITY, SET AS CHILD OF RES COLLEGE SIGNING INTO
	    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	    Entity Student = new Entity("Student", name+year);
	    Student.setProperty("Name", name);
	    Student.setProperty("Year", year);
	    Student.setProperty("Phone", phone);
	    Student.setProperty("CheckedIn", chosenCollege);
	    datastore.put(Student);

		RequestDispatcher signedIn = req.getRequestDispatcher("/create");
	    signedIn.forward(req,resp);


	}
}


