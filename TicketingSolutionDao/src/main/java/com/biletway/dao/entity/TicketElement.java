package com.biletway.dao.entity;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TICKET_ELEMENT", schema = "TicketingSolutionDb")
public class TicketElement {
	@Id
	@GeneratedValue
	@Column(name = "ID")
	private BigInteger id;

	@Column(name = "STATUS")
	private String status;

	@Column(name = "TICKET_DESIGN_ID")
	private BigInteger ticketDesignId;

	@Column(name = "ELEMENT_TYPE")
	private String elementType;

	@Column(name = "ELEMENT_ID")
	private String elementId;

	@Column(name = "XAXIS")
	private int xAxis;

	@Column(name = "YAXIS")
	private int yAxis;

	@Column(name = "ELEMENT_ORDER")
	private int elementOrder;

	@Column(name = "FIELD_TYPE")
	private String fieldType;

	@Column(name = "WIDTH")
	private int width;

	@Column(name = "HEIGHT")
	private int height;

	@Column(name = "PICTURE")
	private String picture;

	@Column(name = "FONT")
	private String font;

	@Column(name = "SIZE")
	private String size;

	@Column(name = "DB_FILE_NAME")
	private String dbFileName;

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

	public BigInteger getTicketDesignId() {
		return ticketDesignId;
	}

	public void setTicketDesignId(BigInteger ticketDesignId) {
		this.ticketDesignId = ticketDesignId;
	}

	public String getElementType() {
		return elementType;
	}

	public void setElementType(String elementType) {
		this.elementType = elementType;
	}

	public String getElementId() {
		return elementId;
	}

	public void setElementId(String elementId) {
		this.elementId = elementId;
	}

	public int getxAxis() {
		return xAxis;
	}

	public void setxAxis(int xAxis) {
		this.xAxis = xAxis;
	}

	public int getyAxis() {
		return yAxis;
	}

	public void setyAxis(int yAxis) {
		this.yAxis = yAxis;
	}

	public int getOrder() {
		return elementOrder;
	}

	public void setOrder(int elementOrder) {
		this.elementOrder = elementOrder;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getFont() {
		return font;
	}

	public void setFont(String font) {
		this.font = font;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getDbFileName() {
		return dbFileName;
	}

	public void setDbFileName(String dbFileName) {
		this.dbFileName = dbFileName;
	}

}
