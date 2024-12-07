package utilities_qatek;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

public class SqlConnector {

    private static final Logger LOGGER = LogManager.getLogger(SqlConnector.class);

    private static final String User = ReadConfigFiles.getPropertyValues("DbUser");
    private static final String Password = ReadConfigFiles.getPropertyValues("DbPassword");
    private static final String DBName = ReadConfigFiles.getPropertyValues("DbName");
    private static final String Host = ReadConfigFiles.getPropertyValues("DbHost");
    private static final String Port = ReadConfigFiles.getPropertyValues("DbPort");

    private static final String ConnectionUrl = "jdbc:postgresql://" + Host + ":" + Port + "/" + DBName;

    private static Connection connect() throws Exception{
        LOGGER.debug(ConnectionUrl);
        Connection conn;
        try{
            conn = DriverManager.getConnection(ConnectionUrl, User, Password);
            if(conn != null)
                LOGGER.debug("Database connection is successful");
        } catch(SQLException e){
            throw new Exception(e.getMessage());
        }
        return conn;
    }

    public static ResultSet readData(String SQL) throws Exception {
        ResultSet rs;
        try{
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery(SQL);
        } catch(SQLException e){
            throw new Exception("SQL Result set exception: " + e.getMessage());
        }
        return rs;
    }



}
