package edu.kaist.g4.function.fileManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Traceability_XML {
    private String srcModelID;
    private String dstModelID;
    
    private HashMap<String, ArrayList<String>> links = new HashMap<String, ArrayList<String>>();     //key: id(String), value1: type(ElementType), value2: name(String)
    
    
    public String getSrcModelID(){
        return srcModelID;
    }
    
    public void setSrcModelID(String id){
        this.srcModelID = id;
    }
    
    public String getDstModelID(){
        return dstModelID;
    }
    
    public void setDstModelID(String id){
        this.dstModelID = id;
    }
    
    public HashMap<String, ArrayList<String>> getLinks(){
        return links;
    }
}
