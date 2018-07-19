package com.whpu.infoplat.model;


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * TOrders entity. @author MyEclipse Persistence Tools
 */

public class TOrders implements java.io.Serializable {

	// Fields

	private Integer RId;
	private TEmp TEmp;
	private TClient TClient;
	private String RTime;
	private Integer RIs;
	private Set TOrdersmxes = new HashSet(0);

	// Constructors

	private Integer current;
	private Integer up;
	private Integer next;
	private Integer allpages;
	private Integer allcount;

	public TOrders(Integer current, Integer up, Integer next, Integer allpages,
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
	public TOrders() {
	}

	/** full constructor */
	public TOrders(TEmp TEmp, TClient TClient, String RTime, Integer RIs,
			Set TOrdersmxes) {
		this.TEmp = TEmp;
		this.TClient = TClient;
		this.RTime = RTime;
		this.RIs = RIs;
		this.TOrdersmxes = TOrdersmxes;
	}

	// Property accessors

	public Integer getRId() {
		return this.RId;
	}

	public void setRId(Integer RId) {
		this.RId = RId;
	}

	public TEmp getTEmp() {
		return this.TEmp;
	}

	public void setTEmp(TEmp TEmp) {
		this.TEmp = TEmp;
	}

	public TClient getTClient() {
		return this.TClient;
	}

	public void setTClient(TClient TClient) {
		this.TClient = TClient;
	}

	public String getRTime() {
		return this.RTime;
	}

	public void setRTime(String RTime) {
		this.RTime = RTime;
	}

	public Integer getRIs() {
		return this.RIs;
	}

	public void setRIs(Integer RIs) {
		this.RIs = RIs;
	}

	public Set getTOrdersmxes() {
		return this.TOrdersmxes;
	}

	public void setTOrdersmxes(Set TOrdersmxes) {
		this.TOrdersmxes = TOrdersmxes;
	}

}