<#import "/spring.ftl" as spring />
<#escape x as x?html>
<h2>Clustered Executor Infomation</h2>
<div id="executor">
    <#if executor??>
        <p/>
        <ol>
            <li><label for="executor.id">Executor ID</label><span id="executor.id">${executor.id}</span></li>
            <#if executor.runtime??>
                <li><label for="executor.runtime.time">Update Time</label><span
                        id="executor.runtime.time">${(executor.runtime.time?string("yyyy-MM-dd HH:mm:ss"))!'Unknow'}</span></li>
                <li><label for="executor.runtime.ip">IP Address</label><span
                        id="executor.runtime.ip"><#if executor.runtime.ip??>${executor.runtime.ip}</#if></span></li>
                <li><label for="executor.runtime.hostName">Host Name</label><span
                        id="executor.runtime.hostName"><#if executor.runtime.hostName??>${executor.runtime.hostName}</#if></span></li>
                <li><label for="executor.runtime.vmTotalMemory">JVM Total Memory</label><span
                        id="executor.runtime.vmTotalMemory">${executor.runtime.vmTotalMemory}</span></li>
                <li><label for="executor.runtime.vmFreeMemory">JVM Free Memory</label><span
                        id="executor.runtime.vmFreeMemory">${executor.runtime.vmFreeMemory}</span></li>
                <li><label for="executor.runtime.totalMemory">Total Memory</label><span
                        id="executor.runtime.totalMemory">${executor.runtime.totalMemory}</span></li>
                <li><label for="executor.runtime.usedMemory">Used Memory</label><span
                        id="executor.runtime.usedMemory">${executor.runtime.usedMemory}</span></li>
                <li><label for="executor.runtime.freeMemory">Free Memory</label><span
                        id="executor.runtime.ip">${executor.runtime.freeMemory}</span></li>
                <li><label for="executor.runtime.totalSwap">Total Swap</label><span
                        id="executor.runtime.totalSwap">${executor.runtime.totalSwap}</span></li>
                <li><label for="executor.runtime.usedSwap">Used Swap</label><span
                        id="executor.runtime.usedSwap">${executor.runtime.usedSwap}</span></li>
                <li><label for="executor.runtime.freeSwap">Free Swap</label><span
                        id="executor.runtime.freeSwap">${executor.runtime.freeSwap}</span></li>
                <li><label for="executor.runtime.osArch">OS Arch</label><span
                        id="executor.runtime.osArch"><#if executor.runtime.osArch??>${executor.runtime.osArch}</#if></span>
                </li>
                <li><label for="executor.runtime.osCpuEndian">OS CPU Endian</label><span
                        id="executor.runtime.osCpuEndian"><#if executor.runtime.osCpuEndian??>${executor.runtime.osCpuEndian}</#if></span>
                </li>
                <li><label for="executor.runtime.osDataModel">OS Data Model</label><span
                        id="executor.runtime.osDataModel"><#if executor.runtime.osDataModel??>${executor.runtime.osDataModel}</#if></span>
                </li>
                <li><label for="executor.runtime.osDescription">OS Description</label><span
                        id="executor.runtime.osDescription"><#if executor.runtime.osDescription??>${executor.runtime.osDescription}</#if></span>
                </li>
                <li><label for="executor.runtime.osVendor">OS Vendor</label><span
                        id="executor.runtime.osVendor"><#if executor.runtime.osVendor??>${executor.runtime.osVendor}</#if></span></li>
                <li><label for="executor.runtime.osVendorCodeName">OS Vendor Code</label><span
                        id="executor.runtime.osVendorCodeName"><#if executor.runtime.osVendorCodeName??>${executor.runtime.osVendorCodeName}</#if></span>
                </li>
                <li><label for="executor.runtime.osVendorName">OS Vendor Name</label><span
                        id="executor.runtime.osVendorName"><#if executor.runtime.osVendorName??>${executor.runtime.osVendorName}</#if></span>
                </li>
                <li><label for="executor.runtime.osVendorVersion">OS Vendor Version</label><span
                        id="executor.runtime.osVendorVersion"><#if executor.runtime.osVendorVersion??>${executor.runtime.osVendorVersion}</#if></span>
                </li>
                <li><label for="executor.runtime.osVersion">OS Version</label><span
                        id="executor.runtime.osVersion"><#if executor.runtime.osVersion??>${executor.runtime.osVersion}</#if></span></li>
            <#else>
                <li><label>Sigar</label><span>Please install Sigar 1.6.5 into Java Library Path</span></li>
            </#if>
        </ol>
        <br/>
    </#if>
    <#if cpus?? && cpus?size!=0>
        <p>

        <h3>CPU:</h3></p>
        <table class="bordered-table">
            <tr>
                <th>Index</th>
                <th>Vendor</th>
                <th>Model</th>
                <th>MHz</th>
                <th>Cache</th>
                <th>us</th>
                <th>sys</th>
                <th>wait</th>
                <th>nice</th>
                <th>idle</th>
                <th>combined</th>
            </tr>
            <#list cpus as cpu>
                <#if cpu_index % 2 == 0>
                    <#assign rowClass="name-sublevel1-even"/>
                <#else>
                    <#assign rowClass="name-sublevel1-odd"/>
                </#if>
                <tr class="${rowClass}">
                    <td>${cpu_index + 1}</td>
                    <td><#if cpu.vendor??>${cpu.vendor}</#if></td>
                    <td><#if cpu.model??>${cpu.model}</#if></td>
                    <td>${cpu.mhz}</td>
                    <td>${cpu.cacheSize}</td>
                    <td>${cpu.user}</td>
                    <td>${cpu.sys}</td>
                    <td>${cpu.wait}</td>
                    <td>${cpu.nice}</td>
                    <td>${cpu.idle}</td>
                    <td>${cpu.combined}</td>
                </tr>
            </#list>
        </table>
    </#if>
    <#if disks?? && disks?size!=0>
        <p>

        <h3>Disk:</h3></p>
        <table class="bordered-table">
            <tr>
                <th>Index</th>
                <th>Device</th>
                <th>Dir</th>
                <th>Flags</th>
                <th>FS</th>
                <th>Type</th>
                <th>Reads</th>
                <th>Writes</th>
                <th>Total</th>
                <th>Free</th>
                <th>Avil</th>
                <th>Used</th>
                <th>Used%</th>
            </tr>
            <#list disks as disk>
                <#if disk_index % 2 == 0>
                    <#assign rowClass="name-sublevel1-even"/>
                <#else>
                    <#assign rowClass="name-sublevel1-odd"/>
                </#if>
                <tr class="${rowClass}">
                    <td>${disk_index + 1}</td>
                    <td><#if disk.device??>${disk.device}</#if></td>
                    <td><#if disk.dir??>${disk.dir}</#if></td>
                    <td>${disk.flags}</td>
                    <td><#if disk.fs??>${disk.fs}</#if></td>
                    <td><#if disk.type??>${disk.type}</#if></td>
                    <td>${disk.reads}</td>
                    <td>${disk.writes}</td>
                    <td>${disk.total}</td>
                    <td>${disk.free}</td>
                    <td>${disk.avil}</td>
                    <td>${disk.used}</td>
                    <td>${disk.usedPercent * 100}%</td>
                </tr>
            </#list>
        </table>
    </#if>
    <#if nets?? && nets?size!=0>
        <p>

        <h3>Network:</h3></p>
        <table class="bordered-table">
            <tr>
                <th>Index</th>
                <th>Device</th>
                <th>IP</th>
                <th>Mask</th>
                <th>RXPackets</th>
                <th>TXPackets</th>
                <th>RXBytes</th>
                <th>TXBytes</th>
                <th>RXErrors</th>
                <th>TXErrors</th>
                <th>RXDropped</th>
                <th>TXDropped</th>
            </tr>
            <#list nets as net>
                <#if net_index % 2 == 0>
                    <#assign rowClass="name-sublevel1-even"/>
                <#else>
                    <#assign rowClass="name-sublevel1-odd"/>
                </#if>
                <tr class="${rowClass}">
                    <td>${net_index + 1}</td>
                    <td><#if net.device??>${net.device}</#if></td>
                    <td><#if net.ip??>${net.ip}</#if></td>
                    <td><#if net.mask??>${net.mask}</#if></td>
                    <td>${net.rxPackets}</td>
                    <td>${net.txPackets}</td>
                    <td>${net.rxBytes}</td>
                    <td>${net.txBytes}</td>
                    <td>${net.rxErrors}</td>
                    <td>${net.txErrors}</td>
                    <td>${net.rxDropped}</td>
                    <td>${net.txDropped}</td>
                </tr>
            </#list>
        </table>
    </#if>
    <#if ethernets?? && ethernets?size!=0>
        <p>

        <h3>Ethernet:</h3></p>
        <table class="bordered-table">
            <tr>
                <th>Index</th>
                <th>Device</th>
                <th>Type</th>
                <th>IP</th>
                <th>MAC</th>
                <th>Mask</th>
                <th>Broadcast</th>
                <th>Description</th>
            </tr>
            <#list ethernets as net>
                <#if net_index % 2 == 0>
                    <#assign rowClass="name-sublevel1-even"/>
                <#else>
                    <#assign rowClass="name-sublevel1-odd"/>
                </#if>
                <tr class="${rowClass}">
                    <td>${net_index + 1}</td>
                    <td><#if net.device??>${net.device}</#if></td>
                    <td><#if net.type??>${net.type}</#if></td>
                    <td><#if net.ip??>${net.ip}</#if></td>
                    <td><#if net.mac??>${net.mac}</#if></td>
                    <td><#if net.mask??>${net.mask}</#if></td>
                    <td><#if net.broadcast??>${net.broadcast}</#if></td>
                    <td><#if net.description??>${net.description}</#if></td>
                </tr>
            </#list>
        </table>
    </#if>
    <#if properties?? && properties?size!=0>
        <p>

        <h3>System Properties:</h3></p>
        <table class="bordered-table">
            <tr>
                <th>Index</th>
                <th>Name</th>
                <th>Value</th>
            </tr>
            <#list properties?keys as key>
                <#if key_index % 2 == 0>
                    <#assign rowClass="name-sublevel1-even"/>
                <#else>
                    <#assign rowClass="name-sublevel1-odd"/>
                </#if>
                <tr class="${rowClass}">
                    <td>${key_index + 1}</td>
                    <td>${key}</td>
                    <td><#if properties[key]??>${properties[key]}</#if></td>
                </tr>
            </#list>
        </table>
    </#if>
    <#if env?? && env?size!=0>
        <p>

        <h3>System Environment:</h3></p>
        <table class="bordered-table">
            <tr>
                <th>Index</th>
                <th>Name</th>
                <th>Value</th>
            </tr>
            <#list env?keys as key>
                <#if key_index % 2 == 0>
                    <#assign rowClass="name-sublevel1-even"/>
                <#else>
                    <#assign rowClass="name-sublevel1-odd"/>
                </#if>
                <tr class="${rowClass}">
                    <td>${key_index + 1}</td>
                    <td>${key}</td>
                    <td><#if env[key]??>${env[key]}</#if></td>
                </tr>
            </#list>
        </table>
    </#if>
</div>
</#escape>
