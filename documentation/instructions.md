# Käyttöohje

### Ohjelman suorittaminen

Ohjelma voidaan suorittaa komentoriviltä komennolla:

```
mvn compile exec:java -Dexec.mainClass=tictactoe.main.Main
```

### Jar-tiedoston generointi ja suorittaminen
Suoritettava jar-tiedosto generoidaan komennolla:

```
mvn package
```

Suoritettava jar-tiedosto generoituu polkuun *target/tictactoe-1.0-SNAPSHOT.jar*. Jar-tiedosto suoritetaan komennolla:

```
java -jar target/tictactoe-1.0-SNAPSHOT.jar
```

### Ohjelman käyttö

Ohjelmaa käytetään komentorivin kautta. Käynnistyttyään ohjelma pyytää ensin pelin asetuksiin liittyviä parametreja, kuten pelimoodin (ihminen vs. AI vai AI vs. AI), ruudukon koon ja voittosuoran pituuden.

```
=== Tic Tac Toe ===
--- Setup ---
Choose mode:
1 - Human vs. AI
2 - AI vs. AI
Mode: 1
Board rows: 5
Board columns: 5
Winning streak: 5
```

#### Ihminen vs. AI
Pelin alustuksen jälkeen ohjelma kysyy käyttäjältä siirron koordinaatteja, tulostaa peliruudukon ja hakee AI:n siirron vuorotellen kunnes peli loppuu jomman kumman voittoon tai tasapeliin.

```
Player X's turn
Enter move coordinates: 
row: 3
column: 3

- - - - - 
- - - - - 
- - X - - 
- - - - - 
- - - - - 

Player O's turn
Operation took: 20ms.

- - - - - 
- - O - - 
- - X - - 
- - - - - 
- - - - - 
```

#### AI vs. AI
Pelin alustuksen jälkeen tekoälyt pelaavat toisiaan vastaan ja pelin kulun voi nähdä konsoliin tulostetuista väliaikatilanteista kunkin vuoron jälkeen. Tekoälyt vuorottelevat kunnes peli loppuu.

### Testaus
Testit suoritetaan komennolla:

```
mvn test
```

Testikattavuusraportti luodaan komennolla:

```
mvn jacoco:report
```

Kattavuusraporttia voi tarkastella avaamalla selaimessa tiedoston *target/site/jacoco/index.html*.

### JavaDoc
JavaDoc generoidaan komennolla:

```
mvn javadoc:javadoc
```

JavaDocia voi tarkastella avaamalla selaimessa tiedoston *target/site/apidocs/index.html*.

### Checkstyle
Tiedostoon [checkstyle.xml](https://github.com/emmalait/FilmLogger/blob/master/checkstyle.xml) määritellyt tarkistukset suoritetaan komennolla:

```
mvn jxr:jxr checkstyle:checkstyle
```

Virheraporttia voi tarkastella avaamalla selaimessa tiedoston *target/site/checkstyle.html*.