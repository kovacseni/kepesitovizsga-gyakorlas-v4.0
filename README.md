# Vizsgafeladat

A feladatok megoldásához az IDEA fejlesztőeszközt használd!
Bármely régebbi osztályt megnyithatsz.

Új repository-ba dolgozz! A `pom.xml` tartalmát nyugodtan át lehet másolni.
Projekt, könyvtár, repository neve legyen: `kepesitovizsga-gyakorlas-v4.0`.
GroupId: `training`, artifactId: `kepesitovizsga-gyakorlas-v4.0`. Csomagnév: `hu.nive.ujratervezes.kepesitovizsga`.

A feladatok megoldásához ajánlom figyelmedbe a cheat sheetet:

https://github.com/Training360/strukturavalto-java-public/blob/master/cheet-sheet/cheet-sheet.md

Először másold át magadhoz a teszteseteket, majd commitolj azonnal!

A három (de ötnek számító) feladatra 3 órád van összesen!

Oldd meg a feladatokat, minden feladat megoldását egy külön commitban
add be!
Ha letelik az idő az első commitodhoz képest és nem fejezted be, megint commitolj, akkor is,
ha nem vagy kész! Utána nyugodtan folytathatod a megoldást, akár több órát is
ülhetsz felette, ha kész vagy, commitolj!

## Számjegyek összege

A SumOfDigits osztályban írd meg a `getSumOfDigits()` metódust, amely paraméterként egy Random példányt vár. A metódus
a Random-tól kapott egész számban adja össze a számjegyeket, majd az így kapott szám számjegyeit ismét adja össze. Ezt a lépést
addig ismételje, amíg az összeg egy számjegyű nem lesz, és végül ezt az egy számjegyet adja vissza!
(Pl. ha a véletlenszám 12345, akkor a számjegyek összege először 15, majd 6, ezt adja vissza a metódus.)

## Covid oltási lista (avagy újabb bőrt húzunk le ugyanarról a témáról, kicsit másképpen :) )

Adott egy fájl (`vaccinationlist_2021-03-02_1234.csv`, az src/test/resources könyvtárban található), amely egy adott
településen egy adott napra a koronavírus elleni oltásra, időpontra behívott emberek listáját tartalmazza.
A VaccinationList osztályban olvasd be a fájlt a memóriába! Ez a következő műveleteket jelenti:
- van az osztálynak egy MetaData metadata attribútuma. A MetaData osztály tárolja az oltást szervező település
  irányítószámát, nevét és az oltás napjának dátumát. A fájlt beolvasó metódus ezeket az adatokat beállítja az attribútumban.
- van az osztálynak egy Map<LocalTime, Person> attribútuma, amelyet szintén ez a metódus tölt fel adatokkal. (A Person
  osztály értelemszerűen az oltásra behívott emberek minden fontos adatát tárolja.)
  
Ezután oldd meg az alábbi feladatokat:
1. Add vissza a 100 évnél idősebbek listáját: `getPersonsMoreThanHundredYearsOld()` !
2. Add vissza a délutánra behívottak listáját: `getAfternoonPersons()`!
3. Ellenőrizd le minden ember TAJ-számát, hogy érvényes-e: `validateTaj()`! Ha találsz esetleg érvénytelen TAJ-számot, akár többet,
   dobj kivételt a megfelelő üzenettel!
   (A TAJ-szám ellenőrzés algoritmusa:
   a TAJ-szám 1., 3., 5. és 7. számjegyét szorozd meg 3-mal, a 2., 4., 6. és 8. számjegyét 7-tel, ezeket add össze.
   Az összeget 10-zel elosztva vedd az osztás maradékát, és ha ez a számjegy megegyezik a 9. számjeggyel (cdv-kód),
   akkor ez egy érvényes TAJ-szám.)
4. Hívj be egy megadott időpontra érkező embert! Legyen egy metódus (`inviteExactPerson(LocalTime time)`), amely egy
   konkrét időpontot vár paraméterül, és a következő szöveget adja vissza: "Kedves {nev}! Ön következik. Kérem, fáradjon be!"
   Természetesen ez egy template, neked a nevet be kell helyettesítened a szövegben a megfelelő helyre.
   Azt az esetet is kezeld megfelelően, ha a paraméterként kapott időpont nem létezik a rendszerben!
5. Legyen egy metódus, amely visszaadja az oltást szervező település nevét és irányítószámát: `Town getTown()`!
6. Legyen egy metódus, amely visszaadja, az oltás dátumát: `LocalDate getDateOfVaccination()`!
7. Legyen egy metódus, amely visszaadja egy adatszerkezetben, hogy melyik típusú oltást hányan kapták az itt felsoroltak
   közül: `getVaccinationStatistics()`! Ehhez szükséged lesz egy VaccinationType enumra a megfelelő példányokkal.
   Legyen érték azok számára is, akik még nem kaptak semmilyen oltást!
   
## Álláshirdetésre jelentkezők nyilvántartása

Egy cég adatbázisban tartja nyilván a hozzá jelentkezőket (Applicant) név (külön kereszt- és vezetéknév, ebben a sorrendben),
nem, telefonszám, email, és egy mindenkinek egyénileg kiosztott kód alapján, valamint mindenkinél szerepel a táblázatban
az a skill, amelyet a jelentkező az önéletrajzában a legerősebb oldalaként megjelölt. Ezen adatok alapján a jelentkezőkről
két olyan listát is használnak, amelyek nem tartalmazzák mindenkinek minden adatát, hanem a jobb áttekinthetőség érdekében
csak a lényegeseket.
Hozz létre egy ApplicantListGenerator interfészt, amelynek egyetlen metódusa van (`List<Applicant> getListFromDatabase()`).
Ez a metódus a DataSource-ot várja paraméterül, a feladata pedig az, hogy az adatbázisból lekérdezze a szükséges adatokat és
egy listában visszaadja azokat.
Két különböző módon teszi ezt:
- A ListByApplicantsPersonal osztályban egy olyan listát készít, amelyben az összes jelentkező szerepel, de mindenki
  csak a legfontosabb személyes adataival: keresztnév, vezetéknév, telefonszám és e-mail cím.
- A ListBySkills osztályban pedig egy olyat, amelyben csak azok szerepelnek (keresztnévvel, vezetéknévvel és skill-lel),
  akiknek az egyéni képességük valamilyen három karakteres rövidítéssel leírható (hiszen a cég tudja, hogy ezek a legmenőbb
  képességek a munkaerőpiacon :) ). A szűrést természetesen akár az adatbázis-lekérdezésnél, akár Java-ban is végezheted.
Vigyázz, mert a megfelelő megvalósításokhoz különböző konstruktorokkal és az equals metódussal is lesz teendőd!

(A teszteléshez szükséges fájl: resources/db/migration/V1__applicants.sql)