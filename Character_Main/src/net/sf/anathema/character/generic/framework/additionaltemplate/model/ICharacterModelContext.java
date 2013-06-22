package net.sf.anathema.character.generic.framework.additionaltemplate.model;

import java.util.List;

public interface ICharacterModelContext {

  <T> List<T> getAllRegistered(Class<T> interfaceClass);
}