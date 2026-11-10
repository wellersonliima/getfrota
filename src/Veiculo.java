import java.time.LocalDate;
import java.util.ArrayList;

public class Veiculo {
    private String placa;
    private String modelo;
    private int ano;
    private double quilometragem;
    private double kmUltimaPreventiva;
    private static final double INTERVALO_PREVENTIVA_KM = 10000;
    private SituacaoVeiculo situacao;
    private ArrayList<Abastecimento> historico = new ArrayList<>();

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

    public ArrayList<Abastecimento> getHistorico(){
        return historico;
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

    public boolean registrarAbastecimento(LocalDate data, double km, double litros, double valor){
        if(data.isAfter(LocalDate.now())){
            return false;
        }
        if(litros <= 0 || valor < 0){
            return false;
        }
        if(km < quilometragem){
            return false;
        }
        historico.add(new Abastecimento(data, km, litros, valor));
        quilometragem = km;
        return true;
    }


    public void exibirDados() {
        System.out.println(placa + " - " + modelo + " (" + ano + ")" + " - " + quilometragem + " km" + " - " + "Preventiva: "
                + preventivaVencida() + " Situação: " + situacao + " Pode ser associado: " + podeSerAssociadoARota());
    }

}