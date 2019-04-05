# Viikkoraportti 4

**Mitä olen tehnyt tällä viikolla? Miten ohjelma on edistynyt? Mitä opin tällä viikolla / tänään?**

Käytin suurimman osan ajasta minmax-algoritmin paranteluun ja sain sen vihdoin toimimaan toivotulla tavalla. Ryhdyin tämän jälkeen toteuttamaan minmaxia alpha-beta-karsinnalla ja sain toteutettua siitä jollain asteella toimivan version. Verrattuna perus-minmax-algoritmiin minmaxAB-algoritmin toiminta on huomattavasti nopeampaa (esim. 3x3 tyhjässä ruudukossa pikaisella testauksella perus-minmaxilla kestää ensimmäisen siirron valinnassa ~400 ms, kun minmaxAB:lla kestää ~2 ms), mutta huomasin, että joillain siirtoyhdistelmillä minmaxAB-algoritmi häviää eli se ei toimi oikein. En ole vielä ehtinyt perehtymään siihen miksi näin käy.

Algoritmien ohella tein koodille pientä refaktorointia ja jaoin osia koodista omiksi luokikseen ja useammaksi, pienemmäksi metodiksi. Lisäsin koodiin kommentteja ja JavaDoc-notaatioita sekä lisäsin muutamia testejä AI-luokalle. Aloitin myös toteutus- ja testausdokumenttien toteuttamisen.

Seuraavaksi yritän saada alpha-beta-karsinnan toimimaan paremmin ja aloitan omien tietorakenteiden (List, ArrayList) toteuttamisen. AI:n koodia voisi myös parantaa ja vähentää toisteisuutta.

**Mikä jäi epäselväksi tai tuottanut vaikeuksia?**

Alpha-beta-karsinnan toiminta tällä hetkellä hämmentää vaikka omasta mielestä logiikan pitäisi olla oikein. AB-algoritmi ei tällä hetkellä myöskään ole kovin tehokas yhtään suuremmissa kentissä (esim. 4 x 4). Olisiko järkevää yrittää tehostaa sen toimintaa esim. rajaamalla paikkoja, joita algoritmi kokeilee (tällä hetkellä kaikki tyhjät) tai rajaamalla laskennan syvyyttä (tällä hetkellä laskee terminaalitilaan asti)?

**Käytetty aika:** 10 h