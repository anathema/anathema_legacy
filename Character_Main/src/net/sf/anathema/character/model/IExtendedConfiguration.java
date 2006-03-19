package net.sf.anathema.character.model;

import net.sf.anathema.character.generic.additionaltemplate.AdditionalModelType;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;

public interface IExtendedConfiguration {

  public IAdditionalModel[] getAdditionalModels();

  public IAdditionalModel[] getAdditionalModels(AdditionalModelType type);

  public IAdditionalModel getAdditionalModel(String id);
}