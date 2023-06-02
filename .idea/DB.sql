CREATE TABLE USER (
    userNumber INT(11) NOT NULL,
    id VARCHAR(45) UNIQUE NOT NULL,
    password VARCHAR(45) NOT NULL,
    name VARCHAR(45) NOT NULL,
    roomID INT(11) NULL
);

CREATE TABLE ROOM (
    date VARCHAR(45) NOT NULL,
    region VARCHAR(45) NOT NULL,
    genre VARCHAR(45)	NOT NULL,
    difficulty FLOAT(11) NOT NULL	DEFAULT 0,
    fear FLOAT(11) NOT NULL DEFAULT 0,
    activity	FLOAT(11)	NOT NULL	DEFAULT 0,
    roomID	INT(11)	NOT NULL,
    title	VARCHAR(45)	NOT NULL, 
    roomIntro	VARCHAR(200) NULL 
);

CREATE TABLE CHAT (
    chatID INT(11) NULL,
    roomID INT(11) NULL,
    senderID INT(11) NULL,
    content text NULL,
    createAT DATETIME NULL
);

ALTER TABLE USER ADD CONSTRAINT PK_USER PRIMARY KEY (userNumber);

ALTER TABLE CHAT ADD CONSTRAINT PK_CHAT PRIMARY KEY (chatID);

ALTER TABLE INFO ADD CONSTRAINT PK_INFO PRIMARY KEY (roomID);

ALTER TABLE USER ADD CONSTRAINT FK_Room_TO_User FOREIGN KEY (roomID2)
REFERENCES INFO (roomID);

ALTER TABLE CHAT ADD CONSTRAINT FK_Room_TO_Chat FOREIGN KEY (roomID2)
REFERENCES INFO (roomID);