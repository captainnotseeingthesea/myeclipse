package model;

import java.util.Date;

public class Goods {
	private Integer goodsId;
	private Integer classId;
	private Integer sellerId;
	private String goodsName;
	private double price;
	private Integer status;
	private String picture;
	private String description;
	private String sellerContact;
	private Date createDate;
	private Date reserveDate;
	public Goods(Integer classId, Integer sellerId,
			String goodsName, double price, Integer status,String picture, String description,
			String sellerContact, String buyerContact, Integer buyerId) {
		this.classId = classId;
		this.sellerId = sellerId;
		this.goodsName = goodsName;
		this.price = price;
		this.status = status;
		this.picture=picture;
		this.description = description;
		this.sellerContact = sellerContact;
		this.buyerContact = buyerContact;
		this.buyerId = buyerId;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Goods(){
		
	}

	private Date buyDate;
	private Date cancelDate;
	private String buyerContact;

	public Integer getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}

	public Integer getClassId() {
		return classId;
	}

	public void setClassId(Integer classId) {
		this.classId = classId;
	}

	public Integer getSellerId() {
		return sellerId;
	}

	public void setSellerId(Integer sellerId) {
		this.sellerId = sellerId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSellerContact() {
		return sellerContact;
	}

	public void setSellerContact(String sellerContact) {
		this.sellerContact = sellerContact;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(Date reserveDate) {
		this.reserveDate = reserveDate;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}

	public String getBuyerContact() {
		return buyerContact;
	}

	public void setBuyerContact(String buyerContact) {
		this.buyerContact = buyerContact;
	}

	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

	private Integer buyerId;
}