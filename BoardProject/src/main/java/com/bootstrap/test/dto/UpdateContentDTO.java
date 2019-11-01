package com.bootstrap.test.dto;

public class UpdateContentDTO {
   private int ccode;
   private int bcode;
   private String ccontent;
   
   public int getBcode() {
      return bcode;
   }
   public void setBcode(int bcode) {
      this.bcode = bcode;
   }
   public int getCcode() {
      return ccode;
   }
   public void setCcode(int ccode) {
      this.ccode = ccode;
   }
   public String getCcontent() {
      return ccontent;
   }
   public void setCcontent(String ccontent) {
      this.ccontent = ccontent;
   }
   public UpdateContentDTO() {
      
   }
   public UpdateContentDTO(int bcode, int ccode, String ccontent) {
      this.bcode = bcode;
      this.ccode = ccode;
      this.ccontent = ccontent;
   }
   
}