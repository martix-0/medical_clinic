import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;


public class Pacjent extends Czlowiek implements Comparable<Pacjent> {
    static int pacjent = 1001;
    public static List<Pacjent> pacjentLista = new ArrayList<>();
    private List<Lek> listaLekow = new ArrayList<>();

    public Pacjent(String imie, String nazwisko) {
        super(imie, nazwisko, przydzielId());
        pacjentLista.add(this);
        wszyscyPacjenci.add(this);
    }

    public static void wypiszPacjentow() {
        System.out.println("Pacjenci przychodni:");
        for (Pacjent pacjent : Pacjent.pacjentLista) {
            System.out.println("ID: " + pacjent.getId() + ", Imię: " + pacjent.getImie() + ", Nazwisko: " + pacjent.getNazwisko());
        }
    }

    public static void zarejestrujPacjenta(String imie, String nazwisko) {
        Pacjent nowyPacjent = new Pacjent(imie, nazwisko);
        System.out.println("Zarejestrowano nowego pacjenta: " + nowyPacjent.getImie() + " " + nowyPacjent.getNazwisko() + " (ID: " + nowyPacjent.getId() + ")");
    }

    public static void usunPacjenta(int id) {
        Pacjent pacjentDoUsuniecia = znajdzPacjenta(id);
        if (pacjentDoUsuniecia != null) {
            pacjentLista.remove(pacjentDoUsuniecia);
            System.out.println("Pacjent o ID " + id + " został usunięty.");
        } else {
            System.out.println("Nie znaleziono pacjenta o podanym ID.");
        }
    }

    public static int przydzielId() {
        return pacjent++;
    }

    public static void listaPacjentow(){
        new Pacjent("Michał", "Głuś");
        new Pacjent("Sebastian", "Bąk");
        new Pacjent("Jadwiga", "Hymel");
        new Pacjent("Rudolf", "Czerwononosy");

        wypiszPacjentow();
    }

    public static Pacjent znajdzPacjenta(int id) {
        for (Pacjent pacjent : Pacjent.pacjentLista) {
            if (pacjent.getId() == id) {
                return pacjent;
            }
        }
        return null;
    }

//planowanie wizyt
    private LocalDate dataNastepnejWizyty;

    public void umowNastepnaWizyte(String data) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            this.dataNastepnejWizyty = LocalDate.parse(data, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Błędny format daty. Użyj formatu DD-MM-YYYY.");
        }
    }

    public String sprawdzNastepnaWizyte() {
        if (dataNastepnejWizyty != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            return dataNastepnejWizyty.format(formatter);
        }
        return "Brak zaplanowanej wizyty";
    }

    public void anulujWizyte() {
        if (this.dataNastepnejWizyty != null) {
            this.dataNastepnejWizyty = null;
            System.out.println("Wizyta została anulowana.");
        } else {
            System.out.println("Brak wizyty do anulowania.");
        }
    }

// compare to jest wymagane przez nia
    public int compareTo(Pacjent innyPacjent) {
        return this.getNazwisko().compareTo(innyPacjent.getNazwisko());
    }

    public static void sortujPacjentowPoNazwisku() {
        Collections.sort(pacjentLista);
        System.out.println("Pacjenci posortowani po nazwisku:");
        for (Pacjent p : pacjentLista) {
            System.out.println(p.getNazwisko() + ", " + p.getImie());
        }
    }

    public static void sortujPacjentowPoId() {
        pacjentLista.sort(new PacjentComparatorPoId());
        System.out.println("Pacjenci posortowani po ID:");
        for (Pacjent p : pacjentLista) {
            System.out.println("ID: " + p.getId() + ", " + p.getNazwisko() + ", " + p.getImie());
        }
    }

    public static void sortujPacjentowPoImieniu() {
        pacjentLista.sort(new PacjentComparatorPoImieniu());
        System.out.println("Pacjenci posortowani po imieniu:");
        for (Pacjent p : pacjentLista) {
            System.out.println(p.getImie() + " " + p.getNazwisko());
        }
    }

    public void dodajLek( Lek lek ){
        listaLekow.add(lek);
    }

    static class PacjentComparatorPoId implements Comparator<Pacjent> {
        @Override
        public int compare(Pacjent p1, Pacjent p2) {
            return Integer.compare(p1.getId(), p2.getId());
        }
    }

    static class PacjentComparatorPoImieniu implements Comparator<Pacjent> {
        @Override
        public int compare(Pacjent p1, Pacjent p2) {
            return p1.getImie().compareTo(p2.getImie());
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Pacjent)) return false;
        Pacjent pacjent = (Pacjent) obj;
        return this.getId() == pacjent.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getImie(), getNazwisko());
    }

}