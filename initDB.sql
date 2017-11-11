\encoding utf-8

DROP TABLE IF EXISTS BLOODTYPE;
CREATE TABLE BLOODTYPE
(
	BLOODTYPEID INTEGER PRIMARY KEY,
	BLOODTYPE CHARACTER(16)
);
INSERT INTO BLOODTYPE VALUES
(1,'A'),
(2,'B'),
(3,'O'),
(4,'AB');
DROP TABLE IF EXISTS DISEASE;
CREATE TABLE DISEASE
(
	DISEASEID CHARACTER(5) PRIMARY KEY,
	DISEASE VARCHAR,
	PRESCRIPTID CHARACTER(5),
	PROCEDUREID CHARACTER(5)
);
INSERT INTO DISEASE VALUES
('00001','病気1','00001','00001'),
('00002','病気2','00002','00002'),
('00003','病気3','00003','00003'),
('00004','病気4','00004','00004'),
('00005','病気5','00005','00005');
DROP TABLE IF EXISTS INSPECTION;
CREATE TABLE INSPECTION
(
	INSPECTID CHARACTER(7) PRIMARY KEY,
	INSPECTNAME VARCHAR,
	INSPECTIONDAY DATE,
	PATH VARCHAR
);
INSERT INTO INSPECTION VALUES
('0000001','検査1','2017-11-01','path1'),
('0000002','検査2','2017-11-02','path2'),
('0000003','検査3','2017-11-03','path3'),
('0000004','検査4','2017-11-04','path4'),
('0000005','検査5','2017-11-05','path5');
DROP TABLE IF EXISTS KARTE;
CREATE TABLE KARTE
(
	KARTEID CHARACTER(7) PRIMARY KEY,
	PATIENTID CHARACTER(5),
	SYMPTOM VARCHAR,
	SYMPTOMNOTE VARCHAR,
	DISEASEID CHARACTER(5),
	DISEASENOTE VARCHAR,
	INSPECTID CHARACTER(7),
	CONSULTATIONDAY DATE,
	MEDICINEHISTORY VARCHAR
);
INSERT INTO KARTE VALUES
('0000001','00001','病状1','病状備考1','00001','病名備考1','0000001','2017-11-01'),
('0000002','00002','病状2','病状備考2','00002','病名備考2','0000002','2017-11-02'),
('0000003','00003','病状3','病状備考3','00003','病名備考3','0000003','2017-11-03'),
('0000004','00004','病状4','病状備考4','00004','病名備考4','0000004','2017-11-04'),
('0000005','00005','病状5','病状備考5','00005','病名備考5','0000005','2017-11-05');
DROP TABLE IF EXISTS MEDICINE;
CREATE TABLE MEDICINE
(
	MEDICINEID CHARACTER(5) PRIMARY KEY,
	MEDICINE VARCHAR
);
INSERT INTO MEDICINE VALUES
('00001','薬1'),
('00002','薬2'),
('00003','薬3'),
('00004','薬4'),
('00005','薬5');
DROP TABLE IF EXISTS PATIENT;
CREATE TABLE PATIENT
(
	PATIENTID CHARACTER(5) PRIMARY KEY,
	FNAME CHARACTER(16),
	LNAME CHARACTER(16),
	FNAMEKANA CHARACTER(16),
	LNAMEKANA CHARACTER(64),
	SEXID INTEGER,
	BIRTHDAY DATE,
	BLOODTYPE INTEGER,
	ZIPCODE CHARACTER(7),
	ADDRESS VARCHAR,
	TEL CHARACTER(11),
	CLINICALRECORD VARCHAR
);
INSERT INTO PATIENT VALUES
('00001','病院','太郎','ビョウイン','タロウ',1,'1900-01-01',1,'0000001','住所1','08000000001','病歴1'),
('00002','病院','次郎','ビョウイン','ジロウ',2,'1900-01-02',2,'0000002','住所2','08000000002','病歴2'),
('00003','病院','三郎','ビョウイン','サブロウ',3,'1900-01-03',3,'0000003','住所3','08000000003','病歴3'),
('00004','病院','四郎','ビョウイン','シロウ',4,'1900-01-04',4,'0000004','住所4','08000000004','病歴4'),
('00005','病院','花子','ビョウイン','ハナコ',5,'1900-01-05',5,'0000005','住所5','08000000005','病歴5');
DROP TABLE IF EXISTS PRESCRIPTION;
CREATE TABLE PRESCRIPTION
(
	PRESCRIPTID CHARACTER(5) PRIMARY KEY,
	PATIENTID CHARACTER(5),
	MEDICINEID CHARACTER(5),
	QUANTITY CHARACTER(5)
);
INSERT INTO PRESCRIPTION VALUES
('00001','00001','00001','1個'),
('00002','00002','00002','2個'),
('00003','00003','00003','3個'),
('00004','00004','00004','4個'),
('00005','00005','00005','5個');
DROP TABLE IF EXISTS PROCEDURE;
CREATE TABLE PROCEDURE
(
	PROCEDUREID CHARACTER(5)PRIMARY KEY,
	PROCEDURE VARCHAR
);
INSERT INTO PROCEDURE VALUES
('00001','処方1'),
('00002','処方2'),
('00003','処方3'),
('00004','処方4'),
('00005','処方5');
DROP TABLE IF EXISTS RECEPTION;
CREATE TABLE RECEPTION
(
	RECEPTID CHARACTER(5) PRIMARY KEY,
	PATIENTID CHARACTER(5)
);
INSERT INTO RECEPTION VALUES
('00001','00001'),
('00002','00002'),
('00003','00003'),
('00004','00004'),
('00005','00005');
DROP TABLE IF EXISTS SEX;
CREATE TABLE SEX
(
	SEXID INTEGER PRIMARY KEY,
	SEX CHARACTER(6)
);
INSERT INTO SEX VALUES
(1,'male'),
(2,'female');