-- linkoptions
<#list links as link>
INSERT INTO linkoptions (link_id, name, value)
VALUES(SELECT ID FROM links WHERE ftn_address = '${link.ftnAddress}' , 'areaautocreate', 'TRUE');
</#list>