package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.character.generic.IBasicCharacterData;
import net.sf.anathema.character.generic.character.IGenericTraitCollection;
import net.sf.anathema.character.generic.character.IMagicCollection;

import java.util.List;

public interface ICharacterModelContext {

  IBasicCharacterData getBasicCharacterContext();

  IMagicCollection getMagicCollection();

  IGenericTraitCollection getTraitCollection();

  IGenericSpecialtyContext getSpecialtyContext();

  <T> List<T> getAllRegistered(Class<T> interfaceClass);
}