<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- CSS파일 -->
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
<style>
   .form-control{
      font-size:10pt;
      height:70%;
   }
   label{
      font-size:10pt;
      font-style:bold;
   }
   #btnList{
      text-align:center;
   }
</style>
</head>
<body>

<article style="margin-bottom:30px; ">

   <div style="margin-top:70px;"class="container" role="main">
      <!-- 콘텐츠 타이틀 -->
      <div class="mb-3">
      <h2>${content.ctitle}</h2>
      </div>
      
      <!-- 게시글 정보 표시 -->
      <div class="mb-3">
      <label for="title" style="width:150px;">작성자 | ${content.pid}</label>
      <label for="title" style="width:200px;">작성일 | ${content.cdate}</label>
      <label for="title" style="width:200px;">조회수 | ${content.chits}</label>
      <form name="listForm">
      <button type="button" class="btn btn-sm btn-primary" style="background-color:#0B173B; border-color:#0B173B;width:100px;float:right;margin-bottom:10px;" id="btnList" onclick="goList('${boardTitle}')">List</button>
      <input type="hidden" name="boardTitle" value="${boardTitle}"/>
      </form>
      </div>
      
      
      <form name="modifyForm" id="modifyForm">   
      <!-- 게시글 내용 -->
      <div class="mb-3">
      <label for="content">내용</label>
      <textarea class="form-control" rows="10" name="ccontent" id="ccontent" readonly="readonly" >${content.ccontent}</textarea>
      </div>
      
      <!-- 수정완료 버튼 -> modify 버튼을 눌러야 활성화됨 -->
        <button id="doneBtn" class="btn btn-sm btn-primary" onclick ="complete()" class="btn_style" style="background-color:#0B173B; border-color:#0B173B;float:right;">Done</button>
      <input type="hidden" name="ccode" value="${content.ccode}"/>
      <input type="hidden" name="boardTitle" value="${boardTitle}"/>
      </form>
      
      <!-- 글쓴이만 수정삭제 / 관리자 삭제 가능하도록 설정  -->
      <c:choose>
      <c:when test="${2 eq login.pgrade}">
      <form name="deleteForm" method="post" >
        <button onclick="goDelete()" class="btn btn-sm btn-primary"style="background-color:#0B173B; border-color:#0B173B;float:right;">Delete</button>
         <input type="hidden" name="ccode" value="${content.ccode}"/>
         <input type="hidden" name="boardTitle" value="${boardTitle}"/>
         </form>
      </c:when>
      
      <c:when test="${content.pid eq login.pid}">
      <form name="deleteForm" method="post" >
      <button onclick="goDelete()" class="btn btn-sm btn-primary" style="background-color:#0B173B; border-color:#0B173B;float:right;">Delete</button>
      <input type="hidden" name="ccode" value="${content.ccode}"/>
      <input type="hidden" name="ctitle" value="${content.ctitle}"/>
      <input type="hidden" name="boardTitle" value="${boardTitle}"/>
      </form>
      <div class="btn_group"><button id="modifyBtn" onclick ="modify()" class="btn btn-sm btn-primary" style="background-color:#0B173B; border-color:#0B173B;float:right;">Modify</button></div>
      </c:when>

      <c:otherwise></c:otherwise>
      </c:choose>

      <div style="margin-top:50px;">
      <jsp:include page="comment.jsp"/>
      </div>
      <br/>
      <div>
   </div>
</div>
   </article>

<script>

   $(function(){
      $("#doneBtn").hide();
   });
   
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

   function goContentList(){
      var btitle = "<c:out value='${boardTitle}'/>";
      alert(btitle);
      location.href="/test/board/moveBoard?boardTitle=" + btitle;
   }


   function modify(){
      $("#doneBtn").show();
      $("#modifyBtn").hide();
      $("#ccontent").attr('readonly',false);
   }

   function complete(){
      
      $.ajax({
         type:'POST',
           url : "/test/board/updateContent",
           data:$("#modifyForm").serialize(),
           success : function(data){
            alert("게시글이 수정되었습니다");
          }

      });
      
      $("#doneBtn").hide();
      $("#modifyBtn").show();
      $("#ccontent").attr('readonly',true);
   }

   function goDelete(){
      alert("게시물과 포함된 댓글이 모두 삭제됩니다");
      var formObj = document.forms["deleteForm"];
      formObj.action = "/test/board/deleteContent";
   }

</script>

</body>
</html>