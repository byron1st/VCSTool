package edu.kaist.g4.data;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

/**
 * 
 * @author Junhaeng Heo
 * Every Views contain it's ArchitecturalElement and Relation
 * 
 *      In C&C View
 *            ArchitecturalElement : Component
 *            Relation : Connector
 *           
 *      In Module View
 *            ArchitecturalElement : Module
 *            Relation : Use
 *            
 * We named ArchitecturalElement "Component" and Relation "Connector"
 *           
 */



public class ArchitectureModel {
    
    private String name;
    private String id;
    private HashMap<String, ArchitectureElement> elements;
    private Vector<Relation> relations;
    private ViewType type;
    
    public ArchitectureModel(ViewType viewtype){
        elements = new HashMap<String, ArchitectureElement>();
        relations = new Vector<Relation>();
        
        id = "00000";
        name = "NO NAME";
        this.type = viewtype;
        
    }
    
    public ArchitectureModel(String filename, ViewType viewtype){
        elements = new HashMap<String, ArchitectureElement>();
        relations = new Vector<Relation>();

        id = "00000";
        name = "NO NAME";
        this.type = viewtype;
        
    }
    
    public void addArchitectureElement(ArchitectureElement ae){
        elements.put(ae.getId(),ae);
    }
    
    public void addRelation(Relation r){
        relations.add(r);
    }
    
    public ArchitectureElement serachElementByID(String ID){
        return elements.get(ID);
    }
    
    public Iterator<Relation> connectorIterator(){
        return relations.iterator();
    }

    public ViewType getType() {
        return type;
    }

    public void setType(ViewType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashMap<String, ArchitectureElement> getElements() {
        return elements;
    }

    public void setElements(HashMap<String, ArchitectureElement> elements) {
        this.elements = elements;
    }

    public Vector<Relation> getRelations() {
        return relations;
    }

    public void setRelations(Vector<Relation> relations) {
        this.relations = relations;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String overallInformation(){
        String result = "Model name: " + this.name + "\n";
        result += "Model ID: "+ this.id + "\n";
        
        result += "Elements List\n";
        Iterator<ArchitectureElement> it = elements.values().iterator();
        while(it.hasNext()){
            ArchitectureElement ae = it.next();
            result += ae.getInformation() + "\n";
        }
        result += "\n";
        result += "Relation List\n";
        Iterator<Relation> it2 = this.relations.iterator();
        while(it2.hasNext()){
            Relation r = it2.next();
            result += r.getInformation() +"\n";
        }
        
        return result;
    }

}
