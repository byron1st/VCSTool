package edu.kaist.g4.architectureAPI;

public interface IArchitectureAPI {
    public String showRecentArchitecture();
    public void checkoutRecentArchitecture(String path) throws Exception;
    public String traceVersionInfoWith(String command, String parameter);
    public String commitNewArchitecture(String dirPathforNewArchitecture);
}
