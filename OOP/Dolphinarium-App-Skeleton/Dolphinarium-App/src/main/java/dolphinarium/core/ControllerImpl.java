package dolphinarium.core;

import dolphinarium.common.ConstantMessages;
import dolphinarium.entities.dolphins.BottleNoseDolphin;
import dolphinarium.entities.dolphins.Dolphin;
import dolphinarium.entities.dolphins.SpinnerDolphin;
import dolphinarium.entities.dolphins.SpottedDolphin;
import dolphinarium.entities.foods.Food;
import dolphinarium.entities.foods.Herring;
import dolphinarium.entities.foods.Mackerel;
import dolphinarium.entities.foods.Squid;
import dolphinarium.entities.pools.DeepWaterPool;
import dolphinarium.entities.pools.Pool;
import dolphinarium.entities.pools.ShallowWaterPool;
import dolphinarium.repositories.FoodRepository;
import dolphinarium.repositories.FoodRepositoryImpl;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static dolphinarium.common.ConstantMessages.*;
import static dolphinarium.common.ExceptionMessages.*;

//TODO Implement all methods
public class ControllerImpl implements Controller {
    private final FoodRepository foodRepository;
    private final Map<String, Pool> pools;

    public ControllerImpl() {
        foodRepository = new FoodRepositoryImpl();
        pools = new LinkedHashMap<>();
    }

    @Override
    public String addPool(String poolType, String poolName) {
        Pool pool;
        switch (poolType) {
            case "DeepWaterPool" -> pool = new DeepWaterPool(poolName);
            case "ShallowWaterPool" -> pool = new ShallowWaterPool(poolName);
            default -> throw new NullPointerException(INVALID_POOL_TYPE);
        }

        Pool isExistedPool = pools.get(poolName);
        if (isExistedPool != null) {
            throw new NullPointerException(POOL_EXISTS);
        }

        pools.put(pool.getName(), pool);
        return String.format(SUCCESSFULLY_ADDED_POOL_TYPE, poolType, poolName);
    }

    @Override
    public String buyFood(String foodType) {
        Food food;
        switch (foodType) {
            case "Squid" -> food = new Squid();
            case "Herring" -> food = new Herring();
            case "Mackerel" -> food = new Mackerel();
            default -> throw new IllegalArgumentException(INVALID_FOOD_TYPE);
        }

        foodRepository.add(food);
        return String.format(SUCCESSFULLY_BOUGHT_FOOD_TYPE, foodType);
    }

    @Override
    public String addFoodToPool(String poolName, String foodType) {
        Pool pool = pools.get(poolName);

        Food food = foodRepository.findByType(foodType);
        if (food == null) {
            throw new IllegalArgumentException(String.format(NO_FOOD_FOUND, foodType));
        }

        pool.addFood(food);
        foodRepository.remove(food);
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_FOOD_IN_POOL, foodType, poolName);
    }

    @Override
    public String addDolphin(String poolName, String dolphinType, String dolphinName, int energy) {
        Dolphin dolphin = getDolphinByType(dolphinType, dolphinName, energy);
        Pool pool = pools.get(poolName);
        boolean isDolphinExist = isDolphinWithThisNameExist(pool, dolphin.getName());
        if (isDolphinExist) {
            throw new IllegalArgumentException(DOLPHIN_EXISTS);
        }

        // BottleNoseDolphin Can only swim in DeepWaterPool!
        // SpottedDolphin Can swim in DeepWaterPool as well as in ShallowWaterPool.
        // SpinnerDolphin Can only swim in ShallowWaterPool!
        if (dolphin instanceof BottleNoseDolphin && !(pool instanceof DeepWaterPool)) {
            return POOL_NOT_SUITABLE;
        }

        if (dolphin instanceof SpinnerDolphin && !(pool instanceof ShallowWaterPool)) {
            return POOL_NOT_SUITABLE;
        }

        if (dolphin instanceof SpottedDolphin && !((pool instanceof DeepWaterPool) || (pool instanceof ShallowWaterPool))) {
            return POOL_NOT_SUITABLE;
        }

        pool.addDolphin(dolphin);
        return String.format(SUCCESSFULLY_ADDED_DOLPHIN_IN_POOL, dolphinType, dolphinName, poolName);
    }

    @Override
    public String feedDolphins(String poolName, String foodType) {
        Pool pool = pools.get(poolName);
        Food food = getPoolFoodByType(pool, foodType);
        if (food == null) {
            throw new IllegalArgumentException(NO_FOOD_OF_TYPE_ADDED_TO_POOL);
        }

        int feedDolphins = 0;
        for (Dolphin dolphin : pool.getDolphins()) {
            dolphin.eat(food);
            feedDolphins++;
        }

        pool.getFoods().remove(food);
        return String.format(DOLPHINS_FED, feedDolphins, poolName);
    }

    @Override
    public String playWithDolphins(String poolName) {
        Pool pool = pools.get(poolName);
        if (pool.getDolphins().isEmpty()) {
            throw new IllegalArgumentException(NO_DOLPHINS);
        }

        Iterator<Dolphin> dolphins = pool.getDolphins().iterator();
        int removedDolphins = 0;
        while (dolphins.hasNext()) {
            Dolphin dolphin = dolphins.next();
            dolphin.jump();

            if (dolphin.getEnergy() <= 0) {
                dolphins.remove();
                removedDolphins++;
            }
        }

        return String.format(DOLPHINS_PLAY, poolName, removedDolphins);
    }

    @Override
    public String getStatistics() {
        StringBuilder sb = new StringBuilder();
        pools.forEach((poolName, pool) -> {
            sb.append(String.format(DOLPHINS_FINAL, poolName)).append(System.lineSeparator());
            sb.append(getFormattedDolphinInfo(pool)).append(System.lineSeparator());
        });

        return sb.toString();
    }

    // **************** Support methods **********************
    private String getFormattedDolphinInfo(Pool pool) {
        return (pool.getDolphins().isEmpty()) ? NONE
                : pool.getDolphins()
                .stream()
                .map(e -> String.format("%s - %d",e.getName(), e.getEnergy()))
                .collect(Collectors.joining(DELIMITER));
    }

    private Food getPoolFoodByType(Pool pool, String foodType) {
        return pool.getFoods().stream()
                .filter(e -> foodType.equals(e.getClass().getSimpleName()))
                .findFirst()
                .orElse(null);
    }

    private boolean isDolphinWithThisNameExist(Pool pool, String dolphinName) {
        Dolphin dolphin = pool.getDolphins().stream()
                .filter(e -> dolphinName.equals(e.getName()))
                .findFirst()
                .orElse(null);

        return dolphin != null;
    }

    private Dolphin getDolphinByType(String dolphinType, String dolphinName, int energy) {
        Dolphin dolphin;
        switch (dolphinType) {
            case "BottleNoseDolphin" -> dolphin = new BottleNoseDolphin(dolphinName, energy);
            case "SpottedDolphin" -> dolphin = new SpottedDolphin(dolphinName, energy);
            case "SpinnerDolphin" -> dolphin = new SpinnerDolphin(dolphinName, energy);
            default -> throw new IllegalArgumentException(INVALID_DOLPHIN_TYPE);
        }

        return dolphin;
    }
}
