<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	<FORM TYPE=POST ACTION=checkresult.jsp>
<BR>
<font size=3 color="black">
select the MOVIE preferences: <br>

<input TYPE=checkbox name=fruit VALUE=action> action
<span style="font-size:12px;">&nbsp;&nbsp;&nbsp;</span>
<input TYPE=checkbox name=fruit VALUE=affectional> affectional
<input TYPE=checkbox name=fruit VALUE=sciencefiction> sciencefiction<BR>
<BR>
<BR>
select the SPORT preferences: <br>

<input TYPE=checkbox name=fruit VALUE=> baseball
<input TYPE=checkbox name=fruit VALUE=> football
<input TYPE=checkbox name=fruit VALUE=> basketball

<BR>
<br> <INPUT TYPE=submit name=submit Value="Submit">

</font>
</FORM>
</div>
</body>
</html>