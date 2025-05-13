package com.steamdatasetprojetoleda.ordenações;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class AlgoritmosPrice {
    public static CSVRecord[] ordenarPorSelection(CSVRecord[] registros) {
        for (int i = 0; i < registros.length - 1; i++) {
            int indiceMinimo = i;
            for (int j = i + 1; j < registros.length; j++) {
                if (Double.parseDouble(registros[indiceMinimo].get(6)) > Double.parseDouble(registros[j].get(6))) {
                    indiceMinimo = j;
                }
            }
            CSVRecord aux = registros[indiceMinimo];
            registros[indiceMinimo] = registros[i];
            registros[i] = aux;
        }
        return registros;
    }
    
    public static CSVRecord[] ordenarPorInsertion(CSVRecord[] registros) {
        for (int i = 1; i < registros.length; i++) {
            CSVRecord atual = registros[i];
            double precoAtual = Double.parseDouble(atual.get(6));
            int j = i - 1;
            while (j >= 0 && Double.parseDouble(registros[j].get(6)) > precoAtual) {
                registros[j + 1] = registros[j];
                j--;
            }
            registros[j + 1] = atual;
        }
        return registros;
    }
    
    public static CSVRecord[] ordenarPorMerge(CSVRecord[] registros) {
        if (registros.length <= 1) {
            return registros;
        }
    
        int meio = registros.length / 2;
        CSVRecord[] esquerda = new CSVRecord[meio];
        CSVRecord[] direita = new CSVRecord[registros.length - meio];
    
        System.arraycopy(registros, 0, esquerda, 0, meio);
        System.arraycopy(registros, meio, direita, 0, registros.length - meio);
    
        ordenarPorMerge(esquerda);
        ordenarPorMerge(direita);
        combinar(registros, esquerda, direita);
        return registros;
    }
    
    private static void combinar(CSVRecord[] destino, CSVRecord[] esquerda, CSVRecord[] direita) {
        int i = 0, j = 0, k = 0;
    
        while (i < esquerda.length && j < direita.length) {
            double precoEsq = Double.parseDouble(esquerda[i].get(6));
            double precoDir = Double.parseDouble(direita[j].get(6));
            if (precoEsq <= precoDir) {
                destino[k++] = esquerda[i++];
            } else {
                destino[k++] = direita[j++];
            }
        }
    
        while (i < esquerda.length) {
            destino[k++] = esquerda[i++];
        }
        while (j < direita.length) {
            destino[k++] = direita[j++];
        }
    }
    
    public static int dividir(CSVRecord[] registros, int inicio, int fim) {
        double pivo = Double.parseDouble(registros[fim].get(6));
        int limite = inicio - 1;
    
        for (int atual = inicio; atual < fim; atual++) {
            double precoAtual = Double.parseDouble(registros[atual].get(6));
            if (precoAtual <= pivo) {
                limite++;
                CSVRecord temp = registros[limite];
                registros[limite] = registros[atual];
                registros[atual] = temp;
            }
        }
    
        CSVRecord temp = registros[limite + 1];
        registros[limite + 1] = registros[fim];
        registros[fim] = temp;
    
        return limite + 1;
    }
    
    public static CSVRecord[] ordenarPorQuickSort(CSVRecord[] registros, int inicio, int fim) {
        while (inicio < fim) {
            int posPivo = dividir(registros, inicio, fim);
            if (posPivo - inicio < fim - posPivo) {
                ordenarPorQuickSort(registros, inicio, posPivo - 1);
                inicio = posPivo + 1;
            } else {
                ordenarPorQuickSort(registros, posPivo + 1, fim);
                fim = posPivo - 1;
            }
        }
        return registros;
    }
    
    public static int encontrarMediana(CSVRecord[] dados, int ini, int meio, int fim) {
        double precoIni = Double.parseDouble(dados[ini].get(6));
        double precoMeio = Double.parseDouble(dados[meio].get(6));
        double precoFim = Double.parseDouble(dados[fim].get(6));
    
        if ((precoIni < precoMeio && precoMeio < precoFim) || (precoFim < precoMeio && precoMeio < precoIni)) {
            return meio;
        } else if ((precoMeio < precoIni && precoIni < precoFim) || (precoFim < precoIni && precoIni < precoMeio)) {
            return ini;
        } else {
            return fim;
        }
    }
    
    public static int dividirMediana(CSVRecord[] dados, int ini, int fim) {
        int meio = (ini + fim) / 2;
        int mediana = encontrarMediana(dados, ini, meio, fim);
    
        CSVRecord temp = dados[mediana];
        dados[mediana] = dados[fim];
        dados[fim] = temp;
    
        double pivo = Double.parseDouble(dados[fim].get(6));
        int limite = ini - 1;
    
        for (int i = ini; i < fim; i++) {
            double precoAtual = Double.parseDouble(dados[i].get(6));
            if (precoAtual <= pivo) {
                limite++;
                CSVRecord aux = dados[limite];
                dados[limite] = dados[i];
                dados[i] = aux;
            }
        }
    
        CSVRecord aux = dados[limite + 1];
        dados[limite + 1] = dados[fim];
        dados[fim] = aux;
    
        return limite + 1;
    }
    
    public static CSVRecord[] ordenarPorQuickSortMediana3(CSVRecord[] registros, int ini, int fim) {
        while (ini < fim) {
            int posicaoPivo = dividirMediana(registros, ini, fim);
            if (posicaoPivo - ini < fim - posicaoPivo) {
                ordenarPorQuickSortMediana3(registros, ini, posicaoPivo - 1);
                ini = posicaoPivo + 1;
            } else {
                ordenarPorQuickSortMediana3(registros, posicaoPivo + 1, fim);
                fim = posicaoPivo - 1;
            }
        }
        return registros;
    }
    
    public static CSVRecord[] ordenarPorheap(CSVRecord[] registros) {
        int tamanho = registros.length;
    
        for (int i = tamanho / 2 - 1; i >= 0; i--) {
            ajustarHeap(registros, tamanho, i);
        }
    
        for (int i = tamanho - 1; i > 0; i--) {
            CSVRecord temp = registros[i];
            registros[i] = registros[0];
            registros[0] = temp;
    
            ajustarHeap(registros, i, 0);
        }
        return registros;
    }
    
    private static void ajustarHeap(CSVRecord[] registros, int tamanho, int raiz) {
        int maior = raiz;
        int esquerda = 2 * raiz + 1;
        int direita = 2 * raiz + 2;
    
        if (esquerda < tamanho && Double.parseDouble(registros[esquerda].get(6)) > Double.parseDouble(registros[maior].get(6))) {
            maior = esquerda;
        }
    
        if (direita < tamanho && Double.parseDouble(registros[direita].get(6)) > Double.parseDouble(registros[maior].get(6))) {
            maior = direita;
        }
    
        if (maior != raiz) {
            CSVRecord temp = registros[raiz];
            registros[raiz] = registros[maior];
            registros[maior] = temp;
    
            ajustarHeap(registros, tamanho, maior);
        }
    }
    
    public static CSVRecord[] carregarArray(Path caminhoCSV) throws IOException {
        File arquivo = caminhoCSV.toFile();

        int tamanho = 0;
        try (
            Reader leitorContagem = new FileReader(arquivo);
            CSVParser analisadorContagem = new CSVParser(leitorContagem, CSVFormat.DEFAULT.withHeader());
        ) {
            for (CSVRecord ignorar : analisadorContagem) {
                tamanho++;
            }
        }

        CSVRecord[] registros = new CSVRecord[tamanho];
        try (
            Reader leitor = new FileReader(arquivo);
            CSVParser analisador = new CSVParser(leitor, CSVFormat.DEFAULT.withHeader());
        ) {
            int i = 0;
            for (CSVRecord registro : analisador) {
                registros[i++] = registro;
            }
        }

        return registros;
}


    
    public static void salvarCSV(String nomeArquivo, CSVRecord[] registros) throws IOException {
        Path caminhoEntrada = Paths.get("src", "test", "java", "com","steamdatasetprojetoleda");
        File arquivoSaida = new File(caminhoEntrada.toString(), nomeArquivo);
        File arquivoCabecalho = new File(caminhoEntrada.toString(), "games_formated_release_data.csv");
    
        try (
            Writer escritor = new FileWriter(arquivoSaida);
            CSVPrinter impressora = new CSVPrinter(escritor, CSVFormat.DEFAULT);
            Reader leitorCabecalho = new FileReader(arquivoCabecalho);
            CSVParser parserCabecalho = new CSVParser(leitorCabecalho, CSVFormat.DEFAULT.withFirstRecordAsHeader())
        ) {
            impressora.printRecord(parserCabecalho.getHeaderMap().keySet());
            for (CSVRecord registro : registros) {
                impressora.printRecord(registro);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public static void inverterArray(CSVRecord[] vetor) {
        int inicio = 0;
        int fim = vetor.length - 1;
    
        while (inicio < fim) {
            CSVRecord temp = vetor[inicio];
            vetor[inicio] = vetor[fim];
            vetor[fim] = temp;
            inicio++;
            fim--;
        }
    }
}
