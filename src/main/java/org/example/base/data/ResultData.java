package org.example.base.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ResultData {

    class FailureResult implements ResultData {
        @NotNull
        private final Payment payment;
        @Nullable
        private final String message;

        public FailureResult(@NotNull Payment payment, @Nullable String message) {
            this.payment = payment;
            this.message = message;
        }

        @NotNull
        public Payment getPayment() {
            return payment;
        }

        @Nullable
        public String getMessage() {
            return message;
        }
    }

    class SuccessResult implements ResultData {
        @NotNull
        private final ItemInformation item;
        @Nullable
        private final Payment payment;
        public SuccessResult(@NotNull ItemInformation item, @Nullable Payment payment) {
            this.item = item;
            this.payment = payment;
        }

        @NotNull
        public ItemInformation getItem() {
            return item;
        }

        @Nullable
        public Payment getPayment() {
            return payment;
        }
    }

    class ErrorResult implements ResultData {
        @Nullable
        private final Exception e;
        @Nullable
        private final String message;

        public ErrorResult(@Nullable Exception e, @Nullable String message) {
            this.e = e;
            this.message = message;
        }

        @Nullable
        public Exception getException() {
            return e;
        }

        @Nullable
        public String getMessage() {
            return message;
        }
    }

}
