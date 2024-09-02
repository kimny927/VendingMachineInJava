package org.example.vendingmachine.task;

import org.example.base.data.ItemQuantity;
import org.example.base.feature.core.task.BaseReadTask;
import org.example.vendingmachine.Drink;
import org.example.vendingmachine.storage.DrinkStorage;

import java.util.List;

public class DrinkReader extends BaseReadTask<Drink, DrinkStorage> {

    public DrinkReader(DrinkStorage storage) {
        this.storage = storage;
    }
    @Override
    public List<ItemQuantity<Drink>> getItems() {
        return storage.getItemQuantities();
    }

}