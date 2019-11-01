package com.bootstrap.test.dto;

public class Comment {
   private int cmcode;
   private String pid;
   private int ccode;
   private String cmdate;
   private String cmcontent;
   public Comment() {
      
   }
   public Comment(int ccode, String pid,String cmdate, String cmcontent) {
      super();
      this.ccode = ccode;
      this.pid = pid;
      this.cmdate = cmdate;
      this.cmcontent = cmcontent;
   }
   public Comment(int cmcode, int ccode, String cmdate, String cmcontent) {
      super();
      this.cmcode = cmcode;
      this.ccode = ccode;
      this.cmdate = cmdate;
      this.cmcontent = cmcontent;
   }
   public int getCmcode() {
      return cmcode;
   }
   public void setCmcode(int cmcode) {
      this.cmcode = cmcode;
   }
   public int getCcode() {
      return ccode;
   }
   public void setCcode(int ccode) {
      this.ccode = ccode;
   }
   public String getCmdate() {
      return cmdate;
   }
   public void setCmdate(String cmdate) {
      this.cmdate = cmdate;
   }
   public String getCmcontent() {
      return cmcontent;
   }
   public void setCmcontent(String cmcontent) {
      this.cmcontent = cmcontent;
   }
   
   public String getPid() {
      return pid;
   }
   public void setPid(String pid) {
      this.pid = pid;
   }
   @Override
   public String toString() {
      return "Comment [cmcode=" + cmcode + ", ccode=" + ccode + ", cmdate=" + cmdate + ", cmcontent=" + cmcontent
            + "]";
   }
   
   
}