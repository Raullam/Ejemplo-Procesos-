import java.io.*;

public class CercarParaulaClauProcess {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        String line = "";
        String readLine;
        while ((readLine = in.readLine()) != null) {
            line += readLine + "\n";
        }


        // Dividir el text en paraules utilitzant l'espai com a delimitador
        String[] palabras = line.split(" ");

        String ultimaPalabra = palabras[palabras.length - 1];


        // Trobem l'última aparició de la paraula
        int lastIndex = line.lastIndexOf(ultimaPalabra);

        String textoSinUltimapalabra = "";
        if (lastIndex >= 0) {
            // Eliminar l'última coincidencia
            textoSinUltimapalabra = line.substring(0, lastIndex) + line.substring(lastIndex + ultimaPalabra.length());
        }

        boolean trobada = false;

        if (textoSinUltimapalabra.contains(ultimaPalabra.trim())) {
            System.out.println("\n Paraula clau trobada en el contingut HTML.");
            trobada = true;
        }

        if (!trobada) {
            System.out.println("\n Paraula clau no trobada en el contingut HTML.");
        }

        in.close();
    }
}