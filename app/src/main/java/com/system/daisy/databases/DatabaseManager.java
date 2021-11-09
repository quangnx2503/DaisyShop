package com.system.daisy.databases;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    public Connection connection;
    private static final String LOG = "DEBUG";
    private  static String ip = "sql5063.site4now.net";//? ip nao d
    private static String port = "1433";
    private static String Classes = "net.sourceforge.jtds.jdbc.Driver"; // connection name nay ko phai cua sql server anh xem thay giup em voi
    private static String database = "db_a7bd8b_dbproject";
    private static String username = "db_a7bd8b_dbproject_admin";
    private static String password = "DBProject123";

    public static Connection connect() {
        Connection conn = null;
        String ConnURL = null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName(Classes);
            ConnURL = "jdbc:jtds:sqlserver://" + ip +":"+port+";"
                    + "databaseName=" + database + ";user=" + username + ";password=" + password + ";";
            conn = DriverManager.getConnection(ConnURL);
        } catch (SQLException e) {
            Log.d(LOG, e.getMessage());
            Log.i("Message : ", "SQL sai");
        } catch (ClassNotFoundException e) {
            Log.d(LOG, e.getMessage());
            Log.i("Message : ", "Class sai");
        }
        return conn;
    }
}
