import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Veiculo carreta1 = new Veiculo("RJS9A00", "Volvo", 2026, 76000, 70000);
        Veiculo carreta2 = new Veiculo("LTA1012", "Mercedes", 2018, 82000, 70000);
        Veiculo carreta3 = new Veiculo("LUL4A26", "MarcoPolo", 2026, 30000, 28000);
        Veiculo carreta4 = new Veiculo("LPL9X20", "AUDIO", 2026, 79800, 70000);

        Motorista motorista1 = new Motorista("Jorel", "16473707794", "D", LocalDate.of(2030, 12, 10));
        Motorista motorista2 = new Motorista("Lima", "18206902063", "E", LocalDate.of(2032, 9, 5));
        Motorista motorista3 = new Motorista("Caio", "17804406934", "E", LocalDate.of(2030, 1, 1));
        Motorista motorista4 = new Motorista("José", "17800698934", "E", LocalDate.of(2024, 1, 1));

        Rota rota1 = new Rota("São Paulo", "Rio de Janeiro", 430);
        Rota rota2 = new Rota("Bahia", "Foz do Iguaçu", 930);
        Rota rota3 = new Rota("Amapá", "Rio Grande do Sul", 430);

        carreta1.registrarAbastecimento(LocalDate.of(2026, 11, 1), 76200, 50, 300);
        carreta1.registrarAbastecimento(LocalDate.of(2026, 11, 5), 76700, 60, 360);
        carreta1.registrarAbastecimento(LocalDate.of(2026, 11, 10), 77300, 70, 420);

        ArrayList<Veiculo> garagem = new ArrayList<>();
        garagem.add(carreta1);
        garagem.add(carreta2);
        garagem.add(carreta3);
        garagem.add(carreta4);

        ArrayList<Motorista> motoristas = new ArrayList<>();
        motoristas.add(motorista1);
        motoristas.add(motorista2);
        motoristas.add(motorista3);
        motoristas.add(motorista4);

        ArrayList<Rota> rotas = new ArrayList<>();
        rotas.add(rota1);
        rotas.add(rota2);
        rotas.add(rota3);

        Scanner leia = new Scanner(System.in);
        int opcaoPrincipal = -1;

        while (opcaoPrincipal != 0) {
            System.out.println("=== MENU PRINCIPAL ===");
            System.out.println("1 - Veículos");
            System.out.println("2 - Motoristas");
            System.out.println("3 - Rotas");
            System.out.println("0 - Sair");
            opcaoPrincipal = Entrada.lerInt(leia, "Digite a opção desejada: ");

            switch (opcaoPrincipal) {
                case 1:
                    MenuVeiculos.menuVeiculos(garagem, leia);
                    break;
                case 2:
                    MenuMotoristas.menuMotoristas(motoristas, leia);
                    break;
                case 3:
                    MenuRotas.menuRotas(rotas, garagem, motoristas, leia);
                    break;
                case 0:
                    System.out.println("Finalizando o programa...\n");
                    break;
                default:
                    System.out.println("Digite uma opção válida!\n");
            }
        }
    }
}