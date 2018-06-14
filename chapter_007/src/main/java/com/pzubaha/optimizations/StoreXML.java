package com.pzubaha.optimizations;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
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
 * 6. Time of working with N (~1000000) should be not bigger than 5min.
 *
 * StoreXML.
 * Class proves storing List of entries to the file.
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class StoreXML {
    private final File target;

    public StoreXML(File target) {
        this.target = target;
    }

    /**
     * Getter for file.
     * @return store com.pzubaha.optimizations.Entry File.
     */
    public File getTargetFile() {
        return target;
    }

    /**
     * Save list of entries to file xml format.
     * @param entries list of entries.
     */
    public void save(List<Entry> entries) {
        JAXBContext jaxbContext;
        Marshaller jaxbMarshaller;
        try {
            jaxbContext = JAXBContext.newInstance(Entries.class);
            jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.marshal(new Entries(entries), target);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}
