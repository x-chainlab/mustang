<#import "/spring.ftl" as spring />
<div id="history">
	
	<#if histories?? && histories?size!=0>
		
			<h2>Job Execution History:${jobName}</h2>
	
			<table title="Job Execution History" class="bordered-table">
				<tr>
                    <th>Executor</th>
					<th>Start</th>
					<th>End</th>
					<th>Status</th>
					<th>Exit&nbsp;Status</th>
				</tr>
				<#list histories as history>
					<#if history_index % 2 == 0>
						<#assign rowClass="name-sublevel1-even"/>
					<#else>
						<#assign rowClass="name-sublevel1-odd"/>
					</#if>
					<tr class="${rowClass}">
                        <td><#if history.executorId??>${history.executorId}</#if></td>
						<td>${history.start?string("yyyy-MM-dd HH:mm:ss")}</td>
                        <td>${history.end?string("yyyy-MM-dd HH:mm:ss")}</td>
                        <td>${history.status.name()!}</td>
                        <td><#if history.exitStatus??>${history.exitStatus.getExitCode()!}</#if></td>
					</tr>
				</#list>
			</table>
			<ul class="controlLinks">
				<li>Rows: ${startHistory}-${endHistory} of ${totalHistories}</li>
                <#assign history_url><@spring.url relativeUrl="${servletPath}/history/${jobName}/"/></#assign>
                <#if next??><li><a href="${history_url}?start=${next?c}&pageSize=${pageSize!20}">Next</a></li></#if>
                <#if previous??><li><a href="${history_url}?start=${previous?c}&pageSize=${pageSize!20}">Previous</a></li></#if>
				<li>Page Size: ${pageSize!20}</li>
			</ul>

	<#else>
		<p>There are no executions.</p>
	</#if>

</div>
