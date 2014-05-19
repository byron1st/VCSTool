package edu.kaist.g4.function.architectureVersionManagement;

import java.util.Vector;

import edu.kaist.g4.data.Architecture;
import edu.kaist.g4.data.architecturalDifferentiations.ArchitecturalDifferentiations;
import edu.kaist.g4.data.architecturalDifferentiations.ArchitectureChange;
import edu.kaist.g4.function.fileManager.FileManager;
import edu.kaist.g4.function.fileManager.IFileManager;

/**
 * 
 * @FileName : ArchitectureVersionManager.java
 * @Package  : edu.kaist.g4.function.architectureVersionManagement
 * @Author   : Hwi Ahn (ahnhwi@kaist.ac.kr)
 * @Date     : 2014
 * @Detail   :
 * 
 */
public class ArchitectureVersionManager {
    
    //TODO: 차후 수정.
    IFileManager fileManager = new FileManager();
    
    public void commitNewArchitecture(String filePathforNewArchitecture) {
        //TODO: 여기다 Sequence 다이어그램 내용 넣기
        Architecture workingArchitecture = fileManager.readWorkingArchitecture(filePathforNewArchitecture);
        Architecture recentArchitecture = fileManager.readRecentArchitecture();
        
        INewVersionGenerator newVersionGenerator = new NewVersionGenerator(workingArchitecture, recentArchitecture);
        Vector<ArchitectureChange> architectureChange = newVersionGenerator.buildNewVersion();
        
        //TODO: Change Dicision에 관련된 내용을 여기에 넣기
        
        ArchitecturalDifferentiations newDiffList = new ArchitecturalDifferentiations(null, architectureChange);
        
        fileManager.writeNewRecentArchitecture(workingArchitecture);
        fileManager.appendDiffList(newDiffList);
    }
}
