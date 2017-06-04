<#import "/spring.ftl" as spring />
<div id="update" style="margin: auto;">
    <form action="/updateAuthority" method="post">
        <ol>
        <#if error??>
            <li><label for="msg">Message</label><span id="msg"><font color="red">${error}</font></span></li></#if>
            <li><label for="username">User Name</label><input  readonly="readonly" id="username" name="username" type="text" value="${user.username}"></li>
            <li><label for="password">Password</label><input readonly="readonly" id="password" name="password" type="text" value="${user.password}"></li>
            <li><label for="authority">Authority<select id="authority" name="authority"><#list roles as role >
                <#if user.roles==role> <option selected="selected" value="${role}">${role}</option>
                <#else>
                    <option  value="${role}">${role}</option>
                </#if>
               </#list></select></li>
            <li><input type="submit" value="submit"></li>
        </ol>
    </form>
</div>
