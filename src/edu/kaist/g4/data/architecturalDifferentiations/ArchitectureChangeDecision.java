package edu.kaist.g4.data.architecturalDifferentiations;

import java.util.Vector;

/**
 * 
 * @FileName : ArchitectureChangeDecision.java
 * @Package  : edu.kaist.g4.data.architecturalDifferentiations
 * @Author   : Hwi Ahn (ahnhwi@kaist.ac.kr)
 * @Date     : 2014
 * @Detail   : 1개의 Architecture Change Decision을 표현하는 클래스.
 * 
 */
public class ArchitectureChangeDecision {
    private String id;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private String architectureChangeDrivers;
    private Vector<ArchitectureChange> architectureChanges = new Vector<ArchitectureChange>();
    
    public String getArchitectureChangeDrivers() {
        return architectureChangeDrivers;
    }
    
    public void setArchitectureChangeDrivers(String architectureChangeDrivers) {
        this.architectureChangeDrivers = architectureChangeDrivers;
    }
    
    public Vector<ArchitectureChange> getArchitectureChanges() {
        return architectureChanges;
    }
    
    public void setArchitectureChanges(
            Vector<ArchitectureChange> architectureChanges) {
        this.architectureChanges = architectureChanges;
    }
    
}
