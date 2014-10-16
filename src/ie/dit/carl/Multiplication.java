package ie.dit.carl;

import java.io.IOException;

import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Multiplication extends HttpServlet {
	
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		
		String sA, sB; // parameters as strings
		int iA, iB;    // parameters as integers
		
		// get and tidy parameters
		sA = req.getParameter("A");
		sB = req.getParameter("B");
		if (sA == null) sA = "";
		if (sB == null) sB = "";
		sA = sA.trim();
		sB = sB.trim();
		
		// get default parameters
		String sDefaultA = getServletConfig().getInitParameter("A");
		String sDefaultB = getServletConfig().getInitParameter("B");		
		
		// print parameters
		resp.setContentType("text/plain");
		resp.getWriter().println("Multiplication");
		resp.getWriter().println("Params: A = "+sA + ", B = "+sB);
		resp.getWriter().println("Default Params: A = "+sDefaultA + ", B = "+sDefaultB);
		
		// Validation		
		try {
		
			if (sA == "" && sB == "") {
				throw new Exception("No parameters supplied!");
			}
	
			if (sA == "" || sB == "") {
				throw new Exception("Insufficient parameters supplied!");
			} 
			
			try {
				 iA = Integer.parseInt(sA);
			} catch (NumberFormatException ex) {
				 throw new Exception("Parameter A is not a valid number!");
			}
			
			try {
				 iB = Integer.parseInt(sB);
			} catch (NumberFormatException ex) {
				 throw new Exception("Parameter B is not a valid number!");
			}

		} catch (Exception ex) {
			resp.getWriter().println(ex.getMessage()+" Using defaults instead.");
			try {
				iA = Integer.parseInt(sDefaultA);
				iB = Integer.parseInt(sDefaultB);			
			} catch (Exception ex1) {
				resp.getWriter().println("The default parameters are not valid!");
				return;
			}
		}
	
		// perform the multiplication
		int iTot = 0;
		try {
			iTot = iA * iB;
		} catch (Exception ex) {
			resp.getWriter().println("The total is not a valid number or is too large!");
			return;
		}
		
		// output result
		resp.getWriter().println("Multiplication result is " + iTot);
		
	}

}
