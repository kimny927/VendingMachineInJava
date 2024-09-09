package org.example.vendingmachine.ui;

import org.example.Util;
import org.example.base.data.ItemQuantity;
import org.example.base.feature.ui.BaseInterface;
import org.example.vendingmachine.Drink;
import org.example.vendingmachine.action.*;
import org.example.vendingmachine.exception.VMInvalidInputException;
import org.example.vendingmachine.payment.Cash;
import org.example.vendingmachine.payment.CreditCard;

import java.util.List;

public class DrinkVendingMachineInterface implements BaseInterface<DrinkActionResult<?>> {

    @Override
    public DrinkActionResult<?> chooseItem(Integer maximumPrice, List<ItemQuantity> list) {
        println("목록을 보고 구매할 물품을 선택해주세요.");
        if (list.isEmpty()) {
            System.out.println("판매 상품이 없습니다.");
            return new DrinkExceptionalActionResult("판매 상품이 없습니다.");
        }
        println("-----------목록------------");

        for (int index = 0; index < list.size(); index++) {
            ItemQuantity itemQuantity = list.get(index);
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
                return new DrinkExceptionalActionResult("입력 값에 문제가 있어 주문을 취소합니다.");
            }

            ItemQuantity drink = list.get(number);
            if (drink.isEnoughQuantity() && drink.isEnoughBudget(maximumPrice)) {
                println("선택한 물품 : " + number + "번 " + list.get(number).getItem());
                return new ChooseItemResult((Drink) list.get(number).getItem());
            } else {
                return new DrinkExceptionalActionResult("해당 물품은 재고 소진으로 구매할 수 없습니다.");
            }
        } catch (NumberFormatException e) {
            return new DrinkExceptionalActionResult("입력 값에 문제가 있어 주문을 취소합니다.");
        } catch (IndexOutOfBoundsException e) {
            return new DrinkExceptionalActionResult("해당 번호의 물품은 존재하지 않습니다");
        } catch (Exception e) {
            return new DrinkExceptionalActionResult(e.getMessage());
        }
    }

    @Override
    public DrinkActionResult<?> pay() {
        println("지불 방식을 선택해주세요. \n 1. 신용 카드, 2. 현금, 그 외 : 취소");

        try {
            int input = Integer.parseInt(Util.scanner.nextLine());
            if (input == 1) {
                return payByCard();
            } else if (input == 2) {
                return payByCash();
            } else {
                return new DrinkExceptionalActionResult("주문을 취소합니다.");
            }
        } catch (Exception e) {
            return new DrinkExceptionalActionResult("주문을 취소합니다.");
        }
    }

    private InsertPaymentResult payByCash() throws VMInvalidInputException {
        println("투입할 현금 액수를 입력하세요.");
        print("입력: ");
        String priceInput = Util.scanner.nextLine();
        try {
            int price = Integer.parseInt(priceInput);
            return new InsertPaymentResult(new Cash(price));
        } catch (NumberFormatException e) {
            throw new VMInvalidInputException("입력 값에 문제가 있어 주문을 취소합니다.", e);
        }
    }

    private InsertPaymentResult payByCard() {
        println("카드를 삽입했습니다.");
        return new InsertPaymentResult(new CreditCard());
    }

    public DrinkActionResult<?> display(List<ItemQuantity> list) {
        println("자판기에서 판매하는 물품들을 확인합니다.");

        if (list.isEmpty()) {
            System.out.println("판매 상품이 없습니다.");
        } else {
            println("-----------목록------------");

            for (int index = 0; index < list.size(); index++) {
                ItemQuantity itemQuantity = list.get(index);
                String state = itemQuantity.isEnoughQuantity() ? "구매 가능" : "매진";
                println("번호 : " + index + " " + itemQuantity + " " + state);
            }
            println("-------------------------");
        }
        return DisplayActionResult.successResult(list);

    }

    private void println(String message) {
        System.out.println(message);
    }

    private void print(String message) {
        System.out.print(message);
    }

}
