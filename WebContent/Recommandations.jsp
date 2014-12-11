<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="tables.*, java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="icon" href="../../favicon.ico">

    <title>search movies</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
     

    

   
    

    
  </head>

  <body>
  	<%
  		User user = (User) request.getSession().getAttribute("user");
		//System.out.println("zipcode: "+user.getCity().getZipcode());
		if (user==null){
			response.sendRedirect("/Etertainment2/login.jsp");
			return;
		}
  		List<Movie> movies = (List<Movie>)request.getSession().getAttribute("movies"); 
  		//System.out.println("numofm"+movies.size());
  		if (movies==null)
  			movies = new ArrayList<Movie>();
    	Movie	movie = (Movie)request.getSession().getAttribute("movie");
    	if (movie == null &&  movies!=null && movies.size()>0)
    		movie = movies.get(0);
    	if (movie == null){
    		movie = new Movie();
    		movie.setMoviename("Sorry!!!");
    		movie.setLongDescription("Descripton");
    		movie.setShortDescription("Descripton");
    		//movie.setOfficialUrl("not available");
    		movie.setQualityRating("??");
    		movie.setRating("class");
    		movie.setReleaseYear("Not available");
    		//movie.setRatingsBody("not available");
    		//movie.setImageUrl("");
    		
    	}
    	//if (movie.getImageUrl()==null)
    	//	movie.setImageUrl("");
    	String index = (String)request.getSession().getAttribute("index");
    	System.out.println(index);
    	if(index==null) 
    		index="0";
    	
    	//String search = (String) request.getParameter("search");
		String searchKeyWord = (String) request.getSession().getAttribute("searchKeyWord");
		String inTheatre = (String) request.getSession().getAttribute("inTheatre");
		String numOfDays = (String) request.getSession().getAttribute("numOfDays");
		String radius = (String) request.getSession().getAttribute("radius");
		String zipcode = (String) request.getSession().getAttribute("zipcode");
		String showRec = (String) request.getSession().getAttribute("showRec");
		
		if (zipcode == null && user!=null)
			zipcode = user.getCity().getZipcode();
		if (zipcode == null)
			zipcode = "02115";
		if (radius == null)
			radius = "10";
		if (numOfDays == null)
			numOfDays = "10";
		if (inTheatre == null)
			inTheatre = "1";
		
		if (showRec == null)
			showRec = "1";
		if ( searchKeyWord == null)
			 searchKeyWord = "moviename";
  	
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
          <p class="navbar-brand" href="#"><strong>Search for fan!</strong></p>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="#">Home</a></li>
            <li><a href="/Etertainment2/Profile.jsp">Profile</a></li>
            <li><a href="/Etertainment2/event">Schedule</a></li>
          </ul>
        </div><!-- /.nav-collapse -->
      </div><!-- /.container -->
    </nav><!-- /.navbar -->

    <div class="container">
    
   
    
    <div>
			<h1>
				Seach your movies
				<p class="text-right">
					<a class="btn btn-info" href="/Etertainment2/Logout.jsp" >logout</a>
				</p>
			</h1>
		</div>
		
      <div class="row row-offcanvas row-offcanvas-right">
		
		
		
		
		 <div class="col-xs-6 col-sm-2 sidebar-offcanvas" id="sidebar" role="navigation">
          <div class="list-group">
          	
          	<h1><a href="#" class="list-group-item  active" >Movies</a></h1>
            <a href="#" class="list-group-item " >Sports</a>
            <a href="#" class="list-group-item " >Concerts</a>
           	
           	<a href="#" class="list-group-item">TV shows</a>
            
            
            
          </div>
          <hr>
          
        
        
          <form action="/Etertainment2/rec" method="post">
          	
          	
          	<div class="input-group">
      <span class="input-group-btn">
        <button type="submit" class="btn btn-default" name="search" value="1" aria-label="Left Align">
  			<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
		</button>

      </span>
      <input type="text" class="form-control" name="searchKeyWord" placeholder=<%= searchKeyWord %>>
    </div><!-- /input-group -->

          	
          </form>
         
         
          <hr>
        <div class="thumbnail">
        
           <form action="/Etertainment2/rec" method="post">
           
				<h4>&nbsp;My filter:</h4>
				<hr>
				<h4><div class="radio">
				  <label>
				    <input type="radio" name="inTheatre"  id="optionsRadios1" value="1"        <%if (inTheatre.equals("1")) {%>checked  <%} %> >
				     --In theater
				  </label>
				</div></h4>
				<h4><div class="radio">
				   <label>
				    <input type="radio" name="inTheatre"  id="optionsRadios2" value="0"         <%if (inTheatre.equals("0")) {%>checked  <%} %> >
				    --Upcoming
				  </label>
				</div></h4>
			<hr>	
			
			<div class="radio">
			  <label>
			    <input type="radio" name="showRec" id="optionsRadios1" value="1" <%if (showRec.equals("1")) {%>checked  <%} %>  >
			    Recommendations 
			  </label>
			</div>
			<h4><div class="radio">
			  <label>
			    <input type="radio" name="showRec" id="optionsRadios2" value="0"     <%if (showRec.equals("0")) {%>checked  <%} %> >
			    Show me all
			  </label>
			</div></h4>

			<hr>
			<h4>Show results in <select name="numOfDays">
			  <option><%=numOfDays %></option>
			  <option>1</option>
			  <option>5</option>
			  <option>10</option>
			  <option>15</option>
			  <option>25</option>
			</select> days!</h4>
			 <hr>
			 <h4>zipcode:<input type="text" class="form-control"name="zipcode" placeholder=<%=zipcode %>></h4>
			 <h4>radius in <select name="radius">
			 <option><%=radius %></option>
			  <option>1</option>
			  <option>5</option>
			  <option>10</option>
			  <option>15</option>
			  <option>25</option>
			</select> miles!</h4>
			 <button type="submit" class="btn btn-default form-control">Go!</button>
			 

			</form>	
          
        </div>
        
        
          
        </div><!--/.sidebar-offcanvas-->
        
        
        
        
        <div class="col-xs-12 col-sm-10">
          <p class="pull-right visible-xs">
            <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
          </p>
          <div class="jumbotron">
            <h1><%=movie.getMoviename()  %></h1>
            <div class="row">
            	<div  class="col-xs-6 col-lg-7"><img src=<%="http://developer.tmsimg.com/movieposters"+movie.getImageUrl()+"?api_key=9gnk9dw7rbpzg55wmk8qamn9&h=600"%><% System.out.println(movie.getImageUrl());%> alt="" class="img-responsive"/></div>
            	<div  class="col-xs-6 col-lg-5">
            		<table  class="table">
            			<tr><td><h3>rate: &nbsp;&nbsp;&nbsp;<%=movie.getQualityRating() %></h3></tr>
            			<tr><td><h3>By:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=movie.getRatingsBody() %></h3></td></tr>    
            			<tr><td><h3>class: &nbsp;<%=movie.getRating() %></h3></td></tr> 
            			<tr><td><h3>release: <%=movie.getReleaseYear()%></h3></td></tr>
            			<tr><td><h3>run time: <%=movie.getRunTime()%></h3></td></tr> 
            			<tr><td><h3>Top Cast:&nbsp;&nbsp;</h3></td></tr> 
            			<tr><td><p>&nbsp;<%=movie.getTopCast() %></p></td></tr> 
            			
            			<%if(movie.getOfficialUrl()!=null) { %><tr><td><h3>Official Site:&nbsp;&nbsp; <a class="btn btn-primary btn-lg" href=<%=movie.getOfficialUrl() %> role="button">&nbsp;&nbsp;go&nbsp;&nbsp;</a></h3></td></tr>  <%} %>
            			
            			
            		</table>
            		
            		
            		
            	</div>
            		
            </div>
            <tr><td><h2><a class="btn btn-primary btn-lg form-control" href="MovieDetail2.jsp" role="button">&nbsp;&nbsp;See tickets!!!&nbsp;&nbsp;</a></h2></td></tr>  
            			<tr><td><h2><a class="btn btn-primary btn-lg form-control" href="#" role="button">&nbsp;&nbsp;add to my schedule&nbsp;&nbsp;</a></h2></td></tr>  
            <hr>
            <p><%=movie.getLongDescription()%></p>
            <%List<Movietype> mts=movie.getMovietypes(); int sz=0; if (mts!=null){sz = mts.size();} %>
          	<%if( 0<sz ){%><span class="label label-default"><%=mts.get(0).getType() %></span><%} %>
			<%if( 1<sz ){%><span class="label label-primary"><%=mts.get(1).getType() %></span><%} %>
			<%if( 2<sz ){%><span class="label label-success"><%=mts.get(2).getType() %></span><%} %>
			<%if( 3<sz ){%><span class="label label-primary"><%=mts.get(3).getType() %></span><%} %>
			<%if( 4<sz ){%><span class="label label-info"><%=mts.get(4).getType() %></span><%} %>
			<%if( 5<sz ){%><span class="label label-warning"><%=mts.get(5).getType() %></span><%} %>
			<%if( 6<sz ){%><span class="label label-danger"><%=mts.get(6).getType() %></span><%} %>
			
          </div>
          
          
          
       
          
        
          <hr>
          <%
          	int i = Integer.parseInt(index); //index=String.valueOf(i);
          	System.out.println(index); 
          	sz= movies.size();
          	Movie m = null;
          %>
         
         <form action="/Etertainment2/rechangepage" method="post">
          <div class="col-xs-6 col-lg-3">
              <%if (sz>4*i+0) { m=movies.get(4*i+0);} else{m = movie;}%>
	              
	              <h4><img class="img-responsive" class="img-responsive" src=<%="http://developer.tmsimg.com/movieposters"+m.getImageUrl()+"?api_key=9gnk9dw7rbpzg55wmk8qamn9&h=300"%><%System.out.println("http://developer.tmsimg.com/movieposters"+m.getImageUrl()+"?api_key=9gnk9dw7rbpzg55wmk8qamn9&h=300"); %> alt="" class="img-responsive"/></h4>
	              <h3><%=m.getMoviename() %></h3>
	              <h4>Class: &nbsp;<%=m.getRating() %> </h4>
	              <h4>Rating: <%=m.getQualityRating() %> </h4>
	               <h4><button class="btn btn-default"  name = "page"  value ="0"  role="submit">View details &raquo;</button></h4>
              
             </div>
             <div class="col-xs-6 col-lg-3">
              <%if (sz>4*i+1) { m=movies.get(4*i+1);} else{m = movie;}%>
	              
	              <h4><img class="img-responsive" src=<%="http://developer.tmsimg.com/movieposters"+m.getImageUrl()+"?api_key=9gnk9dw7rbpzg55wmk8qamn9&h=300"%> alt="" class="img-responsive"/></h4>
	              <h3><%=m.getMoviename() %></h3>
	              <h4>Class: &nbsp;<%=m.getRating() %> </h4>
	              <h4>Rating: <%=m.getQualityRating() %> </h4>
	               <h4><button class="btn btn-default"  name = "page"  value ="1"  role="submit">View details &raquo;</button></h4>
              
             </div>
             <div class="col-xs-6 col-lg-3">
              <%if (sz>4*i+2) { m=movies.get(4*i+2);} else{m = movie;}%>
	              
	              <h4><img class="img-responsive" src=<%="http://developer.tmsimg.com/movieposters"+m.getImageUrl()+"?api_key=9gnk9dw7rbpzg55wmk8qamn9&h=300"%> alt="" class="img-responsive"/></h4>
	              <h3><%=m.getMoviename() %></h3>
	              <h4>Class: &nbsp;<%=m.getRating() %> </h4>
	              <h4>Rating: <%=m.getQualityRating() %> </h4>
	               <h4><button class="btn btn-default"  name = "page"  value ="2"  role="submit">View details &raquo;</button></h4>
              
             </div>
            <div class="col-xs-6 col-lg-3">
              <%if (sz>4*i+3) { m=movies.get(4*i+3);} else{m = movie;}%>
	              
	              <h4><img class="img-responsive" src=<%="http://developer.tmsimg.com/movieposters"+m.getImageUrl()+"?api_key=9gnk9dw7rbpzg55wmk8qamn9&h=300"%> alt="" class="img-responsive"/></h4>
	              <h3><%=m.getMoviename() %></h3>
	              <h4>Class: &nbsp;<%=m.getRating() %> </h4>
	              <h4>Rating: <%=m.getQualityRating() %> </h4>
	              <h4><button class="btn btn-default"  name = "page"  value ="3"  role="submit">View details &raquo;</button></h4>
              
             </div>
            
            </form>
            
          
          
            
            
           
             
          </div><!--/row-->
          <form action="/Etertainment2/rechangepage" method="post">
          <% int ii = i-1; int iii=i+1; %>
           <h5><nav>
  				<ul class="pager">
    				<li><button class="btn btn-default" name="recpage"   value =<%=ii %>>Previous</button></li>
    				&nbsp;<%=i+1 %> &nbsp;
   					<li><button class="btn btn-default" name="recpage"   value =<%=iii %>> Next   </button></li>
  				</ul>
			</nav></h5></form>
           
        </div><!--/.col-xs-12.col-sm-9-->

       
      </div><!--/row-->

      <hr>

      <footer>
        <p>By ZFS</p>
      </footer>

    </div><!--/.container-->
          
          


   
  </body>
</html>

