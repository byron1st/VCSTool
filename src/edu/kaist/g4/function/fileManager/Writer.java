package edu.kaist.g4.function.fileManager;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class Writer extends DefaultHandler{
    Architecture_XML architecture;
    XMLParsingRules parsingRule;
    
    public Architecture_XML getParsedData(){
        return architecture;
    }
    
    @Override  
    public void startDocument() throws SAXException {  
        architecture = new Architecture_XML();
        parsingRule = new XMLParsingRules();
    }  

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        parsingRule.executeRule(architecture, qName, attributes);
    }
}
 