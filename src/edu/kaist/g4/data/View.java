package edu.kaist.g4.data;

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
    private String id;
    
    private Vector<ArchitectureElement> components;
    private Vector<Relation> connectors;
    private ViewType type;
    
    
    public View(Architecture architecture, ViewType viewtype){
        components = new Vector<ArchitectureElement>();
        connectors = new Vector<Relation>();
        
        architecture = this.architecture;
        type = viewtype;
        
        id = "testid" + type.toString();
    }
    
    public void addComponent(ArchitectureElement component){
        components.add(component);
    }
    
    public void addConnector(Relation connector){
        connectors.add(connector);
    }
    
    public Iterator<ArchitectureElement> componentIterator(){
        return components.iterator();
    }
    
    public Iterator<Relation> connectorIterator(){
        return connectors.iterator();
    }
    
}
