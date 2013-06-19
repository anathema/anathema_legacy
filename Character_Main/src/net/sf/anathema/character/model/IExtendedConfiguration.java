package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.hero.display.HeroModelGroup;

public interface IExtendedConfiguration {

  IAdditionalModel[] getAdditionalModels();

  IAdditionalModel[] getAdditionalModels(HeroModelGroup type);

  IAdditionalModel getAdditionalModel(String id);
}