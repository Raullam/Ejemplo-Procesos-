import java.io.*;

public class LlegirFitxerEncriptatProcess {


    public static void main(String[] args) throws IOException {
        File fitxer = new File("encrypted.txt");

        BufferedReader br = new BufferedReader(new FileReader(fitxer));

        String encrypted = "";
        String linia;
        while ((linia = br.readLine()) != null) {
            encrypted += linia + "\n";
        }
        System.out.println(encrypted);

        br.close();
    }


}