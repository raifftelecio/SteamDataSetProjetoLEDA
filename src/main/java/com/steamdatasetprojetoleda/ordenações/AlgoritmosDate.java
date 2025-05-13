package com.steamdatasetprojetoleda.ordenações;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

public class AlgoritmosDate {
    private static final DateTimeFormatter FORMATO_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static CSVRecord[] ordenarPorSelection(CSVRecord[] registros) {
        for (int i = 0; i < registros.length - 1; i++) {
            int indiceMinimo = i;
            for (int j = i + 1; j < registros.length; j++) {
                LocalDate dataAtual = LocalDate.parse(registros[j].get(2), FORMATO_DATA);
                LocalDate dataMinima = LocalDate.parse(registros[indiceMinimo].get(2), FORMATO_DATA);
                if (dataAtual.isBefore(dataMinima)) {
                    indiceMinimo = j;
                }
            }
            CSVRecord aux = registros[i];
            registros[i] = registros[indiceMinimo];
            registros[indiceMinimo] = aux;
        }
        return registros;
    }

    public static CSVRecord[] ordenarPorInsertion(CSVRecord[] registros) {
        for (int i = 1; i < registros.length; i++) {
            CSVRecord atual = registros[i];
            LocalDate dataAtual = LocalDate.parse(atual.get(2), FORMATO_DATA);
            int j = i - 1;

            while (j >= 0 && LocalDate.parse(registros[j].get(2), FORMATO_DATA).isAfter(dataAtual)) {
                registros[j + 1] = registros[j];
                j--;
            }
            registros[j + 1] = atual;
        }
        return registros;
    }

    public static CSVRecord[] ordenarPorMerge(CSVRecord[] registros) {
        if (registros.length <= 1) return registros;

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
            LocalDate dataEsquerda = LocalDate.parse(esquerda[i].get(2), FORMATO_DATA);
            LocalDate dataDireita = LocalDate.parse(direita[j].get(2), FORMATO_DATA);
            if (dataEsquerda.isBefore(dataDireita) || dataEsquerda.equals(dataDireita)) {
                destino[k++] = esquerda[i++];
            } else {
                destino[k++] = direita[j++];
            }
        }
        while (i < esquerda.length) destino[k++] = esquerda[i++];
        while (j < direita.length) destino[k++] = direita[j++];
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

    private static int dividir(CSVRecord[] registros, int inicio, int fim) {
        LocalDate pivo = LocalDate.parse(registros[fim].get(2), FORMATO_DATA);
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            LocalDate dataAtual = LocalDate.parse(registros[j].get(2), FORMATO_DATA);
            if (!dataAtual.isAfter(pivo)) {
                i++;
                CSVRecord temp = registros[i];
                registros[i] = registros[j];
                registros[j] = temp;
            }
        }
        CSVRecord temp = registros[i + 1];
        registros[i + 1] = registros[fim];
        registros[fim] = temp;
        return i + 1;
    }

    public static CSVRecord[] ordenarPorQuickSortMediana3(CSVRecord[] registros, int inicio, int fim) {
        if (inicio < fim) {
            int pivo = dividirMediana(registros, inicio, fim);
            ordenarPorQuickSortMediana3(registros, inicio, pivo - 1);
            ordenarPorQuickSortMediana3(registros, pivo + 1, fim);
        }
        return registros;
    }

    private static int dividirMediana(CSVRecord[] registros, int inicio, int fim) {
        int meio = (inicio + fim) / 2;
        int indiceMediana = encontrarMediana(registros, inicio, meio, fim);

        CSVRecord temp = registros[indiceMediana];
        registros[indiceMediana] = registros[fim];
        registros[fim] = temp;

        LocalDate pivo = LocalDate.parse(registros[fim].get(2), FORMATO_DATA);
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            LocalDate atual = LocalDate.parse(registros[j].get(2), FORMATO_DATA);
            if (!atual.isAfter(pivo)) {
                i++;
                CSVRecord troca = registros[i];
                registros[i] = registros[j];
                registros[j] = troca;
            }
        }

        CSVRecord troca = registros[i + 1];
        registros[i + 1] = registros[fim];
        registros[fim] = troca;

        return i + 1;
    }

    private static int encontrarMediana(CSVRecord[] registros, int i, int j, int k) {
        LocalDate a = LocalDate.parse(registros[i].get(2), FORMATO_DATA);
        LocalDate b = LocalDate.parse(registros[j].get(2), FORMATO_DATA);
        LocalDate c = LocalDate.parse(registros[k].get(2), FORMATO_DATA);

        if ((a.isBefore(b) && b.isBefore(c)) || (c.isBefore(b) && b.isBefore(a))) return j;
        if ((b.isBefore(a) && a.isBefore(c)) || (c.isBefore(a) && a.isBefore(b))) return i;
        return k;
    }

    public static CSVRecord[] ordenarPorHeap(CSVRecord[] registros) {
        int n = registros.length;
        for (int i = n / 2 - 1; i >= 0; i--) ajustarHeap(registros, n, i);
        for (int i = n - 1; i > 0; i--) {
            CSVRecord temp = registros[0];
            registros[0] = registros[i];
            registros[i] = temp;
            ajustarHeap(registros, i, 0);
        }
        return registros;
    }

    private static void ajustarHeap(CSVRecord[] registros, int n, int i) {
        int maior = i;
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;

        if (esq < n && LocalDate.parse(registros[esq].get(2), FORMATO_DATA).isAfter(LocalDate.parse(registros[maior].get(2), FORMATO_DATA))) {
            maior = esq;
        }

        if (dir < n && LocalDate.parse(registros[dir].get(2), FORMATO_DATA).isAfter(LocalDate.parse(registros[maior].get(2), FORMATO_DATA))) {
            maior = dir;
        }

        if (maior != i) {
            CSVRecord troca = registros[i];
            registros[i] = registros[maior];
            registros[maior] = troca;

            ajustarHeap(registros, n, maior);
        }
    }

    public static CSVRecord[] carregarArray(Path caminhoCSV) throws IOException {
        try (
            Reader leitor = new FileReader(caminhoCSV.toFile());
            CSVParser parser = new CSVParser(leitor, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        ) {
            int tamanho = 0;
            for (CSVRecord ignorar : parser) {
                tamanho++;
            }

            leitor.close();
            CSVParser novoParser = new CSVParser(new FileReader(caminhoCSV.toFile()), CSVFormat.DEFAULT.withFirstRecordAsHeader());
            CSVRecord[] registros = new CSVRecord[tamanho];

            int i = 0;
            for (CSVRecord r : novoParser) {
                registros[i++] = r;
            }

            novoParser.close();
            return registros;
        }
    }

    public static void salvarCSV(String nomeArquivo, CSVRecord[] registros) throws IOException {
        Path caminhoSaida = Paths.get("src", "test", "java", "com", "steamdatasetprojetoleda");
        File arquivoSaida = new File(caminhoSaida.toFile(), nomeArquivo);
        File cabecalhoOrigem = new File(caminhoSaida.toFile(), "games_formated_release_data.csv");

        try (
            Writer escritor = new FileWriter(arquivoSaida);
            CSVPrinter printer = new CSVPrinter(escritor, CSVFormat.DEFAULT);
            Reader leitorCabecalho = new FileReader(cabecalhoOrigem);
            CSVParser parserCabecalho = new CSVParser(leitorCabecalho, CSVFormat.DEFAULT.withFirstRecordAsHeader());
        ) {
            printer.printRecord(parserCabecalho.getHeaderMap().keySet());
            for (CSVRecord registro : registros) {
                printer.printRecord(registro);
            }
        }
    }

    public static void inverterArray(CSVRecord[] registros) {
        int inicio = 0;
        int fim = registros.length - 1;
        while (inicio < fim) {
            CSVRecord aux = registros[inicio];
            registros[inicio] = registros[fim];
            registros[fim] = aux;
            inicio++;
            fim--;
        }
    }
}

