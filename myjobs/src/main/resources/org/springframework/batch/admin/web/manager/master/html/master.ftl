<#import "/spring.ftl" as spring />
<#escape x as x?html>
<h2>Cluster Master</h2>
<div id="master">
    <#if master??>
        <p/>
        <ol>
            <#assign executor_url><@spring.url relativeUrl="${servletPath}/executor/${master.id}/"/></#assign>
            <li><label for="master.id">Master ID</label><span id="master.id"><a href="${executor_url}">${master.id}</a></span></li>
        </ol>
        <br/>
    <#else>
        <p>There is no master registered.</p>
    </#if>
</div>
</#escape>
