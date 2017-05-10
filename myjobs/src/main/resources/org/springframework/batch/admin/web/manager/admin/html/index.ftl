<#import "/spring.ftl" as spring />
<div id="login" style="margin: auto;">
    <form action="/login" method="post">
        <ol>
            <li><label for="username">User Name</label><input id="username" name="username" type="text"></li>
            <li><label for="password">Password</label><input id="password" name="password" type="password"></li>
            <li><input type="submit" value="Login"></li>
        </ol>
    </form>
</div>
