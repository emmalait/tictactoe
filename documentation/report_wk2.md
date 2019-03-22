# Viikkoraportti 2

**Mitä olen tehnyt tällä viikolla? Miten ohjelma on edistynyt? Mitä opin tällä viikolla / tänään?**

Tällä viikolla aloitin toteuttamaan pelin perustoiminnallisuutta Javalla. Pelin perusmekaniikka toimii ihan hyvin, mutta siitä puuttuu vielä muutamia asioista (mm. diagonaalinen voiton tarkistus ja täysi ruudukko ilman voittoa -tilanteen käsittely). Pelillä on yksinkertainen konsoli-UI ja sitä pystyy tällä hetkellä pelaamaan konsolin kautta kaksi ihmistä. Tein myös keskeisimmälle osalle mekaniikasta testit ja lisäsin projektiin Jacocon testikattavuuden seurattavuutta varten. Lisäsin myös Checkstylen tyylin ylläpitoa ajatellen.

Tutustuin ohjaajan ehdottamaan alpha-beta-karsintaan ja aion yrittää toteuttaa algoritmin sen kera. Tarkensin myös määrittelydokumenttia sen osalta, että tarkoitus on että ruudukon koko ja voittosuoran pituus voidaan määrittää peliä aloittaessa. Tällä hetkellä nuo parametrit on kovakoodattuna TicTacToe-luokan konstruktorissa, mutta on tarkoitus, että ne voidaan antaa peliä käynnistäessä.

Seuraavaksi aion viimeistellä pelin perusmekaniikan ja alkaa rakentamaan itse algoritmia. 


**Mikä jäi epäselväksi tai tuottanut vaikeuksia?**

Viime viikon kommentista jäi mietityttämään, että onko jokin minimivaatimus minkäkokoisella ruudukolla/voittosuoralla toteutus pitäisi tehdä? Ymmärrän, että 3x3 on liian pieni ja ajatuksena on tällä hetkellä että toteutukseen määritellään parametreilla ruudukon koko ja voittosuoran pituus jolloin ne voivat periaatteessa olla mitä vaan.  En kuitenkaan tiedä vielä tuleeko tässä jokin seinä vastaan kun pääsen itse algoritmin kimppuun, minkä seurauksena joutuisin ehkä määrittämään niille jotkut maksimiarvot.

Onko työ tarpeeksi laaja kurssille jos siinä on samantyylinen konsoli-UI kuin nyt ja jos toteutan algoritmin rekursiivisesti alpha-beta-karsimisella?

**Käytetty aika:** 5 h