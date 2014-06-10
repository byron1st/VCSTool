package edu.kaist.g4.function.fileManager;

import java.io.File;

import edu.kaist.g4.data.Architecture;
import edu.kaist.g4.data.architecturalDifferentiations.ArchitecturalDifferentiations;


public class Writer{
    private Rules rule;
    
    public Writer(){
        rule = new Rules();
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
        rule.executeWriteRule(arch, dir);
    }
    
    public void writeDiffList(ArchitecturalDifferentiations diffList, String dir){
        File cDir = new File(dir);
        if(!cDir.exists()){
            cDir.mkdirs();
        }
        rule.appendDiffList(diffList, dir);
    }
}
 