# Ristinolla

## Dokumentaatio
- Viikkoraportit: [vko 1](https://github.com/emmalait/tictactoe/blob/master/documentation/report_wk1.md), [vko 2](https://github.com/emmalait/tictactoe/blob/master/documentation/report_wk2.md), [vko 3](https://github.com/emmalait/tictactoe/blob/master/documentation/report_wk3.md), [vko 4](https://github.com/emmalait/tictactoe/blob/master/documentation/report_wk4.md), [vko 5](https://github.com/emmalait/tictactoe/blob/master/documentation/report_wk5.md), [vko 6](https://github.com/emmalait/tictactoe/blob/master/documentation/report_wk6.md)
- [Määrittelydokumentti](https://github.com/emmalait/tictactoe/blob/master/documentation/specification.md)
- [Toteutusdokumentti](https://github.com/emmalait/tictactoe/blob/master/documentation/implementation.md)
- [Testausdokumentti](https://github.com/emmalait/tictactoe/blob/master/documentation/testing.md)


## Muuta

Seuraavat toimenpiteet suoritetaan Maven-projektin juuressa eli polussa */tictactoe*.

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
