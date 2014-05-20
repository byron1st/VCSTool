package edu.kaist.g4.function.fileManager;

import java.io.File;

import edu.kaist.g4.data.Architecture;


public class Writer{
    XMLParsingRules parsingRule;
    
    public Writer(){
        parsingRule = new XMLParsingRules();
    }
    public void writeArchitecture(Architecture arch, String version_no){
        File dir = new File(version_no);
        if(!dir.exists()){
            dir.mkdir();
        }
        parsingRule.executeWriteRule(arch);
    }
}
 