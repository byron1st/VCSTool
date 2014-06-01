package edu.kaist.g4.function.fileManager;

import java.io.File;

import edu.kaist.g4.data.Architecture;


public class Writer{
    private XMLParsingRules parsingRule;
    
    public Writer(){
        parsingRule = new XMLParsingRules();
    }
    public void writeArchitecture(Architecture arch, String dir){
        File mDir = new File(dir+"/Model");
        if(!mDir.exists()){
            mDir.mkdirs();
        }
        File tDir = new File(dir+"/Traceability");
        if(!tDir.exists()){
            tDir.mkdirs();
        }
        parsingRule.executeWriteRule(arch, dir);
    }
}
 