package com.steamdatasetprojetoleda.ordenações;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVRecord;

public class OrdenacaoPrice {
    public static void processarOrdenacoesPorPrice() throws IOException {
        Path caminhoArquivo = Paths.get("src", "test", "java", "com","steamdatasetprojetoleda","games_formated_release_data.csv");
        CSVRecord[] arrayPorOrdem = AlgoritmosPrice.carregarArray(caminhoArquivo);
        assert arrayPorOrdem != null;

        long tempoInicio, tempoFim, duracao, memoriaAntes, memoriaDepois, memoriaTotal;
        CSVRecord[] arrayOrdenada;

        System.out.println("Executando ordenações no campo Price (caso médio):\n");

        // Selection Sort
        System.out.println("Método Selection Sort -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosPrice.ordenarPorSelection(arrayPorOrdem.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_selectionSort_medioCaso.csv", arrayOrdenada);

        // Insertion Sort
        System.out.println("Método Insertion sort -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosPrice.ordenarPorInsertion(arrayPorOrdem.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_insertionSort_medioCaso.csv", arrayOrdenada);

        // Merge Sort
        System.out.println("Método Merge sort -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosPrice.ordenarPorMerge(arrayPorOrdem.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_mergeSort_medioCaso.csv", arrayOrdenada);

        // Quick Sort
        System.out.println("Método Quick sort -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosPrice.ordenarPorQuickSort(arrayPorOrdem.clone(), 0, arrayPorOrdem.length - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_quickSort_medioCaso.csv", arrayOrdenada);

        // Quick Sort com Mediana de 3
        System.out.println("Método Quick sort (Mediana de 3) -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosPrice.ordenarPorQuickSortMediana3(arrayPorOrdem.clone(), 0, arrayPorOrdem.length - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_quickSort_mediana_de_3_medioCaso.csv", arrayOrdenada);

        // Heap Sort
        System.out.println("Método Heap sort -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosPrice.ordenarPorheap(arrayPorOrdem.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_heapSort_medioCaso.csv", arrayOrdenada);



        System.out.println("\nObservação: O melhor caso será simulado utilizando um array previamente ordenado.\n");

        Path caminhoOrdenado = Paths.get("src", "test", "java", "com","steamdatasetprojetoleda","games_price_heapSort_medioCaso.csv");
        CSVRecord[] arrayMelhorCaso = AlgoritmosPrice.carregarArray(caminhoOrdenado);
        assert arrayMelhorCaso != null;

        System.out.println("Executando ordenações no campo Price (melhor caso - dados já ordenados):\n");

        // Selection Sort
        System.out.println("Método Selection sort -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosPrice.ordenarPorSelection(arrayMelhorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_selectionSort_melhorCaso.csv", arrayOrdenada);

        // Insertion Sort
        System.out.println("Método Insertion sort -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosPrice.ordenarPorInsertion(arrayMelhorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_insertionSort_melhorCaso.csv", arrayOrdenada);

        // Merge Sort
        System.out.println("Método Merge sort -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosPrice.ordenarPorMerge(arrayMelhorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_mergeSort_melhorCaso.csv", arrayOrdenada);

        // Quick Sort
        System.out.println("Método Quick sort -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosPrice.ordenarPorQuickSort(arrayMelhorCaso.clone(), 0, arrayMelhorCaso.length - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_quickSort_melhorCaso.csv", arrayOrdenada);

        // Quick Sort com Mediana de 3
        System.out.println("Método Quick sort (Mediana de 3) -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosPrice.ordenarPorQuickSortMediana3(arrayMelhorCaso.clone(), 0, arrayMelhorCaso.length - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_quickSort_mediana_de_3_melhorCaso.csv", arrayOrdenada);

        // Heap Sort
        System.out.println("Método Heap sort -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosPrice.ordenarPorheap(arrayMelhorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_heapSort_melhorCaso.csv", arrayOrdenada);


        CSVRecord[] arrayPiorCaso = AlgoritmosPrice.carregarArray(caminhoOrdenado);
        assert arrayPiorCaso != null;
        AlgoritmosPrice.inverterArray(arrayOrdenada);

        System.out.println("\nExecutando ordenações no campo Price (pior caso - dados ordenados em ordem inversa):\n");

        // Selection Sort
        System.out.println("Método Selection sort -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosPrice.ordenarPorSelection(arrayPiorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_selectionSort_piorCaso.csv", arrayOrdenada);

        // Insertion Sort
        System.out.println("Método Insertion sort -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosPrice.ordenarPorInsertion(arrayPiorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_insertionSort_piorCaso.csv", arrayOrdenada);

        // Merge Sort
        System.out.println("Método Merge sort -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosPrice.ordenarPorMerge(arrayPiorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_mergeSort_piorCaso.csv", arrayOrdenada);

        // Quick Sort
        System.out.println("Método Quick sort -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosPrice.ordenarPorQuickSort(arrayPiorCaso.clone(), 0, arrayPiorCaso.length - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_quickSort_piorCaso.csv", arrayOrdenada);

        // Quick Sort com Mediana de 3
        System.out.println("Método Quick sort (Mediana de 3) -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosPrice.ordenarPorQuickSortMediana3(arrayPiorCaso.clone(), 0, arrayPiorCaso.length - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_quickSort_mediana_de_3_piorCaso.csv", arrayOrdenada);

        // Heap Sort
        System.out.println("Método Heap sort -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosPrice.ordenarPorheap(arrayPiorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosPrice.salvarCSV("games_price_heapSort_piorCaso.csv", arrayOrdenada);
    }
}
