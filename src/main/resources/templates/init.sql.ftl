-- links
<#list links as link>
INSERT INTO links (station_name, ftn_address, pkt_password, host, port, password)
VALUES ('${link.stationName}', '${link.ftnAddress}', '${link.pktPassword}', '${link.host}', ${link.port}, '${link.password}');
</#list>

-- echoareas
<#list areas as s>
INSERT INTO ECHOAREA (NAME, DESCRIPTION, WLEVEL, RLEVEL,GRP) values('${s.name}', '${s.desc}',0, 0,'');
</#list>

-- fileareas
<#list fileareas as f>
INSERT INTO FILEAREA (NAME, DESCRIPTION, WLEVEL, RLEVEL,GRP) values('${f.name}', '${f.desc}',0, 0,'');
</#list>

-- linkoptions
<#list links as link>
    <#if !link.point>
    INSERT INTO linkoptions (link_id, name, value)
    VALUES(SELECT ID FROM links WHERE ftn_address = '${link.ftnAddress}' , 'areaautocreate', 'TRUE');
    </#if>
</#list>

-- subscription
<#list subscr as st>
INSERT INTO SUBSCRIPTION (ECHOAREA_ID , LINK_ID) VALUES (SELECT ID FROM ECHOAREA WHERE NAME = '${st.area}', SELECT ID FROM LINKS WHERE FTN_ADDRESS = '${st.node}');
</#list>

-- filesubscription
<#list filesubscr as fst>
INSERT INTO FILESUBSCRIPTION (FILEAREA_ID , LINK_ID) VALUES (SELECT ID FROM FILEAREA WHERE NAME = '${fst.area}', SELECT ID FROM LINKS WHERE FTN_ADDRESS = '${fst.node}');
</#list>

-- routing
INSERT INTO routing(nice, route_via) SELECT 65535,id FROM links WHERE ftn_address='${mainuplink}';

-- robots
INSERT INTO robots VALUES('areafix', 'jnode.robot.AreaFix');
INSERT INTO robots VALUES('filefix', 'jnode.robot.FileFix');