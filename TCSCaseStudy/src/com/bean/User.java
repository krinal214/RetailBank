package com.bean;

import java.sql.Timestamp;

public class User {
private String userId;
private Timestamp lastLogin;

public User() {}
public String getUserId() {
	return userId;
}
public void setUserId(String userId) {
	this.userId = userId;
}
public Timestamp getLastLogin() {
	return lastLogin;
}
public void setLastLogin(Timestamp lastLogin) {
	this.lastLogin = lastLogin;
}


}
