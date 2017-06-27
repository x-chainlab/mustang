<#import "/spring.ftl" as spring />
<#escape x as x?html>
<h2>Cluster Master</h2>
<div id="master">
    <#if message??>
        <p><font color="red">${message}</font></p>
    </#if>
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
    <h2>Update My Password </h2>
    <form action="/updateUserPassword" method="post">
        <ol>
            <li><label for="username">User Name</label><input  readonly="readonly" id="username" name="username" type="text" value="${username}"></li>
            <li><label for="password">New Password</label><input id="password" name="password" type="password" value=""></li>
            <li><input type="submit" value="Update My Password"></li>
        </ol>
    </form>
    <#if isClusterSupper>
    <h2>Add User </h2>
    <form action="/addUser" method="post">
        <ol>
            <li><label for="username">User Name</label><input id="username" name="username" type="text" ></li>
            <li><label for="password">User Password</label><input id="password" name="password" type="password"></li>
            <li><label for="authority">Authority</label><select id="authority" name="authority"><#list roles as role ><option value="${role}">${role}</option></#list></select></li>
           <li><input type="submit" value="Add User"></li>
        </ol>
    </form>
    </#if>
    <#if isClusterSupper && users?? && users?size!=0>

        <h2>User List</h2>

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
                    <#assign updateUser_url><@spring.url relativeUrl="${servletPath}/userDetail/${user.username}/"/></#assign>
                    <#assign deleteUser_url><@spring.url relativeUrl="${servletPath}/deleteUser/${user.username}/"/></#assign>
                    <td>${user.username}</td>
                    <td>${user.roles}</td>
                    <#if user.username==username>
                        <td></td>
                    <#else>
                        <td><a href="${updateUser_url}">Edit</a>&nbsp;<a href="${deleteUser_url}">Delete</a></td>
                    </#if>
                </tr>
            </#list>
        </table>
    </#if>
</div>
</#escape>
