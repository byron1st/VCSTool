package edu.kaist.g4.data;

/**
 * 
 * @author Junhaeng Heo
 *
 * 
 * 
 */

enum AttributeType {TestType1, TestType2}

public class Attribute {

    private AttributeType type;
    private String description;
    
    public Attribute(AttributeType type, String description){
        this.type = type;
        this.description = description;
    }
    
    public String getString(){
        return description;
    }

    public AttributeType getType() {
        return type;
    }

    public void setType(AttributeType type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
