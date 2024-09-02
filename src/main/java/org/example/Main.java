package org.example;

import org.example.vendingmachine.DrinkVendingMachine;
import org.example.vendingmachine.core.DrinkVendingMachineCore;
import org.example.vendingmachine.ui.DrinkVendingMachineInterface;

public class Main {
    public static void main(String[] args) {
        DrinkVendingMachine vendingMachine = new DrinkVendingMachine(
                new DrinkVendingMachineInterface(),
                new DrinkVendingMachineCore()
        );
        vendingMachine.start();

        System.out.println("프로그램의 마칩니다.");
    }
}