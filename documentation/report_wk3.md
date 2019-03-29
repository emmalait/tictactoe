# Viikkoraportti 3

**Mitä olen tehnyt tällä viikolla? Miten ohjelma on edistynyt? Mitä opin tällä viikolla / tänään?**

Tällä viikolla viimeistelin pelin perusmekaniikkaa ja aloitin toteuttamaan minimax-algoritmia. Pelissä tehdään nyt voittotarkistukset vain viimeisen siirron "ympäriltä" eli vain niiltä riveiltä, sarakkeilta ja diagonaaleilta, joilla siirto on eikä tarkisteta koko pelilautaa. Pilkoin tarkistuksia myös pienemmiksi rekursiivisiksi metodeiksi, jotta niissä olisi vähemmän toistoa.

Pelissä on nyt myös alkeellinen minimax-algoritmi, joka ei vielä toimi 100% halutulla tavalla. En ole vielä debugannut algoritmia täydellisesti ja vaikuttaa siltä, että sitä pitää vielä jonkin verran työstää. Joissain pelitilanteissa se palauttaa outoja siirtoja (esim. jos on tilanne, jossa molemmilla pelaajilla on yhtä vaille valmis voittosuora ja on AI:n pelivuoro, algoritmi pyrkii edelleen blokkaamaan toista pelaajaa, vaikka voisi voittaa pelin yhdellä siirrolla), mutta en ole vielä ehtinyt tutkia mistä nämä johtuvat. En myöskään ole vielä kirjoittanut algoritmille minkäänlaisia testejä tai dokumentoinut sitä kunnolla, koska se tulee todennäköisesti vielä jonkin verran muuttumaan.

Seuraavaksi aion jatkaa minimax-algoritmin työstämistä ja varmistaa, että se toimii tarkoitetulla tavalla. Myös alfa-beta-karsinnan käytännön toteutus tulee ajankohtaiseksi kun saan algoritmin perusversion toimimaan kunnolla.

**Mikä jäi epäselväksi tai tuottanut vaikeuksia?**

Olen saanut aika paljon aikaa palamaan minimax-algoritmin tämänhetkisen version toteuttamiseen ja se on osoittautunutkin aika paljon haastavammaksi kuin mitä ennakoin. Uskon kyllä että nyt muutaman päivän vääntämisen jälkeen asiat alkavat selviytyä kun jättää koodin hetkeksi lepäämään. 

Tietorakenteihin liittyen on pohdituttanut se että mitä Javan valmiita tietorakenteita ja -tyyppejä lopullisessa versiossa saa olla eli mitä pitäisi itse toteuttaa?

**Käytetty aika:** 9 h