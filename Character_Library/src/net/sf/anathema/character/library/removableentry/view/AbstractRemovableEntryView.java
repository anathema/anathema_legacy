package net.sf.anathema.character.library.removableentry.view;

import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntriesView;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;
import net.sf.anathema.lib.gui.IView;

public abstract class AbstractRemovableEntryView<V extends IRemovableEntryView> implements
    IRemovableEntriesView<V>,
    IView {

  public void removeEntryView(IRemovableEntryView removableView) {
    removableView.delete();
  }

  public boolean needsScrollbar() {
    return false;
  }
}