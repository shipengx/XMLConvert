package com.shipeng.xml;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

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

        XMLTest st = new XMLTest();
        try {
            st.transform(dataXML, inputXSL, output);
        }catch (TransformerConfigurationException e) {
            System.err.println("TransformerConfigurationException");
            System.err.println(e);
        }catch (TransformerException e) {
            System.err.println("TransformerException");
            System.err.println(e);
        }
    
    }//end main


    public void transform(String dataXML, String inputXSL, String outputXML)throws TransformerConfigurationException, TransformerException {
        
        TransformerFactory factory = TransformerFactory.newInstance();
        StreamSource    xslStream  = new StreamSource(inputXSL);
        Transformer transformer    = factory.newTransformer(xslStream);
        StreamSource in            = new StreamSource(dataXML);
        StreamResult out           = new StreamResult(outputXML);
        transformer.transform(in, out);
        System.out.println("the generated XML file is: " + outputXML);

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
