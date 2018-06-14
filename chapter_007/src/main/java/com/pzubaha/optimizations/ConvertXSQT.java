package com.pzubaha.optimizations;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author Pavel Zubaha (mailto:Apximar@gmail.com)
 * @version 1
 */
public class ConvertXSQT {
    /**
     * Convert xml document from source file to the dest file according to the spec schema.
     * @param source file.
     * @param dest destination file.
     * @param schema schema file.
     */
    public static void  convert(File source, File dest, File schema) {
        TransformerFactory factory = TransformerFactory.newInstance();
        try {
            Transformer transformer = factory.newTransformer(new StreamSource(new FileReader(schema)));
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(new StreamSource(source), new StreamResult(dest));
        } catch (FileNotFoundException | TransformerException e) {
            e.printStackTrace();
        }
    }
}
