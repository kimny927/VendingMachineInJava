package org.example.base.feature.storage;

import org.example.base.data.ItemInformation;
import org.example.base.data.ItemQuantity;

import java.util.List;

public interface BaseStorage<I extends ItemInformation> {

    List<ItemQuantity<I>> addItemQuantity(I item, int quantity);

    List<ItemQuantity<I>> getItemQuantities();

    ItemQuantity<I> getItemQuantity(int index);

    ItemQuantity<I> getItemQuantity(I item);

    ItemQuantity<I> updateItemQuantity(int index, int resultQuantity);

    ItemQuantity<I> updateItemQuantity(I item, int resulQuantity);

}
