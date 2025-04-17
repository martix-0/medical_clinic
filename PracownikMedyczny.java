import java.util.HashMap;
import java.util.Map;


public class PracownikMedyczny extends Czlowiek {
    static int pracownikMedyczny = 0;

    public static Map< Integer, PracownikMedyczny > pracownicyMedyczni = new HashMap<>();

    public PracownikMedyczny(String imie, String nazwisko) {
        super(imie, nazwisko, przydzielId());
        wszyscyPracownicy.add(this);
        pracownicyMedyczni.put(pracownikMedyczny, this);
    }
    private static int przydzielId() {
        if (pracownikMedyczny > 200) {
            throw new IllegalStateException("Nie można utworzyć więcej niż 200 profili dla pracowników medycznych. Skontaktuj się z administracją.");
        }
        return ++pracownikMedyczny;
    }

    public static void wypiszPracownikow() {
        System.out.println("Pracownicy medyczni:");
        for (PracownikMedyczny pracownik : pracownicyMedyczni.values()) {
            System.out.println("ID: " + pracownik.getId() + ", Imię: " + pracownik.getImie() + ", Nazwisko: " + pracownik.getNazwisko());
        }
    }
     static void zainicjalizujPracownikowMedycznych() {

        new PracownikMedyczny("Adam", "Mickiewicz");
        new PracownikMedyczny("Juliusz", "Słowacki");
        new PracownikMedyczny("Zofia", "Nałkowska");
        new PracownikMedyczny("Bolesław", "Prus");

        wypiszPracownikow();
    }


    @Override
    public int compareTo(Pacjent innyPacjent) {
        return 0;
    }
}