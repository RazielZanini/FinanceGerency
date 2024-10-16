package config.Db;

import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDb {

    private static MongoClient mongoClient;

    // Método para criar ou retornar uma conexão já existente
    public static MongoDatabase createConnection() {
        String url = "mongodb://localhost:27017";

        if (mongoClient == null) {
            try {
                mongoClient = MongoClients.create(url);
                System.out.println("Conexão estabelecida com sucesso ao banco de dados FinanceSystem");
            } catch (MongoException e) {
                throw new DbException("Erro ao conectar ao banco de dados: " + e.getMessage());
            }
        }

        return mongoClient.getDatabase("FinanceSystem");
    }

    // Método para fechar a conexão quando necessário
    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
            System.out.println("Conexão com o banco de dados fechada.");
        }
    }
}
