package edu.kaist.g4.function.fileManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Vector;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import edu.kaist.g4.data.Architecture;
import edu.kaist.g4.data.ArchitectureElement;
import edu.kaist.g4.data.ArchitectureModel;
import edu.kaist.g4.data.ElementType;
import edu.kaist.g4.data.Relation;
import edu.kaist.g4.data.RelationType;
import edu.kaist.g4.data.TraceabilityLink;

public class Reader extends DefaultHandler{
    private Model_XML modelXML;
    private Traceability_XML tLinkXML;
    private Architecture arch;
    private XMLParsingRules parsingRule;
    private ArchitectureModel m;
    private TraceabilityLink t;
    private String text;
    
    public Reader(){
        arch = new Architecture("aa");      //이름을 뭘로 해야되지?
    }

    public Architecture addArchitectureModel(){
        m = new ArchitectureModel(modelXML.getType());

        m.setId(modelXML.getId());
        //elements
        Set<String> elementSet = modelXML.getElements().keySet();
        for(String str : elementSet){
            ArchitectureElement ae = new ArchitectureElement();
            ArrayList<Object> list = modelXML.getElements().get(str);
            ae.setId(str);
            ae.setType((ElementType)list.get(0));
            ae.setName((String)list.get(1));
            m.addArchitectureElement(ae);
        }
        
        //relations
        Vector<Object> relationSet = modelXML.getRelations();
        for(Object relationList : relationSet){
            ArrayList<Object> list = (ArrayList<Object>)relationList;
            String srcID = (String)list.get(1);
            String dstID = (String)list.get(2);

            Relation relation = new Relation((RelationType)list.get(0), getElementByID(srcID), getElementByID(dstID)); //type, source, dst
            //srcID에 해당하는 element에 relation 추가
            m.serachElementByID(srcID).addRelation(relation);
            
            m.addRelation(relation);
        }
        
        arch.addArchitectureModel(modelXML.getType(), m);
        return arch;
    }
    
    public void addTraceability(){
        HashMap<String, ArrayList<Object>> links = tLinkXML.getLinks();
        Set<String> srcElementSet = links.keySet();
        for(String srcElementId : srcElementSet){
            ArrayList<Object> list = links.get(srcElementId);
            String srcModelId = (String)list.get(0);
            String dstModelId = (String)list.get(1);
            Vector<String> v = (Vector<String>)list.get(2);
            arch.addTracebilityLink(srcElementId, v, getModelByID(srcModelId), getModelByID(dstModelId));
        }
    }
    
    public ArchitectureElement getElementByID(String id){
        HashMap<String, ArchitectureElement> hm = m.getElements();
        return (ArchitectureElement)hm.get(id);
    }
    
    public ArchitectureModel getModelByID(String id){
        Vector<ArchitectureModel> am = arch.getArchitectureModels();
        for(ArchitectureModel model : am){
            if(model.getId().equals(id)){
                return model;
            }
        }
        return null;
    }
    
    public Model_XML getParsedData(){
        return modelXML;
    }
    public Architecture getArchitecture(){
        return arch;
    }
    
    @Override  
    public void startDocument() throws SAXException {  
        modelXML = new Model_XML();
        tLinkXML = new Traceability_XML();
        parsingRule = new XMLParsingRules();
    }  

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        parsingRule.executeReadRule(modelXML, tLinkXML, qName, attributes);
    }
    
    @Override
    public void endElement(String uri, String localName, String qName){
        parsingRule.endReadRule(tLinkXML, qName, text);
    }
    
    @Override
    public void characters(char[] ch, int start, int length){
        text = new String(ch, start, length).trim();
    }
}
