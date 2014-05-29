package edu.kaist.g4.architectureAPI;

import edu.kaist.g4.function.architectureVersionManagement.ArchitectureVersionManager;
import edu.kaist.g4.function.architectureVersionManagement.IArchitectureVersionManager;

public class APIManager implements IArchitectureAPI{

    private IArchitectureVersionManager vmanager;
    
    public APIManager(){
        vmanager = new ArchitectureVersionManager();
    }
    
    @Override
    public String checkoutRecentArchitecture() {
        return vmanager.checkoutRecentArchitecture();
    }

}
