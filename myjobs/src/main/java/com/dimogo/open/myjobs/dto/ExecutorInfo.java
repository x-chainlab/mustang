package com.dimogo.open.myjobs.dto;

/**
 * Created by Ethan Xiao on 2017/4/19.
 */
public class ExecutorInfo {
	private String id;
	private String host;
	private String ip;
	private String arch;
	private double cpuUsedPercent;
	private double diskUsedPercent;
	private String osVendorName;
	private String osVersion;

	public String getOsVendorName() {
		return osVendorName;
	}

	public void setOsVendorName(String osVendorName) {
		this.osVendorName = osVendorName;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getArch() {
		return arch;
	}

	public void setArch(String arch) {
		this.arch = arch;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public double getCpuUsedPercent() {
		return cpuUsedPercent;
	}

	public void setCpuUsedPercent(double cpuUsedPercent) {
		this.cpuUsedPercent = cpuUsedPercent;
	}

	public double getDiskUsedPercent() {
		return diskUsedPercent;
	}

	public void setDiskUsedPercent(double diskUsedPercent) {
		this.diskUsedPercent = diskUsedPercent;
	}
}
