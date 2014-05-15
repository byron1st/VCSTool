package edu.kaist.g4.data;

/**
 * 
 * @author Junhaeng Heo
 *
 */

public interface IArchitecture {
    
    //init architecture object
    public void addView(ViewType type, View view) throws Exception;
    public void addAnComponent(ViewType type, ArchitectureElement ae) throws Exception;
    public void addAnConnector(ViewType type, Relation r) throws Exception;
    
    public void addTracebilityLink(ViewType source, ViewType dest) throws Exception;
    
    //get information of architecture
    public String overallInformation();
    
    
}
