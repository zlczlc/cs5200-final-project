<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"  import="tables.*, DAOs.*, java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>profile</title>
<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body>

<div class="container">
<style>
li {
    list-style-type: none;
    margin: 0;
    padding: 0;
    overflow: hidden;
}

h1 {
    color:blue;
    text-align: center;
    border: 1px solid red;
    outline: green dotted thick;
}
}
</style>

	<h1>Edit Your File </h1>
	<FORM ACTION=/Etertainment2/profile method="post">
<BR>
<font size=3 color="black">
select the MOVIE preferences: <br>
<% 
	MovieDao mdao=new MovieDao() ;
	Movietype mt=null;
	List<Movietype> mts = mdao.findAllMovietypes();
	request.getSession().setAttribute("movietypes", mts);
	for (int i=0; i<mts.size();i++){
		mt=mts.get(i);
		
%>
<input TYPE=checkbox name=<%=mt.getType().replace(" ","")%> VALUE=<%=mt.getType()%>> <%=mt.getType() %>
<span style="font-size:12px;">&nbsp;&nbsp;&nbsp;</span>

<%};   %>
<BR>
<BR>
select the SPORT preferences: <br>

<input TYPE=checkbox name= baseball VALUE=1> baseball
<input TYPE=checkbox name= football VALUE=1> football
<input TYPE=checkbox name= basketball VALUE=1> basketball

<BR>
<BR>
<BR>
<input type=text name=zipcode> zipcode
<BR>
<br> <INPUT TYPE=submit name=submit Value="Submit">

</font>
</FORM>
</div>
</body>
</html>