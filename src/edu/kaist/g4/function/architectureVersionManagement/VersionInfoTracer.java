package edu.kaist.g4.function.architectureVersionManagement;

import java.util.Iterator;
import java.util.Vector;

import edu.kaist.g4.data.architecturalDifferentiations.ArchitecturalDifferentiations;
import edu.kaist.g4.data.architecturalDifferentiations.ArchitectureChange;

public class VersionInfoTracer implements IVersionInfoTracer{
    private ArchitecturalDifferentiations diffList;
    private Vector<ArchitecturalDifferentiations> diffAllList;
    
    
    public Vector<ArchitecturalDifferentiations> getDiffAllList() {
        return diffAllList;
    }

    public void setDiffAllList(Vector<ArchitecturalDifferentiations> diffAllList) {
        this.diffAllList = diffAllList;
    }

    public VersionInfoTracer(ArchitecturalDifferentiations currentDiffList, Vector<ArchitecturalDifferentiations> allLists) {
        this.diffList = currentDiffList;
        diffAllList = allLists;
    }
    
    public String printAllDiffs(String parameter) {
        String printedMessage = "Revision timestamp: " + diffList.getId() + "\n";
        printedMessage += "ChangeDecision id: " + diffList.getId() + "\n";

        printedMessage += "Description: "
                + diffList.getArchitectureChangeDecision()
                        .getArchitectureChangeDrivers() + "\n";
        printedMessage += "Difference List\n";
        Vector<ArchitectureChange> changes = diffList
                .getArchitectureChanges();
        for (ArchitectureChange change : changes) {
            printedMessage += " - id:" + change.getParameter()
                    + " operation: " + change.getChangeOperation()
                    + " message: " + change.getMessage() + '\n';
        }
        printedMessage += "\n\n";

        return printedMessage;
    }

    @Override
    public String printAllLists(String parameter) {
        // TODO Auto-generated method stub
        String printedMessage = "";
        ArchitecturalDifferentiations diffList;
        
        Iterator<ArchitecturalDifferentiations> it = diffAllList.iterator();
        while (it.hasNext()) {
            diffList = it.next();
            printedMessage = "Revision timestamp: " + diffList.getId() + "\n";
            printedMessage += "ChangeDecision id: " + diffList.getId() + "\n";

            printedMessage += "Description: "
                    + diffList.getArchitectureChangeDecision()
                            .getArchitectureChangeDrivers() + "\n";
            printedMessage += "Difference List\n";
            Vector<ArchitectureChange> changes = diffList
                    .getArchitectureChanges();
            for (ArchitectureChange change : changes) {
                printedMessage += " - id:" + change.getParameter()
                        + " operation: " + change.getChangeOperation()
                        + " message: " + change.getMessage() + '\n';
            }
            printedMessage += "\n\n";
        }
        return printedMessage;

    }

}
