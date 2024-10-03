package org.example.keyringvendignmachine.item;

import org.example.base.data.ItemInformation;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class KeyRing extends ItemInformation {

    @NotNull
    private final KeyRingType type;

    @NotNull
    private final String print;

    public KeyRing(@NotNull String name, @NotNull String print, @NotNull KeyRingType type) {
        super(name, type.getPrice());
        this.print = print;
        this.type = type;
    }

    @NotNull
    public String getPrint() {
        return print;
    }

    @NotNull
    public KeyRingType getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        KeyRing keyRing = (KeyRing) o;
        return type == keyRing.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), type);
    }

    @Override
    public String toString() {
        return "키링 :"+getPrint() + ", 이름:" +getName() + ", 종류: "+ getType() + ", 가격 : "+getPrice();
    }
}
