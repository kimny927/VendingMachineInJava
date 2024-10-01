package org.example.vendingmachine.ui;

import org.example.Util;
import org.example.base.data.ItemInformation;
import org.example.base.data.ItemQuantity;
import org.example.base.data.OrderResultData;
import org.example.base.data.Payment;
import org.example.base.feature.ui.BaseInterface;
import org.example.base.feature.ui.action.ActionResult;
import org.example.vendingmachine.action.ChooseItemActionResult;
import org.example.vendingmachine.action.DisplayActionResult;
import org.example.vendingmachine.action.ExceptionalActionResult;
import org.example.vendingmachine.action.InsertPaymentActionResult;
import org.example.vendingmachine.payment.Cash;
import org.example.vendingmachine.payment.CreditCard;
import org.example.vendingmachine.payment.PaymentAgent;

import java.util.List;

public class VendingMachineInterface<Item extends ItemInformation>
        implements BaseInterface<Item, ActionResult<?>> {

    @Override
    public ActionResult<?> chooseItem(Integer maximumPrice, List<ItemQuantity<Item>> list) {
        println("목록을 보고 구매할 물품을 선택해주세요.");
        if (list.isEmpty()) {
            System.out.println("판매 상품이 없습니다.");
            return new ExceptionalActionResult("판매 상품이 없습니다.");
        }
        println("-----------목록------------");

        for (int index = 0; index < list.size(); index++) {
            ItemQuantity<Item> itemQuantity = list.get(index);
            String state = (!itemQuantity.isEnoughQuantity()) ? "매진" :
                    (itemQuantity.isEnoughBudget(maximumPrice)) ? "구매 가능" : "구매 불가능";
            println("번호 : " + index + " " + itemQuantity.getItem() + " " + state);
        }
        println("-------------------------");

        print("입력 : ");

        try {
            int number;
            if (Util.scanner.hasNextInt()) {
                number = Util.scanner.nextInt();
                Util.scanner.nextLine();
            } else {
                return new ExceptionalActionResult("입력 값에 문제가 있어 주문을 취소합니다.");
            }

            ItemQuantity<Item> drink = list.get(number);
            if (drink.isEnoughQuantity() && drink.isEnoughBudget(maximumPrice)) {
                println("선택한 물품 : " + number + "번 " + list.get(number).getItem());
                return new ChooseItemActionResult<Item>(list.get(number).getItem());
            } else {
                return new ExceptionalActionResult("해당 물품은 재고 소진으로 구매할 수 없습니다.");
            }
        } catch (NumberFormatException e) {
            return new ExceptionalActionResult("입력 값에 문제가 있어 주문을 취소합니다.");
        } catch (IndexOutOfBoundsException e) {
            return new ExceptionalActionResult("해당 번호의 물품은 존재하지 않습니다");
        } catch (Exception e) {
            return new ExceptionalActionResult(e.getMessage());
        }
    }

    @Override
    public ActionResult<?> pay(List<PaymentAgent> paymentAgents) {
        println("지불 방식을 선택해주세요.");
        for (int index = 0; index < paymentAgents.size(); index++) {
            print(index + ". " + paymentAgents.get(index).getName() + ", ");
        }
        println("그 외 : 취소");

        try {
            int input = Integer.parseInt(Util.scanner.nextLine());
            if (input >= 0 && input < paymentAgents.size()) {
                Payment myPayment = paymentAgents.get(input).getPayment();

                println(paymentAgents.get(input).getName() + "를/을 삽입했습니다.");

                if (myPayment != null) {
                    return new InsertPaymentActionResult(myPayment);
                } else {
                    return new ExceptionalActionResult("지불 수단 투입에 문제가 생겼습니다.");
                }

            } else {
                return new ExceptionalActionResult("주문을 취소합니다.");
            }
        } catch (Exception e) {
            return new ExceptionalActionResult("주문을 취소합니다.");
        }
    }

    public ActionResult<?> display(List<ItemQuantity<Item>> list) {
        println("자판기에서 판매하는 물품들을 확인합니다.");

        if (list.isEmpty()) {
            System.out.println("판매 상품이 없습니다.");
        } else {
            println("-----------목록------------");

            for (int index = 0; index < list.size(); index++) {
                ItemQuantity<Item> itemQuantity = list.get(index);
                String state = itemQuantity.isEnoughQuantity() ? "구매 가능" : "매진";
                println("번호 : " + index + " " + itemQuantity + " " + state);
            }
            println("-------------------------");
        }
        return new DisplayActionResult<Item>(true, list);

    }

    public void completeService(OrderResultData resultData) {
        if (resultData instanceof OrderResultData.SuccessResult) {
            OrderResultData.SuccessResult success = (OrderResultData.SuccessResult) resultData;

            if (success.getPayment() == null) {
                System.out.println("거스름 돈이 없습니다.");
            } else {
                String comment;
                if (success.getPayment() instanceof Cash) {
                    comment = "거스름 돈이 나왔습니다.";
                } else if (success.getPayment() instanceof CreditCard) {
                    comment = "카드를 반환합니다.";
                } else {
                    comment = "계산을 완료했습니다.";
                }
                System.out.println(comment + " 잔액 : " + success.getPayment().getBudget());
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
        } else {
            System.out.println("예외 사항. resultData 확인. resultData:" + resultData);
        }
    }


    private void println(String message) {
        System.out.println(message);
    }

    private void print(String message) {
        System.out.print(message);
    }

}
