package org.example.base.feature.ui;

import org.example.base.data.ItemInformation;
import org.example.base.data.ItemQuantity;
import org.example.vendingmachine.payment.PaymentAgent;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public interface BaseInterface<Item extends ItemInformation, R> {

    /**
     * 사용자가 구매할 아이템을 선택한다.
     *
     * @param maximumPrice 최대 가격 값. null이거나 Int.MAX_VALUE 일 경우 최대 가격 값의 한도가 없다.
     * @param list         아이템 목록
     * @return 사용자 액션 반환. 다음 작업을 진행 정보를 담는다.
     */
    R chooseItem(@Nullable Integer maximumPrice, List<ItemQuantity<Item>> list);

    /**
     * 사용자가 지불한다.
     */
    R pay(List<PaymentAgent> paymentAgent);

    /**
     * 사용자가 아이템 목록을 본다.
     * 아이템 목록을 노출한다.
     */
    R display(List<ItemQuantity<Item>> itemQuantities);


}
