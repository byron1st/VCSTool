package edu.kaist.g4.ui;

public class VCSFuncionManager implements IVCSFunctions{

    ClientCommunicationManager manager;
    
    
    public VCSFuncionManager(ClientCommunicationManager manager) {
        super();
        this.manager = manager;
    }

    @Override
    public String showRecentArchitecture() {
        // TODO Auto-generated method stub
        return manager.requestInform();
    }

    @Override
    public void commitWorkingArchitecture() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void checkoutRecentArchitecture(String path) {
        // TODO Auto-generated method stub
        manager.requestCheckout(path);
        
    }
    

}
