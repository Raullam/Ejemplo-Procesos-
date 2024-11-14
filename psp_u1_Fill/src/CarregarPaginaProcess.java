import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class CarregarPaginaProcess {
    public static void main(String[] args) {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        try {
            // Llegim l'URL de l'entrada estàndard (passada des del procés principal)
            String webURL = in.readLine();


            // Crea l'URL de l'objecte a partir de la cadena URL
            URL url = new URL(webURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");  // Utilitzem GET per obtenir el contingut de la pàgina

            // Connectar i llegir la resposta
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder html = new StringBuilder();
            String inputLine;

            while ((inputLine = reader.readLine()) != null) {
                    html.append(inputLine).append("\n");  // Concatenem les línies del HTML
            }

            // Mostrem l'HTML descarregat
            System.out.println(html);

            reader.close();
            conn.disconnect();  // Tanquem la connexió HTTP després de la lectura

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(" Página web incorrecta o no accesible.");
        }
    }
}
