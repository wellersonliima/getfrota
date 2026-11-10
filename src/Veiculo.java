public class Veiculo {
    private String placa;
    private String modelo;
    private int ano;
    private double quilometragem;
    private double kmUltimaPreventiva;
    private static final double INTERVALO_PREVENTIVA_KM = 10000;
    private SituacaoVeiculo situacao;

    Veiculo(String placa, String modelo, int ano, double quilometragem, double kmUltimaPreventiva) {
        this.placa = placa;
        this.modelo = modelo;
        this.ano = ano;
        this.quilometragem = quilometragem;
        this.kmUltimaPreventiva = kmUltimaPreventiva;
        if(preventivaVencida()){
            this.situacao = SituacaoVeiculo.MANUTENCAO_PENDENTE;
        }else {
            this.situacao = SituacaoVeiculo.DISPONIVEL;
        }

    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public double getQuilometragem() {
        return quilometragem;
    }

    public double getKmUltimaPreventiva() {
        return kmUltimaPreventiva;
    }

    public double getKmProximaPreventiva() {
        return kmUltimaPreventiva + INTERVALO_PREVENTIVA_KM;
    }

    public boolean preventivaVencida() {
        return quilometragem >= getKmProximaPreventiva();
    }

    public SituacaoVeiculo getSituacao() {
        return situacao;
    }

    public boolean podeSerAssociadoARota() {
        return situacao == SituacaoVeiculo.DISPONIVEL && !preventivaVencida();
    }

    public void setSituacao(SituacaoVeiculo situacao){
        this.situacao = situacao;
    }

    public void adicionarQuilometragem(double distanciaKm){
        if (distanciaKm > 0){
            this.quilometragem += distanciaKm;
        }

    }

    public void exibirDados() {
        System.out.println(placa + " - " + modelo + " (" + ano + ")" + " - " + quilometragem + " km" + " - " + "Preventiva: "
                + preventivaVencida() + " Situação: " + situacao + " Pode ser associado: " + podeSerAssociadoARota());
    }

}