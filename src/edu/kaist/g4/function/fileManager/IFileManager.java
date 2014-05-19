package edu.kaist.g4.function.fileManager;

import edu.kaist.g4.data.Architecture;
import edu.kaist.g4.data.architecturalDifferentiations.ArchitecturalDifferentiations;

public interface IFileManager {
    /**
     * @Method Name : readWorkingArchitecture
     * @Detail      : 유저가 commitNewArchitecture를 하면, filePath를 받아와서 Architecture를 읽어오는 함수.
     *
     * @param filePath
     * @return
     */
    public Architecture readWorkingArchitecture(String filePath);
    
    /**
     * @Method Name : readRecentArchitecture
     * @Detail      : 시스템에 이미 저장되어 있던 가장 최근 Architecture를 불러오는 함수. 
     *                Path는 이미 시스템에서 알고 있을 것이기 때문에 따로 변수로 넘기진 않음. 
     *
     * @return
     */
    public Architecture readRecentArchitecture();
    
    /**
     * @Method Name : writeNewRecentArchitecture
     * @Detail      : 가장 최근 Architecture를 recent architecture로 업데이트하는 함수. 
     *
     * @param workingArchitecture
     */
    public void writeNewRecentArchitecture(Architecture workingArchitecture);
    
    /**
     * @Method Name : appendDiffList
     * @Detail      : 새롭게 추가된 differentiations 정보를 기존 differentiations 정보를 기록해둔 log에 append하는 함수.
     *                TODO: 현재는 Architecture Change만 있고 Architecture Change Decision은 없음. (null로 처리됨.)
     *
     * @param addedArchitecturalDifferentiations
     */
    public void appendDiffList(ArchitecturalDifferentiations addedArchitecturalDifferentiations);
}
