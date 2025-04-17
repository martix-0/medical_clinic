import java.util.ArrayList;
import java.util.List;

public abstract class Czlowiek {
    protected final int id;
    protected String imie;
    protected String nazwisko;

    public Czlowiek(String imie, String nazwisko, int id) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Override
    public String toString() {
        return "Pracownik{" +
                "id=" + id +
                ", imie='" + imie + '\'' +
                ", nazwisko='" + nazwisko + '\'' +
                '}';
    }
    static List<Czlowiek> wszyscyPracownicy = new ArrayList<>();
    static List<Czlowiek> wszyscyPacjenci = new ArrayList<>();


    public abstract int compareTo(Pacjent innyPacjent);
}