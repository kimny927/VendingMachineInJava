package org.example.vendingmachine.useraction;

import org.example.vendingmachine.Drink;
import org.jetbrains.annotations.NotNull;

public class ChooseItemAction extends DrinkUserAction {

    @NotNull
    private final ActionData data;

    public ChooseItemAction(Drink item) {
        data = new ActionData(item, 1);
    }

    public ChooseItemAction(Drink item, int number) {
        data = new ActionData(item, number);
    }

    @NotNull
    @Override
    public ActionData getData() {
        return data;
    }

    public static class ActionData extends DrinkUserAction.DrinkData {
        @NotNull
        private final Drink item;
        private final int number;

        ActionData(@NotNull Drink item, int number) {
            this.item = item;
            this.number = number;
        }

        @NotNull
        public Drink getItem() {
            return item;
        }

        public int getNumber() {
            return number;
        }
    }
}
