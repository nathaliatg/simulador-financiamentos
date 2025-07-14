package modelo;

public class Apartamento extends Financiamento {
    private int vagasGaragem;
    private int numeroAndar;

    public Apartamento(double valorImovel, int prazo, double taxa, int vagasGaragem, int numeroAndar) {
        super(valorImovel, prazo, taxa);
        this.vagasGaragem = vagasGaragem;
        this.numeroAndar = numeroAndar;
    }

    @Override
    public double calcularPagamentoMensal() {
        double taxaMensal = (getTaxaJurosAnual() / 100) / 12;
        int meses = getPrazoFinanciamento() * 12;
        double valorPresente = getValorImovel();

        double fator = Math.pow(1 + taxaMensal, meses);

        return valorPresente * (taxaMensal * fator) / (fator - 1);
    }

    public int getNumeroVagasGaragem() {
        return vagasGaragem;
    }

    public int getNumeroAndar() {
        return numeroAndar;
    }
}