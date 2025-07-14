package modelo;

public class Terreno extends Financiamento {
    private String tipoZona;

    public Terreno(double valorImovel, int prazo, double taxa, String tipoZona) {
        super(valorImovel, prazo, taxa);
        this.tipoZona = tipoZona;
    }

    @Override
    public double calcularPagamentoMensal() {
        double jurosMensal = getTaxaJurosAnual() / 12 / 100;
        double parcelaBase = (getValorImovel() / (getPrazoFinanciamento() * 12)) * (1 + jurosMensal);

        // Acrescenta 2% sobre o valor da parcela com juros
        return parcelaBase * 1.02;
    }

    public String getTipoZona() {
        return tipoZona;
    }
}