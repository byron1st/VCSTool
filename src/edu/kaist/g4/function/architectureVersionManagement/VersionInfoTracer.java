package edu.kaist.g4.function.architectureVersionManagement;

import edu.kaist.g4.data.architecturalDifferentiations.ArchitecturalDifferentiations;

public class VersionInfoTracer implements IVersionInfoTracer{
    private ArchitecturalDifferentiations diffList;
    
    
    public VersionInfoTracer(ArchitecturalDifferentiations currentDiffList) {
        this.diffList = currentDiffList;
    }
    
    public String orderCommand(String command) {
        String printedMessage = "";
        
        //TODO: Algorithm to process the command.
        
        return printedMessage;
    }

}
