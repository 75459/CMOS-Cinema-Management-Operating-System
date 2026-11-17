CMOS - Cinema Management OS

zamysl funkcji:


zakres pracownika:

* memo (ogloszenia pracownicze) - odczytaj tylko
* sprzedaż biletów 
* rezerwacja miejsc (zarezerwuj / usun rezerwacje / oplac rezerwacje)
* zarządzanie harmonogramem [tworzenie seansu, usuwanie seansu, modyfikacja seansu]
* zarządzanie salami [parametry zmienne: czysta(gotowa do seansu), sprawna, wyswietl stan(wszystkie parametry zmienne
i stale, liczba miejsc sprzedanych, over all info)]
* dostep do polityki kina (zasady wewnetrzen)


zarządca:
* memo (ogloszenia pracownicze) - dodaj / usun / odczytaj
* otwieranie lokalizacji / zamykanie lokalizacji
* dodawanie/usuwanie nowych sal []
* modyfikacja wartosci stalych istniejacych sal
* dodawanie filmów
* edycja polityki kina (zasady wenwetrzne)
* zarzadzanie filmami (dodawanie/usuwanie/modyfikacja)
* wyswietlanie listy praocowników i zarzadzanie grafikiem (rola, dni pracy)


-----------------------------------------
klasy:

main (func checkPassword, func issueMemo,func editMemo, )

cinema.saleManager (tabela z salami, func setNumber, func setSeatCount, func setClean, func setActive)
sala(wlasciwosci: number, seatCount, isClean, active)

filmy()
film()

filmy (nazwa{get}, gatunek{get}, dlugosc{get}, rating{get}, func setNazwa, func setGatunek, func setDlugosc, func setRating)

seanse()

pracownicy




Klasa modelu (liczba pojedyncza: cinema.Sala, Film) – odpowiada tylko za siebie. Trzyma swoje zmienne i ma własne gettery/settery (jak w Twoim modelu B).

Klasa managera (liczba mnoga: SaleManager, FilmyManager) – odpowiada za listę obiektów. Ma metody typu: dodajSale(), usunSale(), znajdzPoNumerze(), wyswietlWszystkie().










sale:
numer sali [STAŁA]
liczba miejsc [STAŁA]
czysta
sprawna

filmy: [CAŁA STAŁA]
nazwa
gatunek
dlugosc w min
rating(wiek min, np. 13 albo 18 albo all - wszyscy)

pracownicy:
imie nazwisko [STAŁA]
rola [STAŁA]
czy dzisiaj pracuje

seanse (harmonogram seansów):
film
sala
miejsca kupione
miejsca zarezerwowane

memo:
tresc


lokacje: [CAŁA STAŁA]
nazwa
adres
sale
pracownicy
(filmy jest taka sama dla kazdej lokalizacji)
(polityka jest taka sama dla kazdej lokalizacji)

