package controller;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.MongoException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import config.Db.DbException;
import config.Db.MongoDb;
import model.EntradaFinanceira;

public class EntradaFinanceiraController {

    private static MongoDatabase Database = MongoDb.createConnection();

    public EntradaFinanceiraController(){
    }

    public MongoDatabase getDatabase(){
        return Database;
    }

    //Realiza a criação da Collection caso ela não exista
    private static MongoCollection<Document> Collection = Database.getCollection("FinanceEntry");


    //Método para inserir dados no banco
    public static void createEntry(EntradaFinanceira data){

        try{
            Document entry = new Document("nome", data.getNome())
            .append("classificacao", data.getClassificacao())
            .append("valor", data.getValor())
            .append("dataEntrada", data.getDataEntrada())
            .append("dataCadastro", data.getDataCriacao())
            .append("tipoEntrada", data.getTipoEntrada());

            
            Collection.insertOne(entry);

            System.out.println("Dados inseridos com sucesso!");
        } catch(MongoException e){
            throw new DbException(e.getMessage());
        }
    }

    //Método para listar as entradas registradas no banco
    public static List<Document> getEntries(){
        List<Document> entries = new ArrayList<>();

        try{
            for(Document doc : Collection.find()){
                entries.add(doc);
            }
        } catch(MongoException e){
            throw new DbException(e.getMessage());
        }

        return entries;
    }

    //Método para deletar uma Entrada, filtrando por nome
    public static void deleteEntry(String nome){
        try{
            Document query = new Document("nome", nome);
            Collection.deleteOne(query);
            System.out.println("Entrada deletada com sucesso!");
        } catch(MongoException e){
            throw new DbException(e.getMessage());
        }
    }
}
