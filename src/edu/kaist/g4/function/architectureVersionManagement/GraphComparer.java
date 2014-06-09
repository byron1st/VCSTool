package edu.kaist.g4.function.architectureVersionManagement;

import java.util.Collection;
import java.util.Vector;

import edu.kaist.g4.data.ArchitectureElement;
import edu.kaist.g4.data.ArchitectureModel;
import edu.kaist.g4.data.Relation;
import edu.kaist.g4.data.architecturalDifferentiations.ArchitectureChange;
import edu.kaist.g4.data.architecturalDifferentiations.ChangeOperationTypes;

/**
 * 
 * @FileName : GraphComparer.java
 * @Package  : edu.kaist.g4.function.architectureVersionManagement
 * @Author   : Hwi Ahn (ahnhwi@kaist.ac.kr)
 * @Date     : 2014
 * @Detail   :
 * 
 */
public class GraphComparer {
    Vector<ArchitectureChange> archiChanges;
    
    public GraphComparer(){
        archiChanges = new Vector<ArchitectureChange>();
    }
    
    public Vector<ArchitectureChange> Compare(Vector<ArchitectureModel> modelsA, Vector<ArchitectureModel> modelsB){
        for(ArchitectureModel modelA : modelsA){
            String modelAId = modelA.getId();
            ArchitectureModel targetModel = getArchitectureModelById(modelsB, modelAId);
            if(targetModel == null){
                //해당 모델은 delete된 것임                
                Collection<ArchitectureElement> modelAElements = modelA.getElements().values();

                for (ArchitectureElement modelAElement : modelAElements) {
                    ArchitectureChange change = new ArchitectureChange(ChangeOperationTypes.DELETE, "(Element) " + modelAElement.getName(), null);
                    archiChanges.add(change);
                }
                modelsB.removeElement(modelA);
            }
            else{
                //modify된 model들 (model이 modify되었다는 내용은 따로 기록 안함)
                //노드 비교 알고리즘 시작
                Collection<ArchitectureElement> modelAElements = modelA.getElements().values();
                Collection<ArchitectureElement> targetElements = targetModel.getElements().values();
                
                for(ArchitectureElement modelAElement : modelAElements){
                    String modelAElementId = modelAElement.getId();
                    ArchitectureElement targetElement = targetModel.serachElementByID(modelAElementId);
                    
                    if(targetElement == null){
                        //해당 엘리먼트는 delete된 것임
                        ArchitectureChange change = new ArchitectureChange(ChangeOperationTypes.DELETE, "(Element) "+modelAElement.getName(), null); 
                        archiChanges.add(change);
                    }
                    else{
                        //element -> modify or same
                        //modelAElement와 targetElement를 비교 수행
                        if(!modelAElement.getType().toString().equals(targetElement.getType().toString())){
                            ArchitectureChange change = new ArchitectureChange(ChangeOperationTypes.MODIFY, "(Element) "+modelA.getId());
                            change.setMessage(modelAElement.getType().toString() + " -> " + targetElement.getType().toString());
                            archiChanges.add(change);
                        }
                        if(!modelAElement.getName().equals(targetElement.getName())){
                            ArchitectureChange change = new ArchitectureChange(ChangeOperationTypes.MODIFY, "(Element) "+modelA.getId());
                            change.setMessage(modelAElement.getName() + " -> " + targetElement.getName());
                            archiChanges.add(change);
                        }
                        Vector<Relation> modelARelations = modelAElement.getRelations();
                        Vector<Relation> targetModelRelations = targetElement.getRelations();
                        
                        for(Relation modelARelation : modelARelations){
                            String dstElementId = modelARelation.getDestination().getId();
                            Relation targetRelation = getDestinationbyId(targetModelRelations, dstElementId);
                            if(targetRelation == null){
                                //releation -> Deleted
                                ArchitectureChange change = new ArchitectureChange(ChangeOperationTypes.MODIFY, "(Element) "+modelAElement.getName(), null);
                                change.setMessage("(Relation."+modelARelation.getType().toString()+") toward [["+modelARelation.getDestination().getName()+"]] is deleted");
                                archiChanges.add(change);
                            }
                            else{
                                //relation -> modify or same
                                if(!modelARelation.getType().toString().equals(targetRelation.getType().toString())){
                                    ArchitectureChange change = new ArchitectureChange(ChangeOperationTypes.MODIFY, "(Element) "+modelAElement.getName(), null);
                                    change.setMessage("(Relation."+modelARelation.getType().toString()+") -> "+ "(Relation."+targetRelation.getType().toString()+")");
                                    archiChanges.add(change);
                                }
                                
                                targetModelRelations.removeElement(targetRelation);
                            }
                        }
                        //relation -> add
                        for(Relation targetModelRelation : targetModelRelations){
                            ArchitectureChange change = new ArchitectureChange(ChangeOperationTypes.MODIFY, "(Element) "+modelAElement.getName(), null);
                            change.setMessage("(Relation."+targetModelRelation.getType().toString()+") toward [["+targetModelRelation.getDestination().getName()+"]] is added");
                            archiChanges.add(change);
                        }
                        
                        targetElements.remove(targetElement);
                    }
                    
                }
                //남아있는 recentElement는 모두 add된 것들임
                for(ArchitectureElement targetElement : targetElements){
                    ArchitectureChange change = new ArchitectureChange(ChangeOperationTypes.ADD, "(Element) "+targetElement.getName(), null); 
                    archiChanges.add(change);
                }
                modelsB.removeElement(targetModel);
            }
        }
        // 남이있는 workingModel은 모두 add된 것들임
        for (ArchitectureModel modelB : modelsB) {
            Collection<ArchitectureElement> modelBElements = modelB.getElements().values();

            for (ArchitectureElement modelBElement : modelBElements) {
                ArchitectureChange change = new ArchitectureChange(ChangeOperationTypes.ADD, "(Element) " + modelBElement.getName(), null);
                archiChanges.add(change);
            }
            modelsB.removeElement(modelB);
        }

        return archiChanges;
    }

    public ArchitectureModel getArchitectureModelById(Vector<ArchitectureModel> modelsB, String modelAId){
        for(ArchitectureModel workingModel : modelsB){
            if(workingModel.getId().equals(modelAId)){
                return workingModel;
            }
        }
        return null;
    }
    
    public Relation getDestinationbyId(Vector<Relation> targetModelRelations, String id){
        for(Relation relation : targetModelRelations){
            if(relation.getDestination().getId().equals(id)){
                return relation;
            }
        }
        return null;
    }
}
