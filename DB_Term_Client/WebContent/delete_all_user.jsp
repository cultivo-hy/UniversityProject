<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "myPackage.*" %>
<%@ page import = "java.sql.*" %>
<%@ page import = "java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<%
		Connection conn = ConnectionProvider.getConnection();	
		PreparedStatement pst = conn.prepareStatement("DELETE FROM RANK");
	 	pst.execute();
	 	pst = conn.prepareStatement("DELETE FROM COUPON");
	 	pst.execute();
	 	pst = conn.prepareStatement("DELETE FROM ITEM");
	 	pst.execute();
	 	pst = conn.prepareStatement("DELETE FROM SELL");
	 	pst.execute();
	 	pst = conn.prepareStatement("DELETE FROM CART");
	 	pst.execute();
	 	pst = conn.prepareStatement("DELETE FROM USERLIST WHERE email <>'admin'");
	 	pst.execute();
	 	pst.close();
		conn.close();
	%>
</body>
</html>