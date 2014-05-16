package edu.kaist.g4.function.fileManager;

import java.util.ArrayList;

import org.xml.sax.Attributes;

import edu.kaist.g4.data.ElementType;
import edu.kaist.g4.data.RelationType;
import edu.kaist.g4.data.ViewType;

public class XMLParsingRules{

    
    public void executeRule(View_XML viewXML, String qName, Attributes attributes){
        if (qName.equals("XMI")){
            viewXML.id = attributes.getValue("timestamp");
        }
        else if(qName.equals("UML:Package")){
            String type = attributes.getValue("name");
            if(type.equals("Class Model")){
                viewXML.type = ViewType.MODULE;
            }
            else if(type.equals("Component Model")){
                viewXML.type = ViewType.CNC;
            }
        }
        else if(qName.equals("UML:Class") && attributes.getValue("visibility") != null) {
            ArrayList<Object> list = new ArrayList<Object>();
            list.add(ElementType.MODULE);
            list.add(attributes.getValue("name"));
            viewXML.elements.put(attributes.getValue("xmi.id"), list);
        }
        else if(qName.equals("UML:Generalization")){
            ArrayList<Object> list = new ArrayList<Object>();
            list.add(RelationType.GENERALIZATION);
            list.add(attributes.getValue("subtype"));
            list.add(attributes.getValue("supertype"));
            viewXML.relations.put(attributes.getValue("xmi.id"), list);
        }
        else if(qName.equals("UML:Dependency")){
            ArrayList<Object> list = new ArrayList<Object>();
            list.add(RelationType.DEPENDENCY);
            list.add(attributes.getValue("client"));
            list.add(attributes.getValue("supplier"));
            viewXML.relations.put(attributes.getValue("xmi.id"), list);
        }
    }
}
 