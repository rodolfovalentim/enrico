package enrico;

import java.util.Map;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.remote.UnreachableBrowserException;

public class FixedPhantomJSDriver extends PhantomJSDriver {

    private final int retryCount = 2;
    public FixedPhantomJSDriver() {
    }
    public FixedPhantomJSDriver(Capabilities desiredCapabilities) {
        super(desiredCapabilities);
    }
    public FixedPhantomJSDriver(PhantomJSDriverService service, Capabilities desiredCapabilities) {
        super(service, desiredCapabilities);
    }
    @Override
    protected Response execute(String driverCommand, Map<String, ?> parameters) {
        int retryAttempt = 0;
        while (true) {
            try {
                return super.execute(driverCommand, parameters);
            } catch (UnreachableBrowserException e) {
                retryAttempt++;
                if (retryAttempt > retryCount) {
                    throw e;
                }
            }
        }
    }
}

