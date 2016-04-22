package com.shipeng.xml;

import net.sf.saxon.s9api.*;
import org.w3c.dom.Document;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import javax.xml.transform.ErrorListener;
import javax.xml.transform.SourceLocator;
import javax.xml.transform.TransformerException;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.math.BigDecimal;
import java.net.URI;
import java.util.*;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class XMLTest {

    public static void main(String[] args) {
        
        if (args.length != 3) {
            System.err.println("please give commands as follows: ");
            System.err.println("XSLTest data.xml converted.xsl converted.xml");
            return;
        }

        String dataXML  = args[0];
        String inputXSL = args[1];
        String output   = args[2];

        System.out.println("dataXML is: " + dataXML);
        System.out.println("inputXSL is: " + inputXSL);
        System.out.println("output is: " + output);


        XMLTest st = new XMLTest();
        try {
            st.transform(dataXML, inputXSL, output);
        }catch (SaxonApiException e) {
            e.printStackTrace();
        }
    
    }//end main


    public void transform(String dataXML, String inputXSL, String output)throws SaxonApiException {
        Processor proc     = new Processor(false);
        XsltCompiler comp  = proc.newXsltCompiler();
        XsltExecutable exp = comp.compile(new StreamSource(new File(inputXSL)));
        XdmNode source     = proc.newDocumentBuilder().build(new StreamSource(new File(dataXML)));
        Serializer out     = proc.newSerializer(new File(output));
        out.setOutputProperty(Serializer.Property.METHOD, "text");
        out.setOutputProperty(Serializer.Property.INDENT, "yes");
        XsltTransformer trans = exp.load();
        trans.setInitialContextNode(source);
        trans.setDestination(out);
        trans.transform();

        System.out.println("output written to " + output);

    }//end transform


    public static final String readFile(String filePath) {
        String result     = "";
        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader(filePath));

            while ((sCurrentLine = br.readLine()) != null) {
                result += sCurrentLine;
            }//end while

        }catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (br != null) {
                    br.close();
                }
            }catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }//end readFile


}//end class XMLTest
