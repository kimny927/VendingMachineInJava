package org.example.base.feature.core.worker;

import org.example.base.data.ItemQuantity;
import org.example.base.feature.storage.BaseStorage;

import java.util.List;

public abstract class BaseReader<S extends BaseStorage> implements Worker {

    protected S storage;

    public abstract List<ItemQuantity> getItems();
}
