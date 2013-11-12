-- links
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

-- routing
INSERT INTO routing(nice, route_via) SELECT 65535,id FROM links WHERE ftn_address='${mainuplink}';

-- robots
DELETE FROM robots;
INSERT INTO robots VALUES('areafix', 'jnode.robot.AreaFix');
INSERT INTO robots VALUES('filefix', 'jnode.robot.FileFix');