package com.bootstrap.test.pagination;

public class Pagination {
   private int listSize = 5;
   private int pageSize = 5;
   /*
    * 전체게시글수 
    * 전체페이지수
    * 가려는 페이지
    * 페이지표시의 시작점
    * 페이지표시의 끝점
    * 게시물표시의 시작점
    * 게시물표시의 끝점
    * */
   private int listCnt;
   private int pageCnt;
   private int destnPage;
   private int startPage;
   private int endPage;
   private int startList;
   private int endList;
   
   public Pagination(){
      
   }
   
   public Pagination(int destnpage, int listCnt) {
      
      //전체게시글수
      this.listCnt = listCnt;
      //전체페이지수
      if((listCnt%this.listSize) == 0) {
         this.pageCnt = (listCnt/this.pageSize);
      }else {
         this.pageCnt = (listCnt/this.pageSize) + 1;
      }
      
      //목표페이지
      this.destnPage = destnpage;
      //페이지 표시의 시작점/끝점
      if((destnpage%this.pageSize) == 0) {
         this.startPage = ((destnpage/this.pageSize)-1)*pageSize + 1;
      }else {
         this.startPage = (destnpage/this.pageSize)*pageSize + 1;
      }
      
      if((destnpage%this.pageSize) == 0) {
         this.endPage = ((destnpage/this.pageSize))*(pageSize);
      }else {
         this.endPage = ((destnpage/this.pageSize)+1)*(pageSize);
      }
      
      if(pageCnt < endPage) {
         this.endPage = pageCnt;
      }
      //게시물 표시의 시작점/끝점
      this.startList = ((destnpage-1)*this.listSize);
      this.endList =  this.startList +(this.listSize);
      if(listCnt < this.endList) {
         this.endList = listCnt;
      }
   }
   public int getListCnt() {
      return listCnt;
   }


   public void setListCnt(int listCnt) {
      this.listCnt = listCnt;
   }


   public int getPageCnt() {
      return pageCnt;
   }


   public void setPageCnt(int pageCnt) {
      this.pageCnt = pageCnt;
   }


   public int getDestnPage() {
      return destnPage;
   }


   public void setDestnPage(int destnPage) {
      this.destnPage = destnPage;
   }


   public int getStartPage() {
      return startPage;
   }


   public void setStartPage(int startPage) {
      this.startPage = startPage;
   }


   public int getEndPage() {
      return endPage;
   }


   public void setEndPage(int endPage) {
      this.endPage = endPage;
   }


   public int getStartList() {
      return startList;
   }


   public void setStartList(int startList) {
      this.startList = startList;
   }


   public int getEndList() {
      return endList;
   }


   public void setEndList(int endList) {
      this.endList = endList;
   }
   public int getListSize() {
      return listSize;
   }

   public void setListSize(int listSize) {
      this.listSize = listSize;
   }

   public int getPageSize() {
      return pageSize;
   }

   public void setPageSize(int pageSize) {
      this.pageSize = pageSize;
   }

   @Override
   public String toString() {
      return "Pagination [listCnt=" + listCnt + ", pageCnt=" + pageCnt + ", destnPage=" + destnPage + ", startPage="
            + startPage + ", endPage=" + endPage + ", startList=" + startList + ", endList=" + endList + "]";
   }
   
   
   
}