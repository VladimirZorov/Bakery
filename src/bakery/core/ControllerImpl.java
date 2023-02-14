package bakery.core;

import bakery.core.interfaces.Controller;
import bakery.entities.bakedFoods.Bread;
import bakery.entities.bakedFoods.Cake;
import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.drinks.Tea;
import bakery.entities.drinks.Water;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.tables.InsideTable;
import bakery.entities.tables.OutsideTable;
import bakery.entities.tables.interfaces.Table;
import bakery.repositories.DrinkRepositoryImpl;
import bakery.repositories.FoodRepositoryImpl;
import bakery.repositories.TableRepositoryImpl;
import bakery.repositories.interfaces.DrinkRepository;
import bakery.repositories.interfaces.FoodRepository;
import bakery.repositories.interfaces.TableRepository;

import static bakery.common.ExceptionMessages.*;
import static bakery.common.OutputMessages.*;

public class ControllerImpl implements Controller {

    private FoodRepository<BakedFood> foodRepository;
    private DrinkRepository<Drink> drinkRepository;
    private TableRepository<Table> tableRepository;

    public ControllerImpl(FoodRepository<BakedFood> foodRepository, DrinkRepository<Drink> drinkRepository, TableRepository<Table> tableRepository) {
        this.foodRepository = new FoodRepositoryImpl();
        this.drinkRepository = new DrinkRepositoryImpl();
        this.tableRepository = new TableRepositoryImpl();
    }

    @Override
    public String addFood(String type, String name, double price) {
        BakedFood bakedFood = foodRepository.getByName(name);
        if (foodRepository.getAll().contains(bakedFood)) {
            throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
        }
        if (type.equals("Bread")) {
            bakedFood = new Bread(name, price);
        } else if (type.equals("Cake")) {
            bakedFood = new Cake(name, price);
        }

        foodRepository.add(bakedFood);
            return String.format(FOOD_ADDED, name, type);
    }

    @Override
    public String addDrink(String type, String name, int portion, String brand) {
       Drink drink = drinkRepository.getByNameAndBrand(name, brand);
       if (drinkRepository.getAll().contains(drink)) {
           throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, name, brand));
       }
       if (type.equals("Water")) {
           drink = new Water(name, portion, brand);
       } else if (type.equals("Tea")) {
           drink = new Tea(name, portion, brand);
       }

       drinkRepository.add(drink);
        return String.format(DRINK_ADDED, name, brand);
    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        Table table = tableRepository.getByNumber(tableNumber);
        if (tableRepository.getAll().contains(table)) {
            throw new IllegalArgumentException(String.format(TABLE_EXIST, tableNumber));
        }

        if (type.equals("InsideTable")) {
            table = new InsideTable(tableNumber, capacity);
        } else if (type.equals("OutsideTable")) {
            table = new OutsideTable(tableNumber, capacity);
        }

        tableRepository.add(table);
        return String.format(TABLE_ADDED, tableNumber);
    }

    @Override
    public String reserveTable(int numberOfPeople) {
        Table table = null;
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }

        for (Table table1 : tableRepository.getAll()) {
            if (table1.getCapacity() >= numberOfPeople && !table1.isReserved()) {
                table = table1;
                break;
            }
        }

        if (table == null) {
            throw new IllegalArgumentException(String.format(RESERVATION_NOT_POSSIBLE, numberOfPeople));
        }

        table.reserve(numberOfPeople);
        return String.format(TABLE_RESERVED, table.getTableNumber(), numberOfPeople);
    }

    @Override
    public String orderFood(int tableNumber, String foodName) {
        Table table = tableRepository.getByNumber(tableNumber);
        if (table == null || !table.isReserved()) {
            throw new IllegalArgumentException(String.format(WRONG_TABLE_NUMBER, tableNumber));
        }
        BakedFood bakedFood = foodRepository.getByName(foodName);
        if (bakedFood == null) {
            throw new IllegalArgumentException(String.format(NONE_EXISTENT_FOOD, foodName));
        }
        table.orderFood(bakedFood);
        return String.format(FOOD_ORDER_SUCCESSFUL, tableNumber, foodName);
    }

    @Override
    public String orderDrink(int tableNumber, String drinkName, String drinkBrand) {
        Table table = tableRepository.getByNumber(tableNumber);
        if (table == null || !table.isReserved()) {
            throw new IllegalArgumentException(String.format(WRONG_TABLE_NUMBER, tableNumber));
        }
        Drink drink = drinkRepository.getByNameAndBrand(drinkName, drinkBrand);
        if (drink == null) {
            throw new IllegalArgumentException(String.format(NON_EXISTENT_DRINK, drinkName, drinkBrand));
        }
        table.orderDrink(drink);
        return String.format(DRINK_ORDER_SUCCESSFUL, tableNumber, drinkName, drinkBrand);

    }

    @Override
    public String leaveTable(int tableNumber) {
       Table table = tableRepository.getByNumber(tableNumber);
       double bill = table.getBill();
       table.clear();
        return String.format(BILL, tableNumber, bill);
    }

    @Override
    public String getFreeTablesInfo() {
        StringBuilder sb = new StringBuilder();
        for (Table table : tableRepository.getAll()) {
            if (!table.isReserved()) {
               sb.append(table.getFreeTableInfo()).append(System.lineSeparator());
            }
        }
        return sb.toString().trim();
    }

    @Override
    public String getTotalIncome() {
        //TODO:
        return null;
    }
}
