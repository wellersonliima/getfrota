import java.util.ArrayList;
import java.util.Scanner;

public class MenuRotas {
    static void menuRotas(ArrayList<Rota> rotas, ArrayList<Veiculo> garagem, ArrayList<Motorista> motoristas, Scanner leia) {
        int opcaoRotas = -1;
        while (opcaoRotas != 0) {
            System.out.println("1 - Criar rota");
            System.out.println("2 - Listar rotas");
            System.out.println("3 - Associar recursos a rota");
            System.out.println("4 - Iniciar rota");
            System.out.println("5 - Finalizar rota");
            System.out.println("6 - Registrar consumo realizado");
            System.out.println("0 - Voltar ao menu principal");
            opcaoRotas = Entrada.lerInt(leia, "Digite a opção desejada: ");

            switch (opcaoRotas) {
                case 1:
                    criarRota(rotas, leia);
                    break;
                case 2:
                    listarRotas(rotas);
                    break;
                case 3:
                    associarRecursos(rotas, garagem, motoristas, leia);
                    break;
                case 4:
                    iniciarRota(rotas, leia);
                    break;
                case 5:
                    finalizarRota(rotas, leia);
                    break;
                case 6:
                    registrarConsumoRealizado(rotas, leia);
                    break;
                case 0:
                    System.out.println("Retornando ao menu principal...\n");
                    break;
                default:
                    System.out.println("Digite uma opção válida!\n");
            }

        }
    }

    static void criarRota(ArrayList<Rota> rotas, Scanner leia) {
        System.out.print("Informe a origem: ");
        String origem = leia.nextLine();
        if(origem.isBlank()){
            System.out.println("Campo vazio, gentileza verificar.");
            return;
        }

        System.out.print("Informe o destino: ");
        String destino = leia.nextLine();
        if(destino.isBlank()){
            System.out.println("Campo vazio, gentileza verificar.");
            return;
        }

        double distanciaKm = Entrada.lerDouble(leia, "Informe a distância(Km): ");
        if(distanciaKm <= 0){
            System.out.println("A distância informada é menor ou igual a zero, gentileza verificar.");
            return;
        }

        Rota nova = new Rota(origem, destino, distanciaKm);
        rotas.add(nova);
        System.out.println("Rota cadastrada com sucesso!");
    }

    static void listarRotas(ArrayList<Rota> rotas) {
        System.out.println("Rotas cadastradas:");
        for (int i = 0; i < rotas.size(); i++) {
            Rota r = rotas.get(i);
            System.out.print(i + 1 + ": ");
            r.exibirDados();
        }
    }

    static void associarRecursos(ArrayList<Rota> rotas, ArrayList<Veiculo> garagem, ArrayList<Motorista> motoristas, Scanner leia) {
        Rota r = escolherRota(rotas, leia);
        if (r == null) {
            return;
        }

        System.out.print("Informe a placa: ");
        String placa = leia.nextLine();
        if(placa.isBlank()){
            System.out.println("Campo vazio, gentileza verificar.");
            return;
        }
        Veiculo v = Buscas.buscarVeiculoPorPlaca(garagem, placa);
        if (v == null) {
            System.out.println("Placa não encontrada!");
            return;
        }

        System.out.print("Informe o CPF do motorista: ");
        String cpf = leia.nextLine();
        cpf = cpf.replaceAll("[^0-9]", "");
        if(cpf.isBlank()){
            System.out.println("Campo vazio, gentileza verificar.");
            return;
        }
        if(cpf.length() != 11){
            System.out.println("Digite um cpf válido!");
            return;
        }
        Motorista m = Buscas.buscarMotoristaPorCpf(motoristas, cpf);
        if (m == null) {
            System.out.println("CPF não encontrado!");
            return;
        }

        if (r.associarRecursos(v, m)) {
            System.out.println("Recursos associados com sucesso!");
        } else {
            System.out.println("Veículo ou motorista indisponível (situação, preventiva ou CNH)");
        }

    }

    static void iniciarRota(ArrayList<Rota> rotas, Scanner leia) {
        Rota r = escolherRota(rotas, leia);
        if (r == null) {
            return;
        }
        if (r.iniciar()) {
            System.out.println("Rota iniciada com sucesso!");
        } else {
            System.out.println("Não foi possível iniciar a rota.");
        }
    }

    static void finalizarRota(ArrayList<Rota> rotas, Scanner leia) {
        Rota r = escolherRota(rotas, leia);
        if (r == null) {
            return;
        }
        if (r.finalizar()) {
            System.out.println("Rota finalizada com sucesso!");
        } else {
            System.out.println("Não foi possível finalizar a rota.");
        }
    }

    static Rota escolherRota(ArrayList<Rota> rotas, Scanner leia) {
        int numRota = Entrada.lerInt(leia, "Informe o número da rota: ");
        if (numRota < 1 || numRota > rotas.size()) {
            System.out.println("Número da rota inválido!");
            return null;
        }
        return rotas.get(numRota - 1);
    }

    static void registrarConsumoRealizado(ArrayList<Rota> rotas, Scanner leia){
        Rota r = escolherRota(rotas, leia);
        if(r == null){
            return;
        }
        double litros = Entrada.lerDouble(leia, "Informe os litros: ");

        if((r.registrarConsumoRealizado(litros))){
            System.out.printf("Consumo previsto: %.1f | Consumo realizado: %.1f | Diferença: %.1f%% \n",
                    r.getLitrosPrevistos() , r.getLitrosRealizados(), r.calcularDiferencaPercentual());
            if(r.temGap()){
                System.out.println("Gap operacional detectado!\n");
            }
        }else{
            System.out.println("Falha ao registrar o consumo.");
        }
    }

}
