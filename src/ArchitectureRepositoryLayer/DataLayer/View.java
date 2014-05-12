package ArchitectureRepositoryLayer.DataLayer;

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

public class View {
   
    Vector<Component> components;
    Vector<Connector> connector;
    
    public View(String viewpoint){
        components = new Vector<Component>();
        connector = new Vector<Connector>();
    }
    
}
