import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PracownikRecepcji extends Czlowiek implements Main.Pracownik {
    static int pracownikRecepcji = 200;
    public static Map< Integer, PracownikRecepcji> pracownicyRecepcji = new HashMap<>();

    public PracownikRecepcji(String imie, String nazwisko) {
        super(imie, nazwisko, przydzielId());
        wszyscyPracownicy.add(this);
        pracownicyRecepcji.put(pracownikRecepcji, this);
    }

    public static int przydzielId() {
        return ++pracownikRecepcji;
    }

    public static void wypiszPracownikow() {
        System.out.println("Pracownicy recepcji:");
        for (PracownikRecepcji pracownik : pracownicyRecepcji.values()) {
            System.out.println("ID: " + pracownik.getId() + ", Imię: " + pracownik.getImie() + ", Nazwisko: " + pracownik.getNazwisko());
        }
    }

    static void ListaPracownikow() {

        new PracownikRecepcji("Tytus", "Bomba");
        new PracownikRecepcji("Izabela", "Łęcka");
        new PracownikRecepcji("Jan Paweł", "Adamczewski");
        new PracownikRecepcji("Urszulka", "Kochanowska");

        wypiszPracownikow();
    }


    @Override
    public int compareTo(Pacjent innyPacjent) {
        return 0;
    }
}