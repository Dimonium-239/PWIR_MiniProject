## HOW TO RUN

```
java -jar ./PWIR_MiniProject_jar/PWIR_MiniProject.jar
```


## Wstęp

Realizowaliśmy projekt na temat dźwigu w języku Java. Realizowaliśmy działanie żurawa wieżowego górno obrotowego.

## Opis działania

**Konstrukcja realizowanego żurawa:**

![PWiR%20Dokumentacja%2062403ef96e31458281d0666ac0c1bfd1/schemat-zurawia-wiezowego.jpg](PWiR%20Dokumentacja%2062403ef96e31458281d0666ac0c1bfd1/schemat-zurawia-wiezowego.jpg)

Na powyższy rysunku zaznaczone tę elementy dźwigu które zostały zaimplementowane przez nas.

Na poniższym rysunku przedstawiona projekcja górna roboczej płaszczyzny.

![PWiR%20Dokumentacja%2062403ef96e31458281d0666ac0c1bfd1/Angles.jpg](PWiR%20Dokumentacja%2062403ef96e31458281d0666ac0c1bfd1/Angles.jpg)

Okrąg opisany linią przerywaną to jest zasięg naszego dżwiga. Promień tego okręgu jest równy długości wysięgnika. 

W punkcie **(1)** widzimy położenie startowe naszego kranu, i w danym przypadku położenie startowe jest takie same jak i 0° w danym okręgu. W punkcie **(1)** hak jest bez ładunku natomiast ładunek już czeka na dostarczenie do punktu docelowego.  Ze swojego punktu startowego dżwig decyduję czy musi obracać się przeciwnie do ruchu wskazówek zegara czy zgodnie ze wskazówkami zegara żeby ustawić wysięgnik nad ładunkiem najkrótszą drogą. 

Kiedy ładunek jest pod wysięgnikiem **(2)** to hak może już być w takich samych koordynatach jak i ładunek - wtedy następuję załadowanie, lub wysięgnik może być nad ładunkiem, a hak nie zdąży się przemieścić do koordynat ładunku, to wtedy czekamy aż póki to się nie stanie. Kiedy hak jest w dokładnych koordynatach ładunku to wtedy następuje załadowanie. 

Dale dżwig decyduję jak dostarczyć ładunek najkrótszą drogą do punktu docelowego **(3)**. W analogiczny sposób jak w punkcie **(2)** hak dociera do punktu docelowego i następuję rozładowanie. 

Po rozładowaniu żuraw cieka na nowe zadanie (koordynaty ładunku i koordynaty punktu docelowego) i powtarza algorytm. 

## Procesy współbieżne

![PWiR%20Dokumentacja%2062403ef96e31458281d0666ac0c1bfd1/crane(2).png](PWiR%20Dokumentacja%2062403ef96e31458281d0666ac0c1bfd1/crane(2).png)

### Mamy 5 procesów współbieżnych:

- **Supplier** generuje **Supply** co **<10*, 20*>** sekund i zapisuję ich do kolejki.
- **Crane** bierze z kolejki ładunek (**Supply)** i zaczyna algorytm pobierania i dostawy ładunku do punku docelowego.
    - **RotatorEngine** odpowiedzialny za obracanie wysięgu dookoła wieży.
    - **VerticalEngine** odpowiedzialny za pionowe poruszenie haka.
    - **HorizontalEngine** odpowiedzialny za poziome poruszanie haka wzdłuż wysięgnika.

### Szczególnej o Supplier

W javie każda klasa, która będzie wyniesiona do osobnego wątku, musi implementować interfejs `Runnable`, który potrżebuje żeby funkcja `run()` była nadpisana, i ta funkcja będzie wywoływana jako funkcja główna tego nowego wątku.

Jak widzimy z powyższego diagramu, **Supplier** i **Crane**  komunikują między sobą równolegle i w taki sposób rozwiązują problem producenta i konsumenta z buforem. 

Klasa `Crane`:

 

![PWiR%20Dokumentacja%2062403ef96e31458281d0666ac0c1bfd1/Untitled.png](PWiR%20Dokumentacja%2062403ef96e31458281d0666ac0c1bfd1/Untitled.png)

Klasa `SupplierRunner`:

![PWiR%20Dokumentacja%2062403ef96e31458281d0666ac0c1bfd1/Untitled%201.png](PWiR%20Dokumentacja%2062403ef96e31458281d0666ac0c1bfd1/Untitled%201.png)

`supplies` jest naszą kolejką zadań do kturej zostają przesłane objekty klasy `Supply` która jest klasą opisującą ładunek. 

### Komunikowacja współbieżna pomiędzy dżwigem i dostawcą

Dla **krana** jego funkcja `run()` (odpowiedzialna za wątek) wykonuje metode `startTask()` z pierwszym, w kolejce ładunków, elmentem, przesłanym jako argument. I to jest powtarzane w nieskączonej pętli `while`. Po rozładowaniu i załadowaniu następuje zatrzymanie wątku na 100 milisekund, dlatego żeby symuloważ rozładowanie i załadowaie.

![PWiR%20Dokumentacja%2062403ef96e31458281d0666ac0c1bfd1/Untitled%202.png](PWiR%20Dokumentacja%2062403ef96e31458281d0666ac0c1bfd1/Untitled%202.png)

Dla **dostawcy** jego funkcja `run()` wygląda następująco:

W nieskończonej pętli `while` został stworzony nowy ładunek i wpisany do kolejki `supplies` i to wszystko z różnym odstępem czasowym w każdej iteracji.

![PWiR%20Dokumentacja%2062403ef96e31458281d0666ac0c1bfd1/Untitled%203.png](PWiR%20Dokumentacja%2062403ef96e31458281d0666ac0c1bfd1/Untitled%203.png)

### Szczególnej o **Crane**

W procesie **Crane** za każdym razem po otrzymaniu ładunku (**Supply)** z kolejki zostają uruchamiane silniki (**RotatorEngine, VerticalEngine, HorizontalEngine**) które działają równoległe do siebie, aż póki nie dostarczą hak do odpowiednich współrzędnyh. Po otrzymaniu nowego zadania algorytm się powtarza. 

![PWiR%20Dokumentacja%2062403ef96e31458281d0666ac0c1bfd1/Untitled%204.png](PWiR%20Dokumentacja%2062403ef96e31458281d0666ac0c1bfd1/Untitled%204.png)

Z powyższego kodu widzimy że tworzymy obiekty dziedziczące od klasy abstrakcyjnej `**Engine**`  która z kolei implementuje interface `**Runnable**` który pozwala na tworzenie nowych wątków.

Po stworzeniu obiektów klas **`RotatorEngine`, `VerticalEngine`, `HorizontalEngine`** wrzucamy ich do puli wątków (executor) (`Executors.*newChachedThreadPool()*`) co pozwala na proste i przejrzyste tworzenie procesów współbieżnych w Javie. 

`workers.forEach(executor::execute);` wątki zaczynają się wykonywać. 

`executor.shutdown();` kończymy działanie executor'a.

`executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);` czekamy póki wątki nie skączą swoje działanie samodzielnie, lub zabijamy executor'a po upłynienciu czasu. 

![PWiR%20Dokumentacja%2062403ef96e31458281d0666ac0c1bfd1/Untitled%205.png](PWiR%20Dokumentacja%2062403ef96e31458281d0666ac0c1bfd1/Untitled%205.png)
