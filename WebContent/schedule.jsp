<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import = "tables.*,websevice.*,java.util.*, DAOs.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Schedule</title>
<link href="css/bootstrap.css" rel="stylesheet" />
</head>
<body>


<% 

 User user = (User) request.getSession().getAttribute("user");
if (user==null){
	response.sendRedirect("/Etertainment2/login.jsp");
	return;
}

List<Event> events = user.getEvents();
System.out.println(user.getUsername());

MovieService ms = new MovieService();
String dateNow = ms.getDate();
int days = 15;

// System.out.println(now);


%>



	<div class="container">
		<ul class="nav nav-tabs">
			<li role="presentation" ><a href="/Etertainment2/Recommandations.jsp">Home</a></li>
			<li role="presentation"><a href="/Etertainment2/Profile.jsp">Profile</a></li>
			<li role="presentation" class="active"><a href="#">Schedule</a></li>
		</ul>
		<div>
			<h1>
				View Your Schedule
				<p class="text-right">
					<a class="btn btn-info" href="/Etertainment2/Logout.jsp" >logout</a>
				</p>
			</h1>
		</div>
		<p class="lead">here is your recently schedule</p>

		<div>
			<table class="table table-striped">
				<tr class="active">
				</tr>
				<tr class="success">
				</tr>
				<tr class="warning">
				</tr>
				<tr class="danger">
				</tr>
				<tr class="info">
				</tr>
				<tr>
					<td class="active " >All</td>
					<td class="success"><strong>Movies</strong></td>
					<td class="warning">Sport games</td>
					<td class="danger">Concerts</td>
					<td class="info">TV shows</td>
				</tr>
			</table>
			
			<hr>
			
			<%for (int i = 0; i< days; i++) { dateNow = ms.getDateF(i); List<Event> ev = Event.getByDate(events, dateNow); %>
				<%if (ev.size()!=0){ %><h2><%=dateNow %>: </h2>
				<hr>
					<%for (int j = 0; j< ev.size(); j++) { Event e = ev.get(j);%>
					   
					<div class="row">
						<form action="/Etertainment2/event" method="post">
						<div class="col-md-3">
							<%MovieDao mdao = new MovieDao(); Movie mv = mdao.findMovie(e.getMovie().getId());  %>
							<img  src=<%="http://developer.tmsimg.com/movieposters"+mv.getImageUrl()+"?api_key=9gnk9dw7rbpzg55wmk8qamn9&h=300"%>  class="img-rounded" 
							alt="<%=mv.getMoviename()%>"/>
							
						</div>
						<div class="col-md-1">
							<textarea class="form-control" rows="3" name="time" ><%=e.getTime() %></textarea>
						</div>
						<div class="col-md-2">
							<textarea class="form-control" rows="3" name="type"><%=e.getType() %></textarea>
						</div>
						<div class="col-md-2">
							<textarea class="form-control" rows="3" name="location"><%=e.getLocation() %></textarea>
						</div>
						<div class="col-md-2">
							<textarea class="form-control" rows="3" name="remark"><%=e.getRemark() %></textarea>
						</div>
						<select  hidden="true" name = "eventId"><option><%=e.getId()%></option></select>
						<div class="col-md-1">
							<button  class="btn btn-primary" name = "event" value="update"   role = "submit">reset</button>
						</div>
		
						<div class="col-md-1">
							<button  class="btn btn-danger"  name = "event" value="drop" role = "submit">remove</button>
						</div>
						</form>
					</div>
					
					<br> <br>
					<%} %>
				<%} %>
			<%} %>
			
			
			<div>
			<hr>
				<p class="text-right">
					<footer>
         <p>By ZFS</p>
      </footer>
				</p>
			</div>
		</div>
</body>
</html>