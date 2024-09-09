package org.example.vendingmachine.action;

import org.example.base.data.ItemQuantity;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DisplayActionResult extends DrinkActionResult<List<ItemQuantity>> {

    public static DisplayActionResult successResult(List<ItemQuantity> list) {
        return new DisplayActionResult(true, list);
    }

    public static DisplayActionResult failureResult() {
        return new DisplayActionResult(false, null);
    }

    @Nullable
    private final List<ItemQuantity> data;
    private boolean isSucceed = false;

    private DisplayActionResult(boolean isSuccess, @Nullable List<ItemQuantity> list) {
        this.isSucceed = isSuccess;
        data = list;
    }

    @Override
    public boolean isSucceed() {
        return isSucceed;
    }

    @Override
    public List<ItemQuantity> getData() {
        return data;
    }
}
