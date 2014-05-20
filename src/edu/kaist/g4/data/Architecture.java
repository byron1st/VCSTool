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

    protected Vector<ArchitectureModel>[] viewlist;
    protected HashMap<String, TraceabilityLink> tLinks;
    
    //객체생성을 언제, 어떻게
    public Architecture(String archname){
        viewlist = new Vector[ViewType.values().length];
        for(int i=0;i<viewlist.length;i++){
            viewlist[i] = new Vector<ArchitectureModel>();
        }
        tLinks = new HashMap<String, TraceabilityLink>();
        
        id = archname + System.currentTimeMillis();
        this.archname = archname;
    }

    @Override
    public void addArchitectureModel(ViewType type, ArchitectureModel model){

        viewlist[type.ordinal()].add(model);     
    }

    @Override
    public void addArchitectureElement(ViewType type, String name, ArchitectureElement ae){
        
        Iterator<ArchitectureModel> it = viewlist[type.ordinal()].iterator();
        ArchitectureModel el;
        while(it.hasNext()){
            el = it.next();
            if(el.getName().equals(name))
                el.addComponent(ae);                
        }
    }

    @Override
    public void addRelation(ViewType type, String name, Relation r){

        Iterator<ArchitectureModel> it = viewlist[type.ordinal()].iterator();
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

        Iterator<ArchitectureModel> it = viewlist[type.ordinal()].iterator();
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
        Vector<ArchitectureModel> result = new Vector<ArchitectureModel>();
        for(int i=0;i<viewlist.length;i++){
            Iterator<ArchitectureModel> it = viewlist[i].iterator();
            while(it.hasNext()){                
                result.add(it.next());
            }
        }
        return result;
    }



}
