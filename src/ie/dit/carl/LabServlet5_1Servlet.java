package ie.dit.carl;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;

import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class LabServlet5_1Servlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		//resp.setContentType("text/plain");
		//resp.getWriter().println("Hello, world");
		
		//create var referencing the response output writer
		PrintWriter writer = resp.getWriter(); 
		
		//Use Google API to create an object with the users credentials
		UserService userService = UserServiceFactory.getUserService();
		
		//Store the entity that made the request to this page
		Principal myPrincipal = req.getUserPrincipal();
		String emailAddress = null; 
		
		String thisURL = req.getRequestURI();					//store this page's address
		String loginURL = userService.createLoginURL(thisURL);	//ask Google API to generate a login URL based on thisURL
		String logoutURL = userService.createLogoutURL(thisURL);//ask Google API to generate a logout URL based on thisURL
		
		resp.setContentType("text/html"); //prepare to write response
		if(myPrincipal == null) {		  //no entity is logged in
			writer.println("<p>You are not logged in.");
			writer.println("<p>You can <a href=\""+loginURL+"\">sign in here</a>.</p>"); //link to loginURL
		} // end if not logged in
		
		if (myPrincipal != null) {		 //an entity is logged in
			emailAddress = myPrincipal.getName(); //get the id of the logged in entity
			writer.println("<p>You are logged in as (email): "+emailAddress+"</p>");
			writer.println("<p>You can <a href=\""+logoutURL+"\">sign out</a>.</p>"); //link to logoutURL
		} // end if logged in
		
	}
}
