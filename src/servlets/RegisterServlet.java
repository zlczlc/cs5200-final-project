package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tables.User;
import DAOs.UserDao;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet(name = "register", urlPatterns = { "/register" })
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			
		
		System.out.println("aaaaaaaaaaa");

		User u2=new User();
		String  username = request.getParameter("username");
		String  email = request.getParameter("email");
		String  password = request.getParameter("password");
		String  password1 = request.getParameter("password1");
		if (password1.equals(password)){
			UserDao udao=new UserDao();
			User u1= udao.findUserByUserName(username);
			if (u1==null){
				u2.setEmail(email);
				u2.setUsername(username);
				u2.setPassword(password);
				udao.createUser(u2);
				request.getSession().setAttribute("user", u2);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/Profile.jsp");
				dispatcher.forward(request, response);
				

				
			}
			else response.sendRedirect("/Etertainment2/register.jsp");

		}
		else response.sendRedirect("/Etertainment2/register.jsp");
		
	}

	

}
