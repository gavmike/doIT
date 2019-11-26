<html>
<head>
    <title>Home</title>
</head>
<body>
<#if users?has_content>
<ul>
    <#list users as user>
    <li>${user.surname}</li>
    </#list>
</ul>
<#else >
<p>users not have yet</p>
</#if>
</body>
</html>