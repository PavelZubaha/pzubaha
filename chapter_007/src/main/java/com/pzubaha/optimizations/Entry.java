package com.pzubaha.optimizations;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Entry {
    @XmlElement
    private int field;

    public Entry() {
    }

    public Entry(int field) {
        this.field = field;
    }

    public int getField() {
        return field;
    }

    public void setField(int element) {
        this.field = element;
    }

    @Override
    public String toString() {
        return String.format("Entry{field=%d}", field);
    }
}
