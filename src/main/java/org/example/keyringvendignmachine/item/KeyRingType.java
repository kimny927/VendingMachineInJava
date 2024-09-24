package org.example.keyringvendignmachine.item;

public enum KeyRingType {

    PLASTIC(1000), DOLL(2000),WOOD(500);

    private final int price;

    KeyRingType(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

}
