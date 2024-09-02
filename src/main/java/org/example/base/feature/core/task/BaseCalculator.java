package org.example.base.feature.core.task;

import org.example.base.data.ItemInformation;
import org.example.base.data.Payment;
import org.example.base.data.ResultData;

public interface BaseCalculator<I extends ItemInformation, P extends Payment> {

    ResultData calculate(I item, P payment);

}
