package org.example.base.feature.ui;

import org.example.base.feature.ui.action.UserAction;
import org.jetbrains.annotations.Nullable;

public interface BaseInterface<D extends UserAction.Data, U extends UserAction<D>> {

    /**
     * 사용자가 구매할 아이템을 선택한다.
     * @param maximumPrice 최대 가격 값. null이거나 Int.MAX_VALUE 일 경우 최대 가격 값의 한도가 없다.
     * @return 사용자 액션 반환. 다음 작업을 진행 정보를 담는다.
     * @throws Exception
     */
    U chooseItem(@Nullable Integer maximumPrice) throws Exception;

    /**
     * 사용자가 지불한다.
     */
    U pay() throws Exception;

    /**
     * 사용자가 아이템 목록을 본다.
     * 아이템 목록을 노출한다.
     */
    U display() throws Exception;


}
