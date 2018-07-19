package com.whpu.infoplat.model;

import java.math.BigDecimal;

/**
 * TInproduct entity. @author MyEclipse Persistence Tools
 */

public class TInproduct implements java.io.Serializable {

	// Fields

	private Integer DId;
	private TEmp TEmp;
	private TProduct TProduct;
	private String DRemark;
	private Integer DNumber;
	private String DTime;

	// Constructors

	private Integer current;
	private Integer up;
	private Integer next;
	private Integer allpages;
	private Integer allcount;

	public TInproduct(Integer current, Integer up, Integer next, Integer allpages,
			Integer allcount) {
		super();
		this.current = current;
		this.up = up;
		this.next = next;
		this.allpages = allpages;
		this.allcount = allcount;
	}

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
	}

	public Integer getUp() {
		return up;
	}

	public void setUp(Integer up) {
		this.up = up;
	}

	public Integer getNext() {
		return next;
	}

	public void setNext(Integer next) {
		this.next = next;
	}

	public Integer getAllpages() {
		return allpages;
	}

	public void setAllpages(Integer allpages) {
		this.allpages = allpages;
	}

	public Integer getAllcount() {
		return allcount;
	}

	public void setAllcount(Integer allcount) {
		this.allcount = allcount;
	}

	/** default constructor */
	public TInproduct() {
	}

	/** full constructor */
	public TInproduct(TEmp TEmp, TProduct TProduct, String DRemark,
			Integer DNumber, String DTime) {
		this.TEmp = TEmp;
		this.TProduct = TProduct;
		this.DRemark = DRemark;
		this.DNumber = DNumber;
		this.DTime = DTime;
	}

	// Property accessors

	public Integer getDId() {
		return this.DId;
	}

	public void setDId(Integer DId) {
		this.DId = DId;
	}

	public TEmp getTEmp() {
		return this.TEmp;
	}

	public void setTEmp(TEmp TEmp) {
		this.TEmp = TEmp;
	}

	public TProduct getTProduct() {
		return this.TProduct;
	}

	public void setTProduct(TProduct TProduct) {
		this.TProduct = TProduct;
	}

	public String getDRemark() {
		return this.DRemark;
	}

	public void setDRemark(String DRemark) {
		this.DRemark = DRemark;
	}

	public Integer getDNumber() {
		return this.DNumber;
	}

	public void setDNumber(Integer DNumber) {
		this.DNumber = DNumber;
	}

	public String getDTime() {
		return this.DTime;
	}

	public void setDTime(String DTime) {
		this.DTime = DTime;
	}

}