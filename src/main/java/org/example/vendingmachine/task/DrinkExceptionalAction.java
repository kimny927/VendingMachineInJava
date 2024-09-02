package org.example.vendingmachine.task;

import org.example.vendingmachine.useraction.DrinkUserAction;
import org.jetbrains.annotations.NotNull;

public class DrinkExceptionalAction extends DrinkUserAction {

    @NotNull
    private final ActionData data;

    public DrinkExceptionalAction() {
        this.data = new ActionData(null);
    }

    public DrinkExceptionalAction(String message) {
        this.data = new ActionData(message);
    }

    public DrinkExceptionalAction(@NotNull ActionData data) {
        this.data = data;
    }

    public static class ActionData extends DrinkData {
        private final String message;

        public ActionData(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    @Override
    public DrinkData getData() {
        return data;
    }
}
