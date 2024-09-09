package org.example.base.data;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class ItemQuantity {
    @NotNull
    private final ItemInformation item;
    private int quantity;

    public ItemQuantity(@NotNull ItemInformation item, int count) {
        this.item = item;
        this.quantity = count;
    }

    @NotNull
    public ItemInformation getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public boolean isEnoughQuantity() {
        return quantity > 0;
    }

    public boolean isEnoughBudget(int budget) {
        return item.getPrice() <= budget;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemQuantity that = (ItemQuantity) o;
        return quantity == that.quantity && item.equals(that.item);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, quantity);
    }

    @Override
    public String toString() {
        return item + ", 개수 : "+ quantity;
    }
}
