package edu.kaist.g4.data.architecturalDifferentiations;

/**
 * 
 * @FileName : ArchitectureChange.java
 * @Package  : edu.kaist.g4.data
 * @Author   : Hwi Ahn (ahnhwi@kaist.ac.kr)
 * @Date     : 2014
 * @Detail   : 1개의 Architecture Change를 기록하는 클래스.
 * 
 */
public class ArchitectureChange {
    private ChangeOperationTypes changeOperation;
    private String parameter;
    private String message;
    
    public ArchitectureChange(ChangeOperationTypes changeOperation, String parameter, String message){
        this.changeOperation = changeOperation;
        this.parameter = parameter;
        this.message = message;
    }
    
    public ArchitectureChange(ChangeOperationTypes changeOperation, String parameter){
        this.changeOperation = changeOperation;
        this.parameter = parameter;
    }
    
    public ChangeOperationTypes getChangeOperation() {
        return changeOperation;
    }
    
    public void setChangeOperation(ChangeOperationTypes changeOperation) {
        this.changeOperation = changeOperation;
    }
    
    public String getParameter() {
        return parameter;
    }
    
    public void setParameter(String parameter) {
        this.parameter = parameter;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
