package edu.kaist.g4.ui;

import edu.kaist.g4.architectureAPI.CommunicationManager;

/**
 * 
 * @FileName : ClientCommunicationManager.java
 * @Package  : edu.kaist.g4.ui
 * @Author   : Hwi Ahn (ahnhwi@kaist.ac.kr)
 * @Date     : Jun 14, 2014
 * @Detail   : 아키텍처 문서를 저장해두는 저장소와 실제 작업공간이 분리되어 있을 경우, 원격 통신을 위한 클래스.
 *             현재는 바로 함수를 콜하는 형태로 되어 있지만, 원격 통신 시 RPC를 위한 코드가 삽입.
 * 
 */
public class ClientCommunicationManager {
    
    private CommunicationManager manager;
    
    public void connectRepository(CommunicationManager manager){
        this.manager = manager;
    }
    
    public String requestInform(){
        return this.manager.showRecentArchitecture();
    }
    
    public void requestCheckout(String path){
        this.manager.checkoutRecentArchitecture(path);
    }
    
    public String requestCommit(String dirPathforNewArchitecture, String changeDecision) {
        return this.manager.commitNewArchitecture(dirPathforNewArchitecture, changeDecision);
    }
    
    public void requestDiffList(){
       this.manager.showDiffList();
    }
}
