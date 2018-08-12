package pzubaha.classes.inner.start;
import pzubaha.classes.inner.models.Category;
import pzubaha.classes.inner.models.Item;
import pzubaha.classes.inner.models.User;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.*;

/**
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class TrackerDB extends Tracker implements AutoCloseable {
    /**
     * Connection instance.
     */
    private Connection connection;

    /**
     * Constructor for tracker with properties.
     * @param p properties
     * @throws SQLException exception.
     */
    public TrackerDB(Properties p) throws SQLException {
        this.connection = getConnection(p);
    }
    /**
     * Get connection to DB.
     * @return connection
     * @throws SQLNonTransientConnectionException when connection not established.
     */
    private Connection getConnection(Properties p) throws SQLException {
        Connection c;
        String drivers = p.getProperty("jdbc.drivers");
        try {
            System.out.println(drivers);
            Class.forName(drivers);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found");
            exit();
        }
        c = DriverManager.getConnection(p.getProperty("jdbc.url"),
                p.getProperty("jdbc.username"),
                p.getProperty("jdbc.password"));
        if (c == null) {
            throw new SQLNonTransientConnectionException();
        }
        return c;
    }

    public void checkDB() throws URISyntaxException, IOException {
        System.out.println("check");
        URL startSqlFileURL = getClass().getResource("/db/startDB.sql");
        String sql = new String(Files.readAllBytes(Paths.get(startSqlFileURL.toURI())), StandardCharsets.UTF_8);
        try (Statement stmnt = connection.createStatement()) {
            stmnt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws Exception {
        if (!connection.isClosed()) {
            connection.close();
        }
    }

    public void exit() {
        try {
            close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.exit(0);
    }

    @Override
    public void add(Item item) {
        String sql = "INSERT INTO items(item_name, item_desc, item_date, stat_id, cat_id, user_id) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, item.getName());
            preparedStatement.setString(2, item.getDescription());
            preparedStatement.setTimestamp(3, item.getCreate());
            preparedStatement.setInt(4, item.getStatId());
            preparedStatement.setInt(5, item.getCatId());
            preparedStatement.setInt(6, item.getUserId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            Integer id = null;
            if (resultSet.next()) {
                id = resultSet.getInt(1);
            }
            if (id != null) {
                item.setId(resultSet.getInt(1));
            } else {
                System.out.println("Not added");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static java.sql.Timestamp getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        return new java.sql.Timestamp(today.getTime());
    }

    @Override
    public User getUserByLoginAndPass(String name, String pass) {
        String sql = "SELECT user_id, user_name, user_email, user_registration FROM users WHERE user_name = ? AND user_password = ?";
        User result = null;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.setString(2, pass);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                result = new User(resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getTimestamp(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Category> getCategories() {
        List<Category> results = new ArrayList<>();
        String sql = "SELECT cat_id, cat_name FROM category;";
        try (Statement s = connection.createStatement()) {
            ResultSet res = s.executeQuery(sql);
            while (res.next()) {
                results.add(new Category(res.getInt("cat_id"), res.getString("cat_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public Item findById(int id) {
        String sql = "SELECT item_name, item_desc, item_date,"
                + " cat_id, user_id, stat_id FROM items WHERE item_id = ?";
        Item result = null;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                result = new Item(
                        id,
                        rs.getString("item_name"),
                        rs.getString("item_desc"),
                        rs.getTimestamp("item_date"),
                        rs.getInt("user_id"),
                        rs.getInt("stat_id"),
                        rs.getInt("cat_id")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> getAll() {
        String sql = "SELECT item_id, item_desc, item_name, item_date, user_id, cat_id, stat_id FROM items LIMIT 10";
        List<Item> results = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                results.add(new Item(
                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getString("item_desc"),
                        rs.getTimestamp("item_date"),
                        rs.getInt("user_id"),
                        rs.getInt("stat_id"),
                        rs.getInt("cat_id"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public List<Item> findByName(String name) {
        String sql = "SELECT item_id, item_desc, "
        + "item_name, item_date, user_id, cat_id, stat_id FROM items WHERE item_name = ? LIMIT 10";
        List<Item> results = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                results.add(new Item(
                        rs.getInt("item_id"),
                        rs.getString("item_name"),
                        rs.getString("item_desc"),
                        rs.getTimestamp("item_date"),
                        rs.getInt("user_id"),
                        rs.getInt("stat_id"),
                        rs.getInt("cat_id"))
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public void delete(Item item) {
        String sql = "DELETE FROM items WHERE item_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, item.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void editItem(Item item) {
        String sql = "UPDATE items SET item_name = ?, item_desc = ?, item_date = ?, stat_id = ?, cat_id = ? "
                + "WHERE item_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, item.getName());
            ps.setString(2, item.getDescription());
            ps.setTimestamp(3, item.getCreate());
            ps.setInt(4, item.getStatId());
            ps.setInt(5, item.getCatId());
            ps.setInt(6, item.getId());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
