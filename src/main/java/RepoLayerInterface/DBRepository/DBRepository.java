package RepoLayerInterface.DBRepository;

import java.sql.*;
import RepoLayerInterface.*;

public abstract class DBRepository<T> implements repo<T>, AutoCloseable {

    protected final Connection conn;

    public DBRepository(String DB_URL, String DB_USER, String DB_PASS) {
        try{
            conn = DriverManager.getConnection(DB_URL,DB_USER,DB_PASS);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() throws Exception{
        conn.close();
    }

}
