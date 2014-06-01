package edu.kaist.g4.function.architectureVersionManagement;

import java.util.Vector;

import edu.kaist.g4.data.Architecture;
import edu.kaist.g4.data.ArchitectureModel;
import edu.kaist.g4.data.architecturalDifferentiations.ArchitectureChange;
import edu.kaist.g4.function.fileManager.XMLParsingRules;

/**
 * 
 * @FileName : NewVersionGenerator.java
 * @Package  : edu.kaist.g4.function.architectureVersionManagement
 * @Author   : Hwi Ahn (ahnhwi@kaist.ac.kr)
 * @Date     : 2014
 * @Detail   : 새로운 아키텍처 버전을 생성하는 곳으로, 아키텍처 요소들별로 변경사항을 체크해서 그 아키텍처 요소의 revision 정보를 업데이트.
 * 
 */
public class NewVersionGenerator implements INewVersionGenerator{
    Architecture workingArchitecture;
    Architecture recentArchitecture;
    XMLParsingRules rule;
    
    public NewVersionGenerator(Architecture workingArchitecture, Architecture recentArchitecture) {
        this.workingArchitecture = workingArchitecture;
        this.recentArchitecture = recentArchitecture;
    }
    
    @Override
    public Vector<ArchitectureChange> buildNewVersion() {
        //change 정보를 만들어서 리턴하는 함수
        Vector<ArchitectureModel> workingModels = workingArchitecture.getArchitectureModels();
        Vector<ArchitectureModel> recentModels = recentArchitecture.getArchitectureModels();
        
        Vector<ArchitectureChange> archiChanges = new GraphComparer().Compare(recentModels, workingModels);
    
        return archiChanges;
    }

}
