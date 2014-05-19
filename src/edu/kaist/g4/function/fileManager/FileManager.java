package edu.kaist.g4.function.fileManager;

import edu.kaist.g4.data.Architecture;
import edu.kaist.g4.data.architecturalDifferentiations.ArchitecturalDifferentiations;

public class FileManager implements IFileManager{
    
    // TODO: Singleton 으로 할 필요가 있을까? Github 이슈 게시판 참조.

    @Override
    public Architecture readWorkingArchitecture(String filePath) {
        // TODO 만들어줘!! by 휘. ~(-_ -)~
        return null;
    }

    @Override
    public Architecture readRecentArchitecture() {
        // TODO 만들어줘!! by 휘. ~(-_ -)~
        return null;
    }

    @Override
    public void writeNewRecentArchitecture(Architecture workingArchitecture) {
        // TODO 만들어줘!! by 휘. ~(-_ -)~
        
    }

    @Override
    public void appendDiffList(
            ArchitecturalDifferentiations addedArchitecturalDifferentiations) {
        // TODO 만들어줘!! by 휘. ~(-_ -)~
        
    }

}
