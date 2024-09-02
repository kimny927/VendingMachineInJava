package org.example.vendingmachine.core;

import org.example.base.data.ItemQuantity;
import org.example.base.data.Payment;
import org.example.base.data.ResultData;
import org.example.base.feature.core.BaseCore;
import org.example.vendingmachine.Drink;
import org.example.vendingmachine.storage.DrinkStorage;
import org.example.vendingmachine.task.DrinkCalculator;
import org.example.vendingmachine.task.DrinkReader;
import org.example.vendingmachine.useraction.DisplayAction;

import java.util.List;

public class DrinkVendingMachineCore implements BaseCore<DisplayAction, Drink> {

    private final DrinkReader reader;
    private final DrinkCalculator calculator;

    public DrinkVendingMachineCore() {
        DrinkStorage storage = DrinkStorage.getInstance();
        reader = new DrinkReader(storage);
        calculator = new DrinkCalculator(storage);
    }

    @Override
    public List<ItemQuantity<Drink>> getItems(DisplayAction userAction) {
        //DisplayAction.ActionData data = userAction.getData();
        //DisplayAction 값으로 분기 처리 필요할 경우 작업
        return reader.getItems();
    }

    @Override
    public ResultData order(Drink item, Payment payment) {
        return calculator.calculate(item, payment);
    }
}
