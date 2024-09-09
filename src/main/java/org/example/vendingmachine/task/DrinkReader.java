package org.example.vendingmachine.task;

import org.example.base.data.ItemQuantity;
import org.example.base.feature.core.worker.BaseReader;
import org.example.vendingmachine.storage.DrinkStorage;

import java.util.List;

public class DrinkReader extends BaseReader<DrinkStorage> {

    public DrinkReader(DrinkStorage storage) {
        this.storage = storage;
    }
    @Override
    public List<ItemQuantity> getItems() {
        return storage.getItemQuantities();
    }

}