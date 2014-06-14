package edu.kaist.g4.ui;

import edu.kaist.g4.architectureAPI.CommunicationManager;

public class CVSMain {
	public static void main(String[] args){
	    
	    ClientCommunicationManager cmanager = new ClientCommunicationManager();
	    cmanager.connectRepository(new CommunicationManager());
	    
	    VCSFuncionManager vcsFunctionManager = new VCSFuncionManager(cmanager);
	    try {
    	    switch(args.length){
    	    case 0:
    	        //TODO: Version 정보 출력.
    	        /*System.out.println("type command");
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
    	        }*/
    	        break;
    	    case 1:
    	        //TODO: 나중에 어떻게 할지 생각해보기.
    	        switch(args[0]) {
    	        case "show":
    	            vcsFunctionManager.callVCSFunction(VCSFunctions.SHOW);
    	            break;
    	        case "checkout":
    	            vcsFunctionManager.callVCSFunction(VCSFunctions.CHECKOUT);
    	            break;
    	        default:
    	            System.out.println("Wrong argument.");
    	            break;
    	        }
    	        break;
    	    default:
    	        System.out.println("Wrong argument.");
    	        //TODO: argument 값이 잘못되었다는 Error 메세지.
    	        break;
    	    }
	    } catch(Exception e) {
	        System.out.println("Exception error : "+ e.getMessage());
	    }
	}
}
