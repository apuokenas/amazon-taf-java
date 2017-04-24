# Amazon Test Automation Framework in Java
EN: Test Automation Framework in Java for the French Amazon Website<br />
LT: Automatinis prancūziško "Amazon" tinklalapio testų karkasas Java kalba

## Stateful
<table>
  <tr>
    <td>LT: SKAITYKMANE lietuviškai:</td><td>baigta</td>
  </tr>
  <tr>
    <td>EN: README in English:</td><td>TO-DO</td>
  </tr>
</table>

## Paskirtis
Šis automatinių testų karkasas yra kanditato į vienos įmonės testuotojo poziciją praktinės programavimo užduoties įgyvendinimas.<br />

#### Netaisyta užduoties formuluotė:
Pagrindiniai testo žingsniai būtų tokie:
1. Paleidžiama pasirinkta naršyklė;
2. Nueinama į pasirinktą puslapį (pvz. delfi.lt);
3. Paspaudžiama pasirinkta nuoroda;
4. Patikrinamas atsidaręs puslapis (pvz. patikrinama, kad puslapyje yra tam tikras mygtukas arba teksto fragmentas).

# Sudėtis
[Testų rinkinyje](https://github.com/apuokenas/amazon-taf-java/blob/master/src/test/java/cases/PurchaseOrderTest.java) atliekami 5 testų atvejai:
1. `testLaunchSite()`: maksimizuotai atidaroma naršyklė ("Chrome") [išpildomas 1/4 užduoties punktas], užkraunamas "Amazon" puslapis [išpildomas 2/4 užduoties punktas], ir jei yra prisijungta - atsijungiama [išpildomas 3/4 užduoties punktas];
2. `testLogIn()`: prisijungiama prie puslapio pagal numatytuosius credential'us ([`src/test/java/properties/user.properties`](https://github.com/apuokenas/amazon-taf-java/blob/master/src/test/java/properties/user.properties)) [išpildomas 3/4 užduoties punktas];
3. `testInitializeShoppingCart()`: nueinama į prekių krepšelį [išpildomas 3/4 užduoties punktas], ir jei jame yra prekių - jos pašalinamos [išpildomas 4/4 užduoties punktas];
4. `testAddProductToShoppingCart()`: pasirinkta prekė ([`src/test/java/enums/Products.java`](https://github.com/apuokenas/amazon-taf-java/blob/master/src/test/java/enums/Products.java), šiuo atveju - knyga ["Foundations of Software Testing: ISTQB Certification"](https://www.amazon.fr/Foundations-Software-Testing-Istqb-Certification/dp/1408044056 "The essential guide to software testing and to the ISTQB Foundation qualification")) dedama į pirkinių krepšelį [išpildomas 3/4 užduoties punktas];
5. `testVerifyPrices()`: tikrinama, ar sutampa prekės ir pirkinių krepšelio kainos (galbūt buvo rankiniu būdu pridėta daugiau prekių arba "Amazonas", kol automatizuotai apsipirkinėjome, staiga pakeitė knygos kainą) [išpildomas 4/4 užduoties punktas].

Projekte:
* 11 klasių, 
* 2 enum'ai ir 
* 1 failas su credential'ais, 
* taip pat pridėtos reikalingos bibliotekos logginimui (projekto struktūra pateikiama [žemiau](#standartinis-pagrindini%C5%B3-direktorij%C5%B3-ir-fail%C5%B3-layoutas)), šie lib'ai taip pat papildomai nurodyti ir programos classpath'e. 

Build'inimui (nurodymui, kad reikia JDK 8 `maven-compiler-plugin`'o, ir 5 priklausomybių injection'ui) automatizuoti naudoju "Maven'ą" ([`pom.xml`](https://github.com/apuokenas/amazon-taf-java/blob/master/pom.xml)). Pasirinktas "WebDriver'is", kaip jau galima suprasti iš pirmo testo, - "ChromeDriver" (sėkmingai naudojau v2.27.440174, o "Google Chrome" - v58.0.3029.81, kas yra keista, nes per macOS paketų valdyklę "Homebrew" parsisiųsta "ChromeDriver" versija palaiko tik "Chrome" v54-56). Programavau "IntelliJ IDEA" aplinkoje.

#### Reikalingų priklausomybių artifaktai: 
* `selenium-java` (galima naudoti pasirinktą "Selenium" web aplikacijų testavimo framework'o "WebDriver" implementaciją - "ChromeDriver", "FirefoxDriver" arba "InternetExplorerDriver", yra alternatyvos "Android'ui" ir "iOS'ui"), 
* `testng` ("TestNG" testavimo karkasas), 
* `log4j-api` (aplikacija programuojama "Log4j 2" API interfeisui), 
* `log4j-core` ("Log4j 2" implementacija, reikalinga programos vykdymo metu, bet ne per kompiliavimą), 
* `org.osgi.core` ("Open Service Gateway Initiative" framework'as kurti modulinę programą). 

## Standartinis pagrindinių direktorijų ir failų layoutas
<pre>
/img - README faile naudojamos instrukcijų iliustracijos

/lib - projektui reikalingos bibliotekos

/src
 |___test
      |___java - testų source'ai
           |___actions
           |    |___ShoppingActions.java - custom'iniai testavimo veiksmų metodai
           |
           |___base
           |    |____LoadProperties.java - user.properties failo užkrovimas
           |
           |___cases
           |    |____PurchaseOrderTest.java - programatinis testų scenarijus su testavimo atvejų žingsniais
           |
           |___enums - konstantų kolekcijos
           |    |____Products.java - informacija apie prekę su konstruktoriumi ir getter'iais
           |    |
           |    |____Url.java - reikalingos URL nuorodos su konstruktoriumi ir getter'iu
           |
           |___pages - visi web aplikaciją per jos elementų ID ir CSS selektorius (dėl to XPath'as niekur nenaudojamas, nors jis ir gali traverse'inti per DOM'ą, bet aplikacijoje reikalingi elementų ID ir klasių pavadinimai buvo unikalūs) reprezentuojantys puslapiai, paveldintys iš tėvinės CommonUtils.java klasės
           |    |____HomePage.java - metodai, skirti atsijungti, nueiti į pradžios ir prisijungimo puslapius, gauti krepšelio prekių skaičių, paspausti krepšelio ikoną
           |    |
           |    |____ProductPage.java - metodai veiksmams, atliekamiems produkto puslapyje
           |    |
           |    |____ShoppingCartPage.java - metodai patikrinti, ar esame krepšelio puslapyje, taip pat ištrinti iš krepšelio prekes
           |    |
           |    |____ShoppingCartReviewPage.java- metodai patikrinti, ar esame krepšelio apžvalgos puslapyje, ir gauti krepšelio kainą
           |    |
           |    |____SignInPage.java - metodai prisijungti
           |
           |___pojo
           |    |____Book.java - Book esybė kaip Plain Old Java Object su metodais užkrauti duomenis apie produktą iš jo puslapio
           |
           |___properties
           |    |____user.properties - nešifruoti dummy, bet validūs prisijungimo duomenys
           |
           |___utils
                |____CommonUtils.java - įvairios "žemesnio lygio" utilitos, realizuojančios testavimo veiksmus su WebDriver'iu
                |
                |____DriverUtils.java - metodai inicializuoti ir deinicializuoti WebDriver'į

.gitignore - failai ir folderiai, kurių Git'as netrackina

pom.xml - Maven'o failas, kuriame yra Project Object Model'is

README.md - projekto apžvalga Markdown formatu
</pre>

## Ko reikia, norint paleisti testus:
1. Interneto ryšio (pageidautina - "Skynet" ;-));
2. Integruotos programavimo aplinkos (pageidautina - mokamos "IntelliJ IDEA Ultimate" arba nemokamos "IntelliJ IDEA Community Edition" iš <https://www.jetbrains.com/idea/#chooseYourEdition>);
3. "Java SE Development Kit 8" (pagal savo kompiuterio architektūrą iš <http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html>);
4. "ChromeDriver WebDriver'io" (galima parsisiųsti iš <https://sites.google.com/a/chromium.org/chromedriver/downloads>, o į savo sistemos PATH'ą "ChromeDriver" aplinkos kintamąjį įdėti, vadovaujantis <https://sites.google.com/a/chromium.org/chromedriver/getting-started> ir savo operacinės sistemos gamintojo instrukcijomis);
5. Versijavimo kontrolės sistemos "Git" (iš <https://git-scm.com/download>).

## Projekto importavimo į IDE instrukcijos paveiksliukų "flow chart'u":
1. Atsidaryti Java kalbą palaikančią IDE (pavyzdžiuose - "IntelliJ IDEA", bet principai galėtų būti pritaikomi ir kitoms aplinkoms).<br />
![Step 1](https://github.com/apuokenas/amazon-taf-java/blob/master/img/taf-00.png)
2. Pasirinkti projekto importavimą.<br />
![Step 2](https://github.com/apuokenas/amazon-taf-java/blob/master/img/taf-01.png)
3. Pasirinkti į savo kompiuterį atsisiųstą projektą (parsisiųsti ir išarchyvuoti archyvą [`amazon-taf-java-master.zip`](https://github.com/apuokenas/amazon-taf-java/archive/master.zip) arba, turint susiinstaliavus "Git", terminale parsiklonuoti iš "GitHub'o": `git clone https://github.com/apuokenas/amazon-taf-java.git`).<br />
![Step 3](https://github.com/apuokenas/amazon-taf-java/blob/master/img/taf-02.png)
4. Pasirinkti "Maven" build'inimo automatizavimo įrankį.<br />
![Step 4](https://github.com/apuokenas/amazon-taf-java/blob/master/img/taf-03.png)
5. ​Projekto nustatymo importavimo lange sužymėti varneles taip, kaip parodyta. Pagal nutylėjimą, pasirinkimas "Import Maven projects automatically" yra nepažymėtas - tai reikia pakeisti, kad nereiktų vėliau vargti su "Maven" ir "IntelliJ IDEA" projektų modelių sinchronizacija, pakeitus ką nors (pvz., priklausomybės versiją) [`pom.xml`](https://github.com/apuokenas/amazon-taf-java/blob/master/pom.xml) faile.<br />
![Step 5](https://github.com/apuokenas/amazon-taf-java/blob/master/img/taf-04.png)
6. Pasirinkti norimą importuoti "Maven'o" projektą.<br />
![Step 6](https://github.com/apuokenas/amazon-taf-java/blob/master/img/taf-05.png)
7. Šiame žingsnyje galima persivadinti projektą, taip pat patikslinti vietą kompiuteryje, kur projektas saugomas. Dažniausiai čia iškart yra spaudžiamas po ilgų varginančiai neautomatizuotų "Next" click'inimų pamatytas projekto importavimo nustatymų pabaigos mygtukas "Finish".<br />
![Step 7](https://github.com/apuokenas/amazon-taf-java/blob/master/img/taf-06.png)
8. Palaukite, kol bus visiškai suimportuota projekto struktūra. Vykstančias fonines užduotis galite peržiūrėti, paspaudę apačioje dešinėje esančią progreso juostą.<br />
![Step 8](https://github.com/apuokenas/amazon-taf-java/blob/master/img/taf-07.png)
9. Kai viskas baigsis, galite atsidaryti failą [`src/test/java/cases/PurchaseOrderTest.java`](https://github.com/apuokenas/amazon-taf-java/blob/master/src/test/java/cases/PurchaseOrderTest.java), kuriame yra nurodyti visi testai bei jų metodų pavadinimai, suprantami ir biznio žmogui (metodų kūnai detalizuojami kitoje klasėje: `src/test/java/actions/ShoppingActions.java`).<br />
![Step 9](https://github.com/apuokenas/amazon-taf-java/blob/master/img/taf-08.png)
10. Gali būti, kad iššoks naudojamos ugniasienės pranešimas apie įeinančius tinklo jungimusis į Java programas. Leiskite joms priimti šiuos susijungimus.<br />
![Step 19](https://github.com/apuokenas/amazon-taf-java/blob/master/img/taf-09.png)
11. Dabar jau galima leisti automatinius testus. Spustelkite dešinį pelės mygtuką ant ​failo [`src/test/java/cases/PurchaseOrderTest.java`](https://github.com/apuokenas/amazon-taf-java/blob/master/src/test/java/cases/PurchaseOrderTest.java), esančio IDE dešinėje, projekto lange, ir pasirinkite "Run 'PurchaseOrderTest'" (greitosios klaviatūros kombinacijos specifiškos operacinei sistemai: `Control` (&#8963;) + `Shift` (&#8679;) + `R` - macOS, `Ctrl` + `Shift` + `F10` - Windows ir Linux; programeriai nežino, kas yra pelė - jie juk ne dizaineriai).<br />
![Step 11](https://github.com/apuokenas/amazon-taf-java/blob/master/img/taf-10.png)
12. Testų suite'as subuildinamas ir pradedamas vykdyti.<br />
![Step 12](https://github.com/apuokenas/amazon-taf-java/blob/master/img/taf-11.png)
13. macOS naudotojai informuojami su sėkmingu pranešimo burbulu "Notification Centre", Windows 10 gerbėjai turėtų irgi matyti kažką panašaus "Action Centre".<br />
![Step 13](https://github.com/apuokenas/amazon-taf-java/blob/master/img/taf-12.png)
14. Kitų operacinių sistemų naudotojams lieka naršyti žaliuojančius pranešimus "IntelliJ IDEA" vykdymo lange apačioje.<br />
![Step 14](https://github.com/apuokenas/amazon-taf-java/blob/master/img/taf-13.png)
15. Pavyzdinis testų eigos ir rezultato output'as (testai veikia vienodai gerai ir kai būna iš anksčiau prisijungta prie "Amazon", ir kai ne, ir kai nėra prekių krepšelyje, ir kai yra viena ar daugiau ar dar daugiau).<br />
<pre>
*** TEST_SUITE_BEGIN ***

INITIALIZING: Trying to sign out--maybe the session is open...
INITIALIZING: Navigating to Amazon.fr: http://www.amazon.fr...

HOME_PAGE: Selecting [YOUR_ACCOUNT] in navigation bar.
HOME_PAGE: Navigating to the [SIGNIN_PAGE]...

SIGNIN_PAGE: Entering username.
SIGNIN_PAGE: Entering password.
SIGNIN_PAGE: Clicking the [SIGN_IN] button.

INITIALIZING: Preparing the shopping cart...
*** Nothing to delete - the shopping cart appears to be empty. ***

*** Adding ISTQB_FOUNDATION to the shopping cart: ***
PRODUCT_PAGE: Navigating to http://www.amazon.fr/gp/product/1408044056...
PRODUCT_PAGE: Verifying that the product title is 'Foundations of Software Testing: Istqb Certification'...
PRODUCT_PAGE: Clicking on [ADD_TO_CART] button.

SHOPPING_CART_REVIEW_PAGE: Verifying that we are on SHOPPING_CART_REVIEW_PAGE...
*** Loading information about ISTQB_FOUNDATION: ***
PRODUCT_PAGE: Navigating to http://www.amazon.fr/gp/product/1408044056...
PRODUCT_PAGE: Verifying that the product title is 'Foundations of Software Testing: Istqb Certification'...
LOAD_INFO: Loading data from the Amazon...
	* Product Title: Foundations of Software Testing: Istqb Certification
	* Author: Dorothy Graham
	* Edition: (Anglais)
	* Offer Price: EUR 50,45

*** Verifying the actual price listed for the book: ***
	* The expected price is EUR 50,45
	* The actual price is EUR 50,45
PASS: The expected and actual prices do match! :-)

*** TEST_SUITE_END ***
</pre>
