package com.bootstrap.test.pagination;

public class PaginationDto {
   private int startList;
   private int listSize;
   private String boardTitle;
   
   public PaginationDto() {
      
   }
   public PaginationDto(int startList, int listSize, String boardTitle) {
      super();
      this.startList = startList;
      this.listSize = listSize;
      this.boardTitle = boardTitle;
   }

   public int getStartList() {
      return startList;
   }
   public void setStartList(int startList) {
      this.startList = startList;
   }
   public String getBoardTitle() {
      return boardTitle;
   }
   public void setBoardTitle(String boardTitle) {
      this.boardTitle = boardTitle;
   }
   
   public int getListSize() {
      return listSize;
   }
   public void setListSize(int listSize) {
      this.listSize = listSize;
   }
   @Override
   public String toString() {
      return "PaginationDto [startList=" + startList + ", listSIze + "+ listSize + "boardTitle=" + boardTitle + "]";
   }
   
}