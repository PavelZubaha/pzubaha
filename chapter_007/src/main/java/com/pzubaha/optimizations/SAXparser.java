package com.pzubaha.optimizations;


import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Parser for xml
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class SAXparser {

    private int sum = 0;
    class Handler extends DefaultHandler {
        int total;
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (attributes != null && attributes.getLength() > 0) {
                total += Integer.parseInt(attributes.getValue(0));
            }
        }
        @Override
        public void endDocument() throws SAXException {
            SAXparser.this.sum = total;
        }
    }

    public int parse(File f) throws ParserConfigurationException, SAXException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        try {
            saxParser.parse(f, new Handler());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sum;
    }
}
