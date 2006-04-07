package net.sf.anathema.character.library.intvalue;

import net.sf.anathema.framework.value.IIntValueView;
import net.sf.anathema.lib.control.booleanvalue.IBooleanValueChangedListener;

public interface IFavorableIntValueView extends IIntValueView {

  public void addButtonSelectedListener(IBooleanValueChangedListener listener);

  public void setButtonState(boolean selected, boolean enabled);
}