package edu.kaist.g4.function.architectureVersionManagement;

public interface IVersionInfoTracer {
    public String printAllDiffs(String command);
    public String printAllLists(String parameter);
}
