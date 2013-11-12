-- echoareas
<#list areas as s>
INSERT INTO ECHOAREA (NAME, DESCRIPTION, WLEVEL, RLEVEL,GRP) values('${s.name}', '${s.desc}',0, 0,'');
</#list>