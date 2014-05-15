package edu.kaist.g4.data;

import java.awt.Component;
import java.util.Vector;

/**
 * 
 * @author Junhaeng Heo
 *
 */

public class Architecture implements IArchitecture{

    protected String archname,  version,    id;
    
    
    protected Vector<View> views;
    protected Vector<TraceabilityLink> tLinks;
    
    //객체생성을 언제, 어떻게
    public Architecture(String archname){
        views = new Vector<View>();
        tLinks = new Vector<TraceabilityLink>();
        
        id = archname + System.currentTimeMillis();
        version = "??";
        this.archname = archname;
    }

    @Override
    public void addView(ViewType type, View view) throws Exception {

        type.compareTo(ViewType.CNC);
        switch(type){

        default:
            throw new Exception("not covered viewpoint");
        }
    }

    @Override
    public void addAnComponent(ViewType type, ArchitectureElement ae) throws Exception {
        // TODO Auto-generated method stub
        switch(type){
        
        default:
            throw new Exception("not covered viewpoint");
        }
        
    }

    @Override
    public void addAnConnector(ViewType type, Relation r) throws Exception {
        // TODO Auto-generated method stub
        switch(type){
        
        default:
            throw new Exception("not covered viewpoint");
        }
        
    }

    @Override
    public String overallInformation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addTracebilityLink(ViewType source, ViewType dest)
            throws Exception {
        // TODO Auto-generated method stub
        
    }
    


}
