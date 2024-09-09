package org.example.vendingmachine.storage;

import org.example.base.data.ItemInformation;
import org.example.base.data.ItemQuantity;
import org.example.base.feature.storage.BaseStorage;
import org.example.vendingmachine.Drink;

import java.util.ArrayList;
import java.util.List;

public class DrinkStorage implements BaseStorage {

    public DrinkStorage() {

        //test
        initialTestData();
    }

    private final ArrayList<ItemQuantity> storage = new ArrayList<>();


    private void initialTestData() {
        addItemQuantity(new Drink("AAA", 3000), 3);
        addItemQuantity(new Drink("BBB", 2000), 10);
        addItemQuantity(new Drink("CCC", 1000), 9);
        addItemQuantity(new Drink("DDD", 500), 5);
        addItemQuantity(new Drink("EEE", 300), 2);
        addItemQuantity(new Drink("FFF", 100), 0);
    }


    @Override
    public List<ItemQuantity> addItemQuantity(ItemInformation item, int quantity) {
        storage.add(new ItemQuantity(item, quantity));
        return storage;
    }

    @Override
    public List<ItemQuantity> getItemQuantities() {
        return storage;
    }

    @Override
    public ItemQuantity getItemQuantity(int index) throws IndexOutOfBoundsException {
        if (index >= 0 && index < storage.size()) {
            return storage.get(index);
        }
        throw new IndexOutOfBoundsException("index 확인. index:" + index);
    }

    @Override
    public ItemQuantity getItemQuantity(ItemInformation item) throws IndexOutOfBoundsException {
        for (ItemQuantity drink : storage) {
            if (drink.getItem().equals(item)) {
                return drink;
            }
        }
        throw new IndexOutOfBoundsException("item 확인. item:" + item);
    }

    @Override
    public ItemQuantity updateItemQuantity(int index, int resultQuantity) throws IndexOutOfBoundsException {
        if (index >= 0 && index < storage.size()) {
            ItemQuantity drink = storage.get(index);
            drink.setQuantity(resultQuantity);
            return drink;
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public ItemQuantity updateItemQuantity(ItemInformation item, int resulQuantity) {
        ItemQuantity itemQuantity = null;
        for (ItemQuantity iq : storage) {
            if (iq.getItem().equals(item)) {
                itemQuantity = iq;
                itemQuantity.setQuantity(resulQuantity);
            }
        }
        return itemQuantity;
    }
}
