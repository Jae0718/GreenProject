<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="jy.dto.*"%>
<%@ page import="jy.dao.*"%>
<body>
<%
	Student temp = (Student)request.getAttribute("stu");
%>
<form>
<center><table>
	<tr><th>id</th><td><input type="text" name="sid" value ="<%=temp.getSid() %>" readonly="readonly"/></td></tr>
	<tr><th>name</th><td><input type="text" name="sname" value ="<%=temp.getSname() %>"/></td></tr>
	<tr><th>tel</th><td><input type="text" name="stel" value ="<%=temp.getStel() %>"/></td></tr>
	<tr><th>grade</th><td><input type="text" name="sgrade" value ="<%=temp.getSgrade() %>"/></td></tr>
	<tr><th>class</th><td><input type="text" name="sclass" value ="<%=temp.getSclass() %>"/></td></tr>
	<tr><td colspan="2" style="text-align : center;">
		<input type ="button" value="���� �Ϸ�" onclick="goMain(this.form,'update')" />
		<input type ="button" value="���� �ϱ�" onclick="goMain(this.form,'delete')" />
	</td></tr>
</table></center>
</form>
<script>
function goMain(){
	var objForm = arguments[0];
	objForm.action = arguments[1] +".do";
	objForm.submit();

	var alertStr="����";
	if(arguments[1] == 'delete'){
		alertStr="����";
	}
	alert(alertStr + "�Ǿ����ϴ�");
}


</script>

</body>
