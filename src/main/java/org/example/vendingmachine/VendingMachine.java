package org.example.vendingmachine;

import org.example.Util;
import org.example.base.data.ItemInformation;
import org.example.base.data.OrderResultData;
import org.example.vendingmachine.action.DisplayActionResult;
import org.example.vendingmachine.core.CoreWorker;
import org.example.vendingmachine.ui.VendingMachineInterface;

public class VendingMachine<Item extends ItemInformation> {

    private final CoreWorker<Item> core;
    private final VendingMachineInterface<Item> userInterface;

    private boolean runningFlag = true;

    public VendingMachine(
            VendingMachineInterface<Item> vendingMachineInterface,
            CoreWorker<Item> core) {
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
    }

    public void order() {
        core.order(
                userInterface::pay,
                (payment, itemQuantities) -> userInterface.chooseItem(payment.getBudget(), itemQuantities),
                orderActionResult -> userInterface.completeService(orderActionResult.getData())
        );
    }

    @SuppressWarnings("unchecked")
    public void display() {
        core.display(
                drinkList -> (DisplayActionResult<Item>) userInterface.display(drinkList),
                result -> {
                    if (!result.isSucceed()) {
                        userInterface.completeService(new OrderResultData.ErrorResult(null, "상품 목록 확인 문제가 발생했습니다."));
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
            userInterface.completeService(new OrderResultData.ErrorResult(e, e.getMessage()));
        }
    }

}
