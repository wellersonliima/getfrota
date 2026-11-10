import java.math.BigDecimal;
import java.time.LocalDate;

public class Abastecimento {
    private LocalDate data;
    double quilometragem;
    double litros;
    double valor;

    Abastecimento(LocalDate data, double quilometragem, double litros, double valor){
        this.data = data;
        this.quilometragem = quilometragem;
        this.litros = litros;
        this.valor = valor;
    }

    public LocalDate getData(){
        return data;
    }
    public double getQuilometragem(){
        return quilometragem;
    }
    public double getLitros(){
        return litros;
    }
    public double getValor(){
        return valor;
    }

    public void exibirDados(){
        System.out.println("Data do abastecimento: " + data + " - " +
                           "Quilometragem: " + quilometragem + " - " +
                           "Litros: " + litros + " - " +
                           "Valor R$: " + valor);
    }
}
