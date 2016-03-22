package study.java.jdks.version1_6.jaxb2.a_sample;

import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
//@XmlRootElement
@XmlType(name = "mLoginBean")
public class MLoginBean {
	@XmlElement
	private String userloginname;
	@XmlElement
	private String userPassword;
	@XmlElement
	private double userMAcctBalance;
	@XmlElement
	private Date userMAcctLatestExpireDate;
	@XmlElement
	private double userMAcctLatestExpirePoint;
	@XmlElement
	private long mchtId;
	@XmlElement
	private int mchtType;
	@XmlElement
	private String mchtName;
	public String getUserloginname() {
		return userloginname;
	}
	public void setUserloginname(String userloginname) {
		this.userloginname = userloginname;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public double getUserMAcctBalance() {
		return userMAcctBalance;
	}
	public void setUserMAcctBalance(double userMAcctBalance) {
		this.userMAcctBalance = userMAcctBalance;
	}
	public Date getUserMAcctLatestExpireDate() {
		return userMAcctLatestExpireDate;
	}
	public void setUserMAcctLatestExpireDate(Date userMAcctLatestExpireDate) {
		this.userMAcctLatestExpireDate = userMAcctLatestExpireDate;
	}
	public double getUserMAcctLatestExpirePoint() {
		return userMAcctLatestExpirePoint;
	}
	public void setUserMAcctLatestExpirePoint(double userMAcctLatestExpirePoint) {
		this.userMAcctLatestExpirePoint = userMAcctLatestExpirePoint;
	}
	public long getMchtId() {
		return mchtId;
	}
	public void setMchtId(long mchtId) {
		this.mchtId = mchtId;
	}
	public int getMchtType() {
		return mchtType;
	}
	public void setMchtType(int mchtType) {
		this.mchtType = mchtType;
	}
	public String getMchtName() {
		return mchtName;
	}
	public void setMchtName(String mchtName) {
		this.mchtName = mchtName;
	}
	
}