package ru.job4j.tracker;

import ru.job4j.tracker.Item;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store {
    private Connection connection;

    public SqlTracker() {
        init();
    }

    public SqlTracker(Connection connection) {
        this.connection = connection;
    }

    private void init() {
        try (InputStream input = SqlTracker.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(input);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (PreparedStatement statement = connection.prepareStatement("insert into items(name, created) values (?, ?);",
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            statement.execute();
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean result = false;
        try (PreparedStatement statement = connection.prepareStatement(
                "UPDATE items SET name = ?, created = ? WHERE id = ?;")) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            statement.setInt(3, id);
            result = statement.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void delete(int id) {
        try (PreparedStatement statement =
                connection.prepareStatement("delete from items where id = ?")) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Item> findAll() {
        List<Item> allItems = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement("select * from items;",
                Statement.RETURN_GENERATED_KEYS)) {
        statement.execute();
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                allItems.add(new Item(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getTimestamp(3).toLocalDateTime()
                ));
            }
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return allItems;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> allItems = findAll();
        List<Item> itemByName = new ArrayList<>();
        for (Item item : allItems) {
            if (key.equals(item.getName())) {
                itemByName.add(item);
            }
        }
        return itemByName;
    }

    @Override
    public Item findById(int id) {
        List<Item> allItems = findAll();
        for (Item item : allItems) {
            if (id == item.getId()) {
                return item;
            }
        }
        return null;
    }
}
