package org.example.base.feature.core.worker;

import org.example.base.data.ItemInformation;
import org.example.base.data.ItemQuantity;

import java.util.List;

public abstract class BaseReader<Item extends ItemInformation> implements Worker {

    public abstract List<ItemQuantity<Item>> getItems();
}
