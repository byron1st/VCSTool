package edu.kaist.g4.function.fileManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Vector;

import edu.kaist.g4.data.ViewType;



public class Model_XML{
    private String id;
    private ViewType type;
    private HashMap<String, ArrayList<Object>> elements = new HashMap<String, ArrayList<Object>>();     //key: id(String), value1: type(ElementType), value2: name(String)
    private Vector<Object> relations = new Vector<Object>();    //key: id(String), value1: type(RelationType), value2: source(String), value3: target (String)
    
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public ViewType getType(){
        return type;
    }
    
    public void setType(ViewType type){
        this.type = type;
    }
    
    public HashMap<String, ArrayList<Object>> getElements(){
        return elements;
    }
    
    public Vector<Object> getRelations(){
        return relations;
    }
    
} 