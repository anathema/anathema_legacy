package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.main.hero.CharacterModelGroup;

public interface IExtendedConfiguration {

  IAdditionalModel[] getAdditionalModels();

  IAdditionalModel[] getAdditionalModels(CharacterModelGroup type);

  IAdditionalModel getAdditionalModel(String id);
}