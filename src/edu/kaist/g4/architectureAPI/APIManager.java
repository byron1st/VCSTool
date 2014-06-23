package edu.kaist.g4.architectureAPI;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import edu.kaist.g4.function.architectureVersionManagement.ArchitectureVersionManager;
import edu.kaist.g4.function.architectureVersionManagement.IArchitectureVersionManager;

public class APIManager implements IArchitectureAPI{

    private IArchitectureVersionManager vmanager;
    
    public APIManager(){
        vmanager = new ArchitectureVersionManager();
    }
    
    @Override
    public String showRecentArchitecture() {
        return vmanager.showRecentArchitecture();
    }

    @Override
    public void checkoutRecentArchitecture(String path) throws Exception {
        String origin_path = vmanager.getRecentArchPath();
        File origin_file = new File(origin_path);
        File new_file = new File(path);

        if(origin_file.isDirectory()){
            copyFolder(origin_file.getAbsolutePath(), new_file.getAbsolutePath());
        }
        
        
//        FileInputStream fis = new FileInputStream(origin_file);
//        FileOutputStream fos = new FileOutputStream(new_file);
//        
//        int data = 0;
//        while ((data = fis.read()) != -1) {
//           fos.write(data);
//        }
//        fis.close();
//        fos.close();
    }

    @Override
    public String traceVersionInfoWith(String command, String parameter) {
        String messageForConsoleWindow = vmanager.traceVersionInfoWith(command, parameter);
        return messageForConsoleWindow;
    }
    
    void copyFolder(String path, String new_path) throws Exception{
        File folder = new File(path);
        if(folder.isDirectory()){
            File new_folder = new File(new_path +"/"+ folder.getName());
            new_folder.mkdir();
            String[] list = folder.list();
            for(int i=0;i<list.length;i++){
                File f = new File(path + "/"+ list[i]);
                if(f.isDirectory()){
                    copyFolder(f.getAbsolutePath(), new_folder.getAbsolutePath());
                }
                else{
                  FileInputStream fis = new FileInputStream(f);
                  FileOutputStream fos = new FileOutputStream(new_folder.getAbsoluteFile() + "/" +f.getName());
                 
                  
                  int data = 0;
                  while ((data = fis.read()) != -1) {
                     fos.write(data);
                  }
                  fis.close();
                  fos.close();
                }
            }
 
        }
    }

    @Override
    public String commitNewArchitecture(String dirPathforNewArchitecture, String changeDecision) {
        return vmanager.commitNewArchitecture(dirPathforNewArchitecture, changeDecision);
    }
    
}
