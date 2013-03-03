package net.sf.anathema.campaign.music.impl.view.library;

import com.google.common.base.Preconditions;
import net.sf.anathema.campaign.music.impl.view.TabbedView;
import net.sf.anathema.campaign.music.model.track.IMp3Track;
import net.sf.anathema.lib.gui.list.actionview.ActionAddableListView;
import net.sf.anathema.lib.gui.list.actionview.NamedActionAddableListView;

import javax.swing.Action;

public class TabbedActionListView implements NamedActionAddableListView<IMp3Track> {
  private final TabbedView namedView;
  private final ActionAddableListView<IMp3Track> listView;

  public TabbedActionListView(TabbedView namedView, ActionAddableListView<IMp3Track> listView) {
    this.namedView = namedView;
    this.listView = listView;
  }

  @Override
  public void setListTitle(String title) {
    Preconditions.checkArgument(namedView.hasExactlyOneTab(), "More than one tab - confused");
    namedView.setTitle(title);
  }

  @Override
  public void setObjects(IMp3Track[] items) {
    listView.setObjects(items);
  }

  @Override
  public void addListSelectionListener(Runnable listener) {
    listView.addListSelectionListener(listener);
  }

  @Override
  public IMp3Track[] getSelectedItems() {
    return listView.getSelectedItems();
  }

  @Override
  public void addAction(Action action) {
    listView.addAction(action);
  }

  @Override
  public void refreshView() {
    listView.refreshView();
  }
}
