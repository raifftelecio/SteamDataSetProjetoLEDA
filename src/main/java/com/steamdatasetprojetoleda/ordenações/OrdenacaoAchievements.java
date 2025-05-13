package com.steamdatasetprojetoleda.ordenações;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.csv.CSVRecord;

public class OrdenacaoAchievements {
    public static void processarOrdenacoesPorAchievements() throws IOException {
        Path caminhoArquivo = Paths.get("src","test","java","com","steamdatasetprojetoleda","games_formated_release_data.csv");
        CSVRecord[] arrayPorOrdem = AlgoritmosAchievements.carregarArray(caminhoArquivo);
        assert arrayPorOrdem != null;

        long tempoInicio, tempoFim, duracao, memoriaAntes, memoriaDepois, memoriaTotal;
        CSVRecord[] arrayOrdenada;

        System.out.println("Executando ordenações no campo Achievements (caso médio):\n");

        // Selection Sort
        System.out.println("Método Selection sort -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosAchievements.ordenarPorSelection(arrayPorOrdem.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosAchievements.salvarCSV("games_achievements_selectionSort_medioCaso.csv", arrayOrdenada);

        // Insertion Sort
        System.out.println("Método Insertion sort -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosAchievements.ordenarPorInsertion(arrayPorOrdem.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosAchievements.salvarCSV("games_achievements_insertionSort_medioCaso.csv", arrayOrdenada);

        // Merge Sort
        System.out.println("Método Merge sort -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosAchievements.ordenarPorMerge(arrayPorOrdem.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosAchievements.salvarCSV("games_achievements_mergeSort_medioCaso.csv", arrayOrdenada);

        // Quick Sort
        System.out.println("Método Quick sort -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosAchievements.ordenarPorQuickSort(arrayPorOrdem.clone(), 0, arrayPorOrdem.length - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosAchievements.salvarCSV("games_achievements_quickSort_medioCaso.csv", arrayOrdenada);

        // Quick Sort com Mediana de 3
        System.out.println("Método Quick sort (Mediana de 3) -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosAchievements.ordenarPorQuickSortMediana3(arrayPorOrdem.clone(), 0, arrayPorOrdem.length - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosAchievements.salvarCSV("games_achievements_quickSort_mediana_de_3_medioCaso.csv", arrayOrdenada);

        // Heap Sort
        System.out.println("Método Heap sort -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosAchievements.ordenarPorHeap(arrayPorOrdem.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosAchievements.salvarCSV("games_achievements_heapSort_medioCaso.csv", arrayOrdenada);

        // Counting Sort
        System.out.println("Método Counting sort -> Entrada desordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosAchievements.ordenarPorCouting(arrayPorOrdem.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosAchievements.salvarCSV("games_achievements_countingSort_medioCaso.csv", arrayOrdenada);


        System.out.println("\nObservação: O melhor caso será simulado utilizando um array previamente ordenado.\n");

        Path caminhoOrdenado = Paths.get("src", "test", "java", "com","steamdatasetprojetoleda","games_achievements_insertionSort_medioCaso.csv");
        CSVRecord[] arrayMelhorCaso = AlgoritmosAchievements.carregarArray(caminhoOrdenado);
        assert arrayMelhorCaso != null;

        System.out.println("Executando ordenações no campo Achievements (melhor caso - dados já ordenados):\n");

        // Selection Sort
        System.out.println("Método Selection sort -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosAchievements.ordenarPorSelection(arrayMelhorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosAchievements.salvarCSV("games_achievements_selectionSort_melhorCaso.csv", arrayOrdenada);

        // Insertion Sort
        System.out.println("Método Insertion sort -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosAchievements.ordenarPorInsertion(arrayMelhorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosAchievements.salvarCSV("games_achievements_insertionSort_melhorCaso.csv", arrayOrdenada);

        // Merge Sort
        System.out.println("Método Merge sort -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosAchievements.ordenarPorMerge(arrayMelhorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosAchievements.salvarCSV("games_achievements_mergeSort_melhorCaso.csv", arrayOrdenada);

        // Quick Sort
        System.out.println("Método Quick sort -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosAchievements.ordenarPorQuickSort(arrayMelhorCaso.clone(), 0, arrayMelhorCaso.length - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosAchievements.salvarCSV("games_achievements_quickSort_melhorCaso.csv", arrayOrdenada);

        // Quick Sort com Mediana de 3
        System.out.println("Método Quick sort (Mediana de 3) -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosAchievements.ordenarPorQuickSortMediana3(arrayMelhorCaso.clone(), 0, arrayMelhorCaso.length - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosAchievements.salvarCSV("games_achievements_quickSort_mediana_de_3_melhorCaso.csv", arrayOrdenada);

        // Heap Sort
        System.out.println("Método Heap sort -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosAchievements.ordenarPorHeap(arrayMelhorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosAchievements.salvarCSV("games_achievements_heapSort_melhorCaso.csv", arrayOrdenada);

        // Counting Sort
        System.out.println("Método Counting sort -> Entrada ordenada:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosAchievements.ordenarPorCouting(arrayMelhorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosAchievements.salvarCSV("games_achievements_countingSort_melhorCaso.csv", arrayOrdenada);

        CSVRecord[] arrayPiorCaso = AlgoritmosAchievements.carregarArray(caminhoOrdenado);
        assert arrayPiorCaso != null;
        AlgoritmosAchievements.inverterArray(arrayPiorCaso);

        System.out.println("\nExecutando ordenações no campo Achievements (pior caso - dados ordenados em ordem inversa):\n");

        // Selection Sort
        System.out.println("Método Selection sort -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosAchievements.ordenarPorSelection(arrayPiorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosAchievements.salvarCSV("games_achievements_selectionSort_piorCaso.csv", arrayOrdenada);

        // Insertion Sort
        System.out.println("Método Insertion sort -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosAchievements.ordenarPorInsertion(arrayPiorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosAchievements.salvarCSV("games_achievements_insertionSort_piorCaso.csv", arrayOrdenada);

        // Merge Sort
        System.out.println("Método Merge sort -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosAchievements.ordenarPorMerge(arrayPiorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosAchievements.salvarCSV("games_achievements_mergeSort_piorCaso.csv", arrayOrdenada);

        // Quick Sort
        System.out.println("Método Quick sort -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosAchievements.ordenarPorQuickSort(arrayPiorCaso.clone(), 0, arrayPiorCaso.length - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosAchievements.salvarCSV("games_achievements_quickSort_piorCaso.csv", arrayOrdenada);

        // Quick Sort com Mediana de 3
        System.out.println("Método Quick sort (Mediana de 3) -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosAchievements.ordenarPorQuickSortMediana3(arrayPiorCaso.clone(), 0, arrayPiorCaso.length - 1);
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosAchievements.salvarCSV("games_achievements_quickSort_mediana_de_3_piorCaso.csv", arrayOrdenada);

        // Heap Sort
        System.out.println("Método Heap sort -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosAchievements.ordenarPorHeap(arrayPiorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosAchievements.salvarCSV("games_achievements_heapSort_piorCaso.csv", arrayOrdenada);

        // Counting Sort
        System.out.println("Método Counting sort -> Entrada ordenada em ordem inversa:");
        tempoInicio = System.currentTimeMillis();
        memoriaAntes = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        arrayOrdenada = AlgoritmosAchievements.ordenarPorCouting(arrayPiorCaso.clone());
        memoriaDepois = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
        tempoFim = System.currentTimeMillis();
        duracao = tempoFim - tempoInicio;
        memoriaTotal = Math.abs((memoriaDepois - memoriaAntes) / (1024 * 1024));
        System.out.println("Tempo: " + duracao + " ms");
        System.out.println("Uso de memória: " + memoriaTotal + " MB\n");
        AlgoritmosAchievements.salvarCSV("games_achievements_countingSort_piorCaso.csv", arrayOrdenada);
    }
}
