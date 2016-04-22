package com.shipeng.xml;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.IOException;

public class XSLTransform {

    public static void main(String[] args) {
    
        if (args.length > 3 || args.length < 2) {
            System.err.println("Usage: XSLTransform xmlFile.xml stylesheet.xsl");
            System.exit(-1);
        }

        try {
            String inputXMLPath      = args[0];
            String inputXSLPath      = args[1];
            String outputPath        = args[2];

            StreamSource source      = new StreamSource(inputXMLPath);
            StreamSource stylesource = new StreamSource(inputXSLPath);
            
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer    = factory.newTransformer(stylesource);
        
            //print the result output to screen
            //StreamResult result        = new StreamResult(System.out);

            //save the output to a file
            StreamResult result        = new StreamResult(new File(outputPath));
            transformer.transform(source, result);
 
        }catch(Exception e) {
            e.printStackTrace();
        }
    }//end main

}//end class





