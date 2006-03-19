package net.sf.anathema.character.generic.additionaltemplate;

import javax.swing.event.ChangeListener;

public interface IAdditionalModel {

  public String getTemplateId();
  
  public AdditionalModelType getAdditionalModelType();

  public IAdditionalModelBonusPointCalculator getBonusPointCalculator();

  public void addBonusPointsChangeListener(ChangeListener listener);

  public IAdditionalModelExperienceCalculator getExperienceCalculator();
}