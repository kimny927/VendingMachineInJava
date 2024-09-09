package org.example.vendingmachine;

import org.example.Util;
import org.example.base.data.OrderResultData;
import org.example.base.data.Payment;
import org.example.vendingmachine.action.DisplayActionResult;
import org.example.vendingmachine.core.CoreWorker;
import org.example.vendingmachine.payment.Cash;
import org.example.vendingmachine.payment.CreditCard;
import org.example.vendingmachine.ui.DrinkVendingMachineInterface;

public class DrinkVendingMachine {

    private final CoreWorker core;
    private final DrinkVendingMachineInterface userInterface;

    private boolean runningFlag = true;

    private Payment temporarayPayment = null;

    public DrinkVendingMachine(
            DrinkVendingMachineInterface vendingMachineInterface,
            CoreWorker core) {
        this.userInterface = vendingMachineInterface;
        this.core = core;
    }

    public void start() {
        while (runningFlag) {
            try {
                interact();
            } catch (Exception e) {
                System.out.println("이슈 발생 : " + e);
            }
        }
        Util.scanner.close();
    }

    public void order() {
        core.order(
                userInterface::pay,
                (payment, itemQuantities) -> userInterface.chooseItem(payment.getBudget(), itemQuantities),
                orderActionResult -> completeService(orderActionResult.getData())
        );
    }

    public void display() {
        core.display(
                drinkList -> (DisplayActionResult) userInterface.display(drinkList),
                result -> {
                    if (!result.isSucceed()) {
                        completeService(new OrderResultData.ErrorResult(null, "상품 목록 확인 문제가 발생했습니다."));
                    }
                }
        );
    }

    protected void interact() {
        System.out.println();
        System.out.println("원하는 작업에 따라 숫자를 입력해주세요.\n " +
                "1: 물품 보기, 2: 주문하기, 그 외 숫자 : 종료");
        System.out.print("입력 : ");

        try {
            Thread.sleep(700);
            if (!Util.scanner.hasNextInt()) {
                System.out.println("종료합니다.");
                runningFlag = false;
                return;
            }

            int taskNumber = Util.scanner.nextInt();
            Util.scanner.nextLine();
            switch (taskNumber) {
                case 1:
                    display();
                    break;
                case 2:
                    order();
                    break;
                default:
                    System.out.println("종료합니다.");
                    runningFlag = false;
            }
        } catch (Exception e) {
            System.out.println("문제가 발생했습니다.");
            e.printStackTrace();
            completeService(new OrderResultData.ErrorResult(e, e.getMessage()));
        }
    }

    private void completeService(OrderResultData resultData) {
        if (resultData instanceof OrderResultData.SuccessResult) {
            OrderResultData.SuccessResult success = (OrderResultData.SuccessResult) resultData;
            if (success.getPayment() == null) {
                System.out.println("거스름 돈이 없습니다.");
            } else if (success.getPayment() instanceof Cash) {
                System.out.println("거스름 돈이 나왔습니다. " + success.getPayment());
            } else if (success.getPayment() instanceof CreditCard) {
                System.out.println("카드를 반환합니다. " + success.getPayment());
            } else {
                System.out.println("예외 사항. payment 확인. payment:" + success.getPayment());
            }

            System.out.println("구매한 물품이 나왔습니다. " + success.getItem());
        } else if (resultData instanceof OrderResultData.FailureResult) {
            OrderResultData.FailureResult failure = (OrderResultData.FailureResult) resultData;
            System.out.println("구매에 실패했습니다");
            System.out.println(failure.getMessage());

            System.out.println("투입한 현금/카드를 반환합니다. " + failure.getPayment());
        } else if (resultData instanceof OrderResultData.ErrorResult) {
            OrderResultData.ErrorResult error = (OrderResultData.ErrorResult) resultData;
            System.out.println(error.getMessage());
            System.out.println("해당 프로세스를 중지합니다.");
            if (temporarayPayment != null) {
                System.out.println("투입한 현금/카드를 반환합니다. " + temporarayPayment);
                temporarayPayment = null;
            }
        } else {
            System.out.println("예외 사항. resultData 확인. resultData:" + resultData);
            if (temporarayPayment != null) {
                System.out.println("투입한 현금/카드를 반환합니다. " + temporarayPayment);
                temporarayPayment = null;
            }
        }

        clear();
    }

    private void clear() {
        temporarayPayment = null;
    }

}
