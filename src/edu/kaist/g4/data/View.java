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
    
    private Vector<Component> components;
    private Vector<Connector> connectors;
    private ViewType type;
    
    
    public View(Architecture architecture, ViewType viewtype){
        components = new Vector<Component>();
        connectors = new Vector<Connector>();
        
        architecture = this.architecture;
        type = viewtype;
        
        id = "testid" + type.toString();
    }
    
    public void addComponent(Component component){
        components.add(component);
    }
    
    public void addConnector(Connector connector){
        connectors.add(connector);
    }
    
    public Iterator<Component> componentIterator(){
        return components.iterator();
    }
    
    public Iterator<Connector> connectorIterator(){
        return connectors.iterator();
    }
    
}
