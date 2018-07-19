package com.whpu.infoplat.model;


import java.util.HashSet;
import java.util.Set;

/**
 * TSort entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("unchecked")
public class TSort implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer SId;
	private String SName;
	private String SRemark;
	private Integer SIs;

	private Set TProducts = new HashSet(0);

	// Constructors

	private Integer myflag;
	private Integer current;
	private Integer up;
	private Integer next;
	private Integer allpages;
	private Integer allcount;

	public TSort(Integer current, Integer up, Integer next, Integer allpages,
			Integer allcount) {
		super();
		this.current = current;
		this.up = up;
		this.next = next;
		this.allpages = allpages;
		this.allcount = allcount;
	}

	public Integer getMyflag() {
		return myflag;
	}

	public void setMyflag(Integer myflag) {
		this.myflag = myflag;
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
	public TSort() {
	}

	/** full constructor */
	public TSort(String SName, String SRemark, Integer SIs, Set TProducts) {
		this.SName = SName;
		this.SRemark = SRemark;
		this.SIs = SIs;
		this.TProducts = TProducts;
	}

	// Property accessors

	public Integer getSId() {
		return this.SId;
	}

	public void setSId(Integer SId) {
		this.SId = SId;
	}

	public String getSName() {
		return this.SName;
	}

	public void setSName(String SName) {
		this.SName = SName;
	}

	public String getSRemark() {
		return this.SRemark;
	}

	public void setSRemark(String SRemark) {
		this.SRemark = SRemark;
	}

	public Integer getSIs() {
		return this.SIs;
	}

	public void setSIs(Integer SIs) {
		this.SIs = SIs;
	}

	public Set getTProducts() {
		return this.TProducts;
	}

	public void setTProducts(Set TProducts) {
		this.TProducts = TProducts;
	}

}