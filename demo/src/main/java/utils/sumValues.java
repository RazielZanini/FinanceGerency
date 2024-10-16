package utils;

import java.util.List;

import org.bson.Document;

import controller.EntradaFinanceiraController;

public class sumValues {

    static List<Document> entradas = EntradaFinanceiraController.getEntries();

    public static double calculaValores() {
        double valorTotal = 0;

        for (Document item : entradas) {
            if ("Ganho".equals(item.getString("tipoEntrada"))) {
                valorTotal += item.getDouble("valor") != null ? item.getDouble("valor") : 0;
            } else {
                valorTotal -= item.getDouble("valor") != null ? item.getDouble("valor") : 0;
            }
        }

        return valorTotal;
    }

    public static double calculaGanhos() {
        double valorGanhos = 0;

        for (Document item : entradas) {
            if ("Ganho".equals(item.getString("tipoEntrada"))) {
                valorGanhos += item.getDouble("valor") != null ? item.getDouble("valor") : 0;
            }
        }

        return valorGanhos;
    }

    public static double calculaGastos() {
        double valorGastos = 0;

        for (Document item : entradas) {
            if ("Gasto".equals(item.getString("tipoEntrada"))) {
                valorGastos += item.getDouble("valor") != null ? item.getDouble("valor") : 0;
            }
        }

        return valorGastos;
    }

}
