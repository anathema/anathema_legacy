package net.sf.anathema.character.library.removableentry.view;

import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryTabView;
import net.sf.anathema.character.library.removableentry.presenter.IRemovableEntryView;

public abstract class AbstractRemovableEntryView<V extends IRemovableEntryView> implements IRemovableEntryTabView<V> {

  public void removeEntryView(IRemovableEntryView removableView) {
    removableView.delete();
  }
}