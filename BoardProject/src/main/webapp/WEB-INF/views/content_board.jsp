<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<head>
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="/test/resources/table/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/test/resources/table/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/test/resources/table/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/test/resources/table/vendor/animate/animate.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/test/resources/table/vendor/select2/select2.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/test/resources/table/vendor/perfect-scrollbar/perfect-scrollbar.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="/test/resources/table/css/util.css">
	<link rel="stylesheet" type="text/css" href="/test/resources/table/css/main.css">
<!--===============================================================================================-->
</head>    
<div style="margin-top:15%;">

   <!-- 게시판 리스트 -->
   <div class="board_contents">
   <c:choose>
   <c:when test="${empty boardTitle}">
   <c:forEach var="board" items="${boardList}">
   <div class="container">
    <div class="row">
      <div class="col-lg-8 col-md-10 mx-auto">
        <div class="post-preview">
        <form name="moveForm">
        <h2 class="post-title"><a onclick="movePageBoard('${board.btitle}')" style="text-decoration: none;cursor: pointer;">${board.btitle}</a></h2>
        <h6 class="post-subtitle">${board.binfo}</h6>
        <hr>
        <input type="hidden" name="boardTitle">
        </form>
        </div>
      </div>
     </div>
  	</div>
   </c:forEach>
	
	<!--게시판 수정 버튼 -->
   <section class="py-5">
   <c:choose>
   <c:when test="${2 eq login.pgrade }">
   <form action="/test/board/editBoard">
   <div class="btn_group">
   <center><button style="background-color:#0B173B; border-color:#0B173B;" type="button" class="btn btn-sm btn-primary" >Edit Board</button></center>
   </div>
   </form>
   </c:when>
   <c:otherwise>
   </c:otherwise>
   </c:choose>
   </section>
   </c:when>
   <c:otherwise>
   
   <!-- 게시판 내의 게시물 리스트 -->
   <div class="container">
   <h1>${boardTitle}</h1>
   <div style="text-align:right;margin-bottom:5px;">
   <form name="listForm">
   <button style="background-color:#0B173B; border-color:#0B173B;" type="button" class="btn btn-sm btn-primary" style="width:100px;" id="btnList" onclick="goList()">List</button>
   <input type="hidden" name="boardTitle"/>
   </form>
   </div>
   <form name="selectContentForm">
   <input type="hidden" name="boardTitle" value="${boardTitle}"/>
   
   <table>
   <thead>
   <tr style="background-color:#0B173B;" class="table100-head">
	   <th class="column1" style="text-align:center;width:70px;">번호</th>
	   <th class="column2" style="text-align:center;width: 150px;">제목</th>
	   <th class="column3" style="text-align:center;width: 100px;">글쓴이</th>
	   <th class="column4" style="text-align:center;width: 300px;">작성일</th>
	   <th class="column5" style="text-align:center;width: 100px;">조회수</th>
   </tr>
   </thead>
   <c:choose>
   <c:when test="${! empty contentList}">
   	<c:forEach var="contentList" items="${contentList}" begin="0" end="5">
	   <tbody>
		   <tr>
		   <td class="column1">${contentList.ccode}</td>
		   <td class="column2"><a onclick="viewContent('${contentList.ccode}')" style="cursor:pointer">${contentList.ctitle}</a></td>
		   <td class="column3">${contentList.pid}</td>
		   <td class="column4">${contentList.cdate}</td>
		   <td class="column5">${contentList.chits}</td>
		   </tr>
		</tbody>
   </c:forEach>
   </c:when>
   <c:otherwise>
   <tbody>
   	<tr><td style="text-align:center;" colspan="5">등록된 게시물이 없습니다</td></tr>
   </tbody>
   </c:otherwise>
   </c:choose>
   
   </table>
   <input type="hidden" name="ccode"/>
   </form>
   
   <div>
   <c:choose>
   <c:when test="${!empty login}">
   <form action="/test/board/write" method="post">
   <div class="btn_group" >
   <button style="background-color:#0B173B; border-color:#0B173B;float:right;" class="btn btn-sm btn-primary" style="float:right;">Write</button>
   </div>
   <input type="hidden" name="page" value="write"/>
   </form>
   </c:when>
   <c:otherwise></c:otherwise>
   </c:choose>
   </div>
   
   <div style="margin-left:45%;margin-top:10%;">
   <div class="row">
   <div class="col">
   <ul class="pagination" >
   <li class="page-item">
   <form name="preNextForm">
   <a class="page-link" onclick="preNext('pre','${pagination.destnPage}')">Pre</a>
   <input type="hidden" name="destnPage"/>
   <input type="hidden" name="boardTitle" value="${boardTitle}"/>
   </form>
   </li>
   <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}">
   <li class="page-item">
   <form name="pageForm" action="/test/board/moveBoard" method="post">
   <a  class="page-link" onclick="movePage('${i}','${pagination.destnPage}')">${i}</a>
   <input type="hidden" name="boardTitle" value="${boardTitle}"/>
   <input type="hidden" name="paginationPage"/> 
   <input type="hidden" name="destnPage"/>
   </form>
   </li>
   </c:forEach>                  
   <li class="page-item">
   <form name="preNextForm">
   <a class="page-link" onclick="preNext('next','${pagination.destnPage}','${pagination.pageCnt}')">Next</a>
   <input type="hidden" name="destnPage"/>
   <input type="hidden" name="boardTitle" value="${boardTitle}"/>
   </form>
   </li>
   </ul>
   </div>
   </div>
   </div>
   
   </div>
   
   </c:otherwise>
   </c:choose>
   </div>
   </div> 
   
<!--===============================================================================================-->	
	<script src="/test/resources/table/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="/test/resources/table/vendor/bootstrap/js/popper.js"></script>
	<script src="/test/resources/table/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="/test/resources/table/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="/test/resources/table/js/main.js"></script>
   <script>
   function movePageBoard() {
      var formObj = document.forms['moveForm'];
      formObj.boardTitle.value = arguments[0];
      formObj.action = "/test/board/moveBoard";
      formObj.method = "post";
      formObj.submit();
   }

   function movePage(){
      var formObj = document.forms['pageForm'];
      formObj.destnPage.value = arguments[0];
      formObj.action = "/test/board/moveBoard";
      formObj.submit();
   }

   function preNext(){
      var formObj = document.forms['preNextForm'];
      var paginationPage = arguments[1];
      var paginationPageCnt = arguments[2];
      
      if(arguments[0] == 'pre'){
         var prePage = (paginationPage/5)*5 - 5;
         if(prePage > 0){
            formObj.destnPage.value = (paginationPage/5)*5 - 1;
            formObj.action = "/test/board/moveBoard";
            formObj.method = "post";
            formObj.submit();
         }else{
            alert("이전페이지가 없습니다");
         }
      }else{
         var nextPage = (paginationPage/5)*5 + 1;
         if( nextPage <= paginationPageCnt){
            formObj.destnPage.value = (paginationPage/5)*5 + 1;
            formObj.action = "/test/board/moveBoard";
            formObj.method = "post";
            formObj.submit();
         }else{
            alert("다음페이지가 없습니다");
         }
      }
   }

   function viewContent(){
      var formObj = document.forms['selectContentForm'];
      formObj.ccode.value = arguments[0];
      formObj.action = "/test/board/viewBoard";
      formObj.method = "post";
      formObj.submit();
   }

   function goList(){
      var formObj = document.forms["listForm"];
      if(arguments[0] != null){
         formObj.action = "/test/board/moveBoard";
         formObj.boardTitle.value = arguments[0];
      }else{
         formObj.action="/test/board";
      }
      formObj.submit();
   }
   </script>
</body>
</html>