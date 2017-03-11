<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
</head>
<body>
<%@ page import = "java.sql.*" %>
<%
Connection conn = null; //초기화
Statement stmt = null;
try {
String url = "jdbc:mysql://localhost:3306/marketdata";      // URL, "jdbc:mysql://localhost:3306/(mySql에서 만든 DB명)" << 입력 이때 3306은 mysql기본 포트
String id = "root";         // SQL 사용자 이름
String pw = "ghdyd1";     // SQL 사용자 패스워드
Class.forName("com.mysql.jdbc.Driver");              // DB와 연동하기 위해 DriverManager에 등록한다.
conn=DriverManager.getConnection(url,id,pw);    // DriverManager 객체로부터 Connection 객체를 얻어온다.
//out.println("연결됨...");      // 커넥션이 제대로 연결되면 수행된다.
} catch(Exception e) {     // 예외 처리
e.printStackTrace();
}
stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_UPDATABLE);

%>
<%

ResultSet rs1 = stmt.executeQuery("select * from products where market = 1 and event = 0");
rs1.last();
int oneEvent = rs1.getRow();
request.setAttribute("one",oneEvent);

ResultSet rs2 = stmt.executeQuery("select * from products where market = 1 and event = 1");
rs2.last();
int twoEvent = rs2.getRow();
request.setAttribute("two",twoEvent);

ResultSet rs3 = stmt.executeQuery("select * from products where market = 1 and event = 2");
rs3.last();
int plusEvent = rs3.getRow();
request.setAttribute("plus",plusEvent);

ResultSet rs = stmt.executeQuery("select * from products where market = 1");
rs.last();
int rowCount = rs.getRow();
rs.beforeFirst();
request.setAttribute("rs", rs);// main으로 list넘기기
request.setAttribute("num",rowCount);

%>
<jsp:forward page="JSP_FOLDER/UI/module/productshowWITHME.jsp"></jsp:forward>

</body>
</html>