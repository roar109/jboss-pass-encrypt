package com.softtek.encrypt;

import java.io.IOException;

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
		String password = request.getParameter("p");
		System.out.println("Get: "+password);
		encrypt(password, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String password = request.getParameter("pass");
		System.out.println("Post: "+password);
		encrypt(password, response);
	}
	
	/**
	 * Call jboss class to encrypt password.
	 * 
	 * @param password
	 * @param response
	 * */
	private void encrypt(String password, HttpServletResponse response){
		try {
			org.picketbox.datasource.security.SecureIdentityLoginModule.main(new String[]{password});
			String buildResponse = "<h1>Check log for the encrypted password</h1><br>"+GO_BACK;
			response.setContentType("text/html");
			response.getOutputStream().write(buildResponse.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
