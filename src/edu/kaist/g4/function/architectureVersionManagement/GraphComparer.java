package edu.kaist.g4.function.architectureVersionManagement;

import java.util.Collection;
import java.util.Vector;

import edu.kaist.g4.data.Architecture;
import edu.kaist.g4.data.ArchitectureElement;
import edu.kaist.g4.data.ArchitectureModel;
import edu.kaist.g4.data.Relation;
import edu.kaist.g4.data.architecturalDifferentiations.ArchitectureChange;
import edu.kaist.g4.data.architecturalDifferentiations.ChangeOperationTypes;

public class GraphComparer {
    Vector<ArchitectureChange> archiChanges;
    
    public GraphComparer(){
        archiChanges = new Vector<ArchitectureChange>();
    }
    
    public Vector<ArchitectureChange> Compare(Architecture recentArchitecture, Architecture workingArchitecture){
 
        Architecture workingArchClone = new Architecture(workingArchitecture);
        Architecture recentArchClone = new Architecture(recentArchitecture);
 
        
        Vector<ArchitectureModel> workingModels = workingArchClone.getArchitectureModels();
        Vector<ArchitectureModel> recentModels = recentArchClone.getArchitectureModels();
        
        Vector<ArchitectureModel> modelsAClone = (Vector<ArchitectureModel>)recentModels.clone();
        Vector<ArchitectureModel> modelsBClone = (Vector<ArchitectureModel>)workingModels.clone();
        
        
        for(ArchitectureModel modelA : modelsAClone){
            String modelAId = modelA.getId();
            ArchitectureModel targetModel = getArchitectureModelById(modelsBClone, modelAId);
            if(targetModel == null){
                //해당 모델은 delete된 것임                
                Collection<ArchitectureElement> modelAElements = modelA.getElements().values();

                for (ArchitectureElement modelAElement : modelAElements) {
                    ArchitectureChange change = new ArchitectureChange(ChangeOperationTypes.DELETE, modelAElement.getId(), null);
                    archiChanges.add(change);
                }
//                modelsBClone.removeElement(modelA);
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
                        ArchitectureChange change = new ArchitectureChange(ChangeOperationTypes.DELETE, modelAElement.getId(), null); 
                        archiChanges.add(change);
                    }
                    else{
                        //element -> modify or same
                        //modelAElement와 targetElement를 비교 수행
                        int revisionNo = targetElement.getRevision();
                        if(!modelAElement.getType().toString().equals(targetElement.getType().toString())){
                            ArchitectureChange change = new ArchitectureChange(ChangeOperationTypes.MODIFY, modelA.getId());
                            change.setMessage(modelAElement.getType().toString() + " -> " + targetElement.getType().toString());
                            archiChanges.add(change);
                            
                            ArchitectureModel workingModel = workingArchitecture.getArchitectureModelById(targetModel.getId());
                            
                            workingModel.serachElementByID(targetElement.getId()).setRevision(revisionNo+1);
                        }
                        if(!modelAElement.getName().equals(targetElement.getName())){
                            ArchitectureChange change = new ArchitectureChange(ChangeOperationTypes.MODIFY, modelA.getId());
                            change.setMessage(modelAElement.getId() + " -> " + targetElement.getId());
                            archiChanges.add(change);
                            
                            ArchitectureModel workingModel = workingArchitecture.getArchitectureModelById(targetModel.getId());
                            workingModel.serachElementByID(targetElement.getId()).setRevision(revisionNo+1);
                        }
                        Vector<Relation> modelARelations = modelAElement.getRelations();
                        Vector<Relation> targetModelRelations = targetElement.getRelations();
                        
                        for(Relation modelARelation : modelARelations){
                            String dstElementId = modelARelation.getDestination().getId();
                            Relation targetRelation = getDestinationbyId(targetModelRelations, dstElementId);
                            if(targetRelation == null){
                                //releation -> Deleted
                                ArchitectureChange change = new ArchitectureChange(ChangeOperationTypes.MODIFY, modelAElement.getId(), null);
                                change.setMessage("(Relation."+modelARelation.getType().toString()+") toward [["+modelARelation.getDestination().getId()+"]] is deleted");
                                archiChanges.add(change);
                                
                                ArchitectureModel workingModel = workingArchitecture.getArchitectureModelById(targetModel.getId());
                                workingModel.serachElementByID(targetElement.getId()).setRevision(revisionNo+1);
                            }
                            else{
                                //relation -> modify or same
                                if(!modelARelation.getType().toString().equals(targetRelation.getType().toString())){
                                    ArchitectureChange change = new ArchitectureChange(ChangeOperationTypes.MODIFY, modelAElement.getId(), null);
                                    change.setMessage("(Relation."+modelARelation.getType().toString()+") -> "+ "(Relation."+targetRelation.getType().toString()+")");
                                    archiChanges.add(change);
                                    
                                    ArchitectureModel workingModel = workingArchitecture.getArchitectureModelById(targetModel.getId());
                                    workingModel.serachElementByID(targetElement.getId()).setRevision(revisionNo+1);
                                }
                                
                                targetModelRelations.removeElement(targetRelation);
                            }
                            
                        }
                        //relation -> add
                        for(Relation targetModelRelation : targetModelRelations){
                            ArchitectureChange change = new ArchitectureChange(ChangeOperationTypes.MODIFY, modelAElement.getId(), null);
                            change.setMessage("(Relation."+targetModelRelation.getType().toString()+") toward [["+targetModelRelation.getDestination().getId()+"]] is added");
                            archiChanges.add(change);
                            
                            ArchitectureModel workingModel = workingArchitecture.getArchitectureModelById(targetModel.getId());
                            workingModel.serachElementByID(targetElement.getId()).setRevision(revisionNo+1);
                        }
                        
                        targetElements.remove(targetElement);
                    }
                    
                }
                //남아있는 recentElement는 모두 add된 것들임
                for(ArchitectureElement targetElement : targetElements){
                    ArchitectureChange change = new ArchitectureChange(ChangeOperationTypes.ADD, targetElement.getId(), null); 
                    archiChanges.add(change);
                    
                    ArchitectureModel workingModel = workingArchitecture.getArchitectureModelById(targetModel.getId());
                    workingModel.serachElementByID(targetElement.getId()).setRevision(0);
                }
                modelsBClone.removeElement(targetModel);
            }
        }
        // 남이있는 workingModel은 모두 add된 것들임
        for (ArchitectureModel modelB : modelsBClone) {
            Collection<ArchitectureElement> modelBElements = modelB.getElements().values();

            for (ArchitectureElement modelBElement : modelBElements) {
                ArchitectureChange change = new ArchitectureChange(ChangeOperationTypes.ADD, modelBElement.getId(), null);
                archiChanges.add(change);
                
                ArchitectureModel workingModel = workingArchitecture.getArchitectureModelById(modelB.getId());
                workingModel.serachElementByID(modelBElement.getId()).setRevision(0);
            }
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
