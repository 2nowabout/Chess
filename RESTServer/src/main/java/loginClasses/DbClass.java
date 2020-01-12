package loginClasses;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;
import java.util.Map;


public class DbClass {

    private String url;
    private String username;
    private String password;
    private Connection connection = null;

    public DbClass() {
    }
    public void executeNonQuery(String procedure, Map map){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://192.168.0.107:3306/chess", "root", "");
            PreparedStatement statement = connection.prepareStatement(procedure);

            for (int i = 1; i < map.size() + 1; i++) {
                statement.setObject( i, map.get(i));
            }

            statement.executeUpdate();
            connection.close();

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public CachedRowSet executeQuery(String procedure, Map map) throws SQLException {
        CachedRowSet crs = RowSetProvider.newFactory().createCachedRowSet();
        ResultSet resultSet = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://192.168.0.107:3306/chess", "root", "");
            PreparedStatement statement = connection.prepareStatement(procedure);

            for (int i = 1; i <= map.size(); i++) {
                statement.setObject( i, map.get(i));
            }

            resultSet = statement.executeQuery();
            crs.populate(resultSet);
            connection.close();

        } catch(Exception e){
            e.printStackTrace();
        }
        return crs;
    }
}
