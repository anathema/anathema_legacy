package net.sf.anathema.character.generic.additionaltemplate;

import net.sf.anathema.lib.control.IChangeListener;

public interface IAdditionalModel {

  String getTemplateId();

  AdditionalModelType getAdditionalModelType();

  IAdditionalModelBonusPointCalculator getBonusPointCalculator();

  void addChangeListener(IChangeListener listener);

  IAdditionalModelExperienceCalculator getExperienceCalculator();
}