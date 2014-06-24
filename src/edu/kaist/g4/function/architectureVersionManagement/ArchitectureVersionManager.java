package edu.kaist.g4.function.architectureVersionManagement;

import java.util.Vector;

import edu.kaist.g4.data.Architecture;
import edu.kaist.g4.data.architecturalDifferentiations.ArchitecturalDifferentiations;
import edu.kaist.g4.data.architecturalDifferentiations.ArchitectureChange;
import edu.kaist.g4.data.architecturalDifferentiations.ArchitectureChangeDecision;
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
    
    //TODO: 李⑦썑 �섏젙.
    //TODO: Constructor�먯꽌 珥덇린�뷀븯��嫄몃줈 �섏젙.
    IFileManager fileManager = new FileManager();
    
    //checkout�먯꽌��recentArchitecture瑜��ъ슜�섍린 �뚮Ц��硫ㅻ쾭蹂�닔濡��좎뼵
    Architecture recentArchitecture;
    
    //diff �붿껌���뚮쭏��filemanager瑜�遺덈윭�ㅻ뒗嫄�留됯린 �꾪빐 硫ㅻ쾭蹂�닔濡��좎뼵
    ArchitecturalDifferentiations currentDiffList;
   
    
    @Override
    public String commitNewArchitecture(String dirPathforNewArchitecture, String changeDecision) {
        //TODO: �ш린��Sequence �ㅼ씠�닿렇���댁슜 �ｊ린
        Architecture workingArchitecture = fileManager.readWorkingArchitecture(dirPathforNewArchitecture);
        
        if(recentArchitecture == null)
            recentArchitecture = fileManager.readRecentArchitecture();
        
        INewVersionGenerator newVersionGenerator = new NewVersionGenerator(workingArchitecture, recentArchitecture);
        Vector<ArchitectureChange> architectureChange = newVersionGenerator.buildNewVersion();
        if(architectureChange.size() == 0){
            return null;
        }
        //TODO: Change Decision��愿�젴���댁슜���ш린���ｊ린
        ArchitectureChangeDecision architectureChangeDecision = new ArchitectureChangeDecision();
        architectureChangeDecision.setArchitectureChangeDrivers(changeDecision);
        architectureChangeDecision.setArchitectureChanges(architectureChange);
        
//        workingArchitecture = fileManager.readWorkingArchitecture(dirPathforNewArchitecture);   //源딆� 蹂듭궗媛��놁뼱���쇰떒 �ㅼ떆 遺덈윭��        ArchitecturalDifferentiations newDiffList = new ArchitecturalDifferentiations(architectureChangeDecision, architectureChange);
        ArchitecturalDifferentiations newDiffList = new ArchitecturalDifferentiations(architectureChangeDecision, architectureChange);
        

        
        fileManager.removeRecentArchitecture();
        fileManager.writeNewRecentArchitecture(workingArchitecture);
        fileManager.appendDiffList(newDiffList);
        
        return newDiffList.getArchitectureChanges().size() + " architecture changes derived from " + newDiffList.getArchitectureChangeDecision().getArchitectureChangeDrivers();
    }

    @Override
    public String showRecentArchitecture() {
        // TODO Auto-generated method stub
        if(recentArchitecture == null)
            recentArchitecture = fileManager.readRecentArchitecture();
        
        return recentArchitecture.overallInformation();
        
    }

    @Override
    public String traceVersionInfoWith(String command, String parameter) {
        Vector<ArchitecturalDifferentiations> allLists = null;
        if(currentDiffList == null){
            allLists = fileManager.readDiffList();
            if(allLists == null) 
               System.out.println("exit diff mode");
            currentDiffList = allLists.get(0);
        }
        IVersionInfoTracer versionInfoTracer = new VersionInfoTracer(currentDiffList,allLists);
        String printedMessage = "";
        if(command.equals("ViewAll")) //TODO: �ν썑 Enum�쇰줈 蹂�꼍.
            printedMessage = versionInfoTracer.printAllDiffs(parameter);
        else if(command.equals("ViewAllList")){
            printedMessage = versionInfoTracer.printAllLists(parameter);
        }
        else
            printedMessage = "This is not a supported command."; //TODO: �ν썑 硫붿꽭吏�뱾 ��怨녹뿉 紐⑥쑝湲�
        return printedMessage;
    }
    
    public String getRecentArchPath() {
        // TODO Auto-generated method stub
        return "RecentArchitecture";
    }
    
    
}
