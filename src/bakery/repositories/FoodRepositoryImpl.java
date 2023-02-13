package bakery.repositories;

import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.repositories.interfaces.FoodRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class FoodRepositoryImpl implements FoodRepository<BakedFood> {

    private Collection<BakedFood> bakedFoods;

    public FoodRepositoryImpl() {
        this.bakedFoods = new ArrayList<>();
    }

    @Override
    public BakedFood getByName(String name) {
        return this.bakedFoods.stream()
                .filter(d -> name.equals(d.getName()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Collection<BakedFood> getAll() {
        return Collections.unmodifiableCollection(bakedFoods);
    }

    @Override
    public void add(BakedFood bakedFood) {
        bakedFoods.add(bakedFood);
    }
}
