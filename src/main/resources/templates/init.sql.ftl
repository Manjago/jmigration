DROP ALL OBJECTS;

-- links
DELETE FROM links;
<#list links as link>
INSERT INTO links (station_name, ftn_address, pkt_password, host, port, password)
VALUES ('${link.stationName}', '${link.ftnAddress}', '${link.pktPassword}', '${link.host}', ${link.port}, '${link.password}');
</#list>

-- linkoptions
<#list links as link>
    <#if !link.point>
    INSERT INTO linkoptions (link_id, name, value)
    VALUES(SELECT ID FROM links WHERE ftn_address = '${link.ftnAddress}' , 'areaautocreate', 'TRUE');
    </#if>
</#list>