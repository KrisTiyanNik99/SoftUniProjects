package competition;

import competition.core.Controller;
import competition.core.ControllerImpl;
import competition.core.Engine;
import competition.core.EngineImpl;

public class Main {
    public static void main(String[] args) {
        Controller controller = new ControllerImpl();
        Engine engine = new EngineImpl(controller);
        engine.run();
    }
}