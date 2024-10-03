package org.example.vendingmachine.payment;

import org.example.base.data.Payment;

public interface PaymentAgent {

    Payment getPayment();

    String getName();
}
