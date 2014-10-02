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
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

public class StudentCheckout extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {

		//INITIALIZE COLLEGE SIGNING INTO, STUDENT NAME, STUDENT COLLEGE
	    //get name and college from session
		HttpSession session = req.getSession(true);
	    String name = (String) session.getAttribute("StudentName");
	    String year = (String) session.getAttribute("Year");
	    req.getSession(true).setAttribute("Checkedin", "No");
	    //END INITIALIZE

	    //CREATE STUDENT ENTITY, SET AS CHILD OF RES COLLEGE SIGNING INTO
	    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        Key checkoutKey = KeyFactory.createKey("Student", name+year);
  

	    com.google.appengine.api.datastore.Transaction t = datastore.beginTransaction();
        // If the 'table' exists - delete it
        datastore.delete(checkoutKey);
        // Really make sure it's deleted/
        t.commit();


		RequestDispatcher signedOut = req.getRequestDispatcher("/create");
	    signedOut.forward(req,resp);


	}
}


