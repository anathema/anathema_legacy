package net.sf.anathema.character.generic.additionaltemplate;

import net.sf.anathema.lib.control.change.IChangeListener;

public abstract class AbstractAdditionalModelAdapter implements IAdditionalModel {

  public void addBonusPointsChangeListener(IChangeListener listener) {
    // nothing to do
  }

  public IAdditionalModelBonusPointCalculator getBonusPointCalculator() {
    return new NullAdditionalModelBonusPointCalculator();
  }

  public IAdditionalModelExperienceCalculator getExperienceCalculator() {
    return new NullAdditionalModelExperienceCalculator();
  }
}