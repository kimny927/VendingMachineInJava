package org.example.base;

import org.example.base.feature.ui.BaseInterface;
import org.example.base.feature.ui.action.UserAction;

public abstract class BaseVendingMachine<D extends UserAction.Data, A extends UserAction<D>> implements BaseInterface<D, A> {
    protected BaseInterface<D, A> userInterface;

    @Override
    public A chooseItem(Integer maximumPrice) throws Exception {
        return userInterface.chooseItem(maximumPrice);
    }

    @Override
    public A pay() throws Exception {
        return userInterface.pay();
    }

    @Override
    public A display() throws Exception {
        return userInterface.display();
    }

    protected abstract void interact();
}
