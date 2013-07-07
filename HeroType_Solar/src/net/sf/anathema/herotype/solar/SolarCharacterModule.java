package net.sf.anathema.herotype.solar;

import net.sf.anathema.character.main.framework.ICharacterGenerics;
import net.sf.anathema.character.main.framework.module.CharacterModule;
import net.sf.anathema.character.main.framework.module.CharacterTypeModule;
import net.sf.anathema.character.main.caste.CasteCollection;
import net.sf.anathema.character.main.template.TemplateType;
import net.sf.anathema.character.main.type.ICharacterType;
import net.sf.anathema.herotype.solar.model.SolarCaste;
import net.sf.anathema.herotype.solar.model.SolarCharacterType;

@CharacterModule
public class SolarCharacterModule extends CharacterTypeModule {

  public static final ICharacterType type = new SolarCharacterType();
  @SuppressWarnings("unused")
  private static final TemplateType solarTemplateType = new TemplateType(type);

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    characterGenerics.getCasteCollectionRegistry().register(type, new CasteCollection(SolarCaste.values()));
  }

  @Override
  protected ICharacterType getType() {
	  return type;
  }
}
