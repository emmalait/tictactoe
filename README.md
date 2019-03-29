# Ristinolla

## Dokumentaatio
- Viikkoraportit: [vko 1](https://github.com/emmalait/tictactoe/blob/master/documentation/report_wk1.md), [vko 2](https://github.com/emmalait/tictactoe/blob/master/documentation/report_wk2.md), [vko 3](https://github.com/emmalait/tictactoe/blob/master/documentation/report_wk3.md)
- [Määrittelydokumentti](https://github.com/emmalait/tictactoe/blob/master/documentation/specification.md)


## Muuta

Seuraavat toimenpiteet suoritetaan Maven-projektin juuressa eli polussa */tictactoe*.

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
Tiedostoon [checkstyle.xml](https://github.com/emmalait/tictactoe/blob/master/tictactoe/checkstyle.xml) määritellyt tarkistukset suoritetaan komennolla:

```
mvn jxr:jxr checkstyle:checkstyle
```

Virheraporttia voi tarkastella avaamalla selaimessa tiedoston *target/site/checkstyle.html*.
