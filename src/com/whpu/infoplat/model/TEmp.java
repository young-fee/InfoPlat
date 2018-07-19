package com.whpu.infoplat.model;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

/**
 * TEmp entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("unchecked")
public class TEmp implements java.io.Serializable {

	// Fields

	private static final long serialVersionUID = 1L;
	private Integer EId;
	private TPart TPart;
	private String ELoginname;
	private String EPsw;
	private String EImg;
	private String ESex;
	private Integer EFlag;
	private Integer EAdmin;
	private String ERemark;
	private String ETruename;
	private Integer EIs;

	private Set TOutproductsForOEid = new HashSet(0);
	private Set TInproducts = new HashSet(0);
	private Set TClients = new HashSet(0);
	private Set TOrderses = new HashSet(0);
	private Set TOutproductsForEId = new HashSet(0);

	// Constructors

	private File EImgimg;
	private String EImgimgFileName;

	private Integer myflag;

	private Integer current;
	private Integer up;
	private Integer next;
	private Integer allpages;
	private Integer allcount;

	public TEmp(Integer current, Integer up, Integer next, Integer allpages,
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

	public Integer getMyflag() {
		return myflag;
	}

	public void setMyflag(Integer myflag) {
		this.myflag = myflag;
	}

	public File getEImgimg() {
		return EImgimg;
	}

	public void setEImgimg(File eImgimg) {
		EImgimg = eImgimg;
	}

	public String getEImgimgFileName() {
		return EImgimgFileName;
	}

	public void setEImgimgFileName(String eImgimgFileName) {
		EImgimgFileName = eImgimgFileName;
	}

	/** default constructor */
	public TEmp() {
	}

	/** full constructor */
	public TEmp(TPart TPart, String ELoginname, String EPsw, String EImg,
			String ESex, Integer EFlag, Integer EAdmin, String ERemark,
			String ETruename, Integer EIs, Set TOutproductsForOEid,
			Set TInproducts, Set TClients, Set TOrderses, Set TOutproductsForEId) {
		this.TPart = TPart;
		this.ELoginname = ELoginname;
		this.EPsw = EPsw;
		this.EImg = EImg;
		this.ESex = ESex;
		this.EFlag = EFlag;
		this.EAdmin = EAdmin;
		this.ERemark = ERemark;
		this.ETruename = ETruename;
		this.EIs = EIs;
		this.TOutproductsForOEid = TOutproductsForOEid;
		this.TInproducts = TInproducts;
		this.TClients = TClients;
		this.TOrderses = TOrderses;
		this.TOutproductsForEId = TOutproductsForEId;
	}

	// Property accessors

	public Integer getEId() {
		return this.EId;
	}

	public void setEId(Integer EId) {
		this.EId = EId;
	}

	public TPart getTPart() {
		return this.TPart;
	}

	public void setTPart(TPart TPart) {
		this.TPart = TPart;
	}

	public String getELoginname() {
		return this.ELoginname;
	}

	public void setELoginname(String ELoginname) {
		this.ELoginname = ELoginname;
	}

	public String getEPsw() {
		return this.EPsw;
	}

	public void setEPsw(String EPsw) {
		this.EPsw = EPsw;
	}

	public String getEImg() {
		return this.EImg;
	}

	public void setEImg(String EImg) {
		this.EImg = EImg;
	}

	public String getESex() {
		return this.ESex;
	}

	public void setESex(String ESex) {
		this.ESex = ESex;
	}

	public Integer getEFlag() {
		return this.EFlag;
	}

	public void setEFlag(Integer EFlag) {
		this.EFlag = EFlag;
	}

	public Integer getEAdmin() {
		return this.EAdmin;
	}

	public void setEAdmin(Integer EAdmin) {
		this.EAdmin = EAdmin;
	}

	public String getERemark() {
		return this.ERemark;
	}

	public void setERemark(String ERemark) {
		this.ERemark = ERemark;
	}

	public String getETruename() {
		return this.ETruename;
	}

	public void setETruename(String ETruename) {
		this.ETruename = ETruename;
	}

	public Integer getEIs() {
		return this.EIs;
	}

	public void setEIs(Integer EIs) {
		this.EIs = EIs;
	}

	public Set getTOutproductsForOEid() {
		return this.TOutproductsForOEid;
	}

	public void setTOutproductsForOEid(Set TOutproductsForOEid) {
		this.TOutproductsForOEid = TOutproductsForOEid;
	}

	public Set getTInproducts() {
		return this.TInproducts;
	}

	public void setTInproducts(Set TInproducts) {
		this.TInproducts = TInproducts;
	}

	public Set getTClients() {
		return this.TClients;
	}

	public void setTClients(Set TClients) {
		this.TClients = TClients;
	}

	public Set getTOrderses() {
		return this.TOrderses;
	}

	public void setTOrderses(Set TOrderses) {
		this.TOrderses = TOrderses;
	}

	public Set getTOutproductsForEId() {
		return this.TOutproductsForEId;
	}

	public void setTOutproductsForEId(Set TOutproductsForEId) {
		this.TOutproductsForEId = TOutproductsForEId;
	}

}