package RepoLayerInterface.DBRepository;
import ModelLayer.Bet;

import java.sql.*;
import java.util.*;
public class BetDBRepository extends DBRepository<Bet> {

    public BetDBRepository(String DB_URL, String DB_USER, String DB_PASS) {
        super(DB_URL, DB_USER, DB_PASS);
    }

    @Override
    public void create(Bet bet){
        String sql = "Insert INTO"
    }

}
