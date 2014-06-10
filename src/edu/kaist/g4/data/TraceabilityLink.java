package edu.kaist.g4.data;

import java.util.Iterator;
import java.util.Vector;


/**
 * 
 * @author Junhaeng Heo
 *
 *  traceability contains source ArchitectureElement reference and destination ArchitectureElement reference
 *  we assume that every ArchitectureElement Id is unique in whole ArchitectureModels
 */

public class TraceabilityLink {
    
    private ArchitectureModel sourceModel, destModel;
    private ArchitectureElement source;
    private Vector<ArchitectureElement> destination;
    private String description;
    
    public TraceabilityLink(TraceabilityLink link){
        sourceModel = new ArchitectureModel(link.sourceModel);
        destModel = new ArchitectureModel(link.destModel);
        source = new ArchitectureElement(link.source);
        destination = (Vector<ArchitectureElement>)link.destination.clone();
        description = new String(link.description);
    }
    
    public TraceabilityLink(ArchitectureElement srcRefer, Vector<ArchitectureElement> destList){
      this.source = srcRefer;
      this.destination = destList;
    }
    
    public TraceabilityLink(ArchitectureModel smodelID, ArchitectureElement srcID, ArchitectureModel dmodelID, Vector<ArchitectureElement> destID){
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


    public Vector<ArchitectureElement> getDestination() {
        return destination;
    }

    public void setDestination(Vector<ArchitectureElement> destination) {
        this.destination = destination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String overallInformation(){
        String result = "source:";
        result += source.getName() + "(" + source.getId() +")\n";

        Iterator<ArchitectureElement> it = destination.iterator();
        while(it.hasNext()){
            ArchitectureElement ae = it.next();
            result += "destination: " + ae.getName()+"("+ae.getId()+")\n";
        }

        return result;
    }
    
}
