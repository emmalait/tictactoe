# Testausdokumentti

Ohjelman testaus on toteutettu JUnit-yksikkötestein.

Testejä on luotu kahdelle pääluokalle: pelimekaniikasta vastuussa olevalle TicTacToe-luokalle sekä tekoälyn toteuttavalle AI-luokalle.

TicTacToe-luokan testit testaavat, että pelimekaniikka toimii oikealla tavalla: esim. siirtojen laillisuus, erilaisten voittosuorien löytäminen ja vuoronvaihto.

AI-luokan testit testaavat, että algoritmi(t) löytävät voittavan siirron. AI-luokan testeissä testataan erikseen minmax-algoritmin perusversiota sekä minmax-algoritmia, jossa tehdään alpha-beta-karsintaa.

## Testien ajaminen
Testit suoritetaan Maven-projektin juuressa eli polussa */tictactoe* komennolla:

```
mvn test
```

Testikattavuusraportti luodaan komennolla:

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimessa tiedoston *target/site/jacoco/index.html*.