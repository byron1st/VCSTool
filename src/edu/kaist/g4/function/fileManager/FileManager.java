package edu.kaist.g4.function.fileManager;

import java.io.File;
import java.io.FileInputStream;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import edu.kaist.g4.data.Architecture;
import edu.kaist.g4.data.architecturalDifferentiations.ArchitecturalDifferentiations;

public class FileManager implements IFileManager{
    
    // TODO: Singleton 으로 할 필요가 있을까? Github 이슈 게시판 참조.
    Reader reader;
    
    public FileManager(){
        reader = new Reader();
    }

    @Override
    public Architecture readWorkingArchitecture(String filePath) {
        return null;
    }

    @Override
    public Architecture readRecentArchitecture() {
        try{
            File file = new File("Tool Input_Module View XMI 1.1.xml");   
            
            SAXParserFactory spf = SAXParserFactory.newInstance();  
            SAXParser sp = spf.newSAXParser();  
            XMLReader xr = sp.getXMLReader();  
                
            xr.setContentHandler(reader);  
    
            xr.parse(new InputSource(new FileInputStream(file)));
            reader.makeArchitecture();
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return null;
    }

    @Override
    public void writeNewRecentArchitecture(Architecture workingArchitecture) {
        
    }

    @Override
    public void appendDiffList(ArchitecturalDifferentiations addedArchitecturalDifferentiations) {
        
    }

}
