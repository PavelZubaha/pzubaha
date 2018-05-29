package com.pzubaha.optimizations;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Entries {
    @XmlElement(name = "entry")
    private List<Entry> entry;

    public Entries() {
    }

    public Entries(List<Entry> entries) {
        this.entry = entries;
    }

    public List<Entry> getEntry() {
        return entry;
    }

    public void setEntry(List<Entry> entry) {
        this.entry = entry;
    }
}
