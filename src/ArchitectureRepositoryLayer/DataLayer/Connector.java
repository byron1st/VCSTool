package ArchitectureRepositoryLayer.DataLayer;

import java.util.Vector;

/**
 * 
 * @author Junhaeng Heo
 *
 *  Connector contains Attributes
 */

public class Connector {
    
    Vector<Attribute> attributes;
    
    public Connector(String s){
        attributes = new Vector<Attribute>();
    }

}
