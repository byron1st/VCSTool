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

enum ViewType { CNC, MODULE };

public class View {
    
    private Architecture architecture; 
 
    private String name;
    private HashMap<String, ArchitectureElement> elements;
    private Vector<Relation> relations;
    private ViewType type;
    
    public View(String name, ViewType viewtype){
        elements = new HashMap<String, ArchitectureElement>();
        relations = new Vector<Relation>();
        
        this.type = viewtype;
        
    }
    
    public View(Architecture architecture, String name, ViewType viewtype){
        elements = new HashMap<String, ArchitectureElement>();
        relations = new Vector<Relation>();
        
        architecture = this.architecture;
        this.type = viewtype;
        
    }
    
    public void addComponent(ArchitectureElement component){
        elements.put(component.getId(),component);
    }
    
    public void addConnector(Relation connector){
        relations.add(connector);
    }
    
    public  Collection<ArchitectureElement> getelements(){
        return elements.values();
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

    public Architecture getArchitecture() {
        return architecture;
    }

    public void setArchitecture(Architecture architecture) {
        this.architecture = architecture;
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
    

}
