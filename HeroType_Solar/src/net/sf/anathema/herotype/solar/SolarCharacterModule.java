package net.sf.anathema.herotype.solar;

import net.sf.anathema.character.main.framework.HeroEnvironment;
import net.sf.anathema.character.main.framework.module.CharacterModule;
import net.sf.anathema.character.main.framework.module.CharacterTypeModule;
import net.sf.anathema.herotype.solar.model.SolarCharacterType;

@CharacterModule
public class SolarCharacterModule extends CharacterTypeModule {

  public SolarCharacterModule() {
    super(new SolarCharacterType());
  }

  @Override
  public void registerCommonData(HeroEnvironment characterGenerics) {
  }
}