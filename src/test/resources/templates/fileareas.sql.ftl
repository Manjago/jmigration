-- fileareas
<#list fileareas as f>
INSERT INTO FILEAREA (NAME, DESCRIPTION, WLEVEL, RLEVEL,GRP) values('${f.name}', '${f.desc}',0, 0,'');
</#list>