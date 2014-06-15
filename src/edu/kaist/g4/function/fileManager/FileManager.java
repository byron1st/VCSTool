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
    private Reader reader;
    private Writer writer;
    
    public FileManager(){
        writer = new Writer();
    }

    @Override
    public Architecture readWorkingArchitecture(String dirPath) {
        reader = new Reader();
        try{
            SAXParserFactory spf = SAXParserFactory.newInstance();  
            SAXParser sp = spf.newSAXParser();  
            XMLReader xr = sp.getXMLReader();  
            xr.setContentHandler(reader);
            
            File file = new File(dirPath+"/Model");
            File[] listFiles = file.listFiles();
            for(File f : listFiles){   
                if(f.getName().endsWith(".xml")){
                    xr.parse(new InputSource(new FileInputStream(f)));
                    reader.addArchitectureModel();
                }
            }
            
            File tFile = new File(dirPath+"/Traceability");
            File[] tListFiles = tFile.listFiles();            
            for(File f : tListFiles){
                if(f.getName().endsWith(".xml")){
                    xr.parse(new InputSource(new FileInputStream(f)));
                    reader.addTraceability();
                }
            }
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        return reader.getArchitecture();
    }

    @Override
    public Architecture readRecentArchitecture() {
        reader = new Reader();
        try{
            SAXParserFactory spf = SAXParserFactory.newInstance();  
            SAXParser sp = spf.newSAXParser();  
            XMLReader xr = sp.getXMLReader();
            xr.setContentHandler(reader);
            
            File mFile = new File("RecentArchitecture/Model");
            if(!mFile.exists()){
                mFile.mkdirs();
            }
            File[] mListFiles = mFile.listFiles();
            
            for(File f : mListFiles){   
                if(f.getName().endsWith(".xml")){
                    xr.parse(new InputSource(new FileInputStream(f)));
                    reader.addArchitectureModel();
                }
            }
            
            File tFile = new File("RecentArchitecture/Traceability");
            if(!tFile.exists()){
                tFile.mkdirs();
            }            
            File[] tListFiles = tFile.listFiles();            
            for(File f : tListFiles){
                if(f.getName().endsWith(".xml")){
                    xr.parse(new InputSource(new FileInputStream(f)));
                    reader.addTraceability();
                }
            }
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        return reader.getArchitecture();
    }

    @Override
    public void removeRecentArchitecture(){
        File rFile = new File("RecentArchitecture/Model");
        File[] recentFiles = rFile.listFiles();
        
        for(File f : recentFiles){
            f.delete();
        }
        
        File tFile = new File("RecentArchitecture/Traceability/traceability.xml");
        tFile.delete();
    }
    
    @Override
    public void writeNewRecentArchitecture(Architecture workingArchitecture) {
//        reader = new Reader();
        writer.writeArchitecture(workingArchitecture, "RecentArchitecture");
    }

    @Override
    public void appendDiffList(ArchitecturalDifferentiations addedArchitecturalDifferentiations) {
        writer.writeDiffList(addedArchitecturalDifferentiations, "DifferencesList");
    }

    @Override
    public ArchitecturalDifferentiations readDiffList() {
        // TODO 쌈빡하게 하나 만들어주삼.
        return null;
    }

}
