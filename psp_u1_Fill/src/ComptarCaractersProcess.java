import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ComptarCaractersProcess {

    public static void main(String[] args) {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            String html = "";
            String linia;
            while ((linia = in.readLine()) != null) {
                html += linia;
            }

            in.close();
            char caracterIntroduitPerUsuari = html.charAt(html.length() - 1);

            long count = html.chars().filter(ch -> ch == caracterIntroduitPerUsuari).count();
            System.out.println("\n El caracter s'ha trobat " + count + " vegades");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
