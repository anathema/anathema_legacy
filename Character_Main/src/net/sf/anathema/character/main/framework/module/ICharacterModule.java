package net.sf.anathema.character.main.framework.module;

import net.sf.anathema.character.main.framework.HeroEnvironment;
import net.sf.anathema.initialization.InitializationException;

public interface ICharacterModule {

  void addCharacterTemplates(HeroEnvironment characterGenerics);

  void addAdditionalTemplateData(HeroEnvironment characterGenerics) throws InitializationException;

  void registerCommonData(HeroEnvironment characterGenerics);
}