# Määrittelydokumentti

## Aihe
Ristinollatekoäly, jota vastaan voi pelata käyttäjä ja jota voi peluuttaa itseään vastaan

## Syötteet
Pohjana on nxn-kokoinen ruudukko, johon pelaajat syöttävät vuorotellen omaa merkkiään (X/O). Algoritmin syötteenä on siis ruudukko senhetkisestä pelitilanteesta, jonka perusteella se valitsee mihin koordinaattiin syöttää oman merkkinsä. Algoritmin tulos on siis siirto.

## Algoritmit ja tietorakenteet
- Puu
- Min-max-algoritmi

## Aika- ja tilavaativuus
Arvio:
- Aikavaativuus: O(bm)
- Tilavaativuus: O(m)
jossa m = puun maksimisyvyys ja b = siirtojen määrä kussakin solmussa

## Lähteet
- https://en.wikipedia.org/wiki/Minimax