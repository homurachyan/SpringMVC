package com.springmvc.entity;

public class Session {
	private String userid;
    private String username;
    private String ip;
    private String timestamp;
    private String sessionid;

	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSessionid() {
		return sessionid;
	}
	public void setSessionid(String sessionid) {
		this.sessionid = sessionid;
	}
	public String toString(){
		return "session={ sessionid ="+this.sessionid+"userid="+this.userid+"username ="+this.username+"ip ="+this.ip+"timestamp ="+this.timestamp+"}";
	}
}
