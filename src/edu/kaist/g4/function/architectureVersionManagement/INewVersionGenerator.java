package edu.kaist.g4.function.architectureVersionManagement;

/**
 * 
 * @FileName : INewVersionGenerator.java
 * @Package  : edu.kaist.g4.function.architectureVersionManagement
 * @Author   : Hwi Ahn (ahnhwi@kaist.ac.kr)
 * @Date     : 2014
 * @Detail   :
 * 
 */
public interface INewVersionGenerator {
    //TODO: diffList를 return해야함. diffList의 형태를 미리 정의 후 void를 변경
    public void buildNewVersion();
}
