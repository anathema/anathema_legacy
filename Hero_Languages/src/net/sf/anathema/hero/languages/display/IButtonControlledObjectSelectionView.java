package net.sf.anathema.hero.languages.display;

import net.sf.anathema.lib.control.ObjectValueListener;
import net.sf.anathema.lib.gui.selection.IObjectSelectionView;

public interface IButtonControlledObjectSelectionView<V> extends IObjectSelectionView<V> {

  void setButtonEnabled(boolean enabled);

  void addButtonListener(ObjectValueListener<V> listener);
}