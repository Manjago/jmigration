-- filesubscription
<#list filesubscr as fst>
INSERT INTO FILESUBSCRIPTION (FILEAREA_ID , LINK_ID) VALUES (SELECT ID FROM FILEAREA WHERE NAME = '${fst.area}', SELECT ID FROM LINKS WHERE FTN_ADDRESS = '${fst.node}');
</#list>