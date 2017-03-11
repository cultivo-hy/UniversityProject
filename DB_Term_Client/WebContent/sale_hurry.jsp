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
	List<String> resultSet = SaleDAO.getSaleHurry();
	Iterator<String> iter = resultSet.iterator();
	%>
	<div style="width:100%; height:100%; white-space:nowrap;">
	<form name = "ifrm"> 
		<table border="1" >
		<label>재고가 카트에 담긴 걍보다 작은 제품 = 빨리 사야 되는 제품</label>
		<tr>
			<td>itemCode</td>
			<td>sellerName</td>
			<td>sumOfCart</td>
			<td>numberOfStocks</td>
			<td>price</td>
		</tr>
		<%
	    while(iter.hasNext()) { //rs 를 통해 테이블 객체들의 필드값을 넘겨볼 수 있다.
		    String itemCode =iter.next();
		    String sellerName =iter.next();
		    String sumOfCart =iter.next();
		    String numberOfStocks =iter.next();
		    String price =iter.next();
		%>
		<tr>
			<td><input type="text" size="13" name = "name" value = "<%=itemCode%>"/></td>
			<td><input type="text" size="12" name = "sellerName" value = "<%=sellerName %>"/></td>
			<td><input type="text" size="10" name = "price" value = "<%=sumOfCart%>"/></td>
			<td><input type="text" size="10" name = "price" value = "<%=numberOfStocks%>"/></td>
			<td><input type="text" size="10" name = "price" value = "<%=price%>"/></td>
		</tr>
		<%
		    } // end while
		%>
		</table>
		</form>
	</div>
</body>
</html>