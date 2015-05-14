CREATE TABLE MEASURELINK (
    ID            INTEGER NOT NULL,
    GOODSID       INTEGER NOT NULL,
    MEASURESRCID  INTEGER NOT NULL,
    MEASUREDSTID  INTEGER NOT NULL,
    SRCVALUE      DOUBLE PRECISION DEFAULT 1,
    DSTVALUE      DOUBLE PRECISION DEFAULT 1
);

ALTER TABLE MEASURELINK ADD CONSTRAINT UNQ1_MEASURELINK UNIQUE (GOODSID, MEASURESRCID, MEASUREDSTID);

ALTER TABLE MEASURELINK ADD CONSTRAINT PK_MEASURELINK PRIMARY KEY (ID);

ALTER TABLE MEASURELINK ADD CONSTRAINT FK_GOODSID FOREIGN KEY (GOODSID) REFERENCES GOODS (ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE MEASURELINK ADD CONSTRAINT FK_MEASUREDSTID FOREIGN KEY (MEASUREDSTID) REFERENCES MEASURES (ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE MEASURELINK ADD CONSTRAINT FK_MEASURESRCID FOREIGN KEY (MEASURESRCID) REFERENCES MEASURES (ID) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE SEQUENCE GEN_MEASURELINK_ID;
ALTER SEQUENCE GEN_MEASURELINK_ID RESTART WITH 1000;