package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tables.Movie;
import tables.User;

/**
 * Servlet implementation class rechangepage
 */
@WebServlet("/rechangepage")
public class rechangepage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public rechangepage() {
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
		
			String recpage=(String)request.getParameter("recpage");
			List<Movie> ms = (List<Movie>) request.getSession().getAttribute("movies");
			if (recpage!=null){
				
				int ii = Integer.parseInt(recpage);
				int maxindex = (ms.size()-1)/4;
				if (ii<0)
					ii=maxindex;
				if (ii>maxindex)
					ii=0;
				
				request.getSession().setAttribute("index",String.valueOf(ii));
				
				
				Movie m = ms.get(ii*4);
				request.getSession().setAttribute("movie", m);
				response.sendRedirect("/Etertainment2/Recommandations.jsp");
				return;
				
			}
		
			String page = (String)request.getParameter("page");
			System.out.println(page);
			int p = Integer.parseInt(page);
			//List<Movie> ms = (List<Movie>) request.getSession().getAttribute("movies");
			String index = (String)request.getSession().getAttribute("index");
			int i = Integer.parseInt(index);
			Movie m = ms.get(Math.min(i*4+p, ms.size()-1));
			request.getSession().setAttribute("movie", m);
			response.sendRedirect("/Etertainment2/Recommandations.jsp");

		
	}

}
