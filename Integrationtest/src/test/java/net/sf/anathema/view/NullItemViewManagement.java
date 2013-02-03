package net.sf.anathema.view;

import net.sf.anathema.framework.view.IItemView;
import net.sf.anathema.framework.view.IItemViewManagement;
import net.sf.anathema.framework.view.IViewSelectionListener;

import javax.swing.*;

public class NullItemViewManagement implements IItemViewManagement {
    @Override
    public void addItemView(IItemView view, Action action) {
        //nothing to do
    }

    @Override
    public void addViewSelectionListener(IViewSelectionListener listener) {
        //nothing to do
    }

    @Override
    public void setSelectedItemView(IItemView view) {
        //nothing to do
    }

    @Override
    public void removeItemView(IItemView view) {
        //nothing to do
    }
}