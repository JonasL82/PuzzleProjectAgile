PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE reviews(
MovieID int NOT NULL,
AccountID int NOT NULL,
ReviewID int PRIMARY KEY,
ReviewText text,
Grade int);
CREATE TABLE actors(Name text NOT NULL, Birthplace text, DateOfBirth text, ActorID int PRIMARY KEY);
INSERT INTO "actors" VALUES('Henry Cavill','UK','1983-05-05',10003);
INSERT INTO "actors" VALUES('Orson Welles','USA','1915-05-06',10001);
INSERT INTO "actors" VALUES('Ben Affleck','USA','1972-08-15',10002);
INSERT INTO "actors" VALUES('Ryan Gosling','Canada','1980-11-12',10004);
INSERT INTO "actors" VALUES('Emma Stone','USA','1988-11-06',10005);
CREATE TABLE accounts(Username text UNIQUE NOT NULL, Password text NOT NULL, Email text NOT NULL, AdminStatus boolean, AccountID int PRIMARY KEY);
INSERT INTO "accounts" VALUES('standard','!#¤%&/()','none@email.com',0,0);
INSERT INTO "accounts" VALUES('DemoAdmin','aaaeee','not@important.com',1,10001);
INSERT INTO "accounts" VALUES('DemoUser','bbbfff','user@iths.se',0,10002);
CREATE TABLE casts (MovieID int, ActorID int, CharacterName text);
INSERT INTO "casts" VALUES(10002,10003,'Clark Kent / Superman');
INSERT INTO "casts" VALUES(10002,10002,'Bruce Wayne / Batman');
INSERT INTO "casts" VALUES(10006,10002,'Tony Mendez');
CREATE TABLE movies(Title text NOT NULL, ProductionYear int NOT NULL, Genre text, Language text, Director text, Screenwriter text, ReleaseDate text, AgeLimit text, ProductionCompany text, MovieID int PRIMARY KEY);
INSERT INTO "movies" VALUES('Citizen Kane',1940,'Drama','English','Orson Welles','Orson Welles','1940-06-05','11','Universal',10001);
INSERT INTO "movies" VALUES('Batman v Superman: Dawn of Justice',2016,'Action','English','Zack Snyder','David S. Goyer','2016-03-23','R','Warner Bros.',10002);
INSERT INTO "movies" VALUES('Argo',2012,'Thriller','English, Iranian','Ben Affleck','Ben Affleck','2012-03-01','R','Unknown',10006);
COMMIT;
