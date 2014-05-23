package edu.kaist.g4.function.fileManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Traceability_XML {
    private HashMap<String, ArrayList<Object>> links = new HashMap<String, ArrayList<Object>>();     //key: src(String), value1: srcModel(String), value2: dstModel(Vector<String>)
    
    
    public HashMap<String, ArrayList<Object>> getLinks(){
        return links;
    }
}
