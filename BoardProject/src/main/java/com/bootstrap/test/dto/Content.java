package com.bootstrap.test.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Content {
   private int ccode;
   //게시글이 포함된 게시판의 번호
   private int bcode;
   //작성자의 pcode
   private int pcode;
   private String pid;
   private String ctitle;
   private String ccontent;
   private String cdate;
   private int chits;
   
   public Content() {
      
   }

   public Content(int bcode, int pcode, String pid, String ctitle, String ccontent) {
      this.bcode = bcode;
      this.pcode = pcode;
      this.pid = pid;
      this.ctitle = ctitle;
      this.ccontent = ccontent;
   }
   
   

   public Content(int ccode, int bcode, int pcode, String pid, String ctitle, String ccontent) {
      this.ccode = ccode;
      this.bcode = bcode;
      this.pcode = pcode;
      this.pid = pid;
      this.ctitle = ctitle;
      this.ccontent = ccontent;
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
      this.cdate = sdf.format(new Date());
   }

   public Content(int ccode, int bcode, int pcode,String pid, String ctitle, String ccontent, String
      cdate, int chits) {
      super();
      this.ccode = ccode;
      this.bcode = bcode;
      this.pcode = pcode;
      this.pid = pid;
      this.ctitle = ctitle;
      this.ccontent = ccontent;
      this.cdate = cdate;
      this.chits = chits;
   }


   public int getCcode() {
      return ccode;
   }
   public void setCcode(int ccode) {
      this.ccode = ccode;
   }
   public int getBcode() {
      return bcode;
   }
   public void setBcode(int bcode) {
      this.bcode = bcode;
   }
   public int getPcode() {
      return pcode;
   }
   public void setPcode(int pcode) {
      this.pcode = pcode;
   }
   public String getPid() {
      return pid;
   }
   public void setPid(String pid) {
      this.pid = pid;
   }
   public String getCtitle() {
      return ctitle;
   }
   public void setCtitle(String ctitle) {
      this.ctitle = ctitle;
   }
   public String getCcontent() {
      return ccontent;
   }
   public void setCcontent(String ccontent) {
      this.ccontent = ccontent;
   }
   public String getCdate() {
      return cdate;
   }
   public void setCdate(String cdate) {
      this.cdate = cdate;
   }
   public int getChits() {
      return chits;
   }
   public void setChits(int chits) {
      this.chits = chits;
   }
   
   
   @Override
   public String toString() {
      return "Content [ccode=" + ccode + ", bcode=" + bcode + ", pcode=" + pcode + ", ctitle=" + ctitle
            + ", ccontent=" + ccontent + ", cdate=" + cdate + ", chits=" + chits + "]";
   }
   
   
}