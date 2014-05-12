package ArchitectureRepositoryLayer.DataLayer;

/**
 * 
 * @author Junhaeng Heo
 *
 * 
 */

public class Attribute {

    String description;
    
    public Attribute(String description){
        this.description = description;
    }
    
    public String getString(){
        return description;
    }
    
}
