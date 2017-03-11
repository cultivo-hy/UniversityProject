<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "myPackage.*" %>
<%@ page import = "java.sql.*" %>
<%@ page import = "java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<style type="text/css">
	 <!--
	  table {
	    text-align: center;
	  }
	  input {
	  	border:0px;	  
	  	text-align: center;
	  	height :30px;
	  }
	  td {
	  	height : 10px;
	  }
	 //-->
	 </style>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
	<title>Insert title here</title>
</head>
<body>
	<%
	String start = request.getParameter("search1");
	String end = request.getParameter("search2");
	List<String> resultSet = SaleDAO.getSalePeriod(start, end);
	
	Iterator<String> iter = resultSet.iterator();
	%>
	<div style="width:100%; height:100%; white-space:nowrap;">
	<form name = "ifrm"> 
		<table border="1" >
		<label><%= start.replace("T"," ") %> ~ <%=end.replace("T"," ") %> 기간 사이의 TOP 3 Best Selling Item</label>
		<tr>
			<td>name</td>
			<td>sellerName</td>
			<td>price</td>
			<td>brand</td>
			<td>allSell</td>
		</tr>
		<%
	    while(iter.hasNext()) { //rs 를 통해 테이블 객체들의 필드값을 넘겨볼 수 있다.
		    String name =iter.next();
		    String sellerName =iter.next();
		    String price =iter.next();
		    String brand =iter.next();
		    String allSell =iter.next();
		%>
		<tr>
			<td><input type="text" size="13" name = "name" value = "<%=name%>"/></td>
			<td><input type="text" size="12" name = "sellerName" value = "<%=sellerName %>"/></td>
			<td><input type="text" size="10" name = "price" value = "<%=price%>"/></td>
			<td><input type="text" size="10" name = "price" value = "<%=brand%>"/></td>
			<td><input type="text" size="10" name = "price" value = "<%=allSell%>"/></td>
		</tr>
		<%
		    } // end while
		%>
		</table>
		</form>
	</div>
</body>
</html>