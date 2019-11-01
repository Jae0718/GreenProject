
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
</head>
<body>
<span><strong>Comments</strong></span> <span id="cCnt"></span>
<hr>
<div class="container">
   <form id="commentListForm" name="commentListForm" method="post">
   <input type="hidden" id="ccode" name="ccode" value="${content.ccode}"/>
   <div id="commentList">
   </div>
   </form>
</div>

<c:choose>
<c:when test="${! empty login.pid}">
<div class="container">
    <form id="commentForm" name="commentForm" method="post">
    <textarea style="width:85%;" rows="3" cols="30" id="cmcontent" name="cmcontent" placeholder="내용을 입력해주세요"></textarea>
    <button onClick="fn_comment()" class="btn btn-sm btn-primary" style="background-color:#0B173B; border-color:#0B173B;width:100px;position:absolute;width:73px;height:73px;margin-left:5px;">등록</button>
    <input type="hidden" id="ccode" name="ccode" value="${content.ccode}" />
   <input type="hidden" name="boardTitle" value="${boardTitle}"/>      
    </form>
</div>
</c:when>
<c:otherwise><center><a href="/test">로그인</a>후 댓글 작성이 가능합니다</center></c:otherwise>
</c:choose>



<script>

$(function(){
    getCommentList();    
});


function fn_comment(){
    $.ajax({
        type:'POST',
        url : "/test/board/addComment.do",
        data:$("#commentForm").serialize(),
        success : function(data){
            if(data=="success")
            {
                getCommentList();
                $("#cmcontent").val("");
            }
        },
        error:function(request,status,error){
            alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
       }
        
    });
}
 
function getCommentList(){
    
    $.ajax({
        type:'GET',
        url : "<c:url value='/board/commentList.do'/>",
        dataType : "json",
        data:$("#commentListForm").serialize(),
        contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
        success : function(data){
            
            var html = "";
            var cCnt = data.length;
            
            if(data.length > 0){
                
                for(i=0; i<data.length; i++){
                    html += "<div>";
                    html += "<div><table class='table'><h6><strong>"+data[i].writer+"</strong></h6>";
                    html += data[i].comment + "<tr><td></td></tr>";
                    html += "</table></div>";
                    html += "</div>";
                }
                
            } else {
                
                html += "<div>";
                html += "<div><table class='table'><h6><strong>등록된 댓글이 없습니다</strong></h6>";
                html += "</table></div>";
                html += "</div>";
                
            }
            
            $("#cCnt").html(cCnt);
            $("#commentList").html(html);
            
        },
        error:function(request,status,error){
            
       }
        
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
}
   

</script>

</body>
</html>