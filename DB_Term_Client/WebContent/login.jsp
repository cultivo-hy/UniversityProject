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
		if(user != null)
		{
			String enterEmail = request.getParameter("email");
			String enterPassword = request.getParameter("password");
			String email = user.getEmail();
			String password = user.getPw();
			// class type 보내는 것도 구성		
			if(email.compareTo(enterEmail)==0){
				if(password.compareTo(enterPassword)==0){
			        if(email.compareTo("admin")==0){
			        	response.sendRedirect("admin.jsp");	
			        }
			        else{
						session.setAttribute("lastName",user.getLastName());
				        session.setAttribute("familyName", user.getFamilyName());
				        session.setAttribute("email", email);
			        	response.sendRedirect("user.jsp");	
			        }			
				}
				else{
					response.sendRedirect("index.html");
				}
			}
			else{
				response.sendRedirect("index.html");
			}
		}
		else{
			response.sendRedirect("index.html");
		}
			
	 %>
</body>
</html>