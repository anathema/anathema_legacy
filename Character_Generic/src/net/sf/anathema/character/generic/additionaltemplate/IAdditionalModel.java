package net.sf.anathema.character.generic.additionaltemplate;

import net.sf.anathema.lib.control.change.IChangeListener;

public interface IAdditionalModel {

  public String getTemplateId();

  public AdditionalModelType getAdditionalModelType();

  public IAdditionalModelBonusPointCalculator getBonusPointCalculator();

  public void addBonusPointsChangeListener(IChangeListener listener);

  public IAdditionalModelExperienceCalculator getExperienceCalculator();
}