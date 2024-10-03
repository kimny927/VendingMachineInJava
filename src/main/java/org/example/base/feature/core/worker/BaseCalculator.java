package org.example.base.feature.core.worker;

import org.example.base.data.ItemInformation;
import org.example.base.data.OrderResultData;
import org.example.base.data.Payment;
import org.example.vendingmachine.payment.PaymentAgent;

public abstract class BaseCalculator<I extends ItemInformation, P extends Payment> implements Worker {

    abstract public OrderResultData calculate(I item, P payment);

    abstract public Class<P> targetPaymentClass();

    abstract public PaymentAgent paymentAgent();

}
