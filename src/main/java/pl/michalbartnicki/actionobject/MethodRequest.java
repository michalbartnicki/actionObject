package pl.michalbartnicki.actionobject;

public interface MethodRequest {
    boolean canExecute();
    void execute();
}
