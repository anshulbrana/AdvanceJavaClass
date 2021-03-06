package services;

import datamodel.Passenger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.PassengerDAO;

import java.sql.*;

public class PassengerDAOTest {

    //static because It will be executed before everything
    static {
        System.setProperty("conf.file","src/test/resources/conf.properties");
    }

    private Connection cnt;

    //TO not repeat the connection every time we need it

    @BeforeEach
    public void initialize() throws SQLException {
        // TO create a table in in-memory database
        this.cnt = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "username", "password");
        PreparedStatement preparedStatement = cnt.prepareStatement("CREATE TABLE IF NOT EXISTS PASSENGERS (name VARCHAR(255), age INT, pclass INT, survived BOOLEAN, gender INT)");
        preparedStatement.execute();
    }

    @Test
    @DisplayName("When passenger is inserted with valid name, it should be found")
    public void test() throws SQLException {

        //given

        Passenger passenger = new Passenger("test", 30, 3, true, 1);

        //When
        PassengerDAO dao = new PassengerDAO();
        dao.insert(passenger);

        //Then

        Connection connection = cnt;
        ResultSet resultSet = connection.prepareStatement("select * from passengers where name = 'test'").executeQuery();
        String retrievedName = null;

        while (resultSet.next()) {
            retrievedName = resultSet.getString("name");
        }

        if (retrievedName == null) {
            throw new RuntimeException("Name test was not found, expected to be not null");
        }
    }

}
