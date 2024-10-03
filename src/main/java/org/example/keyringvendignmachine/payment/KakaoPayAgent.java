package org.example.keyringvendignmachine.payment;

import org.example.base.data.Payment;
import org.example.vendingmachine.payment.PaymentAgent;

public class KakaoPayAgent implements PaymentAgent {

    private static KakaoPayAgent instance;

    private KakaoPayAgent() {
    }

    public static KakaoPayAgent getInstance() {
        if (instance == null) {
            instance = new KakaoPayAgent();
        }
        return instance;
    }

    @Override
    public Payment getPayment() {
        return KakaoPay.getRandomInstance();
    }

    @Override
    public String getName() {
        return "카카오페이";
    }


}
