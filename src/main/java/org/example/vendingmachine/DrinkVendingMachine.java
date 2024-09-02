package org.example.vendingmachine;

import org.example.Util;
import org.example.base.BaseVendingMachine;
import org.example.base.data.Payment;
import org.example.base.data.ResultData;
import org.example.vendingmachine.core.DrinkVendingMachineCore;
import org.example.vendingmachine.exception.VMWrongActionException;
import org.example.vendingmachine.payment.Cash;
import org.example.vendingmachine.payment.CreditCard;
import org.example.vendingmachine.task.DrinkExceptionalAction;
import org.example.vendingmachine.ui.DrinkVendingMachineInterface;
import org.example.vendingmachine.useraction.ChooseItemAction;
import org.example.vendingmachine.useraction.DisplayAction;
import org.example.vendingmachine.useraction.DrinkUserAction;
import org.example.vendingmachine.useraction.InsertPaymentAction;

public class DrinkVendingMachine extends BaseVendingMachine<DrinkUserAction.DrinkData, DrinkUserAction> {

    private final DrinkVendingMachineCore core;

    private Payment temporarayPayment = null;

    public DrinkVendingMachine(
            DrinkVendingMachineInterface vendingMachineInterface,
            DrinkVendingMachineCore core) {
        vendingMachineInterface.setCallBack(() -> core.getItems(new DisplayAction()));
        this.userInterface = vendingMachineInterface;
        this.core = core;
    }

    public void start() {
        while (true) {
            try {
                interact();
            } catch (Exception e) {
                System.out.println("이슈 발생 : " + e);
            }
        }
    }

    @Override
    protected void interact() {
        System.out.println();
        System.out.println("원하는 작업에 따라 숫자를 입력해주세요.\n " +
                "1: 물품 보기, 2: 주문하기, 그 외 숫자 : 종료");
        System.out.print("입력 : ");


        try {
            Thread.sleep(700);
            if (!Util.scanner.hasNextInt()) {
                System.out.println("종료합니다.");
                System.exit(0);
                return;
            }

            int taskNumber = Util.scanner.nextInt();
            Util.scanner.nextLine();
            switch (taskNumber) {
                case 1:
                    display();
                    break;
                case 2:
                    DrinkUserAction action = pay();
                    if (action instanceof InsertPaymentAction) {
                        InsertPaymentAction paymentAction = (InsertPaymentAction) action;
                        InsertPaymentAction.ActionData data = paymentAction.getData();
                        Payment payment = data.getPayment();
                        temporarayPayment = payment;

                        DrinkUserAction itemAction = chooseItem(payment.getBudget());
                        if (itemAction == null) {
                            completeService(new ResultData.FailureResult(payment, "판매 물품이 없습니다."));
                        } else if (itemAction instanceof ChooseItemAction) {
                            ChooseItemAction chooseItemAction = (ChooseItemAction) itemAction;
                            ResultData result = core.order(chooseItemAction.getData().getItem(), payment);
                            completeService(result);
                        } else if (itemAction instanceof DrinkExceptionalAction) {
                            DrinkExceptionalAction exceptionalAction = (DrinkExceptionalAction) itemAction;
                            completeService(new ResultData.FailureResult(temporarayPayment, ((DrinkExceptionalAction.ActionData) exceptionalAction.getData()).getMessage()));
                        } else {
                            throw new IllegalArgumentException("DrinkUserAction 타입 확인 필요. itemAction :" + itemAction);
                        }
                    } else if (action instanceof DrinkExceptionalAction) {
                        DrinkExceptionalAction exceptionalAction = (DrinkExceptionalAction) action;
                        completeService(new ResultData.FailureResult(temporarayPayment, ((DrinkExceptionalAction.ActionData) exceptionalAction.getData()).getMessage()));
                    } else {
                        throw new VMWrongActionException("주문 과정에 문제가 생겼습니다.");

                    }
                    break;
                default:
                    System.out.println("종료합니다.");
                    System.exit(0);
            }
        } catch (Exception e) {
            System.out.println("문제가 발생했습니다.");
            e.printStackTrace();
            completeService(new ResultData.ErrorResult(e, e.getMessage()));
        }
    }

    private void completeService(ResultData resultData) {
        if (resultData instanceof ResultData.SuccessResult) {
            ResultData.SuccessResult success = (ResultData.SuccessResult) resultData;
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
        } else if (resultData instanceof ResultData.FailureResult) {
            ResultData.FailureResult failure = (ResultData.FailureResult) resultData;
            System.out.println("구매에 실패했습니다");
            System.out.println(failure.getMessage());

            System.out.println("투입한 현금/카드를 반환합니다. " + failure.getPayment());
        } else if (resultData instanceof ResultData.ErrorResult) {
            ResultData.ErrorResult error = (ResultData.ErrorResult) resultData;
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
