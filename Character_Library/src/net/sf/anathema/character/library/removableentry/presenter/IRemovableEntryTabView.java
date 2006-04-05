package net.sf.anathema.character.library.removableentry.presenter;

import javax.swing.Icon;

import net.sf.anathema.framework.presenter.view.ISimpleTabView;

public interface IRemovableEntryTabView<V extends IRemovableEntryView> extends ISimpleTabView {

  public V addEntryView(Icon removeIcon, String string);

  public void removeEntryView(IRemovableEntryView removableView);
}