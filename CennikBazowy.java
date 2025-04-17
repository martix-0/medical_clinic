abstract class CennikBazowy {
    protected int wizytaPierwszorazowa = 100;
    protected int wizytaKolejna = 50;
    protected int badanieUSG = 50;
    protected int badanieKrwiMorfologia = 25;
    protected int badanieKrwiInne = 50;

    public abstract int obliczKoszt(int[] badania);
}

class Cennik extends CennikBazowy {
    @Override
    public int obliczKoszt(int[] badania) {
        int suma = 0;
        for (int badanie : badania) {
            switch (badanie) {
                case 1 -> suma += wizytaPierwszorazowa;
                case 2 -> suma += wizytaKolejna;
                case 3 -> suma += badanieUSG;
                case 4 -> suma += badanieKrwiMorfologia;
                case 5 -> suma += badanieKrwiInne;
                default -> System.out.println("Nieprawidłowy numer badania: " + badanie);
            }
        }
        return suma;
    }
}