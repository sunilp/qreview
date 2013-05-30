package models;

import java.util.Date;

public class ReviewDetailHistory {
	private String reviewDetailHtrId;
	private String reviewId;
	private String attrName;
	private String oldVal;
	private String newVal;
	private Date creationDate;
	private String modifier;
	
	public String getReviewDetailHtrId() {
		return reviewDetailHtrId;
	}
	public void setReviewDetailHtrId(String reviewDetailHtrId) {
		this.reviewDetailHtrId = reviewDetailHtrId;
	}
	public String getReviewId() {
		return reviewId;
	}
	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}
	public String getAttrName() {
		return attrName;
	}
	public void setAttrName(String attrName) {
		this.attrName = attrName;
	}
	public String getOldVal() {
		return oldVal;
	}
	public void setOldVal(String oldVal) {
		this.oldVal = oldVal;
	}
	public String getNewVal() {
		return newVal;
	}
	public void setNewVal(String newVal) {
		this.newVal = newVal;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getModifier() {
		return modifier;
	}
	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

}
