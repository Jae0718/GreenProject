<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<link rel="stylesheet" type="text/css" href="css/style.css">
<title>Student Information System</title>
</head>

<%
	String nextOrder = (String)request.getAttribute("nextOrder");
	if(nextOrder == null){
		nextOrder ="list.jsp";
	}
%>
<body>

<div id="label1">
	<h1>Student Information System</h1><br/><hr>
</div>
	<form action="main.jsp" method="post">
		<div id="mainForm" style="border:1px; width:800px;margin:auto;">
			<div style="float : left;">
				<table class="templateTable">
					<tr><th>MENU</th></tr>
					<tr><td><a href="home.do" class="aTag">GO HOME</a></td></tr>
					<tr><td><a href="list.do" class="aTag">VIEW LIST</a></td></tr>
					<tr><td><a href="input.do" class="aTag">INPUT INFO</a></td></tr>
					<tr><td><a href="search.do" class="aTag">SEARCH</a></td></tr>
				</table>
			</div>

			
			<div style =" margin-left : 100px; ">
				<jsp:include page="<%=nextOrder%>" flush="false" />
			</div>
			
		</div>
	</form>
	
</body>
</html>





