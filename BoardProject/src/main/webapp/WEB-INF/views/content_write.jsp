<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
</head>
<body>
<div style="margin-top:100px;">
<article style="margin-bottom:10%;">

      <div class="container" role="main" style="margin-top:50px;">

         <h2>board Form</h2>

         <form name="form" id="form" role="form" method="post" action="${pageContext.request.contextPath}/board/write/insertContent">
      
            <div class="mb-3">
               게시판 선택 <select name="selectedBoard">
               <c:forEach var="boardTitle" items="${boardList}">
                  <option value="${boardTitle}">${boardTitle}</option>
               </c:forEach>
            </select>
            </div>
            
            <div class="mb-3">
               <label for="title">제목</label>
               <input type="text" class="form-control" name="ctitle" id="title" placeholder="제목을 입력해 주세요">
            </div>


            <div class="mb-3">
               <label for="content">내용</label>
               <textarea class="form-control" rows="10" name="ccontent" id="content" placeholder="내용을 입력해 주세요" ></textarea>
            </div>
            
         <div >
            <center><button style="background-color:#0B173B; border-color:#0B173B;" "type="button" class="btn btn-sm btn-primary" id="btnSave" onclick="saveCheck()">저장</button>
            <button style="background-color:#0B173B; border-color:#0B173B;" type="button" class="btn btn-sm btn-primary" id="btnList" onclick="goList()">목록</button></center>
         </div>
      </form>
      </div>

   </article>
</div>
<script>
   function saveCheck(){
      var formObj = document.forms[0];
      if(formObj.title.value == "" ){
         alert("제목을 입력해주세요");
      }else if(formObj.content.value == ""){
         alert("내용을 입력해주세요");
      }else{
         formObj.submit();
      }
   }

   function goList(){
      var formObj = document.forms[0];
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