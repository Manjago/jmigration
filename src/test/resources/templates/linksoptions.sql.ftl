-- linkoptions
<#list links as link>
<#if !link.point>
INSERT INTO linkoptions (link_id, name, value)
VALUES(SELECT ID FROM links WHERE ftn_address = '${link.ftnAddress}' , 'areaautocreate', 'TRUE');
</#if>
</#list>