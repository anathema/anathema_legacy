package net.sf.anathema.character.generic.framework.module;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.initialization.InitializationException;

public interface ICharacterModule {

  void addCharacterTemplates(ICharacterGenerics characterGenerics);

  void addBackgroundTemplates(ICharacterGenerics generics);

  void addAdditionalTemplateData(ICharacterGenerics characterGenerics) throws InitializationException;

  void registerCommonData(ICharacterGenerics characterGenerics);
}