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
/**
 * 
 * @author : Junhaeng Heo
 * Implement IArchitectureVersionManager
 *
 */
public class ArchitectureVersionManager implements IArchitectureVersionManager{
    
    //TODO: 차후 수정.
    //TODO: Constructor에서 초기화하는 걸로 수정.
    IFileManager fileManager = new FileManager();
    
    //checkout에서도 recentArchitecture를 사용하기 때문에 멤버변수로 선언
    Architecture recentArchitecture;
    
    //diff 요청할 때마다 filemanager를 불러오는걸 막기 위해 멤버변수로 선언
    ArchitecturalDifferentiations currentDiffList;
   
    
    
    public void commitNewArchitecture(String dirPathforNewArchitecture) {
        //TODO: 여기다 Sequence 다이어그램 내용 넣기
        Architecture workingArchitecture = fileManager.readWorkingArchitecture(dirPathforNewArchitecture);
        
        if(recentArchitecture == null)
            recentArchitecture = fileManager.readRecentArchitecture();
        
        INewVersionGenerator newVersionGenerator = new NewVersionGenerator(workingArchitecture, recentArchitecture);
        Vector<ArchitectureChange> architectureChange = newVersionGenerator.buildNewVersion();
        
        //TODO: Change Dicision에 관련된 내용을 여기에 넣기
        
        workingArchitecture = fileManager.readWorkingArchitecture(dirPathforNewArchitecture);   //깊은 복사가 없어서 일단 다시 불러옴
        ArchitecturalDifferentiations newDiffList = new ArchitecturalDifferentiations(null, architectureChange);
        
        fileManager.writeNewRecentArchitecture(workingArchitecture);
        fileManager.appendDiffList(newDiffList);

    }



    @Override
    public String checkoutRecentArchitecture() {
        // TODO Auto-generated method stub
        if(recentArchitecture == null)
            recentArchitecture = fileManager.readRecentArchitecture();
        
        return recentArchitecture.overallInformation();
        
    }



    @Override
    public String traceVersionInfoWith(String command) {
        if(currentDiffList == null)
            currentDiffList = fileManager.readDiffList();
        IVersionInfoTracer versionInfoTracer = new VersionInfoTracer(currentDiffList);
        return versionInfoTracer.orderCommand(command);
    }
    
    
}
