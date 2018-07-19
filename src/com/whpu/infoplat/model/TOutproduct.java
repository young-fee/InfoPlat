package com.whpu.infoplat.model;


import java.math.BigDecimal;


/**
 * TOutproduct entity. @author MyEclipse Persistence Tools
 */

public class TOutproduct  implements java.io.Serializable {


    // Fields    

     private Integer OId;
     private TEmp TEmpByEId;
     private TEmp TEmpByOEid;
     private TProduct TProduct;
     private Integer ONumber;
     private String OTime;
     private String ORemark;


    // Constructors

    /** default constructor */
    public TOutproduct() {
    }

    
    /** full constructor */
    public TOutproduct(TEmp TEmpByEId, TEmp TEmpByOEid, TProduct TProduct, Integer ONumber, String OTime, String ORemark) {
        this.TEmpByEId = TEmpByEId;
        this.TEmpByOEid = TEmpByOEid;
        this.TProduct = TProduct;
        this.ONumber = ONumber;
        this.OTime = OTime;
        this.ORemark = ORemark;
    }

   
    // Property accessors

    public Integer getOId() {
        return this.OId;
    }
    
    public void setOId(Integer OId) {
        this.OId = OId;
    }

    public TEmp getTEmpByEId() {
        return this.TEmpByEId;
    }
    
    public void setTEmpByEId(TEmp TEmpByEId) {
        this.TEmpByEId = TEmpByEId;
    }

    public TEmp getTEmpByOEid() {
        return this.TEmpByOEid;
    }
    
    public void setTEmpByOEid(TEmp TEmpByOEid) {
        this.TEmpByOEid = TEmpByOEid;
    }

    public TProduct getTProduct() {
        return this.TProduct;
    }
    
    public void setTProduct(TProduct TProduct) {
        this.TProduct = TProduct;
    }

    public Integer getONumber() {
        return this.ONumber;
    }
    
    public void setONumber(Integer ONumber) {
        this.ONumber = ONumber;
    }

    public String getOTime() {
        return this.OTime;
    }
    
    public void setOTime(String OTime) {
        this.OTime = OTime;
    }

    public String getORemark() {
        return this.ORemark;
    }
    
    public void setORemark(String ORemark) {
        this.ORemark = ORemark;
    }
   








}