package study.java.jsrs.jsr303;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class Order {

	 // 必须不为 null, 大小是 10 
	 @NotNull 
	 private String orderId; 
	 // 必须不为空
	 @NotNull 
	 private String customer; 
	 // 必须是一个电子信箱地址
	 @NotNull 
	 private String email; 
	 // 必须不为空
	 @NotNull 
	 private String address; 
	 // 必须不为 null, 必须是下面四个字符串'created', 'paid', 'shipped', 'closed'其中之一
	 // @Status 是一个定制化的 contraint 
	 @NotNull 
	 @Status 
	 private String status; 
	 // 必须不为 null 
	 @NotNull 
	 private Date createDate;
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	} 
}
