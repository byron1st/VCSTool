package edu.kaist.g4.data;

/**
 * 
 * @author Junhaeng Heo
 *
 */

public interface IArchitecture {
    
    //init architecture object
    public void addView(ViewType type, ArchitectureModel view);
    public void addAnComponent(ViewType type,String name, ArchitectureElement ae);
    public void addAnConnector(ViewType type,String name, Relation r);
    
    public ArchitectureModel getView(ViewType type,String name);
    
    public void addTracebilityLink(ViewType source, ViewType dest);

    //get information of architecture
    public String overallInformation();
    
    
}
