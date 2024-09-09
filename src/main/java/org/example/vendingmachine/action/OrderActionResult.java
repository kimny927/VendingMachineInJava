package org.example.vendingmachine.action;

import org.example.base.data.OrderResultData;
import org.jetbrains.annotations.NotNull;

public class OrderActionResult extends DrinkActionResult<OrderResultData> {
    @NotNull
    private final OrderResultData data;

    public OrderActionResult(@NotNull OrderResultData data) {
        this.data = data;
    }

    @Override
    public boolean isSucceed() {
        return data instanceof OrderResultData.SuccessResult;
    }

    @NotNull
    @Override
    public OrderResultData getData() {
        return data;
    }

}
