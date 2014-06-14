package edu.kaist.g4.ui;

/**
 * 
 * @FileName : VCSFuncionManager.java
 * @Package  : edu.kaist.g4.ui
 * @Author   : Hwi Ahn (ahnhwi@kaist.ac.kr)
 * @Date     : Jun 14, 2014
 * @Detail   : 
 * 
 */
public class VCSFuncionManager implements IVCSFunctions{

    ClientCommunicationManager manager;
    
    
    public VCSFuncionManager(ClientCommunicationManager manager) {
        super();
        this.manager = manager;
    }

    @Override
    public String showRecentArchitecture() {
        return manager.requestInform();
    }

    @Override
    public void commitWorkingArchitecture() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void checkoutRecentArchitecture(String path) {
        manager.requestCheckout(path);
        
    }
    

}
