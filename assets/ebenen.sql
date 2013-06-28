-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Erstellungszeit: 14. Jan 2013 um 20:45
-- Server Version: 5.5.27
-- PHP-Version: 5.4.7

--SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
--SET time_zone = "+00:00";


--/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
--/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
-- /*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
--/*!40101 SET NAMES utf8 */;

--
-- Datenbank: `baumarkt_der_zukunft`
--

-- --------------------------------------------------------
--
-- Tabellenstruktur für Tabelle `hauptkategorien`
--

DROP TABLE IF EXISTS `hauptkategorien`;
CREATE TABLE IF NOT EXISTS `hauptkategorien` (
`_id` int(9) NOT NULL,
`hptkbezeichnung` varchar(99) NOT NULL,
`hptkstandort` varchar(9) NOT NULL);
--
-- Daten für Tabelle `hauptkategorien`
--
INSERT INTO `hauptkategorien` (`_id`, `hptkbezeichnung`, `hptkstandort`) VALUES
(10000000,'Bohren', '2');
INSERT INTO `hauptkategorien` (`_id`, `hptkbezeichnung`, `hptkstandort`) VALUES
(80000000,'Beleuchtung', '1');
INSERT INTO `hauptkategorien` (`_id`, `hptkbezeichnung`, `hptkstandort`) VALUES
(20000000,'Schrauben', '4');
INSERT INTO `hauptkategorien` (`_id`, `hptkbezeichnung`, `hptkstandort`) VALUES
(30000000,'Sägen/Schneiden', '5');
INSERT INTO `hauptkategorien` (`_id`, `hptkbezeichnung`, `hptkstandort`) VALUES
(40000000,'Sanitär','6');
INSERT INTO `hauptkategorien` (`_id`, `hptkbezeichnung`, `hptkstandort`) VALUES
(50000000,'Garten','9');
INSERT INTO `hauptkategorien` (`_id`, `hptkbezeichnung`, `hptkstandort`) VALUES
(60000000,'Boden','7');
INSERT INTO `hauptkategorien` (`_id`, `hptkbezeichnung`, `hptkstandort`) VALUES
(70000000,'Tapezieren/Streichen','8');
INSERT INTO `hauptkategorien` (`_id`, `hptkbezeichnung`, `hptkstandort`) VALUES
(90000000,'Reinigen', '3');
INSERT INTO `hauptkategorien` (`_id`, `hptkbezeichnung`, `hptkstandort`) VALUES
(100000000, 'Tierbedarf/Pflanzen','10');
INSERT INTO `hauptkategorien` (`_id`, `hptkbezeichnung`, `hptkstandort`) VALUES
(110000000, 'Elektrik/Elektronik','3');
-- --------------------------------------------------------
-- CREATE TABLE android_metadata (locale TEXT DEFAULT 'en_US');
-- INSERT INTO android_metadata(locale) VALUES ('en_US');
--
-- Tabellenstruktur für Tabelle `unterkategorien`
--
DROP TABLE IF EXISTS `unterkategorien`;
CREATE TABLE IF NOT EXISTS `unterkategorien` (
  `_id` int(11) NOT NULL,
  `untkbezeichnung` varchar(99) NOT NULL,
  `untkstandort` varchar(11) NOT NULL,
  fk_hauptkategorien int(11) NOT NULL, 
  PRIMARY KEY (`_id`)
);

--
-- Daten für Tabelle `unterkategorien`
--

INSERT INTO `unterkategorien` (`_id`, `untkbezeichnung`, `untkstandort`,`fk_hauptkategorien`) VALUES
(10100000, 'Bohrer', '2CDE',10000000);
INSERT INTO `unterkategorien` (`_id`, `untkbezeichnung`, `untkstandort`,`fk_hauptkategorien`) VALUES
(10200000, 'Bohrmaschinen', '3CDE',10000000);
INSERT INTO `unterkategorien` (`_id`, `untkbezeichnung`, `untkstandort`,`fk_hauptkategorien`) VALUES
(10300000, 'Zubehör', '2CDE',10000000);
INSERT INTO `unterkategorien` (`_id`, `untkbezeichnung`, `untkstandort`,`fk_hauptkategorien`) VALUES
(20100000, 'Schrauben', '4AB',20000000);
INSERT INTO `unterkategorien` (`_id`, `untkbezeichnung`, `untkstandort`,`fk_hauptkategorien`) VALUES
(20200000, 'Muttern', '4AB',20000000);
INSERT INTO `unterkategorien` (`_id`, `untkbezeichnung`, `untkstandort`,`fk_hauptkategorien`) VALUES
(20300000, 'Unterlegscheiben', '4AB',20000000);
INSERT INTO `unterkategorien` (`_id`, `untkbezeichnung`, `untkstandort`,`fk_hauptkategorien`) VALUES
(20400000, 'Sicherungen', '4AB',20000000);
INSERT INTO `unterkategorien` (`_id`, `untkbezeichnung`, `untkstandort`,`fk_hauptkategorien`) VALUES
(20500000, 'Schraubenzieher', '5AB',20000000);
INSERT INTO `unterkategorien` (`_id`, `untkbezeichnung`, `untkstandort`,`fk_hauptkategorien`) VALUES
(20600000, 'Schlüssel', '5AB',20000000);
INSERT INTO `unterkategorien` (`_id`, `untkbezeichnung`, `untkstandort`,`fk_hauptkategorien`) VALUES
(20700000, 'Akkuschrauber', '3C',20000000);
INSERT INTO `unterkategorien` (`_id`, `untkbezeichnung`, `untkstandort`,`fk_hauptkategorien`) VALUES
(20800000, 'Bits/Nüsse', '5AB',20000000);
INSERT INTO `unterkategorien` (`_id`, `untkbezeichnung`, `untkstandort`,`fk_hauptkategorien`) VALUES
(20900000, 'Zubehör', '5AB',20000000);
INSERT INTO `unterkategorien` (`_id`, `untkbezeichnung`, `untkstandort`,`fk_hauptkategorien`) VALUES
(80100000, 'Lampen', '1DE',80000000);
INSERT INTO `unterkategorien` (`_id`, `untkbezeichnung`, `untkstandort`,`fk_hauptkategorien`) VALUES
(80200000, 'Leuchtmittel', '1C',80000000);

-- --------------------------------------------------------
--
-- Tabellenstruktur für Tabelle `artikel`
--

DROP TABLE IF EXISTS `artikel`;
CREATE TABLE IF NOT EXISTS `artikel` (
  `_id` int(9) NOT NULL,
  `artikelstandort` varchar(11) NOT NULL,
  `artikelbezeichnung` varchar(99) NOT NULL,
  `preis` float DEFAULT NULL,
  `bildname` varchar(25) NOT NULL,
  fk_produktkategorien int(11) NOT NULL,
  beschreibung varchar(255),
  PRIMARY KEY (`_id`)
);

--
-- Daten für Tabelle `artikel`
--

INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(10101001, '07B3', 'Holzbohrer 50x6', 1.99, 'holzbohrer',10101000, 'Holzbohrer 50x6 für den ambitionierten Heimwerker. Stiftung Warentest sehr gut');
INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(10101002, '07B2', 'Holzbohrer 70x8', 2.99, 'holzbohrer',10101000, 'Universeller Holzbohrer für viele Bohrdurchmesser nutzbar, jedoch nur in dünnem Material.Stiftung Warentest sehr gut');
INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(10101003, '07B1', 'Holzbohrer 90x10', 2.99, 'holzbohrer',10101000, 'Dieser Bohrer ist vor allem für die Leitungsverlegung im konstruktiven Holzbau geeignet - die Schnittqualitüt ist dementsprechend weniger wichtig. Stiftung Warentest gut');
INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(10101004, '07A2', 'Holzbohrer 30x4', 1.49, 'holzbohrer',10101000, 'Holzbohrer 30x4 hat eine sehr gute Bohrleistungen und lange Standzeit');
INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(10102001, '07A2', 'Stahlbohrer 50x7', 2.99, 'stahlbohrer',10102000, 'Der Spezialist in der Bohrerfertigung, wird den hüchsten Ansprüchen gerecht. Der besondere Anschliff ermüglicht sicheres Ansetzen und einen sehr guten');
INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(10102002, '07A1', 'Stahlbohrer 70x9', 3.99, 'stahlbohrer',10102000, 'Optimales Bohrergebnis mit minimalem Vorschub');
INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(10201001, '03C2', 'Bohrhammer Bosch PBH 2800 RE inkl. Flachmei&szlig;el', 140.99, 'bohrhammer',10201000, 'Der neue Boschhammer PBH 2800 RE ist besonders leicht und liegt angenehm und sicher in der Hand');
INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(10201002, '03C1', 'Bohrhammer Bosch PBH 3000-2 FRE ', 208.99, 'bohrhammer',10201000, 'Das Bosch Bohrhammer PBH 3000 FRE Set eignet sich optimal für alle Heimwerker, die es mit Abrissarbeiten zu tun haben, die viel in Sichtbeton bohren und bei denen Qualitüt ruhig einen etwas hüheren Preis haben darf');
INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(10201003, '03C3', 'Tischbohrmaschine Bosch PBD 40', 280.99, '',10201000, 'Leistung: 710 W, Elektronik, Gewicht: 11.2 kg');
INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(10202001, '03D1', 'Schlagbohrmaschine Bosch PSB 500 RE', 55.99, 'schlagbohrmaschine',10202000, 'Leistung: 500 W, Abgabeleistung: 228 W, Max. Drehmoment: 7,5 Nm, Leerlaufdrehzahl: 50 ü3000 min-1, Schlagzahl: 48000 min-1, Gewicht 1,5 kg, Max. Bohrdurchmesser in Beton: 13 mm, Max. Bohrdurchmesser in Stahl: 10 mm, Max. Bohrdurchmesser in Holz: 20 mm');
INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(10202002, '03D2', 'Schlagbohrmaschine Bosch PSB 750 RCE', 79.99, 'schlagbohrmaschine',10202000, 'Schlagbohrmaschine Bosch PSB 750 RCE');
INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(20105001, '04C2', 'Holzschraube Linsensenkkopf m. I-Stern 3,5 x 25 mm, DIN 95, Vernickelt, 200 Stück', 2.15, '',20105000, 'Marke: Dresselhaus, Produkttyp: Blechschraube, Bohrschraube Linsenkopf I-Stern 4,2 x 16 mm DIN 7504 Galv. verz., Oberkategorie: Eisenwaren, schraubetyp: Bohrschraube I-Stern');
INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(20106001, '04A2', 'Spreizdübel Barracuda SD 8/40+S', 2.89, '',20106000, 'Die Dübel sind für Befestigung an fast allen Baustoffen geeignet. Ob Beton, Kalksandstein, Vollziegel, der Dübel hült bei fachgerechter Bohrung sicher und fest. Ungeeignet ist der Dübel bei Leichtbauwünden wie Rigips.');
INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(20106002, '04A1', 'Spreizdübel Barracuda SD 6/30+S', 2.99, '',20106000, 'Schwerlastdübel');
INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(20106003, '04B2', 'Allzweckdübel TRI 6/36 ', 2.99, '',20106000, 'Der Allzweckdübel TRI 6/36 hat sich zu einem Universal Dübel gemausert.');
INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(20201001, '04B3', 'Sechskantmutter selbstsichernd DIN 985, M3 galv.verzinkt, 100 Stück', 2.99, '',20201000, 'Sechskantmutter aus Stahl 6/8 verzinkt oder Edelstahl A2');
INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(80102001, '01D1', 'Halogen-Stehlampe Spider Glaskugel mit Chrom', 108.95, '',80102000, 'Hühe: 168 cm');
INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(80102002, '01D2', 'Halogen-Stehlampe sinned chrom mit Metallkopf', 158.9, '',80102000, 'Hühe: 150 cm');
INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(80102003, '01E1', 'Massive Halogen-Bodenleuchte 5-flg. Aiken ', 182, '',80102000, 'Material: Metall ; Glas , Besonderheiten: mit Fuüschalter , Länge: 250 mm , Breite: 180 mm , Höhe: 1550 mm , Flamme: 5 , Watt: G4 230V 20W , Leuchtmittel: inkl.Leuchtmittel , Schutzklasse: 2 , Schutzart: IP20');
INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(80102004, '01E2', 'dimmbarer LED-Fluter mit Lesearm in Nickel matt, getrennt ', 219, '',80102000, 'Am Deckenfluter befindet sich ein Metallreflektor, der über einen kleinen Lichtaustritt das Licht nach oben abgibt');
INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(80102005, '01D3', 'Innovative LED-Stehleuchte inklusive Power LED ', 149.9, '',80102000, 'Die Standleuchte überzeugt durch ihre schlichte kompakte Bauweise und wird auch Sie mit vielfältigen Funktionen beeindrucken');
INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(80201001, '02A3', 'Halogenbirne E27 100W', 1.99, 'halogenbirne',80201001, 'Stiftung Warentest sehr hell');
INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(80201002, '02A2', 'Halogenbirne E27 60W', 1.99, 'halogenbirne',80201001, 'Stiftung Warentest lange Lebensdauer');
INSERT INTO `artikel` (`_id`, `artikelstandort`, `artikelbezeichnung`, `preis`, `bildname`,`fk_produktkategorien`, beschreibung) VALUES
(80201003, '02A1', 'Halogenbirne E15 45W', 1.99, 'halogenbirne',80201001, 'Stiftung Warentest sehr geringer Verbrauch');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `bestellkopf`
--

DROP TABLE IF EXISTS `bestellkopf`;
CREATE TABLE IF NOT EXISTS `bestellkopf` (
  `_id` int(9) NOT NULL,
  `kundennr` int(4) ,
  `tempkundennr` int(4) ,
  `statnr` int(9) NOT NULL,
  `datum` date NOT NULL,
  `uhrzeit` time NOT NULL,
  `bestellwert` float NOT NULL,
  PRIMARY KEY (`_id`)
);

--
-- Daten für Tabelle `bestellkopf`
--

INSERT INTO `bestellkopf` (`_id`, `kundennr`, `tempkundennr`, `statnr`, `datum`, `uhrzeit`, `bestellwert`) VALUES
(0, 9999, NULL, 0, '0000-00-00', '00:00:00', 0);
INSERT INTO `bestellkopf` (`_id`, `kundennr`, `tempkundennr`, `statnr`, `datum`, `uhrzeit`, `bestellwert`) VALUES
(1, 0001, NULL, 0, '2012-12-29', '15:27:48', 7.57);
INSERT INTO `bestellkopf` (`_id`, `kundennr`, `tempkundennr`, `statnr`, `datum`, `uhrzeit`, `bestellwert`) VALUES
(2, NULL, 0001, 0, '2013-01-14', '15:27:36', 9.95);
INSERT INTO `bestellkopf` (`_id`, `kundennr`, `tempkundennr`, `statnr`, `datum`, `uhrzeit`, `bestellwert`) VALUES
(3, 0002, NULL, 0, '2012-12-23', '17:27:27', 0);
INSERT INTO `bestellkopf` (`_id`, `kundennr`, `tempkundennr`, `statnr`, `datum`, `uhrzeit`, `bestellwert`) VALUES
(4, 0003, NULL, 0, '2012-12-28', '16:23:35', 11.36);
INSERT INTO `bestellkopf` (`_id`, `kundennr`, `tempkundennr`, `statnr`, `datum`, `uhrzeit`, `bestellwert`) VALUES
(5, 0004, NULL, 0, '0000-00-00', '00:00:00', 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `bestellpositionen`
--

DROP TABLE IF EXISTS `bestellpositionen`;
CREATE TABLE IF NOT EXISTS `bestellpositionen` (
  `_id` int(9) NOT NULL,
  `bpos` int(3) NOT NULL,
  `artikelnr` int(9) NOT NULL,
  `menge` int(3) NOT NULL
);

--
-- Daten für Tabelle `bestellpositionen`
--

INSERT INTO `bestellpositionen` (`_id`, `bpos`, `artikelnr`, `menge`) VALUES
(2, 1, 10101001, 5);
INSERT INTO `bestellpositionen` (`_id`, `bpos`, `artikelnr`, `menge`) VALUES
(1, 2, 10101001, 1);

-- --------------------------------------------------------



--
-- Tabellenstruktur für Tabelle `produktkategorien`
--

DROP TABLE IF EXISTS `produktkategorien`;
CREATE TABLE IF NOT EXISTS `produktkategorien` (
  `_id` int(9) NOT NULL,
  `prodkbezeichnung` varchar(99) NOT NULL,
  `prodkstandort` varchar(11) NOT NULL,
  fk_unterkategorien int(11) NOT NULL,
  PRIMARY KEY (`_id`)
);

--
-- Daten für Tabelle `produktkategorien`
--

INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(10101000, 'Holzbohrer', '2C',10100000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(10102000, 'Stahlbohrer', '2C',10100000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(10103000, 'Steinbohrer', '2C',10100000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(10104000, 'Forstner/Kunstbohrer', '2C',10100000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(10105000, 'Spezialbohrer', '2C',10100000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(10201000, 'Bohrmaschine', '3C',10200000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(10202000, 'Schlagbohrmaschine', '3D',10200000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(10203000, 'Industriebohrmaschine', '3E',10200000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(10204000, 'Akkuschrauber', '3C',10200000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(20101000, 'Sechskantschrauben', '4A',20100000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(20102000, 'Zylinderschrauben', '4A',20100000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(20103000, 'Gewindeschrauben', '4A',20100000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(20104000, 'Blechschrauben', '4A',20100000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(20105000, 'Holzschrauben', '4B',20100000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(20106000, 'Dübel', '4B',20100000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(20201000, 'Sicherungsmuttern', '4B',20200000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(20202000, 'Kronenmuttern', '4B',20200000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(20203000, 'Hutmuttern', '4B',20200000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(80101000, 'Lampen', '1E',80100000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(80102000, 'Stehlampen', '1D',80100000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(80103000, 'Baustellen-/Industrielampen', '1E',80100000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(80201000, 'Glühbirnen', '1 C',80200000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(80202000, 'Energiesparbirnen', '1C',80200000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(80203000, 'Halogen', '1C',80200000);
INSERT INTO `produktkategorien` (`_id`, `prodkbezeichnung`, `prodkstandort`,`fk_unterkategorien`) VALUES
(80204000, 'LED', '1C',80200000);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `stammkunden`
--

DROP TABLE IF EXISTS `stammkunden`;
CREATE TABLE IF NOT EXISTS `stammkunden` (
  `_id` int(4) NOT NULL,
  `passwort` varchar(50) NOT NULL,
  `anrede` char(4) NOT NULL,
  `vorname` varchar(20) NOT NULL,
  `nachname` varchar(20) NOT NULL,
  `strasse` varchar(30) NOT NULL,
  `plz` int(5) NOT NULL,
  `ort` varchar(20) NOT NULL,
  `telenr` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `kartennr` int(8) NOT NULL,
  `statnr` int(8) NOT NULL,
  PRIMARY KEY (`_id`)
);

--
-- Daten für Tabelle `stammkunden`
--

INSERT INTO `stammkunden` (`_id`, `passwort`, `anrede`, `vorname`, `nachname`, `strasse`, `plz`, `ort`, `telenr`, `email`, `kartennr`, `statnr`) VALUES
(0001, 'e80b5017098950fc58aad83c8c14978e', 'Herr', 'Max', 'Mustermann', 'Musterstrasse 15', 72458, 'Albstadt-Ebingen', '0743177777', 'max.mustermann@mustermail.com', 20000001, 1);
INSERT INTO `stammkunden` (`_id`, `passwort`, `anrede`, `vorname`, `nachname`, `strasse`, `plz`, `ort`, `telenr`, `email`, `kartennr`, `statnr`) VALUES
(0002, '26e162d0b5706141bdb954900aebe804', 'Frau', 'Maxelina', 'Musterfrau', 'Musterweg 2', 72458, 'Albstadt-Ebingen', '0743188888', 'maxelina.musterfrau@mustermail.de', 20000002, 1);
INSERT INTO `stammkunden` (`_id`, `passwort`, `anrede`, `vorname`, `nachname`, `strasse`, `plz`, `ort`, `telenr`, `email`, `kartennr`, `statnr`) VALUES
(0003, '879b75376498eab4e9b8968a46bfdb09', 'Herr', 'Hugo', 'Bartel', 'Dingsweg 5', 12345, 'Dingshausen', '012345678', 'dings@da.eu', 20000003, 1);
INSERT INTO `stammkunden` (`_id`, `passwort`, `anrede`, `vorname`, `nachname`, `strasse`, `plz`, `ort`, `telenr`, `email`, `kartennr`, `statnr`) VALUES
(0004, '8376935b94232768ee9113c12efd75b6', 'Herr', 'Michael', 'Teufele', 'Ingostrasse 65', 12345, 'Ringshausen', '07056 78859', 'Michael.Teufele@gmx.de', 20000004, 1);
INSERT INTO `stammkunden` (`_id`, `passwort`, `anrede`, `vorname`, `nachname`, `strasse`, `plz`, `ort`, `telenr`, `email`, `kartennr`, `statnr`) VALUES
(9999, '912ec803b2ce49e4a541068d495ab570', 'Herr', 'Patrick', 'Leuschner', 'Forsthausstrasse 4', 72119, 'Ammerbuch', '07073910382', 'patrick_leuschner@web.de', 0, 0);

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `stationen`
--

DROP TABLE IF EXISTS `stationen`;
CREATE TABLE IF NOT EXISTS `stationen` (
  `_id` int(8) NOT NULL,
  `statname` varchar(30) NOT NULL,
  PRIMARY KEY (`_id`)
);

--
-- Daten für Tabelle `stationen`
--

INSERT INTO `stationen` (`_id`, `statname`) VALUES
(1, 'Stationairy Device XCX II 001');
INSERT INTO `stationen` (`_id`, `statname`) VALUES
(2, 'Stationairy Device XCX II 002');
INSERT INTO `stationen` (`_id`, `statname`) VALUES
(3, 'Stationairy Device XCX II 003');
INSERT INTO `stationen` (`_id`, `statname`) VALUES
(4, 'Stationairy Device XCX II 004');
INSERT INTO `stationen` (`_id`, `statname`) VALUES
(5, 'Stationairy Device XCX II 005');
INSERT INTO `stationen` (`_id`, `statname`) VALUES
(6, 'Mobile Device CRY III 001');
INSERT INTO `stationen` (`_id`, `statname`) VALUES
(7, 'Mobile Device CRY III 002');
INSERT INTO `stationen` (`_id`, `statname`) VALUES
(8, 'Mobile Device CRY III 003');
INSERT INTO `stationen` (`_id`, `statname`) VALUES
(9, 'Mobile Device CRY III 004');
INSERT INTO `stationen` (`_id`, `statname`) VALUES
(10, 'Mobile Device CRY III 005');
INSERT INTO `stationen` (`_id`, `statname`) VALUES
(11, 'Mobile Device CRY III 006');
INSERT INTO `stationen` (`_id`, `statname`) VALUES
(12, 'Mobile Device CRY III 007');
INSERT INTO `stationen` (`_id`, `statname`) VALUES
(13, 'Mobile Device CRY III 008');
INSERT INTO `stationen` (`_id`, `statname`) VALUES
(14, 'Mobile Device CRY III 009');
INSERT INTO `stationen` (`_id`, `statname`) VALUES
(15, 'Mobile Device CRY III 010');
INSERT INTO `stationen` (`_id`, `statname`) VALUES
(16, 'Mobile Device CRY III 011');
INSERT INTO `stationen` (`_id`, `statname`) VALUES
(17, 'Mobile Device CRY III 012');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `tempkunden`
--

DROP TABLE IF EXISTS `tempkunden`;
CREATE TABLE IF NOT EXISTS `tempkunden` (
  `_id` int(4) NOT NULL,
  `statnr` int(3) NOT NULL,
  PRIMARY KEY (`_id`)
);

--
-- Daten für Tabelle `tempkunden`
--

INSERT INTO `tempkunden` (`_id`, `statnr`) VALUES
(0001, 1);

-- --------------------------------------------------------



--
-- Tabellenstruktur für Tabelle `zusatzinformationen`
--

DROP TABLE IF EXISTS `zusatzinformationen`;
CREATE TABLE IF NOT EXISTS `zusatzinformationen` (
  `_id` int(9) NOT NULL,
  `artikelbeschreibung` varchar(250) NOT NULL,
  PRIMARY KEY (`_id`)
);

--
-- Daten für Tabelle `zusatzinformationen`
--

INSERT INTO `zusatzinformationen` (`_id`, `artikelbeschreibung`) VALUES
(10201001, '  Nennaufnahmeleistung: 720 W     Max. Bohrdurchmesser in Beton: 26 mm     Max. Einzelschlagenergie: 2,6 J');
INSERT INTO `zusatzinformationen` (`_id`, `artikelbeschreibung`) VALUES
(10201002, 'Nennaufnahmeleistung: 750 W     Max. Bohrdurchmesser in Beton: 26 mm     Max. Einzelschlagenergie: 2,8 J');

-- /*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
-- /*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
-- /*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
