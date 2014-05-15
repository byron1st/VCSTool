package edu.kaist.g4.function.fileManager;

import java.util.ArrayList;

import org.xml.sax.Attributes;

public class XMLParsingRules{

    
    public void executeRule(Architecture_XML architecture, String qName, Attributes attributes){
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
}
