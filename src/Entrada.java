import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class Entrada {

    static int lerInt(Scanner leia, String mensagem){
        while (true){
            System.out.print(mensagem);
            String texto = leia.nextLine();
            try {
                return Integer.parseInt(texto.trim());
            } catch (NumberFormatException e){
                System.out.println("Digite um número inteiro válido!");
            }
        }
    }

    static double lerDouble(Scanner leia, String mensagem){
        while (true){
            System.out.print(mensagem);
            String texto = leia.nextLine();
            texto = texto.replace(",",".");
            try{
                return Double.parseDouble(texto.trim());
            } catch (NumberFormatException e){
                System.out.println("Digite um número válido!");
            }
        }
    }

    static LocalDate lerData(Scanner leia, String mensagem){
        while (true){
            System.out.print(mensagem);
            String texto = leia.nextLine();
            try {
                return LocalDate.parse(texto.trim());
            } catch (DateTimeException e){
                System.out.println("Digite uma data válida!");
            }
        }
    }
}
