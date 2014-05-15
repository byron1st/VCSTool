package ArchitectureRepositoryLayer.DataLayer;

import java.util.HashMap;

/**
 * 
 * @author Junhaeng Heo
 *
 *  Connector contains Attributes
 */

public class Connector {
    
    private String id;
    private HashMap<String,String> attributes;

    public Connector(String id){
        id = this.id;
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
