package org.example.vendingmachine.payment;

import org.example.base.data.Payment;

public class CreditCard implements Payment {

    private int limitBudget;

    public CreditCard() {
        this.limitBudget = Integer.MAX_VALUE;
        System.out.println("한도 없음");
    }

    public CreditCard(int budget) {
        this.limitBudget = budget;
    }

    @Override
    public int getBudget() {
        return limitBudget;
    }

    @Override
    public boolean spend(int price) {
        if (limitBudget == Integer.MAX_VALUE) {
            return true;
        }

        int result = limitBudget - price;
        if (result >= 0) {
            limitBudget = result;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String limitState = (limitBudget == Integer.MAX_VALUE) ? "없음" : String.valueOf(limitBudget);
        return "신용 카드{" +
                "카드 한도:" + limitState +
                '}';
    }
}
