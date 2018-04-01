package model;

import java.sql.Timestamp;
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
	private Timestamp createDate;
	private Timestamp reserveDate;
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

	private Timestamp buyDate;
	private Timestamp cancelDate;
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

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Timestamp getReserveDate() {
		return reserveDate;
	}

	public void setReserveDate(Timestamp reserveDate) {
		this.reserveDate = reserveDate;
	}

	public Timestamp getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Timestamp buyDate) {
		this.buyDate = buyDate;
	}

	public Timestamp getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Timestamp cancelDate) {
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