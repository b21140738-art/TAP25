package tap.casos_de_uso;

import java.sql.Connection;

public class ConnectionFactory {
    public static Connection createConnection(String type){
        if(type.equals("MySQL")){
            return new MySQLConnection();
        }
        else if(type.equals("Oracle")){
            return new OracleConnection();
        }
        else if(type.equals("PostgreSQL")){
            return new PostgreSQLConnection();
        }
        return null;

    }
}
