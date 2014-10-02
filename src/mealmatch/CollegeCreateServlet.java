package mealmatch;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
 

@SuppressWarnings("serial")
public class CollegeCreateServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ServletException {
		
	    String chosenCollege = req.getParameter("choose");//set chosenCollege as what college they signed into
	    //Initialize datastore 
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		
		//Query based off college
	    Filter collegeFilter =
				 new FilterPredicate("CheckedIn", FilterOperator.EQUAL, chosenCollege);
		
	    Query q = new Query("Student");
			q.setFilter(collegeFilter);
			PreparedQuery pq = datastore.prepare(q);
			
			//COUNT HOW MANY PEOPLE CHECKED IN:
			int checkInCount=0;
			for (@SuppressWarnings("unused") Entity result: pq.asIterable()) {
				checkInCount++;
			}
			String[] checkedNames = new String[checkInCount];
			String[] checkedYear = new String[checkInCount];
			String[] checkedPhone = new String[checkInCount];
			if(checkInCount>0){
				int i = 0;
				for (Entity result: pq.asIterable()) {

					checkedNames[i] = (String) result.getProperty("Name");
					checkedYear[i] = (String) result.getProperty("Year");
					checkedPhone[i] = (String) result.getProperty("Phone");
					i++;
				}
			}
		req.setAttribute("nameList",checkedNames);
		req.setAttribute("yearList", checkedYear);
		req.setAttribute("phoneList", checkedPhone);
		req.setAttribute("url", chosenCollege);

	    RequestDispatcher individualView = req.getRequestDispatcher("/"+chosenCollege+".jsp");
	    individualView.forward(req,resp);
	}
}
