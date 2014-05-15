package edu.kaist.g4.function.fileManager;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParsingRules extends DefaultHandler{
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
         
    }
  
    /**
     * This will be called when the tags of the XML end.
     **/
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
  
        
    }
  
    /**
     * This is called to get the tags value
     **/
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
       
    }
}
