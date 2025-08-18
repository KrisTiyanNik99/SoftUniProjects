package furnitureFactory.repositories;

import furnitureFactory.entities.wood.Wood;

import java.util.ArrayList;
import java.util.Collection;

import static furnitureFactory.common.ExceptionMessages.INVALID_WOOD;

public class WoodRepositoryImpl implements WoodRepository {
    private final Collection<Wood> woodTypes;

    public WoodRepositoryImpl() {
        woodTypes = new ArrayList<>();
    }

    @Override
    public void add(Wood wood) {
        if (wood == null) {
            throw new IllegalArgumentException(INVALID_WOOD);
        }

        woodTypes.add(wood);
    }

    @Override
    public boolean remove(Wood wood) {
        return woodTypes.remove(wood);
    }

    @Override
    public Wood findByType(String type) {
        return woodTypes.stream()
                .filter(e -> type.equals(e.getClass().getSimpleName()))
                .findFirst()
                .orElse(null);
    }
}
