package org.example.vendingmachine.payment.agent;

import org.example.Util;
import org.example.base.data.Payment;
import org.example.vendingmachine.payment.Cash;
import org.example.vendingmachine.payment.PaymentAgent;

public class CashAgent implements PaymentAgent {

    private static CashAgent instance;

    private CashAgent() {
    }

    public static CashAgent getInstance() {
        if (instance == null) {
            instance = new CashAgent();
        }
        return instance;
    }

    @Override
    public Payment getPayment() {
        System.out.println("투입할 현금 액수를 입력하세요.");
        System.out.print("입력: ");
        String priceInput = Util.scanner.nextLine();
        try {
            int price = Integer.parseInt(priceInput);
            return new Cash(price);
        } catch (NumberFormatException e) {
            e.printStackTrace();
//            "입력 값에 문제가 있어 주문을 취소합니다."
            return null;
        }

    }

    @Override
    public String getName() {
        return "현금";
    }
}
