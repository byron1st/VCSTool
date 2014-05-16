package edu.kaist.g4.data;

/**
 * 
 * @author Junhaeng Heo
 *
 */

public interface IArchitecture {
    
    //init architecture object
    public void setView(ViewType type, ViewVesion version, View view);
    public void addAnComponent(ViewType type, ViewVesion version, ArchitectureElement ae);
    public void addAnConnector(ViewType type, ViewVesion version, Relation r);
    
    public View getView(ViewType type, ViewVesion vesion);
    
    public void addTracebilityLink(ViewType source, ViewType dest);

    //get information of architecture
    public String overallInformation();
    
    
}
