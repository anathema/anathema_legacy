package net.sf.anathema.charmentry.view;

import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import net.sf.anathema.lib.control.ObjectValueListener;

public interface ISourceSelectionView {

  void addSourceChangeListener(ObjectValueListener<IExaltedSourceBook> listener);

  void setPageSelectionEnabled(boolean enabled);

  void addPageChangeListener(IIntValueChangedListener listener);

  void setObjects(IExaltedSourceBook[] legalSources);
}