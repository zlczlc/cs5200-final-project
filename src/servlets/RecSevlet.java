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

import tables.*;
import websevice.MovieService;


/**
 * Servlet implementation class RecSevlet
 */
@WebServlet(name = "rec", urlPatterns = { "/rec" })
public class RecSevlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecSevlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = (User) request.getSession().getAttribute("user");
		System.out.println(user);
		if (user==null){
			response.sendRedirect("/Etertainment2/login.jsp");
			return;
		}
		String search = (String) request.getParameter("search");
		String searchKeyWord = (String) request.getParameter("searchKeyWord");
		String inTheatre = (String) request.getParameter("inTheatre");
		String numOfDays = (String) request.getParameter("numOfDays");
		String radius = (String) request.getParameter("radius");
		String zipcode = (String) request.getParameter("zipcode");
		String showRec = (String) request.getParameter("showRec");
		System.out.println(inTheatre);
		System.out.println(numOfDays);
		if (zipcode == null && user!=null)
			zipcode = user.getCity().getZipcode();
		if (zipcode == null || zipcode=="")
			zipcode = "02115";
		if (radius == null)
			radius = "15";
		if (numOfDays == null)
			numOfDays = "15";
		if (inTheatre == null)
			inTheatre = "1";
		
		if (showRec == null)
			showRec = "1";
		
		
		System.out.println(search);
		System.out.println(searchKeyWord);
		
		MovieService MS = new MovieService();
		List<Movie> movies = new ArrayList<Movie>();
		if (search!=null && !(searchKeyWord==null) && !(searchKeyWord.equals("")) ){
			movies = MS.searchOne(zipcode, numOfDays, radius, searchKeyWord);
			request.getSession().setAttribute("movies", movies);
			//RequestDispatcher dispatcher = request.getRequestDispatcher("/NewFile.jsp");
			//dispatcher.forward(request, response);
			response.sendRedirect("/Etertainment2//Recommandations.jsp");
			return;
		}
		
		if (inTheatre.equals("1") && showRec.equals("1")){
			
			movies = MS.findBypreference(zipcode, numOfDays, radius, user);
			
			System.out.println("show by prefer");
			System.out.println(user.getMovietypes().get(0).getType());
		}
		
		if (inTheatre.equals("1") && showRec.equals("0")){
			
			movies = MS.findAll(zipcode, numOfDays, radius);
			System.out.println("show all");
		}
		
		if (inTheatre.equals("0") && showRec.equals("1")){
			
			//movies = MS.findBypreferenceFutureRelease(zipcode, numOfDays, radius, user);
			movies = MS.findBypreference(zipcode, numOfDays, radius, user);
		}
		
		if (inTheatre.equals("0") && showRec.equals("0")){
			
			//movies = MS.findAllFutureRelease(zipcode, numOfDays, radius);
			movies = MS.findAll(zipcode, numOfDays, radius);
		}
		
		
		
		System.out.println("inTheatre:" +inTheatre);
		System.out.println("radius:"+radius);
		request.getSession().setAttribute("showRec", showRec);
		request.getSession().setAttribute("searchKeyWord", searchKeyWord);
		request.getSession().setAttribute("inTheatre", inTheatre);
		request.getSession().setAttribute("numOfDays", numOfDays);
		request.getSession().setAttribute("zipcode", zipcode);
		request.getSession().setAttribute("radius", radius);
		request.getSession().setAttribute("index", "0");
		
		request.getSession().setAttribute("movies", movies);
		if (movies.size()!=0)
			request.getSession().setAttribute("movie", movies.get(0));
		//RequestDispatcher dispatcher = request.getRequestDispatcher("/Recommandations.jsp");
		//dispatcher.forward(request, response);
		
		response.sendRedirect("/Etertainment2//Recommandations.jsp");
	}

}
