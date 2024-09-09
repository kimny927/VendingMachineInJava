package org.example.vendingmachine.core;

import org.example.base.data.ItemQuantity;
import org.example.base.data.OrderResultData;
import org.example.base.data.Payment;
import org.example.vendingmachine.Drink;
import org.example.vendingmachine.action.*;
import org.example.vendingmachine.task.DrinkCalculator;
import org.example.vendingmachine.task.DrinkReader;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class CoreWorker {

    private final DrinkReader reader;
    private final DrinkCalculator calculator;

    public CoreWorker(DrinkReader reader, DrinkCalculator calculator) {
        this.reader = reader;
        this.calculator = calculator;
    }

    public void display(Function<List<ItemQuantity>, DisplayActionResult> displayInteraction,
                        Consumer<DisplayActionResult> onResult) {
        DisplayActionResult result = displayInteraction.apply(reader.getItems());
        onResult.accept(result);
    }

    public void order(Supplier<DrinkActionResult<?>> paymentSupplier, BiFunction<Payment, List<ItemQuantity>, DrinkActionResult<?>> drinkPickInteraction,
                      Consumer<OrderActionResult> onResult) {
        DrinkActionResult<?> paymentSupplierResult = paymentSupplier.get();

        if (paymentSupplierResult instanceof InsertPaymentResult) {
            InsertPaymentResult paymentResult = (InsertPaymentResult) paymentSupplierResult;
            Payment payment = paymentResult.getData();
            DrinkActionResult<?> drinkPickInteractionResult = drinkPickInteraction.apply(payment, reader.getItems());

            if (drinkPickInteractionResult instanceof ChooseItemResult) {
                ChooseItemResult chooseItemResult = (ChooseItemResult) drinkPickInteractionResult;
                Drink drink = chooseItemResult.getData().getItem();
                OrderResultData result = calculator.calculate(drink, payment);
                onResult.accept(new OrderActionResult(result));
            } else if (drinkPickInteractionResult instanceof DrinkExceptionalActionResult) {
                DrinkExceptionalActionResult exceptionalResult = (DrinkExceptionalActionResult) drinkPickInteractionResult;
                onResult.accept(new OrderActionResult(new OrderResultData.FailureResult(payment, exceptionalResult.getData().getMessage())));
            } else {
                onResult.accept(new OrderActionResult(new OrderResultData.ErrorResult(null, "주문을 중단합니다.")));
            }
        } else if (paymentSupplierResult instanceof DrinkExceptionalActionResult) {
            DrinkExceptionalActionResult exceptionalResult = (DrinkExceptionalActionResult) paymentSupplierResult;
            onResult.accept(new OrderActionResult(new OrderResultData.FailureResult(null, exceptionalResult.getData().getMessage())));
        } else {
            onResult.accept(new OrderActionResult(new OrderResultData.ErrorResult(null, "주문을 중단합니다.")));
        }

    }

}
