package edu.kaist.g4.architectureAPI;

public interface IArchitectureAPI {
    public String showRecentArchitecture();
    public String checkoutRecentArchitecture(String path);
    public String traceVersionInfoWith(String command, String parameter);
}
