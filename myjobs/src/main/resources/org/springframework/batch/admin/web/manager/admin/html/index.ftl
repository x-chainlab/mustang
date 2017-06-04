<#import "/spring.ftl" as spring />
<div id="login" style="margin: auto;">
    <form action="/login" method="post">
        <ol>
        <#if error??>
            <li><label for="msg">Message</label><span id="msg"><font color="red">${error}</font></span></li></#if>
            <li><label for="username">User Name</label><input id="username" name="username" type="text"></li>
            <li><label for="password">Password</label><input id="password" name="password" type="password"></li>
            <#--<li><a href="#">register</a></li>-->
            <li><input type="submit" value="Login"></li>
        </ol>
    </form>
</div>
