package edu.kaist.g4.function.fileManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.Attributes;

import edu.kaist.g4.data.Architecture;
import edu.kaist.g4.data.ArchitectureElement;
import edu.kaist.g4.data.ArchitectureModel;
import edu.kaist.g4.data.ElementType;
import edu.kaist.g4.data.Relation;
import edu.kaist.g4.data.RelationType;
import edu.kaist.g4.data.ViewType;

public class XMLParsingRules{

    
    public void executeReadRule(View_XML viewXML, String qName, Attributes attributes){
        if (qName.equals("XMI")){
            viewXML.id = attributes.getValue("timestamp");
        }
        else if(qName.equals("UML:Type")){
            String type = attributes.getValue("name");
            if(type.equals("Class Model")){
                viewXML.type = ViewType.MODULE;
            }
            else if(type.equals("Component Model")){
                viewXML.type = ViewType.CNC;
            }
        }
        //Class Model(Module View)
        else if(qName.equals("UML:Class")) {
            ArrayList<Object> list = new ArrayList<Object>();
            list.add(ElementType.MODULE);
            list.add(attributes.getValue("name"));
            viewXML.elements.put(attributes.getValue("xmi.id"), list);
        }
        else if(qName.equals("UML:Package")) {          //하위 class 읽을 필요
            ArrayList<Object> list = new ArrayList<Object>();
            list.add(ElementType.MODULE);               //Package로 수정 필요
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
        //Component Model(CNC View)
        else if(qName.equals("UML:Component")){
            ArrayList<Object> list = new ArrayList<Object>();
            list.add(ElementType.COMPONENT);
            list.add(attributes.getValue("name"));
            viewXML.elements.put(attributes.getValue("xmi.id"), list);
        }
        else if(qName.equals("UML:Connector")){
            ArrayList<Object> list = new ArrayList<Object>();
            list.add(ElementType.CONNECTOR);
            list.add(attributes.getValue("name"));
            viewXML.elements.put(attributes.getValue("xmi.id"), list);
        }
        else if(qName.equals("UML:Association")){
            ArrayList<Object> list = new ArrayList<Object>();
            list.add(RelationType.RELATION);
            list.add(attributes.getValue("client"));
            list.add(attributes.getValue("supplier"));
            viewXML.relations.put(attributes.getValue("xmi.id"), list);
        }
    }
    public void executeWriteRule(Architecture arch){
      //XMLWriter variables
        try{
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance(); 
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();          

            Vector<ArchitectureModel> archModels = arch.getArchitectureModels();
            for(ArchitectureModel archModel : archModels){
                Document doc = docBuilder.newDocument();
                Element childElement = null;
                Element rootElement;
                Attr attr;
                
                //root element
                rootElement = doc.createElement("XMI");
                attr = doc.createAttribute("timestamp");
                attr.setValue(Long.toString(System.currentTimeMillis()));
                rootElement.setAttributeNode(attr);
                attr = doc.createAttribute("xmlns:UML");
                attr.setValue("omg.org/UML1.3");
                rootElement.setAttributeNode(attr);
                doc.appendChild(rootElement);
                
                //UML:Package tag(module view ? or CNC view ?)
                Element typeElement = doc.createElement("UML:Type");
                attr = doc.createAttribute("name");
                
                ViewType vType = archModel.getType();
                if(vType == ViewType.MODULE){
                    attr.setValue("Class Model");
                }
                else if(vType == ViewType.CNC){
                    attr.setValue("Component Model");
                }
                typeElement.setAttributeNode(attr);
                rootElement.appendChild(typeElement);
                
                //add element
                Iterator<ArchitectureElement> archElementIt;
                Iterator<Relation> relationIt;
                archElementIt = archModel.getElements().iterator();
                while(archElementIt.hasNext()){
                    ArchitectureElement archElement = (ArchitectureElement)archElementIt.next();
                    
                    //write archElement
                    ElementType eType = archElement.getType();
                    if(eType == ElementType.MODULE){
                        childElement = doc.createElement("UML:Class");
                    }
                    else if(eType == ElementType.COMPONENT){
                        childElement = doc.createElement("UML:Component");
                    }
                    else if(eType == ElementType.CONNECTOR){
                        childElement = doc.createElement("UML:Connector");
                    }   
                    
                    attr = doc.createAttribute("name");     //name
                    attr.setValue(archElement.getName());
                    childElement.setAttributeNode(attr);
                    
                    attr = doc.createAttribute("xmi.id");   //xmi.id
                    attr.setValue(archElement.getId());
                    childElement.setAttributeNode(attr);
                    
                    typeElement.appendChild(childElement);
                }
                
                //add relations
                relationIt = archModel.getRelations().iterator();
                while(relationIt.hasNext()){
                    Relation rel = (Relation)relationIt.next();
                    
                    //write rel
                    RelationType rType = rel.getType();
                    if(rType == RelationType.DEPENDENCY){
                        childElement = doc.createElement("UML:Dependency");
                        
                        attr = doc.createAttribute("client");   //client
                        attr.setValue(rel.getSource().getId());
                        childElement.setAttributeNode(attr);
                        
                        attr = doc.createAttribute("supplier");   //supplier
                        attr.setValue(rel.getDestination().getId());
                        childElement.setAttributeNode(attr);
                    }
                    else if(rType == RelationType.RELATION){
                        childElement = doc.createElement("UML:Association");
                        
                        attr = doc.createAttribute("client");   //client
                        attr.setValue(rel.getSource().getId());
                        childElement.setAttributeNode(attr);
                        
                        attr = doc.createAttribute("supplier");   //supplier
                        attr.setValue(rel.getDestination().getId());
                        childElement.setAttributeNode(attr);
                    }
                    else if(rType == RelationType.GENERALIZATION){
                        childElement = doc.createElement("UML:Generalization");
                        
                        attr = doc.createAttribute("subtype");   //subtype
                        attr.setValue(rel.getSource().getId());
                        childElement.setAttributeNode(attr);
                        
                        attr = doc.createAttribute("supertype");   //supertype
                        attr.setValue(rel.getDestination().getId());
                        childElement.setAttributeNode(attr);
                    }
                    typeElement.appendChild(childElement);
                }
                
                // XML write 
                TransformerFactory transformerFactory = TransformerFactory.newInstance(); 
                Transformer transformer = transformerFactory.newTransformer(); 
         
                transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8"); 
                transformer.setOutputProperty(OutputKeys.INDENT, "yes");        
                
                DOMSource source = new DOMSource(doc); 
                StreamResult result = new StreamResult(new FileOutputStream(new File("version1.1/aa"+vType.toString()+".xml"))); 
         
                transformer.transform(source, result);
            }
        }catch(ParserConfigurationException pce){
            pce.printStackTrace();
        }catch(TransformerException tfe){
            tfe.printStackTrace();
        }catch(FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }
    }
}
 