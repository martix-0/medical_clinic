import java.util.Scanner;
import java.io.*;
import java.lang.*;

public class Main {
    public interface Pracownik {
        int getId();
        String getImie();
        String getNazwisko();
    }

    public static void main(String[] args) {
        PracownikMedyczny.zainicjalizujPracownikowMedycznych();
        PracownikRecepcji.ListaPracownikow();
        Pacjent.listaPacjentow();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Witamy w przychodni MedicJava\nPodaj swój numer id: ");
        int id;
        while (true) {
            try {
                id = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Nieprawidłowy format ID. Spróbuj ponownie: ");
            }
        }

        final int finalId = id;

        // Sprawdzamy, czy ID należy do pracownika medycznego, czy recepcji
        PracownikMedyczny pracownikMedyczny = PracownikMedyczny.pracownicyMedyczni.get( finalId );

        PracownikRecepcji pracownikRecepcji = PracownikRecepcji.pracownicyRecepcji.get( finalId );

        //Pracownik ma do wyboru poniższe opcje
        if (id < 200 && pracownikMedyczny != null) {
            while (true) {
            int wyborPracownikaMedycznego = -1;
            System.out.println("Witaj," + pracownikMedyczny.getImie() + " " + pracownikMedyczny.getNazwisko() +" Co chcesz zrobić?\n" +
                    "1. Otwórz kartę pacjenta\n" +
                    "2. Wyjdź z programu");
            while (true) {
                try {
                    wyborPracownikaMedycznego = Integer.parseInt(scanner.nextLine());
                    if (wyborPracownikaMedycznego >= 1 && wyborPracownikaMedycznego <= 2) {
                        break;
                    } else {
                        System.out.println("Wybierz odpowiedź 1 lub 2:");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Nieprawidłowy format. Podaj liczbę od 1 do 2:");
                }
            }

                if (wyborPracownikaMedycznego == 1) {
                    System.out.println("Podaj ID pacjenta:");
                    int idPacjenta = Integer.parseInt(scanner.nextLine());
                    Pacjent pacjent = Pacjent.znajdzPacjenta(idPacjenta);

                    if (pacjent == null) {
                        System.out.println("Nie znaleziono pacjenta o podanym ID.");
                        return;
                    }
// pracownik medyczny moze zrobic notatke do pacjenta badz wypisac lek
                    System.out.println("Wybrano pacjenta: " + pacjent.getImie() + " " + pacjent.getNazwisko() + " "+
                            "Co chcesz zrobić?\n" +
                            "1. Wpisz notatkę do wizyty\n" +
                            "2. Wypisz lek \n" +
                            "3. Powróć do głównego menu");

                    int opcja = Integer.parseInt(scanner.nextLine());

//tworzenie pliku w którym pacjent wypisuje notatke
                    switch (opcja) {
                        case 1:
                            System.out.println("Podaj nazwę pliku jako imie i nazwisko pacjenta, np.JanKowalski: ");
                            String fileName = scanner.nextLine() + ".txt";

                            File file = new File(fileName);

                            try {
                                if (file.createNewFile()) {
                                    System.out.println("Plik utworzony: " + file.getName());
                                } else {
                                    System.out.println("Plik już istnieje.");
                                }

                                FileWriter writer = new FileWriter(file);

                                System.out.println("Wpisz tekst do zapisania w pliku (wpisz 'koniec' aby zakończyć):");

                                while (true) {
                                    String input = scanner.nextLine();
                                    if ("koniec".equalsIgnoreCase(input)) {
                                        break;
                                    }
                                    writer.write(input + "\n");
                                }

                                writer.close();
                                System.out.println("Dane zapisane do pliku: " + file.getAbsolutePath());
                            } catch (IOException e) {
                                System.out.println("Wystąpił błąd: " + e.getMessage());
                            }
                            System.out.println("Wpisz treść notatki:");
                            String notatka = scanner.nextLine();
                            System.out.println("Notatka została dodana.");
                            break;
// wypisywanie leku
                        case 2:
                            System.out.println("Podaj nazwę leku:");
                            String nazwa = scanner.nextLine();
                            System.out.println("Podaj gramaturę leku:");
                            double gramatura = Double.parseDouble(scanner.nextLine());
                            System.out.println("Podaj ilość sztuk:");
                            int ilosc = Integer.parseInt(scanner.nextLine());

                            Lek nowyLek = new Lek(nazwa, gramatura, ilosc);
                            pacjent.dodajLek(nowyLek);
                            System.out.println("Lek został dodany.");
                            break;
                        case 3:
                            System.out.println("Powrót do menu głównego.");
                            break;
                        default:
                            System.out.println("Niepoprawny wybór.");
                    }
                } else {
                    System.out.println("Wyjście z programu.");
                    return;
                }
            }
        }
//ID dla pracownika recepcji, który nie ma dostępu do zapisu karty pacjenta przez lekarza
        else if (id > 200 && id < 1000 && pracownikRecepcji != null) {
            while (true) {
                // dodaj switch case z wyjsciem z programu
                int wyborPracownikaRecepcji = -1;
                System.out.println("Witaj " +  pracownikRecepcji.getImie() + " " + pracownikRecepcji.getNazwisko() +"\nCo chcesz zrobić?\n" +
                        "1. Wejdź do karty pacjenta" + "\n" +
                        "2. Zarejestruj nowego / usuń pacjenta\n" +
                        "3. Oblicz koszt wizyty \n"+
                        "4. Wyjdź z programu");

                int opcja = Integer.parseInt(scanner.nextLine());
                switch (opcja) {
                    case 1:
                        System.out.println("Podaj ID pacjenta:");
                        int idPacjenta = Integer.parseInt(scanner.nextLine());
                        Pacjent pacjent = Pacjent.znajdzPacjenta(idPacjenta);

                        if (pacjent == null) {
                            System.out.println("Nie znaleziono pacjenta o podanym ID.");
                            continue;
                        }
                        System.out.println("Wybrałeś pacjenta " + pacjent.getImie() + " " + pacjent.getNazwisko() + "\n" +
                                "Co chcesz zrobić?\n" +
                                "1. Zarezerwuj wizytę\n" +
                                "2. Anuluj wizytę\n" +
                                "3. Powróć do głównego menu");

                        int opcjaKarta = Integer.parseInt(scanner.nextLine());
                        switch (opcjaKarta) {
                            case 1:
                                try {
                                    System.out.println("Podaj datę następnej wizyty (DD-MM-RRRR):");
                                    String dataWizyty = scanner.nextLine();
                                    pacjent.umowNastepnaWizyte(dataWizyty);
                                    System.out.println("Wizyta została zapisana.");
                                } catch (NumberFormatException e) {
                                    System.out.println("Nieprawidłowy format. Podaj datę w formacie DD-MM-RRRR");
                                }
                                break;
                            case 2:
                                pacjent.anulujWizyte();
                                System.out.println("Wizyta została anulowana.");
                                break;

                            case 3:
                                System.out.println("Powrót do menu głównego.");
                                break;

                            default:
                                System.out.println("Niepoprawny wybór. Wybierz 1 lub 3.");
                        }
                        break;

//rejestracja/ usuwanie karty pacjenta
                    case 2:
                        System.out.println("Co chcesz zrobić? \n 1. Rejestracja pacjenta \n" + "2: Usuwanie karty pacjenta\n" + "3. Powróć do głównego menu");
                        int subOpcja = Integer.parseInt(scanner.nextLine());
                        switch (subOpcja) {
                            case 1:
                                System.out.println("Podaj imię pacjenta:");
                                String imie = scanner.nextLine();
                                System.out.println("Podaj nazwisko pacjenta:");
                                String nazwisko = scanner.nextLine();
                                Pacjent.zarejestrujPacjenta(imie, nazwisko);
                                break;

                            case 2:
                                System.out.println("Podaj ID pacjenta do usunięcia:");
                                int idDoUsuniecia = Integer.parseInt(scanner.nextLine());
                                Pacjent.usunPacjenta(idDoUsuniecia);
                                break;
                            case 3:
                                System.out.println("Powrót do menu głównego.");
                                break;
                        }
// liczenie ile wyszlo za wizyte
                    case 3:
                        CennikBazowy cennik = new Cennik();

                        System.out.println("Podaj przebyte badania (wpisz numery oddzielone spacją):");
                        System.out.println("1 - Wizyta pierwszorazowa \n" + "2 - Wizyta kolejna\n" + "3 - Badanie USG \n" +
                                "4 - Badanie krwi (morfologia) \n" + "5 - Badanie krwi (inne)");

                        String input = scanner.nextLine();
                        String[] inputArray = input.split(" ");
                        int[] badania = new int[inputArray.length];

                        for (int i = 0; i < inputArray.length; i++) {
                            try {
                                badania[i] = Integer.parseInt(inputArray[i]);
                            } catch (NumberFormatException e) {
                                System.out.println("Nieprawidłowa wartość: " + inputArray[i]);
                            }
                        }

                        int koszt = cennik.obliczKoszt(badania);
                        System.out.println("Koszt za badania wynosi: " + koszt + " zł");



                    case 4:
                        System.out.println("Wyjście z programu\n");
                        System.exit(0);
                }

            }
        }
        else {
            System.out.println("Nieprawidłowe ID.");
        }
        scanner.close();
    }
}