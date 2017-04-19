<#import "/spring.ftl" as spring />
<div id="executors">
	
	<#if executors?? && executors?size!=0>
		
			<h2>Clustered Executors Registered</h2>
	
			<table title="Executor Names" class="bordered-table">
				<tr>
					<th>ID</th>
				</tr>
				<#list executors as executor>
					<#if executor_index % 2 == 0>
						<#assign rowClass="name-sublevel1-even"/>
					<#else>
						<#assign rowClass="name-sublevel1-odd"/>
					</#if>
					<tr class="${rowClass}">
						<td>${executor.id}</td>
					</tr>
				</#list>
			</table>
			<ul class="controlLinks">
				<li>Rows: ${startExecutor}-${endExecutor} of ${totalExecutors}</li>
				<#assign executors_url><@spring.url relativeUrl="${servletPath}/executors"/></#assign>
				<#if next??><li><a href="${executors_url}?startJob=${next?c}&pageSize=${pageSize!20}">Next</a></li></#if>
				<#if previous??><li><a href="${executors_url}?startJob=${previous?c}&pageSize=${pageSize!20}">Previous</a></li></#if>
				<li>Page Size: ${pageSize!20}</li>
			</ul>

	<#else>
		<p>There are no executors registered.</p>
	</#if>

</div><!-- jobs -->
