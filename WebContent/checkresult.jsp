<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"   import="tables.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<% String  action = request.getParameter("action");
	System.out.println(action);
	String  sciencefiction = request.getParameter("sciencefiction");
	System.out.println(sciencefiction);
	User user=(User)request.getSession().getAttribute("user");
	System.out.println(user);
	%>
</body>
</html>