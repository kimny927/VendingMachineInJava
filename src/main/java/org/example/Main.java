package org.example;

import org.example.keyringvendignmachine.item.KeyRing;
import org.example.keyringvendignmachine.storage.KeyRingStorage;
import org.example.keyringvendignmachine.task.KakaoPayCalculator;
import org.example.vendingmachine.Drink;
import org.example.vendingmachine.VendingMachine;
import org.example.vendingmachine.core.CoreWorker;
import org.example.vendingmachine.storage.DrinkStorage;
import org.example.vendingmachine.task.CardCalculator;
import org.example.vendingmachine.task.CashCalculator;
import org.example.vendingmachine.task.CoreCalculator;
import org.example.vendingmachine.task.ItemReader;
import org.example.vendingmachine.ui.VendingMachineInterface;

public class Main {
    public static void main(String[] args) {

        runDrinkVendingMachine();

        runKeyRingVendingMachine();

        System.out.println("프로그램을 마칩니다.");
        Util.scanner.close();
    }

    static void runDrinkVendingMachine() {
        DrinkStorage storage = new DrinkStorage();
        VendingMachine<Drink> vendingMachine = new VendingMachine<>(
                new VendingMachineInterface<>(),
                new CoreWorker<>(new ItemReader<>(storage),
                        new CoreCalculator<>(new CashCalculator<>(storage), new CardCalculator<>(storage)))
        );
        vendingMachine.start();
    }

    static void runKeyRingVendingMachine() {
        KeyRingStorage keyRingStorage = new KeyRingStorage();
        VendingMachine<KeyRing> keyRingVendingMachine = new VendingMachine<>(
                new VendingMachineInterface<>(),
                new CoreWorker<>(new ItemReader<>(keyRingStorage),
                        new CoreCalculator<>(
                                new KakaoPayCalculator<>(keyRingStorage)
                        )
                )
        );
        keyRingVendingMachine.start();
    }
}