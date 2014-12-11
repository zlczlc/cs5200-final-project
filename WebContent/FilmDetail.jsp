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
String searchKeyWord = (String) request.getSession().getAttribute("searchKeyWord");
String inTheatre = (String) request.getSession().getAttribute("inTheatre");
String numOfDays = (String) request.getSession().getAttribute("numOfDays");
String radius = (String) request.getSession().getAttribute("radius");
String zipcode = (String) request.getSession().getAttribute("zipcode");
String showRec = (String) request.getSession().getAttribute("showRec");

Showtimes s=new Showtimes();
MovieService ms=new MovieService();
List<Showtimes> a = ms.searchShowTimes(zipcode, numOfDays, radius, movie.getId());
//int ss=a.size();
//System.out.println("ss:"+ss);
%>
	<div class="container">
		<div>
			<p class="text-center">
			<h1>See Movie Details</h1>
			</p>
		</div>
		<br>
<br>
		<div class="row">
			<div class="col-md-2">
				<div class="btn-group-vertical" role="group" aria-label="...">
		 		<button type="button" class="btn btn-default">film1</button>
		 		<br>
		 		<button type="button" class="btn btn-default">film2</button>
		 		<br>
		 		<button type="button" class="btn btn-default">film3</button>
		 		<br>
		 		<button type="button" class="btn btn-default">film4</button>
		 		<br>
		 		<button type="button" class="btn btn-default">film5</button>
		 		<br>
		 		<button type="button" class="btn btn-default">film6</button>
		 		<br>
		 		<button type="button" class="btn btn-default">film7</button>
		 		</div>
			</div>

			<div class="col-md-2">
				<img alt="" src="#" class="img-rounded">
			</div>
			<div class="col-md-8">
			<h3>Available Theaters</h3>
				<table class="table table-condensed">
					<div class="list-group">
					<%
					
					%>
						<a href="FilmDetail.jsp" class="list-group-item list-group-item-success">
						<%
							a.get(0).getTheatrename();
						%></a>
						<a href="#" class="list-group-item list-group-item-info"><% a.get(0).getTheatrename();%></a>
						<a href="#" class="list-group-item list-group-item-warning"><% a.get(1).getTheatrename();%></a>
						<a href="#" class="list-group-item list-group-item-danger"><% a.get(2).getTheatrename();%></a>
					</div>
				</table>
				<nav>
				<ul class="pagination">
					<li><a href="#"><span aria-hidden="true">&laquo;</span><span
							class="sr-only">Previous</span></a></li>
					<li><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#"><span aria-hidden="true">&raquo;</span><span
							class="sr-only">Next</span></a></li>
				</ul>
				</nav>
			</div>
		</div>
		<br>
		<div class="row">
			<div class="col-md-2">
				<div class="btn-group-vertical" role="group" aria-label="...">
		 		<button type="button" class="btn btn-default">film1</button>
		 		<br>
		 		<button type="button" class="btn btn-default">film2</button>
		 		<br>
		 		<button type="button" class="btn btn-default">film3</button>
		 		</div>
			</div>

			<div class="col-md-2">
				
			</div>
			<div class="col-md-8">
			<h3>Available Times</h3>
				<table class="table table-condensed"><%
				String Time1="";
				String Time2="";
				String Time3="";
				String Time4="";
				Showtimes stt = (Showtimes)request.getSession().getAttribute("Showtimes");
				Showtimes sts=new Showtimes();
				List<Showtimes> nst=sts.FindTheatres(a,stt.getTheatreId());
				//Time1=nst.get(0).getDatetime();
				//Time2=nst.get(1).getDatetime();
				//Time3=nst.get(2).getDatetime();
				//Time4=nst.get(3).getDatetime();
				%>
					<div class="list-group">
						<a href="#" class="list-group-item list-group-item-success">Time1</a>
						<a href="#" class="list-group-item list-group-item-info">Time2</a>
						<a href="#" class="list-group-item list-group-item-warning">Time3</a>
						<a href="#" class="list-group-item list-group-item-danger">Time4</a>
					</div>
				</table>
				<nav>
				<ul class="pagination">
					<li><a href="#"><span aria-hidden="true">&laquo;</span><span
							class="sr-only">Previous</span></a></li>
					<li><a href="#">1</a></li>
					<li><a href="#">2</a></li>
					<li><a href="#">3</a></li>
					<li><a href="#">4</a></li>
					<li><a href="#">5</a></li>
					<li><a href="#"><span aria-hidden="true">&raquo;</span><span
							class="sr-only">Next</span></a></li>
				</ul>
				</nav>
			</div>
		</div>
	</div>
</body>
</html>