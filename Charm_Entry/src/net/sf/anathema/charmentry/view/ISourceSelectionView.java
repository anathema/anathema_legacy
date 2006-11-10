package net.sf.anathema.charmentry.view;

import net.sf.anathema.character.generic.rules.IExaltedSourceBook;
import net.sf.anathema.lib.control.intvalue.IIntValueChangedListener;
import net.sf.anathema.lib.control.objectvalue.IObjectValueChangedListener;

public interface ISourceSelectionView {

  public void addSourceChangeListener(IObjectValueChangedListener<IExaltedSourceBook> listener);

  public void setPageSelectionEnabled(boolean enabled);

  public void addPageChangeListener(IIntValueChangedListener listener);
}