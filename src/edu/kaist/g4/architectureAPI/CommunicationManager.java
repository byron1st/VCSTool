package edu.kaist.g4.architectureAPI;

public class CommunicationManager {

    private APIManager manager;
    
    public CommunicationManager() {
        // TODO Repository 구성을 여기서한다
        manager = new APIManager();
    }
    
    public String showRecentArchitecture(){
        return manager.showRecentArchitecture();
    }

    public void checkoutRecentArchitecture(String path) {
        // TODO Auto-generated method stub
        manager.checkoutRecentArchitecture(path);
    }
    
}
