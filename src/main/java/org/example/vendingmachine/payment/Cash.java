package org.example.vendingmachine.payment;

import org.example.base.data.Payment;

public class Cash implements Payment {

    private int budget;

    public Cash(int budget) {
        this.budget = budget;
        System.out.println("금액 :" + budget);
    }

    @Override
    public int getBudget() {
        return budget;
    }

    @Override
    public boolean spend(int price) {
        int result = budget - price;
        if (result >= 0) {
            budget = result;
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "현금 {" +
                "남은 돈:" + budget +
                '}';
    }
}
