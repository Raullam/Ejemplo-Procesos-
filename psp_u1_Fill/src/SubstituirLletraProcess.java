import java.io.*;

public class SubstituirLletraProcess {

    public static void main(String[] args) {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

            BufferedWriter writer = new BufferedWriter(new FileWriter("encrypted.txt"));
            String contingut = "";
            String linia;
            while ((linia = in.readLine()) != null) {  // Llegim les línies del flux d'entrada
                contingut += linia + "\n";  // Afegim cada línia llegida al contingut
            }

            // Agafem els dos últims caràcters per substituir-los
            char charAntic = contingut.charAt(contingut.length() - 3);  // El caràcter antic (penúltim)
            char charNou = contingut.charAt(contingut.length() - 2);   // El caràcter nou (últim)

            String contingutTransformant = contingut.replace(charAntic, charNou);  // Substitució del caràcter antic pel caràcter nou
            contingutTransformant = contingutTransformant.substring(0, contingutTransformant.length() - 2);  // Eliminem els dos últims caràcters

            // Escrivim el contingut (encrypted.txt)
            writer.write(contingutTransformant);

            writer.close();  // Tanquem el flux de sortida
            in.close();      // Tanquem el flux d'entrada

            System.out.println("\n Arxiu creat amb èxit.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}




