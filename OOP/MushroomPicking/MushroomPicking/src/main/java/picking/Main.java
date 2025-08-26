package picking;

import picking.core.Controller;
import picking.core.ControllerImpl;
import picking.core.Engine;
import picking.core.EngineImpl;

public class Main {
    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}