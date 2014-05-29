package edu.kaist.g4.ui;

public class VCSFuncionManager implements IVCSFunctions{

    ClientCommunicationManager manager;
    
    
    public VCSFuncionManager(ClientCommunicationManager manager) {
        super();
        this.manager = manager;
    }

    @Override
    public String checkoutRecentArchitecture() {
        // TODO Auto-generated method stub
        return manager.requestCheckout();
    }

    @Override
    public void commitWorkingArchitecture() {
        // TODO Auto-generated method stub
        
    }
    

}
