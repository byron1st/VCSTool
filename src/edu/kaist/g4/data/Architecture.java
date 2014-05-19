package edu.kaist.g4.data;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

/**
 * 
 * @author Junhaeng Heo
 *
 */


public class Architecture implements IArchitecture{

    protected String archname, id;

    protected Vector<ArchitectureModel>[] views;
    protected HashMap<String, TraceabilityLink> tLinks;
    
    //객체생성을 언제, 어떻게
    public Architecture(String archname){
        views = new Vector[ViewType.values().length];
        for(int i=0;i<views.length;i++){
            views[i] = new Vector<ArchitectureModel>();
        }
        tLinks = new HashMap<String, TraceabilityLink>();
        
        id = archname + System.currentTimeMillis();
        this.archname = archname;
    }

    @Override
    public void addView(ViewType type, ArchitectureModel view){

        views[type.ordinal()].add(view);     
    }

    @Override
    public void addAnComponent(ViewType type, String name, ArchitectureElement ae){
        
        Iterator<ArchitectureModel> it = views[type.ordinal()].iterator();
        ArchitectureModel el;
        while(it.hasNext()){
            el = it.next();
            if(el.getName().equals(name))
                el.addComponent(ae);                
        }
    }

    @Override
    public void addAnConnector(ViewType type, String name, Relation r){

        Iterator<ArchitectureModel> it = views[type.ordinal()].iterator();
        ArchitectureModel el;
        while(it.hasNext()){
            el = it.next();
            if(el.getName().equals(name))
                el.addConnector(r);                
        }
       
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
    public ArchitectureModel getView(ViewType type, String name) {

        Iterator<ArchitectureModel> it = views[type.ordinal()].iterator();
        ArchitectureModel el;
        while(it.hasNext()){
            el = it.next();
            if(el.getName().equals(name))
                return el;                
        }
        return null;
    }

    @Override
    public Vector<ArchitectureModel> getArchitectureModels() {
        // TODO 만들어줘!!!
        return null;
    }



}
