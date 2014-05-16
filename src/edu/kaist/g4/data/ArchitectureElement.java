package edu.kaist.g4.data;

import java.util.HashMap;
import java.util.Vector;

/**
 * 
 * @author Junhaeng Heo
 *
 *  Component contains Attributes
 */

public class ArchitectureElement {
    
    private String name;
    private String id;
    private int revision;
    private ElementType type;
    
    private HashMap<AttributeType,Attribute> attributes;
    private Vector<Relation> relations;

    public ArchitectureElement(){
        attributes = new HashMap<AttributeType,Attribute>();
        relations = new Vector<Relation>();
    }
    
    public ArchitectureElement(String name, String id, int revision, ElementType type){
        this.name = name;
        this.id = id;
        this.revision = revision;
        this.type = type;
        attributes = new HashMap<AttributeType,Attribute>();
    }
    
    public void addRelation(Relation r){
        relations.add(r);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int revision) {
        this.revision = revision;
    }

    public ElementType getType() {
        return type;
    }

    public void setType(ElementType type) {
        this.type = type;
    }

    public HashMap<AttributeType, Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(HashMap<AttributeType, Attribute> attributes) {
        this.attributes = attributes;
    }

    public Vector<Relation> getRelations() {
        return relations;
    }

    public void setRelations(Vector<Relation> relations) {
        this.relations = relations;
    }
    
    

    
}
