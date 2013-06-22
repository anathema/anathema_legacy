package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import net.sf.anathema.character.generic.character.IGenericTraitCollection;

import java.util.List;

public interface ICharacterModelContext {

  IGenericSpecialtyContext getSpecialtyContext();

  <T> List<T> getAllRegistered(Class<T> interfaceClass);
}