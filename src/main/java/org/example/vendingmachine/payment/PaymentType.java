package org.example.vendingmachine.payment;

public enum PaymentType {
    Cash("현금"), CreditCard("신용 카드"), KakaoPay("카카오페이"), Other("기타");

    private final String name;

    PaymentType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

//    public static PaymentType get(String type) {
//        try {
//            return PaymentType.valueOf(type);
//        } catch (IllegalArgumentException e) {
//            return PaymentType.Other;
//        }
//    }
}

