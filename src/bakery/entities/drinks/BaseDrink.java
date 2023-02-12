package bakery.entities.drinks;

import bakery.entities.drinks.interfaces.Drink;

public abstract class BaseDrink implements Drink {

    private String name;
    private int portion;
    private double price;
    private String brand;

    public BaseDrink(String name, int portion, double price, String brand) {
        this.name = name;
        this.portion = portion;
        this.price = price;
        this.brand = brand;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public int getPortion() {
        return 0;
    }

    @Override
    public double getPrice() {
        return 0;
    }

    @Override
    public String getBrand() {
        return null;
    }

    @Override
    public String toString() {
        return "BaseDrink{" +
                "name='" + name + '\'' +
                ", portion=" + portion +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                '}';
    }
}