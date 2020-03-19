package pl.michalbartnicki.actionobject;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ActionServant {

    private int counter;

    @SneakyThrows
    public void action1(int timeout) {
        log.info(">>>> BEFORE INCREMENT " + counter);
        counter++;
        Thread.sleep(timeout);
        log.info(">>>> AFTER INCREMENT " + counter);
    }

    public void action2() {
        // I'm just a lazy bastard and do nothing
    }
}
