INSERT INTO STOCKEXCHANGE (ID, "NAME") 
	VALUES (1, 'NASDAQ');
INSERT INTO STOCKEXCHANGE (ID, "NAME") 
	VALUES (2, 'NYSE');
INSERT INTO STOCKEXCHANGE (ID, "NAME") 
	VALUES (3, 'AMEX');

INSERT INTO WALLET_USER (ID, DTYPE, LASTCONNECTION, PASSWORD, USERNAME, FIRSTNAME, LASTNAME, STATUS, "TYPE", WALLET_ID) 
	VALUES (1, 'Administrator', '2014-01-13', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'admin', NULL, NULL, NULL, NULL, NULL);

INSERT INTO WALLET (ID, BALANCE)
        VALUES(1, 0.0);

INSERT INTO WALLET_USER (ID, DTYPE, LASTCONNECTION, PASSWORD, USERNAME, FIRSTNAME, LASTNAME, STATUS, "TYPE", WALLET_ID) 
	VALUES (2, 'Client', '2014-01-13', '04f8996da763b7a969b1028ee3007569eaf3a635486ddab211d512c85b9df8fb', 'user', 'Quentin', 'Bisson', 0, 0, 1);

INSERT INTO WALLET (ID, BALANCE)
        VALUES(2, 50000);

INSERT INTO WALLET_USER (ID, DTYPE, LASTCONNECTION, PASSWORD, USERNAME, FIRSTNAME, LASTNAME, STATUS, "TYPE", WALLET_ID) 
	VALUES (3, 'Client', '2014-01-13', '9f86d081884c7d659a2feaa0c55ad015a3bf4f1b2b0b822cd15d6c15b0f00a08', 'test', 'David', 'Charpentier', 0, 1, 2);