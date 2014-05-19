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
    private Vector<ArchitectureChangeDecision> architectureChangeDecision = new Vector<ArchitectureChangeDecision>();
    
    
    
    
}
