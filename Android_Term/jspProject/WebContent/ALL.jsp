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
Connection conn = null; //�ʱ�ȭ
Statement stmt = null;
try {
String url = "jdbc:mysql://localhost:3306/testdata";      // URL, "jdbc:mysql://localhost:3306/(mySql���� ���� DB��)" << �Է� �̶� 3306�� mysql�⺻ ��Ʈ
String id = "root";         // SQL ����� �̸�
String pw = "ghdyd1";     // SQL ����� �н�����
Class.forName("com.mysql.jdbc.Driver");              // DB�� �����ϱ� ���� DriverManager�� ����Ѵ�.
conn=DriverManager.getConnection(url,id,pw);    // DriverManager ��ü�κ��� Connection ��ü�� ���´�.
//out.println("�����...");      // Ŀ�ؼ��� ����� ����Ǹ� ����ȴ�.
} catch(Exception e) {     // ���� ó��
e.printStackTrace();
}
stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
        ResultSet.CONCUR_UPDATABLE);

%>
<%
ResultSet rs1 = stmt.executeQuery("select distinct name, cost, src, market, event, category  FROM testdata.products where category=0");
rs1.last();
int one= rs1.getRow();
request.setAttribute("one",one);

ResultSet rs2 = stmt.executeQuery("select distinct name, cost, src, market, event, category  FROM testdata.products where category=1");
rs2.last();
int two = rs2.getRow();
request.setAttribute("two",two);

ResultSet rs3 = stmt.executeQuery("select distinct name, cost, src, market, event, category  FROM testdata.products where category=2 ");
rs3.last();
int three = rs3.getRow();
request.setAttribute("three",three);

ResultSet rs4 = stmt.executeQuery("select distinct name, cost, src, market, event, category  FROM testdata.products where category=3 ");
rs4.last();
int four = rs4.getRow();
request.setAttribute("four",four);

ResultSet rs = stmt.executeQuery("select distinct name, cost, src, market, event, category FROM testdata.products order by  category asc , cost asc");
rs.last();
int rowCount = rs.getRow();
rs.beforeFirst();

request.setAttribute("rs", rs);// main���� list�ѱ��
request.setAttribute("num",rowCount);
%>
<jsp:forward page="JSP_FOLDER/UI/module/productshowALL.jsp"></jsp:forward>

</body>
</html>