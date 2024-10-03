package org.example.keyringvendignmachine.storage;

import org.example.base.data.ItemQuantity;
import org.example.base.feature.storage.BaseStorage;
import org.example.keyringvendignmachine.item.KeyRing;
import org.example.keyringvendignmachine.item.KeyRingType;

import java.util.ArrayList;
import java.util.List;

public class KeyRingStorage implements BaseStorage<KeyRing> {

    public KeyRingStorage() {
        addItemQuantity(new KeyRing("곰돌이", "٩ʕ•ﻌ•*ʔو -!", KeyRingType.DOLL), 10);
        addItemQuantity(new KeyRing("고양이", "/ᐠ •ヮ• マ Ⳋ", KeyRingType.WOOD), 5);
        addItemQuantity(new KeyRing("강아지", "૮ .• ﻌ • ა", KeyRingType.PLASTIC), 3);
        addItemQuantity(new KeyRing("물고기", "('-' э )Э", KeyRingType.DOLL), 2);
    }

    private final ArrayList<ItemQuantity<KeyRing>> storage = new ArrayList<>();

    @Override
    public List<ItemQuantity<KeyRing>> addItemQuantity(KeyRing item, int quantity) {
        storage.add(new ItemQuantity<KeyRing>(item, quantity));
        return storage;
    }

    @Override
    public ItemQuantity<KeyRing> getItemQuantity(KeyRing item) {
        for (ItemQuantity<KeyRing> drink : storage) {
            if (drink.getItem().equals(item)) {
                return drink;
            }
        }
        throw new IndexOutOfBoundsException("item 확인. item:" + item);
    }

    @Override
    public ItemQuantity<KeyRing> updateItemQuantity(KeyRing item, int resulQuantity) {
        ItemQuantity<KeyRing> itemQuantity = null;
        for (ItemQuantity<KeyRing> iq : storage) {
            if (iq.getItem().equals(item)) {
                itemQuantity = iq;
                itemQuantity.setQuantity(resulQuantity);
            }
        }
        return itemQuantity;
    }

    @Override
    public List<ItemQuantity<KeyRing>> getItemQuantities() {
        return storage;
    }

    @Override
    public ItemQuantity<KeyRing> getItemQuantity(int index) {
        if (index >= 0 && index < storage.size()) {
            return storage.get(index);
        }
        throw new IndexOutOfBoundsException("index 확인. index:" + index);
    }

    @Override
    public ItemQuantity<KeyRing> updateItemQuantity(int index, int resultQuantity) {
        if (index >= 0 && index < storage.size()) {
            ItemQuantity<KeyRing> drink = storage.get(index);
            drink.setQuantity(resultQuantity);
            return drink;
        }
        throw new IndexOutOfBoundsException();
    }
}
