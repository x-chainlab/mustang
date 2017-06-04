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
    <h2>Update Password </h2>
    <form action="/updateUser" method="post">
        <ol>
            <#if error??>
                <li><label for="msg">Message</label><span id="msg"><font color="red">${error}</font></span></li></#if>
            <li><label for="username">User Name</label><input  readonly="readonly" id="username" name="username" type="text" value="${username}"></li>
            <li><label for="password">Password</label><input id="password" name="password" type="text" value="${password}"></li>
            <li><input type="submit" value="Change Password"></li>
        </ol>
    </form>
    <h2>Add User </h2>
    <form action="/addUser" method="post">
        <ol>
            <#if error??>
                <li><label for="msg">Message</label><span id="msg"><font color="red">${error}</font></span></li></#if>
            <li><label for="username">User Name</label><input id="username" name="username" type="text" ></li>
            <li><label for="password">Password</label><input id="password" name="password" type="text"></li>
            <li><label for="authority">Authority<select id="authority" name="authority"><#list roles as role ><option value="${role}">${role}</option></#list></select></li>
           <li><input type="submit" value="Add User"></li>
        </ol>
    </form>
    <#if users?? && users?size!=0>

        <h2>Update Authority </h2>

        <table title="User List" class="bordered-table">
            <tr>
                <th>Name</th>
                <th>Roles</th>
                <th>Operation</th>
            </tr>
            <#list users as user>
                <#if user_index % 2 == 0>
                    <#assign rowClass="name-sublevel1-even"/>
                <#else>
                    <#assign rowClass="name-sublevel1-odd"/>
                </#if>
                <tr class="${rowClass}">
                    <#assign updateUser_url><@spring.url relativeUrl="${servletPath}/updateOthersBefore/${user.username}/"/></#assign>
                <#--<#assign history_url><@spring.url relativeUrl="${servletPath}/history/${job.jobName}/"/></#assign>-->
                <#--<td><a href="${job_url}">${job.jobName}</a></td>-->
                    <td>${user.username}</td>
                    <td>${user.roles}</td>
                    <#if user.username==username>
                        <td>UpdateAuthority</td>
                    <#else>
                        <td><a href="${updateUser_url}">UpdateAuthority</a></td>
                    </#if>
                </tr>
            </#list>
        </table>
    </#if>
</div>
</#escape>
