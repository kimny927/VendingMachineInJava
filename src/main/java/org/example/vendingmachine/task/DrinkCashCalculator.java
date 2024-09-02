package org.example.vendingmachine.task;

import org.example.base.data.ItemQuantity;
import org.example.base.data.ResultData;
import org.example.base.feature.core.task.BaseCalculator;
import org.example.vendingmachine.Drink;
import org.example.vendingmachine.payment.Cash;
import org.example.vendingmachine.storage.DrinkStorage;

public class DrinkCashCalculator implements BaseCalculator<Drink, Cash> {

    private final DrinkStorage storage;

    public DrinkCashCalculator(DrinkStorage storage) {
        this.storage = storage;
    }
    @Override
    public ResultData calculate(Drink item, Cash payment) {
        ItemQuantity<Drink> drinkItemQuantity = storage.getItemQuantity(item);
        Drink drink = drinkItemQuantity.getItem();
        boolean isSuccess = payment.spend(drink.getPrice());
        if(isSuccess) {
            storage.updateItemQuantity(item, drinkItemQuantity.getQuantity() - 1);
            if(payment.getBudget() == 0) {
                return new ResultData.SuccessResult(drink, null);
            } else {
                return new ResultData.SuccessResult(drink, payment);
            }
        } else {
            return new ResultData.FailureResult(payment, "돈이 모자릅니다");
        }
    }
}
