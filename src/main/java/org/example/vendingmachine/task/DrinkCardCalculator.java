package org.example.vendingmachine.task;

import org.example.base.data.ItemInformation;
import org.example.base.data.ItemQuantity;
import org.example.base.data.OrderResultData;
import org.example.base.feature.core.worker.BaseCalculator;
import org.example.vendingmachine.Drink;
import org.example.vendingmachine.payment.CreditCard;
import org.example.vendingmachine.storage.DrinkStorage;

public class DrinkCardCalculator extends BaseCalculator<Drink, CreditCard> {

    private final DrinkStorage storage;

    public DrinkCardCalculator(DrinkStorage storage) {
        this.storage = storage;
    }

    @Override
    public OrderResultData calculate(Drink item, CreditCard payment) {
        ItemQuantity drinkItemQuantity = storage.getItemQuantity(item);
        ItemInformation drink = drinkItemQuantity.getItem();
        boolean isSuccess = payment.spend(drink.getPrice());
        if(isSuccess) {
            storage.updateItemQuantity(item, drinkItemQuantity.getQuantity() - 1);
            return new OrderResultData.SuccessResult(drink, payment);
        } else {
            return new OrderResultData.FailureResult(payment, "카드 한도로 구매가 불가능합니다.");
        }
    }
}
