package org.example.base.data;

public interface Payment {
    int getBudget();

    /**
     *
     * @param price 지불할 가격
     * @return 지불 가능할 경우 budget 값은 price 값을 차감하고 true 반환, 지불 불가능할 경우 budget 값 변경 없이 false
     */
    boolean spend(int price);

    String toString();
}
