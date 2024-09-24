package org.example.base.feature.storage;

import org.example.base.data.ItemInformation;
import org.example.base.data.ItemQuantity;

import java.util.List;

public interface BaseStorage<Item extends ItemInformation> {

    List<ItemQuantity<Item>> addItemQuantity(Item item, int quantity);

    List<ItemQuantity<Item>> getItemQuantities();

    ItemQuantity<Item> getItemQuantity(int index);

    ItemQuantity<Item> getItemQuantity(Item item);

    ItemQuantity<Item> updateItemQuantity(int index, int resultQuantity);

    ItemQuantity<Item> updateItemQuantity(Item item, int resulQuantity);

}
