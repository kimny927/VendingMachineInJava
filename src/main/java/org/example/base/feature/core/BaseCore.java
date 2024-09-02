package org.example.base.feature.core;

import org.example.base.data.ItemInformation;
import org.example.base.data.ItemQuantity;
import org.example.base.data.Payment;
import org.example.base.data.ResultData;
import org.example.base.feature.ui.action.UserAction;

import java.util.List;

public interface BaseCore<U extends UserAction<? extends UserAction.Data>, I extends ItemInformation> {

    List<ItemQuantity<I>> getItems(U userAction);

    ResultData order(I item, Payment payment);

}
