package com.whpu.infoplat.model;

import java.util.HashSet;
import java.util.Set;

/**
 * TClient entity. @author MyEclipse Persistence Tools
 */

public class TClient implements java.io.Serializable {

	// Fields

	private Integer CId;
	private TEmp TEmp;
	private String CName;
	private String CTel;
	private String CAddress;
	private Integer CIs;
	private Set TOrderses = new HashSet(0);

	// Constructors

	private Integer current;
	private Integer up;
	private Integer next;
	private Integer allpages;
	private Integer allcount;

	public TClient(Integer current, Integer up, Integer next, Integer allpages,
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
	public TClient() {
	}

	/** full constructor */
	public TClient(TEmp TEmp, String CName, String CTel, String CAddress,
			Integer CIs, Set TOrderses) {
		this.TEmp = TEmp;
		this.CName = CName;
		this.CTel = CTel;
		this.CAddress = CAddress;
		this.CIs = CIs;
		this.TOrderses = TOrderses;
	}

	// Property accessors

	public Integer getCId() {
		return this.CId;
	}

	public void setCId(Integer CId) {
		this.CId = CId;
	}

	public TEmp getTEmp() {
		return this.TEmp;
	}

	public void setTEmp(TEmp TEmp) {
		this.TEmp = TEmp;
	}

	public String getCName() {
		return this.CName;
	}

	public void setCName(String CName) {
		this.CName = CName;
	}

	public String getCTel() {
		return this.CTel;
	}

	public void setCTel(String CTel) {
		this.CTel = CTel;
	}

	public String getCAddress() {
		return this.CAddress;
	}

	public void setCAddress(String CAddress) {
		this.CAddress = CAddress;
	}

	public Integer getCIs() {
		return this.CIs;
	}

	public void setCIs(Integer CIs) {
		this.CIs = CIs;
	}

	public Set getTOrderses() {
		return this.TOrderses;
	}

	public void setTOrderses(Set TOrderses) {
		this.TOrderses = TOrderses;
	}

}