import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class MenuMotoristas {

    static void menuMotoristas(ArrayList<Motorista> motoristas, Scanner leia) {
        int opcaoMotorista = -1;
        while (opcaoMotorista != 0) {
            System.out.println("1 - Cadastrar motorista");
            System.out.println("2 - Listar todos os motoristas");
            System.out.println("3 - Listar motoristas aptos");
            System.out.println("0 - Voltar ao menu principal");
            opcaoMotorista = Entrada.lerInt(leia, "Digite a opção desejada: ");

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
        if (Buscas.buscarMotoristaPorCpf(motoristas, cpf) != null) {
            System.out.println("CPF já encontra-se cadastrado no sistema.");
            return;
        }

        System.out.print("Categoria da CNH: ");
        String categoriaCnh = leia.nextLine();
        if(categoriaCnh.isBlank()){
            System.out.println("Campo vazio, gentileza verificar.");
            return;
        }

        LocalDate data =  Entrada.lerData(leia, "Validade da CNH (AAAA-MM-DD): ");

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
}
