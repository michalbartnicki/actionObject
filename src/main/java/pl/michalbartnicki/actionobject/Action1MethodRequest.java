package pl.michalbartnicki.actionobject;

import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class Action1MethodRequest implements MethodRequest {

    public static final int MIN_TIMEOUT = 0;
    public static final int MAX_TIMEOUT = 10000;
    private final ActionServant servant;
    private final int timeout;

    @Override
    public boolean canExecute() {
        return MIN_TIMEOUT <= timeout && timeout <= MAX_TIMEOUT;
    }

    @Override
    public void execute() {
        servant.action1(timeout);
    }
}
