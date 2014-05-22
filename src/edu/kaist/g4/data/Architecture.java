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
    protected Vector<TraceabilityLink> tLinks;
    //protected HashMap<String, TraceabilityLink> tLinks;
    
    //객체생성을 언제, 어떻게
    public Architecture(String archname){
        viewlist = new Vector[ViewType.values().length];
        for(int i=0;i<viewlist.length;i++){
            viewlist[i] = new Vector<ArchitectureModel>();
        }
        //tLinks = new HashMap<String, TraceabilityLink>();
        tLinks = new Vector<TraceabilityLink>();
        
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
                el.addArchitectureElement(ae);                
        }
    }

    @Override
    public void addRelation(ViewType type, String name, Relation r){

        Iterator<ArchitectureModel> it = viewlist[type.ordinal()].iterator();
        ArchitectureModel el;
        while(it.hasNext()){
            el = it.next();
            if(el.getName().equals(name))
                el.addRelation(r);                
        }
       
    }
    
    
    @Override
    public String overallInformation() {
        // TODO Auto-generated method stub
        return null;
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

    @Override
    public boolean addTracebilityLink(String sourceId, Vector<String> destId) {
        
        //find source and destID reference
        ArchitectureElement sourceRefer = null;
        ArchitectureElement destRefer = null;
        Vector<ArchitectureElement> destlist = new Vector<ArchitectureElement>();
        
        for(int i=0; i<viewlist.length;i++){
            Iterator<ArchitectureModel> it = viewlist[i].iterator();
            while(it.hasNext()){
                ArchitectureModel model = it.next();
                sourceRefer = model.getElements().get(sourceId);
                if(sourceRefer == null){
                     destRefer = model.getElements().get(destId);
                     if(destRefer != null)
                         destlist.add(destRefer);
                }
            }
        }
        
        
        if(sourceRefer != null && destlist.size() != 0){
            tLinks.add(new TraceabilityLink(sourceRefer, destlist));
            return true;
        }
        else 
            return false;
      
        
    }

    @Override
    public void addTracebilityLink(ViewType source, ViewType dest){
        // TODO Auto-generated method stub
        
    }


}
