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
		List<String> resultSet = SaleDAO.getBestSelling();
		Iterator<String> iter = resultSet.iterator();
	%>
	<div style="width:100%; height:100%; white-space:nowrap;">
		<form name = "ifrm"> 
			<table border="1" >
			<label>20��,30�뿡�� �������� ���� ���� �ȸ� TOP 10 Item</label>
			<tr>
				<td>Name</td>
				<td>Brand</td>
			</tr>
			<%
		    while(iter.hasNext()) { //rs �� ���� ���̺� ��ü���� �ʵ尪�� �Ѱܺ� �� �ִ�.
			    String name =iter.next();
			    String brand =iter.next();

			%>
			<tr>
				<td><input type="text" size="12" name = "sellerName" value = "<%=name %>"/></td>
				<td><input type="text" size="10" name = "price" value = "<%=brand%>"/></td>

			</tr>
			<%
			    } // end while
			%>
	
			</table>
		</form>
	</div>
</body>
</html>