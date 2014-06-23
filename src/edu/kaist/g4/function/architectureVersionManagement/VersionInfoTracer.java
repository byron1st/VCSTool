package edu.kaist.g4.function.architectureVersionManagement;

import java.util.Vector;

import edu.kaist.g4.data.architecturalDifferentiations.ArchitecturalDifferentiations;
import edu.kaist.g4.data.architecturalDifferentiations.ArchitectureChange;

public class VersionInfoTracer implements IVersionInfoTracer{
    private ArchitecturalDifferentiations diffList;
    private ArchitecturalDifferentiations[] diffAllList;
    
    
    public ArchitecturalDifferentiations[] getDiffAllList() {
        return diffAllList;
    }

    public void setDiffAllList(ArchitecturalDifferentiations[] diffAllList) {
        this.diffAllList = diffAllList;
    }

    public VersionInfoTracer(ArchitecturalDifferentiations currentDiffList, ArchitecturalDifferentiations[] allLists) {
        this.diffList = currentDiffList;
        diffAllList = allLists;
    }
    
    public String printAllDiffs(String parameter) {
        String printedMessage = "Differences id: " + diffList.getId() + "\n";
  
        printedMessage += "Description: "+ diffList.getArchitectureChangeDecision().getArchitectureChangeDrivers();
        printedMessage += "Difference List";
        Vector<ArchitectureChange> changes = diffList.getArchitectureChanges();
        for(ArchitectureChange change : changes){
            printedMessage += " - id:" + change.getParameter() + " operation: " + change.getChangeOperation() +" message: "+ change.getMessage();
        }
        printedMessage += "\n\n";

        return printedMessage;
    }

    @Override
    public String printAllLists(String parameter) {
        // TODO Auto-generated method stub
        return null;
    }

}
