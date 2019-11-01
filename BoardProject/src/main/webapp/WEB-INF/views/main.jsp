<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="false" %>
<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Spring Board</title>

  <!-- Bootstrap core CSS -->
  <link href="/test/resources/startbootstrap/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

  <!-- Custom fonts for this template -->
  <link href="/test/resources/startbootstrap/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
  <link href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic' rel='stylesheet' type='text/css'>
  <link href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800' rel='stylesheet' type='text/css'>

  <!-- Custom styles for this template -->
  <link href="/test/resources/startbootstrap/css/clean-blog.min.css" rel="stylesheet">
<style>
</style>
</head>

<body>
  <!-- Navigation -->
  <nav style="background-color:#D8D8D8" class="navbar navbar-expand-lg navbar-light fixed-top" id="mainNav">
    <div class="container">
   <a style="foreground-color:WHITE;" class="navbar-brand" href="/test/main">Welcome ${login.pid}</a>
   <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
   <span class="navbar-toggler-icon"></span>
   </button>
   <div class="collapse navbar-collapse" id="navbarResponsive">
   
   <ul class="navbar-nav ml-auto">
   <li class="nav-item">
   <a class="nav-link" href="/test/main">Home</a>
   </li>
   <li class="nav-item">
   <a class="nav-link" href="/test/board">Board</a>
   </li>
   <c:choose>
   <c:when test="${!empty login.pid}">
   <li class="nav-item">
   <a class="nav-link" href="/test/viewMyInfo">MyInfo</a>
   </li>
   <li class="nav-item">
   <a class="nav-link" href="/test/logout">Logout</a>
   </li>
   </c:when>
   <c:otherwise>
   <li><a class="nav-link" href="/test" style="foreground-color:WHITE;">LOGIN</a></li>
   <li><a class="nav-link" href="/test/join" style="foreground-color:WHITE;">JOIN</a></li>
   </c:otherwise>
   </c:choose>
   
   </ul>
   </div>
   </div>
  </nav>

  <!-- Main Content -->
	<c:choose>
      <c:when test="${page eq 'board'}">
         <jsp:include page="content_board.jsp">
            <jsp:param value="${boardList}" name="boardList"/>
            <jsp:param value="${login}" name="login"/>
            <jsp:param value="${boardTitle}" name="boardTitle"/>
            <jsp:param value="${contentList}" name="contentList"/>
         </jsp:include>
      </c:when>
      <c:when test="${page eq 'write'}">
         <jsp:include page="content_write.jsp"/>
      </c:when>
      <c:when test="${page eq 'view'}">
         <jsp:include page="content_view.jsp"/>
      </c:when>
      <c:when test="${page eq 'editBoard'}">
         <jsp:include page="board_edit.jsp"/>
      </c:when>
      <c:when test="${page eq 'myInfo'}">
         <jsp:include page="myInfo.jsp"/>
      </c:when>
      <c:otherwise>
         <jsp:include page="content_main.jsp"/>
      </c:otherwise>
   </c:choose>

  <hr>

  <!-- Footer -->
  <footer>
    <div class="container">
      <div class="row">
        <div class="col-lg-8 col-md-10 mx-auto">
          <ul class="list-inline text-center">
            <li class="list-inline-item">
              <a href="#">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-twitter fa-stack-1x fa-inverse"></i>
                </span>
              </a>
            </li>
            <li class="list-inline-item">
              <a href="#">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-facebook-f fa-stack-1x fa-inverse"></i>
                </span>
              </a>
            </li>
            <li class="list-inline-item">
              <a href="#">
                <span class="fa-stack fa-lg">
                  <i class="fas fa-circle fa-stack-2x"></i>
                  <i class="fab fa-github fa-stack-1x fa-inverse"></i>
                </span>
              </a>
            </li>
          </ul>
          <p class="copyright text-muted">Copyright &copy; Your Website 2019</p>
        </div>
      </div>
    </div>
  </footer>

  <!-- Bootstrap core JavaScript -->
  <script src="/test/resources/startbootstrap/vendor/jquery/jquery.min.js"></script>
  <script src="/test/resources/startbootstrap/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

  <!-- Custom scripts for this template -->
  <script src="/test/resources/startbootstrap/js/clean-blog.min.js"></script>

</body>

</html>
