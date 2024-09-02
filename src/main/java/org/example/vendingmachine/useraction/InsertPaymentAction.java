package org.example.vendingmachine.useraction;

import org.example.base.data.Payment;
import org.jetbrains.annotations.NotNull;

public class InsertPaymentAction extends DrinkUserAction {

    @NotNull
    private final ActionData data;

    public InsertPaymentAction(Payment payment) {
        data = new ActionData(payment);
    }

    @NotNull
    @Override
    public ActionData getData() {
        return data;
    }

    static public class ActionData extends DrinkUserAction.DrinkData {
        @NotNull
        private final Payment payment;

        public ActionData(@NotNull Payment payment) {
            this.payment = payment;
        }

        @NotNull
        public Payment getPayment() {
            return payment;
        }
    }


}
