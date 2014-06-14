package edu.kaist.g4.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @FileName : VCSFuncionManager.java
 * @Package  : edu.kaist.g4.ui
 * @Author   : Hwi Ahn (ahnhwi@kaist.ac.kr)
 * @Date     : Jun 14, 2014
 * @Detail   : 
 * 
 */
public class VCSFuncionManager {
    //TODO: manager를 바로 호출하는 방식이 올바른가?
    ClientCommunicationManager manager;
    
    
    public VCSFuncionManager(ClientCommunicationManager manager) {
        super();
        this.manager = manager;
    }

    public void commitWorkingArchitecture() {
        // TODO Auto-generated method stub
        
    }

    public void checkoutRecentArchitecture(String path) {
        manager.requestCheckout(path);
    }

    public void callVCSFunction(VCSFunctions requestedFunction) throws IOException {
        switch(requestedFunction) {
        case SHOW:
            functionShow();
            break;
        case CHECKOUT:
            functionCheckout();
            break;
        default:
            break;
        }
    }
    
    private void functionShow() {
        String output = manager.requestInform();
        System.out.println(output);
    }

    private void functionCheckout() throws IOException {
        String path_input = null;
        System.out.println("Type path :");
        File file = null;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while(file == null || !file.exists()){
            System.out.println("Copy to "); 
            path_input = br.readLine();
            file = new File(path_input);
        }
        manager.requestCheckout(path_input);
        
        System.out.println("Complete Checkout");
    }
}
