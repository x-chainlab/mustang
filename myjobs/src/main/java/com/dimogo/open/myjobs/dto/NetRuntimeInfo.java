package com.dimogo.open.myjobs.dto;

import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;

/**
 * Created by Ethan Xiao on 2017/4/28.
 */
public class NetRuntimeInfo {
	private String device;
	private String ip;
	private String mask;

	private long rxPackets;
	private long txPackets;
	private long rxBytes;
	private long txBytes;
	private long rxErrors;
	private long txErrors;
	private long rxDropped;
	private long txDropped;

	public NetRuntimeInfo() {

	}

	public NetRuntimeInfo(NetInterfaceConfig config, NetInterfaceStat stat) {
		device = config.getName();
		ip = config.getAddress();
		mask = config.getNetmask();

		if (stat != null) {
			rxPackets = stat.getRxPackets();
			txPackets = stat.getTxPackets();
			rxBytes = stat.getRxBytes();
			txBytes = stat.getTxBytes();
			rxErrors = stat.getRxErrors();
			txErrors = stat.getTxErrors();
			rxDropped = stat.getRxDropped();
			txDropped = stat.getTxDropped();
		}
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setMask(String mask) {
		this.mask = mask;
	}

	public void setRxPackets(long rxPackets) {
		this.rxPackets = rxPackets;
	}

	public void setTxPackets(long txPackets) {
		this.txPackets = txPackets;
	}

	public void setRxBytes(long rxBytes) {
		this.rxBytes = rxBytes;
	}

	public void setTxBytes(long txBytes) {
		this.txBytes = txBytes;
	}

	public void setRxErrors(long rxErrors) {
		this.rxErrors = rxErrors;
	}

	public void setTxErrors(long txErrors) {
		this.txErrors = txErrors;
	}

	public void setRxDropped(long rxDropped) {
		this.rxDropped = rxDropped;
	}

	public void setTxDropped(long txDropped) {
		this.txDropped = txDropped;
	}

	public String getDevice() {
		return device;
	}

	public String getIp() {
		return ip;
	}

	public String getMask() {
		return mask;
	}

	public long getRxPackets() {
		return rxPackets;
	}

	public long getTxPackets() {
		return txPackets;
	}

	public long getRxBytes() {
		return rxBytes;
	}

	public long getTxBytes() {
		return txBytes;
	}

	public long getRxErrors() {
		return rxErrors;
	}

	public long getTxErrors() {
		return txErrors;
	}

	public long getRxDropped() {
		return rxDropped;
	}

	public long getTxDropped() {
		return txDropped;
	}
}
