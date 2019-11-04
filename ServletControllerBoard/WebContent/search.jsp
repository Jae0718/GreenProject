<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="jy.dto.*"%>
<%
	Student[] list = (Student[])request.getAttribute("list");
	boolean isEmpty = true;
	if(list == null){
		isEmpty = false;
	}
%>
<body>
<h1>SEARCH</h1><hr><br>
<form>
<div id="top" style="margin-left:200px; margin-bottom:30px;">
	<select name="option">
	    <option value="sid">ID</option>
	    <option value="sname">NAME</option>
	    <option value="sclass">CLASS</option>
	    <option value="stel">TEL</option>
	</select>
	항목별 검색 
	<input type="text" name="sValue" />
	<input type="submit" value="search" onclick="goMain(this.form,'find')"/>
</div>
</form>
<%
	if(isEmpty){
%>
<center><h3>Result</h3></center>
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
<%
	}
%>

<script>
function goMain(){
	var objForm = arguments[0];
	objForm.action = arguments[1] +".do";
	objForm.submit();
}
</script>
</body>