package net.sf.anathema.character.martialarts;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;

import static net.sf.anathema.character.martialarts.IMartialArtsSpecialCharms.DRAGON_CLAW_ELEMENTAL_STRIKE;

@CharacterModule
public class MartialArtsCharacterModule extends NullObjectCharacterModuleAdapter {

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    characterGenerics.getCharmProvider().addMartialArtsSpecialCharm(DRAGON_CLAW_ELEMENTAL_STRIKE);
  }
}