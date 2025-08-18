package dolphinarium.core;

import dolphinarium.common.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class EngineImpl implements Engine {
    private final Controller controller;
    private final BufferedReader reader;

    public EngineImpl() {
        this.controller = new ControllerImpl();
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
        String result;
        String[] data = Arrays.stream(tokens).skip(1).toArray(String[]::new);

        result = switch (command) {
            case AddPool -> addPool(data);
            case BuyFood -> buyFood(data);
            case AddFoodToPool -> addFoodToPool(data);
            case AddDolphin -> addDolphin(data);
            case FeedDolphins -> feedDolphins(data);
            case PlayWithDolphins -> playWithDolphins(data);
            case GetStatistics -> getStatistics();
            case Exit -> Command.Exit.name();
        };
        return result;
    }
    private String addPool(String[] data) {
        //TODO
        return controller.addPool(data[0], data[1]);
    }

    private String buyFood(String[] data) {
        //TODO
        return controller.buyFood(data[0]);
    }

    private String addFoodToPool(String[] data) {
        //TODO
        return controller.addFoodToPool(data[0],data[1]);
    }


    private String addDolphin(String[] data) {
        //TODO
        return controller.addDolphin(data[0],data[1],data[2], Integer.parseInt(data[3]));
    }

    private String feedDolphins(String[] data) {
        //TODO
        return controller.feedDolphins(data[0], data[1]);
    }

    private String playWithDolphins(String[] data) {
        //TODO
        return controller.playWithDolphins(data[0]);
    }


    private String getStatistics() {
        //TODO
        return controller.getStatistics();
    }
}