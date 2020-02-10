DROP SEQUENCE BOARDSEQ;
DROP TABLE BOARD;

CREATE SEQUENCE BOARDSEQ;
CREATE TABLE BOARD(
	SEQ NUMBER PRIMARY KEY,
	WRITER VARCHAR2(1000) NOT NULL,
	TITLE VARCHAR2(2000) NOT NULL,
	CONTENT VARCHAR2(4000) NOT NULL,
	REGDATE DATE NOT NULL
);

INSERT INTO BOARD VALUES(BOARDSEQ.NEXTVAL, '관리자', 'TEST','TEST',SYSDATE);

SELECT * FROM BOARD ORDER BY SEQ DESC;

SELECT B.* FROM ( SELECT A.*, ROWNUM AS RNUM  FROM ( SELECT * FROM BOARD ORDER BY SEQ DESC )A )B WHERE RNUM BETWEEN 1 AND 10 ;

 SELECT COUNT(*) AS TOTALPAGE FROM BOARD;

 SELECT COUNT(*) FROM BOARD

 