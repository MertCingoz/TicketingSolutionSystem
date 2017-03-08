package com.biletway.dao.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MAIN_MENU", schema = "TicketingSolutionDb")
public class MainMenu {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private BigInteger id;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "NAME")
	private String name;

	@Column(name = "PAGE")
	private String page;

	@Column(name = "URL")
	private String url;

	@Column(name = "ROOT")
	private BigInteger root;

	@Column(name = "FAICON")
	private String faicon;

	public BigInteger getId() {
		return id;
	}

	public void setId(BigInteger id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public BigInteger getRoot() {
		return root;
	}

	public void setRoot(BigInteger root) {
		this.root = root;
	}

	public String getFaicon() {
		return faicon;
	}

	public void setFaicon(String faicon) {
		this.faicon = faicon;
	}

}
