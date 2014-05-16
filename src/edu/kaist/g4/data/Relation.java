package edu.kaist.g4.data;

import java.util.HashMap;

/**
 * 
 * @author Junhaeng Heo
 *
 *  Connector contains Attributes
 */



public class Relation {
    
    private RelationType type;
    private ArchitectureElement source, destination;
    private HashMap<AttributeType ,String> attributes;

    public Relation(RelationType type, ArchitectureElement source, ArchitectureElement destination){
        this.type = type;
        this.source = source;
        this.destination = destination;
        this.attributes = new HashMap<AttributeType, String>();
    }

    public RelationType getType() {
        return type;
    }

    public void setType(RelationType type) {
        this.type = type;
    }

    public ArchitectureElement getSource() {
        return source;
    }

    public void setSource(ArchitectureElement source) {
        this.source = source;
    }

    public ArchitectureElement getDestination() {
        return destination;
    }

    public void setDestination(ArchitectureElement destination) {
        this.destination = destination;
    }

    public HashMap<AttributeType, String> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<AttributeType, String> attributes) {
        this.attributes = attributes;
    }

    

}
