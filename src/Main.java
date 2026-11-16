import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

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

    static void menuVeiculos(ArrayList<Veiculo> garagem, Scanner leia) {
        int opcaoVeiculo = -1;
        while (opcaoVeiculo != 0) {
            System.out.println("1 - Cadastrar veículo");
            System.out.println("2 - Listar todos");
            System.out.println("3 - Listar os aptos para rota");
            System.out.println("4 - Registrar abastecimento");
            System.out.println("0 - Voltar ao menu principal");
            opcaoVeiculo = lerInt(leia, "Digite a opção desejada: ");

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
        if (buscarVeiculoPorPlaca(garagem, placa) != null) {
            System.out.println("Placa já encontra-se cadastrada no sistema.");
            return;
        }

        System.out.print("Modelo: ");
        String modelo = leia.nextLine();
        if(modelo.isBlank()){
            System.out.println("Campo vazio, gentileza verificar.");
            return;
        }

        int ano = lerInt(leia,"Ano de fabricação: ");
        if(ano < 2000){
            System.out.println("Ano de fabricação inferior ao limite estabelecido nas normas da empresa.");
            return;
        }
        if(ano > LocalDate.now().getYear() + 1){
            System.out.println("Ano superior ao ano vigente, gentileza verificar.");
            return;
        }

        double quilometragem = lerDouble(leia, "Quilometragem atual: ");
        if(quilometragem < 0){
            System.out.println("A quilometragem informada é menor que zero, favor verificar.");
            return;
        }

        double kmUltimaPreventiva = lerDouble(leia, "Informe a quilometragem da última preventiva: ");
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
        Veiculo v = buscarVeiculoPorPlaca(garagem, placa);
        if(v == null){
            System.out.println("Placa não encontrada!");
            return;
        }

        LocalDate data =  lerData(leia, "Informe a data do abastecimento: ");

        double quilometragem = lerDouble(leia, "Informe a quilometragem: ");

        double litros = lerDouble(leia, "Informe a quantidade de litros abastecidos: ");

        double valor = lerDouble(leia, "Informe o valor R$: ");

        v.registrarAbastecimento(data, quilometragem, litros, valor);
        System.out.println("Abastecimento registrado com sucesso!");


    }
    static void listarAbastecimentos(ArrayList<Veiculo> garagem, ArrayList<Abastecimento> historico, Scanner leia){
        System.out.print("Informe a placa: ");
        String placa = leia.nextLine();
        if(placa.isBlank()){
            System.out.println("Campo vazio, gentileza verificar.");
            return;
        }
        Veiculo v = buscarVeiculoPorPlaca(garagem, placa);
        if(v == null){
            System.out.println("Placa não encontrada!");
            return;
        }

        for (int i = 0; i < garagem.size(); i++) {
            v = garagem.get(i);

        }
    }

    static void menuMotoristas(ArrayList<Motorista> motoristas, Scanner leia) {
        int opcaoMotorista = -1;
        while (opcaoMotorista != 0) {
            System.out.println("1 - Cadastrar motorista");
            System.out.println("2 - Listar todos os motoristas");
            System.out.println("3 - Listar motoristas aptos");
            System.out.println("0 - Voltar ao menu principal");
            opcaoMotorista = lerInt(leia, "Digite a opção desejada: ");

            switch (opcaoMotorista) {
                case 1:
                    cadastrarMotorista(motoristas, leia);
                    break;
                case 2:
                    listarMotoristas(motoristas);
                    break;
                case 3:
                    listarMotoristasAptos(motoristas);
                    break;
                case 0:
                    System.out.println("Retornando ao menu principal...\n");
                    break;
                default:
                    System.out.println("Digite uma opção válida!\n");
            }
        }
    }
    static void cadastrarMotorista(ArrayList<Motorista> motoristas, Scanner leia) {
        System.out.print("Nome: ");
        String nome = leia.nextLine();
        if(nome.isBlank()){
            System.out.println("Campo vazio, gentileza verificar.");
            return;
        }

        System.out.print("CPF: ");
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
        if (buscarMotoristaPorCpf(motoristas, cpf) != null) {
            System.out.println("CPF já encontra-se cadastrado no sistema.");
            return;
        }

        System.out.print("Categoria da CNH: ");
        String categoriaCnh = leia.nextLine();
        if(categoriaCnh.isBlank()){
            System.out.println("Campo vazio, gentileza verificar.");
            return;
        }

        LocalDate data =  lerData(leia, "Validade da CNH (AAAA-MM-DD): ");

        Motorista novo = new Motorista(nome, cpf, categoriaCnh, data);
        motoristas.add(novo);
        System.out.println("Motorista cadastrado com sucesso!");
    }
    static void listarMotoristas(ArrayList<Motorista> motoristas) {
        for (int i = 0; i < motoristas.size(); i++) {
            Motorista m = motoristas.get(i);
            m.exibirDados();
        }
    }
    static void listarMotoristasAptos(ArrayList<Motorista> motoristas) {
        System.out.println("Motoristas aptos para rota: ");
        for (int i = 0; i < motoristas.size(); i++) {
            Motorista m = motoristas.get(i);
            if (m.podeSerAssociadoARota()) {
                m.exibirDados();
            }
        }
    }

    static void menuRotas(ArrayList<Rota> rotas, ArrayList<Veiculo> garagem, ArrayList<Motorista> motoristas, Scanner leia) {
        int opcaoRotas = -1;
        while (opcaoRotas != 0) {
            System.out.println("1 - Criar rota");
            System.out.println("2 - Listar rotas");
            System.out.println("3 - Associar recursos a rota");
            System.out.println("4 - Iniciar rota");
            System.out.println("5 - Finalizar rota");
            System.out.println("0 - Voltar ao menu principal");
            opcaoRotas = lerInt(leia, "Digite a opção desejada: ");

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

        double distanciaKm = lerDouble(leia, "Informe a distância(Km): ");
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
        Veiculo v = buscarVeiculoPorPlaca(garagem, placa);
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
        Motorista m = buscarMotoristaPorCpf(motoristas, cpf);
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
        int numRota = lerInt(leia, "Informe o número da rota: ");
        if (numRota < 1 || numRota > rotas.size()) {
            System.out.println("Número da rota inválido!");
            return null;
        }
        return rotas.get(numRota - 1);
    }
    static Veiculo buscarVeiculoPorPlaca(ArrayList<Veiculo> garagem, String placa) {
        for (int i = 0; i < garagem.size(); i++) {
            Veiculo v = garagem.get(i);
            if (v.getPlaca().equalsIgnoreCase(placa)) {
                return v;
            }
        }
        return null;
    }
    static Motorista buscarMotoristaPorCpf(ArrayList<Motorista> motoristas, String cpf) {
        for (int i = 0; i < motoristas.size(); i++) {
            Motorista m = motoristas.get(i);
            if (m.getCpf().equalsIgnoreCase(cpf)) {
                return m;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        Veiculo carreta1 = new Veiculo("RJS9A00", "Volvo", 2026, 76000, 70000);
        Veiculo carreta2 = new Veiculo("LTA1012", "Mercedes", 2018, 82000, 70000);
        Veiculo carreta3 = new Veiculo("LUL4A26", "MarcoPolo", 2026, 30000, 28000);
        Veiculo carreta4 = new Veiculo("LPL9X20", "AUDIO", 2026, 79800, 70000);

        Motorista motorista1 = new Motorista("Jorel", "16473707794", "D", LocalDate.of(2026, 12, 10));
        Motorista motorista2 = new Motorista("Lima", "18206902063", "E", LocalDate.of(2032, 9, 5));
        Motorista motorista3 = new Motorista("Caio", "17804406934", "E", LocalDate.of(2030, 1, 1));
        Motorista motorista4 = new Motorista("José", "17800698934", "E", LocalDate.of(2024, 1, 1));

        Rota rota1 = new Rota("São Paulo", "Rio de Janeiro", 430);
        Rota rota2 = new Rota("Bahia", "Foz do Iguaçu", 930);
        Rota rota3 = new Rota("Amapá", "Rio Grande do Sul", 430);

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

        carreta1.registrarAbastecimento(LocalDate.of(2026, 11, 8),80100,50,300);

        carreta1.exibirDados();

        Scanner leia = new Scanner(System.in);
        int opcaoPrincipal = -1;

        while (opcaoPrincipal != 0) {
            System.out.println("=== MENU PRINCIPAL ===");
            System.out.println("1 - Veículos");
            System.out.println("2 - Motoristas");
            System.out.println("3 - Rotas");
            System.out.println("0 - Sair");
            opcaoPrincipal = lerInt(leia,"Digite a opção desejada: ");

            switch (opcaoPrincipal) {
                case 1:
                    menuVeiculos(garagem, leia);
                    break;
                case 2:
                    menuMotoristas(motoristas, leia);
                    break;
                case 3:
                    menuRotas(rotas, garagem, motoristas, leia);
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
