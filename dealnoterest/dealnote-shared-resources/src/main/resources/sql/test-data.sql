DELETE FROM AGENTS;
COMMIT WORK;

INSERT INTO AGENTS (ID, ISACTIVE, SNAME, SUPERVISORID, ROLECODE, USERID, OUTERID, MYCLIENTUID, REGNUMNEXT1, REGNUMNEXT2, VSANDPS, AUTODISCOUNT, STRICTSTOPSHIP, REGNUMPREFIX1, REGNUMPREFIX2, FIO, EMAIL, IMPORTDATETIME, UPDATEDATETIME, ADMIN_PASS, MONEYNAME, MONEYFORMAT, QTYFORMAT, DAYDELDOC, ISGPS, FREQUENCYGETGPS, FREQUENCYSENDGPS, GPSFROMHOUR, GPSTOHOUR, GPSBYDAY, ISAPPSTAT, ISGPSBEFOREORDER)
            VALUES (444, 1, '370 Додич', NULL, 2, NULL, 581, 9, 10000, 10000, 1, 1, 0, 'W', 'A', 'Додичь', 'dodich@ukr.net', '2015-03-25 00:00:00', '2015-03-25 12:52:46', '9YnRQmI2/VyEuu/fRaWMRA==', 'грн.', '##0.##', '##0.##', 10, 1, 10, 60, 9, 18, '1,1,1,1,1,0,0', 0, 0);
INSERT INTO AGENTS (ID, ISACTIVE, SNAME, SUPERVISORID, ROLECODE, USERID, OUTERID, MYCLIENTUID, REGNUMNEXT1, REGNUMNEXT2, VSANDPS, AUTODISCOUNT, STRICTSTOPSHIP, REGNUMPREFIX1, REGNUMPREFIX2, FIO, EMAIL, IMPORTDATETIME, UPDATEDATETIME, ADMIN_PASS, MONEYNAME, MONEYFORMAT, QTYFORMAT, DAYDELDOC, ISGPS, FREQUENCYGETGPS, FREQUENCYSENDGPS, GPSFROMHOUR, GPSTOHOUR, GPSBYDAY, ISAPPSTAT, ISGPSBEFOREORDER)
            VALUES (495, 1, '32_Кульчицький', NULL, 2, NULL, 3060, 9, 10000, 10000, 1, 1, 0, 'W', 'A', 'Додичь', 'dodich@ukr.net', '2015-03-25 00:00:00', '2015-03-25 12:52:46', '9YnRQmI2/VyEuu/fRaWMRA==', 'грн.', '##0.##', '##0.##', 10, 1, 10, 60, 9, 18, '1,1,1,1,1,0,0', 0, 0);
INSERT INTO AGENTS (ID, ISACTIVE, SNAME, SUPERVISORID, ROLECODE, USERID, OUTERID, MYCLIENTUID, REGNUMNEXT1, REGNUMNEXT2, VSANDPS, AUTODISCOUNT, STRICTSTOPSHIP, REGNUMPREFIX1, REGNUMPREFIX2, FIO, EMAIL, IMPORTDATETIME, UPDATEDATETIME, ADMIN_PASS, MONEYNAME, MONEYFORMAT, QTYFORMAT, DAYDELDOC, ISGPS, FREQUENCYGETGPS, FREQUENCYSENDGPS, GPSFROMHOUR, GPSTOHOUR, GPSBYDAY, ISAPPSTAT, ISGPSBEFOREORDER)
            VALUES (496, 0, '33_Клочко', NULL, 2, NULL, 3080, 9, 10000, 10000, 1, 1, 0, 'W', 'A', 'Додичь', 'dodich@ukr.net', '2015-03-25 00:00:00', '2015-03-25 12:52:46', '9YnRQmI2/VyEuu/fRaWMRA==', 'грн.', '##0.##', '##0.##', 10, 1, 10, 60, 9, 18, '1,1,1,1,1,0,0', 0, 0);
            
COMMIT WORK;

DELETE FROM CLIENTGROUP;
COMMIT WORK;

INSERT INTO CLIENTGROUP (ID, SNAME, OUTERID)
                 VALUES (-1, 'All', NULL);
INSERT INTO CLIENTGROUP (ID, SNAME, OUTERID)
                 VALUES (3, 'new', NULL);
INSERT INTO CLIENTGROUP (ID, SNAME, OUTERID)
                 VALUES (0, 'Base group', NULL);

COMMIT WORK;

DELETE FROM ROUTES;
COMMIT WORK;

INSERT INTO ROUTES (ID, SNAME, OUTERID)
            VALUES (81, 'Route1', 1);
INSERT INTO ROUTES (ID, SNAME, OUTERID)
            VALUES (82, 'Route2', 2);
INSERT INTO ROUTES (ID, SNAME, OUTERID)
            VALUES (-1, 'All', NULL);
INSERT INTO ROUTES (ID, SNAME, OUTERID)
            VALUES (0, 'No data', NULL);

COMMIT WORK;

DELETE FROM CLIENT;
COMMIT WORK;

INSERT INTO CLIENT (ID, AGENTID, PARENTID, NAME, DEBTSUMM1, DEBTDAYS1, DEBTSUMM2, DEBTDAYS2, STOPSHIPMENT, ROUTEID, DEFAULTDISCOUNT, NOACTIVE, ADDRESSLOCATION, PHONE, TAXCODE, TAXNUM, OKPO, MFO, BANKNAME, BANKACCOUNT, DOGNUM, FNAME, ADDRESSLAW, CLIENTTYPEID, LONGITUDE, LATITUDE, OUTERID)
            VALUES (2, 444, 0, 'Client2', 0, 0, 0, 0, 1, 0, 0, 1, 'ADDRESSLOCATION2', '050-381-25-17', '', '', '', '', '', '', '', '', '', 0, 123, 5666, 5465);
INSERT INTO CLIENT (ID, AGENTID, PARENTID, NAME, DEBTSUMM1, DEBTDAYS1, DEBTSUMM2, DEBTDAYS2, STOPSHIPMENT, ROUTEID, DEFAULTDISCOUNT, NOACTIVE, ADDRESSLOCATION, PHONE, TAXCODE, TAXNUM, OKPO, MFO, BANKNAME, BANKACCOUNT, DOGNUM, FNAME, ADDRESSLAW, CLIENTTYPEID, LONGITUDE, LATITUDE, OUTERID)
            VALUES (3, 444, 0, 'Клиент3', 0, 0, 0, 0, 0, 0, 0, 0, 'ADDRESSLOCATION3', '044-462-99-99', '', '', '', '', '', '', '', '', '', 0, 0, 0, 10865);
INSERT INTO CLIENT (ID, AGENTID, PARENTID, NAME, DEBTSUMM1, DEBTDAYS1, DEBTSUMM2, DEBTDAYS2, STOPSHIPMENT, ROUTEID, DEFAULTDISCOUNT, NOACTIVE, ADDRESSLOCATION, PHONE, TAXCODE, TAXNUM, OKPO, MFO, BANKNAME, BANKACCOUNT, DOGNUM, FNAME, ADDRESSLAW, CLIENTTYPEID, LONGITUDE, LATITUDE, OUTERID)
            VALUES (4, 444, 0, 'Клиент4', 0, 0, 0, 0, 0, 0, 0, 0, 'ADDRESSLOCATION4', '044-238-45-90', '', '', '', '', '', '', '', '', '', 0, 0, 0, 15198);
INSERT INTO CLIENT (ID, AGENTID, PARENTID, NAME, DEBTSUMM1, DEBTDAYS1, DEBTSUMM2, DEBTDAYS2, STOPSHIPMENT, ROUTEID, DEFAULTDISCOUNT, NOACTIVE, ADDRESSLOCATION, PHONE, TAXCODE, TAXNUM, OKPO, MFO, BANKNAME, BANKACCOUNT, DOGNUM, FNAME, ADDRESSLAW, CLIENTTYPEID, LONGITUDE, LATITUDE, OUTERID)
            VALUES (5, 444, 0, 'Клиент5', 0, 0, 0, 0, 0, 0, 0, 0, 'ADDRESSLOCATION5', '044-200-13-56', '', '', '', '', '', '', '', '', '', 0, 0, 0, 15221);
INSERT INTO CLIENT (ID, AGENTID, PARENTID, NAME, DEBTSUMM1, DEBTDAYS1, DEBTSUMM2, DEBTDAYS2, STOPSHIPMENT, ROUTEID, DEFAULTDISCOUNT, NOACTIVE, ADDRESSLOCATION, PHONE, TAXCODE, TAXNUM, OKPO, MFO, BANKNAME, BANKACCOUNT, DOGNUM, FNAME, ADDRESSLAW, CLIENTTYPEID, LONGITUDE, LATITUDE, OUTERID)
            VALUES (6, 444, 0, 'Клиент6', 0, 0, 0, 0, 0, 0, 0, 0, 'ADDRESSLOCATION6', '044-200-13-56', '', '', '', '', '', '', '', '', '', 0, 0, 0, 15222);
INSERT INTO CLIENT (ID, AGENTID, PARENTID, NAME, DEBTSUMM1, DEBTDAYS1, DEBTSUMM2, DEBTDAYS2, STOPSHIPMENT, ROUTEID, DEFAULTDISCOUNT, NOACTIVE, ADDRESSLOCATION, PHONE, TAXCODE, TAXNUM, OKPO, MFO, BANKNAME, BANKACCOUNT, DOGNUM, FNAME, ADDRESSLAW, CLIENTTYPEID, LONGITUDE, LATITUDE, OUTERID)
            VALUES (7, 444, 0, 'Клиент7', 0, 0, 0, 0, 0, 0, 0, 0, 'ADDRESSLOCATION7', '044-259-38-97', '', '', '', '', '', '', '', '', '', 0, 0, 0, 15230);
INSERT INTO CLIENT (ID, AGENTID, PARENTID, NAME, DEBTSUMM1, DEBTDAYS1, DEBTSUMM2, DEBTDAYS2, STOPSHIPMENT, ROUTEID, DEFAULTDISCOUNT, NOACTIVE, ADDRESSLOCATION, PHONE, TAXCODE, TAXNUM, OKPO, MFO, BANKNAME, BANKACCOUNT, DOGNUM, FNAME, ADDRESSLAW, CLIENTTYPEID, LONGITUDE, LATITUDE, OUTERID)
            VALUES (8, 444, 0, 'Клиент8', 0, 0, 0, 0, 0, 0, 0, 0, 'ADDRESSLOCATION8', '045-5-43-09,050', '', '', '', '', '', '', '', '', '', 0, 31.781601, 50.238049, 15250);
INSERT INTO CLIENT (ID, AGENTID, PARENTID, NAME, DEBTSUMM1, DEBTDAYS1, DEBTSUMM2, DEBTDAYS2, STOPSHIPMENT, ROUTEID, DEFAULTDISCOUNT, NOACTIVE, ADDRESSLOCATION, PHONE, TAXCODE, TAXNUM, OKPO, MFO, BANKNAME, BANKACCOUNT, DOGNUM, FNAME, ADDRESSLAW, CLIENTTYPEID, LONGITUDE, LATITUDE, OUTERID)
            VALUES (9, 444, 0, 'Клиент9', 0, 0, 0, 0, 0, 0, 0, 0, 'ADDRESSLOCATION9', '04594-3-44-38', '', '', '', '', '', '', '', '', '', 0, 0, 0, 15257);
INSERT INTO CLIENT (ID, AGENTID, PARENTID, NAME, DEBTSUMM1, DEBTDAYS1, DEBTSUMM2, DEBTDAYS2, STOPSHIPMENT, ROUTEID, DEFAULTDISCOUNT, NOACTIVE, ADDRESSLOCATION, PHONE, TAXCODE, TAXNUM, OKPO, MFO, BANKNAME, BANKACCOUNT, DOGNUM, FNAME, ADDRESSLAW, CLIENTTYPEID, LONGITUDE, LATITUDE, OUTERID)
            VALUES (10, 444, 0, 'Клиент10', 0, 0, 0, 0, 0, 0, 0, 0, 'ADDRESSLOCATION10', '067-231-22-65', '', '', '', '', '', '', '', '', '', 0, 0, 0, 15271);
INSERT INTO CLIENT (ID, AGENTID, PARENTID, NAME, DEBTSUMM1, DEBTDAYS1, DEBTSUMM2, DEBTDAYS2, STOPSHIPMENT, ROUTEID, DEFAULTDISCOUNT, NOACTIVE, ADDRESSLOCATION, PHONE, TAXCODE, TAXNUM, OKPO, MFO, BANKNAME, BANKACCOUNT, DOGNUM, FNAME, ADDRESSLAW, CLIENTTYPEID, LONGITUDE, LATITUDE, OUTERID)
            VALUES (11, 444, 0, 'Клиент11', 0, 0, 0, 0, 0, 0, 0, 0, 'ADDRESSLOCATION11', '050-443-53-22', '', '', '', '', '', '', '', '', '', 0, 0, 0, 15323);

INSERT INTO MEASURES (ID, OUTERID, NAME)
              VALUES (1, 44443, 'бл');
INSERT INTO MEASURES (ID, OUTERID, NAME)
              VALUES (2, 8, 'л');
INSERT INTO MEASURES (ID, OUTERID, NAME)
              VALUES (3, 1, 'шт');
INSERT INTO MEASURES (ID, OUTERID, NAME)
              VALUES (4, 2, 'ящ');
INSERT INTO MEASURES (ID, OUTERID, NAME)
              VALUES (5, 10, 'SSU');
INSERT INTO MEASURES (ID, OUTERID, NAME)
              VALUES (6, 14, 'брутто кг');
INSERT INTO MEASURES (ID, OUTERID, NAME)
              VALUES (7, 11, 'бут.');
INSERT INTO MEASURES (ID, OUTERID, NAME)
              VALUES (8, 0, 'г');
INSERT INTO MEASURES (ID, OUTERID, NAME)
              VALUES (9, 12, 'далл');
INSERT INTO MEASURES (ID, OUTERID, NAME)
              VALUES (10, 6, 'з/уп');
INSERT INTO MEASURES (ID, OUTERID, NAME)
              VALUES (11, 7, 'кг');
INSERT INTO MEASURES (ID, OUTERID, NAME)
              VALUES (12, 13, 'кега');
INSERT INTO MEASURES (ID, OUTERID, NAME)
              VALUES (13, 9, 'объем м3');
INSERT INTO MEASURES (ID, OUTERID, NAME)
              VALUES (14, 5, 'пач');
INSERT INTO MEASURES (ID, OUTERID, NAME)
              VALUES (15, 3, 'подд');

INSERT INTO GOODSGROUP (ID, PARENTID, NAME, OUTERID)
                VALUES (2, 34, 'GoodsGroup1', 10);
INSERT INTO GOODSGROUP (ID, PARENTID, NAME, OUTERID)
                VALUES (3, 0, 'GoodsGroup2', 30);
INSERT INTO GOODSGROUP (ID, PARENTID, NAME, OUTERID)
                VALUES (4, 0, 'GoodsGroup3', 60);

INSERT INTO PRIORITETCOLOR (ID, COLORCODE, DISCRIPTION)
                    VALUES (0, '#ffffff', 'Default');
INSERT INTO PRIORITETCOLOR (ID, COLORCODE, DISCRIPTION)
                    VALUES (2, '#E6804D', 'ORANGE');
INSERT INTO PRIORITETCOLOR (ID, COLORCODE, DISCRIPTION)
                    VALUES (9, '#FFFF4D', 'YELLOW');
INSERT INTO PRIORITETCOLOR (ID, COLORCODE, DISCRIPTION)
                    VALUES (10, '#0000FF', 'BLUE');
INSERT INTO PRIORITETCOLOR (ID, COLORCODE, DISCRIPTION)
                    VALUES (11, '#009933', 'GREEN');
INSERT INTO PRIORITETCOLOR (ID, COLORCODE, DISCRIPTION)
                    VALUES (12, '#CC0000', 'RED');
INSERT INTO PRIORITETCOLOR (ID, COLORCODE, DISCRIPTION)
                    VALUES (15, '#669966', 'LIGHT.GREEN');
INSERT INTO PRIORITETCOLOR (ID, COLORCODE, DISCRIPTION)
                    VALUES (16, '#000000', 'BLACK');
INSERT INTO PRIORITETCOLOR (ID, COLORCODE, DISCRIPTION)
                    VALUES (22, '#f37c02', 'New color');
                    
INSERT INTO GOODS (ID, CATEGORYID, SORTPOS, VATCOEF, NAME, SERTIFICAT, FULLNAME, WEIGHT, ISACTIVE, PRIORITETCOLORID, OUTERID, MEASUREID)
           VALUES (2, 3, 1, 1, 'Colgate Total 12 Prof.14ml', NULL, 'Colgate Total 12 Professional 14ml', 0.014, NULL, 11, 10, 3);
INSERT INTO GOODS (ID, CATEGORYID, SORTPOS, VATCOEF, NAME, SERTIFICAT, FULLNAME, WEIGHT, ISACTIVE, PRIORITETCOLORID, OUTERID, MEASUREID)
           VALUES (3, 3, 2, 1, 'Goods1', NULL, 'Goods1', 0.42, 1, 11, 100, 1);
INSERT INTO GOODS (ID, CATEGORYID, SORTPOS, VATCOEF, NAME, SERTIFICAT, FULLNAME, WEIGHT, ISACTIVE, PRIORITETCOLORID, OUTERID, MEASUREID)
           VALUES (4, 3, 3, 1, 'Goods2', NULL, 'Goods2', 0.42, 1, 11, 106, 1);
INSERT INTO GOODS (ID, CATEGORYID, SORTPOS, VATCOEF, NAME, SERTIFICAT, FULLNAME, WEIGHT, ISACTIVE, PRIORITETCOLORID, OUTERID, MEASUREID)
           VALUES (5, 3, 4, 1, 'Goods3', '', 'Goods3', 0.26, 0, 11, 111, 1);
INSERT INTO GOODS (ID, CATEGORYID, SORTPOS, VATCOEF, NAME, SERTIFICAT, FULLNAME, WEIGHT, ISACTIVE, PRIORITETCOLORID, OUTERID, MEASUREID)
           VALUES (6, 3, 5, 1, 'Goods4', '', 'Goods4', 0.26, 1, 11, 115, 1);


INSERT INTO MEASURELINK (ID, GOODSID, MEASURESRCID, MEASUREDSTID, SRCVALUE, DSTVALUE)
                 VALUES (2, 2, 3, 8, 1, 14);
INSERT INTO MEASURELINK (ID, GOODSID, MEASURESRCID, MEASUREDSTID, SRCVALUE, DSTVALUE)
                 VALUES (3, 2, 3, 4, 216, 1);
INSERT INTO MEASURELINK (ID, GOODSID, MEASURESRCID, MEASUREDSTID, SRCVALUE, DSTVALUE)
                 VALUES (4, 2, 3, 11, 1, 0.014);
INSERT INTO MEASURELINK (ID, GOODSID, MEASURESRCID, MEASUREDSTID, SRCVALUE, DSTVALUE)
                 VALUES (5, 3, 1, 3, 1, 30);
INSERT INTO MEASURELINK (ID, GOODSID, MEASURESRCID, MEASUREDSTID, SRCVALUE, DSTVALUE)
                 VALUES (6, 3, 1, 4, 20, 1);
INSERT INTO MEASURELINK (ID, GOODSID, MEASURESRCID, MEASUREDSTID, SRCVALUE, DSTVALUE)
                 VALUES (7, 3, 1, 11, 1, 0.42);
INSERT INTO MEASURELINK (ID, GOODSID, MEASURESRCID, MEASUREDSTID, SRCVALUE, DSTVALUE)
                 VALUES (8, 3, 1, 5, 1, 2);
INSERT INTO MEASURELINK (ID, GOODSID, MEASURESRCID, MEASUREDSTID, SRCVALUE, DSTVALUE)
                 VALUES (9, 4, 1, 3, 1, 30);
INSERT INTO MEASURELINK (ID, GOODSID, MEASURESRCID, MEASUREDSTID, SRCVALUE, DSTVALUE)
                 VALUES (10, 4, 1, 4, 20, 1);
INSERT INTO MEASURELINK (ID, GOODSID, MEASURESRCID, MEASUREDSTID, SRCVALUE, DSTVALUE)
                 VALUES (11, 4, 1, 11, 1, 0.42);
INSERT INTO MEASURELINK (ID, GOODSID, MEASURESRCID, MEASUREDSTID, SRCVALUE, DSTVALUE)
                 VALUES (12, 4, 1, 5, 1, 2);

INSERT INTO AGENTSGOODS (GOODSID, AGENTID, PRICE, AVAILABLE, ID)
                 VALUES (2, 444, 1, 1, 8);
INSERT INTO AGENTSGOODS (GOODSID, AGENTID, PRICE, AVAILABLE, ID)
                 VALUES (3, 444, 1, 1, 5);
INSERT INTO AGENTSGOODS (GOODSID, AGENTID, PRICE, AVAILABLE, ID)
                 VALUES (4, 444, 1, 1, 4);
                 
COMMIT WORK;

INSERT INTO PAYFORMS (ID, SNAME, OUTERID)
              VALUES (1, 'form1', NULL);
INSERT INTO PAYFORMS (ID, SNAME, OUTERID)
              VALUES (2, 'form2', NULL);
INSERT INTO PAYFORMS (ID, SNAME, OUTERID)
              VALUES (3, 'form3', NULL);

COMMIT WORK;

INSERT INTO DOCTYPE (ID, PAYFORMID, OUTERID, DAYS, ACCINCRNOVAT, ACCINCRWITHVAT, SNAME, DISCOUNTFIRST, VATOVERSUM)
             VALUES (1, 1, NULL, 30, 2, 2, 'Type1', 1, 1);
INSERT INTO DOCTYPE (ID, PAYFORMID, OUTERID, DAYS, ACCINCRNOVAT, ACCINCRWITHVAT, SNAME, DISCOUNTFIRST, VATOVERSUM)
             VALUES (2, 2, NULL, 30, 2, 2, 'Type2', 1, 1);
INSERT INTO DOCTYPE (ID, PAYFORMID, OUTERID, DAYS, ACCINCRNOVAT, ACCINCRWITHVAT, SNAME, DISCOUNTFIRST, VATOVERSUM)
             VALUES (3, 3, NULL, 30, 2, 2, 'Type3', 1, 1);

COMMIT WORK;

INSERT INTO DOCUMENT (ID, CLIENTID, AGENTID, DOCTYPEID, OUTERCLIENTLINKID, DOCDATE, DISCOUNT, SALETYPE, TERMDATE, SUMWITHOUTVAT, SUMWITHVAT, SENDINGDATE, EXPORTED, DESCRIPT, REGNUM, LONGITUDE, LATITUDE, ITEMCOUNT)
              VALUES (1, 3, 444, 1, 123234, '2015-04-02 00:00:00', 8, 1, '2015-04-30 00:00:00', 123, 167, '2015-04-02 00:00:00', 0, 'Test', 'W123', 34.45678, 23.9999, 23.6754);
INSERT INTO DOCUMENT (ID, CLIENTID, AGENTID, DOCTYPEID, OUTERCLIENTLINKID, DOCDATE, DISCOUNT, SALETYPE, TERMDATE, SUMWITHOUTVAT, SUMWITHVAT, SENDINGDATE, EXPORTED, DESCRIPT, REGNUM, LONGITUDE, LATITUDE, ITEMCOUNT)
              VALUES (2, 4, 444, 1, 123234, '2015-04-02 00:00:00', 8, 1, '2015-04-30 00:00:00', 123, 167, '2015-04-02 00:00:00', 0, 'Test2', 'W124', 34.45678, 23.9999, 23.6754);
INSERT INTO DOCUMENT (ID, CLIENTID, AGENTID, DOCTYPEID, OUTERCLIENTLINKID, DOCDATE, DISCOUNT, SALETYPE, TERMDATE, SUMWITHOUTVAT, SUMWITHVAT, SENDINGDATE, EXPORTED, DESCRIPT, REGNUM, LONGITUDE, LATITUDE, ITEMCOUNT)
              VALUES (3, 5, 444, 1, 123234, '2015-04-02 00:00:00', 8, 1, '2015-04-30 00:00:00', 123, 167, '2015-04-02 00:00:00', 0, 'Test3', 'W125', 34.45678, 23.9999, 23.6754);

COMMIT WORK;

INSERT INTO DOCUMENTDET (ID, DOCID, GOODSID, ITEMCOUNT, PRICEWITHOUTVAT, PRICEWITHVAT)
                 VALUES (1, 1, 2, 12, 1.2, 1.4);
INSERT INTO DOCUMENTDET (ID, DOCID, GOODSID, ITEMCOUNT, PRICEWITHOUTVAT, PRICEWITHVAT)
                 VALUES (2, 1, 3, 44, 2, 5);
INSERT INTO DOCUMENTDET (ID, DOCID, GOODSID, ITEMCOUNT, PRICEWITHOUTVAT, PRICEWITHVAT)
                 VALUES (3, 1, 4, 5, 4.5, 7);

INSERT INTO DOCUMENTDET (ID, DOCID, GOODSID, ITEMCOUNT, PRICEWITHOUTVAT, PRICEWITHVAT)
                 VALUES (4, 2, 2, 22, 2.2, 1.4);
INSERT INTO DOCUMENTDET (ID, DOCID, GOODSID, ITEMCOUNT, PRICEWITHOUTVAT, PRICEWITHVAT)
                 VALUES (5, 2, 3, 14, 2, 5);

INSERT INTO DOCUMENTDET (ID, DOCID, GOODSID, ITEMCOUNT, PRICEWITHOUTVAT, PRICEWITHVAT)
                 VALUES (6, 3, 2, 2, 1.2, 1.4);
INSERT INTO DOCUMENTDET (ID, DOCID, GOODSID, ITEMCOUNT, PRICEWITHOUTVAT, PRICEWITHVAT)
                 VALUES (7, 3, 3, 34, 2, 5);
INSERT INTO DOCUMENTDET (ID, DOCID, GOODSID, ITEMCOUNT, PRICEWITHOUTVAT, PRICEWITHVAT)
                 VALUES (8, 3, 4, 7, 1.5, 7);

COMMIT WORK;

INSERT INTO LOCATION_SAVE_STATE (ID, NAME)
                         VALUES (0, 'Unknown');
INSERT INTO LOCATION_SAVE_STATE (ID, NAME)
                         VALUES (1, 'Success');
INSERT INTO LOCATION_SAVE_STATE (ID, NAME)
                         VALUES (2, 'GPS is off');
INSERT INTO LOCATION_SAVE_STATE (ID, NAME)
                         VALUES (3, 'Time is over');
INSERT INTO LOCATION_SAVE_STATE (ID, NAME)
                         VALUES (4, 'Device on');
INSERT INTO LOCATION_SAVE_STATE (ID, NAME)
                         VALUES (5, 'Device off');

COMMIT WORK;

INSERT INTO LOCATION (LONGITUDE, LATITUDE, CLOCK, PROVIDER, ACCURACY, SEARCHTIME, SAVESTATE, CHECKINS, AGENTID, BATTERY, ID)
              VALUES (30.61214726, 50.45660968, '2013-08-20 09:04:56', 'gps', 1, 7, 1, 0, 444, NULL, 1);
INSERT INTO LOCATION (LONGITUDE, LATITUDE, CLOCK, PROVIDER, ACCURACY, SEARCHTIME, SAVESTATE, CHECKINS, AGENTID, BATTERY, ID)
              VALUES (30.61186402, 50.45675165, '2013-08-20 09:15:04', 'gps', 1, 16, 1, 0, 444, NULL, 2);
INSERT INTO LOCATION (LONGITUDE, LATITUDE, CLOCK, PROVIDER, ACCURACY, SEARCHTIME, SAVESTATE, CHECKINS, AGENTID, BATTERY, ID)
              VALUES (0, 0, '2013-08-20 09:26:18', 'gps', 1, 90, 3, 0, 444, NULL, 3);
INSERT INTO LOCATION (LONGITUDE, LATITUDE, CLOCK, PROVIDER, ACCURACY, SEARCHTIME, SAVESTATE, CHECKINS, AGENTID, BATTERY, ID)
              VALUES (30.61740461, 50.45126843, '2013-08-20 09:35:16', 'gps', 1, 28, 1, 0, 444, NULL, 4);
INSERT INTO LOCATION (LONGITUDE, LATITUDE, CLOCK, PROVIDER, ACCURACY, SEARCHTIME, SAVESTATE, CHECKINS, AGENTID, BATTERY, ID)
              VALUES (30.63629127, 50.42791594, '2013-08-20 09:44:58', 'gps', 1, 10, 1, 0, 444, NULL, 5);

COMMIT WORK;  

INSERT INTO USERS (ID, USERFIO, USERNAME, PASSWD, ISACTIVE)
           VALUES (102, 'User1', 'LOGIST', '123', NULL);
INSERT INTO USERS (ID, USERFIO, USERNAME, PASSWD, ISACTIVE)
           VALUES (10, 'User2', 'SYSDBA', '123', NULL);
INSERT INTO USERS (ID, USERFIO, USERNAME, PASSWD, ISACTIVE)
           VALUES (100, 'DefaultUser', 'DEFAULT_AGENT', '1234567890', NULL);

COMMIT WORK;

INSERT INTO DEVICESERIALNUMBERS (ID, SN, DESCRIPTION, USERID)
                         VALUES (1, 'sn123098', 'Test', 100);

COMMIT WORK;

INSERT INTO JOBS (NAME, ID, ROLENAME)
          VALUES ('Read', 10, 'READ');
INSERT INTO JOBS (NAME, ID, ROLENAME)
          VALUES ('Insert', 20, 'INSERT');
INSERT INTO JOBS (NAME, ID, ROLENAME)
          VALUES ('Edit', 30, 'EDIT');
INSERT INTO JOBS (NAME, ID, ROLENAME)
          VALUES ('Delete', 40, 'DELETE');
INSERT INTO JOBS (NAME, ID, ROLENAME)
          VALUES ('Run', 50, 'RUN');

COMMIT WORK;

INSERT INTO PART (ID, PARENTID, PARTNAME, SORTPOS, ROLENAME)
          VALUES (0, NULL, 'All', 5, NULL);
INSERT INTO PART (ID, PARENTID, PARTNAME, SORTPOS, ROLENAME)
          VALUES (10, 0, 'File', 10, NULL);
INSERT INTO PART (ID, PARENTID, PARTNAME, SORTPOS, ROLENAME)
          VALUES (1000, 0, 'WEB', 1000, NULL);
INSERT INTO PART (ID, PARENTID, PARTNAME, SORTPOS, ROLENAME)
          VALUES (1010, 1000, 'Rest', 1010, 'REST');

COMMIT WORK;

INSERT INTO PARTJOBSFORUSERS (ID, JOBSID, ISACTIVE, PARTID, USERID)
                      VALUES (1, 30, 0, 10, 100);
INSERT INTO PARTJOBSFORUSERS (ID, JOBSID, ISACTIVE, PARTID, USERID)
                      VALUES (2, 40, 0, 10, 100);
INSERT INTO PARTJOBSFORUSERS (ID, JOBSID, ISACTIVE, PARTID, USERID)
                      VALUES (3, 10, 1, 1010, 100);
INSERT INTO PARTJOBSFORUSERS (ID, JOBSID, ISACTIVE, PARTID, USERID)
                      VALUES (4, 20, 1, 1010, 100);

COMMIT WORK;

INSERT INTO WSSERVERS (ID, SERVERADDRESS, DESCRIPTION, ISDEFAULT)
               VALUES (2, '192.168.20.13:8080', 'Main', 1);
INSERT INTO WSSERVERS (ID, SERVERADDRESS, DESCRIPTION, ISDEFAULT)
               VALUES (1, 'http://94.153.229.92:5808', 'Extra', 0);

COMMIT WORK;

INSERT INTO SYSSETS (ID, WEBSERVICEADDRESS, BD_VER, DBFTYPE, MOBILESWAPVERSION)
             VALUES (1, 'http://192.168.20.13:8080/DTraderDaemon/serviceimpl?wsdl', 'ver. 1.0.9', 'FOXPRO_26', 10);

COMMIT WORK;

INSERT INTO SERVICECLIENT (ID, TYPECODE, NAME, FILEPATH, FILEVERSION)
                   VALUES (1, 1, 'Android', 'https://dealnote.biz/download/mobileclient.apk', '14');
INSERT INTO SERVICECLIENT (ID, TYPECODE, NAME, FILEPATH, FILEVERSION)
                   VALUES (2, 2, 'iOS', 'https://dealnote.biz/download/mobileclient.bin', '12');
INSERT INTO SERVICECLIENT (ID, TYPECODE, NAME, FILEPATH, FILEVERSION)
                   VALUES (3, 3, 'Windows', 'https://dealnote.biz/download/mobileclient.exe', '55');

COMMIT WORK;