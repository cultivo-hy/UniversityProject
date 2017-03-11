<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>DB Control site</title>

<style type = "text/css">

#button1{
background: #75BC00;
height:35px;
width:49%;
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

#button2{
background: #2e8ce3;
height:35px;
width:49%;
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
	<button type="button" id="button1" onclick="button1_click();">DB Reset</button>
	<script>
	function button1_click() {
		self.location="DB_Reset.jsp";
	}
	</script>
	<button type="button" id="button2" onclick="button2_click();">Data Insert</button>
	<script>
	function button2_click() {
		self.location="DB_Insert.jsp";
	}
	</script>
</body>
</html>