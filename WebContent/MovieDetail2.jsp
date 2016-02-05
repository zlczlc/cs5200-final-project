<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" import="tables.*,java.util.*" import="websevice.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Details</title>
<link href="css/bootstrap.css" rel="stylesheet" />
</head>
<body>
<% 
Movie movie = (Movie)request.getSession().getAttribute("movie");
User user = (User)request.getSession().getAttribute("user");
if (user==null){
	response.sendRedirect("/Etertainment2/login.jsp");
	return;
}
String searchKeyWord = (String) request.getSession().getAttribute("searchKeyWord");
String inTheatre = (String) request.getSession().getAttribute("inTheatre");
String numOfDays = (String) request.getSession().getAttribute("numOfDays");
String radius = (String) request.getSession().getAttribute("radius");
String zipcode = (String) request.getSession().getAttribute("zipcode");
String showRec = (String) request.getSession().getAttribute("showRec");

System.out.println("radius:"+radius);
System.out.println("numOfDays"+numOfDays);
System.out.println("zipcode"+zipcode);
System.out.println(movie.getId());
Showtimes s=new Showtimes();
MovieService ms=new MovieService();
List<Showtimes> a = ms.searchShowTimes(zipcode, numOfDays, radius, movie.getId());
request.getSession().setAttribute("showtimeList",a);

List<Showtimes> b = (List<Showtimes>)request.getSession().getAttribute("showtimesIntheatre");
//System.out.println("b="+b.get(0).getUrl());
if (b==null)
	b = new ArrayList<Showtimes>();
int ss=a.size();
System.out.println("ss:"+ss);
List<String> theatres = Showtimes.FindTheatresList(a);



%>
<nav class="navbar navbar-fixed-left navbar-inverse" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <p class="navbar-brand" href="#"><strong><h1>See Movie Details &nbsp;</h1></strong></p>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li><a href="/Etertainment2/Recommandations.jsp">Home</a></li>
            <li><a href="/Etertainment2/Profile.jsp">Profile</a></li>
            <li><a href="/Etertainment2/event">Schedule</a></li>
          </ul>
        </div><!-- /.nav-collapse -->
      </div><!-- /.container -->
    </nav><!-- /.navbar -->
	<div class="container">
		
	
		<div class="row">
		<h1>&nbsp;&nbsp;<%=movie.getMoviename() %></h1>
			

			<div class="col-md-6">
				
				<img  src=<%="http://developer.tmsimg.com/movieposters"+movie.getImageUrl()+"?api_key=9gnk9dw7rbpzg55wmk8qamn9&h=600"%> alt="" class="img-rounded">
			</div>
			
			<div class="col-md-6">
			<h3>Available Theaters</h3>
				<table class="table table-condensed">
					<div class="list-group">
					
					<%for (int i = 0; i< theatres.size();i=i+2){%>
						<form action="/Etertainment2/rechangepage2" method="post">
						<button  class="list-group-item list-group-item-success btn  form-control" name = "theatre"  value =<%=theatres.get(i)%>  role="submit"><%=theatres.get(i+1)%></button>
						</form>
					<%} %>
					
					</div>
				</table>
				<hr>
				<h3>Available Times</h3>
				<table class="table table-condensed">
					<div class="list-group">
						<%for( int i = 0 ; i<b.size();i++) {%>
						<form action="/Etertainment2/event" method="post">
							<p  class="list-group-item list-group-item-success"     ><%=b.get(i).getDatetime() %></p>
							
							
							 <select  hidden="true" name = "sti"><option><%=String.valueOf(i)%></option></select>
							<button class = "btn btn-primary"  name = "event"  value ="addEvent"  role="submit">Add to my schedule </button>
							
						</form>
						
						&nbsp;&nbsp;<%if(b.get(i).getUrl()!=null) { %><button class = "btn btn-primary">buy ticket </button>   <%} %>
						
						<%} %>
					</div>
				</table>
				<hr>
				
				<a class = "btn btn-primary form-control" href="/Etertainment2/Recommandations.jsp">go back search</a>
			</div>
		</div>
		
		<br>
		
	</div>
</body>
</html>