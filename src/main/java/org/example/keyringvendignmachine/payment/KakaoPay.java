package org.example.keyringvendignmachine.payment;

import org.example.base.data.Payment;

import java.util.Random;

public class KakaoPay implements Payment {

    private int budget;

    public KakaoPay(int budget) {
        this.budget = budget;
        System.out.println("잔액 : " + budget);
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

    private static final Random random = new Random();

    public static KakaoPay getRandomInstance() {
        return new KakaoPay(random.nextInt(100_000));
    }
}
