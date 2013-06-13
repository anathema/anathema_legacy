package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;

public interface IExtendedConfiguration {

  IAdditionalModel[] getAdditionalModels();

  IAdditionalModel[] getAdditionalModels(CharacterModelGroup type);

  IAdditionalModel getAdditionalModel(String id);
}