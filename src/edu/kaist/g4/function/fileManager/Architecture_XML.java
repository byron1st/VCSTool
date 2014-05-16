package edu.kaist.g4.function.fileManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Architecture_XML{
    HashMap<String, ArrayList<String>> elements = new HashMap<String, ArrayList<String>>();     //key: id, value1: type, value2: name
    HashMap<String, ArrayList<String>> relations = new HashMap<String, ArrayList<String>>();    //key: id, value1: type, value2: source, value3: target         
} 