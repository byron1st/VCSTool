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
enum ViewVesion { WORKING, RECNENT };

public class View {
    
    private Architecture architecture; 
 
    
    private HashMap<String, ArchitectureElement> elements;
    private Vector<Relation> relations;
    private ViewType type;
    private ViewVesion vesion;
    
    public View(Architecture architecture, ViewType viewtype, ViewVesion vesion){
        elements = new HashMap<String, ArchitectureElement>();
        relations = new Vector<Relation>();
        
        architecture = this.architecture;
        this.type = viewtype;
        this.vesion = vesion;
        
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

    public ViewVesion getVesion() {
        return vesion;
    }

    public void setVesion(ViewVesion vesion) {
        this.vesion = vesion;
    }
    
}
