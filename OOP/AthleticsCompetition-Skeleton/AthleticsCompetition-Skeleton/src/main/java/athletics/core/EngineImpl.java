package athletics.core;

import athletics.common.Command;

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
            case AddSportsFacility -> addSportsFacility(data);
            case AddAthlete -> addAthlete(data);
            case TrainAthlete -> trainAthlete(data);
            case StartCompetition -> startCompetition(data);
            case GetStatistics -> getStatistics();
            case Exit -> Command.Exit.name();
        };

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
