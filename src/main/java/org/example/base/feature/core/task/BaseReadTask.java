package org.example.base.feature.core.task;

import org.example.base.data.ItemInformation;
import org.example.base.data.ItemQuantity;
import org.example.base.feature.storage.BaseStorage;

import java.util.List;

public abstract class BaseReadTask<I extends ItemInformation, S extends BaseStorage<I>> {

    protected S storage;

    public abstract List<ItemQuantity<I>> getItems();
}
