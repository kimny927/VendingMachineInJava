package org.example.vendingmachine.payment.agent;

import org.example.base.data.Payment;
import org.example.vendingmachine.payment.CreditCard;
import org.example.vendingmachine.payment.PaymentAgent;

public class CreditCardAgent implements PaymentAgent {

    private static CreditCardAgent instance;

    private CreditCardAgent() {
    }

    public static CreditCardAgent getInstance() {
        if (instance == null) {
            instance = new CreditCardAgent();
        }
        return instance;
    }

    @Override
    public Payment getPayment() {
        return new CreditCard();
    }

    @Override
    public String getName() {
        return "신용 카드";
    }
}
