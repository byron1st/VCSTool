package edu.kaist.g4.function.fileManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import edu.kaist.g4.data.Architecture;
import edu.kaist.g4.data.ArchitectureElement;
import edu.kaist.g4.data.ElementType;
import edu.kaist.g4.data.Relation;
import edu.kaist.g4.data.RelationType;
import edu.kaist.g4.data.ArchitectureModel;

public class Reader extends DefaultHandler{
    View_XML viewXML;
    Architecture arch;
    XMLParsingRules parsingRule;
    ArchitectureModel v;
    
    public Reader(){
        arch = new Architecture("aa");
    }

    public Architecture addArchitectureModel(){
        v = new ArchitectureModel(viewXML.type);

        ArchitectureElement ae = new ArchitectureElement();
        Set<String> elementSet = viewXML.elements.keySet();
        for(String str : elementSet){
            ArrayList<Object> list = viewXML.elements.get(str);
            ae.setId(str);
            ae.setType((ElementType)list.get(0));
            ae.setName((String)list.get(1));
            v.addComponent(ae);
        }
        
        Set<String> relationSet = viewXML.relations.keySet();
        for(String str : relationSet){
            ArrayList<Object> list = viewXML.relations.get(str);
            String srcID = (String)list.get(1);
            String dstID = (String)list.get(2);
            Relation relation = new Relation((RelationType)list.get(0), getElementByID(srcID), getElementByID(dstID)); //type, source, des
            v.addConnector(relation);
        }
        
        arch.addArchitectureModel(viewXML.type, v);
        return arch;
    }
    public ArchitectureElement getElementByID(String id){
        HashMap<String, ArchitectureElement> hm = v.getElements();
        return (ArchitectureElement)hm.get(id);
    }
    
    public View_XML getParsedData(){
        return viewXML;
    }
    public Architecture getArchitecture(){
        return arch;
    }
    
    @Override  
    public void startDocument() throws SAXException {  
        viewXML = new View_XML();
        parsingRule = new XMLParsingRules();
    }  

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        parsingRule.executeRule(viewXML, qName, attributes);
    }
}
