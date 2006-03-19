package net.sf.anathema.character.generic.framework.magic.view;

import net.sf.anathema.framework.value.IIntValueView;

public interface IMultiLearnableCharmView extends ISpecialCharmView {

  public IIntValueView addCategory(String labelText, int maxValue, int value);
}