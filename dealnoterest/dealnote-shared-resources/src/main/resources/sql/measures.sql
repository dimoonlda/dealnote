CREATE TABLE MEASURES (
    ID       INTEGER NOT NULL,
    OUTERID  INTEGER,
    NAME     VARCHAR(35) NOT NULL
);

ALTER TABLE MEASURES ADD CONSTRAINT PK_MEASURES PRIMARY KEY (ID);

CREATE SEQUENCE GEN_MEASURES_ID;
ALTER SEQUENCE GEN_MEASURES_ID RESTART WITH 1000;