package org.example.vendingmachine.task;

import org.example.base.data.ItemInformation;
import org.example.base.data.ItemQuantity;
import org.example.base.data.OrderResultData;
import org.example.base.feature.core.worker.BaseCalculator;
import org.example.vendingmachine.Drink;
import org.example.vendingmachine.payment.Cash;
import org.example.vendingmachine.storage.DrinkStorage;

public class DrinkCashCalculator extends BaseCalculator<Drink, Cash> {

    private final DrinkStorage storage;

    public DrinkCashCalculator(DrinkStorage storage) {
        this.storage = storage;
    }

    @Override
    public OrderResultData calculate(Drink item, Cash payment) {
        ItemQuantity drinkItemQuantity = storage.getItemQuantity(item);
        ItemInformation drink = drinkItemQuantity.getItem();
        boolean isSuccess = payment.spend(drink.getPrice());
        if(isSuccess) {
            storage.updateItemQuantity(item, drinkItemQuantity.getQuantity() - 1);
            if(payment.getBudget() == 0) {
                return new OrderResultData.SuccessResult(drink, null);
            } else {
                return new OrderResultData.SuccessResult(drink, payment);
            }
        } else {
            return new OrderResultData.FailureResult(payment, "돈이 모자릅니다");
        }
    }
}
