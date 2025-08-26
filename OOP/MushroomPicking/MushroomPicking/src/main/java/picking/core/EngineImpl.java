package picking.core;

import picking.common.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class EngineImpl implements Engine {

    private final Controller controller;
    private final BufferedReader reader;

    public EngineImpl(Controller controller) {
        this.controller = controller;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }


    @Override
    public void run() {
        while (true) {
            String result;
            try {
                result = processInput();

                if (result.equals(Command.Exit.name())) {
                    break;
                }
            } catch (NullPointerException | IllegalArgumentException | IOException e) {
                result = e.getMessage();
            }

            System.out.println(result);
        }

    }

    private String processInput() throws IOException {
        String input = this.reader.readLine();
        String[] tokens = input.split("\\s+");

        Command command = Command.valueOf(tokens[0]);
        String result;
        String[] data = Arrays.stream(tokens).skip(1).toArray(String[]::new);

        result = switch (command) {
            case AddPlace -> addPlace(data);
            case AddPicker -> addPicker(data);
            case StartPicking -> startPicking(data);
            case GetStatistics -> getStatistics();
            case Exit -> Command.Exit.name();
        };

        return result;
    }

    private String addPlace(String[] data) {
        return controller.addPlace(data[0], Arrays.stream(data).skip(1).toArray(String[]::new));
    }

    private String addPicker(String[] data) {
        //TODO
        return controller.addPicker(data[0], data[1], data[2]);
    }

    private String startPicking(String[] data){
        //TODO
        return controller.startPicking(data[0]);
    }

    private String getStatistics() {
        //TODO
        return controller.getStatistics();
    }
}
