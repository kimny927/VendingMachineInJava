package org.example.keyringvendignmachine.task;

import org.example.base.data.ItemInformation;
import org.example.base.data.ItemQuantity;
import org.example.base.data.OrderResultData;
import org.example.base.feature.core.worker.BaseCalculator;
import org.example.base.feature.storage.BaseStorage;
import org.example.keyringvendignmachine.payment.KakaoPay;
import org.example.vendingmachine.payment.PaymentType;
import org.jetbrains.annotations.NotNull;

public class KakaoPayCalculator<Item extends ItemInformation> extends BaseCalculator<Item, KakaoPay> {

    @NotNull
    private final BaseStorage<Item> storage;

    public KakaoPayCalculator(@NotNull BaseStorage<Item> storage) {
        this.storage = storage;
    }

    @Override
    public OrderResultData calculate(Item item, KakaoPay payment) {
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
    public PaymentType getPaymentType() {
        return PaymentType.KakaoPay;
    }
}
