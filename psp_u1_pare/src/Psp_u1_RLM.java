import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class Psp_u1_RLM {
    static String html = "";
    static String command = "java -cp ../psp_u1_Raul/psp_u1_fill/out/production/psp_u1_fill";


    public static void main(String[] args) throws IOException {
        // Començem creant 2 instancies per pasar al metode validarURL()
        Scanner scan = new Scanner(System.in);

        Menu menu = new Menu("Menú d'opcions", "Carregar pàgina Web", "Analitzar el nombre de caràcters",
                "Substituir lletra", "Llegir encrypted.txt", "Cercar paraules clau", "Crear arxiu index.html", "Executar arxiu index.html",
                "Sortir");
        validarURL(scan, menu);
    }

    private static void validarURL(Scanner scan, Menu menu) throws IOException {
        String regex = "^(http://|https://).{2,}\\.[a-zA-Z]{2,3}/?$";
        String url;

        do {
            System.out.print("Introdueix la URL de la pàgina web (començant amb http:// o https://): ");
            url = scan.nextLine();

            // Comprobem si la URL es valida
            if (!url.matches(regex)) {
                System.out.println("URL no vàlida! Assegura't que la URL comenci amb http:// o https://" );
            }
        } while (!url.matches(regex)); // Repetim el bucle fins que no s'ingresi una url valida, es podria posar alguna cosa per sortir
        crearMenu(menu, scan, url);
    }



    public static void crearMenu(Menu menu, Scanner scan, String url) throws IOException {
        int valorIngresatPerUsuari = -1;

        while (valorIngresatPerUsuari != 0) {
            valorIngresatPerUsuari = menu.esNumero(scan);

            switch (valorIngresatPerUsuari) {
                case 1:
                    CarregarPaginaProcess(url);
                    break;
                case 2:
                    ComptarCaractersProcess(url, scan, menu);
                    break;
                case 3:
                    SubstituirLletraProcess(url, scan, menu);
                    break;
                case 4:
                    LlegirFitxerEncriptatProcess(url, scan, menu);
                    break;
                case 5:
                    CercarParaulaClauProcess(url, scan, menu);
                    break;
                case 6:
                    CrearIndexHtmlProcess(url, scan, menu);
                    break;
                case 7:
                    ExecutarIndexHtmlProcess(url, scan, menu);
                    break;
                case 8:
                    System.exit(0);
                default:
                    System.out.println("\n Introdueix un valor correcte");
            }
        }
    }
    private static void CarregarPaginaProcess(String url) throws IOException {
        System.out.println(execProcesCarregarPagina(url));
    }

    private static String execProcesCarregarPagina(String url) throws IOException {
        // Cream la comanda i executem el proces fill (CarregarPaginaProcess)
        String commando = command + " CarregarPaginaProcess";
        Process proces = Runtime.getRuntime().exec(commando);

        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(proces.getOutputStream()));
             BufferedReader in = new BufferedReader(new InputStreamReader(proces.getInputStream()))) {

            out.write(url); // Enviem la URL amb un bufered al proces fill
            out.newLine();  // Afegim un salt de línia per indicar que la url està compelta
            out.flush();    // Assegurem que les dades s'envien al proces fill

            //Creem un StringBuilder per llegir les dades del html que ha llegit el proces fill
            StringBuilder htmlBuilder = new StringBuilder();
            String line;
            while ((line = in.readLine()) != null) {
                htmlBuilder.append(line).append("\n");
            }

            html = htmlBuilder.toString();

            // Si la resposta es "Pagina web incorrecta", ho tractam com un error
            if (Objects.equals(html.trim(), "Pagina web incorrecta")) {
                html = "";
                return "Pagina web incorrecta";
            }

        } catch (IOException e) {
            e.printStackTrace();
            return "Error amb el proces CarregarPaginaProcess";
        }

        return html;  // Retornam el HTML descargado Per imprimirlo per pantalla amb el metode (CarregarPaginaProcess)
    }

    private static void ComptarCaractersProcess(String url, Scanner scann, Menu menu) throws IOException {
        if (html.isEmpty()) {
            System.out.println("Necesites executar l'opción 1 abans d'ejecutar l'opció 2");
            crearMenu(menu, scann, url);
        }

        System.out.print("Introdueix el caracter que vols contar: ");
        String caracter = scann.nextLine();
        if (caracter.length() == 1) {
            System.out.println(execProcesComptarCaracters(caracter));
        } else {
            System.out.println("Introdueix nomes un caracter.");
            crearMenu(menu, scann, url);
        }
    }


    private static String execProcesComptarCaracters(String caracter) throws IOException {
        String commando = command + " ComptarCaractersProcess";
        Process proces = Runtime.getRuntime().exec(commando);

        String numVegadesCaracter = "";
            try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(proces.getOutputStream()));
                 BufferedReader in = new BufferedReader(new InputStreamReader(proces.getInputStream()))) {
            out.write(html);
            out.write(caracter);
            out.flush();
            out.close();

            String readLine;
            while ((readLine = in.readLine()) != null) {
                numVegadesCaracter += readLine + "\n";
            }
        }

        return numVegadesCaracter;
    }
    private static void SubstituirLletraProcess(String url, Scanner scann, Menu menu) throws IOException {
        if (html.isEmpty()) {
            System.out.println("Necesites executar la opció 1 abans d'executar l'opció 3");
            crearMenu(menu, scann, url);
        }

        System.out.print("\n Introdueix el carácter que vols substituir: ");
        String caracterASubs = scann.nextLine();
        if (!(caracterASubs.length() == 1)) {
            System.out.println("Introdueix només un caracter.");
            crearMenu(menu, scann, url);
        }

        System.out.print(" Introdueix el carácter per el que el vols substituir: ");
        String caracterSubs = scann.nextLine();
        if (caracterSubs.length() == 1) {
            System.out.println(execProcesSubstituirLletra(caracterSubs, caracterASubs));
        } else {
            System.out.println("Introdueix unicament un caracter.");
            crearMenu(menu, scann, url);
        }

    }

    private static String  execProcesSubstituirLletra(String lletra1, String lletra2) throws IOException {
        String commando = command + " SubstituirLletraProcess";

        Process proces = Runtime.getRuntime().exec(commando);
        String htmlModificat = "";

        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(proces.getOutputStream()));
             BufferedReader in = new BufferedReader(new InputStreamReader(proces.getInputStream()))) {

            // Escribir la entrada en el flujo de salida del proceso hijo
            out.write(html + lletra2 + lletra1);

            out.flush();       // Assegurem d'enviar totes les dades
            out.close();       // Tanquem el fluxe de sortida per indicar al fi de entrada del proces fill

            // Llegim la sortida del process fill
            String linia;
            while ((linia = in.readLine()) != null) {
                htmlModificat += linia + "\n";
            }

            // Esperem a que el proces acabi
            int exitCode = proces.waitFor();
            if (exitCode != 0) {
                System.err.println("Error al proces fill. Codi de sortida: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return "Error al executar el proces";
        }

        return htmlModificat;
    }

    private static void LlegirFitxerEncriptatProcess(String url, Scanner scann, Menu menu) throws IOException {
        //Control de si el fitxer existeix
        File archivo = new File("encrypted.txt");
        if (!archivo.exists()) {
            System.out.println("L'arxiu encrypted.txt no existeix, es necesari primer executar l'opció 4");
            crearMenu(menu, scann, url);
        }
        System.out.println(execProcesLlegiFitxerEncriptat());
    }


    private static String execProcesLlegiFitxerEncriptat() throws IOException {
        String commando = command + " LlegirFitxerEncriptatProcess";
        Process proces = Runtime.getRuntime().exec(commando);

        String contingutProcesFill = "";
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(proces.getOutputStream()));
             BufferedReader in = new BufferedReader(new InputStreamReader(proces.getInputStream()))) {
            out.close();

            String linia;
            while ((linia = in.readLine()) != null) {
                contingutProcesFill += linia + "\n";
            }
        }

        return contingutProcesFill;
    }
    private static void CercarParaulaClauProcess(String url, Scanner scann, Menu menu) throws IOException {


        if (html.isEmpty()) {
            System.out.println("Cal executar l'opció 1 abans d'executar l'opció 5");
            crearMenu(menu, scann, url);
        }
        System.out.print("\n" + "Introdueix la paraula que vols buscar: ");
        String palabra = scann.nextLine();
        if (palabra.contains(" ") || palabra.isEmpty()) {
            System.out.println("Introdueix nomes una paraula.");
            crearMenu(menu, scann, url);

        } else {
            System.out.println(execProcesCercacParaulaClau(palabra));
        }
    }

    private static String execProcesCercacParaulaClau(String paraula) throws IOException {
        String commando = command + " CercarParaulaClauProcess";
        Process proces = Runtime.getRuntime().exec(commando);

        String contingutProcesFill = "";
        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(proces.getOutputStream()));
             BufferedReader in = new BufferedReader(new InputStreamReader(proces.getInputStream()))) {
            out.write(html);
            out.write(" " + paraula);
            out.close();

            String linia;
            while ((linia = in.readLine()) != null) {
                contingutProcesFill += linia + "\n";
            }
        }

        return contingutProcesFill;
    }

    private static void CrearIndexHtmlProcess(String url, Scanner scann, Menu menu) throws IOException {
        //S'ha de controlar si el fitxer existeix
        File archivo = new File("encrypted.txt");
        if (!archivo.exists()) {
            System.out.println("L'arxiu encrypted.txt no existeix, es necesari primer executar la opció 3");
            crearMenu(menu, scann, url);
        }
        System.out.println(execProcesCrearIndex());
    }


    private static String execProcesCrearIndex() throws IOException {
        String commando = command + " CrearIndexHtmlProcess";
        Process proces = Runtime.getRuntime().exec(commando);

        String contingutProcesFill = "";
        try (BufferedReader in = new BufferedReader(new InputStreamReader(proces.getInputStream()))) {

            String linia;
            while ((linia = in.readLine()) != null) {
                contingutProcesFill += linia + "\n";
            }
        }

        return contingutProcesFill;
    }

    private static void ExecutarIndexHtmlProcess(String url, Scanner scann, Menu menu) throws IOException {
        //S'ha de controlar si el fitxer existeix
        File archivo = new File("index.html");
        if (!archivo.exists()) {
            System.out.println("L'arxiu index.html no existeix, es necesari primer executar la opció 6");
            crearMenu(menu, scann, url);
        }
        System.out.println(execProcesExecutarIndexHtml());
    }

    private static String execProcesExecutarIndexHtml() throws IOException {
        String commando = command + " ExecutarIndexHtmlProcess";
        Process proces = Runtime.getRuntime().exec(commando);

        String contingutProcesFill = "";
        try (BufferedReader in = new BufferedReader(new InputStreamReader(proces.getInputStream()))) {

            String linia;
            while ((linia = in.readLine()) != null) {
                contingutProcesFill += linia + "\n";
            }
        }

        return contingutProcesFill;
    }



}
