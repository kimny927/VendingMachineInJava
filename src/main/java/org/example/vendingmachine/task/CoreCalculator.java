package org.example.vendingmachine.task;

import org.example.base.data.ItemInformation;
import org.example.base.data.OrderResultData;
import org.example.base.data.Payment;
import org.example.base.feature.core.worker.BaseCalculator;
import org.example.keyringvendignmachine.payment.KakaoPay;
import org.example.keyringvendignmachine.task.KakaoPayCalculator;
import org.example.vendingmachine.payment.Cash;
import org.example.vendingmachine.payment.CreditCard;
import org.example.vendingmachine.payment.PaymentType;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CoreCalculator<Item extends ItemInformation> extends BaseCalculator<Item, Payment> {

    private final LinkedHashMap<PaymentType, BaseCalculator<Item, ? extends Payment>> map;
    private final List<PaymentType> paymentTypes;

    @SafeVarargs
    public CoreCalculator(BaseCalculator<Item, ? extends Payment>... calculators) {
        this.map = new LinkedHashMap<>();
        this.paymentTypes = new ArrayList<>();
        for (BaseCalculator<Item, ? extends Payment> calculator : calculators) {
            this.map.put(calculator.getPaymentType(), calculator);
            this.paymentTypes.add(calculator.getPaymentType());
            System.out.println("CoreCalculator " + calculator.getPaymentType().getName() + ", " + calculator);
        }
    }

    public List<PaymentType> getSupportPayment() {
        return paymentTypes;
    }

    @SuppressWarnings("unchecked")
    @Override
    public OrderResultData calculate(Item item, Payment payment) throws IllegalArgumentException {

        BaseCalculator<Item, ? extends Payment> calculator = map.get(payment.getPaymentType());
        if (calculator == null) {
            return new OrderResultData.FailureResult(payment, "지원하지 않는 지불 수단입니다. " + payment);
        }

        if (calculator instanceof CashCalculator) {
            CashCalculator<Item> cashCalculator = (CashCalculator<Item>) calculator;
            return cashCalculator.calculate(item, (Cash) payment);
        } else if (calculator instanceof CardCalculator) {
            CardCalculator<Item> cashCalculator = (CardCalculator<Item>) calculator;
            return cashCalculator.calculate(item, (CreditCard) payment);
        } else if (calculator instanceof KakaoPayCalculator) {
            KakaoPayCalculator<Item> cashCalculator = (KakaoPayCalculator<Item>) calculator;
            return cashCalculator.calculate(item, (KakaoPay) payment);
        } else {
            return new OrderResultData.FailureResult(payment, "지원하지 않는 지불 수단입니다. --" + payment);
        }
    }

    @Override
    public PaymentType getPaymentType() {
        return null;
    }
}
