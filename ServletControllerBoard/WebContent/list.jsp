<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="jy.dto.*"%>
<%@ page import="jy.dao.*"%>
<link rel="stylesheet" type="text/css" href="css/style.css">

<%
	Student[] list = (Student[])request.getAttribute("list");
%>


<h1>LIST</h1><hr><br>
<form method="post">
<%
	if(list != null){
		
%>
	<table class="listTable">
		<tr>
			<th>ID</th>
			<th>NAME</th>
			<th>TEL</th>
			<th>GRADE</th>
			<th>CLASS</th>
		</tr>
		<%
			if(list.length == 0){
		%>
		<tr>
			<td colspan="5">empty</td>
		</tr>
		<%
			}else{
				for(Student temp: list){
		%>
			<tr>
				<td><%=temp.getSid() %></td>
				<td><a href="info.do?sid=<%=temp.getSid() %>"><%=temp.getSname() %></a></td>
				<td><%=temp.getStel() %></td>
				<td><%=temp.getSgrade() %></td>
				<td><%=temp.getSclass() %></td>
			</tr>
			
		<%
				}
			}
		%>
		<%
	}
		%>
	</table>	
</form>
<center><div style="margin-top:30px;">
<%
	int totalCount = (Integer)request.getAttribute("total");
	int pageNum = (Integer)request.getAttribute("pageNum");
	for(int i=1; i<=totalCount;i++){
		%>
		[<a href="list.do?pageNum=<%=i%>"><%=i%></a>]
		<%
	};
%>
</div></center>















