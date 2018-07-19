package com.whpu.infoplat.model;


import java.util.HashSet;
import java.util.Set;

/**
 * TProduct entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("unchecked")
public class TProduct implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer PId;
	private TSort TSort;
	private String PName;
	private String PArea;
	private String PMl;
	private Integer PIs;

	private Set TProductyears = new HashSet(0);

	// Constructors

	private Integer myflag;
	private Integer current;
	private Integer up;
	private Integer next;
	private Integer allpages;
	private Integer allcount;
	private Integer stocknum=0;

	public TProduct(Integer current, Integer up, Integer next,
			Integer allpages, Integer allcount) {
		super();
		this.current = current;
		this.up = up;
		this.next = next;
		this.allpages = allpages;
		this.allcount = allcount;
	}

	
	public TProduct(Integer pId) {
		super();
		PId = pId;
	}


	public Integer getStocknum() {
		return stocknum;
	}

	public void setStocknum(Integer stocknum) {
		this.stocknum = stocknum;
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
	public TProduct() {
	}

	/** full constructor */
	public TProduct(TSort TSort, String PName, String PArea, String PMl,
			Integer PIs, Set TProductyears) {
		this.TSort = TSort;
		this.PName = PName;
		this.PArea = PArea;
		this.PMl = PMl;
		this.PIs = PIs;
		this.TProductyears = TProductyears;
	}

	// Property accessors

	public Integer getPId() {
		return this.PId;
	}

	public void setPId(Integer PId) {
		this.PId = PId;
	}

	public TSort getTSort() {
		return this.TSort;
	}

	public void setTSort(TSort TSort) {
		this.TSort = TSort;
	}

	public String getPName() {
		return this.PName;
	}

	public void setPName(String PName) {
		this.PName = PName;
	}

	public String getPArea() {
		return this.PArea;
	}

	public void setPArea(String PArea) {
		this.PArea = PArea;
	}

	public String getPMl() {
		return this.PMl;
	}

	public void setPMl(String PMl) {
		this.PMl = PMl;
	}

	public Integer getPIs() {
		return this.PIs;
	}

	public void setPIs(Integer PIs) {
		this.PIs = PIs;
	}

	public Set getTProductyears() {
		return this.TProductyears;
	}

	public void setTProductyears(Set TProductyears) {
		this.TProductyears = TProductyears;
	}

}