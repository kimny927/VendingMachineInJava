package org.example;

import org.example.vendingmachine.DrinkVendingMachine;
import org.example.vendingmachine.core.CoreWorker;
import org.example.vendingmachine.storage.DrinkStorage;
import org.example.vendingmachine.task.DrinkCalculator;
import org.example.vendingmachine.task.DrinkReader;
import org.example.vendingmachine.ui.DrinkVendingMachineInterface;

public class Main {
    public static void main(String[] args) {
        DrinkStorage storage = new DrinkStorage();
        DrinkVendingMachine vendingMachine = new DrinkVendingMachine(
                new DrinkVendingMachineInterface(),
                new CoreWorker(new DrinkReader(storage), new DrinkCalculator(storage))
        );
        vendingMachine.start();

        System.out.println("프로그램을 마칩니다.");
    }
}