<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>register</title>
<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body>
<style>
div {
    background-color: lightgrey;
    width: 300px;
    padding: 25px;
    border: 25px solid navy;
    margin: 25px;
}

body {
    background-color: ;
    background:url(images/TT.jpg);
    background-repeat: no-repeat;
   
    
}

h1 {
    color:blue;
    text-align: center;
    border: 1px solid blue;
    outline: green dotted thick;
}

p {
    font-family: "Times New Roman",Times, serif;;
    font-size: 20px;
    text-align:
}
</style>

<h1>Welcome New Users</h1>
	<form action="/Etertainment2/register" method="post">
		<p>username:</p>
		<p><input name="username" class="form-control"/></p>
		<p>Email:</p>
		<p><input name="email" class="form-control"/></p>
		
		<p>Password:</p>
		<p><input name="password" type="password"  class="form-control"/></p>
		
		<p>Confirm Password:</p>
		<p><input name="password1" type="password"  class="form-control"/></p>
		
		<p>
		<button class="btn btn-primary btn-block">Register</button>
		</p>
		
	</form>	
	
	
</body>
</html>