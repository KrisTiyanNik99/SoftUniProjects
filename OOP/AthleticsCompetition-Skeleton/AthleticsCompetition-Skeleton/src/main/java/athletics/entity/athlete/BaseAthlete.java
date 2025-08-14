package athletics.entity.athlete;

import static athletics.common.ExceptionMessages.INVALID_ATHLETE_NAME;

public abstract class BaseAthlete implements Athlete {
    private String name;
    private int stamina;
    private int performance;

    public BaseAthlete(String name, int stamina) {
        setName(name);
        this.stamina = stamina;
    }

    public void setName(String name) {
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException(INVALID_ATHLETE_NAME);
        }

        this.name = name;
    }

    @Override
    public int getStamina() {
        return stamina;
    }

    @Override
    public int getPerformance() {
        return performance;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setPerformance(int performance) {
        this.performance = Math.max(performance, 0);
    }

    public void setStamina(int stamina) {
        this.stamina = Math.max(stamina, 0);
    }
}
