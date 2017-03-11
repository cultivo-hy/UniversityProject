<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "myPackage.*" %>
<%@ page import = "java.sql.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,700" rel="stylesheet">
    <link rel="stylesheet" href="css/animate.css">
       <link rel="stylesheet" href="css/icomoon.css">
    <link rel="stylesheet" href="css/themify-icons.css" />
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/magnific-popup.css">
    <link rel="stylesheet" href="css/owl.carousel.min.css">
    <link rel="stylesheet" href="css/owl.theme.default.min.css">
    <link rel="stylesheet" href="css/style.css">
    <script src="js/modernizr-2.6.2.min.js"></script>
	<script language="javascript">
		  function form_update() {
		    document.frm.target = 'ifrm';
		    document.frm.action = 'admin_showUserList.jsp';
		    document.frm.submit();
		  }
		  function form_clear(){
			document.frm.target = 'ifrm';
		    document.frm.action = 'delete_all_user.jsp';
		    document.frm.submit(); 
		  }
		  function showSellingAll(){
			document.frm.target = 'ifrm';
			document.frm.action = 'seller_sellInfo.jsp';
			document.frm.submit();   
		  }
		  function showSellingCount(){
		    document.frm.target = 'ifrm';
			document.frm.action = 'seller_sellCount.jsp';
			document.frm.submit(); 
		  }
	</script>
	<body>
	<div id="page">

	<div class="page-inner">
	<nav class="gtco-nav" role="navigation">
		<div class="gtco-container">
			<div class="row">
				<div class="col-sm-4 col-xs-12">
					<div id="gtco-logo"><a href="admin.jsp">PNUIPS</a></div>
				</div>
			</div>
		</div>
	</nav>
	<header id="gtco-header" class="gtco-cover" role="banner" style="background-image: url(images/img_2.jpg)">
		<div class="overlay"></div>
		<div class="gtco-container">
			<div class ="col-md-15 col-md-offset-0 text-right">
			 <br><br><br>
			<a href="logout.jsp"><button>logout</button></a>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
       		</div>
		</div>
		<div class="gtco-container col-md-15 col-md-offset-0 text-left">
			<div class="form-wrap" style="background-image: url(images/img_0.jpg);" >
				<form name='frm' style="position : fixed; width : 1000px; background-image: url(images/img_0.jpg);">
					<div class = "text-right" style = "padding-top : 4px; padding-bottom : 4px; padding-right : 4px">
						<input type="text" name="search"/>
						<input type="button" value="seller 판매내역" onclick="showSellingAll()"/>
						<input type="button" value="seller 판매량" onclick="showSellingCount()"/>
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="전체 User 출력" onclick="form_update()"/>
						<input type="button" value="전체 User 초기화" onclick="form_clear()" />
						&nbsp;&nbsp;&nbsp;&nbsp;
						Hello admin!!
						&nbsp;&nbsp;&nbsp;&nbsp;
					</div>
					<div>
					<iframe name='ifrm' style=" position: fixed; height: 550px; width: 1000px; border:2px solid grey; background-image: url(images/img_0.jpg);">		
					</iframe>
					</div>
				</form>
			</div>
		</div>
	</header>
	</div>
	</div>
	</body>
</html>