package net.sf.anathema.character.main.framework.module;

import net.sf.anathema.character.main.framework.HeroEnvironment;

public interface ICharacterModule {

  void addCharacterTemplates(HeroEnvironment characterGenerics);

  void registerCommonData(HeroEnvironment characterGenerics);
}