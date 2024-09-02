package org.example.base.data;

import java.util.Objects;

public class ItemInformation {
    private final String name;
    private final int price;

    public ItemInformation(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemInformation itemInfo = (ItemInformation) o;
        return price == itemInfo.price && name.equals(itemInfo.name);
    }

    @Override
    public String toString() {
        return "물품 - 이름 : " + name + ", 가격 : " + price;
    }
}
