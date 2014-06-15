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

    public void callVCSFunction(VCSFunctions requestedFunction, String[] args) throws IOException {
        switch(requestedFunction) {
        case SHOW:
            functionShow();
            break;
        case CHECKOUT:
            functionCheckout();
            break;
        case COMMIT:
            functionCommit(args);
            break;
        default:
            break;
        }
    }
    
    private void functionCommit(String[] args) throws IOException {
        String dirPathforNewArchitecture = args[1];
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean isDone = false;
        String changeDecision = null;
        while(!isDone) {
            System.out.println("Type architecture change decision "
                    + "\n(Recommend use the ID for a change decision "
                    + "\nand write its detail in other separate documents.)");
            changeDecision = br.readLine();
            if(changeDecision == null)
                System.out.println("Don't leave it a blank.");
            else
                isDone = true;
        }
        String completeMessage = manager.requestCommit(dirPathforNewArchitecture, changeDecision);
        System.out.println(completeMessage);
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
        while(file == null){
            System.out.println("Copy to "); 
            path_input = br.readLine();
            file = new File(path_input);

            // 정상 경로 체크
            if(!file.exists()){ 
                System.out.println("Wrong path.");
                file = null;
            }
            
            //TODO: Duplication check
        }
        manager.requestCheckout(path_input);
        
        System.out.println("Complete Checkout");
    }
}
