import java.util.Scanner;

public class Menu {

    String header;
    String[] opcions;

    public Menu(String header, String... opcions) {
        this.opcions = opcions;
        this.header = header;
    }

    public void mostraMenu() {
        System.out.println("\n\t" + header + "\n");

        int contador = 0;
        for (String opcio : opcions) {
            System.out.println("  " + ++contador + ".  " + opcio);
        }
    }


    public int esNumero(Scanner scan) {
        mostraMenu(); // Mostrem el menu
        String input = scan.nextLine(); // Obtenim l'entrada de l'usuari

        // Intentar convertir la entrada a un número
        try {
            return Integer.parseInt(input); // Retorna el numero si es un int sino mostra l'error
        } catch (NumberFormatException e) {
            System.out.println("\n Has d'ingresar un numero entre el 1 i el 8");
            return esNumero(scan); // Llama recursivamente para solicitar una nueva entrada
        }
    }





}
