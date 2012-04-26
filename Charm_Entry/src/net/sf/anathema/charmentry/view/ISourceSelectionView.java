package net.sf.anathema.charmentry.view;

import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.lib.control.IIntValueChangedListener;
import net.sf.anathema.lib.control.IObjectValueChangedListener;

public interface ISourceSelectionView {

  public void addSourceChangeListener(IObjectValueChangedListener<IExaltedSourceBook> listener);

  public void setPageSelectionEnabled(boolean enabled);

  public void addPageChangeListener(IIntValueChangedListener listener);

  public void setObjects(IExaltedSourceBook[] legalSources);
}