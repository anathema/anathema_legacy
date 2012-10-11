package net.sf.anathema.campaign.music.presenter.util;

import net.sf.anathema.lib.gui.action.SmartAction;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;

import javax.swing.Icon;

public abstract class AbstractListViewSelectionEnabledAction<V> extends SmartAction {

  private final IActionAddableListView<V> view;

  public AbstractListViewSelectionEnabledAction(Icon icon, IActionAddableListView<V> view) {
    super(icon);
    this.view = view;
    view.addListSelectionListener(new Runnable() {
      @Override
      public void run() {
        updateEnabled();
      }
    });
    updateEnabled();
  }

  protected final boolean isSelectionEmpty() {
    return view.getSelectedItems().length > 0;
  }

  private void updateEnabled() {
    setEnabled(isSelectionEmpty());
  }

  protected final V[] getSelectedItems() {
    return view.getSelectedItems();
  }
}