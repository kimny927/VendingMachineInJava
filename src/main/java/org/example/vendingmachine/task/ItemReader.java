package org.example.vendingmachine.task;

import org.example.base.data.ItemInformation;
import org.example.base.data.ItemQuantity;
import org.example.base.feature.core.worker.BaseReader;
import org.example.base.feature.storage.BaseStorage;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ItemReader<Item extends ItemInformation> extends BaseReader<Item> {

    @NotNull
    private final BaseStorage<Item> storage;

    public ItemReader(@NotNull BaseStorage<Item> storage) {
        this.storage = storage;
    }
    @Override
    public List<ItemQuantity<Item>> getItems() {
        return storage.getItemQuantities();
    }

}