package org.example.base.feature.core.worker;

import org.example.base.data.ItemInformation;
import org.example.base.data.Payment;
import org.example.base.data.OrderResultData;

public abstract class BaseCalculator<I extends ItemInformation, P extends Payment> implements Worker {

    abstract public OrderResultData calculate(I item, P payment);

}
