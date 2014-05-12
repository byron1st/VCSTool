package ArchitectureRepositoryLayer.DataLayer;

import java.util.Vector;

/**
 * 
 * @author Junhaeng Heo
 *
 *  Component contains Attributes
 */

public class Component {
    
    Vector<Attribute> attributes;

    public Component(String s){
        attributes = new Vector<Attribute>();
    }
    
}
