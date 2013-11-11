-- links
DELETE FROM links;
<#list links as link>
INSERT INTO links (station_name, ftn_address, pkt_password, host, port, password)
VALUES ('${link.stationName}', '${link.ftnAddress}', '${link.pktPassword}', '${link.host}', ${link.port}, '${link.password}');
</#list>