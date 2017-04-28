package com.dimogo.open.myjobs.dto;

import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;

/**
 * Created by Ethan Xiao on 2017/4/28.
 */
public class CpuRuntimeInfo {
	private int mhz;
	private String vendor;
	private String model;
	private long cacheSize;

	private double user;
	private double sys;
	private double wait;
	private double nice;
	private double idle;
	private double combined;

	public CpuRuntimeInfo() {

	}

	public CpuRuntimeInfo(CpuInfo info, CpuPerc perc) {
		mhz = info.getMhz();
		vendor = info.getVendor();
		model = info.getModel();
		cacheSize = info.getCacheSize();

		user = perc.getUser();
		sys = perc.getSys();
		wait = perc.getWait();
		nice = perc.getNice();
		idle = perc.getIdle();
		combined = perc.getCombined();
	}

	public void setMhz(int mhz) {
		this.mhz = mhz;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public void setCacheSize(long cacheSize) {
		this.cacheSize = cacheSize;
	}

	public void setUser(double user) {
		this.user = user;
	}

	public void setSys(double sys) {
		this.sys = sys;
	}

	public void setWait(double wait) {
		this.wait = wait;
	}

	public void setNice(double nice) {
		this.nice = nice;
	}

	public void setIdle(double idle) {
		this.idle = idle;
	}

	public void setCombined(double combined) {
		this.combined = combined;
	}

	public int getMhz() {
		return mhz;
	}

	public String getVendor() {
		return vendor;
	}

	public String getModel() {
		return model;
	}

	public long getCacheSize() {
		return cacheSize;
	}

	public double getUser() {
		return user;
	}

	public double getSys() {
		return sys;
	}

	public double getWait() {
		return wait;
	}

	public double getNice() {
		return nice;
	}

	public double getIdle() {
		return idle;
	}

	public double getCombined() {
		return combined;
	}
}
