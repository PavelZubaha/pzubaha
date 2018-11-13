package com.pzubaha.jsop;

import com.pzubaha.jsop.models.Vacancy;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;


public class UtilsDB implements AutoCloseable {

    private static final Logger LOG = Logger.getLogger(UtilsDB.class);
    private Connection con;

    public void initConection(Properties p) throws SQLException, IOException, URISyntaxException {
        Connection c = null;
        try {
            String drivers = p.getProperty("jdbc.drivers");
            LOG.info(drivers);
            Class.forName(drivers);
            c = DriverManager.getConnection(p.getProperty("jdbc.url"),
                    p.getProperty("jdbc.username"),
                    p.getProperty("jdbc.password"));
        } catch (ClassNotFoundException e) {
            LOG.warn(e);
        }
        con = c;
        checkTable();
    }

    private void checkTable() throws URISyntaxException, IOException, SQLException {
        LOG.debug("checking table");
        URL startSqlFileURL = getClass().getResource("/sql/checkDB.sql");
        String sql = new String(Files.readAllBytes(Paths.get(startSqlFileURL.toURI())), StandardCharsets.UTF_8);
        try (Statement stmnt = con.createStatement()) {
            stmnt.executeUpdate(sql);
        }
    }

    public void addAll(List<Vacancy> vacancies) {
        LOG.debug("try to add all vacancy from list");
        String query = "INSERT INTO vacancies(id, header, content, uptime) VALUES(?, ?, ?, ?) ON CONFLICT(id) DO NOTHING";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            con.setAutoCommit(false);
            Iterator<Vacancy> it = vacancies.listIterator();
            Vacancy v;
            while (it.hasNext()) {
                v = it.next();
                ps.setInt(1, v.getId());
                ps.setString(2, v.getHeader());
                ps.setString(3, v.getDesc());
                ps.setTimestamp(4, v.getUpdateTime());
                ps.addBatch();
            }
            ps.executeBatch();
            LOG.debug("commit adding list of vacancies:");
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException e) {
            LOG.warn("Exceptrion while add list of vacancies:", e);
            try {
                con.rollback();
            } catch (SQLException e1) {
                LOG.info("Exception while rolback");
            }
        }
    }
//todo
    public List<Vacancy> getAll() {
        return new ArrayList<>();
    }

    public Timestamp getLastUpdate() {
        String query = "SELECT MAX(uptime) FROM vacancies;";
        Timestamp ts = Timestamp.valueOf(LocalDateTime.now().minusMonths(1));
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()) {
                LOG.trace("resultset has next element");
                if (resultSet.getTimestamp(1) != null && ts.before(resultSet.getTimestamp(1))) {
                    ts = resultSet.getTimestamp(1);
                }
            }
        } catch (SQLException e) {
            LOG.warn(e);
        }
        return ts;
    }

    @Override
    public void close() throws Exception {
        LOG.debug("closing connection");
        if (con != null) {
            con.close();
        }
    }
}
