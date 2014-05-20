package edu.kaist.g4.data;


/**
 * 
 * @author Junhaeng Heo
 *
 *  traceability contains source ArchitectureElement reference and destination ArchitectureElement reference
 *  we assume that every ArchitectureElement Id is unique in whole ArchitectureModels
 */

public class TraceabilityLink {
    
    private ArchitectureModel sourceModel, destModel;
    private ArchitectureElement source, destination;
    private String description;
    
    
    public TraceabilityLink(ArchitectureElement srcID, ArchitectureElement destID){
      this.source = srcID;
      this.destination = destID;
        
    }
    
    public TraceabilityLink(ArchitectureModel smodelID, ArchitectureElement srcID, ArchitectureModel dmodelID, ArchitectureElement destID){
        //if we can use this type... need more discussion
        this.source = srcID;
        this.destination = destID;
        this.sourceModel = smodelID;
        this.destModel = dmodelID;
    }

    public ArchitectureModel getSourceModel() {
        return sourceModel;
    }

    public void setSourceModel(ArchitectureModel sourceModel) {
        this.sourceModel = sourceModel;
    }

    public ArchitectureModel getDestModel() {
        return destModel;
    }

    public void setDestModel(ArchitectureModel destModel) {
        this.destModel = destModel;
    }

    public ArchitectureElement getSource() {
        return source;
    }

    public void setSource(ArchitectureElement source) {
        this.source = source;
    }

    public ArchitectureElement getDestination() {
        return destination;
    }

    public void setDestination(ArchitectureElement destination) {
        this.destination = destination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
