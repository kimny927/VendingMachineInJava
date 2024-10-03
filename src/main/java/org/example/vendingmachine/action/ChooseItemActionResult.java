package org.example.vendingmachine.action;

import org.example.base.data.ItemInformation;
import org.example.base.feature.ui.action.ActionResult;
import org.jetbrains.annotations.NotNull;

public class ChooseItemActionResult<Item extends ItemInformation> implements ActionResult<ChooseItemActionResult.Data<Item>> {

    @NotNull
    private final Data<Item> data;

    public ChooseItemActionResult(Item item) {
        data = new Data<>(item, 1);
    }

    public ChooseItemActionResult(Item item, int number) {
        data = new Data<>(item, number);
    }

    @Override
    public boolean isSucceed() {
        return true;
    }

    @NotNull
    @Override
    public Data<Item> getData() {
        return data;
    }

    public static class Data<Item extends ItemInformation> {
        @NotNull
        private final Item item;
        private final int number;

        Data(@NotNull Item item, int number) {
            this.item = item;
            this.number = number;
        }

        @NotNull
        public Item getItem() {
            return item;
        }

        public int getNumber() {
            return number;
        }
    }
}
