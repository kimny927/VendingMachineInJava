package org.example.vendingmachine.task;

import org.example.base.data.Payment;
import org.example.base.data.OrderResultData;
import org.example.base.feature.core.worker.BaseCalculator;
import org.example.vendingmachine.Drink;
import org.example.vendingmachine.payment.Cash;
import org.example.vendingmachine.payment.CreditCard;
import org.example.vendingmachine.storage.DrinkStorage;

public class DrinkCalculator extends BaseCalculator<Drink, Payment> {

    private DrinkCashCalculator cashCalculator = null;
    private DrinkCardCalculator cardCalculator = null;

    private final DrinkStorage storage;

    public DrinkCalculator(DrinkStorage storage) {
        this.storage = storage;
    }

    @Override
    public OrderResultData calculate(Drink item, Payment payment) throws IllegalArgumentException {
        if(payment instanceof Cash) {
            if(cashCalculator == null) {
                cashCalculator = new DrinkCashCalculator(storage);
            }
            return cashCalculator.calculate(item, (Cash) payment);
        } else if(payment instanceof CreditCard) {
            if(cardCalculator == null) {
                cardCalculator = new DrinkCardCalculator(storage);
            }
            return cardCalculator.calculate(item, (CreditCard) payment);
        } else {
            throw new IllegalArgumentException("payment 인자 확인. payment: "+ payment);
        }
    }
}
