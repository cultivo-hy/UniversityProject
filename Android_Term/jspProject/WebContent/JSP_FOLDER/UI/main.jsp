<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
<%@ page import="jspProject.JavaTest"%>
<%@ page import="java.util.ArrayList"%>
<%
	JavaTest hw = new JavaTest();
	ArrayList<ArrayList<String>> tmp = null;

	//hw.getWithme(); // clear
	//tmp = hw.getMini();	  // clear
	//tmp = hw.getCu();	  // clear
	tmp.clear();
%>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, 
       maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<script type="text/javascript">
     function Init() {
    	 var img = document.createElement("img");
         var space = document.createTextNode("    ");
         img.src="image/object.png";
         img.width = (window.innerWidth > 0) ? (window.innerWidth / 2) - 20 : (screen.width / 2)- 20;
         document.getElementById("content").appendChild(img);
         document.getElementById("content").appendChild(space);  
    }
       
	$(document).ready(function() {
		$(window).scroll(function() {
			var scrollHeight = $(window).scrollTop()+$(window).height();
			var documentHeight = $(document).height();
			if (scrollHeight + 40 >= documentHeight && scrollHeight != documentHeight) {
				for(var i=0; i<4; i++){
					Init();
				}
			}
			});
	});               
		
</script>
</head>
<body>

<div data-role="page" id="pageone">
  <div data-role="header">
    <div data-role="navbar">
    <img src = "/WebContent/image/p.bmp"></img>
    <button onclick="Init();"></button>
      <ul>
        <li><a href="#" class="ui-btn-active ui-state-persist" > 1+1 </a></li>
        <li><a href="#pagetwo" > 2+1 </a></li>
        <li><a href="#pagethree"> ������� </a></li>
      </ul>
    </div>
    <div style="font : bold 9pt/1.3 ���� , padding: 5 10 10 5" >2016�� 5�� ����ǰ</div>
  </div>

  <div data-role="main" class="ui-content" id = "content" style = "padding-top: 10px;padding-left: 10px;padding-bottom: 10px;padding-right: 10px;">
  </div>

  <div data-role="footer" data-position="fixed" style="font : bold 9pt/1.3 ����">
    <h3> �� ��ǰ���� ���帶�� ����ǰ�� �ٸ� ���� �ֽ��ϴ�.</h3>
  </div>
</div> 

<div data-role="page" id="pagetwo">
  <div data-role="header">
    <img src = "image/p.bmp"></img>
    <div data-role="navbar">
      <ul>
        <li><a href="#pageone" > 1+1 </a></li>
        <li><a href="#" class="ui-btn-active ui-state-persist" > 2+1 </a></li>
        <li><a href="#pagethree" > ������� </a></li>
      </ul>
    </div>
    <div style="font : bold 9pt/1.3 ���� , padding: 5 10 10 5" >2016�� 5�� ����ǰ</div>
  </div>

  <div data-role="main" class="ui-content">
  </div>

  <div data-role="footer" data-position="fixed" style="font : bold 9pt/1.3 ����">
    <h3> �� ��ǰ���� ���帶�� ����ǰ�� �ٸ� ���� �ֽ��ϴ�.</h3>
  </div>
</div> 

<div data-role="page" id="pagethree">
  <div data-role="header">
    <img src = "image/p.bmp"></img>
    <div data-role="navbar">
      <ul>
      	<li><a href="#pageone" > 1+1 </a></li>
        <li><a href="#pagetwo" > 2+1 </a></li>
        <li><a href="#" class="ui-btn-active ui-state-persist" > ������� </a></li>
      </ul>
    </div>
    <div style="font : bold 9pt/1.3 ���� , padding: 5 10 10 5" >2016�� 5�� ����ǰ</div>
  </div>

  <div data-role="main" class="ui-content">
  </div>

  <div data-role="footer" data-position="fixed" style="font : bold 9pt/1.3 ����">
    <h3> �� ��ǰ���� ���帶�� ����ǰ�� �ٸ� ���� �ֽ��ϴ�.</h3>
  </div> 
</div> 

</body>
</html>
