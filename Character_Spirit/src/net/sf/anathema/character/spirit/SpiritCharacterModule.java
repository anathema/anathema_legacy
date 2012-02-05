package net.sf.anathema.character.spirit;

import net.sf.anathema.character.generic.framework.ICharacterGenerics;
import net.sf.anathema.character.generic.framework.magic.FirstExcellency;
import net.sf.anathema.character.generic.framework.magic.SecondExcellency;
import net.sf.anathema.character.generic.framework.magic.ThirdExcellency;
import net.sf.anathema.character.generic.framework.module.CharacterModule;
import net.sf.anathema.character.generic.framework.module.NullObjectCharacterModuleAdapter;
import net.sf.anathema.character.generic.impl.rules.ExaltedSourceBook;
import net.sf.anathema.character.generic.magic.IMagicStats;
import net.sf.anathema.character.spirit.generic.DivineSubordination;
import net.sf.anathema.character.spirit.generic.InfiniteMastery;

import static net.sf.anathema.character.generic.type.CharacterType.SPIRIT;

@CharacterModule
public class SpiritCharacterModule extends NullObjectCharacterModuleAdapter {

  @Override
  public void registerCommonData(ICharacterGenerics characterGenerics) {
    FirstExcellency firstExcellency = new FirstExcellency(SPIRIT, ExaltedSourceBook.SecondEdition, "1 m per die");
    SecondExcellency secondExcellency = new SecondExcellency(SPIRIT, ExaltedSourceBook.SecondEdition);
    ThirdExcellency thirdExcellency = new ThirdExcellency(SPIRIT, "4 m", ExaltedSourceBook.SecondEdition);
    characterGenerics.getGenericCharmStatsRegistry()
            .register(SPIRIT, new IMagicStats[]{firstExcellency, secondExcellency, thirdExcellency, new InfiniteMastery(), new DivineSubordination()});
  }

  @Override
  public void addCharacterTemplates(ICharacterGenerics characterGenerics) {
    registerParsedTemplate(characterGenerics, "template/Spirit2nd.template"); //$NON-NLS-1$
  }
}