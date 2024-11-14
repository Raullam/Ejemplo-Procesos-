import java.io.*;

public class CrearIndexHtmlProcess {
    public static void main(String[] args) {
        try {
            String rutaArchivoTXT = "encrypted.txt";
            String rutaArchivoHTML = "index.html";
            String contenido = "";

            // Llegim l'arxiu encrypted.txt
            try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivoTXT))) {
                String linea;
                while ((linea = br.readLine()) != null) {
                    contenido += linea + "\n";
                }
            }

            // Cerca l'inici i el final de la secció <body> dins del contingut
            int inicioBody = contenido.indexOf("<body");
            int finBody = contenido.indexOf("</body>");

            // Si es troben amb èxit les etiquetes <body i </body>, extreu el contingut entre elles
            if (inicioBody != -1 && finBody != -1) {
                String contenidoBody = contenido.substring(inicioBody + 6, finBody);// +6 per saltar l'etiqueta <body

                // NOU HTML
                String contenidoHTML = "<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "    <title>Raül Lama</title>\n" +
                        "</head>\n" +
                        "<body" +
                        contenidoBody +
                        "</body>\n" +
                        "</html>";


                BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivoHTML));

                // Obrim un nou fluxe i escribim el contenigut HTML a l'arxiu
                writer.write(contenidoHTML);

                // Tanquem el fluxe
                writer.close();

                System.out.println("\n" + "L'arxiu index.html ha estat creat amb èxit.");
            } else {
                System.out.println(" No es va trobar el contingut del cos a l'arxiu .txt.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}






