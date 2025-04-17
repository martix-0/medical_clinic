public class Lek {
    private String nazwa;
    private double gramatura;
    private int ilosc;

    public Lek(String nazwa, double gramatura, int ilosc) {
        this.nazwa = nazwa;
        this.gramatura = gramatura;
        this.ilosc = ilosc;
    }

    public String getNazwa() {
        return nazwa;
    }

    public double getGramatura() {
        return gramatura;
    }

    public int getIlosc() {
        return ilosc;
    }
}
