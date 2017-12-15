package capstone.msd.conestoga.instantsalenotifier.database;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by CatherineChoi on 12/14/2017.
 */

public class DataBaseConnection {
    private String TAG="DataBaseConnection";
    private String className="com.mysql.jdbc.Driver";
    private String  url ="jdbc:mysql://127.0.0.1:3306/instantsalenotifier";
    private String userId ="root";
    private String password ="";
    private Connection conn = null;



    public DataBaseConnection(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        String connURL = null;
        try {
            Class.forName(className);

            conn = DriverManager.getConnection(url,userId, password);
            Log.e(TAG, conn.toString());
        } catch (ClassNotFoundException e) {
            Log.e(TAG,e.getMessage());
        } catch (SQLException e) {
            Log.e(TAG,e.getMessage());
        }
    }
      public Connection getConnection(){
          return conn;
      }
      public void close(){
          if(conn != null) try {
              conn.close();
          } catch (SQLException e) {
              Log.e(TAG, e.getMessage());
          }
      }

}
