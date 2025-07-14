package main;

import util.InterfaceUsuario;
import java.util.ArrayList;
import modelo.Financiamento;
import modelo.Casa;
import modelo.Apartamento;
import modelo.Terreno;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        InterfaceUsuario interfaceUsuario = new InterfaceUsuario();

        ArrayList<Financiamento> listaFinanciamentos = new ArrayList<>();

        // 1 financiamento via input do usuário (CASA)
        System.out.println("\n--- Cadastro de uma CASA ---");
        double valorImovel = interfaceUsuario.pedirValorImovel();
        int prazo = interfaceUsuario.pedirPrazoFinanciamento();
        double taxa = interfaceUsuario.pedirTaxaJurosAnual();

        System.out.print("Digite a área construída (m²): ");
        double areaConstruida = interfaceUsuario.getScanner().nextDouble();

        System.out.print("Digite a área do terreno (m²): ");
        double areaTerreno = interfaceUsuario.getScanner().nextDouble();

        Casa financiamentoUsuario = new Casa(valorImovel, prazo, taxa, areaConstruida, areaTerreno);
        listaFinanciamentos.add(financiamentoUsuario);

        // Outros financiamentos fixos
        listaFinanciamentos.add(new Casa(300000, 20, 7.5, 120, 200));
        listaFinanciamentos.add(new Casa(250000, 15, 8.0, 100, 180));

        listaFinanciamentos.add(new Apartamento(400000, 20, 6.5, 2, 5));
        listaFinanciamentos.add(new Apartamento(350000, 25, 6.0, 1, 10));

        listaFinanciamentos.add(new Terreno(150000, 10, 9.0, "Residencial"));

        // Mostrar resumo e totais
        double totalImoveis = 0;
        double totalFinanciamentos = 0;

        System.out.println("\n--- Resumo dos Financiamentos ---");
        for (int i = 0; i < listaFinanciamentos.size(); i++) {
            Financiamento f = listaFinanciamentos.get(i);

            double valorImovelFin = f.getValorImovel();
            double valorFinanciamento = f.calcularTotalPagamento();

            System.out.printf("Financiamento %d – valor do imóvel: R$ %.2f, valor do financiamento: R$ %.2f%n",
                    i + 1, valorImovelFin, valorFinanciamento);

            totalImoveis += valorImovelFin;
            totalFinanciamentos += valorFinanciamento;
        }

        System.out.printf("\nTotal de todos os imóveis: R$ %.2f%n", totalImoveis);
        System.out.printf("Total de todos os financiamentos: R$ %.2f%n", totalFinanciamentos);

        // === SALVAR EM ARQUIVO TEXTO ===
        // Caminho do arquivo texto
        String caminhoTexto = "financiamentos_formatado.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoTexto))) {
            // Escrever cabeçalho
            writer.write("Valor Imóvel;Valor Financiamento;Taxa Juros Anual (%);Prazo (anos);Atributos Específicos");
            writer.newLine();

            for (Financiamento f : listaFinanciamentos) {
                // Formatar os valores com 2 casas decimais
                String linha = String.format("%.2f;%.2f;%.2f;%d",
                        f.getValorImovel(),
                        f.calcularTotalPagamento(),
                        f.getTaxaJurosAnual(),
                        f.getPrazoFinanciamento());

                // Adicionar atributos específicos de cada tipo
                if (f instanceof Casa casa) {
                    linha += String.format(";Área Construída: %.2f m²;Área do Terreno: %.2f m²",
                            casa.getAreaConstruida(), casa.getAreaTerreno());
                } else if (f instanceof Apartamento apto) {
                    linha += String.format(";Vagas Garagem: %d;Andar: %d",
                            apto.getNumeroVagasGaragem(), apto.getNumeroAndar());
                } else if (f instanceof Terreno terreno) {
                    linha += String.format(";Tipo de Zona: %s", terreno.getTipoZona());
                }

                writer.write(linha);
                writer.newLine();
            }
            System.out.println("\nArquivo de texto formatado salvo com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo de texto formatado: " + e.getMessage());
        }

        // === LER E MOSTRAR O ARQUIVO TEXTO ===
        System.out.println("\n--- Conteúdo do arquivo de texto ---");
        try (BufferedReader reader = new BufferedReader(new FileReader(caminhoTexto))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println(linha);
            }
        } catch (IOException e) {
            System.err.println("Erro ao ler arquivo de texto: " + e.getMessage());
        }

        // === SALVAR ARRAYLIST SERIALIZADO ===
        String caminhoSerializado = "financiamentos.ser";
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminhoSerializado))) {
            oos.writeObject(listaFinanciamentos);
            System.out.println("\nArquivo serializado salvo com sucesso!");
        } catch (IOException e) {
            System.err.println("Erro ao salvar arquivo serializado: " + e.getMessage());
        }

        // === LER O ARQUIVO SERIALIZADO ===
        System.out.println("\n--- Verificando leitura do arquivo serializado ---");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminhoSerializado))) {
            Object obj = ois.readObject();
            if (obj instanceof ArrayList<?> listaLida) {
                for (Object o : listaLida) {
                    if (o instanceof Financiamento f) {
                        System.out.printf("Imóvel: R$ %.2f | Financiamento: R$ %.2f%n",
                                f.getValorImovel(), f.calcularTotalPagamento());
                    }
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Erro ao ler arquivo serializado: " + e.getMessage());
        }
    }
}