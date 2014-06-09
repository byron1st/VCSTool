package edu.kaist.g4.architectureAPI;

import java.io.IOException;

public interface IArchitectureAPI {
    public String showRecentArchitecture();
    public void checkoutRecentArchitecture(String path) throws Exception;
    public String traceVersionInfoWith(String command, String parameter);
}
