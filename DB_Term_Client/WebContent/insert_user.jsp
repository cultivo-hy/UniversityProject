<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "myPackage.*" %>
<%@ page import = "java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
	   UserBean user = UserDAO.selectUser((request.getParameter("email")));
	   if(user == null){
		    UserBean u = new UserBean();
		    String tmp =request.getParameter("email") ;
		    String email = tmp;
		    if(tmp != null) u.setEmail(tmp);
		    tmp = request.getParameter("password");
		    if(tmp != null) u.setPw(tmp);
		    tmp = request.getParameter("lastName");
		    if(tmp != null) u.setLastName(tmp);
		    tmp = request.getParameter("familyName");
		    if(tmp != null) u.setFamilyName(tmp);
		    tmp = request.getParameter("birth");
		    if(tmp != null) u.setBirth(tmp);
		    UserDAO.insertUser(u);
		    RankDAO.insert(email);
	   }
		response.sendRedirect("index.html");
	%>

</body>
</html>