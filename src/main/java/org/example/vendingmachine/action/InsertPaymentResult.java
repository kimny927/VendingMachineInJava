package org.example.vendingmachine.action;

import org.example.base.data.Payment;
import org.jetbrains.annotations.NotNull;

public class InsertPaymentResult extends DrinkActionResult<Payment> {

    @NotNull
    private final Payment data;

    public InsertPaymentResult(@NotNull Payment payment) {
        data = payment;
    }

    @NotNull
    @Override
    public Payment getData() {
        return data;
    }

    @Override
    public boolean isSucceed() {
        return true;
    }

}
