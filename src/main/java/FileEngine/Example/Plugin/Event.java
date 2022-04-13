package FileEngine.Example.Plugin;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

@SuppressWarnings("rawtypes")
public class Event {
    private final AtomicBoolean isBlock = new AtomicBoolean(false);
    private Consumer callback;
    private Consumer errorHandler;

    public AtomicBoolean getBlock() {
        return isBlock;
    }

    public void setBlock() {
        isBlock.set(true);
    }

    public Consumer getCallback() {
        return callback;
    }

    public void setCallback(Consumer callback) {
        this.callback = callback;
    }

    public Consumer getErrorHandler() {
        return errorHandler;
    }

    public void setErrorHandler(Consumer errorHandler) {
        this.errorHandler = errorHandler;
    }
}
