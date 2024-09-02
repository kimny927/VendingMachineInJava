package org.example.vendingmachine.useraction;

import org.jetbrains.annotations.NotNull;

public class DisplayAction extends DrinkUserAction {

    @NotNull
    private final ActionData data;

    public DisplayAction() {
        data = new ActionData();
    }

    public DisplayAction(int maximumPrice) {
        data = new ActionData();
    }

    @NotNull
    @Override
    public ActionData getData() {
        return data;
    }

    public static class ActionData extends DrinkUserAction.DrinkData {
        private final int maximumPrice;

        public ActionData() {
            maximumPrice = Integer.MAX_VALUE;
        }

        public ActionData(int maximumPrice) {
            this.maximumPrice = maximumPrice;
        }

        public int getMaximumPrice() {
            return maximumPrice;
        }

        public boolean isLimited() {
            return maximumPrice != Integer.MAX_VALUE;
        }
    }
}
