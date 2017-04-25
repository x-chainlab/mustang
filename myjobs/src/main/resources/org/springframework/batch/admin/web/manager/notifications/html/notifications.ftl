<#import "/spring.ftl" as spring />
<div id="notifications">
	
	<#if notifications?? && notifications?size!=0>
		
			<h2>Job Notifications</h2>
	
			<table title="Notification Details" class="bordered-table">
				<tr>
					<th>Name</th>
					<th>Parameters</th>
				</tr>
				<#list notifications as notification>
					<#if notification_index % 2 == 0>
						<#assign rowClass="name-sublevel1-even"/>
					<#else>
						<#assign rowClass="name-sublevel1-odd"/>
					</#if>
					<tr class="${rowClass}">
						<td>${notification.name}</td>
						<td>${notification.paras}</td>
					</tr>
				</#list>
			</table>
			<ul class="controlLinks">
				<li>Rows: ${startNotification}-${endNotification} of ${totalNotifications}</li>
                <#assign notifications_url><@spring.url relativeUrl="${servletPath}/notifications"/></#assign>
                <#if next??><li><a href="${notifications_url}?start=${next?c}&pageSize=${pageSize!20}">Next</a></li></#if>
                <#if previous??><li><a href="${notifications_url}?start=${previous?c}&pageSize=${pageSize!20}">Previous</a></li></#if>
				<li>Page Size: ${pageSize!20}</li>
			</ul>

	<#else>
		<p>There are no notifications.</p>
	</#if>

</div><!-- jobs -->
