package com.whpu.infoplat.model;


import java.math.BigDecimal;


/**
 * TStock entity. @author MyEclipse Persistence Tools
 */

public class TStock  implements java.io.Serializable {


    // Fields    

     private Integer KId;
     private TProduct TProduct;
     private Integer KSum;


    // Constructors

    /** default constructor */
    public TStock() {
    }

    
    /** full constructor */
    public TStock(TProduct TProduct, Integer KSum) {
        this.TProduct = TProduct;
        this.KSum = KSum;
    }

   
    // Property accessors

    public Integer getKId() {
        return this.KId;
    }
    
    public void setKId(Integer KId) {
        this.KId = KId;
    }

    public TProduct getTProduct() {
        return this.TProduct;
    }
    
    public void setTProduct(TProduct TProduct) {
        this.TProduct = TProduct;
    }

    public Integer getKSum() {
        return this.KSum;
    }
    
    public void setKSum(Integer KSum) {
        this.KSum = KSum;
    }
}