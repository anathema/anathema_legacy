package net.sf.anathema.character.martialarts;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.rules.ExaltedEdition;

public class MartialArtsCharacterModule extends NullObjectCharacterModuleAdapter {

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    characterGenerics.getCharmProvider().addMartialArtsSpecialCharm(
        ExaltedEdition.FirstEdition,
        IMartialArtsSpecialCharms.TYPE_EXALT_WAYS);
    characterGenerics.getCharmProvider().addMartialArtsSpecialCharm(
        ExaltedEdition.SecondEdition,
        IMartialArtsSpecialCharms.DRAGON_CLAW_ELEMENTAL_STRIKE);
  }
}