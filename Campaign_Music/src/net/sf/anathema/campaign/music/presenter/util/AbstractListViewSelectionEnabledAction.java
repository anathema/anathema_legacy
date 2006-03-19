package net.sf.anathema.campaign.music.presenter.util;

import javax.swing.Icon;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import net.disy.commons.swing.action.SmartAction;
import net.sf.anathema.lib.gui.list.actionview.IActionAddableListView;

public abstract class AbstractListViewSelectionEnabledAction extends SmartAction {

  private final IActionAddableListView view;

  public AbstractListViewSelectionEnabledAction(Icon icon, IActionAddableListView view) {
    super(icon);
    this.view = view;
    view.addListSelectionListener(new ListSelectionListener() {
      public void valueChanged(ListSelectionEvent e) {
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

  protected final Object[] getSelectedItems() {
    return view.getSelectedItems();
  }
}