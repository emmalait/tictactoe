# Viikkoraportti 6

**Mitä olen tehnyt tällä viikolla? Miten ohjelma on edistynyt? Mitä opin tällä viikolla / tänään?**

Sain vihdoin ratkaistua ongelman minmaxAB-algoritmin toiminnassa (mikä tyypillisesti johtui tietenkin suurilta osin testailun yhteydessä asetetusta kovakoodatusta syvyysarvosta) ja korjattua omassa ArrayList-toteutuksessa olleen pienen bugin, mistä syystä se ei toiminut oikein. Lisäsin ArrayList-toteutukseen myös poisto-ominaisuuden. Olen myös tehnyt koodiin jonkin verran refaktorointia ja yrittänyt erotella koodia järkevimmiksi kokonaisuuksiksi. MinmaxAB-algoritmin voi nyt myös rajoittaa tekemään haun tiettyyn syvyyteen eikä käymään läpi kaikkia vaihtoehtoja terminaalitilaan asti.

Aloitin myös toteuttamaan tekoälyn tehostavaa ominaisuutta, jossa tekoäly laskisi pisteytyksen vain pelattua aluetta ympäröiville siirroille. Sain valmiiksi ominaisuuden, joka pystyisi etsimään pelatun alueen reunat perustuen edelliseen siirtoon, mutta huomasin sitä testatessani että se toimii aivan liian hitaasti ollakseen hyödyllinen tekoälyn toiminnan tehostamiseen. Uusi ajatus on pitää kirjaa pelialueen reunoista jokaisen siirron yhteydessä (jokaisen siirron yhteydessä listalle lisätään siirtoa ympäröivät ruudut), mutta sen toteutus on vielä kesken.

Vielä pitäisi tehdä seuraavaa:
- Tekoälyn optimointi
- Refaktorointi: erityisesti AI-luokan pilkkominen pienempiin metodeihin, yleisesti koodin järjestyksen/rakenteen järkevöittäminen, käli
- Testien päivitys vastaamaan refaktoroitua koodia ja sopivien testien kirjoittaminen erityisesti tekoälylle
- Dokumentaation päivittäminen/edistäminen

**Mikä jäi epäselväksi tai tuottanut vaikeuksia?**

Seuraavat asiat ovat pohdituttaneet:
- Jos minmax-algoritmi pysähtyy ennen terminaalitilaa, minkä perusteella asettaa haaralle score?
- Tekoälyn testaus: minkälaisia testejä sille on järkevää kirjoittaa?

**Käytetty aika:** 11 h