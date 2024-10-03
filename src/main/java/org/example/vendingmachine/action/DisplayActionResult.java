package org.example.vendingmachine.action;

import org.example.base.data.ItemInformation;
import org.example.base.data.ItemQuantity;
import org.example.base.feature.ui.action.ActionResult;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DisplayActionResult<Item extends ItemInformation> implements ActionResult<List<ItemQuantity<Item>>> {

    @Nullable
    private final List<ItemQuantity<Item>> data;
    private boolean isSucceed = false;

    public DisplayActionResult(boolean isSuccess, @Nullable List<ItemQuantity<Item>> list) {
        this.isSucceed = isSuccess;
        data = list;
    }

    @Override
    public boolean isSucceed() {
        return isSucceed;
    }

    @Override
    public List<ItemQuantity<Item>> getData() {
        return data;
    }
}
