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

    /**
     * 아이템 목록 노출
     * @param displayFunction 아이템 리스트를 인자로 받아 사용자에게 아이템 목록을 노출하는 메소드
     * @param onResult 결과 처리 메소드
     */
    public void display(Function<List<ItemQuantity>, DisplayActionResult> displayFunction,
                        Consumer<DisplayActionResult> onResult) {
        DisplayActionResult result = displayFunction.apply(reader.getItems());
        onResult.accept(result);
    }

    /**
     * 주문 과정 1) 지불 수단을 받고 2) 구매할 목록을 선택해 3) 처리 결과에 대응
     * @param insetPaymentFunction 사용자에게서 지불 수단을 받는 메소드
     * @param chooseItemFunction 지불 수단과 아이템 목록을 인자로 받아 구매할 아이템을 고르는 메소드
     * @param onResult 처리된 결과를 담당하는 메소드
     */
    public void order(Supplier<DrinkActionResult<?>> insetPaymentFunction,
                      BiFunction<Payment, List<ItemQuantity>, DrinkActionResult<?>> chooseItemFunction,
                      Consumer<OrderActionResult> onResult) {
        DrinkActionResult<?> paymentSupplierResult = insetPaymentFunction.get();

        if (paymentSupplierResult instanceof InsertPaymentActionResult) {
            InsertPaymentActionResult paymentResult = (InsertPaymentActionResult) paymentSupplierResult;
            Payment payment = paymentResult.getData();
            DrinkActionResult<?> drinkPickInteractionResult = chooseItemFunction.apply(payment, reader.getItems());

            if (drinkPickInteractionResult instanceof ChooseItemActionResult) {
                ChooseItemActionResult chooseItemResult = (ChooseItemActionResult) drinkPickInteractionResult;
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
