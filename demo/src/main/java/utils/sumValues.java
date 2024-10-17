package utils;

import java.util.List;

import org.bson.Document;

import controller.EntradaFinanceiraController;

public class sumValues {


    public static double calculaValorTotal() {

         List<Document> entradas = EntradaFinanceiraController.getEntries();

        double valorTotal = 0;

        for (Document item : entradas) {
            if ("Ganho".equals(item.getString("tipoEntrada"))) {
                valorTotal += item.getDouble("valor");
            } else if("Gasto".equals(item.getString("tipoEntrada"))){
                valorTotal -= item.getDouble("valor");
            }
        }

        return valorTotal;
    }

    public static double calculaValorGanho(){

        List<Document> entradas = EntradaFinanceiraController.getEntries();

        double valorGanhos = 0;

        for(Document item : entradas){
            if("Ganho".equals(item.getString("tipoEntrada"))){
                valorGanhos += item.getDouble("valor");
            }
        }

        return valorGanhos;
    }

    public static double caluclaValorGasto(){

        List<Document> entradas = EntradaFinanceiraController.getEntries();

        double valorGastos = 0;

        for(Document item : entradas){
            if("Gasto".equals(item.getString("tipoEntrada"))){
                valorGastos += item.getDouble("valor");
            }
        }

        return valorGastos;
    }

}
