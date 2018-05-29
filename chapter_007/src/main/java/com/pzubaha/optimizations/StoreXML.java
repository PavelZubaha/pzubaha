package com.pzubaha.optimizations;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

/**
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class StoreXML {
    private final File target;

    public StoreXML(File target) {
        this.target = target;
    }

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
