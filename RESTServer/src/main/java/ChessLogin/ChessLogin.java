package ChessLogin;

import loginClasses.DbClass;
import loginClasses.HashingClass;
import restShared.AccountDTO;

import javax.sql.rowset.CachedRowSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessLogin {
    private DbClass dbClass = new DbClass();

    public boolean register(String userName, String password, String email){
        try{
            String hashedPassword = HashingClass.hashPassword(password);
            Map<Integer, Object> map = new HashMap<Integer, Object>();
            map.put(1, userName);
            map.put(2, hashedPassword);
            map.put(3, email);
            String procedure = "INSERT INTO `login` (`userName`, `password`, `email`) VALUES(?,?,?)";

            dbClass.executeNonQuery(procedure, map);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public boolean login(String userName, String inputPassword){
        String procedure = "SELECT userName, password FROM [dbo].[User] WHERE userName = ?";
        if(userName.contains("@"))
            procedure = "SELECT email, password FROM [dbo].[User] WHERE email = ?";
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        map.put(1, userName);
        try{
            CachedRowSet cachedRowSet = dbClass.executeQuery(procedure, map);
            if(cachedRowSet.next()){
                return HashingClass.checkPassword(inputPassword,cachedRowSet.getString("password"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public List<AccountDTO> getAll(){
        String procedure = "SELECT * FROM [dbo].[User]";
        List<AccountDTO> accounts = new ArrayList<>();
        Map<Integer, Object> map = new HashMap<Integer, Object>();
        try{
            CachedRowSet cachedRowSet = dbClass.executeQuery(procedure, map);
            while (cachedRowSet.next()){
                accounts.add(new AccountDTO(cachedRowSet.getInt("userId"),
                        cachedRowSet.getString("userName"),
                        cachedRowSet.getString("password")));
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return accounts;
    }

    public boolean doesAccountExist() {
        //TODO
        return false;
    }


}
