package bakery.entities.bakedFoods;

import bakery.entities.bakedFoods.interfaces.BakedFood;

public class Cake extends BaseFood {

    private static final double PORTION = 245;

    public Cake(String name, double price) {
        super(name, PORTION, price);
    }
}
