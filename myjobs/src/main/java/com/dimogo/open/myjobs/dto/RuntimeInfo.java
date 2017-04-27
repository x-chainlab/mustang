package com.dimogo.open.myjobs.dto;

import org.apache.commons.collections.CollectionUtils;
import org.hyperic.sigar.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Ethan Xiao on 2017/4/27.
 */
public class RuntimeInfo {

	private String javaLibraryPath = System.getProperty("java.library.path");

	//vm
	private String ip;
	private String hostName;
	private long vmTotalMemory;
	private long vmFreeMemory;
	private int javaAvailableProcessors;

	//memory and swap
	private long totalMemory;
	private long usedMemory;
	private long freeMemory;
	private long totalSwap;
	private long usedSwap;
	private long freeSwap;

	//OS
	private String osArch;
	private String osCpuEndian;
	private String osDataModel;
	private String osDescription;
	private String osVendor;
	private String osVendorCodeName;
	private String osVendorName;
	private String osVendorVersion;
	private String osVersion;

	private List<CpuRuntimeInfo> cpus;
	private List<DiskRuntimeInfo> disks;
	private List<NetRuntimeInfo> nets;
	private List<EthernetRuntimeInfo> ethernets;
	private Map<String, String> systemProperties;

	public void setJavaLibraryPath(String javaLibraryPath) {
		this.javaLibraryPath = javaLibraryPath;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public void setVmTotalMemory(long vmTotalMemory) {
		this.vmTotalMemory = vmTotalMemory;
	}

	public void setVmFreeMemory(long vmFreeMemory) {
		this.vmFreeMemory = vmFreeMemory;
	}

	public void setJavaAvailableProcessors(int javaAvailableProcessors) {
		this.javaAvailableProcessors = javaAvailableProcessors;
	}

	public void setTotalMemory(long totalMemory) {
		this.totalMemory = totalMemory;
	}

	public void setUsedMemory(long usedMemory) {
		this.usedMemory = usedMemory;
	}

	public void setFreeMemory(long freeMemory) {
		this.freeMemory = freeMemory;
	}

	public void setTotalSwap(long totalSwap) {
		this.totalSwap = totalSwap;
	}

	public void setUsedSwap(long usedSwap) {
		this.usedSwap = usedSwap;
	}

	public void setFreeSwap(long freeSwap) {
		this.freeSwap = freeSwap;
	}

	public void setOsArch(String osArch) {
		this.osArch = osArch;
	}

	public void setOsCpuEndian(String osCpuEndian) {
		this.osCpuEndian = osCpuEndian;
	}

	public void setOsDataModel(String osDataModel) {
		this.osDataModel = osDataModel;
	}

	public void setOsDescription(String osDescription) {
		this.osDescription = osDescription;
	}

	public void setOsVendor(String osVendor) {
		this.osVendor = osVendor;
	}

	public void setOsVendorCodeName(String osVendorCodeName) {
		this.osVendorCodeName = osVendorCodeName;
	}

	public void setOsVendorName(String osVendorName) {
		this.osVendorName = osVendorName;
	}

	public void setOsVendorVersion(String osVendorVersion) {
		this.osVendorVersion = osVendorVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public void setCpus(List<CpuRuntimeInfo> cpus) {
		this.cpus = cpus;
	}

	public void setDisks(List<DiskRuntimeInfo> disks) {
		this.disks = disks;
	}

	public void setNets(List<NetRuntimeInfo> nets) {
		this.nets = nets;
	}

	public void setEthernets(List<EthernetRuntimeInfo> ethernets) {
		this.ethernets = ethernets;
	}

	public void setSystemProperties(Map<String, String> systemProperties) {
		this.systemProperties = systemProperties;
	}

	public String getJavaLibraryPath() {
		return javaLibraryPath;
	}

	public String getIp() {
		return ip;
	}

	public String getHostName() {
		return hostName;
	}

	public long getVmTotalMemory() {
		return vmTotalMemory;
	}

	public long getVmFreeMemory() {
		return vmFreeMemory;
	}

	public int getJavaAvailableProcessors() {
		return javaAvailableProcessors;
	}

	public long getTotalMemory() {
		return totalMemory;
	}

	public long getUsedMemory() {
		return usedMemory;
	}

	public long getFreeMemory() {
		return freeMemory;
	}

	public long getTotalSwap() {
		return totalSwap;
	}

	public long getUsedSwap() {
		return usedSwap;
	}

	public long getFreeSwap() {
		return freeSwap;
	}

	public String getOsArch() {
		return osArch;
	}

	public String getOsCpuEndian() {
		return osCpuEndian;
	}

	public String getOsDataModel() {
		return osDataModel;
	}

	public String getOsDescription() {
		return osDescription;
	}

	public String getOsVendor() {
		return osVendor;
	}

	public String getOsVendorCodeName() {
		return osVendorCodeName;
	}

	public String getOsVendorName() {
		return osVendorName;
	}

	public String getOsVendorVersion() {
		return osVendorVersion;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public List<CpuRuntimeInfo> getCpus() {
		return cpus;
	}

	public List<DiskRuntimeInfo> getDisks() {
		return disks;
	}

	public List<NetRuntimeInfo> getNets() {
		return nets;
	}

	public List<EthernetRuntimeInfo> getEthernets() {
		return ethernets;
	}

	public Map<String, String> getSystemProperties() {
		return systemProperties;
	}

	public double getCpusUsedPercent() {
		if (CollectionUtils.isEmpty(cpus)) {
			return 0d;
		}
		double usedPercent = 0;
		for (CpuRuntimeInfo cpu : cpus) {
			usedPercent += cpu.getCombined();
		}
		return usedPercent;
	}

	public double getDisksUsedPercent() {
		if (CollectionUtils.isEmpty(disks)) {
			return 0d;
		}
		double usedPercent = 0;
		for (DiskRuntimeInfo disk : disks) {
			usedPercent += disk.getUsedPercent();
		}
		return usedPercent;
	}

	public RuntimeInfo() {

	}

	public void setup() {
		InetAddress addr;
		try {
			addr = InetAddress.getLocalHost();
			ip = addr.getHostAddress();
			hostName = addr.getHostName();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		Runtime r = Runtime.getRuntime();
		vmTotalMemory = r.totalMemory();
		vmFreeMemory = r.freeMemory();
		javaAvailableProcessors = r.availableProcessors();

		systemProperties = System.getenv();

		Sigar sigar;
		try {
			sigar = new Sigar();
		} catch (Throwable e) {
			return;
		}

		try {
			Mem mem = sigar.getMem();
			totalMemory = mem.getTotal();
			usedMemory = mem.getUsed();
			freeMemory = mem.getFree();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		try {
			Swap swap = sigar.getSwap();
			totalSwap = swap.getTotal();
			usedSwap = swap.getUsed();
			freeSwap = swap.getFree();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		try {
			CpuInfo infos[] = sigar.getCpuInfoList();
			CpuPerc cpuList[] = sigar.getCpuPercList();
			cpus = new LinkedList<CpuRuntimeInfo>();
			for (int i = 0; i < infos.length; i++) {
				cpus.add(new CpuRuntimeInfo(infos[i], cpuList[i]));
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}

		try {
			OperatingSystem OS = OperatingSystem.getInstance();
			osArch = OS.getArch();
			osCpuEndian = OS.getCpuEndian();
			osDataModel = OS.getDataModel();
			osDescription = OS.getDescription();
			osVendor = OS.getVendor();
			osVendorCodeName = OS.getVendorCodeName();
			osVendorName = OS.getVendorName();
			osVendorVersion = OS.getVendorVersion();
			osVersion = OS.getVersion();
		} catch (Throwable e) {
			e.printStackTrace();
		}

		try {
			FileSystem fslist[] = sigar.getFileSystemList();
			disks = new LinkedList<DiskRuntimeInfo>();
			for (FileSystem fs : fslist) {
				disks.add(new DiskRuntimeInfo(fs, sigar.getFileSystemUsage(fs.getDirName())));
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}

		try {
			String ifNames[] = sigar.getNetInterfaceList();
			nets = new LinkedList<NetRuntimeInfo>();
			ethernets = new LinkedList<EthernetRuntimeInfo>();
			for (String name : ifNames) {
				NetInterfaceConfig ifconfig = sigar.getNetInterfaceConfig(name);
				NetInterfaceStat ifstat = ((ifconfig.getFlags() & 1L) <= 0L) ? null : sigar.getNetInterfaceStat(name);
				nets.add(new NetRuntimeInfo(ifconfig, ifstat));
				if (NetFlags.LOOPBACK_ADDRESS.equals(ifconfig.getAddress()) || (ifconfig.getFlags() & NetFlags.IFF_LOOPBACK) != 0
						|| NetFlags.NULL_HWADDR.equals(ifconfig.getHwaddr())) {
					continue;
				}
				ethernets.add(new EthernetRuntimeInfo(ifconfig));
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

	private static class EthernetRuntimeInfo {
		private String device;
		private String ip;
		private String broadcast;
		private String mac;
		private String mask;
		private String description;
		private String type;

		public EthernetRuntimeInfo() {

		}

		private EthernetRuntimeInfo(NetInterfaceConfig config) {
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

	private static class NetRuntimeInfo {
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

		private NetRuntimeInfo(NetInterfaceConfig config, NetInterfaceStat stat) {
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

	private static class DiskRuntimeInfo {
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

		private DiskRuntimeInfo(FileSystem fs, FileSystemUsage usage) {
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

	private static class CpuRuntimeInfo {
		private int mHz;
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

		private CpuRuntimeInfo(CpuInfo info, CpuPerc perc) {
			mHz = info.getMhz();
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

		public void setmHz(int mHz) {
			this.mHz = mHz;
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

		public int getmHz() {
			return mHz;
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
}
