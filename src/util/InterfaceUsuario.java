package util;
import java.util.Scanner;

public class InterfaceUsuario {
    private Scanner scanner = new Scanner(System.in);

    // Metodo para pedir o valor do imóvel
    public double pedirValorImovel() {
        double valor = -1;
        while (valor <= 0) {
            try {
                System.out.print("Digite o valor do imóvel (positivo): ");
                valor = Double.parseDouble(scanner.nextLine());
                if (valor <= 0) {
                    System.out.println("Erro: O valor do imóvel deve ser positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida. Digite um número com ponto se necessário (ex: 200000.50).");
            }
        }
        return valor;
    }

    // Metodo para pedir o prazo do financiamento
    public int pedirPrazoFinanciamento() {
        int prazo = -1;
        while (prazo <= 0) {
            try {
                System.out.print("Digite o prazo do financiamento em anos (positivo): ");
                prazo = Integer.parseInt(scanner.nextLine());
                if (prazo <= 0) {
                    System.out.println("Erro: O prazo deve ser positivo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida. Digite um número inteiro (ex: 20).");
            }
        }
        return prazo;
    }

    // Metodo para pedir a taxa de juros anual
    public double pedirTaxaJurosAnual() {
        double taxa = -1;
        while (taxa <= 0) {
            try {
                System.out.print("Digite a taxa de juros anual (positiva): ");
                taxa = Double.parseDouble(scanner.nextLine());
                if (taxa <= 0) {
                    System.out.println("Erro: A taxa deve ser positiva.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Erro: Entrada inválida. Digite um número decimal (ex: 7.5).");
            }
        }
        return taxa;
    }

    // Getter do Scanner para uso externo
    public Scanner getScanner() {
        return scanner;
    }
}