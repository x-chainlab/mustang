package com.dimogo.open.myjobs.dto;

import org.hyperic.sigar.FileSystem;
import org.hyperic.sigar.FileSystemUsage;

/**
 * Created by Ethan Xiao on 2017/4/28.
 */
public class DiskRuntimeInfo {
	private String device;
	private String dir;
	private long flags;
	private String fs;
	private String type;
	private long reads;
	private long writes;

	private long total;
	private long free;
	private long avil;
	private long used;
	private double usedPercent;

	public DiskRuntimeInfo() {

	}

	public DiskRuntimeInfo(FileSystem fs, FileSystemUsage usage) {
		device = fs.getDevName();
		dir = fs.getDirName();
		flags = fs.getFlags();
		this.fs = fs.getSysTypeName();
		type = fs.getTypeName();
		reads = usage.getDiskReads();
		writes = usage.getDiskWrites();

		if (fs.getType() == 2) {
			total = usage.getTotal();
			free = usage.getFree();
			avil = usage.getAvail();
			used = usage.getUsed();
			usedPercent = usage.getUsePercent();
		}
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public void setDir(String dir) {
		this.dir = dir;
	}

	public void setFlags(long flags) {
		this.flags = flags;
	}

	public void setFs(String fs) {
		this.fs = fs;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setReads(long reads) {
		this.reads = reads;
	}

	public void setWrites(long writes) {
		this.writes = writes;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public void setFree(long free) {
		this.free = free;
	}

	public void setAvil(long avil) {
		this.avil = avil;
	}

	public void setUsed(long used) {
		this.used = used;
	}

	public void setUsedPercent(double usedPercent) {
		this.usedPercent = usedPercent;
	}

	public double getUsedPercent() {
		return usedPercent;
	}

	public String getDevice() {
		return device;
	}

	public String getDir() {
		return dir;
	}

	public long getFlags() {
		return flags;
	}

	public String getFs() {
		return fs;
	}

	public String getType() {
		return type;
	}

	public long getReads() {
		return reads;
	}

	public long getWrites() {
		return writes;
	}

	public long getTotal() {
		return total;
	}

	public long getFree() {
		return free;
	}

	public long getAvil() {
		return avil;
	}

	public long getUsed() {
		return used;
	}
}
