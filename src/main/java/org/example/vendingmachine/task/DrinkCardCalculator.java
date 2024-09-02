package org.example.vendingmachine.task;

import org.example.base.data.ItemQuantity;
import org.example.base.data.ResultData;
import org.example.base.feature.core.task.BaseCalculator;
import org.example.vendingmachine.Drink;
import org.example.vendingmachine.payment.CreditCard;
import org.example.vendingmachine.storage.DrinkStorage;

public class DrinkCardCalculator implements BaseCalculator<Drink, CreditCard> {

    private final DrinkStorage storage;

    public DrinkCardCalculator(DrinkStorage storage) {
        this.storage = storage;
    }

    @Override
    public ResultData calculate(Drink item, CreditCard payment) {
        ItemQuantity<Drink> drinkItemQuantity = storage.getItemQuantity(item);
        Drink drink = drinkItemQuantity.getItem();
        boolean isSuccess = payment.spend(drink.getPrice());
        if(isSuccess) {
            storage.updateItemQuantity(item, drinkItemQuantity.getQuantity() - 1);
            return new ResultData.SuccessResult(drink, payment);
        } else {
            return new ResultData.FailureResult(payment, "카드 한도로 구매가 불가능합니다.");
        }
    }
}
