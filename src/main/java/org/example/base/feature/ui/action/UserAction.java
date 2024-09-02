package org.example.base.feature.ui.action;

public interface UserAction<D extends UserAction.Data> {

    interface Data{}

    D getData();
}
