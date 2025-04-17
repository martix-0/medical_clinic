import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Sorter<T extends Comparable<T>> {
    public void sortujListe(List<T> lista) {
        Collections.sort(lista);
    }

        public static void main(String[] args) {
            List<Pacjent> pacjenci = new ArrayList<>(Pacjent.pacjentLista);

            System.out.println("Przed sortowaniem:");
            pacjenci.forEach(p -> System.out.println(p.getNazwisko() + " " + p.getImie()));

            Sorter<Pacjent> sorter = new Sorter<>();
            sorter.sortujListe(pacjenci);

            System.out.println("\nPo sortowaniu po nazwisku:");
            pacjenci.forEach(p -> System.out.println(p.getNazwisko() + " " + p.getImie()));

            pacjenci.sort(new Pacjent.PacjentComparatorPoId());

            System.out.println("\nPo sortowaniu po ID:");
            pacjenci.forEach(p -> System.out.println(p.getId() + ": " + p.getNazwisko() + " " + p.getImie()));
        }


}
