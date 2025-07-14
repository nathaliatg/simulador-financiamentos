package modelo;

public class Casa extends Financiamento {
    private double areaConstruida;
    private double areaTerreno;

    public Casa(double valorImovel, int prazo, double taxa, double areaConstruida, double areaTerreno) {
        super(valorImovel, prazo, taxa);
        this.areaConstruida = areaConstruida;
        this.areaTerreno = areaTerreno;
    }

    @Override
    public double calcularPagamentoMensal() {
        double parcelaBase = getValorImovel() / (getPrazoFinanciamento() * 12);
        double jurosMensal = (getValorImovel() * (getTaxaJurosAnual() / 100)) / (getPrazoFinanciamento() * 12);
        double acrescimo = 80.0;

        try {
            if (acrescimo > (jurosMensal / 2)) {
                throw new AumentoMaiorDoQueJurosException("Acréscimo de R$80 é maior que a metade dos juros mensais.");
            }
        } catch (AumentoMaiorDoQueJurosException e) {
            System.out.println("⚠️ Atenção: " + e.getMessage());
            // Podemos ignorar o acréscimo se a regra for quebrada
            acrescimo = 0.0;
        }

        return parcelaBase + jurosMensal + acrescimo;
    }

    public double getAreaConstruida() {
        return areaConstruida;
    }

    public double getAreaTerreno() {
        return areaTerreno;
    }
}