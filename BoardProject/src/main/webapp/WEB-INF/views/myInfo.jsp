<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/test/resources/myinfo/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/test/resources/myinfo/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/test/resources/myinfo/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/test/resources/myinfo/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="/test/resources/myinfo/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/test/resources/myinfo/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/test/resources/myinfo/vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="/test/resources/myinfo/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="css/util.css">
	<link rel="stylesheet" type="text/css" href="css/main.css">
<!--===============================================================================================-->
</head>
<body>

<div class="limiter">
<div class="container-login100">
<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
	<form class="login100-form validate-form flex-sb flex-w">
		<span class="login100-form-title p-b-32">
			My Info
		</span>
	</form>
</div>
</div>
</div>



<div style="margin-top:10%;margin-bottom:20%; @media(max-width:700px){margin-left:20%; margin-right:20%;}">
<article style="margin:0px auto;width:70%;">
   <c:choose>
   <c:when test="${1 eq login.pgrade}">
   <center><img style="margin-bottom:10%;" src="/test/resources/images/myinfo/user.jpg"/></center>
   </c:when>
   <c:otherwise>
   <center><img style="margin-bottom:10%;" src="/test/resources/images/myinfo/admin.png"/></center>
   </c:otherwise>
   </c:choose>

   <div id="text-section" class="container" role="main">
   <h4 class="text" >ID : ${login.pid}</h4>
   <h4 class="text">Name : ${login.pname}</h4>
   <h4 class="text">Email : ${login.pemail}</h4>
   </div>
   
   <div id="infoupdate-section" class="container">
   <form name="infoForm" id="infoForm">
   <h2>ID : ${login.pid}</h2>
   <input type="hidden" name="pid" value="${login.pid}">
   <h2>Name  <input type="text" name="pname" id="pname" style="width:100%" placeholder="${login.pname}"/></h2>
   <h2>Email  <input type="text" name="pemail" id="pemail" style="width:100%" placeholder="${login.pemail}"/></h2>
   <button type="button" class="btn btn-sm btn-primary" onclick="infoupdateDone()" style="width:100%;height:50px;margin-top:10%;">변경 완료</button>
   </form>
   </div>
   
   <div id="pwupdate-section" class="container">
   <form name="pwForm" id="pwForm">
   <input type="hidden" name="pid" value="${login.pid}">
   <h2>PW</h2>
   기존 비밀번호를 입력해 주세요
   <h2><input type="password" id="old_ppw" style="width:100%" /></h2>
   새로운 비밀번호를 입력해 주세요
   <h2><input type="password" name="new_ppw" id="new_ppw" style="width:100%"/></h2>
   새로운 비밀번호를 한번더 입력해주세요
   <h2><input type="password" name="re_new_pw" id="re_new_ppw" style="width:100%"/></h2>
   <button type="button" class="btn btn-sm btn-primary" onclick="pwupdateDone('${login.ppw}')" style="width:100%;height:50px;">변경 완료</button>
   </form>
   </div>
   <!-- 버튼 -->
   <div id="btn-section" class="container" style=" margin-top:10%;">
   <button type="button" class="btn btn-sm btn-primary" id="infoBtn" onclick="modifyInfo('info')" style="background-color:#0B173B; border-color:#0B173B;width:100%;height:50px;margin-bottom:10px;">내 정보 수정</button>
   <button type="button" class="btn btn-sm btn-primary" id="pwBtn"  onclick="modifyInfo('pw')" style="background-color:#0B173B; border-color:#0B173B;width:100%;height:50px;margin-bottom:10px;">비밀번호 변경</button>
   
   <div style="margin-top:50px;">
   <c:choose>
   <c:when test="${1 eq login.pgrade}">
      <form name="withdrawForm">
      <button type="button" class="btn btn-sm btn-primary" id="withdrawBtn" onclick="withdraw('${login.pid}')" style="background-color:#0B173B; border-color:#0B173B;width:100%;height:50px;">탈퇴하기</button>
      <input type="hidden" name="pid" id="pid" value="${login.pid }"/>
      </form>
   </c:when>
   <c:otherwise></c:otherwise>
   </c:choose>
   </div>
   </div>
</article>
</div>


<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
   $(function (){
      $("#infoupdate-section").hide();
      $("#pwupdate-section").hide();
   });
   
   function modifyInfo(){
      var what = arguments[0];
      $("#btn-section").hide();
      $("#text-section").hide();
      if(what == 'pw'){
         $("#pwupdate-section").show();
      }else{
         $("#infoupdate-section").show();
      }
   }

   function infoupdateDone(){
      var pname = $("#pname").val();
      var pemail = $("#pemail").val();

      if(pname.length == 0 || pemail.length == 0){
         alert("값을 입력해주세요");
      }else{
         var formObj = document.forms["infoForm"];
         formObj.action = "/test/updateInfo";
         formObj.submit();
         alert("정보 수정이 완료 되었습니다");
      }

   }

   function pwupdateDone(){
      var old_ppw = $("#old_ppw").val();
      var new_ppw = $("#new_ppw").val();
      var re_new_ppw = $("#re_new_ppw").val();
      var old_pwFlag = true;
      var new_pwFlag = true;
      
      if(old_ppw != arguments[0]){
         alert("기존 비밀번호가 일치하지 않습니다");
         pwFlag = false;
      }

      if(new_ppw != re_new_ppw){
         new_pwFlag = false;
      }

      if(old_pwFlag && new_pwFlag){
         var formObj = document.forms["pwForm"];
         formObj.action = "/test/updatePw";
         formObj.method = "post";
         formObj.submit();
         alert("비밀번호 변경이 완료 되었습니다");
      }
   }

   function withdraw(){
      var formObj = document.forms["withdrawForm"];
      formObj.action = "/test/deletePerson";
      formObj.method = "post";
      formObj.submit();
      alert("탈퇴 되었습니다");
   }

</script>
</body>
</html>