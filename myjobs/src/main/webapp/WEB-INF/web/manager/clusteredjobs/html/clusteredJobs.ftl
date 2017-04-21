<#import "/spring.ftl" as spring />
<div id="jobs">
	
	<#if jobs?? && jobs?size!=0>
		
			<h2>Clustered Job Names Registered</h2>
	
			<table title="Clustered Job Names" class="bordered-table">
				<tr>
					<th>Name</th>
					<th>Executors</th>
					<th>Executions</th>
					<th>Instance&nbsp;Limit</th>
					<th>Cron&nbsp;Expression</th>
					<th>Parameters</th>
				</tr>
				<#list jobs as job>
					<#if job_index % 2 == 0>
						<#assign rowClass="name-sublevel1-even"/>
					<#else>
						<#assign rowClass="name-sublevel1-odd"/>
					</#if>
					<tr class="${rowClass}">
						<#assign job_url><@spring.url relativeUrl="${servletPath}/clusteredjob/${job.jobName}/"/></#assign>
						<td><a href="${job_url}">${job.jobName}</a></td>
						<td>${job.executors}</td>
						<td>${job.executions}</td>
						<td>${job.maxInstances}</td>
						<td>${job.cron}</td>
						<td>${job.paras}</td>
					</tr>
				</#list>
			</table>
			<ul class="controlLinks">
				<li>Rows: ${startJob}-${endJob} of ${totalJobs}</li>
				<#assign job_url><@spring.url relativeUrl="${servletPath}/clusteredjobs"/></#assign>
				<#if next??><li><a href="${job_url}?startJob=${next?c}&pageSize=${pageSize!20}">Next</a></li></#if>
				<#if previous??><li><a href="${job_url}?startJob=${previous?c}&pageSize=${pageSize!20}">Previous</a></li></#if>
				<!-- TODO: enable pageSize editing -->
				<li>Page Size: ${pageSize!20}</li>
			</ul>

	<#else>
		<p>There are no jobs registered.</p>
	</#if>

</div><!-- jobs -->
