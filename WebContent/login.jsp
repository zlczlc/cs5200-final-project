<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>login</title>
<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<div class="container">
<style>

body {
   background-color: ;
    background:url(images/cat.jpg);
    background-repeat: no-repeat;
}

h1 {
    color:blue;
    text-align: center;
    border: 1px solid red;
    outline: green dotted thick;
}

p {
    font-family: "Times New Roman",Times, serif;;
    font-size: 20px;
    text-align:
}

l{
 font-family: "Times New Roman",Times, serif;;
    font-size: 20px;
    text-align:right;
    color:blue;
}
</style>


	<h1>Entertainment</h1>
	<form action="/Etertainment2/login" method="post">
		<p>Username:</p>
		<p><input name="username" /></p>
		
		<p>Password:</p>
		<p><input name="password" type="password" /></p>
		
	
		<p>
		<button class="btn btn-primary btn-block">Login</button>
		</p>
		
		
		
	</form>	
	<l> <a href="/Etertainment2/register.jsp">New User Register</a></l>
</div>
</body>
</html>