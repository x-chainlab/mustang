<#import "/spring.ftl" as spring />
<div id="update" style="margin: auto;">
    <form action="/updateUserDetail" method="post">
        <ol>
            <li><label for="username">User Name</label><input  readonly="readonly" id="username" name="username" type="text" value="${user.username}"></li>
            <li><label for="password">User Password</label><input id="password" name="password" type="password" value="${user.password}"></li>
            <li><label for="authority">Authority</label><select id="authority" name="authority"><#list roles as role >
                <#if user.roles==role> <option selected="selected" value="${role}">${role}</option>
                <#else>
                    <option  value="${role}">${role}</option>
                </#if>
               </#list></select></li>
            <li><input type="submit" value="Submit"></li>
        </ol>
    </form>
</div>
