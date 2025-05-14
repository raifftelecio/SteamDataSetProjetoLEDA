package com.steamdatasetprojetoleda.ordenações;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;

import org.apache.commons.csv.CSVRecord;

public class OrdenacaoDate {

    public static void processarOrdenacoesPorDate() throws IOException {
        Path caminhoArquivo = Paths.get("src", "test", "java", "com", "steamdatasetprojetoleda", "games_formated_release_data.csv");
        CSVRecord[] arrayPorOrdem = AlgoritmosDate.carregarArray(caminhoArquivo);
        assert arrayPorOrdem != null;
    
        long tempoInicio, tempoFim, duracao, memoriaAntes, memoriaDepois, memoriaTotal;
        CSVRecord[] arrayOrdenada;
    
        System.out.println("Executando ordenações no campo Release Date (caso médio):\n");
    
        // Selection Sort
        System.out.println("Método Selection Sort -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosDate.ordenarPorSelection(arrayPorOrdem.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_selectionSort_medioCaso.csv", arrayOrdenada);
    
        // Insertion Sort
        System.out.println("Método Insertion Sort -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosDate.ordenarPorInsertion(arrayPorOrdem.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_insertionSort_medioCaso.csv", arrayOrdenada);
    
        // Merge Sort
        System.out.println("Método Merge Sort -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosDate.ordenarPorMerge(arrayPorOrdem.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_mergeSort_medioCaso.csv", arrayOrdenada);
    
        // Quick Sort
        System.out.println("Método Quick Sort -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosDate.ordenarPorQuickSort(arrayPorOrdem.clone(), 0, arrayPorOrdem.length - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_quickSort_medioCaso.csv", arrayOrdenada);
    
        // Quick Sort com Mediana de 3
        System.out.println("Método Quick Sort (Mediana de 3) -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosDate.ordenarPorQuickSortMediana3(arrayPorOrdem.clone(), 0, arrayPorOrdem.length - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_quickSort_mediana_de_3_medioCaso.csv", arrayOrdenada);
    
        // Heap Sort
        System.out.println("Método Heap Sort -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosDate.ordenarPorHeap(arrayPorOrdem.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_heapSort_medioCaso.csv", arrayOrdenada);
    
        System.out.println("\nObservação: O melhor caso será simulado utilizando um array previamente ordenado.\n");
    
        Path caminhoOrdenado = Paths.get("src", "test", "java", "com", "steamdatasetprojetoleda", "games_release_date_mergeSort_medioCaso.csv");
        CSVRecord[] arrayMelhorCaso = AlgoritmosDate.carregarArray(caminhoOrdenado);
        assert arrayMelhorCaso != null;

        System.out.println("Executando ordenações no campo Release Date (melhor caso - dados já ordenados):\n");

        // Selection Sort
        System.out.println("Método Selection Sort -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosDate.ordenarPorSelection(arrayMelhorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_selectionSort_melhorCaso.csv", arrayOrdenada);

        // Insertion Sort
        System.out.println("Método Insertion Sort -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosDate.ordenarPorInsertion(arrayMelhorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_insertionSort_melhorCaso.csv", arrayOrdenada);

        // Merge Sort
        System.out.println("Método Merge Sort -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosDate.ordenarPorMerge(arrayMelhorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_mergeSort_melhorCaso.csv", arrayOrdenada);

        // Quick Sort
        System.out.println("Método Quick Sort -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosDate.ordenarPorQuickSort(arrayMelhorCaso.clone(), 0, arrayMelhorCaso.length - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_quickSort_melhorCaso.csv", arrayOrdenada); 

        // Quick Sort com Mediana de 3
        System.out.println("Método Quick Sort (Mediana de 3) -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosDate.ordenarPorQuickSortMediana3(arrayMelhorCaso.clone(), 0, arrayMelhorCaso.length - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_quickSort_mediana_de_3_melhorCaso.csv", arrayOrdenada);

        // Heap Sort
        System.out.println("Método Heap Sort -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosDate.ordenarPorHeap(arrayMelhorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_heapSort_melhorCaso.csv", arrayOrdenada);

        CSVRecord[] arrayPiorCaso = arrayMelhorCaso.clone();
        Collections.reverse(Arrays.asList(arrayPiorCaso));

        System.out.println("\nExecutando ordenações no campo Release Date (pior caso - dados ordenados em ordem inversa):\n");

        // Selection Sort
        System.out.println("Método Selection Sort -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosDate.ordenarPorSelection(arrayPiorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_selectionSort_piorCaso.csv", arrayOrdenada);

        // Insertion Sort
        System.out.println("Método Insertion Sort -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosDate.ordenarPorInsertion(arrayPiorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_insertionSort_piorCaso.csv", arrayOrdenada);*/

        // Merge Sort
        System.out.println("Método Merge Sort -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosDate.ordenarPorMerge(arrayPiorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_mergeSort_piorCaso.csv", arrayOrdenada);

        // Quick Sort
        System.out.println("Método Quick Sort -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosDate.ordenarPorQuickSort(arrayPiorCaso.clone(), 0, arrayPiorCaso.length - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_quickSort_piorCaso.csv", arrayOrdenada);

        // Quick Sort com Mediana de 3
        System.out.println("Método Quick Sort (Mediana de 3) -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosDate.ordenarPorQuickSortMediana3(arrayPiorCaso.clone(), 0, arrayPiorCaso.length - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_quickSort_mediana_de_3_piorCaso.csv", arrayOrdenada);

        // Heap Sort
        System.out.println("Método Heap Sort -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosDate.ordenarPorHeap(arrayPiorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosDate.salvarCSV("games_release_date_heapSort_piorCaso.csv", arrayOrdenada);
    }
}
