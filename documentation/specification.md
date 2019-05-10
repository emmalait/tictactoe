# Määrittelydokumentti

## Aihe
Ristinollatekoäly, jota vastaan voi pelata käyttäjä ja jota voi peluuttaa itseään vastaan. Ruudukon kokoa ja voittosuoran pituutta voidaan määritellä erikseen.

## Syötteet
Pohjana on ruudukko, johon pelaajat syöttävät vuorotellen omaa merkkiään (X/O). Algoritmin syötteenä on ruudukko senhetkisestä pelitilanteesta, jonka perusteella se valitsee mihin koordinaattiin syöttää oman merkkinsä. Algoritmin tulos on siis siirto.

## Algoritmit ja tietorakenteet
- Min-max-algoritmi alpha-beta karsinnalla

## Aika- ja tilavaativuus
- Best case: O(√b<sup>d</sup>)
- Worst case: O(b<sup>d</sup>)

## Lähteet
- https://en.wikipedia.org/wiki/Minimax
- https://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning