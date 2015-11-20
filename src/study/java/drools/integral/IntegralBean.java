package study.java.drools.integral;


//过生日，则加10积分

//2011-01-08 - 2011-08-08每月信用卡还款3次以上，每满3笔赠送30分

//当月购物总金额100以上，每100元赠送10分

//当月购物次数5次以上，每五次赠送50分

//特别的，如果全部满足了要求，则额外奖励100分

//发生退货，扣减10分

//退货金额大于100，扣减100分

public class IntegralBean {
	// 用户名称
	private String userName;
	// 是否当日生日
	private boolean birthDay;
	// 增加积分数目
	private long point;
	// 当月购物次数
	private int buyNums;
	// 当月退款次数
	private int backNums;
	//当月购物总金额	
	private double buyMoney;
	// 当月退货总金额
	private double backMondy;
	// 当月信用卡还款次数	
	private int billThisMonth;
	
	/**
	 * 记录积分变化流水
	 * @param userName 用户名称
	 * @param type 积分发放类型
	 */
	public void recordPointLog(String userName, String type,long ponit){
		System.out.println("增加"+userName+"的类型为"+type+"的积分操作，积分总额"+ponit);
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isBirthDay() {
		return birthDay;
	}

	public void setBirthDay(boolean birthDay) {
		this.birthDay = birthDay;
	}

	public long getPoint() {
		return point;
	}

	public void setPoint(long point) {
		this.point = point;
	}

	public int getBuyNums() {
		return buyNums;
	}

	public void setBuyNums(int buyNums) {
		this.buyNums = buyNums;
	}

	public int getBackNums() {
		return backNums;
	}

	public void setBackNums(int backNums) {
		this.backNums = backNums;
	}

	public double getBuyMoney() {
		return buyMoney;
	}

	public void setBuyMoney(double buyMoney) {
		this.buyMoney = buyMoney;
	}

	public double getBackMondy() {
		return backMondy;
	}

	public void setBackMondy(double backMondy) {
		this.backMondy = backMondy;
	}

	public int getBillThisMonth() {
		return billThisMonth;
	}

	public void setBillThisMonth(int billThisMonth) {
		this.billThisMonth = billThisMonth;
	}

}
