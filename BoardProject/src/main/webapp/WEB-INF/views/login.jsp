<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
<head>
   <title>Login</title>
   
   <meta charset="UTF-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->   
   <link rel="icon" type="image/png" href="resources/images/icons/favicon.ico">
<!--===============================================================================================-->
   <link rel="stylesheet" type="text/css" href="resources/vendor/bootstrap/css/login/bootstrap.min.css">
<!--===============================================================================================-->
   <link rel="stylesheet" type="text/css" href="resources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
   <link rel="stylesheet" type="text/css" href="resources/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
   <link rel="stylesheet" type="text/css" href="resources/vendor/animate/animate.css">
<!--===============================================================================================-->   
   <link rel="stylesheet" type="text/css" href="resources/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
   <link rel="stylesheet" type="text/css" href="resources/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
   <link rel="stylesheet" type="text/css" href="resources/vendor/select2/select2.min.css">
<!--===============================================================================================-->   
   <link rel="stylesheet" type="text/css" href="resources/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
   <link rel="stylesheet" type="text/css" href="resources/css/util.css">
   <link rel="stylesheet" type="text/css" href="resources/css/main.css">
<!--===============================================================================================-->
</head>
<body>

   <c:choose>
      <c:when test="${!empty msg}">
         <script>
            alert('${msg}');
         </script>
      </c:when>
   </c:choose>

   <div class="limiter">
      <div class="container-login100" style="background-image: url('resources/images/bg-01.jpg');">
         <div class="wrap-login100 p-t-30 p-b-50">
            <span class="login100-form-title p-b-41">
               Account Login
            </span>
            <form class="login100-form validate-form p-b-33 p-t-5" name="loginForm">

               <div class="wrap-input100 validate-input" data-validate = "Enter username">
                  <input class="input100" type="text" id="pid" name="pid" placeholder="User name">
                  <span class="focus-input100" data-placeholder="&#xe82a;"></span>
               </div>

               <div class="wrap-input100 validate-input" data-validate="Enter password">
                  <input class="input100" type="password" id="ppw" name="ppw" placeholder="password"/>
                  <span class="focus-input100" data-placeholder="&#xe80f;"></span>
               </div>

               <div class="container-login100-form-btn m-t-32">
                  <input class="login100-form-btn" type="button" value="Login" onclick="goLogin()"/>
                  <input class="login100-form-btn" type="button" id="joinBtn" value="Join" onclick="moveJoin()"/>
                  <input class="login100-form-btn" type="button" id="enterBtn" value="비회원입장" onclick="nonMember()"/>               
               </div>
               
            </form>
         </div>
      </div>
   </div>
   

   <div id="dropDownSelect1"></div>
   
<!--===============================================================================================-->
   <script src="/resources/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
   <script src="/resources/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
   <script src="/resources/vendor/bootstrap/js/login/popper.js"></script>
   <script src="/resources/vendor/bootstrap/js/login/bootstrap.min.js"></script>
<!--===============================================================================================-->
   <script src="/resources/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
   <script src="/resources/vendor/daterangepicker/moment.min.js"></script>
   <script src="/resources/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
   <script src="/resources/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
   <script src="/resources/js/main.js"></script>


   <script>
      function goLogin(){
         var formObj = document.loginForm;
         var valueId = pid.value.trim();
         var valuePw = ppw.value.trim();
   
         if(valueId.length == 0 || valuePw.length == 0){
            alert("��簪�� �Է����ּ���");
         }else{
            formObj.action = "goLogin.do";
            formObj.method = "post";
            formObj.submit();
         }
      }

      function moveJoin(){
         var formObj = document.loginForm;
         formObj.action = "join";
         formObj.method = "post";
         formObj.submit();
      }

      function nonMember(){
         var formObj = document.loginForm;
         formObj.action = "nonMember";
         formObj.method = "post";
         formObj.submit();
      }
   </script>
</body>
</html>