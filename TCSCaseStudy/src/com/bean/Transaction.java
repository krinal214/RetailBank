package com.bean;

import java.sql.Timestamp;

public class Transaction 
{
	private int trxnId;
	private int SrcAcctId;
	private int DestAcctId;
	private long amount;
	private char type;
	private Timestamp timestamp;
	
	public int getTrxnId() {
		return trxnId;
	}
	public void setTrxnId(int trxnId) {
		this.trxnId = trxnId;
	}
	public int getSrcAcctId() {
		return SrcAcctId;
	}
	public void setSrcAcctId(int srcAcctId) {
		SrcAcctId = srcAcctId;
	}
	public int getDestAcctId() {
		return DestAcctId;
	}
	public void setDestAcctId(int destAcctId) {
		DestAcctId = destAcctId;
	}
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
}
