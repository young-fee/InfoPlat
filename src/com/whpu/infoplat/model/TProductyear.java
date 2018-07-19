package com.whpu.infoplat.model;


/**
 * TProductyear entity. @author MyEclipse Persistence Tools
 */

public class TProductyear implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer YId;
	private TProduct TProduct;
	private String YName;
	private Double YPrice;

	// Constructors private Integer myflag;
	private Integer current;
	private Integer up;
	private Integer next;
	private Integer allpages;
	private Integer allcount;

	public TProductyear(Integer current, Integer up, Integer next,
			Integer allpages, Integer allcount) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/** default constructor */
	public TProductyear() {
	}

	/** full constructor */
	public TProductyear(TProduct TProduct, String YName, Double YPrice) {
		this.TProduct = TProduct;
		this.YName = YName;
		this.YPrice = YPrice;
	}

	// Property accessors

	public Integer getYId() {
		return this.YId;
	}

	public void setYId(Integer YId) {
		this.YId = YId;
	}

	public TProduct getTProduct() {
		return this.TProduct;
	}

	public void setTProduct(TProduct TProduct) {
		this.TProduct = TProduct;
	}

	public String getYName() {
		return this.YName;
	}

	public void setYName(String YName) {
		this.YName = YName;
	}

	public Double getYPrice() {
		return this.YPrice;
	}

	public void setYPrice(Double YPrice) {
		this.YPrice = YPrice;
	}

}