package org.example.vendingmachine.action;

import org.jetbrains.annotations.NotNull;

public class DrinkExceptionalActionResult extends DrinkActionResult<DrinkExceptionalActionResult.Data> {

    @NotNull
    private final Data data;

    public DrinkExceptionalActionResult() {
        this.data = new Data(null);
    }

    public DrinkExceptionalActionResult(String message) {
        this.data = new Data(message);
    }

    public DrinkExceptionalActionResult(@NotNull Data data) {
        this.data = data;
    }

    @Override
    public boolean isSucceed() {
        return false;
    }

    public static class Data {
        private final String message;

        public Data(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    @Override
    public Data getData() {
        return data;
    }
}
