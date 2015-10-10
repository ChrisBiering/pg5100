package src.main.persistance;

import javax.inject.Inject;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by Chris on 07/10/15.
 */
public class DBHandler {

    @Inject
    public DBHandler() {}

    public Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/Dropbox/NITH-skoleting/3rd year/PG5100/Enterprise_project", "sa", "sa");
    }

    public void closeConnection(Connection connection) throws SQLException {
        System.out.println("Closing connection");
        connection.close();
    }
}
