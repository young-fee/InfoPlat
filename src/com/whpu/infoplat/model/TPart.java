package com.whpu.infoplat.model;


import java.util.HashSet;
import java.util.Set;

/**
 * TPart entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("unchecked")
public class TPart implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 7772456226719255530L;
	private Integer PId;
	private String PName;
	private String PRemark;
	private Integer PIs;

	private Set TEmps = new HashSet(0);

	// Constructors

	private Integer myflag;
	private Integer current;
	private Integer up;
	private Integer next;
	private Integer allpages;
	private Integer allcount;

	public TPart(Integer current, Integer up, Integer next, Integer allpages,
			Integer allcount) {
		super();
		this.current = current;
		this.up = up;
		this.next = next;
		this.allpages = allpages;
		this.allcount = allcount;
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

	public Integer getCurrent() {
		return current;
	}

	public void setCurrent(Integer current) {
		this.current = current;
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

	public Integer getMyflag() {
		return myflag;
	}

	public void setMyflag(Integer myflag) {
		this.myflag = myflag;
	}

	/** default constructor */
	public TPart() {
	}

	/** full constructor */
	public TPart(String PName, String PRemark, Integer PIs, Set TEmps) {
		this.PName = PName;
		this.PRemark = PRemark;
		this.PIs = PIs;
		this.TEmps = TEmps;
	}

	// Property accessors

	public Integer getPId() {
		return this.PId;
	}

	public void setPId(Integer PId) {
		this.PId = PId;
	}

	public String getPName() {
		return this.PName;
	}

	public void setPName(String PName) {
		this.PName = PName;
	}

	public String getPRemark() {
		return this.PRemark;
	}

	public void setPRemark(String PRemark) {
		this.PRemark = PRemark;
	}

	public Integer getPIs() {
		return this.PIs;
	}

	public void setPIs(Integer PIs) {
		this.PIs = PIs;
	}

	public Set getTEmps() {
		return this.TEmps;
	}

	public void setTEmps(Set TEmps) {
		this.TEmps = TEmps;
	}

}