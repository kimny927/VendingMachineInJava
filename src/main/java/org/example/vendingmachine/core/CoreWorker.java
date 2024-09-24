package org.example.vendingmachine.core;

import org.example.base.data.ItemInformation;
import org.example.base.data.ItemQuantity;
import org.example.base.data.OrderResultData;
import org.example.base.data.Payment;
import org.example.base.feature.ui.action.ActionResult;
import org.example.vendingmachine.action.*;
import org.example.vendingmachine.payment.PaymentType;
import org.example.vendingmachine.task.CoreCalculator;
import org.example.vendingmachine.task.ItemReader;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

public class CoreWorker<Item extends ItemInformation> {

    private final ItemReader<Item> reader;
    private final CoreCalculator<Item> calculator;

    public CoreWorker(ItemReader<Item> reader, CoreCalculator<Item> calculator) {
        this.reader = reader;
        this.calculator = calculator;
    }

    /**
     * 아이템 목록 노출
     *
     * @param displayFunction 아이템 리스트를 인자로 받아 사용자에게 아이템 목록을 노출하는 메소드
     * @param onResult        결과 처리 메소드
     */
    public void display(Function<List<ItemQuantity<Item>>, DisplayActionResult<Item>> displayFunction,
                        Consumer<DisplayActionResult<Item>> onResult) {
        DisplayActionResult<Item> result = displayFunction.apply(reader.getItems());
        onResult.accept(result);
    }

    /**
     * 주문 과정 1) 지불 수단을 받고 2) 구매할 목록을 선택해 3) 처리 결과에 대응
     *
     * @param insetPaymentFunction 사용자에게서 지불 수단을 받는 메소드
     * @param chooseItemFunction   지불 수단과 아이템 목록을 인자로 받아 구매할 아이템을 고르는 메소드
     * @param onResult             처리된 결과를 담당하는 메소드
     */
    public void order(Function<List<PaymentType>, ActionResult<?>> insetPaymentFunction,
                      BiFunction<Payment, List<ItemQuantity<Item>>, ActionResult<?>> chooseItemFunction,
                      Consumer<OrderActionResult> onResult) {
        ActionResult<?> paymentSupplierResult = insetPaymentFunction.apply(calculator.getSupportPayment());

        if (paymentSupplierResult instanceof InsertPaymentActionResult) {
            InsertPaymentActionResult paymentResult = (InsertPaymentActionResult) paymentSupplierResult;
            Payment payment = paymentResult.getData();
            ActionResult<?> drinkPickInteractionResult = chooseItemFunction.apply(payment, reader.getItems());

            if (drinkPickInteractionResult instanceof ChooseItemActionResult) {
                @SuppressWarnings("unchecked")
                ChooseItemActionResult<Item> chooseItemResult = (ChooseItemActionResult<Item>) drinkPickInteractionResult;
                Item drink = chooseItemResult.getData().getItem();
                OrderResultData result = calculator.calculate(drink, payment);
                onResult.accept(new OrderActionResult(result));
            } else if (drinkPickInteractionResult instanceof ExceptionalActionResult) {
                ExceptionalActionResult exceptionalResult = (ExceptionalActionResult) drinkPickInteractionResult;
                onResult.accept(new OrderActionResult(new OrderResultData.FailureResult(payment, exceptionalResult.getData().getMessage())));
            } else {
                onResult.accept(new OrderActionResult(new OrderResultData.ErrorResult(null, "주문을 중단합니다.")));
            }
        } else if (paymentSupplierResult instanceof ExceptionalActionResult) {
            ExceptionalActionResult exceptionalResult = (ExceptionalActionResult) paymentSupplierResult;
            onResult.accept(new OrderActionResult(new OrderResultData.FailureResult(null, exceptionalResult.getData().getMessage())));
        } else {
            onResult.accept(new OrderActionResult(new OrderResultData.ErrorResult(null, "주문을 중단합니다.")));
        }

    }

}
