package net.sf.anathema.character.main.framework.module;

import net.sf.anathema.character.main.framework.ICharacterGenerics;
import net.sf.anathema.initialization.InitializationException;

public interface ICharacterModule {

  void addCharacterTemplates(ICharacterGenerics characterGenerics);

  void addAdditionalTemplateData(ICharacterGenerics characterGenerics) throws InitializationException;

  void registerCommonData(ICharacterGenerics characterGenerics);
}