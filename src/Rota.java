public class Rota {
    private String origem;
    private String destino;
    private double distanciaKm;
    private Veiculo veiculo;
    private Motorista motorista;
    private StatusRota statusRota;

    Rota(String origem, String destino, double distanciaKm) {
        this.origem = origem;
        this.destino = destino;
        this.distanciaKm = distanciaKm;
        this.statusRota = StatusRota.EM_PLANEJAMENTO;
    }

    public String getOrigem() {
        return origem;
    }

    public String getDestino() {
        return destino;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public StatusRota getStatusRota() {
        return statusRota;
    }

    public boolean associarRecursos(Veiculo v, Motorista m) {
        if (statusRota != StatusRota.EM_PLANEJAMENTO) {
            return false;
        }
        if (m.podeSerAssociadoARota() && v.podeSerAssociadoARota()) {
            this.veiculo = v;
            this.motorista = m;
            return true;
        }
        return false;
    }

    public boolean iniciar() {
        if (statusRota != StatusRota.EM_PLANEJAMENTO) {
            return false;
        }
        if (motorista == null || veiculo == null) {
            return false;
        }
        if (!motorista.podeSerAssociadoARota() || !veiculo.podeSerAssociadoARota()) {
            return false;
        }

        veiculo.setSituacao(SituacaoVeiculo.EM_ROTA);
        motorista.setSituacao(SituacaoMotorista.EM_VIAGEM);
        statusRota = StatusRota.EM_EXECUCAO;

        return true;
    }

    public boolean finalizar() {
        if (statusRota != StatusRota.EM_EXECUCAO) {
            return false;
        }
        veiculo.adicionarQuilometragem(distanciaKm);
        statusRota = StatusRota.CONCLUIDA;
        motorista.setSituacao(SituacaoMotorista.DISPONIVEL);
        if (veiculo.preventivaVencida()) {
            veiculo.setSituacao(SituacaoVeiculo.MANUTENCAO_PENDENTE);
        } else {
            veiculo.setSituacao(SituacaoVeiculo.DISPONIVEL);
        }
        return true;
    }

    public boolean temConsumoPrevisto(){
        return veiculo != null && veiculo.temConsumoMedio() && veiculo.calcularConsumoMedio() > 0;
    }
    public double calcularConsumoPrevisto(){
        if(!temConsumoPrevisto()){
            return 0;
        }else {
            return distanciaKm / veiculo.calcularConsumoMedio();
        }

    }

    public void exibirDados() {
        System.out.println("Origem: " + origem + " - " + "Destino: " + destino + " - " + distanciaKm + "km - " + "Status: " + statusRota);
        if (veiculo != null) {
            System.out.println("Placa: " + veiculo.getPlaca() + "\n" +
                    "Motorista: " + motorista.getNome() + "\n");
        } else {
            System.out.println("Veículo e motorista: não associados\n");
        }
        if(temConsumoPrevisto()){
            System.out.printf("Consumo previsto para a viagem: %.1f litros%n", calcularConsumoPrevisto());
        }else {
            System.out.println("Consumo previsto indisponível.");
        }
    }
}
