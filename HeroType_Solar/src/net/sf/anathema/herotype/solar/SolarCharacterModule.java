package net.sf.anathema.herotype.solar;

import net.sf.anathema.character.main.caste.CasteCollection;
import net.sf.anathema.character.main.framework.HeroEnvironment;
import net.sf.anathema.character.main.framework.module.CharacterModule;
import net.sf.anathema.character.main.framework.module.CharacterTypeModule;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.herotype.solar.model.SolarCaste;
import net.sf.anathema.herotype.solar.model.SolarCharacterType;

@CharacterModule
public class SolarCharacterModule extends CharacterTypeModule {

  private static final ICharacterType type = new SolarCharacterType();

  protected SolarCharacterModule() {
    super(type);
  }

  @Override
  public void registerCommonData(HeroEnvironment characterGenerics) {
    characterGenerics.getCasteCollectionRegistry().register(type, new CasteCollection(SolarCaste.values()));
  }
}