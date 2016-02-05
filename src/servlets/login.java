package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tables.User;
import DAOs.UserDao;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDao udao = new UserDao();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = udao.findUser(username);
		if (user==null)
		{
			response.sendRedirect("/Etertainment2/login.jsp");
			return;
			
		}
		if(user.getPassword().equals(password)){
			request.getSession().setAttribute("user", user);
			response.sendRedirect("/Etertainment2/rec");
			return;
			
		}
		
		else{
			response.sendRedirect("/Etertainment2/login.jsp");
			return;
			
		}
		
	}

}
