package com.xyo2o.domain.user;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 钱包模型
 * @author YLJ
 *
 */
public class Wallet implements Serializable{
	private static final long serialVersionUID = 1L;
	private long walletId; // id
	private BigDecimal accountOver; // 账户余额
	private int integral; // 积分
	public long getWalletId() {
		return walletId;
	}
	public void setWalletId(long walletId) {
		this.walletId = walletId;
	}
	public BigDecimal getAccountOver() {
		return accountOver;
	}
	public void setAccountOver(BigDecimal accountOver) {
		this.accountOver = accountOver;
	}
	public int getIntegral() {
		return integral;
	}
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	@Override
	public String toString() {
		return "Wallet [walletId=" + walletId + ", accountOver=" + accountOver
				+ ", integral=" + integral + "]";
	}
	
}
