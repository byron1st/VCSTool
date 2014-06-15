package edu.kaist.g4.data.architecturalDifferentiations;

import java.util.Vector;

/**
 * 
 * @FileName : ArchitecturalDifferentiations.java
 * @Package  : edu.kaist.g4.data.architecturalDifferentiations
 * @Author   : Hwi Ahn (ahnhwi@kaist.ac.kr)
 * @Date     : 2014
 * @Detail   : Architecture Change들과 Architecture Change Decision들의 리스트를 갖고 있는 클래스 
 * 
 */
public class ArchitecturalDifferentiations implements IArchitecturalDifferentiations {
    
    private Vector<ArchitectureChange> architectureChanges = new Vector<ArchitectureChange>();
    private ArchitectureChangeDecision architectureChangeDecision;
    
    public ArchitecturalDifferentiations(ArchitectureChangeDecision architectureChangeDecisions,
            Vector<ArchitectureChange> architectureChanges) {
        this.architectureChangeDecision = architectureChangeDecisions;
        this.architectureChanges = architectureChanges;
        
    }

    public Vector<ArchitectureChange> getArchitectureChanges() {
        return architectureChanges;
    }
    
    public void setArchitectureChanges(
            Vector<ArchitectureChange> architectureChanges) {
        this.architectureChanges = architectureChanges;
    }
    
    public ArchitectureChangeDecision getArchitectureChangeDecision() {
        return architectureChangeDecision;
    }
    
    public void setArchitectureChangeDecision(ArchitectureChangeDecision architectureChangeDecision) {
        this.architectureChangeDecision = architectureChangeDecision;
    }
}
