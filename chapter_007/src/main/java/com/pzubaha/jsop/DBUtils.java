package com.pzubaha.jsop;

import com.pzubaha.jsop.models.Vacancy;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DBUtils {

    private Connection con;

    public void initConection(Properties p) throws SQLException {
        Connection c;
        String drivers = p.getProperty("jdbc.drivers");
        try {
            System.out.println(drivers);
            Class.forName(drivers);
        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found");
        }
        try {
            c = DriverManager.getConnection(p.getProperty("jdbc.url"),
                    p.getProperty("jdbc.username"),
                    p.getProperty("jdbc.password"));
        } catch (SQLException e) {
            e.printStackTrace();
            c = null;
        }
        if (c != null) {
            con = c;
        } else {
            throw new SQLNonTransientConnectionException();
        }
        try {
            checkTable();
        } catch (URISyntaxException e) {
            System.out.println("URI syntax exception");
        } catch (IOException e) {
            System.out.println("Cant find file checkDB.sql");
        }
    }

    private void checkTable() throws URISyntaxException, IOException {
        URL startSqlFileURL = getClass().getResource("/sql/checkDB.sql");
        String sql = new String(Files.readAllBytes(Paths.get(startSqlFileURL.toURI())), StandardCharsets.UTF_8);
        try (Statement stmnt = con.createStatement()) {
            stmnt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addAll(List<Vacancy> vacancies) {
        return false;
    }

    public List<Vacancy> getAll() {
        return new ArrayList<>();
    }
}
