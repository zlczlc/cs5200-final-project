package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tables.Movie;
import tables.Showtimes;
import tables.User;

/**
 * Servlet implementation class rechangepage
 */
@WebServlet("/rechangepage2")
public class rechangepage2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public rechangepage2() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			User user=(User)request.getSession().getAttribute("user");
					
					if (user==null){
						response.sendRedirect("/Etertainment2/login.jsp");
						return;
					}
			String Theatre = (String)request.getParameter("theatre");
			List<Showtimes> stl=(List<Showtimes>) request.getSession().getAttribute("showtimeList");
			List<Showtimes> stl2 = Showtimes.FindTheatres(stl, Theatre);
			System.out.println("TheatreNmae="+Theatre);
			System.out.println("stl2="+stl2);
			request.getSession().setAttribute("showtimesIntheatre", stl2);
			response.sendRedirect("/Etertainment2/MovieDetail2.jsp");

		
	}

}
