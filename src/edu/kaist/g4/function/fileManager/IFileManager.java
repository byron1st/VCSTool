package edu.kaist.g4.function.fileManager;

import edu.kaist.g4.data.Architecture;

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
}
