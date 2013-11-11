-- routing
INSERT INTO routing(nice, route_via) SELECT 65535,id FROM links WHERE ftn_address='${mainuplink}';