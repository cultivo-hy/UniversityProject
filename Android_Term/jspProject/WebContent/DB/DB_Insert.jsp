<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ page import="jspProject.JavaTest"%>
<%@ page import="java.util.ArrayList"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>

<style type = "text/css">

#button1{
background: #2e8ce3;
padding:7px 30px 7px 30px;
font-size:15px;
font-weight:bold;
color:#000000;
text-align:center;
border:solid 1px #73c8f0;
background: -moz-linear-gradient(0%, 100%, 90deg, #2e8ce3, #ffffff);
background: -webkit-gradient(linear, 0% 0%, 0% 100%, from(#ffffff), to(#2e8ce3));
border-radius:5px;
-moz-border-radius:5px;
-webkit-border-radius:5px;
border-bottom-color:#196ebb;
text-shadow:0 -1px 0 #196ebb;
}

</style>
</head>
<body>
<%@ page import = "java.sql.*" %>
<%@ page import = "java.util.ArrayList" %>
<%
	JavaTest hw = new JavaTest();
	ArrayList<ArrayList<String>> tmp = null;

	Connection conn = null; //초기화
	PreparedStatement pstmt = null;
	try {
		String url = "jdbc:mysql://localhost:3306/marketdata";      // URL, "jdbc:mysql://localhost:3306/(mySql에서 만든 DB명)" << 입력 이때 3306은 mysql기본 포트
		String id = "root";         // SQL 사용자 이름
		String pw = "ghdyd1";     // SQL 사용자 패스워드
		Class.forName("com.mysql.jdbc.Driver");              // DB와 연동하기 위해 DriverManager에 등록한다.
		conn=DriverManager.getConnection(url,id,pw);    // DriverManager 객체로부터 Connection 객체를 얻어온다.
		out.println("연결됨...");      // 커넥션이 제대로 연결되면 수행된다.
	} catch(Exception e) {     // 예외 처리
		e.printStackTrace();
	}

	
	for(int story=0; story<3; story++){
		
		if(story==1){
			tmp = hw.getWithme(); // clear
		}
		else if(story==2){
			tmp = hw.getMini();
		}
		else{
			tmp = hw.getCu();
		}
		for(int i = 0; i < tmp.size(); i=i+3){
			if(i < 3){
				for(int j = 0; j < tmp.get(i).size(); j++){
					pstmt = conn.prepareStatement("insert into products (name, cost, src, market, event, category) values(?,?,?,?,?,?) ");
					pstmt.setString(1, tmp.get(i).get(j));//상품명
					pstmt.setInt(2, Integer.parseInt(tmp.get(i+1).get(j).replace(",","")));//가격
					pstmt.setString(3,tmp.get(i+2).get(j));//src
					pstmt.setInt(4, story);//maket 0 (with me)
					pstmt.setInt(5, 0);//event 0 (1+1)
					pstmt.setInt(6, 0);//category 0 (default)
					pstmt.executeUpdate();
				}
			}
			else if(i < 6){
				for(int j = 0; j < tmp.get(i).size(); j++){
					pstmt = conn.prepareStatement("insert into products (name, cost, src, market, event, category) values(?,?,?,?,?,?) ");
					pstmt.setString(1, tmp.get(i).get(j));//상품명
					pstmt.setInt(2, Integer.parseInt(tmp.get(i+1).get(j).replace(",","")));//가격
					pstmt.setString(3,tmp.get(i+2).get(j));//src
					pstmt.setInt(4, story);//maket 0 (with me)
					pstmt.setInt(5, 1);//event 1 (2+1)
					pstmt.setInt(6, 0);//category 0 (default)
					pstmt.executeUpdate();
				}
			}
			else if(i < 9){
				for(int j = 0; j < tmp.get(i).size(); j++){
					pstmt = conn.prepareStatement("insert into products (name, cost, src, market, event, category) values(?,?,?,?,?,?) ");
					pstmt.setString(1, tmp.get(i).get(j));//상품명
					pstmt.setInt(2, Integer.parseInt(tmp.get(i+1).get(j).replace(",","")));//가격
					pstmt.setString(3,tmp.get(i+2).get(j));//src
					pstmt.setInt(4, story);//maket 0 (with me)
					pstmt.setInt(5, 2);//event 2 (덤증정)
					pstmt.setInt(6, 0);//category 0 (default)
					pstmt.executeUpdate();
				}
			}
		}
	}
	
	
	//데이터 삽입 끝
	if(pstmt !=null){
		try {
			pstmt.close();
		} catch(Exception e){}
	}
	if(conn != null){
		try {
			conn.close();
		} catch(Exception e){}
	}
	out.println("DB입력 완료");
	%>
	<button id="button1" onclick="button1_click();">돌아가기</button>
	<script>
	function button1_click() {
		self.location="DB_Control.jsp";
	}
</script>
</body>
</html>