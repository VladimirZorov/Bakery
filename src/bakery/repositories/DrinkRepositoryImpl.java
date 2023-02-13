package bakery.repositories;

import bakery.entities.drinks.interfaces.Drink;
import bakery.repositories.interfaces.DrinkRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class DrinkRepositoryImpl implements DrinkRepository<Drink> {

    private Collection<Drink> drinks;

    public DrinkRepositoryImpl() {
        this.drinks = new ArrayList<>();
    }

    @Override
    public Drink getByNameAndBrand(String drinkName, String drinkBrand) {
        return this.drinks.stream().filter(drink -> drink.getName().equals(drinkName))
                .filter(drink -> drink.getBrand().equals(drinkBrand))
                .findFirst().orElse(null);
    }

    @Override
    public Collection<Drink> getAll() {
        return Collections.unmodifiableCollection(drinks);
    }

    @Override
    public void add(Drink drink) {
        drinks.add(drink);
    }
}
