package org.example.base.feature.storage;

import org.example.base.data.ItemInformation;
import org.example.base.data.ItemQuantity;

import java.util.List;

public interface BaseStorage {

    List<ItemQuantity> addItemQuantity(ItemInformation item, int quantity);

    List<ItemQuantity> getItemQuantities();

    ItemQuantity getItemQuantity(int index);

    ItemQuantity getItemQuantity(ItemInformation item);

    ItemQuantity updateItemQuantity(int index, int resultQuantity);

    ItemQuantity updateItemQuantity(ItemInformation item, int resulQuantity);

}
