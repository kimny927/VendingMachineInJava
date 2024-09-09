package org.example.vendingmachine.action;

import org.example.vendingmachine.Drink;
import org.jetbrains.annotations.NotNull;

public class ChooseItemResult extends DrinkActionResult<ChooseItemResult.Data> {

    @NotNull
    private final Data data;

    public ChooseItemResult(Drink item) {
        data = new Data(item, 1);
    }

    public ChooseItemResult(Drink item, int number) {
        data = new Data(item, number);
    }

    @Override
    public boolean isSucceed() {
        return true;
    }

    @NotNull
    @Override
    public Data getData() {
        return data;
    }

    public static class Data {
        @NotNull
        private final Drink item;
        private final int number;

        Data(@NotNull Drink item, int number) {
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
