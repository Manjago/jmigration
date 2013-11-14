INSERT INTO SCRIPTHELPERS ( HELPER , CLASSNAME) VALUES ('writeFileToEchoareaHelper', 'jnode.jscript.WriteFileToEchoareaHelper');
INSERT INTO JSCRIPTS (ID, CONTENT) VALUES (1, 'writeFileToEchoareaHelper.writeFileToEchoarea("828.test","русский subject","/opt/jnode/rules/828.test.rules.txt");');
INSERT INTO SCHEDULES (DETAILS, JSCRIPT_ID, TYPE) VALUES (0, 1, 'DAILY');