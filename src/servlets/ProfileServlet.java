package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOs.MovieDao;
import DAOs.UserDao;
import tables.*;

/**
 * Servlet implementation class ProfileServlet
 */
@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Movietype> mts=(List<Movietype>) request.getSession().getAttribute("movietypes");
		System.out.println(mts);
		User user=(User)request.getSession().getAttribute("user");
		
		if (user==null){
			response.sendRedirect("/Etertainment2/login.jsp");
			return;
		}
		UserDao udao=new UserDao();
		MovieDao mdao = new MovieDao();
		System.out.println(user.getUsername());
		String  action = request.getParameter("Action");
		System.out.println(action);
		
		String  zipcode = request.getParameter("zipcode");
		City city = new City();
		city.setZipcode(zipcode);
		user.setCity(city);
		user.setMovietypes(new ArrayList<Movietype>());
		for (Movietype mt: mts){
			//System.out.println(mt.getType());
			String s = (String) request.getParameter(mt.getType().replace(" ", ""));

			//System.out.println("s="+s);
			if (s!=null){
				System.out.println("s="+s);
				user.getMovietypes().add(mt);
			}
			
		}
		System.out.println(user.getCity());
		udao.updateUser(user.getUsername(), user);
		request.getSession().setAttribute("user", user);
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/rec");
		response.sendRedirect("/Etertainment2/rec");
		//dispatcher.forward(request, response);

		
	}

}
