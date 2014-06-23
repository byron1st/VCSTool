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
        try{
            manager.checkoutRecentArchitecture(path);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }
    
    public String commitNewArchitecture(String dirPathforNewArchitecture, String changeDecision) {
        return manager.commitNewArchitecture(dirPathforNewArchitecture, changeDecision);
    }
    public void showDiffList(){
        System.out.println(manager.traceVersionInfoWith("ViewAll", null));
    }
}
