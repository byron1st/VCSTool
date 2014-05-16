package edu.kaist.g4.function.fileManager;

import java.util.ArrayList;
import java.util.HashMap;

import edu.kaist.g4.data.ViewType;



public class View_XML{
    String id;
    ViewType type;
    HashMap<String, ArrayList<Object>> elements = new HashMap<String, ArrayList<Object>>();     //key: id(String), value1: type(ElementType), value2: name(String)
    HashMap<String, ArrayList<Object>> relations = new HashMap<String, ArrayList<Object>>();    //key: id(String), value1: type(RelationType), value2: source(String), value3: target (String)        
} 