package ArchitectureRepositoryLayer.DataLayer;

/**
 * 
 * @author Junhaeng Heo
 *
 */

public interface IArchitecture {

    public void createAnArchitecturalElement(String s) throws Exception;
    public void addAnArchitecturalElement(String s) throws Exception;
    public void addAnRelation(String s) throws Exception;
    
}
