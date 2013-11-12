-- routing
INSERT INTO routing(nice, route_via) SELECT 65535,id FROM links WHERE ftn_address='${mainuplink}';
-- robots
INSERT INTO robots VALUES('areafix', 'jnode.robot.AreaFix');
INSERT INTO robots VALUES('filefix', 'jnode.robot.FileFix');