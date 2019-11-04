<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<style>
	table.inputTable{
		margin:auto;
	}
</style>

<body>
<h1>INPUT</h1>
<hr><br>
<table class="inputTable">
	<tr>
		<td>Name</td>
		<td><input type="text" name="sname"></td>
	</tr>
	<tr>
		<td>Tel</td>
		<td><input type="text" name="stel"></td>
	</tr>
	<tr>
		<td>Grade</td>
		<td><input type="text" name="sgrade"></td>
	</tr>
	<tr>
		<td>Class</td>
		<td><input type="text" name="sclass"></td>
	</tr>
	<tr>
		<td colspan="4" style="text-align:center;"><input type="button" value="insert" onclick="goMain(this.form,'insert')"/></td>
	</tr>
</table>

<script>
	function goMain(){
		var objForm = arguments[0];
		objForm.action = arguments[1] +".do";
		objForm.submit();
	}
</script>

</body>





