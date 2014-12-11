package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOs.EventDao;
import DAOs.MovieDao;
import DAOs.UserDao;
import tables.Event;
import tables.Movie;
import tables.Showtimes;
import tables.User;

/**
 * Servlet implementation class Eventservlet
 */
@WebServlet("/event")
public class Eventservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Eventservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = (User) request.getSession().getAttribute("user");
		Movie movie = (Movie) request.getSession().getAttribute("movie");
		if (user==null){
			response.sendRedirect("/Etertainment2/login.jsp");
			return;
		}
		
		String e= (String)request.getParameter("event");
		if (e!=null && e.equals("addEvent")){
			List<Showtimes> sts = (List<Showtimes>)request.getSession().getAttribute("showtimesIntheatre");
			int num = Integer.parseInt((String)request.getParameter("sti"));
			Showtimes st = sts.get(num);
			Event event = new Event();
			event.setType("Movie in theatre");
			event.setMovie(movie);
			String dateTime = st.getDatetime();
			String[] dt = dateTime.split("T"); 
			event.setDate(dt[0]);
			event.setTime(dt[1]);
			event.setUser(user);
			event.setLocation(st.getTheatrename());
			event.setId(111);
			event.setRemark("remark here");
			EventDao edao = new EventDao();
			MovieDao mdao = new MovieDao();
			mdao.updateMovie(movie.getId(), movie);
			if (user.getEvents()==null)
				user.setEvents(new ArrayList<Event>());
			
			edao.createEvent(event);
			
			user.getEvents().add(event);
			
		}
		
		///////////////////////////////////////////////////
		if (e!=null && e.equals("update")){
			EventDao edao = new EventDao();
			int num = Integer.parseInt((String)request.getParameter("eventId"));
			String time = (String)request.getParameter("time");
			String type = (String)request.getParameter("type");
			String location = (String)request.getParameter("location");
			String remark = (String)request.getParameter("remark");
			Event event = edao.findEvent(num);
			System.out.println("from event update:"+time+type+location+remark);
			event.setTime(time);
			//event.setUser(user);
			event.setLocation(location);
			//event.setId(111);
			event.setRemark(remark);
			event.setType(type);
			for (int i = 0; i< user.getEvents().size();i++){
				if(user.getEvents().get(i).getId()==num){
					user.getEvents().remove(i);
					user.getEvents().add(i, event);;
					break;
				}
			}
			edao.updateEvent(num, event);
			
		}
		////////////////////////////////////////////////////
		if (e!=null && e.equals("drop")){
			EventDao edao = new EventDao();
			int num = Integer.parseInt((String)request.getParameter("eventId"));
			
			for (int i = 0; i< user.getEvents().size();i++){
				if(user.getEvents().get(i).getId()==num){
					user.getEvents().remove(i);
					break;
				}
			}
			edao.removeEvent(num);
		}
		
		
		////////////////////////////////////////////////////
		
		//UserDao udao = new UserDao();
		System.out.println(user.getUsername());
		
		//User user1 = udao.findUserByUserName(user.getUsername());
		//System.out.println(user1.getEvents().size());
		request.getSession().setAttribute("user", user);
		//request.setAttribute("events", user.getEvents());
		
		System.out.println(user.getEvents().size());
		
		response.sendRedirect("/Etertainment2/schedule.jsp");
		return;
		
		
		
		
	}

}
