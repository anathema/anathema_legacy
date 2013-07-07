package net.sf.anathema.character.main.library.selection;

import net.sf.anathema.character.main.library.removableentry.IRemovableEntryModel;
import net.sf.anathema.hero.change.FlavoredChangeListener;

public interface IStringEntryTraitModel<V> extends IRemovableEntryModel<V> {

  void setCurrentName(String newValue);

  void addChangeListener(FlavoredChangeListener listener);
}