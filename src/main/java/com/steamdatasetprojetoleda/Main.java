package com.steamdatasetprojetoleda;

import java.io.IOException;

import com.steamdatasetprojetoleda.ordenações.OrdenacaoAchievements;
import com.steamdatasetprojetoleda.ordenações.OrdenacaoDate;
import com.steamdatasetprojetoleda.ordenações.OrdenacaoPrice;
import com.steamdatasetprojetoleda.transformações.TransformationsCSV;

public class Main {
    public static void main(String[] args) throws IOException {
        //Parte 1: Executar transformações
        System.out.println("Executando transformações...");
        TransformationsCSV.executeTransformacoes();
        System.out.println(" ");

        //Parte 2: Executar ordenações
        System.out.println("Executando Ordenações...");
        System.out.println(" ");
        OrdenacaoDate.processarOrdenacoesPorDate();
        OrdenacaoPrice.processarOrdenacoesPorPrice();
        OrdenacaoAchievements.processarOrdenacoesPorAchievements();
    }
}

