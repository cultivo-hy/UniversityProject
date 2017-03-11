<%@ page language="java" contentType="text/html; charset=EUC-KR"%>
<%@ page import = "myPackage.*" %>
<%@ page import = "java.sql.*" %>
<%@ page import = "java.util.*" %>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
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
	</head>
	<script>
		function modify(){
			var popUrl = "userInfo.html";	//팝업창에 출력될 페이지 URL
			var popOption = "width=500, height=600, resizable=no, scrollbars=no, status=no;";    //팝업창 옵션(optoin)
			window.open(popUrl,"",popOption);
			document.frm.target = 'ifrm';
		    document.frm.action = 'admin_UserList.jsp';
		    document.frm.submit();     
		}
		function deleteUser(){
			
		}
	</script>
	<body>
	<%
	   List<UserBean> list =  UserDAO.selectAllStudent();
	   Iterator<UserBean> iter = list.iterator();
	%>
	<div style="width:100%; height:100%; white-space:nowrap;">
	<form name = "ifrm"> 
		<table border="1" cellspacing="0">
		<tr>
			<td>Email</td>
			<td>passwd</td>
			<td>lastName</td>
			<td>familyName</td>
			<td>birth</td>
			<td>check</td>
		</tr>
		<%
	    while(iter.hasNext()) { //rs 를 통해 테이블 객체들의 필드값을 넘겨볼 수 있다.
		UserBean tmp =iter.next();
		%>
		<tr>
			<td><input type="text" size="18" name = "email" value = "<%=tmp.getEmail()%>"/></td>
			<td><input type="text" size="12" name = "password" value = "<%=tmp.getPw() %>"/></td>
			<td><input type="text" size="12" name = "lastName" value = "<%=tmp.getLastName()%>"/></td>
			<td><input type="text" size="12" name = "familyName" value = "<%=tmp.getFamilyName()%>"/></td>
			<td><input type="text" size="8" name = "text" value = "<%=tmp.getBirth()%>"/></td>
			<td><input type="checkbox"/><td>
		</tr>
		<%
		    } // end while
		%>

		</table>
		</form>
	</div>

	</body>
</html>
	

