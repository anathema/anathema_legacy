package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;

public interface IExtendedConfiguration {

  IAdditionalModel[] getAdditionalModels();

  IAdditionalModel[] getAdditionalModels(AdditionalModelType type);

  IAdditionalModel getAdditionalModel(String id);
}