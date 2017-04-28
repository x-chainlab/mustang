package com.dimogo.open.myjobs.dto;

import org.hyperic.sigar.NetInterfaceConfig;

/**
 * Created by Ethan Xiao on 2017/4/28.
 */
public class EthernetRuntimeInfo {
	private String device;
	private String type;
	private String ip;
	private String mac;
	private String mask;
	private String broadcast;
	private String description;

	public EthernetRuntimeInfo() {

	}

	public EthernetRuntimeInfo(NetInterfaceConfig config) {
		device = config.getName();
		ip = config.getAddress();
		broadcast = config.getBroadcast();
		mac = config.getHwaddr();
		mask = config.getNetmask();
		description = config.getDescription();
		type = config.getType();
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setBroadcast(String broadcast) {
		this.broadcast = broadcast;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDevice() {
		return device;
	}

	public String getIp() {
		return ip;
	}

	public String getBroadcast() {
		return broadcast;
	}

	public String getMac() {
		return mac;
	}

	public String getMask() {
		return mask;
	}

	public String getDescription() {
		return description;
	}

	public String getType() {
		return type;
	}
}
