import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class ExecutarIndexHtmlProcess {
    public static void main(String[] args) {
        try {
            File htmlFile = new File("index.html");
            if (htmlFile.exists()) {
                Desktop.getDesktop().browse(htmlFile.toURI());
                System.out.println(" Arxiu obert correctament");
            } else {
                System.out.println(" L'arxiu HTML no existeix.");
            }
        } catch (IOException e) {
            System.out.println(" Error al obrir l'arxiu al navegador: " + e.getMessage());
        }
    }
}
