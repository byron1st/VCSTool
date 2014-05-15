package ArchitectureRepositoryLayer.DataLayer;

import java.util.HashMap;

/**
 * 
 * @author Junhaeng Heo
 *
 *  Component contains Attributes
 */

public class Component {
    
    private String id;
    private HashMap<String,String> attributes;

    public Component(String id){
        this.id = id;
        attributes = new HashMap<String,String>();
    }
    
    public void addAttribute(String key, String value){
        attributes.put(key, value);
    }
    
    public String getID(){
        return id;
    }
    
    public HashMap<String,String> getAttributes(){
        return attributes;
    }
    
}
