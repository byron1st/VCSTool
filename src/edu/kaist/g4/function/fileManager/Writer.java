package edu.kaist.g4.function.fileManager;

import java.io.File;

import edu.kaist.g4.data.Architecture;


public class Writer{
    private XMLParsingRules parsingRule;
    
    public Writer(){
        parsingRule = new XMLParsingRules();
    }
    public void writeArchitecture(Architecture arch, String version_no){
        File mDir = new File(version_no+"/Model");
        if(!mDir.exists()){
            mDir.mkdirs();
        }
        File tDir = new File(version_no+"/Traceability");
        if(!tDir.exists()){
            tDir.mkdirs();
        }
        parsingRule.executeWriteRule(arch);
    }
}
 