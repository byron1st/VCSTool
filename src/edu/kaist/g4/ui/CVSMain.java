package edu.kaist.g4.ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

import edu.kaist.g4.architectureAPI.CommunicationManager;

public class CVSMain {
	public static void main(String[] args){
	    
	    ClientCommunicationManager cmanager = new ClientCommunicationManager();
	    cmanager.connectRepository(new CommunicationManager());
	    
	    IVCSFunctions vcsf = new VCSFuncionManager(cmanager);
	    
	    
	    switch(args.length){
	    case 0:
	        System.out.println("type command");
	        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	        String line = null;
	        try {
	            while((line = br.readLine()) != null) {
	                if(line.indexOf("exit") != -1) {
	                    System.exit(0);
	                } else if(line.equals("show")){
	                    functionShow(vcsf);
	                } else if(line.equals("checkout")){
	                    functionCheckout(vcsf, br);
	                }
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        break;
	    case 1:
	        //TODO: 나중에 어떻게 할지 생각해보기.
//	        if(args[0].equals("checkout"))
//	        {
//	            String output = vcsf.showRecentArchitecture();
//	            System.out.println(output);
//	        }
	        break;
	    }
	}
	
	private static void functionShow(IVCSFunctions vcsf) {
	    String output = vcsf.showRecentArchitecture();
        System.out.println(output);
    }

    private static void functionCheckout(IVCSFunctions vcsf, BufferedReader br) throws IOException {
	    String path_input = null;
        System.out.println("Type path :");
        File file = null;
        while(file == null || !file.exists()){
            System.out.println("Copy to "); 
            path_input = br.readLine();
            file = new File(path_input);
        }
        vcsf.checkoutRecentArchitecture(path_input);
        
        System.out.println("Complete Checkout");
	}
}
