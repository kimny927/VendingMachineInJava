package org.example.base.feature.ui.action;

public interface ActionResult<D> {

    boolean isSucceed();

    D getData();
}
