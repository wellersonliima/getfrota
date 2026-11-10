import java.time.LocalDate;

public class Motorista {
    private String nome;
    private String cpf;
    private String categoriaCnh;
    private LocalDate validadeCnh;
    private SituacaoMotorista situacao = SituacaoMotorista.DISPONIVEL;

    Motorista(String nome, String cpf, String categoriaCnh, LocalDate validadeCnh) {
        this.nome = nome;
        this.cpf = cpf;
        this.categoriaCnh = categoriaCnh;
        this.validadeCnh = validadeCnh;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCategoriaCnh() {
        return categoriaCnh;
    }

    public LocalDate getValidadeCnh() {
        return validadeCnh;
    }

    public SituacaoMotorista getSituacao() {
        return situacao;
    }

    public boolean cnhVencida() {
        LocalDate hoje = LocalDate.now();
        return validadeCnh.isBefore(hoje);
    }

    public boolean podeSerAssociadoARota() {
        return situacao == SituacaoMotorista.DISPONIVEL && !cnhVencida();
    }

    public void setSituacao(SituacaoMotorista situacao) {
        this.situacao = situacao;
    }

    public void exibirDados() {
        System.out.println("Nome: " + nome + " - " + "CPF: " + cpf + " - " + "Categoria: " + categoriaCnh + " - " +
                "Validade da CNH: " + validadeCnh + " - " + "Situação: " + situacao);
    }
}