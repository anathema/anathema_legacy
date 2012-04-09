package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;

import java.util.List;

public interface ICharacterModelContext {

  IAdditionalRules getAdditionalRules();

  IBasicCharacterData getBasicCharacterContext();

  IPresentationProperties getPresentationProperties();

  ICharacterListening getCharacterListening();

  ICharmContext getCharmContext();

  IMagicCollection getMagicCollection();

  IGenericTraitCollection getTraitCollection();

  ITraitContext getTraitContext();

  IGenericSpecialtyContext getSpecialtyContext();

  IAdditionalModel getAdditionalModel(String id);

  boolean isFullyLoaded();

  void setFullyLoaded(boolean loaded);

  <T> List<T> getAllRegistered(Class<T> interfaceClass);
}