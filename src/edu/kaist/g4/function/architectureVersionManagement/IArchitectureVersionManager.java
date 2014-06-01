package edu.kaist.g4.function.architectureVersionManagement;

public interface IArchitectureVersionManager {
    public void commitNewArchitecture(String filePathforNewArchitecture);

    //UC02에는 Current Architecture라고 명명됨
    //checkout의 출력값은 String으로 정한다. - 차후 변경가능
    public String showRecentArchitecture();
    public String getRecentArchPath();
}
