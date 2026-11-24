import java.util.ArrayList;

public class Buscas {
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
}
