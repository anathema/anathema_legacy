package net.sf.anathema.character.generic.additionaltemplate;

import net.sf.anathema.character.main.hero.CharacterModelGroup;
import net.sf.anathema.lib.control.IChangeListener;

public interface IAdditionalModel {

  String getTemplateId();

  CharacterModelGroup getAdditionalModelType();

  IAdditionalModelBonusPointCalculator getBonusPointCalculator();

  void addChangeListener(IChangeListener listener);

  IAdditionalModelExperienceCalculator getExperienceCalculator();
}