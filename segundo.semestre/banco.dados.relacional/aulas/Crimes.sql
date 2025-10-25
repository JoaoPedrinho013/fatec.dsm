CREATE TABLE crimes
(
CRIME_ID        NUMBER(6)    	NOT NULL,
SUSPECT_ID 	NUMBER(6)    	NOT NULL,
CRIME_TYPE  	VARCHAR2(30) 	NOT NULL,
COMMITTED_DATE	DATE		NOT NULL,
PRISON          VARCHAR2(20)    NOT NULL,
BEHAVIOR	VARCHAR2(6),
START_DATE	DATE		NOT NULL,
END_DATE	DATE		NOT NULL,

CONSTRAINT CRIMES_PRIMARY_KEY PRIMARY KEY (CRIME_ID),
CONSTRAINT CRIMES_FOREIGN_KEY FOREIGN KEY(SUSPECT_ID) REFERENCES suspects(SUSPECT_ID) ON DELETE CASCADE
);


INSERT INTO CRIMES VALUES (1001, 338, 'Robbery', TO_DATE ('12-Jan-2016', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('15-Feb-2016', 'dd-Mon-yyyy'), TO_DATE ('02-Jul-2016', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (268, 87, 'Car Theft', TO_DATE ('22-May-2007', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('26-Aug-2010', 'dd-Mon-yyyy'), TO_DATE ('01-Jul-2016', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (834, 106, 'Robbery', TO_DATE ('01-Jan-2015', 'dd-Mon-yyyy'), 'Stone House', 'Bad', TO_DATE ('09-Apr-2015', 'dd-Mon-yyyy'), TO_DATE ('24-Oct-2016', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (657, 207, 'Breach Of the Peace', TO_DATE ('24-Apr-2006', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('09-Jun-2006', 'dd-Mon-yyyy'), TO_DATE ('01-Dec-2006', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (181, 338, 'Robbery', TO_DATE ('22-Dec-2003', 'dd-Mon-yyyy'), 'Stone House', 'Bad', TO_DATE ('24-Feb-2004', 'dd-Mon-yyyy'), TO_DATE ('02-Jan-2005', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (161, 338, 'Robbery', TO_DATE ('26-Jan-2003', 'dd-Mon-yyyy'), 'Stone House', 'Bad', TO_DATE ('01-Apr-2003', 'dd-Mon-yyyy'), TO_DATE ('21-Nov-2003', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (121, 338, 'Robbery', TO_DATE ('12-Mar-2002', 'dd-Mon-yyyy'), 'Stone House', 'Bad', TO_DATE ('08-Jun-2002', 'dd-Mon-yyyy'), TO_DATE ('24-Jan-2003', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (812, 111, 'Impersonating A Police Officer', TO_DATE ('27-Apr-2008', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('11-Aug-2008', 'dd-Mon-yyyy'), TO_DATE ('08-Jan-2009', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (601, 338, 'Robbery', TO_DATE ('12-Jul-2010', 'dd-Mon-yyyy'), 'Pull Mount', 'Bad', TO_DATE ('19-Aug-2010', 'dd-Mon-yyyy'), TO_DATE ('05-Jun-2012', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (401, 338, 'Robbery', TO_DATE ('01-Jul-2008', 'dd-Mon-yyyy'), 'Pull Mount', 'Bad', TO_DATE ('16-Oct-2008', 'dd-Mon-yyyy'), TO_DATE ('24-May-2012', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (201, 338, 'Robbery', TO_DATE ('12-Jul-2005', 'dd-Mon-yyyy'), 'Stone House', 'Good', TO_DATE ('01-Sep-2005', 'dd-Mon-yyyy'), TO_DATE ('04-Oct-2007', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (101, 338, 'Robbery', TO_DATE ('12-Jul-2001', 'dd-Mon-yyyy'), 'Iron Gates', 'Bad', TO_DATE ('01-Nov-2001', 'dd-Mon-yyyy'), TO_DATE ('24-Feb-2002', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (1002, 100, 'Robbery', TO_DATE ('12-Jan-2016', 'dd-Mon-yyyy'), 'Stone House', 'Good', TO_DATE ('15-Feb-2016', 'dd-Mon-yyyy'), TO_DATE ('02-Jul-2016', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (802, 100, 'Robbery', TO_DATE ('01-Jan-2013', 'dd-Mon-yyyy'), 'Stone House', 'Bad', TO_DATE ('09-Apr-2013', 'dd-Mon-yyyy'), TO_DATE ('24-Oct-2015', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (182, 100, 'Robbery', TO_DATE ('22-Dec-2003', 'dd-Mon-yyyy'), 'Stone House', 'Bad', TO_DATE ('24-Feb-2004', 'dd-Mon-yyyy'), TO_DATE ('02-Jan-2005', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (162, 100, 'Robbery', TO_DATE ('26-Jan-2003', 'dd-Mon-yyyy'), 'Pull Mount', 'Bad', TO_DATE ('01-Apr-2003', 'dd-Mon-yyyy'), TO_DATE ('21-Nov-2003', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (122, 100, 'Robbery', TO_DATE ('12-Mar-2002', 'dd-Mon-yyyy'), 'Stone House', 'Bad', TO_DATE ('08-Jun-2002', 'dd-Mon-yyyy'), TO_DATE ('24-Jan-2003', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (416, 343, 'Breach Of the Peace', TO_DATE ('24-Apr-2001', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('09-Jun-2001', 'dd-Mon-yyyy'), TO_DATE ('01-Dec-2002', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (612, 488, 'Breach Of the Peace', TO_DATE ('24-Apr-2014', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('09-Jun-2014', 'dd-Mon-yyyy'), TO_DATE ('01-Dec-2015', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (602, 100, 'Robbery', TO_DATE ('12-Jul-2010', 'dd-Mon-yyyy'), 'Stone House', 'Bad', TO_DATE ('19-Aug-2010', 'dd-Mon-yyyy'), TO_DATE ('05-Jun-2012', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (402, 100, 'Robbery', TO_DATE ('01-Jul-2008', 'dd-Mon-yyyy'), 'Stone House', 'Bad', TO_DATE ('16-Oct-2008', 'dd-Mon-yyyy'), TO_DATE ('24-May-2012', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (202, 100, 'Robbery', TO_DATE ('22-Jul-2005', 'dd-Mon-yyyy'), 'Stone House', 'Bad', TO_DATE ('01-Sep-2005', 'dd-Mon-yyyy'), TO_DATE ('04-Oct-2007', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (1919, 412, 'Impersonating A Police Officer', TO_DATE ('24-Apr-2010', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('10-Aug-2010', 'dd-Mon-yyyy'), TO_DATE ('08-Jan-2011', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (82, 100, 'Robbery', TO_DATE ('18-Jul-2001', 'dd-Mon-yyyy'), 'Iron Gates', 'Bad', TO_DATE ('01-Nov-2001', 'dd-Mon-yyyy'), TO_DATE ('24-Feb-2002', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (62, 100, 'Car Theft', TO_DATE ('12-Jul-1999', 'dd-Mon-yyyy'), 'Iron Gates', 'Bad', TO_DATE ('05-Nov-1999', 'dd-Mon-yyyy'), TO_DATE ('23-Dec-2000', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (12, 100, 'Pickpocket', TO_DATE ('12-Jul-1996', 'dd-Mon-yyyy'), 'The Pokey',  'Good', TO_DATE ('01-Nov-1996', 'dd-Mon-yyyy'), TO_DATE ('11-Feb-1997', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (1211, 400, 'Car Theft', TO_DATE ('12-Jan-2013', 'dd-Mon-yyyy'), 'The Pokey', 'Bad', TO_DATE ('10-Oct-2013', 'dd-Mon-yyyy'), TO_DATE ('09-Oct-2014', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (1111, 400, 'Car Theft', TO_DATE ('11-May-2012', 'dd-Mon-yyyy'), 'The Pokey', 'Bad', TO_DATE ('11-Oct-2012', 'dd-Mon-yyyy'), TO_DATE ('02-Jan-2013', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (711, 400, 'Car Theft', TO_DATE ('19-Sep-2011', 'dd-Mon-yyyy'), 'The Pokey', 'Bad', TO_DATE ('11-Dec-2011', 'dd-Mon-yyyy'), TO_DATE ('28-Apr-2012', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (119, 354, 'Impersonating A Police Officer', TO_DATE ('27-Apr-2012', 'dd-Mon-yyyy'), 'The Pokey', 'Bad', TO_DATE ('10-Aug-2012', 'dd-Mon-yyyy'), TO_DATE ('08-Jan-2013', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (511, 400, 'Car Theft', TO_DATE ('11-May-2010', 'dd-Mon-yyyy'), 'The Pokey', 'Bad', TO_DATE ('11-Oct-2010', 'dd-Mon-yyyy'), TO_DATE ('21-Feb-2011', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (411, 400, 'Car Theft', TO_DATE ('11-May-2008', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('15-Aug-2008', 'dd-Mon-yyyy'), TO_DATE ('21-Feb-2009', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (311, 400, 'Car Theft', TO_DATE ('11-Nov-2007', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('12-Jan-2008', 'dd-Mon-yyyy'), TO_DATE ('21-Apr-2008', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (211, 400, 'Robbery', TO_DATE ('23-May-2005', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('02-Oct-2005', 'dd-Mon-yyyy'), TO_DATE ('21-Apr-2006', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (111, 400, 'Vandalism', TO_DATE ('11-May-2001', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('22-Oct-2001', 'dd-Mon-yyyy'), TO_DATE ('21-Nov-2001', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (123, 383, 'Breach Of the Peace', TO_DATE ('24-Apr-2004', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('09-Jun-2004', 'dd-Mon-yyyy'), TO_DATE ('01-Dec-2005', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (11, 225, 'Vandalism', TO_DATE ('12-Apr-2015', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('10-Jun-2015', 'dd-Mon-yyyy'), TO_DATE ('10-Dec-2015', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (07, 207, 'Vandalism', TO_DATE ('12-Apr-1995', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('11-Jul-1995', 'dd-Mon-yyyy'), TO_DATE ('10-Dec-1995', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (107, 207, 'Pickpocket', TO_DATE ('02-May-1996', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('10-Aug-1996', 'dd-Mon-yyyy'), TO_DATE ('18-Feb-1997', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (207, 207, 'Breach Of the Peace', TO_DATE ('19-Nov1996', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('01-Jun-1997', 'dd-Mon-yyyy'), TO_DATE ('08-Dec-1997', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (257, 207, 'Vandalism', TO_DATE ('12-Jan-1998', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('22-Jun-1998', 'dd-Mon-yyyy'), TO_DATE ('10-Aug-1998', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (307, 207, 'Impersonating A Police Officer', TO_DATE ('12-Apr-2000', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('04-Jan-2001', 'dd-Mon-yyyy'), TO_DATE ('28-Feb-2001', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (357, 207, 'Drunk In Charge', TO_DATE ('02-Apr-2001', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('01-Jun-2001', 'dd-Mon-yyyy'), TO_DATE ('01-Aug-2001', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (407, 207, 'Car Theft', TO_DATE ('12-Jan-2002', 'dd-Mon-yyyy'), 'Pull Mount', 'Good', TO_DATE ('13-Mar-2002', 'dd-Mon-yyyy'), TO_DATE ('27-Jul-2002', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (457, 207, 'Impersonating A Police Officer', TO_DATE ('23-Dec-2002', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('22-May-2002', 'dd-Mon-yyyy'), TO_DATE ('26-Aug-2002', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (1542, 212, 'Impersonating A Police Officer', TO_DATE ('27-Apr-2014', 'dd-Mon-yyyy'), 'The Pokey', 'Bad', TO_DATE ('10-Aug-2014', 'dd-Mon-yyyy'), TO_DATE ('08-Jan-2015', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (507, 207, 'Vandalism', TO_DATE ('24-Sep-2002', 'dd-Mon-yyyy'), 'Pull Mount', 'Good', TO_DATE ('15-Dec-2002', 'dd-Mon-yyyy'), TO_DATE ('18-May-2003', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (557, 207, 'Pickpocket', TO_DATE ('15-May-2004', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('28-Aug-2004', 'dd-Mon-yyyy'), TO_DATE ('12-Feb-2005', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (607, 207, 'Vandalism', TO_DATE ('17-May-2005', 'dd-Mon-yyyy'), 'Pull Mount', 'Good', TO_DATE ('08-Oct-2005', 'dd-Mon-yyyy'), TO_DATE ('26-Feb-2006', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (627, 207, 'Breach Of the Peace', TO_DATE ('24-Apr-2006', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('09-Jun-2006', 'dd-Mon-yyyy'), TO_DATE ('01-Dec-2006', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (1682, 214, 'Pickpocket', TO_DATE ('21-Nov-2007', 'dd-Mon-yyyy'), 'Pull Mount', 'Good', TO_DATE ('03-Oct-2008', 'dd-Mon-yyyy'), TO_DATE ('15-Jul-2010', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (707, 207, 'Drunk In Charge', TO_DATE ('17-May-2007', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('21-Jul-2007', 'dd-Mon-yyyy'), TO_DATE ('16-Feb-2008', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (757, 207, 'Impersonating A Police Officer', TO_DATE ('27-Apr-2008', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('10-Aug-2008', 'dd-Mon-yyyy'), TO_DATE ('08-Jan-2009', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (807, 207, 'Vandalism', TO_DATE ('28-May-2008', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('26-Aug-2008', 'dd-Mon-yyyy'), TO_DATE ('01-May-2009', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (857, 207, 'Pickpocket', TO_DATE ('21-Sep-2009', 'dd-Mon-yyyy'), 'Pull Mount', 'Good', TO_DATE ('30-Oct-2009', 'dd-Mon-yyyy'), TO_DATE ('16-May-2010', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (907, 207, 'Breach Of the Peace', TO_DATE ('10-Nov-2011', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('08-Feb-2012', 'dd-Mon-yyyy'), TO_DATE ('21-Dec-2012', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (957, 207, 'Impersonating A Police Officer', TO_DATE ('12-Oct-2012', 'dd-Mon-yyyy'), 'Pull Mount', 'Good', TO_DATE ('22-Dec-2012', 'dd-Mon-yyyy'), TO_DATE ('10-Dec-2014', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (1007, 207, 'Vandalism', TO_DATE ('16-May-2007', 'dd-Mon-yyyy'), 'The Pokey', 'Bad', TO_DATE ('23-Jul-2007', 'dd-Mon-yyyy'), TO_DATE ('10-Jan-2008', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (1019, 20, 'Pickpocket', TO_DATE ('26-Mar-2008', 'dd-Mon-yyyy'), 'The Pokey', 'Bad', TO_DATE ('21-Jun-2008', 'dd-Mon-yyyy'), TO_DATE ('08-May-2009', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (819, 20, 'Pickpocket', TO_DATE ('12-May-2009', 'dd-Mon-yyyy'), 'The Pokey', 'Bad', TO_DATE ('10-Jun-2009', 'dd-Mon-yyyy'), TO_DATE ('21-Dec-2009', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (619, 20, 'Pickpocket', TO_DATE ('16-Jan-2010', 'dd-Mon-yyyy'), 'The Pokey', 'Bad', TO_DATE ('27-Feb-2010', 'dd-Mon-yyyy'), TO_DATE ('16-Oct-2010', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (419, 20, 'Pickpocket', TO_DATE ('04-Nov-2010', 'dd-Mon-yyyy'), 'The Pokey', 'Bad', TO_DATE ('22-Dec-2010', 'dd-Mon-yyyy'), TO_DATE ('21-May-2011', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (219, 20, 'Robbery', TO_DATE ('16-May-2012', 'dd-Mon-yyyy'), 'The Pokey', 'Bad', TO_DATE ('09-Jul-2012', 'dd-Mon-yyyy'), TO_DATE ('08-Jan-2013', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (87, 20, 'Robbery', TO_DATE ('22-Mar-2013', 'dd-Mon-yyyy'), 'The Pokey', 'Bad', TO_DATE ('21-Jun-2013', 'dd-Mon-yyyy'), TO_DATE ('24-May-2014', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (17, 20, 'Robbery', TO_DATE ('19-Nov-2014', 'dd-Mon-yyyy'), 'The Pokey', 'Bad', TO_DATE ('13-Jan-2015', 'dd-Mon-yyyy'), TO_DATE ('28-May-2016', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (479, 59, 'Impersonating A Police Officer', TO_DATE ('23-Dec-2002', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('22-May-2003', 'dd-Mon-yyyy'), TO_DATE ('26-Aug-2003', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (499, 59, 'Bank Fraud', TO_DATE ('24-Sep-2002', 'dd-Mon-yyyy'), 'Pull Mount', 'Good', TO_DATE ('15-Dec-2002', 'dd-Mon-yyyy'), TO_DATE ('18-May-2003', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (559, 59, 'Pickpocket', TO_DATE ('15-May-2004', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('28-Aug-2004', 'dd-Mon-yyyy'), TO_DATE ('12-Feb-2005', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (749, 59, 'Vandalism', TO_DATE ('17-May-2005', 'dd-Mon-yyyy'), 'Pull Mount', 'Good', TO_DATE ('08-Oct-2005', 'dd-Mon-yyyy'), TO_DATE ('26-Feb-2006', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (789, 59, 'Breach Of the Peace', TO_DATE ('24-Apr-2006', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('09-Jun-2006', 'dd-Mon-yyyy'), TO_DATE ('01-Dec-2006', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (829, 59, 'Drunk In Charge', TO_DATE ('17-May-2007', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('21-Jul-2007', 'dd-Mon-yyyy'), TO_DATE ('16-Feb-2008', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (919, 59, 'Impersonating A Police Officer', TO_DATE ('27-Apr-2015', 'dd-Mon-yyyy'), 'The Pokey', 'Bad', TO_DATE ('10-Aug-2015', 'dd-Mon-yyyy'), TO_DATE ('08-Jan-2016', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (917, 59, 'Impersonating A Police Officer', TO_DATE ('27-Apr-2008', 'dd-Mon-yyyy'), 'The Pokey', 'Bad', TO_DATE ('10-Aug-2008', 'dd-Mon-yyyy'), TO_DATE ('08-Jan-2009', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (1245, 216, 'Breach Of the Peace', TO_DATE ('24-Apr-2015', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('09-Jun-2015', 'dd-Mon-yyyy'), TO_DATE ('01-Dec-2015', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (929, 59, 'Car Theft', TO_DATE ('28-May-2008', 'dd-Mon-yyyy'), 'The Pokey', 'Good', TO_DATE ('26-Aug-2008', 'dd-Mon-yyyy'), TO_DATE ('01-May-2009', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (1029, 59, 'Pickpocket', TO_DATE ('21-Sep-2009', 'dd-Mon-yyyy'), 'Pull Mount', 'Good', TO_DATE ('30-Oct-2009', 'dd-Mon-yyyy'), TO_DATE ('16-May-2010', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (1049, 59, 'Breach Of the Peace', TO_DATE ('10-Nov-2011', 'dd-Mon-yyyy'), 'The Pokey', 'Bad', TO_DATE ('08-Feb-2012', 'dd-Mon-yyyy'), TO_DATE ('21-Dec-2012', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (1209, 59, 'Impersonating A Police Officer', TO_DATE ('12-Oct-2012', 'dd-Mon-yyyy'), 'Pull Mount', 'Good', TO_DATE ('22-Dec-2012', 'dd-Mon-yyyy'), TO_DATE ('10-Dec-2014', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (359, 174, 'Pickpocket', TO_DATE ('21-Sep-2015', 'dd-Mon-yyyy'), 'Pull Mount', 'Good', TO_DATE ('30-Oct-2015', 'dd-Mon-yyyy'), TO_DATE ('16-May-2016', 'dd-Mon-yyyy'));

INSERT INTO CRIMES VALUES (1359, 59, 'Vandalism', TO_DATE ('16-May-2007', 'dd-Mon-yyyy'), 'The Pokey', 'Bad', TO_DATE ('23-Jul-2007', 'dd-Mon-yyyy'), TO_DATE ('10-Jan-2008', 'dd-Mon-yyyy'));
