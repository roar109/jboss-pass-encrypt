package com.rageco.encrypt;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EncryptServlet
 * @author Hector Mendoza roar109@gmail.com
 */
public class EncryptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String CONTEXT_PATH = "/jboss-pass-encrypt";
	private static final String GO_BACK = "<a href='"+CONTEXT_PATH+"'>Go back</a>";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EncryptServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getOutputStream().print("Not available");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("pass");
		if("".equals(password) || password == null){
			response.getOutputStream().print("Parameters not valid");
		}else{
			encrypt(password, response);
		}
	}
	
	/**
	 * Call jboss class to encrypt password.
	 * 
	 * @param password
	 * @param response
	 * */
	private void encrypt(String password, HttpServletResponse response){
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    PrintStream ps = new PrintStream(baos);
		    // IMPORTANT: Save the old System.out!
		    PrintStream old = System.out;
		    // Tell Java to use your special stream
		    System.setOut(ps);
		    // Print some output: goes to your special stream
		    org.picketbox.datasource.security.SecureIdentityLoginModule.main(new String[]{password});
		    
		    // Put things back
		    System.out.flush();
		    System.setOut(old);
		    System.out.flush();
			
		    StringBuilder sb = new StringBuilder( "<h2>Pass: ");
		    sb.append(password);
		    sb.append("</h2><br><h3>Password: ");
		    sb.append(baos.toString());
		    sb.append("</h3><br>");
		    sb.append(GO_BACK);
		    
			response.setContentType("text/html");
			response.getOutputStream().write(sb.toString().getBytes());;
		} catch (Exception e) {
			e.printStackTrace();
			String buildResponse = e.getMessage();
			response.setContentType("text/html");
			try {
				response.getOutputStream().write(buildResponse.getBytes());
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
