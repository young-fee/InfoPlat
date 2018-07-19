package com.whpu.infoplat.model;


import java.math.BigDecimal;

/**
 * TOrdersmx entity. @author MyEclipse Persistence Tools
 */

public class TOrdersmx implements java.io.Serializable {

	// Fields

	private Integer XId;
	private TOrders TOrders;
	private TProductyear TProductyear;
	private TProduct TProduct;
	private Integer XCount;
	private Double XPrice;

	// Constructors

	private Integer current;
	private Integer up;
	private Integer next;
	private Integer allpages;
	private Integer allcount;
	private Integer scockError;

	public TOrdersmx(Integer current, Integer up, Integer next,
			Integer allpages, Integer allcount) {
		super();
		this.current = current;
		this.up = up;
		this.next = next;
		this.allpages = allpages;
		this.allcount = allcount;
	}

	public Integer getScockError() {
		return scockError;
	}

	public void setScockError(Integer scockError) {
		this.scockError = scockError;
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
	public TOrdersmx() {
	}

	/** full constructor */
	public TOrdersmx(TOrders TOrders, TProductyear TProductyear,
			TProduct TProduct, Integer XCount, Double XPrice) {
		this.TOrders = TOrders;
		this.TProductyear = TProductyear;
		this.TProduct = TProduct;
		this.XCount = XCount;
		this.XPrice = XPrice;
	}

	// Property accessors

	public Integer getXId() {
		return this.XId;
	}

	public void setXId(Integer XId) {
		this.XId = XId;
	}

	public TOrders getTOrders() {
		return this.TOrders;
	}

	public void setTOrders(TOrders TOrders) {
		this.TOrders = TOrders;
	}

	public TProductyear getTProductyear() {
		return this.TProductyear;
	}

	public void setTProductyear(TProductyear TProductyear) {
		this.TProductyear = TProductyear;
	}

	public TProduct getTProduct() {
		return this.TProduct;
	}

	public void setTProduct(TProduct TProduct) {
		this.TProduct = TProduct;
	}

	public Integer getXCount() {
		return this.XCount;
	}

	public void setXCount(Integer XCount) {
		this.XCount = XCount;
	}

	public Double getXPrice() {
		return this.XPrice;
	}

	public void setXPrice(Double XPrice) {
		this.XPrice = XPrice;
	}

}