package net.sf.anathema.character.generic.impl.template.magic;

import net.disy.commons.core.predicate.IPredicate;
import net.sf.anathema.character.generic.magic.IMagic;
import net.sf.anathema.character.generic.template.magic.FavoringTraitType;
import net.sf.anathema.character.generic.template.magic.ICharmTemplate;
import net.sf.anathema.character.generic.template.magic.IMagicTemplate;
import net.sf.anathema.character.generic.template.magic.ISpellMagicTemplate;

public class CustomizableMagicTemplate implements IMagicTemplate {

  private final ICharmTemplate charmTemplate;
  private final ISpellMagicTemplate spellTemplate;
  private final IPredicate<IMagic> freePicksPredicate;

  public CustomizableMagicTemplate(
          IPredicate<IMagic> freePicksPredicate,
          ICharmTemplate charmTemplate,
          ISpellMagicTemplate spellTemplate) {
    this.freePicksPredicate = freePicksPredicate;
    this.charmTemplate = charmTemplate;
    this.spellTemplate = spellTemplate;
  }

  public ISpellMagicTemplate getSpellMagic() {
    return spellTemplate;
  }

  public ICharmTemplate getCharmTemplate() {
    return charmTemplate;
  }

  public boolean canBuyFromFreePicks(IMagic magic) {
    return freePicksPredicate.evaluate(magic);
  }

  public FavoringTraitType getFavoringTraitType() {
    return FavoringTraitType.AbilityType;
  }
}