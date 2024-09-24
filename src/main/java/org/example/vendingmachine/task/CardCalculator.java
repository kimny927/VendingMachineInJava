package org.example.vendingmachine.task;

import org.example.base.data.ItemInformation;
import org.example.base.data.ItemQuantity;
import org.example.base.data.OrderResultData;
import org.example.base.feature.core.worker.BaseCalculator;
import org.example.base.feature.storage.BaseStorage;
import org.example.vendingmachine.payment.CreditCard;
import org.example.vendingmachine.payment.PaymentType;

public class CardCalculator<Item extends ItemInformation> extends BaseCalculator<Item, CreditCard> {

    private final BaseStorage<Item> storage;

    public CardCalculator(BaseStorage<Item> storage) {
        this.storage = storage;
    }

    @Override
    public OrderResultData calculate(Item item, CreditCard payment) {
        ItemQuantity<Item> drinkItemQuantity = storage.getItemQuantity(item);
        ItemInformation drink = drinkItemQuantity.getItem();
        boolean isSuccess = payment.spend(drink.getPrice());
        if (isSuccess) {
            storage.updateItemQuantity(item, drinkItemQuantity.getQuantity() - 1);
            return new OrderResultData.SuccessResult(drink, payment);
        } else {
            return new OrderResultData.FailureResult(payment, "카드 한도로 구매가 불가능합니다.");
        }
    }

    @Override
    public PaymentType getPaymentType() {
        return PaymentType.CreditCard;
    }
}
