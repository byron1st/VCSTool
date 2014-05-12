package ArchitectureRepositoryLayer.DataLayer;

import java.util.Vector;

/**
 * 
 * @author Junhaeng Heo
 *
 */

public class Architecture implements IArchitecture{

    protected Vector<View> views;
    protected Vector<TraceabilityLink> tLinks;
    
    //객체생성을 언제, 어떻게
    public Architecture(String s){
        views = new Vector<View>();
        tLinks = new Vector<TraceabilityLink>();
    }
    
    @Override
    //마키택처 객체생성매서드?
    public void createAnArchitecturalElement(String s) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addAnArchitecturalElement(String s) throws Exception {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void addAnRelation(String s) throws Exception {
        // TODO Auto-generated method stub
        
    }

}
