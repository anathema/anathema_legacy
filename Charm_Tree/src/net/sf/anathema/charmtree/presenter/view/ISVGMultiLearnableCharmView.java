package net.sf.anathema.charmtree.presenter.view;

import net.sf.anathema.framework.value.IIntValueView;

public interface ISVGMultiLearnableCharmView extends ISVGSpecialCharmView {

  public IIntValueView addCategory(String labelText, int maxValue, int value);
}