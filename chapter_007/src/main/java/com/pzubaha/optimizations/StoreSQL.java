package com.pzubaha.optimizations;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * SQL JDBC.
 * Task 20459. XML XSLT JDBC Optimization.
 *
 * Task:
 * 1.Create sqlite db. Create schema(delete data if present).
 * 1.1 Create table entry {field integer;}
 * 2. Insert N elements(1..N).
 * 3. Generate target.xml from schema Using JAXB:
 * <entries>
 * <entry>
 * <field>значение поля field</field>
 * </entry>
 * ...
 * <entry>
 * <field>значение поля field</field>
 * </entry>
 * </entries>
 * 4. Create dest.xml using XSLT:
 * <?xml version="1.0" encoding="UTF-8"?>
 * <entries>
 * <entry href="1"/>
 * <entry href="2"/>
 * </entries>
 * 5. Parsing dest.xml and output sum of values.
 * 6. Time of working with N (~1000000) should be not bigger than 5min
 *
 * StoreSQL.
 * Class proves connection to sqlite using JDBC, creating DB and inserting some values.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class StoreSQL {
    private final String diverName = "org.sqlite.JDBC";
    private final String connectionURL =
            String.format("jdbc:sqlite:%s",
            Paths.get("chapter_007\\src\\main\\resources\\db\\db.db")
                    .toAbsolutePath()
                    .toString());
    private Connection con = null;

    public void initDB() {
        try {
            Class.forName(diverName);
        } catch (ClassNotFoundException e) {
            System.out.println("Can't get class. No driver found");
            e.printStackTrace();
            return;
        }
        try {
            con = DriverManager.getConnection(connectionURL);
            if (con != null) {
                DatabaseMetaData meta = con.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println("Can't get connection. Incorrect URL");
            e.printStackTrace();
        }
    }

    public void generate(int n) {
        Paths.get("chapter_007\\src\\main\\resources\\db\\db.db").toAbsolutePath().toString();
        try {
            con.setAutoCommit(false);
            Statement statement = con.createStatement();
            statement.execute("DROP TABLE 'entry'");
            con.commit();
            statement.execute("CREATE TABLE IF NOT EXISTS 'entry' (field INTEGER)");
            con.commit();
            insert(n, statement);
            System.out.println("Table 'entry' created or exists!");
        } catch (SQLException e) {
            System.out.println("Can't create check statement!");
            if (con != null) {
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            e.printStackTrace();
        }
    }

    private void insert(int n, Statement statement) throws SQLException {
        StringBuilder builder = new StringBuilder(1024);
        builder.append("INSERT INTO 'entry' (field) VALUES ");
        for (int i = 1; i <= n; i++) {
            builder.append(String.format("(%d), ", i));
        }
        builder.delete(builder.lastIndexOf(","), builder.length()).append(";");
        statement.execute(builder.toString());
        con.commit();
    }

    private List<Entry> readEntries() {
        List<Entry> entries = new ArrayList<>(128);
        String query = "SELECT field FROM entry";
        Statement stmt;
        try {
            stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                entries.add(new Entry(resultSet.getInt(1)));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entries;
    }

    public static void main(String[] args) {
        File file = new File(Paths.get("chapter_007\\src\\main\\resources\\db\\target.xml").toAbsolutePath().toString());
        File dest = new File(Paths.get("chapter_007\\src\\main\\resources\\db\\dest.xml").toAbsolutePath().toString());
        File schema = new File(Paths.get("chapter_007\\src\\main\\resources\\db\\schema.xml").toAbsolutePath().toString());
        final String xsl = String.format("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s",
                "<?xml version=\"1.0\"?>\n",
                "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n",
                "<xsl:template match=\"/\">\n",
                "<xsl:text>&#xd;</xsl:text>\n",
                "<entries>\n",
                "   <xsl:for-each select=\"entries/entry\">\n",
                "       <entry>\n",
                "           <xsl:attribute name=\"field\">\n",
                "               <xsl:value-of select=\"field\"/>\n",
                "           </xsl:attribute>\n",
                "       </entry>\n",
                "   </xsl:for-each>\n",
                " </entries>",
                "</xsl:template>",
                "</xsl:stylesheet>");
        try {
            Files.write(Paths.get(schema.getAbsolutePath()), xsl.getBytes(StandardCharsets.UTF_8));
            file.createNewFile();
            dest.createNewFile();
//            file.deleteOnExit();
            StoreXML storeXML = new StoreXML(file);
            StoreSQL storeSQL = new StoreSQL();
            storeSQL.initDB();
            storeSQL.generate(2);
            List<Entry> list = storeSQL.readEntries();
//            System.out.println(list);
            storeXML.save(list);
            ConvertXSQT.convert(file, dest, schema);
//            System.out.println(new String(Files.readAllBytes(Paths.get(dest.getPath())), StandardCharsets.UTF_8));
            SAXparser xparser = new SAXparser();
            System.out.printf("Total result: %d", xparser.parse(dest));
        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}
