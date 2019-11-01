<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
   <meta charset="utf-8">
   <meta name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no">
   <meta name="description" content="">
   <meta name="author" content="">
   <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css">
</head>

<body>
   <div style="margin-top:70px;" class="text-center">
      <div class="container" style="margin-top: 36px; margin-bottom: 36px;">
         <h2>Board Edit</h2>
         <form name="inputForm" id="inputForm">
         
         <center><div id="boardList" style="@media(max-width:700px){width:50%;}">
         
         </div></center>
         
         </form>
         <div class="btn_group" style="margin-top:20px;">
            <div class="text-center">
               <button style="background-color:#0B173B; border-color:#0B173B;"  class="btn btn-sm btn-primary" id="addBtn" onclick="add()">추가</button>
               <button style="background-color:#0B173B; border-color:#0B173B;" class="btn btn-sm btn-primary">목록</button>
            </div>
         </div>
      </div>
   </div>
   
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script>
   $(function(){
      getBoardList();
   });
   
   function add(){
      $("#firstEle").append('<tr><td><input type="text" name="addTitle" id="addTitle" placeholder="title입력"/></td><td><input type="text" style="width:400px;" name="addInfo" id="addInfo" placeholder="게시판 소개 입력"/></td><td style="text-align:center;"><div class="btn_group"><button onclick="remove(this)" style=\"background-color:#0B173B; border-color:#0B173B;\" class=\"btn btn-sm btn-primary\">삭제</button><button style=\"background-color:#0B173B; border-color:#0B173B;\" class=\"btn btn-sm btn-primary\" onclick="save()">저장</button></div></td></tr>');
   }

   function addFirst(){
      $("#boardList").append('<table><tr><td><input type="text" name="addTitle" id="addTitle" placeholder="title입력"/></td><td><input type="text" style="width:400px;" name="addInfo" id="addInfo" placeholder="게시판 소개 입력"/></td><td style="text-align:center;"><div class="btn_group"><button onclick="remove(this)" class="btn_style">삭제</button><button class="btn_style" onclick="save()">저장</button></div></td></tr></table>');
   }

   function remove(obj){
      var tr = $(obj).parent().parent().parent();
      tr.remove();
   }
   
   function deleteBoard(obj, btitle){
      alert(btitle+"게시판의 모든 게시글과 댓글이 삭제됩니다.");
      $.ajax({
         type : "POST",
         url : "<c:url value='/board/deleteBoard.do'/>",
         data: {btitle : btitle},
         success : function(data){
            if(data == "success"){
               getBoardList();
            }
         },
         error:onError
      });
   }

   function save(){
      var addTitle = $('#addTitle').val();
      var addInfo = $('#addInfo').val();
      $.ajax({
         type : "POST",
         url : "<c:url value='/board/insertBoard.do'/>",
         data: {
            addTitle : addTitle,
            addInfo : addInfo
            },
         success : function(data){
            if(data == "success"){
               getBoardList();
            }
         },
         error:onError
      });

   }
   
   function getBoardList(){
      $.ajax({
         type:'POST',
           url : "<c:url value='/board/boardList.do'/>",
           dataType : "json",
           data:$("#inputForm").serialize(),
           contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
           success : function(data){
              var html = "";
              if(data.length > 0){
               html += "<table id=\"firstEle\"><thead style=\"color:BLUE;\">";
               for(i=0; i<data.length; i++){
                  html += "<tbody><tr><td style=\"width:230px;\" class=\"column1\"><p name=\"btitle\">"+data[i].title +"</p></td><td style=\"width:400px;\" class=\"column2\"><p>"+ data[i].info +"</p></td>";
                  html += "<td class=\"column3\" style=\"text-align:center;\"><div class=\"btn_group\"><button style=\"background-color:#0B173B; border-color:#0B173B;\" class=\"btn btn-sm btn-primary\" onclick=\"deleteBoard(this,'"+data[i].title+"')\" class=\"btn_style\">삭제</button></div></td>";
                  html += "</tr></tbody>";
                   }
               html += "</table>";
             }else{
                html += "등록된 게시판이 없습니다";
             }
                 $("#boardList").html(html);
             }
         });
   }

   function onSuccess(json, status){
       alert($.trim(json));
   }

   function onError(data, status,request){
      alert("ERROR");
   }


</script>
</body>
</html>