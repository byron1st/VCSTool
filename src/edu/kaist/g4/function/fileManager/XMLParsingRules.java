package edu.kaist.g4.function.fileManager;

import java.util.ArrayList;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class XMLParsingRules extends DefaultHandler{
    Architecture_XML architecture;
    
    public Architecture_XML getParsedData(){
        return architecture;
    }
    
    @Override  
    public void startDocument() throws SAXException {  
        architecture = new Architecture_XML();
    }  
 
    @Override  
    public void endDocument() throws SAXException {  
        // Nothing to do  
    }  

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("UML:Class") && attributes.getValue("visibility") != null) {
            ArrayList<String> list = new ArrayList<String>();
            list.add("Class");
            list.add(attributes.getValue("name"));
            architecture.elements.put(attributes.getValue("xmi.id"), list);
        }
        else if(qName.equals("UML:Generalization")){
            ArrayList<String> list = new ArrayList<String>();
            list.add("Generalization");
            list.add(attributes.getValue("subtype"));
            list.add(attributes.getValue("supertype"));
            architecture.relations.put(attributes.getValue("xmi.id"), list);
        }
        else if(qName.equals("UML:Dependency")){
            ArrayList<String> list = new ArrayList<String>();
            list.add("Dependency");
            list.add(attributes.getValue("client"));
            list.add(attributes.getValue("supplier"));
            architecture.relations.put(attributes.getValue("xmi.id"), list);
        }
    }
  
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        
    }
  
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
       
    }
}
