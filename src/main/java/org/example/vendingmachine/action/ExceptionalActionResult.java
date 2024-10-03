package org.example.vendingmachine.action;

import org.example.base.feature.ui.action.ActionResult;
import org.jetbrains.annotations.NotNull;

public class ExceptionalActionResult implements ActionResult<ExceptionalActionResult.Data> {

    @NotNull
    private final Data data;

    public ExceptionalActionResult() {
        this.data = new Data(null);
    }

    public ExceptionalActionResult(String message) {
        this.data = new Data(message);
    }

    public ExceptionalActionResult(@NotNull Data data) {
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

    @NotNull
    @Override
    public Data getData() {
        return data;
    }
}
