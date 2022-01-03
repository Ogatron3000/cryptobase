package com.example.cryptobase;

import java.sql.Connection;
import javax.sql.DataSource;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Layer class provides CRUD database
 * operations for the table coin in the database.
 */
public class CoinDAO {
    private DataSource dataSource;

    public CoinDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

//    Try-with-resource is used to connect instead.
//    Registering driver explicitly is not required.
//
//    private void connect() throws SQLException {
//        if (connection == null || connection.isClosed()) {
//            // optional: explicitly register driver
//            connection = dataSource.getConnection();
//        }
//    }
//
//    Try-with-resource is used methods, so we don't need to close
//    resources (connection, statement, result set) explicitly.
//
//    private void disconnect() throws SQLException {
//        if (connection != null || !connection.isClosed()) {
//            connection.close();
//        }
//    }
//
//    private void closeStatement(Statement statement) throws SQLException {
//        if (statement != null && !statement.isClosed()) {
//            statement.close();
//        }
//    }
//
//    private void closeResultSet(ResultSet resultSet) throws SQLException {
//        if (resultSet != null && !resultSet.isClosed()) {
//            resultSet.close();
//        }
//    }

    public boolean addCoin(Coin coin) throws SQLException {
        boolean coinAdded = false;

        try (Connection connection = dataSource.getConnection()) {

            String sql = "INSERT INTO coin (name, abbreviation, price, market_cap) VALUES (?, ?, ?, ?)";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, coin.getName());
                statement.setString(2, coin.getAbbreviation());
                statement.setFloat(3, coin.getPrice());
                statement.setLong(4, coin.getMarketCap());

                coinAdded = statement.executeUpdate() == 1;
            }
        }

        return coinAdded;
    }

    public List<Coin> listAllCoins() throws SQLException {
        ArrayList<Coin> coins = new ArrayList<>();

        try (Connection connection = dataSource.getConnection()) {

            String sql = "SELECT * FROM coin";

            try (Statement statement = connection.createStatement()) {

                try (ResultSet resultSet = statement.executeQuery(sql)) {

                    while (resultSet.next()) {
                        int id = resultSet.getInt("coin_id");
                        String name = resultSet.getString("name");
                        String abbreviation = resultSet.getString("abbreviation");
                        float price = resultSet.getFloat("price");
                        long marketCap = resultSet.getLong("market_cap");

                        Coin coin = new Coin(id, name, abbreviation, price, marketCap);
                        coins.add(coin);
                    }
                }
            }
        }

        return coins;
    }

    // Using only try-with-resources, meaning resources are closed automatically.
    public Coin getCoin(int id) throws SQLException {
        Coin coin = null;

        try (Connection connection = dataSource.getConnection()) {

            String sql = "SELECT * FROM coin WHERE coin_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setInt(1, id);

                try (ResultSet resultSet = statement.executeQuery()) {

                    if (resultSet.next()) {
                        String name = resultSet.getString("name");
                        String abbreviation = resultSet.getString("abbreviation");
                        float price = resultSet.getFloat("price");
                        long marketCap = resultSet.getLong("market_cap");

                        coin = new Coin(id, name, abbreviation, price, marketCap);
                    }
                }
            }
        }

        return coin;
    }

    public boolean updateCoin(Coin coin) throws SQLException {
        boolean coinUpdated = false;

        try (Connection connection = dataSource.getConnection()) {

            String sql = "UPDATE coin SET name = ?, abbreviation = ?, price = ?, market_cap = ? WHERE coin_id = ?";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, coin.getName());
                statement.setString(2, coin.getAbbreviation());
                statement.setFloat(3, coin.getPrice());
                statement.setLong(4, coin.getMarketCap());
                statement.setInt(5, coin.getId());

                coinUpdated = statement.executeUpdate() == 1;
            }
        }

        return coinUpdated;
    }

    public boolean deleteCoin(int id) throws SQLException {
        boolean coinDeleted = false;

        try (Connection connection = dataSource.getConnection()) {

            String sql = "DELETE FROM coin WHERE coin_id = ? ";

            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, id);
                coinDeleted = statement.executeUpdate() == 1;
            }
        }

        return coinDeleted;
    }
}

// Without try-with-resource:
// declare connection
// declare statement
// declare resultSet
// try:
// connect
// prepare statement
// execute statement
// finally:
// close connection
// close statement
// close resultSet