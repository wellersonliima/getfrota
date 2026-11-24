import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuVeiculos {

    static void menuVeiculos(ArrayList<Veiculo> garagem, Scanner leia) {
        int opcaoVeiculo = -1;
        while (opcaoVeiculo != 0) {
            System.out.println("1 - Cadastrar veículo");
            System.out.println("2 - Listar todos");
            System.out.println("3 - Listar os aptos para rota");
            System.out.println("4 - Registrar abastecimento");
            System.out.println("5 - Listar abastecimento do veículo");
            System.out.println("6 - Consultar consumo médio");
            System.out.println("0 - Voltar ao menu principal");
            opcaoVeiculo = Entrada.lerInt(leia, "Digite a opção desejada: ");

            switch (opcaoVeiculo) {
                case 1:
                    cadastrarVeiculo(garagem, leia);
                    break;
                case 2:
                    listarVeiculos(garagem);
                    break;
                case 3:
                    listarVeiculosAptos(garagem);
                    break;
                case 4:
                    registrarAbastecimento(garagem, leia);
                    break;
                case 5:
                    listarAbastecimentos(garagem, leia);
                    break;
                case 6:
                    consultarConsumo(garagem, leia);
                    break;
                case 0:
                    System.out.println("Retornando ao menu principal...\n");
                    break;
                default:
                    System.out.println("Digite uma opção válida!\n");
            }
        }
    }

    static void cadastrarVeiculo(ArrayList<Veiculo> garagem, Scanner leia) {
        System.out.print("Placa: ");
        String placa = leia.nextLine();
        if(placa.isBlank()){
            System.out.println("Campo vazio, gentileza verificar.");
            return;
        }
        if (Buscas.buscarVeiculoPorPlaca(garagem, placa) != null) {
            System.out.println("Placa já encontra-se cadastrada no sistema.");
            return;
        }

        System.out.print("Modelo: ");
        String modelo = leia.nextLine();
        if(modelo.isBlank()){
            System.out.println("Campo vazio, gentileza verificar.");
            return;
        }

        int ano = Entrada.lerInt(leia,"Ano de fabricação: ");
        if(ano < 2000){
            System.out.println("Ano de fabricação inferior ao limite estabelecido nas normas da empresa.");
            return;
        }
        if(ano > LocalDate.now().getYear() + 1){
            System.out.println("Ano superior ao ano vigente, gentileza verificar.");
            return;
        }

        double quilometragem = Entrada.lerDouble(leia, "Quilometragem atual: ");
        if(quilometragem < 0){
            System.out.println("A quilometragem informada é menor que zero, favor verificar.");
            return;
        }

        double kmUltimaPreventiva = Entrada.lerDouble(leia, "Informe a quilometragem da última preventiva: ");
        if(kmUltimaPreventiva > quilometragem){
            System.out.println("Última preventiva informada é maior que a quilometragem atual, gentileza verificar.");
            return;
        }
        if(kmUltimaPreventiva < 0){
            System.out.println("Última preventiva informada é menor que zero, gentileza verificar.");
            return;
        }

        Veiculo novo = new Veiculo(placa, modelo, ano, quilometragem, kmUltimaPreventiva);
        garagem.add(novo);
        System.out.println("Veículo cadastrado com sucesso!");
    }

    static void listarVeiculos(ArrayList<Veiculo> garagem) {
        System.out.println("Lista dos veículos cadastrados:\n");
        for (int i = 0; i < garagem.size(); i++) {
            Veiculo v = garagem.get(i);
            v.exibirDados();
        }
    }

    static void listarVeiculosAptos(ArrayList<Veiculo> garagem) {
        System.out.println("Veículos aptos para rota:\n");
        for (int i = 0; i < garagem.size(); i++) {
            Veiculo v = garagem.get(i);
            if (v.podeSerAssociadoARota()) {
                v.exibirDados();
            }
        }
    }

    static void registrarAbastecimento(ArrayList<Veiculo> garagem, Scanner leia){
        System.out.print("Informe a placa: ");
        String placa = leia.nextLine();
        if(placa.isBlank()){
            System.out.println("Campo vazio, gentileza verificar.");
            return;
        }
        Veiculo v = Buscas.buscarVeiculoPorPlaca(garagem, placa);
        if(v == null){
            System.out.println("Placa não encontrada!");
            return;
        }

        LocalDate data =  Entrada.lerData(leia, "Informe a data do abastecimento: ");

        double quilometragem = Entrada.lerDouble(leia, "Informe a quilometragem: ");

        double litros = Entrada.lerDouble(leia, "Informe a quantidade de litros abastecidos: ");

        double valor = Entrada.lerDouble(leia, "Informe o valor R$: ");

        if(v.registrarAbastecimento(data, quilometragem, litros, valor)) {
            System.out.println("Abastecimento registrado com sucesso!");
        }else {
            System.out.println("Não foi possível registrar o abastecimento, verifique os dados inseridos.");
        }

    }

    static void listarAbastecimentos(ArrayList<Veiculo> garagem, Scanner leia){
        System.out.print("Informe a placa: ");
        String placa = leia.nextLine();
        if(placa.isBlank()){
            System.out.println("Campo vazio, gentileza verificar.");
            return;
        }
        Veiculo v = Buscas.buscarVeiculoPorPlaca(garagem, placa);
        if(v == null){
            System.out.println("Placa não encontrada!");
            return;
        }
        ArrayList<Abastecimento> historico = v.getHistorico();
        if(historico.isEmpty()){
            System.out.println("Nenhum histórico de abastecimento para o veículo solicitado.");
            return;
        }
        for (int i = 0; i < historico.size(); i++) {
            historico.get(i).exibirDados();

        }
    }

    static void consultarConsumo(ArrayList<Veiculo> garagem, Scanner leia){
        System.out.print("Informe a placa: ");
        String placa = leia.nextLine();
        if(placa.isBlank()){
            System.out.println("Campo vazio, gentileza verificar.");
            return;
        }
        Veiculo v = Buscas.buscarVeiculoPorPlaca(garagem, placa);
        if (v == null){
            System.out.println("Placa não encontrada!");
            return;
        }
        if(!v.temConsumoMedio()){
            System.out.println("Histórico insuficiente (mínimo de 2 abastecimentos)");
        }else {
            System.out.printf("Consumo médio: %.2f km/l%n", v.calcularConsumoMedio());
        }

    }
}
