CREATE TABLE DOCTYPE (
    ID              SMALLINT NOT NULL,
    PAYFORMID       INTEGER,
    DOCCLASSID      INTEGER,
    OUTERID         INTEGER,
    DAYS            SMALLINT,
    ACCINCRNOVAT    SMALLINT,
    ACCINCRWITHVAT  SMALLINT,
    SNAME           VARCHAR(50),
    DISCOUNTFIRST   SMALLINT,
    VATOVERSUM      SMALLINT
);

ALTER TABLE DOCTYPE ADD CONSTRAINT PK_DOCTYPE PRIMARY KEY (ID);
ALTER TABLE DOCTYPE ADD CONSTRAINT FK_DOCTYPE_DOCCLASS FOREIGN KEY (DOCCLASSID) REFERENCES DOCCLASS (ID) ON DELETE SET NULL ON UPDATE CASCADE;
ALTER TABLE DOCTYPE ADD CONSTRAINT FK_DOCTYPE_PAYFORM FOREIGN KEY (PAYFORMID) REFERENCES PAYFORMS (ID) ON DELETE SET NULL ON UPDATE CASCADE;

CREATE SEQUENCE GEN_DOCTYPE_ID;
ALTER SEQUENCE GEN_DOCTYPE_ID RESTART WITH 1000;