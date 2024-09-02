package org.example.vendingmachine.useraction;

import org.example.base.feature.ui.action.UserAction;

public abstract class DrinkUserAction implements UserAction<DrinkUserAction.DrinkData> {

    public static class DrinkData implements UserAction.Data {}

}
