package athletics;

import athletics.core.Controller;
import athletics.core.ControllerImpl;
import athletics.core.Engine;
import athletics.core.EngineImpl;

public class Launcher {
    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}
