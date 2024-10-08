package org.example.vendingmachine.action;

import org.example.base.data.Payment;
import org.example.base.feature.ui.action.ActionResult;
import org.jetbrains.annotations.NotNull;

public class InsertPaymentActionResult implements ActionResult<Payment> {

    @NotNull
    private final Payment data;

    public InsertPaymentActionResult(@NotNull Payment payment) {
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
