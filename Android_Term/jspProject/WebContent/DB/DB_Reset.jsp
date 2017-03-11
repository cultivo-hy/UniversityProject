<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<style type = "text/css">

#button1{
background: #75BC00;
padding:7px 30px 7px 30px;
font-size:15px;
font-weight:bold;
color:#000000;
text-align:center;
border:solid 1px #99E000;
background: -moz-linear-gradient(0%, 100%, 90deg, #75BC00, #ffffff);
background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#ffffff), to(#75BC00));
border-radius:5px;
-moz-border-radius:5px;
-webkit-border-radius:5px;
border-bottom-color:#3F8600;
text-shadow:0 -1px 0 #3F8600;
}

</style>

</head>
<body>
<%@ page import = "java.sql.*" %>
<%
Connection conn = null; //초기화
Statement stmt = null;
String sql = null;
try {
String url = "jdbc:mysql://localhost:3306/marketdata";      // URL, "jdbc:mysql://localhost:3306/(mySql에서 만든 DB명)" << 입력 이때 3306은 mysql기본 포트
String id = "root";         // SQL 사용자 이름
String pw = "ghdyd1";     // SQL 사용자 패스워드
Class.forName("com.mysql.jdbc.Driver");              // DB와 연동하기 위해 DriverManager에 등록한다.
conn=DriverManager.getConnection(url,id,pw);    // DriverManager 객체로부터 Connection 객체를 얻어온다.
out.println("DB 연결되었습니다...");      // 커넥션이 제대로 연결되면 수행된다.
stmt = conn.createStatement();
sql = "drop table products;";
out.println("테이블이 삭제되었습니다 ...");
stmt.executeUpdate(sql);
sql = null;
sql = 	"CREATE TABLE `products` (" +
		"`id` int(5) NOT NULL AUTO_INCREMENT, " +
		"`name` text NOT NULL, " +
		"`cost` int(6) NOT NULL, " +
		"`src` text NOT NULL, " +
		"`market` int(1) NOT NULL, " +
		"`event` int(1) NOT NULL, "+
		"`category` int(1) NOT NULL, " +
		"PRIMARY KEY (id)" +
		") ENGINE=InnoDB DEFAULT CHARSET=utf8;";
stmt.executeUpdate(sql);
out.println("새로운 테이블이 생성되었습니다...");
} catch(Exception e) {     // 예외 처리
	e.printStackTrace();
}

if(stmt !=null){
	try {
		stmt.close();
	} catch(Exception e){}
}
if(conn != null){
	try {
		conn.close();
	} catch(Exception e){}
}
%>
<button id="button1" onclick="button1_click();">돌아가기</button>
<script>
function button1_click() {
	self.location="DB_Control.jsp";
}
</script>
</body>
</html>