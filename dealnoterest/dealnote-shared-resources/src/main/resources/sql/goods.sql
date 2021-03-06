CREATE TABLE GOODSGROUP (
    ID          INTEGER NOT NULL,
    PARENTID    INTEGER,
    NAME        VARCHAR(35),
    OUTERID     INTEGER
);

ALTER TABLE GOODSGROUP ADD PRIMARY KEY (ID);
CREATE INDEX GOODSGROUP_OUTERID ON GOODSGROUP (OUTERID, ID);

CREATE SEQUENCE GEN_GOODSGROUP_ID;
ALTER SEQUENCE GEN_GOODSGROUP_ID RESTART WITH 1000;

CREATE TABLE PRIORITETCOLOR (
    ID           INTEGER NOT NULL,
    COLORCODE    VARCHAR(35),
    DISCRIPTION  VARCHAR(255)
);

ALTER TABLE PRIORITETCOLOR ADD CONSTRAINT PK_PRIORITETCOLOR PRIMARY KEY (ID);
CREATE SEQUENCE GEN_PRIORITETCOLOR_ID;
ALTER SEQUENCE GEN_PRIORITETCOLOR_ID RESTART WITH 1000;

CREATE TABLE GOODS (
    ID                INTEGER NOT NULL,
    CATEGORYID        INTEGER,
    SORTPOS           INTEGER,
    VATCOEF           DOUBLE PRECISION,
    NAME              VARCHAR(35),
    SERTIFICAT        VARCHAR(50),
    FULLNAME             VARCHAR(84),
    WEIGHT            DOUBLE PRECISION,
    ISACTIVE          SMALLINT,
    PRIORITETCOLORID  SMALLINT DEFAULT 0,
    GOODSIMAGE        VARBINARY(1024),
    GOODSDATA         CLOB,
    OUTERID           INTEGER,
    MEASUREID         INTEGER DEFAULT 0
);

ALTER TABLE GOODS ADD CONSTRAINT PK_GOODS PRIMARY KEY (ID);

ALTER TABLE GOODS ADD CONSTRAINT FK_GOODS_GROUP FOREIGN KEY (CATEGORYID) REFERENCES GOODSGROUP (ID) ON DELETE SET NULL ON UPDATE CASCADE;
ALTER TABLE GOODS ADD CONSTRAINT FK_MEASUREID FOREIGN KEY (MEASUREID) REFERENCES MEASURES (ID) ON DELETE SET NULL ON UPDATE CASCADE;
ALTER TABLE GOODS ADD CONSTRAINT FK_PRIORITETCOLORID FOREIGN KEY (PRIORITETCOLORID) REFERENCES PRIORITETCOLOR (ID) ON DELETE SET NULL ON UPDATE CASCADE;

CREATE INDEX GOODS_OUTERID ON GOODS (OUTERID, ID);

CREATE SEQUENCE GEN_GOOD_ID;
ALTER SEQUENCE GEN_GOOD_ID RESTART WITH 1000;

CREATE TABLE AGENTSGOODS (
    GOODSID    INTEGER NOT NULL,
    AGENTID    INTEGER NOT NULL,
    PRICE      DOUBLE PRECISION,
    AVAILABLE  DOUBLE PRECISION,
    ID         INTEGER NOT NULL
);

ALTER TABLE AGENTSGOODS ADD PRIMARY KEY (ID);

ALTER TABLE AGENTSGOODS ADD CONSTRAINT FK_AGENTSGOODS_AGENT FOREIGN KEY (AGENTID) REFERENCES AGENTS (ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE AGENTSGOODS ADD CONSTRAINT FK_AGENTSGOODS_GOODS FOREIGN KEY (GOODSID) REFERENCES GOODS (ID) ON DELETE CASCADE ON UPDATE CASCADE;

CREATE SEQUENCE GEN_AGENTSGOODS_ID;
ALTER SEQUENCE GEN_AGENTSGOODS_ID RESTART WITH 1000;