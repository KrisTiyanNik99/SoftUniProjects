package competition.core;

import competition.common.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class EngineImpl implements Engine{

    private Controller controller;
    private BufferedReader reader;


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

                if (result.equals("Exit")) {
                    break;
                }
            } catch (NullPointerException | IllegalArgumentException | IllegalStateException | IOException e) {
                result = e.getMessage();
            }

            System.out.println(result);
        }
    }

    private String processInput() throws IOException {
        String input = this.reader.readLine();
        String[] tokens = input.split("\\s+");

        Command command = Command.valueOf(tokens[0]);
        String result = null;
        String[] data = Arrays.stream(tokens).skip(1).toArray(String[]::new);

        switch (command) {
            case AddDestination:
                result = addDestination(data);
                break;
            case AddCar:
                result = addCar(data);
                break;
            case ReachDestination:
                result = reachDestination(data);
                break;
            case GetStatistics:
                result = getStatistics();
                break;
            case Exit:
                result = Command.Exit.name();
                break;
        }
        return result;
    }

    private String addCar(String[] data) {
        //TODO
        return controller.addCar(data[0], data[1], data[2]);
    }

    private String addDestination(String[] data) {
        //TODO
        return controller.addDestination(data[0], data[1]);
    }

    private String reachDestination(String[] data) {
        //TODO
        return controller.reachDestination(data[0]);
    }

    private String getStatistics() {
        //TODO
        return controller.getStatistics();
    }
}
