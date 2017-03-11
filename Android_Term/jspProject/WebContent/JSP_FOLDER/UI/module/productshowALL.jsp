<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, width=device-width" />
<%-- header에서 필요한 친구들 --%>
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.css">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
<script src="http://code.jquery.com/mobile/1.4.5/jquery.mobile-1.4.5.min.js"></script>
<link rel="stylesheet" type="text/css" href="layout.css">
</head>
<%@ page import = "java.sql.*" %>


<%


ResultSet rs = (ResultSet)request.getAttribute("rs");
ResultSet compare = (ResultSet)request.getAttribute("rs");
int res = (int)request.getAttribute("num");
int one = (int)request.getAttribute("one");
int two = (int)request.getAttribute("two");
int three = (int)request.getAttribute("three");
int four = (int)request.getAttribute("four");
String[] aList = {"CU", "WITH ME", "MINI"};
boolean flag = true;
%>

<div data-role="page" id="pageone">
	<div data-role="header" data-position="fixed" style="top: 0px; border-top-width: 0px; padding-top: 0px;">
    <div data-role="navbar">
    <img id="haha" src = "image/MIN.png"></img>
      <ul>
        <li><a href="#" class="ui-btn-active ui-state-persist" > 음료 </a></li>
        <li><a href="#pagetwo" > 식품 </a></li>
        <li><a href="#pagethree"> 스낵 </a></li>
        <li><a href="#pagefour"> 생필품 </a></li>
      </ul>
    </div>
    <div style="font : bold 9pt/1.3 돋움 , padding: 5 10 10 5" >2016년 6월 행사상품</div>
  </div>

  <div data-role="main" class="ui-content" id = "content" style = "padding-top: 10px;padding-left: 10px;padding-bottom: 10px;padding-right: 10px;">
  	  	<%	
  	  	int max=0;
  	  	if(one%2 == 0){
  	  		max = (one/2);
  	  	}else{
  	  		max = (one/2)+1;
  	  	}
		for(int i=0; i<max; i++){
			rs.next();
		%>
		<table class="product">
		    <tr>
				<div>
					<img src='<%=rs.getString(3)%>'></img>
						<%
						  String name = rs.getString(1);
						  String cost = rs.getString(2);
						  String abs = rs.getString(4);
						  if( max == (one/2) || (max == ((one/2)+1) && (i != max-1) ))
							{
							rs.next();
						  	flag = true;%>
							<img src='<%=rs.getString(3)%>'></img>	
						<%}else{flag=false;}%>
				</div>
		    </tr>
		 </table>
		 <table class="product">
		 	<tr>
		        <div><td id=left_name><%=name%></td>
		            <% if(flag){ %>
		        	<td id=right_name><%=rs.getString(1)%></td>
		        	<% 	}
		        	%>
		      </div>
		    </tr>
		    <tr>
		        <div><td id=left_cost><%=cost%>원</td>
		         	<% if(flag){ %>
		        	<td id=right_cost><%=rs.getString(2)%>원</td>
		        	<% 	}
		        	%>
		        </div>
		    </tr>
		    <tr>
		        <div><td><%=aList[Integer.parseInt(abs)]%></td>
		         	<% if(flag){ %>
		        	<td><%=aList[Integer.parseInt(rs.getString(4))]%></td>
		        	<% 	}
		        	%>
		        </div>
		    </tr>
		</table>
	<%
	}
	%>
	<margin></margin>
  </div>
  <div data-role="footer" data-position="fixed" style="font : bold 9pt/1.3 돋움">
    <h3> 위 상품들은 매장마다 행사상품이 다를 수도 있습니다.</h3>
  </div>
</div> 

 <div data-role="page" id="pagetwo">
	<div data-role="header" data-position="fixed" style="top: 0px; border-top-width: 0px; padding-top: 0px;">
    <img id="haha" src = "image/MIN.png"></img>
    <div data-role="navbar">
      <ul>
        <li><a href="#pageone" > 음료 </a></li>
        <li><a href="#" class="ui-btn-active ui-state-persist" > 식품 </a></li>
        <li><a href="#pagethree" > 스낵 </a></li>
        <li><a href="#pagefour"> 생필품 </a></li>
      </ul>
    </div>
    <div style="font : bold 9pt/1.3 돋움 , padding: 5 10 10 5" >2016년 6월 행사상품</div>
  </div>

  <div data-role="main" class="ui-content">
   	  	<%	
  	  	int max2=0;
  	  	if(two%2 == 0){
  	  		max2 = (two/2);
  	  	}else{
  	  		max2 = (two/2)+1;
  	  	}
		for(int i=0; i<max2; i++){
			rs.next();
		%>
		<table class="product">
		    <tr>
				<div>
					<img src='<%=rs.getString(3)%>'></img>
						<%
						  String name = rs.getString(1);
						  String cost = rs.getString(2);
						  String abs = rs.getString(4);
						  if( max2 == (two/2) || (max2 == ((two/2)+1) && (i != max2-1) )){
							rs.next();
						  	flag = true;%>
							<img src='<%=rs.getString(3)%>'></img>
						<%}else{flag=false;}%>
				</div>
		    </tr>
		    <tr>
		        <div><td id=left_name><%=name%></td>
		            <% if(flag){ %>
		        	<td id=right_name><%=rs.getString(1)%></td>
		        	<% 	}
		        	%>
		      </div>
		    </tr>
		    <tr>
		        <div><td id=left_cost><%=cost%>원</td>
		         	<% if(flag){ %>
		        	<td id=right_cost><%=rs.getString(2)%>원</td>
		        	<% 	}
		        	%>
		        </div>
		    </tr>
		    <tr>
		        <div><td><%=aList[Integer.parseInt(abs)]%></td>
		         	<% if(flag){ %>
		        	<td><%=aList[Integer.parseInt(rs.getString(4))]%></td>
		        	<% 	}
		        	%>
		        </div>
		    </tr>
		</table>
	<%
	}
	%>
	<margin></margin>
  </div>

  <div data-role="footer" data-position="fixed" style="font : bold 9pt/1.3 돋움">
    <h3> 위 상품들은 매장마다 행사상품이 다를 수도 있습니다.</h3>
  </div>
</div> 

<div data-role="page" id="pagethree">
	<div data-role="header" data-position="fixed" style="top: 0px; border-top-width: 0px; padding-top: 0px;">
    <img id="haha" src = "image/MIN.png"></img>
    <div data-role="navbar">
      <ul>
      	<li><a href="#pageone" > 음료 </a></li>
        <li><a href="#pagetwo" > 식품 </a></li>
        <li><a href="#" class="ui-btn-active ui-state-persist" > 스낵 </a></li>
        <li><a href="#pagefour"> 생필품 </a></li>
      </ul>
    </div>
    <div style="font : bold 9pt/1.3 돋움 , padding: 5 10 10 5" >2016년 6월 행사상품</div>
  </div>

  <div data-role="main" class="ui-content">
  	  	<%	
  	  	int max3=0;
  	  	if(three%2 == 0){
  	  		max3 = (three/2);
  	  	}else{
  	  		max3 = (three/2)+1;
  	  	}

		for(int i=0; i<max3; i++){
			rs.next();
		%>
		<table class="product">
		    <tr>
				<div>
					<img src='<%=rs.getString(3)%>'></img>
						<%
						  String name = rs.getString(1);
						  String cost = rs.getString(2);
						  String abs = rs.getString(4);
						  if( max3 == (three/2) || (max3 == ((three/2)+1) && (i != max3-1) )){
							rs.next();
						  	flag = true;%>
							<img src='<%=rs.getString(3)%>'></img>
						<%}else{flag=false;}%>
				</div>
		    </tr>
		    <tr>
		        <div><td id=left_name><%=name%></td>
		            <% if(flag){ %>
		        	<td id=right_name><%=rs.getString(1)%></td>
		        	<% 	}
		        	%>
		      </div>
		    </tr>
		    <tr>
		        <div><td id=left_cost><%=cost%>원</td>
		         	<% if(flag){ %>
		        	<td id=right_cost><%=rs.getString(2)%>원</td>
		        	<% 	}
		        	%>
		        </div>
		    </tr>
		     <tr>
		        <div><td><%=aList[Integer.parseInt(abs)]%></td>
		         	<% if(flag){ %>
		        	<td><%=aList[Integer.parseInt(rs.getString(4))]%></td>
		        	<% 	}
		        	%>
		        </div>
		    </tr>
		</table>
	<%
	}
	%>
	<margin></margin>
  </div>

  <div data-role="footer" data-position="fixed" style="font : bold 9pt/1.3 돋움">
    <h3> 위 상품들은 매장마다 행사상품이 다를 수도 있습니다.</h3>
  </div> 
</div> 

<div data-role="page" id="pagefour">
	<div data-role="header" data-position="fixed" style="top: 0px; border-top-width: 0px; padding-top: 0px;">
    <img id="haha" src = "image/MIN.png"></img>
    <div data-role="navbar">
      <ul>
      	<li><a href="#pageone" > 음료 </a></li>
        <li><a href="#pagetwo" > 식품 </a></li>
        <li><a href="#pagethree" > 스낵 </a></li>
        <li><a href="#" class="ui-btn-active ui-state-persist" > 생필품 </a></li>
      </ul>
    </div>
    <div style="font : bold 9pt/1.3 돋움 , padding: 5 10 10 5" >2016년 6월 행사상품</div>
  </div>

  <div data-role="main" class="ui-content">
  	  	<%	
  	  	int max4=0;
  	  	if(four%2 == 0){
  	  		max4 = (four/2);
  	  	}else{
  	  		max4 = (four/2)+1;
  	  	}

		for(int i=0; i<max4; i++){
			rs.next();
		%>
		<table class="product">
		    <tr>
				<div>
					<img src='<%=rs.getString(3)%>'></img>
						<%
						  String name = rs.getString(1);
						  String cost = rs.getString(2);
						  String abs = rs.getString(4);
						  if( max4 == (four/2) || (max4 == ((four/2)+1) && (i != max4-1) )){
							rs.next();
						  	flag = true;%>
							<img src='<%=rs.getString(3)%>'></img>
						<%}else{flag=false;}%>
				</div>
		    </tr>
		    <tr>
		        <div><td id=left_name><%=name%></td>
		            <% if(flag){ %>
		        	<td id=right_name><%=rs.getString(1)%></td>
		        	<% 	}
		        	%>
		      </div>
		    </tr>
		    <tr>
		        <div><td id=left_cost><%=cost%>원</td>
		         	<% if(flag){ %>
		        	<td id=right_cost><%=rs.getString(2)%>원</td>
		        	<% 	}
		        	%>
		        </div>
		    </tr>
		     <tr>
		        <div><td><%=aList[Integer.parseInt(abs)]%></td>
		         	<% if(flag){ %>
		        	<td><%=aList[Integer.parseInt(rs.getString(4))]%></td>
		        	<% 	}
		        	%>
		        </div>
		    </tr>
		</table>
	<%
	}
	%>
	<margin></margin>
  </div>

  <div data-role="footer" data-position="fixed" style="font : bold 9pt/1.3 돋움">
    <h3> 위 상품들은 매장마다 행사상품이 다를 수도 있습니다.</h3>
  </div> 
</div> 

</body>
</html>