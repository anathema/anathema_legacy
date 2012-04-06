package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.additionalrules.IAdditionalRules;
import net.sf.anathema.character.generic.additionaltemplate.IAdditionalModel;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.IMagicCollection;
import net.sf.anathema.character.generic.template.presentation.IPresentationProperties;

import java.util.List;

public interface ICharacterModelContext {

  public IAdditionalRules getAdditionalRules();

  public IBasicCharacterData getBasicCharacterContext();

  public IPresentationProperties getPresentationProperties();

  public ICharacterListening getCharacterListening();

  public ICharmContext getCharmContext();

  public IMagicCollection getMagicCollection();

  public IGenericTraitCollection getTraitCollection();

  public ITraitContext getTraitContext();

  public IGenericSpecialtyContext getSpecialtyContext();

  public IAdditionalModel getAdditionalModel(String id);

  public boolean isFullyLoaded();

  public void setFullyLoaded(boolean loaded);

  <T> List<T> getAllRegistered(Class<T> interfaceClass);
}