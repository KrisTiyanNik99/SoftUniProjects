package athletics.core;

import athletics.common.Command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class EngineImpl implements Engine {

    private Controller controller;
    private BufferedReader reader;

    public EngineImpl(Controller controller) {
        this.controller = controller;
        this.reader = new BufferedReader(new InputStreamReader(System.in));
    }


    @Override
    public void run() {
        while (true) {
            String result = null;
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
        String result = null;
        String[] data = Arrays.stream(tokens).skip(1).toArray(String[]::new);

        switch (command) {
            case AddSportsFacility:
                result = addSportsFacility(data);
                break;
            case AddAthlete:
                result = addAthlete(data);
                break;
            case TrainAthlete:
                result = trainAthlete(data);
                break;
            case StartCompetition:
                result = startCompetition(data);
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

    private String addSportsFacility(String[] data) {
        return controller.addSportsFacility(data[0], data[1]);
    }

    private String addAthlete(String[] data) {
        return controller.addAthlete(data[0], data[1], data[2]);
    }

    private String trainAthlete(String[] data) {
        return controller.trainAthlete(data[0], data[1]);
    }
    private String startCompetition(String[] data) {
        return controller.startCompetition(data[0]);
    }
    private String getStatistics() {
        return controller.getStatistics();
    }
}
