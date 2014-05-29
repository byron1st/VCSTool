package edu.kaist.g4.ui;

import edu.kaist.g4.architectureAPI.CommunicationManager;

public class ClientCommunicationManager {
    
    private CommunicationManager manger;
    
    public void connectRepository(CommunicationManager manager){
        this.manger = manager;
    }
    
    public String requestCheckout(){
        return this.manger.checkoutRecentArchitecture();
    }
}
