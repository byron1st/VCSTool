package edu.kaist.g4.data;

import java.util.HashMap;

/**
 * 
 * @author Junhaeng Heo
 *
 */


public class Architecture implements IArchitecture{

    protected String archname, id;

    protected View[][] views;
    protected HashMap<String, TraceabilityLink> tLinks;
    
    //객체생성을 언제, 어떻게
    public Architecture(String archname){
        views = new View[ViewType.values().length][2];  //recnentArchitecture, workingArchitecture
        tLinks = new HashMap<String, TraceabilityLink>();
        
        id = archname + System.currentTimeMillis();
        this.archname = archname;
    }

    @Override
    public void setView(ViewType type, ViewVesion version, View view){

        views[type.ordinal()][version.ordinal()] = view;     
    }

    @Override
    public void addAnComponent(ViewType type, ViewVesion version,  ArchitectureElement ae){
        
        views[type.ordinal()][version.ordinal()].addComponent(ae);
    }

    @Override
    public void addAnConnector(ViewType type, ViewVesion version,  Relation r){

        views[type.ordinal()][version.ordinal()].addConnector(r);
       
    }
    

    
    
    
    
    
    @Override
    public String overallInformation() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void addTracebilityLink(ViewType source, ViewType dest){
        // TODO Auto-generated method stub
        
    }

    @Override
    public View getView(ViewType type, ViewVesion vesion) {
        // TODO Auto-generated method stub
        return views[type.ordinal()][vesion.ordinal()];
    }



}
