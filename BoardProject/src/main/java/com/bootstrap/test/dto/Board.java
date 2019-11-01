package com.bootstrap.test.dto;

public class Board {
   private int bcode;
   private String binfo;
   private String btitle;
   private int bcount;
   
   public Board() {
      
   }

   public int getBcode() {
      return bcode;
   }

   public void setBcode(int bcode) {
      this.bcode = bcode;
   }

   public String getBinfo() {
      return binfo;
   }

   public void setBinfo(String binfo) {
      this.binfo = binfo;
   }

   public String getBtitle() {
      return btitle;
   }

   public void setBtitle(String btitle) {
      this.btitle = btitle;
   }

   public int getBcount() {
      return bcount;
   }

   public void setBcount(int bcount) {
      this.bcount = bcount;
   }

   public Board(int bcode, String binfo, String btitle, int bcount) {
      super();
      this.bcode = bcode;
      this.binfo = binfo;
      this.btitle = btitle;
      this.bcount = bcount;
   }

   @Override
   public String toString() {
      return "Board [bcode=" + bcode + ", binfo=" + binfo + ", btitle=" + btitle + ", bcount=" + bcount + "]";
   }
   

   
   
}