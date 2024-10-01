package org.example.vendingmachine.task;

import org.example.base.data.ItemInformation;
import org.example.base.data.ItemQuantity;
import org.example.base.data.OrderResultData;
import org.example.base.feature.core.worker.BaseCalculator;
import org.example.base.feature.storage.BaseStorage;
import org.example.vendingmachine.payment.Cash;
import org.example.vendingmachine.payment.PaymentAgent;
import org.example.vendingmachine.payment.agent.CashAgent;

public class CashCalculator<Item extends ItemInformation> extends BaseCalculator<Item, Cash> {

    private final BaseStorage<Item> storage;

    public CashCalculator(BaseStorage<Item> storage) {
        this.storage = storage;
    }

    @Override
    public OrderResultData calculate(Item item, Cash payment) {
        ItemQuantity<Item> drinkItemQuantity = storage.getItemQuantity(item);
        ItemInformation drink = drinkItemQuantity.getItem();
        boolean isSuccess = payment.spend(drink.getPrice());
        if (isSuccess) {
            storage.updateItemQuantity(item, drinkItemQuantity.getQuantity() - 1);
            if (payment.getBudget() == 0) {
                return new OrderResultData.SuccessResult(drink, null);
            } else {
                return new OrderResultData.SuccessResult(drink, payment);
            }
        } else {
            return new OrderResultData.FailureResult(payment, "돈이 모자릅니다");
        }
    }

    @Override
    public Class<Cash> targetPaymentClass() {
        return Cash.class;
    }

    @Override
    public PaymentAgent paymentAgent() {
        return CashAgent.getInstance();
    }
}
